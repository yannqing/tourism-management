package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.role.AddRoleDto;
import com.qcx.property.domain.dto.role.QueryRoleDto;
import com.qcx.property.domain.dto.role.UpdateRoleDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.service.RoleService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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

    @Resource
    private RoleService roleService;

    @Operation(summary = "新增角色")
    @PostMapping("/add")
    public BaseResponse<?> addRole(AddRoleDto addRoleDto) {
        boolean result = roleService.addRole(addRoleDto);
        if (result) {
            return ResultUtils.success(String.format("新增角色%s成功", addRoleDto.getRoleName()));
        } else {
            return ResultUtils.failure(String.format("新增角色%s失败", addRoleDto.getRoleName()));
        }
    }

    @Operation(summary = "根据id删除角色")
    @PostMapping("/delete/{id}")
    public BaseResponse<?> deleteRole(@PathVariable Integer id) {
        boolean result = roleService.deleteRole(id);
        if (result) {
            return ResultUtils.success(String.format("删除角色（id: %s）成功", id));
        } else {
            return ResultUtils.failure(String.format("删除角色（id: %s）失败", id));
        }
    }

    @Operation(summary = "批量删除角色")
    @PostMapping("/delete/list")
    public BaseResponse<?> deleteRoles(Integer... roleIds) {
        int result = roleService.deleteBatchRoles(roleIds);
        if (result > 0) {
            return ResultUtils.success(String.format("批量删除角色成功（总：%s）（id：%s）", result,
                    String.join(",", Arrays.stream(roleIds)
                            .map(String::valueOf)
                            .toArray(String[]::new))
            ));
        } else {
            return ResultUtils.failure(String.format("批量删除角色失败（总：%s）（id：%s）", result,
                    String.join(",", Arrays.stream(roleIds)
                            .map(String::valueOf)
                            .toArray(String[]::new))
            ));
        }
    }

    @Operation(summary = "获取所有角色")
    @PostMapping("/list")
    public BaseResponse<?> getAllRoles(QueryRoleDto queryRoleDto) {
        Page<Role> result = roleService.getAllRoles(queryRoleDto);
        return ResultUtils.success(Code.SUCCESS, result, "查询所有角色成功");
    }

    @Operation(summary = "更新角色")
    @PostMapping("/update")
    public BaseResponse<?> updateRole(UpdateRoleDto updateRoleDto) {
        boolean result = roleService.updateRole(updateRoleDto);
        if (result) {
            return ResultUtils.success(String.format("更新角色信息（id: %s）成功", updateRoleDto.getId()));
        } else {
            return ResultUtils.failure(String.format("更新角色信息（id: %s）失败", updateRoleDto.getId()));
        }
    }

}
