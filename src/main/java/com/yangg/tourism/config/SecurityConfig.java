package com.yangg.tourism.config;

import com.alibaba.fastjson2.JSON;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.common.Constant;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.security.filter.JwtAuthenticationTokenFilter;
import com.yangg.tourism.security.handler.MyLoginFailureHandler;
import com.yangg.tourism.security.handler.MyLoginSuccessHandler;
import com.yangg.tourism.security.handler.MyLogoutSuccessHandler;
import com.yangg.tourism.utils.RedisCache;
import com.yangg.tourism.utils.ResultUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Resource
    private RedisCache redisCache;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //允许一些请求匿名访问，其他的均需要认证
        http.authorizeHttpRequests((authorize)->authorize
                .requestMatchers(Constant.anonymousConstant)
                .permitAll()
                .requestMatchers(Constant.anonymousMatch)
                .permitAll()
                // 添加这一行，直接允许定时任务的请求
                .requestMatchers("/scheduled/**").permitAll()
                // 添加这一行，对 SSE 请求使用特殊处理
                .requestMatchers(request -> 
                    request.getHeader("Accept") != null && 
                    request.getHeader("Accept").contains(MediaType.TEXT_EVENT_STREAM_VALUE)
                ).authenticated()
                .anyRequest()
                .authenticated()
        );
        
        // 修改这里：添加异步请求支持
        http.securityContext(context -> context
                .requireExplicitSave(false));  // 不需要显式保存安全上下文
                
        //关闭session
        http.sessionManagement((sessionManagement)->
                sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    // 添加这行：支持异步请求
                    .enableSessionUrlRewriting(true)
        );

        //设置用户名密码认证前的jwt过滤器
        JwtAuthenticationTokenFilter jwtFilter = new JwtAuthenticationTokenFilter(redisCache);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // 自定义异常处理
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                    // 对于 SSE 请求的特殊处理
                    if (isServerSentEventRequest(request)) {
                        // 对于 SSE 请求，发送一个特殊的错误事件
                        response.setStatus(HttpServletResponse.SC_OK); // 保持 200 状态码
                        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("event: error\ndata: " + 
                            JSON.toJSONString(ResultUtils.failure(Code.TOKEN_AUTHENTICATE_FAILURE, null, "未认证")) + 
                            "\n\n");
                        response.getWriter().flush();
                        return;
                    }
                    
                    if (!response.isCommitted()) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write(JSON.toJSONString(ResultUtils.failure(Code.TOKEN_AUTHENTICATE_FAILURE, null, "未认证")));
                    }
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    // 对于 SSE 请求的特殊处理
                    if (isServerSentEventRequest(request)) {
                        response.setStatus(HttpServletResponse.SC_OK); // 保持 200 状态码
                        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("event: error\ndata: " + 
                            JSON.toJSONString(ResultUtils.failure(ErrorType.TOKEN_INVALID.getErrorCode(), null, "权限不足")) + 
                            "\n\n");
                        response.getWriter().flush();
                        return;
                    }
                    
                    if (!response.isCommitted()) {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write(JSON.toJSONString(ResultUtils.failure(ErrorType.TOKEN_INVALID.getErrorCode(), null, "权限不足")));
                    }
                })
        );

        //csrf
        http.csrf(AbstractHttpConfigurer::disable);

        //登录可以选择form表单登录，也可选择发送请求，写到controller中
        //form表单登录
        http.formLogin((login)->login.
                loginProcessingUrl("/auth/login")
                .successHandler(new MyLoginSuccessHandler(redisCache))
                .failureHandler(new MyLoginFailureHandler())
        );
        //post请求登录

        //设置退出logout过滤器
        http.logout((logout)->logout
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(new MyLogoutSuccessHandler(redisCache))
        );

        //关闭跨域拦截--适用于前后端分离，另创建跨域拦截的类
        http.cors(Customizer.withDefaults());

        return http.build();
    }
    /**
     * 对密码进行BCrypt加密
     * @return 返回 BCryptEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            log.warn("Access Denied: {}", accessDeniedException.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(403);
            response.getWriter().write("{\"message\": \"Access Denied\"}");
        };
    }

    // 添加这个辅助方法来检测是否为 SSE 请求
    private boolean isServerSentEventRequest(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        return acceptHeader != null && 
               (acceptHeader.contains(MediaType.TEXT_EVENT_STREAM_VALUE) || 
                request.getRequestURI().contains("/chatAgent"));
    }

}
