package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.announcement.AddAnnouncementDto;
import com.yangg.tourism.domain.dto.announcement.QueryAnnouncementDto;
import com.yangg.tourism.domain.dto.announcement.UpdateAnnouncementDto;
import com.yangg.tourism.domain.entity.Announcement;
import com.yangg.tourism.domain.vo.announcement.AnnouncementVo;
import com.yangg.tourism.domain.vo.user.UserVo;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.UserMapper;
import com.yangg.tourism.service.AnnouncementService;
import com.yangg.tourism.mapper.AnnouncementMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author 67121
* @description 针对表【announcement】的数据库操作Service实现
* @createDate 2025-02-08 17:11:24
*/
@Slf4j
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
    implements AnnouncementService{

    @Resource
    private UserMapper userMapper;

    /**
     * 查询全部公告内容
     * @param queryAnnouncementDto 查询的请求参数 dto
     * @return 返回查询结果
     */
    @Override
    public Page<AnnouncementVo> getAllAnnouncements(QueryAnnouncementDto queryAnnouncementDto) {
        // 判空
        if (queryAnnouncementDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 构建查询参数
        Integer id = queryAnnouncementDto.getId();
        String noticeTitle = queryAnnouncementDto.getNoticeTitle();
        String noticeContent = queryAnnouncementDto.getNoticeContent();
        Integer type = queryAnnouncementDto.getType();
        Date publishTime = queryAnnouncementDto.getPublishTime();
        Integer publishUser = queryAnnouncementDto.getPublishUser();
        Integer isTop = queryAnnouncementDto.getIsTop();
        Integer status = queryAnnouncementDto.getStatus();
        String description = queryAnnouncementDto.getDescription();

        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id)
                    .like(StringUtils.isNotBlank(noticeTitle), "noticeTitle", noticeTitle)
                    .like(StringUtils.isNotBlank(noticeContent), "noticeContent", noticeContent)
                    .eq(type!= null, "type", type)
                    .like(publishTime!= null, "publishTime", publishTime)
                    .eq(publishUser!= null, "publishUser", publishUser)
                    .eq(isTop!= null, "isTop", isTop)
                    .eq(status!= null, "status", status)
                    .like(StringUtils.isNotBlank(description), "description", description);

        // 查询并返回结果
        Page<Announcement> page = this.page(new Page<>(queryAnnouncementDto.getCurrent(), queryAnnouncementDto.getPageSize()), queryWrapper);
        // 转换 Announcement 到 AnnouncementVo
        List<AnnouncementVo> announcementVos = page.getRecords().stream()
                .map(announcement -> {
                    AnnouncementVo announcementVo = AnnouncementVo.announcementToVo(announcement);
                    // 假设有一个方法 getUserVoById 来获取 UserVo
                    UserVo userVo = UserVo.objToVo(userMapper.selectById(announcement.getPublishUser()));
                    announcementVo.setPublishUser(userVo); // 设置转换后的 UserVo
                    return announcementVo;
                })
                .collect(Collectors.toList());

        log.info("查询所有公告");
        // 构建 分页结果 Page 并返回
        return new Page<AnnouncementVo>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(announcementVos);
    }

    /**
     * 更新公告内容
     * @param updateAnnouncementDto 更新的请求参数 dto
     * @return 返回更新的结果
     */
    @Override
    public boolean updateAnnouncement(UpdateAnnouncementDto updateAnnouncementDto) {
        // 判空
        Optional.ofNullable(updateAnnouncementDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateCostId = updateAnnouncementDto.getId();
        Optional.ofNullable(this.getById(updateCostId))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        Announcement updateAnnouncement = UpdateAnnouncementDto.objToAnnouncement(updateAnnouncementDto);

        boolean updateResult = this.updateById(updateAnnouncement);
        log.info("更新公告");

        return updateResult;
    }

    /**
     * 新增公告内容
     * @param addAnnouncementDto 新增的请求参数 dto
     * @return 返回新增的结果
     */
    @Override
    public boolean addAnnouncement(AddAnnouncementDto addAnnouncementDto) {
        // 判空
        Optional.ofNullable(addAnnouncementDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Announcement addAnnouncement = AddAnnouncementDto.objToAnnouncement(addAnnouncementDto);

        boolean saveResult = this.save(addAnnouncement);
        log.info("新增公告");

        return saveResult;
    }

    /**
     * 删除单个公告
     * @param id 要删除的 id
     * @return 返回删除的结果
     */
    @Override
    public boolean deleteAnnouncement(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        // 删除并返回结果
        boolean deleteResult = this.removeById(id);
        log.info("删除公告");

        return deleteResult;
    }

    /**
     * 批量删除公告
     * @param announcementIds 要删除的 id 数组
     * @return 返回删除的结果
     */
    @Override
    public boolean deleteBatchAnnouncement(Integer... announcementIds) {
        // 判空
        if (announcementIds == null || announcementIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<Announcement> costList = this.listByIds(Arrays.asList(announcementIds));
        if (costList.size()!= announcementIds.length) {
            throw new BusinessException(ErrorType.COST_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(announcementIds));
        log.info("批量删除公告");

        return deleteResult > 0;
    }
}




