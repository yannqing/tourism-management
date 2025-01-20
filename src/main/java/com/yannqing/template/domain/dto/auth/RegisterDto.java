package com.yannqing.template.domain.dto.auth;

import com.yannqing.template.domain.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 签名
     */
    private String signature;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注
     */
    private String description;

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
