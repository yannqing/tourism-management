package com.yangg.tourism.domain.dto.message;

import com.yangg.tourism.domain.entity.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @description: 更新消息通知 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateMessageDto", description = "修改消息通知对象")
public class UpdateMessageDto {
    /**
     * 消息id
     */
    @Schema(description = "消息id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

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
    @Schema(description = "发送者id（系统默认0）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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

    public static Message objToMessage(UpdateMessageDto updateMessageDto) {
        if (updateMessageDto == null) {
            return null;
        }

        Message message = new Message();
        BeanUtils.copyProperties(updateMessageDto, message);
        return message;
    }
}
