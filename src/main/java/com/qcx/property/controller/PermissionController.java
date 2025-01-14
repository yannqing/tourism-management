package com.qcx.property.controller;

import com.qcx.property.domain.model.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "新增权限")
    @PostMapping("/add")
    public BaseResponse<?> add(@RequestBody String data) {
        return null;
    }

    @Operation(summary = "根据id删除权限")
    @PostMapping("/delete/{id}")
    public BaseResponse<?> delete(@PathVariable String id) {
        return null;
    }

    @Operation(summary = "批量删除权限")
    @DeleteMapping("/delete/list")
    public BaseResponse<?> deleteBatch(Integer[] permissionIds) {
        return null;
    }

    @Operation(summary = "查询所有权限")
    @GetMapping("/getAll")
    public BaseResponse<?> getAll() {
        return null;
    }

    @Operation(summary = "根据角色id查询所有权限")
    @GetMapping("/list/{id}")
    public BaseResponse<?> getAllByRole(@PathVariable Integer id) {
        return null;
    }

    @Operation(summary = "编辑权限")
    @PutMapping("/edit/{id}")
    public BaseResponse<?> edit(@PathVariable String id, @RequestBody String data) {
        return null;
    }

}
