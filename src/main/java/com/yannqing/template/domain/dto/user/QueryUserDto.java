package com.yannqing.template.domain.dto.user;

import com.yannqing.template.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 查询用户请求封装
 * @author: yannqing
 * @create: 2025-01-15 15:09
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryUserDto", description = "查询所有用户信息请求参数")
public class QueryUserDto extends PageRequest implements Serializable {
    /**
     * 用户id
     */
    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer userId;

    /**
     * 用户名
     */
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String username;

    /**
     * 地址
     */
    @Schema(description = "地址", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String address;

    /**
     * 电话
     */
    @Schema(description = "电话", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String email;

    /**
     * 年龄
     */
    @Schema(description = "年龄", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer age;

    /**
     * 签名
     */
    @Schema(description = "签名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String signature;

    /**
     * 性别
     */
    @Schema(description = "性别", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer sex;

    /**
     * 昵称
     */
    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String nickName;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;
}
