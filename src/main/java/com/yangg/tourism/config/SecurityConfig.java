package com.yangg.tourism.config;

import com.yangg.tourism.common.Constant;
import com.yangg.tourism.security.filter.JwtAuthenticationTokenFilter;
import com.yangg.tourism.security.handler.MyLoginFailureHandler;
import com.yangg.tourism.security.handler.MyLoginSuccessHandler;
import com.yangg.tourism.security.handler.MyLogoutSuccessHandler;
import com.yangg.tourism.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    RedisCache redisCache;

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
                .anyRequest()
                .authenticated()
        );
        //关闭session
        http.sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //设置用户名密码认证前的jwt过滤器?
        http.addFilterBefore(new JwtAuthenticationTokenFilter(redisCache), UsernamePasswordAuthenticationFilter.class);

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
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
