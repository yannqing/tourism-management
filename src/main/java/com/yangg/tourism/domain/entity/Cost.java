package com.yangg.tourism.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName cost
 */
@TableName(value ="cost")
@Data
public class Cost implements Serializable {
    /**
     * 费用id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 费用名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 费用类型id
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 支付方式（0现金，1支付宝，-1微信支付）
     */
    @TableField(value = "paymentMethod")
    private Integer paymentMethod;

    /**
     * 金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 消费者id
     */
    @TableField(value = "consumer")
    private Integer consumer;

    /**
     * 收款者id
     */
    @TableField(value = "payee")
    private Integer payee;

    /**
     * 状态（0未支付，1已支付，-1待审批）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 实际支付时间
     */
    @TableField(value = "expenseTime")
    private Date expenseTime;

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
    @TableLogic
    @TableField(value = "isDelete")
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
        Cost other = (Cost) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getConsumer() == null ? other.getConsumer() == null : this.getConsumer().equals(other.getConsumer()))
            && (this.getPayee() == null ? other.getPayee() == null : this.getPayee().equals(other.getPayee()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExpenseTime() == null ? other.getExpenseTime() == null : this.getExpenseTime().equals(other.getExpenseTime()))
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
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getConsumer() == null) ? 0 : getConsumer().hashCode());
        result = prime * result + ((getPayee() == null) ? 0 : getPayee().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExpenseTime() == null) ? 0 : getExpenseTime().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", amount=").append(amount);
        sb.append(", consumer=").append(consumer);
        sb.append(", payee=").append(payee);
        sb.append(", status=").append(status);
        sb.append(", expenseTime=").append(expenseTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}