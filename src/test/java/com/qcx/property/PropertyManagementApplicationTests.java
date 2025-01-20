package com.yannqing.template;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;

@SpringBootTest
class PropertyManagementApplicationTests {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/path/*", "/path/asda/asdasd"));
        System.out.println(antPathMatcher.match("/path/*", "/path"));
        System.out.println(antPathMatcher.match("/path/*", "/path/asda"));
    }

}
