package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.entity.User;
import com.qcx.property.service.UserService;
import com.qcx.property.mapper.UserMapper;
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




