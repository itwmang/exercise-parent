package com.spring.cloud.service.service;

import java.util.Set;

/**
 * Created by yingying on 2018/8/23.
 */
public interface PermissionService {
    /**
     * 通过角色获取菜单
     * @param roleCode
     * @return
     */
    Set<String> findMenuPermission(String roleCode);

    boolean updateRoleMenuPermission(String roleCode, String... permissions);

    List<RoleMenuPermission> findMenuPermissionByRoleId(Integer roleId);



}
