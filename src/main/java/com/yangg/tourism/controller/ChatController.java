package com.yangg.tourism.controller;

import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.ChatRequest;
import com.yangg.tourism.domain.dto.ChatResponse;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatController {
    
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;
    
    @GetMapping("/chat")
    public BaseResponse<String> chat(@RequestParam String prompt) {
        // 创建 request
        ChatRequest request = new ChatRequest(model, prompt);
        
        // 调用 API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        String content = "";
        
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            content =  "No response";
        } else {
            content = response.getChoices().get(0).getMessage().getContent();
        }
        
        // 返回第一个响应
        return ResultUtils.success(Code.SUCCESS, content);
    }
}