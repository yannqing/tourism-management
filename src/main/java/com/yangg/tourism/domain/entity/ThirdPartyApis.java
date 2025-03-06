package com.yangg.tourism.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 第三方服务api调用统计表
 * @TableName third_party_apis
 */
@TableName(value ="third_party_apis")
@Data
public class ThirdPartyApis implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 第三方服务名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 访问次数（1h）
     */
    @TableField(value = "times")
    private Integer times;

    /**
     * 成功次数（1h）
     */
    @TableField(value = "successTimes")
    private Integer successTimes;

    /**
     * 失败次数（1h）
     */
    @TableField(value = "failureTimes")
    private Integer failureTimes;

    /**
     * 失败原因
     */
    @TableField(value = "failureReason")
    private String failureReason;

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
        ThirdPartyApis other = (ThirdPartyApis) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTimes() == null ? other.getTimes() == null : this.getTimes().equals(other.getTimes()))
            && (this.getSuccessTimes() == null ? other.getSuccessTimes() == null : this.getSuccessTimes().equals(other.getSuccessTimes()))
            && (this.getFailureTimes() == null ? other.getFailureTimes() == null : this.getFailureTimes().equals(other.getFailureTimes()))
            && (this.getFailureReason() == null ? other.getFailureReason() == null : this.getFailureReason().equals(other.getFailureReason()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
        result = prime * result + ((getSuccessTimes() == null) ? 0 : getSuccessTimes().hashCode());
        result = prime * result + ((getFailureTimes() == null) ? 0 : getFailureTimes().hashCode());
        result = prime * result + ((getFailureReason() == null) ? 0 : getFailureReason().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", times=").append(times);
        sb.append(", successTimes=").append(successTimes);
        sb.append(", failureTimes=").append(failureTimes);
        sb.append(", failureReason=").append(failureReason);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}