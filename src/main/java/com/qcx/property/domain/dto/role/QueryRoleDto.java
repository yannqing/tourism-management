package com.qcx.property.domain.dto.role;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询所有角色 dto
 * @author: yannqing
 * @create: 2025-01-16 11:25
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryRoleDto", description = "查询所有角色请求参数")
public class QueryRoleDto extends PageRequest implements Serializable {
    /**
     * 角色id
     */
    @Schema(description = "角色id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 角色名
     */
    @Schema(description = "角色名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String roleName;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date updateTime;

    /**
     * 角色含义
     */
    @Schema(description = "角色含义", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String remark;
}
