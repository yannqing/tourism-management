package com.yannqing.template.domain.vo.user;

import com.yannqing.template.domain.entity.Role;
import com.yannqing.template.domain.entity.User;
import com.yannqing.template.enums.ErrorType;
import com.yannqing.template.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 获取个人信息 vo
 * @author: yannqing
 * @create: 2025-01-15 14:42
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class MySelfInfoVo implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

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
     * 角色
     */
    private List<Role> roles;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注
     */
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;

    public static MySelfInfoVo userToObj(User user) {
        if (user == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        MySelfInfoVo mySelfInfoVo = new MySelfInfoVo();
        BeanUtils.copyProperties(user, mySelfInfoVo);

        return mySelfInfoVo;
    }
}
