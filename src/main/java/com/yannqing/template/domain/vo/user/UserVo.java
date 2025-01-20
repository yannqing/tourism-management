package com.yannqing.template.domain.vo.user;

import com.yannqing.template.domain.dto.user.AddUserDto;
import com.yannqing.template.domain.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户 vo
 * @author: yannqing
 * @create: 2025-01-15 14:42
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class UserVo implements Serializable {
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
     * 昵称
     */
    private String nickName;

    /**
     * 账户是否可用
     */
    private Integer enabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;

    public static UserVo objToVo(User user) {
        if (user == null) {
            return null;
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }
}
