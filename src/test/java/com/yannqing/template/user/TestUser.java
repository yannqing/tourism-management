package com.yannqing.template.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yannqing.template.domain.dto.permissions.AddPermissionDto;
import com.yannqing.template.domain.dto.permissions.QueryPermissionsDto;
import com.yannqing.template.domain.dto.role.QueryRoleDto;
import com.yannqing.template.domain.entity.Permissions;
import com.yannqing.template.domain.entity.Role;
import com.yannqing.template.service.PermissionsService;
import com.yannqing.template.service.RoleService;
import com.yannqing.template.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description: 测试用户管理
 * @author: yannqing
 * @create: 2025-01-17 17:46
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@SpringBootTest
public class TestUser {

    @Resource
    private PermissionsService permissionsService;

    @Resource
    private RoleService roleService;


    @Test
    public void addRoleToUser() {

    }


    @Test
    public void getAllPermissions() {
        QueryPermissionsDto queryPermissionsDto = new QueryPermissionsDto();
        // 查询权限菜单
        log.info("=========查询权限菜单=========");
        // 0 是目录，1 是用户认证，5 用户管理，18 角色管理，26 权限管理
        queryPermissionsDto.setPid(5);
        queryPermissionsDto.setCurrent(1);
        queryPermissionsDto.setPageSize(10);

        Page<Permissions> allPermissions = permissionsService.getAllPermissions(queryPermissionsDto);
        allPermissions.getRecords().forEach(System.out::println);
        log.info("============================");
    }

    /**
     * 新增权限
     */
    @Test
    public void addPermissionToUser() {
        AddPermissionDto addPermissionDto = new AddPermissionDto();
        addPermissionDto.setPid(5);
        addPermissionDto.setName("获取个人信息");
        addPermissionDto.setCode("USER_GET_MYSELF_INFO");
        addPermissionDto.setType(1);

        permissionsService.addPermission(addPermissionDto);
    }

    /**
     * 删除权限
     */
    @Test
    public void deletePermission() {
        permissionsService.deletePermission(33);
    }

    @Test
    public void getAllRoles() {

        QueryRoleDto queryRoleDto = new QueryRoleDto();
        queryRoleDto.setCurrent(1);
        queryRoleDto.setPageSize(10);

        Page<Role> allRoles = roleService.getAllRoles(queryRoleDto);
        allRoles.getRecords().forEach(System.out::println);
    }

    @Test
    public void addPermissionToRole() {
        roleService.addPermissionToRole(1, 34);
    }


}
