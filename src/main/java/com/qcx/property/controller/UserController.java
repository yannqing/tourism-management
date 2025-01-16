package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.user.AddUserDto;
import com.qcx.property.domain.dto.user.QueryUserRequest;
import com.qcx.property.domain.dto.user.UpdateMyInfoDto;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.dto.user.UpdateUserDto;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.service.UserService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 用户管理
 * @author: yannqing
 * @create: 2025-01-13 09:50
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "新增用户（管理员）")
    @GetMapping("/add")
    public BaseResponse<?> addUser(AddUserDto addUserDto) {
        boolean result = userService.addUser(addUserDto);
        if (result) {
            return ResultUtils.success(String.format("新增用户%s成功", addUserDto.getUsername()));
        } else {
            return ResultUtils.failure(String.format("新增用户%s失败", addUserDto.getUsername()));
        }
    }

    @Operation(summary = "删除用户（管理员）")
    @GetMapping("/delete/{id}")
    public BaseResponse<?> deleteUserById(@PathVariable Integer id) {
        boolean result = userService.deleteUserById(id);
        if (result) {
            return ResultUtils.success(String.format("删除用户成功（id：%s）", id));
        } else {
            return ResultUtils.failure(String.format("删除用户失败（id：%s）", id));
        }
    }

    @Operation(summary = "批量删除用户（管理员）")
    @GetMapping("/delete/batch")
    public BaseResponse<?> deleteBatchUser(Integer... userIds) {
        int result = userService.deleteBatchUser(userIds);
        if (result > 0) {
            return ResultUtils.success(String.format("批量删除用户成功（总：%s）（id：%s）", result,
                    String.join(",", Arrays.stream(userIds)
                            .map(String::valueOf)
                            .toArray(String[]::new))
            ));
        } else {
            return ResultUtils.failure(String.format("批量删除用户失败（总：%s）（id：%s）", result,
                    String.join(",", Arrays.stream(userIds)
                            .map(String::valueOf)
                            .toArray(String[]::new))
            ));
        }
    }

    @Operation(summary = "查询单个用户")
    @GetMapping("/get/{id}")
    public BaseResponse<?> getUserById(@PathVariable Integer id) {
        UserVo userVo = userService.getUserById(id);
        return ResultUtils.success(Code.SUCCESS, userVo, "查询单个用户成功");
    }

    @Operation(summary = "查询所有用户（管理员）")
    @GetMapping("/list")
    public BaseResponse<?> getAll(QueryUserRequest queryUserRequest) {
        Page<User> userPages = userService.getAll(queryUserRequest);
        return ResultUtils.success(Code.SUCCESS, userPages, "查询所有用户成功");
    }

    @Operation(summary = "修改用户信息（管理员）")
    @PostMapping("/update/admin/{id}")
    public BaseResponse<?> updateUserByAdmin(UpdateUserDto updateUserDto) {
        boolean result = userService.updateUserByAdmin(updateUserDto);
        if (result) {
            return ResultUtils.success(String.format("修改用户信息成功（username：%s）", updateUserDto.getUsername()));
        } else {
            return ResultUtils.failure(String.format("修改用户信息失败（username：%s）", updateUserDto.getUsername()));
        }
    }

    @Operation(summary = "修改个人信息")
    @PostMapping("/update")
    public BaseResponse<?> updateUserById(UpdateMyInfoDto updateMyInfoDto, HttpServletRequest request) throws JsonProcessingException {
        boolean result = userService.updateMyInfo(updateMyInfoDto, request);
        if (result) {
            return ResultUtils.success(String.format("修改个人信息成功（username：%s）", updateMyInfoDto.getUsername()));
        } else {
            return ResultUtils.failure(String.format("修改个人信息失败（username：%s）", updateMyInfoDto.getUsername()));
        }
    }

    @Operation(summary = "修改个人密码")
    @GetMapping("/changePassword")
    public BaseResponse<?> changePassword(String originPassword, String newPassword, String againPassword, HttpServletRequest request) throws JsonProcessingException {
        boolean result = userService.updatePassword(originPassword, newPassword, againPassword, request);
        if (result) {
            return ResultUtils.success(String.format("修改个人密码成功（新密码：%s）", newPassword));
        } else {
            return ResultUtils.failure(String.format("修改个人密码失败（新密码：%s）", newPassword));
        }
    }

    @Operation(summary = "重置用户密码（管理员）")
    @PostMapping("/reset/{id}")
    public BaseResponse<?> resetUserPassword(@PathVariable Integer id) {
        boolean result = userService.resetUserPassword(id);
        if (result) {
            return ResultUtils.success(String.format("重置用户密码成功（userId：%s）", id));
        } else {
            return ResultUtils.failure(String.format("重置用户密码失败（userId：%s）", id));
        }
    }

    @Operation(summary = "导出所有用户信息（管理员）")
    @PostMapping("/export")
    public BaseResponse<?> exportUser() {
        return ResultUtils.failure("接口正在完善，请尝试其他接口");
    }
}
