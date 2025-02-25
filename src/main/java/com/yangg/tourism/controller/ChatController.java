package com.yangg.tourism.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.ChatService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AI 管理")
@RestController
public class ChatController {

    @Resource
    private ChatService chatService;
    
    @PostMapping("/chat")
    public BaseResponse<String> chat(String prompt, HttpServletRequest request) throws JsonProcessingException {
        String result = chatService.sendMessage(prompt, request);

        // 返回第一个响应
        return ResultUtils.success(Code.SUCCESS, result);
    }
}