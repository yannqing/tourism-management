package com.yannqing.template.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName announcement
 */
@TableName(value ="announcement")
@Data
public class Announcement implements Serializable {
    /**
     * 公告id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公告标题
     */
    @TableField(value = "noticeTitle")
    private String noticeTitle;

    /**
     * 公告内容
     */
    @TableField(value = "noticeContent")
    private String noticeContent;

    /**
     * 公告类型：通知/新闻/紧急公告
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 发布时间
     */
    @TableField(value = "publishTime")
    private Date publishTime;

    /**
     * 发布者id
     */
    @TableField(value = "publishUser")
    private Integer publishUser;

    /**
     * 是否置顶（0否，1是）
     */
    @TableField(value = "isTop")
    private Integer isTop;

    /**
     * 状态（0未发布，1已发布）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
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
    @TableLogic
    private Integer isDelete;

    /**
     * 备注
     */
    @TableField(value = "description")
    private String description;

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
        Announcement other = (Announcement) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNoticeTitle() == null ? other.getNoticeTitle() == null : this.getNoticeTitle().equals(other.getNoticeTitle()))
            && (this.getNoticeContent() == null ? other.getNoticeContent() == null : this.getNoticeContent().equals(other.getNoticeContent()))
            && (this.getPublishTime() == null ? other.getPublishTime() == null : this.getPublishTime().equals(other.getPublishTime()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPublishUser() == null ? other.getPublishUser() == null : this.getPublishUser().equals(other.getPublishUser()))
            && (this.getIsTop() == null ? other.getIsTop() == null : this.getIsTop().equals(other.getIsTop()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNoticeTitle() == null) ? 0 : getNoticeTitle().hashCode());
        result = prime * result + ((getNoticeContent() == null) ? 0 : getNoticeContent().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());
        result = prime * result + ((getPublishUser() == null) ? 0 : getPublishUser().hashCode());
        result = prime * result + ((getIsTop() == null) ? 0 : getIsTop().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", noticeTitle=").append(noticeTitle);
        sb.append(", noticeContent=").append(noticeContent);
        sb.append(", type=").append(type);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", publishUser=").append(publishUser);
        sb.append(", isTop=").append(isTop);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}