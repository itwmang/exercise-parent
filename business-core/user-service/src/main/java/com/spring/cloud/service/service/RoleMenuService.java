package com.spring.cloud.service.service;

import com.spring.cloud.service.entity.RoleMenu;

import java.util.List;

/**
 * @Author: wangmian
 * @Date: 2018/9/4 09:35
 * @Description:
 */
public interface RoleMenuService {

    List<RoleMenu> findRoleMenuByRoleId(Integer roleId);

    boolean delete(Integer id);
}
