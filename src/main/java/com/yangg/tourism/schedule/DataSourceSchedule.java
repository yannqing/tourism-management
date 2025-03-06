package com.yangg.tourism.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangg.tourism.domain.entity.DatabaseMonitor;
import com.yangg.tourism.service.DatabaseMonitorService;
import com.yangg.tourism.utils.RedisCache;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class DataSourceSchedule {

    @Resource
    private RedisCache redisCache;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DatabaseMonitorService databaseMonitorService;

    @PostConstruct
    public void init() {
        log.info("定时任务：<数据库监控> 启动！");
    }

    @Scheduled(cron = "0 * * * * ?") // 每分钟执行一次
    public void saveMySQLPerformanceData() throws ParseException {
        saveSystemToDatabase();
    }

    /**
     * 将数据库里的系统监控表的数据同步到新建表中
     */
    public void saveSystemToDatabase() throws ParseException {
        // 定义日期时间格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        String dateStr = redisCache.getCacheObject("async:database-monitor");
        if (dateStr == null) {
            dateStr = formatter.format(new Date());
        }

        String sql = "SELECT * FROM performance_schema.events_statements_summary_by_digest where LAST_SEEN > ?;";


        List<DatabaseMonitor> databaseMonitors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DatabaseMonitor.class), formatter.parse(dateStr));
        log.info("=====数据库监控同步数据条目：{}=====", databaseMonitors.size());
        // 更新时间
        databaseMonitors.forEach(databaseMonitor -> {
            DatabaseMonitor monitor = databaseMonitorService.getOne(new QueryWrapper<DatabaseMonitor>().eq("SCHEMA_NAME", databaseMonitor.getSchemaName()).eq("DIGEST", databaseMonitor.getDigest()));
            if (monitor != null) {
                databaseMonitorService.update(monitor, new QueryWrapper<DatabaseMonitor>().eq("SCHEMA_NAME", databaseMonitor.getSchemaName()).eq("DIGEST", databaseMonitor.getDigest()));
            } else {
                databaseMonitorService.save(databaseMonitor);
            }
        });
        redisCache.setCacheObject("async:database-monitor", formatter.format(new Date()));
        log.info("=====数据库同步数据: 完成=====");
    }
}
