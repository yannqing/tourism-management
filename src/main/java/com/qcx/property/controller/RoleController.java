package com.qcx.property.controller;

import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 角色管理
 * @author: yannqing
 * @create: 2025-01-13 10:28
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Operation(summary = "新增角色")
    @PostMapping("/add")
    public BaseResponse<?> addRole(Role role) {
        return ResultUtils.failure("接口正在完善，请尝试其他接口");
    }

    @Operation(summary = "根据id删除角色")
    @PostMapping("/delete/{id}")
    public BaseResponse<?> deleteRole(@PathVariable String id) {
        return ResultUtils.failure("接口正在完善，请尝试其他接口");
    }

    @Operation(summary = "批量删除角色")
    @PostMapping("/delete/list")
    public BaseResponse<?> deleteRoles(Integer[] roleIds) {
        return ResultUtils.failure("接口正在完善，请尝试其他接口");
    }

    @Operation(summary = "获取所有角色")
    @PostMapping("/list")
    public BaseResponse<?> getAllRoles() {
        return ResultUtils.failure("接口正在完善，请尝试其他接口");
    }

    @Operation(summary = "更新角色")
    @PostMapping("/update")
    public BaseResponse<?> updateRole(Role role) {
        return ResultUtils.failure("接口正在完善，请尝试其他接口");
    }

}
