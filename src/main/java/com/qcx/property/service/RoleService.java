package com.qcx.property.service;

import com.qcx.property.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 67121
* @description 针对表【role】的数据库操作Service
* @createDate 2025-01-10 15:47:20
*/
public interface RoleService extends IService<Role> {
    void verifyRole(int roleId);
}
