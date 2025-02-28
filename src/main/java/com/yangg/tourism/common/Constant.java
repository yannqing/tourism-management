package com.yangg.tourism.common;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;

/**
 * 常量类
 */
@Data
@Configuration
public class Constant {

    public static String[] anonymousConstant = {
            "/login",
            "/favicon.ico",
            "/ws/user-status",
    };

    public static String[] anonymousMatch = {
            "/auth/**",
       "/doc.html/**",
       "/webjars/**",
            "/file/download/image/**",
            "/v3/**",
//            "/test/**",
            "/rsa/**"
    };

    public static final Integer adminUserId = 1;

    public static List<String> anonymousConstantList = Arrays.asList(anonymousConstant);

    public static Boolean isMatch(String path) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        List<Boolean> list = Arrays.stream(anonymousMatch).map((pattern) -> antPathMatcher.match(pattern, path)).toList();
        return list.contains(true);
    }
}