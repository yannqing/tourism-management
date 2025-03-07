package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.entity.UserActions;
import com.yangg.tourism.service.UserActionsService;
import com.yangg.tourism.mapper.UserActionsMapper;
import org.springframework.stereotype.Service;

/**
* @author yanqing
* @description 针对表【user_actions(用户行为分析表)】的数据库操作Service实现
* @createDate 2025-03-07 11:19:36
*/
@Service
public class UserActionsServiceImpl extends ServiceImpl<UserActionsMapper, UserActions>
    implements UserActionsService{

}




