package com.qcx.property.role;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 角色测试
 * @author: yannqing
 * @create: 2025-01-17 10:04
 * @from: <更多资料：yannqing.com>
 **/
@SpringBootTest
public class TestRole {

    @Test
    void testRemoveIf() {
        Integer[] roleIds = {1,2,3,4,5,6,7,8,9,10,11};
        List<Integer> roleIdList = Arrays.asList(roleIds);
        List<Integer> collectRoleIdList = roleIdList.stream().filter(roleId -> !roleId.equals(2)).toList();
        System.out.println(collectRoleIdList);
    }
}
