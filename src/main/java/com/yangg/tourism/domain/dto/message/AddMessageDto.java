package com.yangg.tourism.domain.dto.message;

import com.yangg.tourism.domain.entity.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @description: 新增消息通知 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddMessageDto", description = "新增消息通知请求参数")
public class AddMessageDto implements Serializable {
    /**
     * 消息类型
     */
    @Schema(description = "消息类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;

    /**
     * 消息内容
     */
    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    /**
     * 消息参数(json 格式存储）
     */
    @Schema(description = "消息参数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String args;

    /**
     * 发送者id（系统默认0）
     */
    @Schema(description = "发送者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer sendUser;

    /**
     * 接收者id
     */
    @Schema(description = "接收者id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer receiveUser;

    /**
     * 状态
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    public static Message objToMessage(AddMessageDto addMessageDto) {
        if (addMessageDto == null) {
            return null;
        }

        Message message = new Message();
        BeanUtils.copyProperties(addMessageDto, message);
        return message;
    }
}
