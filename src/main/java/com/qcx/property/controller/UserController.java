package com.qcx.property.controller;

import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.dto.UpdateUserDto;
import com.qcx.property.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "新增用户（管理员）")
    @GetMapping("/add")
    public BaseResponse<?> addUser(User user) {
        return null;
    }

    @Operation(summary = "删除用户（管理员）")
    @GetMapping("/delete/{id}")
    public BaseResponse<?> deleteUser(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "批量删除用户（管理员）")
    @GetMapping("/delete/batch")
    public BaseResponse<?> deleteBatchUser(Integer[] userIds) {
        return null;
    }

    @Operation(summary = "查询单个用户")
    @GetMapping("/get/{id}")
    public BaseResponse<?> getUserById(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "查询所有用户（管理员）")
    @GetMapping("/list")
    public BaseResponse<?> getAll() {
        return null;
    }

    @Operation(summary = "修改用户信息（管理员）")
    @PostMapping("/update/admin/{id}")
    public BaseResponse<?> updateUserByAdmin(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "修改个人信息")
    @PostMapping("/update")
    public BaseResponse<?> updateUserById(@RequestBody UpdateUserDto updateUserDto) {
        return null;
    }

    @Operation(summary = "修改个人密码")
    @GetMapping("/changePassword/{id}")
    public BaseResponse<?> changePassword(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "重置用户密码（管理员）")
    @PostMapping("/reset/{id}")
    public BaseResponse<?> resetUserPassword(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "导出所有用户信息（管理员）")
    @PostMapping("/export")
    public BaseResponse<?> exportUser() {
        return null;
    }
}
