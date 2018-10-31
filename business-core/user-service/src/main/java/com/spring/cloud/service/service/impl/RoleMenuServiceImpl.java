package com.spring.cloud.service.service.impl;

import com.spring.cloud.service.cache.UserCacheKey;
import com.spring.cloud.service.dao.RoleMenuMapper;
import com.spring.cloud.service.entity.RoleMenu;
import com.spring.cloud.service.entity.RoleMenuExample;
import com.spring.cloud.service.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangmian
 * @Date: 2018/9/4 09:36
 * @Description:
 */
@Service("roleMenuService")
@Cacheable(cacheNames = UserCacheKey.ROLE_MENU_INFO)
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuMapper mapper;

    @Override
    @Cacheable(key = "'role_menu_'+#roleId")
    public List<RoleMenu> findRoleMenuByRoleId(Integer roleId) {
        RoleMenuExample exp = new RoleMenuExample();
        exp.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleMenu> roleMenus = mapper.selectByExample(exp);
        return roleMenus;
    }

    @Override
    public boolean delete(Integer id) {
        int menuCount = mapper.deleteByPrimaryKey(id);
        return menuCount>0;
    }
}
