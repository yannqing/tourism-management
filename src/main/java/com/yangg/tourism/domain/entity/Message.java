package com.yangg.tourism.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息通知表
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * 消息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 消息内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 消息参数
     */
    @TableField(value = "args")
    private String args;

    /**
     * 发送者id（系统默认0）
     */
    @TableField(value = "sendUser")
    private Integer sendUser;

    /**
     * 接收者id
     */
    @TableField(value = "receiveUser")
    private Integer receiveUser;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间（下单时间）
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "isDelete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Message other = (Message) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getArgs() == null ? other.getArgs() == null : this.getArgs().equals(other.getArgs()))
            && (this.getSendUser() == null ? other.getSendUser() == null : this.getSendUser().equals(other.getSendUser()))
            && (this.getReceiveUser() == null ? other.getReceiveUser() == null : this.getReceiveUser().equals(other.getReceiveUser()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getArgs() == null) ? 0 : getArgs().hashCode());
        result = prime * result + ((getSendUser() == null) ? 0 : getSendUser().hashCode());
        result = prime * result + ((getReceiveUser() == null) ? 0 : getReceiveUser().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", args=").append(args);
        sb.append(", sendUser=").append(sendUser);
        sb.append(", receiveUser=").append(receiveUser);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}