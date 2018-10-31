package com.spring.cloud.service.service;

import com.spring.cloud.service.entity.RoleMenuPermission;

import java.util.List;
import java.util.Set;

/**
 * Created by yingying on 2018/8/23.
 */
public interface PermissionService {
    /**
     * 通过角色id获取菜单
     * @param roleCode
     * @return
     */
    Set<String> findMenuPermission(String roleCode);

    /**
     * 更新角色-菜单-权限关系
     * @param roleCode
     * @param permissions
     * @return
     */
    boolean updateRoleMenuPermission(String roleCode, String... permissions);

    /**
     * 通过角色id获取权限相关菜单
     * @param roleId
     * @return
     */
    List<RoleMenuPermission> findMenuPermissionByRoleId(Integer roleId);



}
