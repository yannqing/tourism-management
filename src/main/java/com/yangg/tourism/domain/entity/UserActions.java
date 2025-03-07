package com.yangg.tourism.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户行为分析表
 * @TableName user_actions
 */
@TableName(value ="user_actions")
@Data
public class UserActions implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 唯一标识
     */
    @TableField(value = "uniqueIndex")
    private String uniqueIndex;

    /**
     * 页面名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 路由路径
     */
    @TableField(value = "route")
    private String route;

    /**
     * 进入页面时间
     */
    @TableField(value = "accessTime")
    private Date accessTime;

    /**
     * 离开页面时间
     */
    @TableField(value = "departureTime")
    private Date departureTime;

    /**
     * 页面停留时间
     */
    @TableField(value = "stayTime")
    private String stayTime;

    /**
     * 用户id
     */
    @TableField(value = "userId")
    private Integer userId;

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
        UserActions other = (UserActions) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUniqueIndex() == null ? other.getUniqueIndex() == null : this.getUniqueIndex().equals(other.getUniqueIndex()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRoute() == null ? other.getRoute() == null : this.getRoute().equals(other.getRoute()))
            && (this.getAccessTime() == null ? other.getAccessTime() == null : this.getAccessTime().equals(other.getAccessTime()))
            && (this.getDepartureTime() == null ? other.getDepartureTime() == null : this.getDepartureTime().equals(other.getDepartureTime()))
            && (this.getStayTime() == null ? other.getStayTime() == null : this.getStayTime().equals(other.getStayTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUniqueIndex() == null) ? 0 : getUniqueIndex().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRoute() == null) ? 0 : getRoute().hashCode());
        result = prime * result + ((getAccessTime() == null) ? 0 : getAccessTime().hashCode());
        result = prime * result + ((getDepartureTime() == null) ? 0 : getDepartureTime().hashCode());
        result = prime * result + ((getStayTime() == null) ? 0 : getStayTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
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
        sb.append(", uniqueIndex=").append(uniqueIndex);
        sb.append(", name=").append(name);
        sb.append(", route=").append(route);
        sb.append(", accessTime=").append(accessTime);
        sb.append(", departureTime=").append(departureTime);
        sb.append(", stayTime=").append(stayTime);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}