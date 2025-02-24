package com.yangg.tourism.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.annotation.AuthCheck;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.common.PermissionConstant;
import com.yangg.tourism.domain.dto.permissions.AddPermissionDto;
import com.yangg.tourism.domain.dto.permissions.QueryPermissionsDto;
import com.yangg.tourism.domain.dto.permissions.UpdatePermissionsDto;
import com.yangg.tourism.domain.entity.Permissions;
import com.yangg.tourism.enums.PermissionType;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.PermissionsService;
import com.yangg.tourism.utils.ResultUtils;
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

    @AuthCheck(code = PermissionConstant.PERMISSION_ADD)
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

    @AuthCheck(code = PermissionConstant.PERMISSION_DELETE_ONE)
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

    @AuthCheck(code = PermissionConstant.PERMISSION_DELETE_BATCH)
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

    @AuthCheck(code = PermissionConstant.PERMISSION_GET_ALL)
    @Operation(summary = "查询所有权限")
    @GetMapping
    public BaseResponse<?> getAll(QueryPermissionsDto queryPermissionsDto) {
        Page<Permissions> result = permissionsService.getAllPermissions(queryPermissionsDto);
        return ResultUtils.success(Code.SUCCESS, result, "查询所有权限成功");
    }

    @AuthCheck(code = PermissionConstant.PERMISSION_UPDATE)
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
