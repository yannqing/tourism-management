package com.yannqing.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yannqing.template.domain.User;
import com.yannqing.template.service.UserService;
import com.yannqing.template.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 67121
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-28 17:52:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




