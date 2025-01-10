package com.yannqing.qcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yannqing.qcx.domain.entity.User;
import com.yannqing.qcx.service.UserService;
import com.yannqing.qcx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 67121
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-01-10 15:45:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




