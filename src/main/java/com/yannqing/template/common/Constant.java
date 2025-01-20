package com.yannqing.template.common;

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
    };

    public static String[] anonymousMatch = {
            "/auth/**",
       "/doc.html/**",
       "/webjars/**",
            "/v3/**",
//            "/test/**",
            "/rsa/**"
    };

    public static List<String> anonymousConstantList = Arrays.asList(anonymousConstant);

    public static Boolean isMatch(String path) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        List<Boolean> list = Arrays.stream(anonymousMatch).map((pattern) -> antPathMatcher.match(pattern, path)).toList();
        return list.contains(true);
    }
}