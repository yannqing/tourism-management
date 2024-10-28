package com.yannqing.template.security.handler;

import com.alibaba.fastjson2.JSON;
import com.yannqing.template.common.Code;
import com.yannqing.template.utils.ResultUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class MyLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(ResultUtils.failure(Code.LOGIN_FAILURE,null,"用户名或密码错误！")));
        exception.printStackTrace();
    }
}
