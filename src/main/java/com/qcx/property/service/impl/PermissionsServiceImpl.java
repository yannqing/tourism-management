package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.permissions.AddPermissionDto;
import com.qcx.property.domain.dto.permissions.QueryPermissionsDto;
import com.qcx.property.domain.dto.permissions.UpdatePermissionsDto;
import com.qcx.property.domain.dto.role.UpdateRoleDto;
import com.qcx.property.domain.entity.Permissions;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.domain.entity.RolePermissions;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.RolePermissionsMapper;
import com.qcx.property.service.PermissionsService;
import com.qcx.property.mapper.PermissionsMapper;
import com.qcx.property.service.RoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author yannqing
* @description 针对表【permissions】的数据库操作Service实现
* @createDate 2025-01-10 15:47:25
*/
@Slf4j
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions>
    implements PermissionsService{

    @Resource
    private RolePermissionsMapper rolePermissionsMapper;

    @Resource
    private RoleService roleService;

    /**
     * 管理员新增权限
     * @param addPermissionDto 新增权限 dto
     * @return 新增结果
     */
    @Override
    public boolean addPermission(AddPermissionDto addPermissionDto) {
        // TODO 优化代码，代码重复性太高，提取公共方法到 utils 包下（其他类中的判空逻辑一样的处理）
        // 判空
        Optional.ofNullable(addPermissionDto.getName())
                .filter(r -> !r.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(addPermissionDto.getPid())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        String code = Optional.ofNullable(addPermissionDto.getCode())
                .filter(r -> !r.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(addPermissionDto.getType())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 不能插入已有的权限
        boolean isExists = this.baseMapper.exists(new QueryWrapper<Permissions>().eq("code", code));
        if (isExists) {
            throw new BusinessException(ErrorType.PERMISSION_ALREADY_EXIST);
        }

        // 有效性判断
        Permissions addPermission = AddPermissionDto.dtoToObj(addPermissionDto);
        boolean saveResult = this.save(addPermission);
        log.info("管理员新增权限{}", addPermissionDto);

        return saveResult;
    }

    /**
     * 根据id删除权限
     * @param id 权限id
     * @return 删除结果
     */
    @Override
    public boolean deletePermission(Integer id) {
        // 有效性判断
        Permissions removePermission = verifyPermission(id, ErrorType.PERMISSION_NOT_EXIST);

        // 删除权限
        boolean removeResult = this.removeById(id);

        log.info("管理员删除权限：{}", removePermission);
        return removeResult;
    }

    /**
     * 批量删除权限
     * @param permissionIds 要删除的权限 id 数组
     * @return 返回删除的数量
     */
    @Override
    public int deleteBatchPermissions(Integer... permissionIds) {
        // 判空
        if (permissionIds == null || permissionIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 判断参数是否有效
        List<Integer> permissionIdList = Arrays.asList(permissionIds);
        List<Permissions> permissions = this.baseMapper.selectBatchIds(permissionIdList);
        if (permissions == null || permissions.size() < permissionIdList.size()) {
            throw new BusinessException(ErrorType.PERMISSION_NOT_EXIST);
        }

        // 删除权限，并记录日志
        int result = this.baseMapper.deleteBatchIds(permissionIdList);
        log.info("批量删除权限：{}", permissions);

        // 删除权限相关角色的关系
        rolePermissionsMapper.delete(new QueryWrapper<RolePermissions>().in("pid", permissionIdList));
        log.info("删除权限相关角色的关系");
        return result;
    }

    /**
     * 管理员查询所有权限
     * @param queryPermissionsDto 要查询的参数
     * @return 返回分页查询结果
     */
    @Override
    public Page<Permissions> getAllPermissions(QueryPermissionsDto queryPermissionsDto) {
        Optional.ofNullable(queryPermissionsDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryPermissionsDto.getId();
        String name = queryPermissionsDto.getName();
        String code = queryPermissionsDto.getCode();
        Integer type = queryPermissionsDto.getType();
        int current = queryPermissionsDto.getCurrent();
        int pageSize = queryPermissionsDto.getPageSize();

        QueryWrapper<Permissions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.like(StringUtils.isNotBlank(code), "code", code);
        queryWrapper.eq(type != null, "type", type);

        log.info("查询所有权限");
        return this.page(new Page<>(current, pageSize), queryWrapper);
    }

    /**
     * 更新权限信息
     * @param updatePermissionsDto 更新请求 dto
     * @return 返回更新结果
     */
    @Override
    public boolean updatePermissions(UpdatePermissionsDto updatePermissionsDto) {
        // 判空
        Optional.ofNullable(updatePermissionsDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        verifyPermission(updatePermissionsDto.getId(), ErrorType.ROLE_NOT_EXIST);

        Permissions updatePermission = UpdatePermissionsDto.dtoToObj(updatePermissionsDto);
        boolean updateResult = this.updateById(updatePermission);
        log.info("管理员更新权限（id：{}）信息", updatePermissionsDto.getId());

        return updateResult;
    }

    /**
     * 验证权限 id 的有效性
     * @param permissionsId 权限 id
     * @param errorType 错误类型
     */
    public Permissions verifyPermission(Integer permissionsId, ErrorType errorType) {
        Optional.ofNullable(permissionsId)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Permissions permissions = this.getById(permissionsId);
        if (permissions == null) {
            throw new BusinessException(errorType);
        }
        return permissions;
    }
}




