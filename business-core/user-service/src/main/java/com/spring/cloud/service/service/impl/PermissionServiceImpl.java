package com.spring.cloud.service.service.impl;

import com.spring.cloud.framework.utils.WebUtils;
import com.spring.cloud.service.cache.UserCacheKey;
import com.spring.cloud.service.dao.RoleMenuPermissionMapper;
import com.spring.cloud.service.entity.Role;
import com.spring.cloud.service.entity.RoleMenu;
import com.spring.cloud.service.entity.RoleMenuPermission;
import com.spring.cloud.service.service.PermissionService;
import com.spring.cloud.service.service.RoleMenuService;
import com.spring.cloud.service.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private RoleMenuPermissionMapper roleMenuPermissionMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;


    @Override
    @Cacheable(cacheNames = UserCacheKey.PERMISSION_INFO, key = "'permission_'+#roleId")
    public List<RoleMenuPermission> findMenuPermissionByRoleId(Integer roleId) {
        if (null == roleId || roleId == 0) {
            return null;
        }
        List<RoleMenuPermission> roleMenuPermissions = roleMenuPermissionMapper.findRoleMenuPermissionById(roleId);
        return roleMenuPermissions;
    }


    @Override
    public Set<String> findMenuPermission(String roleCode) {
        Set<String> permissions = new HashSet<>();
        //通过角色编码查询角色信息
        Role role = roleService.findRoleByCode(roleCode);
        if (null == role) {
            return permissions;
        }
        //查询菜单
        List<RoleMenuPermission> permissionsList = this.findMenuPermissionByRoleId(role.getRoleId());
        if (CollectionUtils.isEmpty(permissionsList)) {
            return permissions;
        }
        permissionsList.forEach(item -> {
            permissions.add(item.getPermission());
        });

        return permissions;
    }

    @Override
    public boolean updateRoleMenuPermission(String roleCode, String... permissions) {
        Role role = roleService.findRoleByCode(roleCode);
        if(null == role ){
            return Boolean.FALSE;
        }
        //菜单集合
        Map<Integer,List<String>> roleMenuIds = new HashMap<>();
        for (String permission : permissions) {
            if(!permission.contains("|")){
                continue;
            }
            String[] menuPermission = permission.split("|");
            Integer menuId = WebUtils.parseStrToInteger(menuPermission[0],null);
            if(null == menuId || StringUtils.isBlank(menuPermission[1])){
                continue;
            }
            if(!roleMenuIds.containsKey(menuId)){
                roleMenuIds.put(menuId,new ArrayList<>());
            }
            roleMenuIds.get(menuId).add(menuPermission[1].trim());
        }

        //删除roleMenu 和 roleMenuPermission

        this.deleteRoleMenuPermission(role.getRoleId());

        return false;
    }

    private boolean deleteRoleMenuPermission(Integer roleId) {
        List<RoleMenu> menuList = roleMenuService.findRoleMenuByRoleId(roleId);

        Set<Integer> roleMenuIdSet = new HashSet<>();

        menuList.forEach(item -> {
            roleMenuIdSet.add(item.getId());
            roleMenuService.delete(item.getId());
        });
        
        Integer[] roleMenuIdArr = new Integer[roleMenuIdSet.size()];

        return this.deleteROleMenuPermissionByRoleMenuId(roleMenuIdSet.toArray(roleMenuIdArr));

    }

    private boolean deleteROleMenuPermissionByRoleMenuId(Integer[] ids) {
        int delCount = roleMenuPermissionMapper.deleteByRoleMenuIds(ids);
        return delCount>0;
    }


}
