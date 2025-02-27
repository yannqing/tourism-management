package com.yangg.tourism.domain.vo.user;

import com.yangg.tourism.domain.entity.Role;
import com.yangg.tourism.domain.entity.User;
import com.yangg.tourism.domain.vo.tourist.TourismResourcesVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
     * 旅游资源
     */
    private TourismResourcesVo tourismResourcesVo;

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
     * 角色
     */
    private List<Role> roles;

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
