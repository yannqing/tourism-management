package com.yannqing.template.domain.vo.announcement;

import com.yannqing.template.domain.entity.Announcement;
import com.yannqing.template.domain.vo.user.UserVo;
import com.yannqing.template.enums.ErrorType;
import com.yannqing.template.exception.BusinessException;
import com.yannqing.template.mapper.UserMapper;
import com.yannqing.template.service.impl.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 查询公告返回封装类
 * @author: yannqing
 * @create: 2025-02-09 13:59
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class AnnouncementVo {

    /**
     * 公告id
     */
    private Integer id;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告类型：通知/新闻/紧急公告
     */
    private Integer type;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 发布者
     */
    private UserVo publishUser;

    /**
     * 是否置顶（0否，1是）
     */
    private Integer isTop;

    /**
     * 状态（0未发布，1已发布）
     */
    private Integer status;

    /**
     * 备注
     */
    private String description;

    public static AnnouncementVo announcementToVo(Announcement announcement) {
        if (announcement == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        AnnouncementVo announcementVo = new AnnouncementVo();
        BeanUtils.copyProperties(announcement, announcementVo);
        return announcementVo;
    }
}
