package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.domain.dto.user.AddUserDto;
import com.qcx.property.domain.dto.user.QueryUserRequest;
import com.qcx.property.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.user.UserVo;

import java.util.List;

/**
* @author 67121
* @description 针对表【user】的数据库操作Service
* @createDate 2025-01-10 15:45:04
*/
public interface UserService extends IService<User> {

    boolean addUser(AddUserDto addUserDto);

    boolean deleteUserById(Long id);

    int deleteBatchUser(Integer... userIds);

    UserVo getUserById(Long id);

    Page<User> getAll(QueryUserRequest queryUserRequest);
}
