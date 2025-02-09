package com.yannqing.template.domain.dto.announcement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.yannqing.template.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询所有公告 dto
 * @author: yannqing
 * @create: 2025-02-08 17:18
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryAnnouncementDto", description = "查询所有公告请求参数")
public class QueryAnnouncementDto extends PageRequest implements Serializable {

    /**
     * 公告id
     */
    @Schema(description = "公告id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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

    @Serial
    private static final long serialVersionUID = 1L;
}
