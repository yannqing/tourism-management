package com.yannqing.template.domain.dto.announcement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.yannqing.template.domain.entity.Announcement;
import com.yannqing.template.domain.entity.CostType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 更新公告 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateAnnouncementDto", description = "修改公告对象")
public class UpdateAnnouncementDto {

    /**
     * 公告id
     */
    @Schema(description = "公告id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 公告标题
     */
    @Schema(description = "公告标题", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String noticeTitle;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String noticeContent;

    /**
     * 公告类型：通知/新闻/紧急公告
     */
    @Schema(description = "公告类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;

    /**
     * 发布时间
     */
    @Schema(description = "发布时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date publishTime;

    /**
     * 发布者id
     */
    @Schema(description = "发布者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer publishUser;

    /**
     * 是否置顶（0否，1是）
     */
    @Schema(description = "是否置顶", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer isTop;

    /**
     * 状态（0未发布，1已发布）
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    public static Announcement objToAnnouncement(UpdateAnnouncementDto updateAnnouncementDto) {
        if (updateAnnouncementDto == null) {
            return null;
        }

        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(updateAnnouncementDto, announcement);
        return announcement;
    }
}
