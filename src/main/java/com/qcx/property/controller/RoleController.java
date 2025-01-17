package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.role.AddRoleDto;
import com.qcx.property.domain.dto.role.QueryRoleDto;
import com.qcx.property.domain.dto.role.UpdateRoleDto;
import com.qcx.property.domain.entity.Permissions;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.service.RoleService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    @PostMapping
    public BaseResponse<?> addRole(AddRoleDto addRoleDto) {
        boolean result = roleService.addRole(addRoleDto);
        if (result) {
            return ResultUtils.success(String.format("新增角色%s成功", addRoleDto.getRoleName()));
        } else {
            return ResultUtils.failure(String.format("新增角色%s失败", addRoleDto.getRoleName()));
        }
    }

    @Operation(summary = "根据id删除角色")
    @DeleteMapping("/{id}")
    public BaseResponse<?> deleteRole(@PathVariable Integer id) {
        boolean result = roleService.deleteRole(id);
        if (result) {
            return ResultUtils.success(String.format("删除角色（id: %s）成功", id));
        } else {
            return ResultUtils.failure(String.format("删除角色（id: %s）失败", id));
        }
    }

    @Operation(summary = "批量删除角色")
    @DeleteMapping("/batch")
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
    @GetMapping
    public BaseResponse<?> getAllRoles(QueryRoleDto queryRoleDto) {
        Page<Role> result = roleService.getAllRoles(queryRoleDto);
        return ResultUtils.success(Code.SUCCESS, result, "查询所有角色成功");
    }

    @Operation(summary = "更新角色")
    @PutMapping
    public BaseResponse<?> updateRole(UpdateRoleDto updateRoleDto) {
        boolean result = roleService.updateRole(updateRoleDto);
        if (result) {
            return ResultUtils.success(String.format("更新角色信息（id: %s）成功", updateRoleDto.getId()));
        } else {
            return ResultUtils.failure(String.format("更新角色信息（id: %s）失败", updateRoleDto.getId()));
        }
    }

    @Operation(summary = "根据角色id查询所有权限")
    @GetMapping("/getByRole/{id}")
    public BaseResponse<?> getAllPermissionsByRoleId(@PathVariable Integer id) {
        List<Permissions> permissionsList = roleService.getAllPermissionsByRoleId(id);
        return ResultUtils.success(Code.SUCCESS, permissionsList, String.format("查询角色（id：%s）下的所有权限成功", id));
    }

    @Operation(summary = "给角色新增权限")
    @PostMapping("/addPermissionToRole")
    public BaseResponse<?> addPermissionToRole(Integer roleId, Integer... permissionIds) {
        boolean result = roleService.addPermissionToRole(roleId, permissionIds);
        if (result) {
            return ResultUtils.success(String.format("给角色新增权限（id: %s）成功", roleId));
        } else {
            return ResultUtils.failure(String.format("给角色新增权限（id: %s）失败", roleId));
        }
    }
}
