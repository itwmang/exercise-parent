package com.spring.cloud.service.service;

import com.github.pagehelper.PageInfo;
import com.spring.boot.framework.api.beans.page.PageParams;
import com.spring.boot.framework.api.beans.RoleDeptBean;
import com.spring.cloud.service.entity.Role;

import java.util.List;

/**
 *
 */
public interface RoleService {
    List<Role> findRoleListByDeptId(Integer deptId);

    List<Role> getRoleList();

    PageInfo<RoleDeptBean> findAll(PageParams pageParams, Role role);

    Role saveOrUpdate(Role role);

    Role saveRoleAndDept(Role role, Integer deptId);

    boolean delById(Integer roleId);

    Role findRoleByCode(String roleCode);


    List<Role> findRoleListByUserId(Integer userId);
}
