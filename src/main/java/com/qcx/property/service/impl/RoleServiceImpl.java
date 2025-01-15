package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.service.RoleService;
import com.qcx.property.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 67121
* @description 针对表【role】的数据库操作Service实现
* @createDate 2025-01-10 15:47:20
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    /**
     * 验证角色 id 的有效性
     * @param roleId
     */
    public void verifyRole(int roleId) {
        Role addRole = this.getById(roleId);
        if (addRole == null) {
            throw new BusinessException(ErrorType.ADD_ROLE_ERROR);
        }
    }
}




