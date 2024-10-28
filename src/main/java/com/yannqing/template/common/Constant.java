package com.yannqing.template.common;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 常量类
 */
@Data
@Configuration
public class Constant {

    public static String[] anonymous = {
            "/register",
            "/login",
            "/logout"
    };

    public static List<String> annosList = Arrays.asList(anonymous);
}