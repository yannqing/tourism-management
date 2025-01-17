package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.permissions.AddPermissionDto;
import com.qcx.property.domain.dto.permissions.QueryPermissionsDto;
import com.qcx.property.domain.dto.permissions.UpdatePermissionsDto;
import com.qcx.property.domain.entity.Permissions;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.service.PermissionsService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 权限管理
 * @author: yannqing
 * @create: 2025-01-13 10:28
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "权限管理")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionsService permissionsService;

    @Operation(summary = "新增权限")
    @PostMapping
    public BaseResponse<?> addPermission(AddPermissionDto addPermissionDto) {
        boolean result = permissionsService.addPermission(addPermissionDto);
        if (result) {
            return ResultUtils.success(String.format("新增权限%s成功", addPermissionDto.getName()));
        } else {
            return ResultUtils.failure(String.format("新增权限%s失败", addPermissionDto.getName()));
        }
    }

    @Operation(summary = "根据id删除权限")
    @DeleteMapping("/{id}")
    public BaseResponse<?> delete(@PathVariable Integer id) {
        boolean result = permissionsService.deletePermission(id);
        if (result) {
            return ResultUtils.success(String.format("删除权限（id: %s）成功", id));
        } else {
            return ResultUtils.failure(String.format("删除权限（id: %s）失败", id));
        }
    }

    @Operation(summary = "批量删除权限")
    @DeleteMapping("/batch")
    public BaseResponse<?> deleteBatch(Integer... permissionIds) {
        int result = permissionsService.deleteBatchPermissions(permissionIds);
        if (result > 0) {
            return ResultUtils.success(String.format("批量删除权限成功（总：%s）（id：%s）", result,
                    String.join(",", Arrays.stream(permissionIds)
                            .map(String::valueOf)
                            .toArray(String[]::new))
            ));
        } else {
            return ResultUtils.failure(String.format("批量删除权限失败（总：%s）（id：%s）", result,
                    String.join(",", Arrays.stream(permissionIds)
                            .map(String::valueOf)
                            .toArray(String[]::new))
            ));
        }
    }

    @Operation(summary = "查询所有权限")
    @GetMapping
    public BaseResponse<?> getAll(QueryPermissionsDto queryPermissionsDto) {
        Page<Permissions> result = permissionsService.getAllPermissions(queryPermissionsDto);
        return ResultUtils.success(Code.SUCCESS, result, "查询所有权限成功");
    }

    @Operation(summary = "编辑权限")
    @PutMapping
    public BaseResponse<?> edit(UpdatePermissionsDto updatePermissionsDto) {
        boolean result = permissionsService.updatePermissions(updatePermissionsDto);
        if (result) {
            return ResultUtils.success(String.format("更新权限信息（id: %s）成功", updatePermissionsDto.getId()));
        } else {
            return ResultUtils.failure(String.format("更新权限信息（id: %s）失败", updatePermissionsDto.getId()));
        }
    }

}
