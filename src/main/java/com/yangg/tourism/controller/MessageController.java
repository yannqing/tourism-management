package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.dto.message.AddMessageDto;
import com.yangg.tourism.domain.dto.message.QueryMessageDto;
import com.yangg.tourism.domain.dto.message.UpdateMessageDto;
import com.yangg.tourism.domain.entity.Message;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.domain.model.PageRequest;
import com.yangg.tourism.domain.vo.message.MessageVo;
import com.yangg.tourism.service.MessageService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 消息通知管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "消息通知管理")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @Operation(summary = "查询所有消息通知（仅限管理员）")
    @GetMapping
    public BaseResponse<?> getAllMessages(QueryMessageDto queryMessageDto) {
        Page<Message> costList = messageService.getAllMessages(queryMessageDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部消息通知成功！");
    }

    @Operation(summary = "查询所有消息通知（个人消息）")
    @GetMapping("/myself")
    public BaseResponse<?> getMyselfMessages(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException {
        Page<MessageVo> costList = messageService.getMyselfMessages(pageRequest, request);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部消息通知成功！");
    }

    @PutMapping
    @Operation(summary = "更新消息通知（仅限管理员）")
    public BaseResponse<?> updateMessage(UpdateMessageDto updateMessageDto) {
        boolean result = messageService.updateMessage(updateMessageDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改消息通知成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改消息通知失败！");
        }
    }

    @Operation(summary = "添加新消息通知")
    @PostMapping
    public BaseResponse<?> addMessage(AddMessageDto addMessageDto) {
        boolean result = messageService.addMessage(addMessageDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增消息通知成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增消息通知失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "消息通知 id", required = true, in = ParameterIn.PATH))
    @Operation(summary = "删除单个消息通知")
    public BaseResponse<?> deleteMessage(@PathVariable Integer id) {
        boolean result = messageService.deleteMessage(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除消息通知成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除消息通知失败！");
        }
    }

    @DeleteMapping("/batch")
    @Parameters(@Parameter(name = "costTypeIds", description = "消息通知 id 数组", required = true))
    @Operation(summary = "批量删除消息通知")
    public BaseResponse<?> deleteBatchMessage(Integer... costTypeIds) {
        boolean result = messageService.deleteBatchMessage(costTypeIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除消息通知成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除消息通知失败！");
        }
    }


}
