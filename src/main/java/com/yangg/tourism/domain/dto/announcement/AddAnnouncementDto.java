package com.yangg.tourism.domain.dto.announcement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.yangg.tourism.domain.entity.Announcement;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 新增公告 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddAnnouncementDto", description = "新增公告请求参数")
public class AddAnnouncementDto implements Serializable {

    /**
     * 公告标题
     */
    @Schema(description = "公告标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String noticeTitle;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String noticeContent;

    /**
     * 公告类型：通知/新闻/紧急公告
     */
    @Schema(description = "公告类型（默认：通知）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    @Schema(description = "是否置顶（默认：否）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer isTop;

    /**
     * 状态（0未发布，1已发布）
     */
    @Schema(description = "状态（默认：已发布）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;

    public static Announcement objToAnnouncement(AddAnnouncementDto addAnnouncementDto) {
        if (addAnnouncementDto == null) {
            return null;
        }

        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(addAnnouncementDto, announcement);
        return announcement;
    }
}
