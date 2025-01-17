package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.role.AddRoleDto;
import com.qcx.property.domain.dto.role.QueryRoleDto;
import com.qcx.property.domain.dto.role.UpdateRoleDto;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.domain.entity.RolePermissions;
import com.qcx.property.domain.entity.RoleUser;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.RolePermissionsMapper;
import com.qcx.property.mapper.RoleUserMapper;
import com.qcx.property.service.RoleService;
import com.qcx.property.mapper.RoleMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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




