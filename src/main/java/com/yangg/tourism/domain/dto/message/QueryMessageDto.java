package com.yangg.tourism.domain.dto.message;

import com.yangg.tourism.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询消息通知Dto
 * @author: yannqing
 * @create: 2025-02-14 17:15
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryTakeoutsDto", description = "查询所有消息通知请求参数")
public class QueryMessageDto extends PageRequest implements Serializable {
    /**
     * 消息类型
     */
    @Schema(description = "消息类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;

    /**
     * 消息内容
     */
    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String content;

    /**
     * 发送者id（系统默认0）
     */
    @Schema(description = "发送者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer sendUser;

    /**
     * 接收者id
     */
    @Schema(description = "接收者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer receiveUser;

    /**
     * 状态
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 创建时间（下单时间）
     */
    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date updateTime;
}
