package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.role.AddRoleDto;
import com.yangg.tourism.domain.dto.role.QueryRoleDto;
import com.yangg.tourism.domain.dto.role.UpdateRoleDto;
import com.yangg.tourism.domain.entity.Permissions;
import com.yangg.tourism.domain.entity.Role;
import com.yangg.tourism.domain.entity.RolePermissions;
import com.yangg.tourism.domain.entity.RoleUser;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.PermissionsMapper;
import com.yangg.tourism.mapper.RolePermissionsMapper;
import com.yangg.tourism.mapper.RoleUserMapper;
import com.yangg.tourism.service.RoleService;
import com.yangg.tourism.mapper.RoleMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author 67121
* @description 针对表【role】的数据库操作Service实现
* @createDate 2025-01-10 15:47:20
*/
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Resource
    private RolePermissionsMapper rolePermissionsMapper;

    @Resource
    private RoleUserMapper roleUserMapper;
    @Autowired
    private PermissionsMapper permissionsMapper;


    /**
     * 管理员新增角色
     * @param addRoleDto
     * @return
     */
    @Override
    public boolean addRole(AddRoleDto addRoleDto) {
        // 判空
        String roleName = Optional.ofNullable(addRoleDto.getRoleName())
                .filter(r -> !r.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(addRoleDto.getRemark())
                .filter(r -> !r.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 不能插入重复的数据
        boolean isExists = this.baseMapper.exists(new QueryWrapper<Role>().eq("roleName", roleName));
        if (isExists) {
            throw new BusinessException(ErrorType.ROLE_ALREADY_EXIST);
        }

        // 插入角色
        Role addRole = AddRoleDto.dtoToObj(addRoleDto);

        boolean saveResult = this.save(addRole);

        log.info("管理员新增角色{}", addRoleDto);
        return saveResult;
    }

    /**
     * 管理员删除角色
     * @param id
     * @return
     */
    @Override
    public boolean deleteRole(Integer id) {
        // 有效性判断
        Role removeRole = verifyRole(id, ErrorType.ROLE_NOT_EXIST);

        // 不能删除管理员
        if (id == 2) {
            throw new BusinessException(ErrorType.ROLE_ADMIN_CANNOT_DELETE);
        }

        // 删除
        boolean removeResult = this.removeById(id);

        log.info("管理员删除角色：{}", removeRole);
        return removeResult;
    }

    /**
     * 管理员批量删除角色
     * @param roleIds
     * @return
     */
    @Override
    public int deleteBatchRoles(Integer... roleIds) {
        // 判空
        if (roleIds == null || roleIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 判断参数是否有效（移出管理员角色）
        List<Integer> roleIdList = Arrays.asList(roleIds);
        List<Integer> collectRoleIdList = roleIdList.stream().filter(roleId -> !roleId.equals(2)).toList();
        if (collectRoleIdList.isEmpty()) {
            throw new BusinessException(ErrorType.ROLE_ADMIN_CANNOT_DELETE);
        }
        List<Role> roles = this.baseMapper.selectBatchIds(collectRoleIdList);
        if (roles == null || roles.size() < collectRoleIdList.size()) {
            throw new BusinessException(ErrorType.USER_NOT_EXIST);
        }

        // 删除角色，并记录日志
        int result = this.baseMapper.deleteBatchIds(collectRoleIdList);
        logDeletedRoles(roles);

        // 删除此角色所有关联的用户关系，并记录日志
        rolePermissionsMapper.delete(new QueryWrapper<RolePermissions>().in("rid", roleIdList));
        log.info(String.format("删除角色（id: %s）所有的关联权限信息", String.join(",", roleIdList.stream().map(String::valueOf).toArray(String[]::new))));
        roleUserMapper.delete(new QueryWrapper<RoleUser>().in("rid", roleIdList));
        log.info(String.format("删除角色（id: %s）所有的关联用户信息", String.join(",", roleIdList.stream().map(String::valueOf).toArray(String[]::new))));

        return result;
    }

    /**
     * 管理员查询全部角色
     * @param queryRoleDto
     * @return
     */
    @Override
    public Page<Role> getAllRoles(QueryRoleDto queryRoleDto) {
        Optional.ofNullable(queryRoleDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryRoleDto.getId();
        String roleName = queryRoleDto.getRoleName();
        String remark = queryRoleDto.getRemark();
        int current = queryRoleDto.getCurrent();
        int pageSize = queryRoleDto.getPageSize();

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(roleName), "roleName", roleName);
        queryWrapper.like(StringUtils.isNotBlank(remark), "remark", remark);

        log.info("管理员查询所有角色");
        // TODO 这里返回的结果 total 一样为0.和用户管理出现了一样的问题
        return this.page(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public boolean updateRole(UpdateRoleDto updateRoleDto) {
        // 判空
        Optional.ofNullable(updateRoleDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        verifyRole(updateRoleDto.getId(), ErrorType.ROLE_NOT_EXIST);

        Role updateRole = UpdateRoleDto.dtoToObj(updateRoleDto);
        boolean updateResult = this.updateById(updateRole);
        log.info("管理员更新角色（id：{}）信息", updateRoleDto.getId());

        return updateResult;
    }

    @Override
    public List<Permissions> getAllPermissionsByRoleId(Integer id) {
        verifyRole(id, ErrorType.ROLE_NOT_EXIST);

        List<RolePermissions> rolePermissions = rolePermissionsMapper.selectList(new QueryWrapper<RolePermissions>().eq("rid", id));
        if (rolePermissions == null || rolePermissions.isEmpty()) {
            return null;
        }
        List<Integer> permissionIds = rolePermissions.stream().map(RolePermissions::getPid).toList();

        List<Permissions> permissions = permissionsMapper.selectBatchIds(permissionIds);

        log.info("查询角色（id：{}）下的所有权限", id);
        return permissions;
    }

    /**
     * 给角色新增权限
     * @param roleId 要添加权限的角色
     * @param permissionIds 要添加的权限
     * @return
     */
    @Override
    public boolean addPermissionToRole(Integer roleId, Integer... permissionIds) {
        // 判空
        Optional.ofNullable(roleId)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(permissionIds)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        verifyRole(roleId, ErrorType.ROLE_NOT_EXIST);

        // 权限是否存在
        List<Permissions> permissions = permissionsMapper.selectBatchIds(Arrays.asList(permissionIds));
        if (permissions == null || permissions.isEmpty() || permissions.size() < permissionIds.length) {
            throw new BusinessException(ErrorType.PERMISSION_NOT_EXIST);
        }

        // 查出此角色原来的权限id有哪些
        List<RolePermissions> rolePermissions = rolePermissionsMapper.selectList(new QueryWrapper<RolePermissions>().eq("rid", roleId));
        List<Integer> oldPermissionIds = rolePermissions.stream().map(RolePermissions::getPid).toList();

        // 找出需要添加的权限id有哪些（过滤掉以前有的权限id）
        List<Integer> addPermissionsId = Arrays.stream(permissionIds).filter(permissionId -> !oldPermissionIds.contains(permissionId)).toList();

        // 如果没有需要添加的权限，返回 true
        if (addPermissionsId.isEmpty()) {
            return true;
        }

        // 添加权限
        addPermissionsId.forEach(permissionId -> {
            RolePermissions rolePermission = new RolePermissions();
            rolePermission.setRid(roleId);
            rolePermission.setPid(permissionId);
            rolePermissionsMapper.insert(rolePermission);
        });

        log.info("给角色（id：{}）添加权限：{}", roleId, String.join(",", addPermissionsId.stream().map(String::valueOf).toArray(String[]::new)));
        return true;
    }

    /**
     * 验证角色 id 的有效性
     * @param roleId 角色 id
     * @param errorType 错误类型
     */
    public Role verifyRole(Integer roleId, ErrorType errorType) {
        Optional.ofNullable(roleId)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Role addRole = this.getById(roleId);
        if (addRole == null) {
            throw new BusinessException(errorType);
        }
        return addRole;
    }

    // 辅助方法：批量删除角色，日志记录
    private void logDeletedRoles(List<Role> roles) {
        String roleNames = roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.joining(", "));
        log.info("批量删除角色: {}", roleNames);
    }
}




