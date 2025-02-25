package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.domain.dto.message.AddMessageDto;
import com.yangg.tourism.domain.dto.message.QueryMessageDto;
import com.yangg.tourism.domain.dto.message.UpdateMessageDto;
import com.yangg.tourism.domain.entity.Message;
import com.yangg.tourism.domain.model.PageRequest;
import com.yangg.tourism.domain.vo.message.MessageVo;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 67121
* @description 针对表【message(消息通知表)】的数据库操作Service
* @createDate 2025-02-14 17:09:37
*/
public interface MessageService extends IService<Message> {

    /**
     * 查询所有消息通知（仅限管理员）
     * @param queryMessageDto 查询dto
     * @return 分页结果
     */
    Page<Message> getAllMessages(QueryMessageDto queryMessageDto);

    /**
     * 查询个人的消息通知
     * @param pageRequest 分页请求
     * @param request session 会话
     * @return 消息封装
     * @throws JsonProcessingException 异常
     */
    Page<MessageVo> getMyselfMessages(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 更新消息通知
     * @param updateMessageDto 更新dto
     * @return 更新结果
     */
    boolean updateMessage(UpdateMessageDto updateMessageDto);

    /**
     * 新增消息通知
     * @param addMessageDto 新增 dto
     * @return 新增结果
     */
    boolean addMessage(AddMessageDto addMessageDto);

    /**
     * 删除单个消息通知（仅限管理员）
     * @param id 要删除的消息通知id
     * @return 返回删除结果
     */
    boolean deleteMessage(Integer id);

    /**
     * 批量删除消息通知（仅限管理员）
     * @param messageIds 要删除的消息 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchMessage(Integer[] messageIds);
}
