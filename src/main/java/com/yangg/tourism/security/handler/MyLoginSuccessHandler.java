package com.yangg.tourism.security.handler;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.entity.Role;
import com.yangg.tourism.domain.vo.auth.LoginVo;
import com.yangg.tourism.domain.model.SecurityUser;
import com.yangg.tourism.domain.entity.User;
import com.yangg.tourism.utils.JwtUtils;
import com.yangg.tourism.utils.RedisCache;
import com.yangg.tourism.utils.ResultUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {


    private RedisCache redisCache;


    public MyLoginSuccessHandler(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    /**
     * 登录成功处理器：返回用户信息，对应用户的权限信息，登录生成token
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        ObjectMapper objectMapper = new ObjectMapper();
        String userInfo = objectMapper.writeValueAsString(user);


        List<Role> roles = securityUser.getRole();
        //生成token
        String token = JwtUtils.token(userInfo, JSON.toJSONString(roles));
        //将token存入redis中，并设置token过期时间：3小时
        redisCache.setCacheObject("token:"+token,String.valueOf(authentication),60*60*3, TimeUnit.SECONDS);

        LoginVo userInfoVo = new LoginVo(user, token, roles);

        response.getWriter().write(JSON.toJSONString(ResultUtils.success(Code.LOGIN_SUCCESS, userInfoVo, "登录成功")));
        log.info("用户{}登录成功！", user.getUsername());
    }
}
