package com.qcx.property.auth;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;

@SpringBootTest
class AuthTests {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void testPassword() {
        String encodePassword = passwordEncoder.encode("123456");
        System.out.println(encodePassword);
    }

}
