package com.spring.cloud.service.entity;

/**
 * 系统菜单功能权限
 */
public class RoleMenuPermission {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleMenuId;
    /**
     * 权限编码
     */
    private String permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}