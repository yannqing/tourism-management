package com.yannqing.qcx.domain.vo;

import com.yannqing.qcx.domain.entity.Role;
import com.yannqing.qcx.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录成功后返回的封装信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {

    private Integer userId;
    private String userName;
    private String token;
    private List<Role> roles;

    public LoginVo(User user, String token, List<Role> roles) {
        this.userId = user.getUserId();
        this.userName = user.getUsername();
        this.token = token;
        this.roles = roles;
    }

}
