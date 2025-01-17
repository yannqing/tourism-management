package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.user.AddUserDto;
import com.qcx.property.domain.dto.user.QueryUserDto;
import com.qcx.property.domain.dto.user.UpdateMyInfoDto;
import com.qcx.property.domain.dto.user.UpdateUserDto;
import com.qcx.property.domain.entity.Permissions;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.user.UserVo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author 67121
* @description 针对表【user】的数据库操作Service
* @createDate 2025-01-10 15:45:04
*/
public interface UserService extends IService<User> {

    boolean addUser(AddUserDto addUserDto);

    boolean deleteUserById(Integer id);

    int deleteBatchUser(Integer... userIds);

    UserVo getUserById(Integer id);

    Page<User> getAll(QueryUserDto queryUserDto);

    boolean updateUserByAdmin(UpdateUserDto updateUserDto);

    boolean updateMyInfo(UpdateMyInfoDto updateMyInfoDto, HttpServletRequest request) throws JsonProcessingException;

    User verifyUserId(Integer userId);

    boolean updatePassword(String originPassword, String newPassword, String againPassword, HttpServletRequest request) throws JsonProcessingException;

    boolean resetUserPassword(Integer id);

    boolean addRoleToUser(Integer userId, Integer... roleIds);

    List<Role> getRoleByUser(Integer userId);

    List<Permissions> getPermissionByUser(Integer userId);
}
