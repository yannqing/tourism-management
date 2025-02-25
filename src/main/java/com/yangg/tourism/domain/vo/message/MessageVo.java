package com.yangg.tourism.domain.vo.message;

import com.yangg.tourism.domain.entity.Message;
import com.yangg.tourism.domain.vo.user.UserVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 封装消息通知vo（普通用户）
 * @author: yannqing
 * @create: 2025-02-14 17:38
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class MessageVo implements Serializable {
    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送者
     */
    private UserVo sendUser;

    /**
     * 接收者
     */
    private UserVo receiveUser;

    /**
     * 状态
     */
    private Integer status;
    @Serial
    private static final long serialVersionUID = 1L;

    public static MessageVo objToVo(Message message) {
        if (message == null) {
            return null;
        }

        MessageVo messageVo = new MessageVo();
        BeanUtils.copyProperties(message, messageVo);
        return messageVo;
    }
}
