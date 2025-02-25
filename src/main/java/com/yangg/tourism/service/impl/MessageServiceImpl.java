package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yangg.tourism.domain.dto.message.AddMessageDto;
import com.yangg.tourism.domain.dto.message.QueryMessageDto;
import com.yangg.tourism.domain.dto.message.UpdateMessageDto;
import com.yangg.tourism.domain.entity.Message;
import com.yangg.tourism.domain.entity.User;
import com.yangg.tourism.domain.model.PageRequest;
import com.yangg.tourism.domain.vo.message.MessageVo;
import com.yangg.tourism.domain.vo.user.UserVo;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.enums.MessageType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.MessageMapper;
import com.yangg.tourism.mapper.UserMapper;
import com.yangg.tourism.service.MessageService;
import com.yangg.tourism.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @author 67121
* @description 针对表【message(消息通知表)】的数据库操作Service实现
* @createDate 2025-02-14 17:09:37
*/
@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<Message> getAllMessages(QueryMessageDto queryMessageDto) {
        // 判空
        Optional.ofNullable(queryMessageDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer type = queryMessageDto.getType();
        String content = queryMessageDto.getContent();
        Integer sendUser = queryMessageDto.getSendUser();
        Integer receiveUser = queryMessageDto.getReceiveUser();
        Integer status = queryMessageDto.getStatus();
        Date createTime = queryMessageDto.getCreateTime();
        Date updateTime = queryMessageDto.getUpdateTime();

        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(type!= null, "type", type);
        queryWrapper.like(content!= null, "content", content);
        queryWrapper.eq(createTime!= null, "createTime", createTime);
        queryWrapper.eq(updateTime!= null, "updateTime", updateTime);
        queryWrapper.eq(sendUser!= null, "sendUser", sendUser);
        queryWrapper.eq(receiveUser!= null, "receiveUser", receiveUser);
        queryWrapper.eq(status!= null, "description", status);
        log.info("查询所有消息通知");

        return this.page(new Page<>(queryMessageDto.getCurrent(), queryMessageDto.getPageSize()), queryWrapper);
    }

    @Override
    public Page<MessageVo> getMyselfMessages(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        // 查询所有信息
        List<Message> allMessageInfo = this.baseMapper.selectList(new QueryWrapper<Message>().eq("receiveUser", loginUser.getUserId()));

        // 二次封装
        List<MessageVo> messageVoList = allMessageInfo.stream().map(message -> {
            MessageVo messageVo = MessageVo.objToVo(message);
            messageVo.setSendUser(UserVo.objToVo(userMapper.selectById(message.getSendUser())));
            messageVo.setReceiveUser(UserVo.objToVo(userMapper.selectById(message.getReceiveUser())));
            messageVo.setType(MessageType.getRemarkById(message.getType()));

            return messageVo;
        }).toList();
        log.info("查询所有消息通知(个人)");

        return new Page<MessageVo>(pageRequest.getCurrent(), pageRequest.getPageSize()).setRecords(messageVoList);
    }

    @Override
    public boolean updateMessage(UpdateMessageDto updateMessageDto) {
        // 判空
        Optional.ofNullable(updateMessageDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateMessageId = updateMessageDto.getId();
        Optional.ofNullable(this.getById(updateMessageId))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        Message updateMessage = UpdateMessageDto.objToMessage(updateMessageDto);

        boolean updateResult = this.updateById(updateMessage);
        log.info("更新消息通知");

        return updateResult;
    }

    @Override
    public boolean addMessage(AddMessageDto addMessageDto) {
        // 判空
        // 消息内容不能为空
        Optional.ofNullable(addMessageDto.getContent())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        //接收者id 不能为空
        Optional.ofNullable(addMessageDto.getReceiveUser())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 添加消息通知
        Message message = AddMessageDto.objToMessage(addMessageDto);
        boolean saveResult = this.save(message);
        log.info("新增消息通知");

        return saveResult;
    }

    @Override
    public boolean deleteMessage(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.MESSAGE_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除消息通知（id：{}）", id);

        return deleteResult;
    }

    @Override
    public boolean deleteBatchMessage(Integer[] messageIds) {
        // 判空
        if (messageIds == null || messageIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<Message> messageList = this.listByIds(Arrays.asList(messageIds));
        if (messageList.size()!= messageIds.length) {
            throw new BusinessException(ErrorType.MESSAGE_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(messageIds));
        log.info("批量删除消息通知");

        return deleteResult > 0;
    }
}




