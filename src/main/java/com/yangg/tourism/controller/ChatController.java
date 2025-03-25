package com.yangg.tourism.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.annotation.AuthCheck;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.chat.AiMessageDTO;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.AiChatService;
import com.yangg.tourism.service.ChatService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "AI 管理")
@RestController
public class ChatController {

    @Resource
    private ChatService chatService;

    @Resource
    private AiChatService aiChatService;

    @Deprecated
    @PostMapping("/chat")
    public BaseResponse<String> chat(String prompt, HttpServletRequest request) throws JsonProcessingException {
        String result = chatService.sendMessage(prompt, request);

        // 返回第一个响应
        return ResultUtils.success(Code.SUCCESS, result);
    }

    @AuthCheck(code = "AI_CHAT_AGENT")
    @PostMapping(value = "/chatAgent", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "智能客服聊天接口" )
    public Flux<ServerSentEvent<String>> chatStreamWithHistory(@RequestBody AiMessageDTO aiMessageDTO) {
        return aiChatService.chatWithAgent(aiMessageDTO);
    }
}