package com.yannqing.qcx.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试
 * @author: yannqing
 * @create: 2025-01-10 16:04
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "用户认证")
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/getOne")
    public String getOne() {
        return "Hello, this is test!";
    }
}
