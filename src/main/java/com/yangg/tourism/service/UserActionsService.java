package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangg.tourism.domain.entity.UserActions;
import com.yangg.tourism.domain.vo.user.UserActionsVo;

import java.util.List;

/**
* @author yanqing
* @description 针对表【user_actions(用户行为分析表)】的数据库操作Service
* @createDate 2025-03-07 11:19:36
*/
public interface UserActionsService extends IService<UserActions> {

    List<UserActionsVo> getUserActions();
}
