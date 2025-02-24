package com.yangg.tourism.domain.dto.auth;

import com.yangg.tourism.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description:
 * @author: yannqing
 * @create: 2025-01-14 16:35
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class RegisterDto implements Serializable {

    /**
     * 用户名
     */
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

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
     * 角色id
     */
    @Schema(description = "角色id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer roleId;

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
     * 头像
     */
    @Schema(description = "头像", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String avatar;

    /**
     * 昵称
     */
    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickName;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;

    public static User dtoToUser(RegisterDto registerDto) {
        if (registerDto == null) {
            return null;
        }

        User user = new User();
        BeanUtils.copyProperties(registerDto, user);
        return user;
    }
}
