package com.spring.cloud.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.framework.api.beans.RoleDeptBean;
import com.spring.boot.framework.api.beans.page.PageBean;
import com.spring.boot.framework.api.beans.page.PageParams;
import com.spring.cloud.framework.utils.constant.CommonConstant;
import com.spring.cloud.service.cache.UserCacheKey;
import com.spring.cloud.service.dao.RoleDeptMapper;
import com.spring.cloud.service.dao.RoleMapper;
import com.spring.cloud.service.entity.Role;
import com.spring.cloud.service.entity.RoleDept;
import com.spring.cloud.service.entity.RoleExample;
import com.spring.cloud.service.service.RoleService;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangmian
 * @Date: 2018/8/30 23:26
 * @Description: 角色操作
 */
@Slf4j
@Service("roleService")
@CacheConfig(cacheNames = UserCacheKey.ROLE_INFO)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleDeptMapper roleDeptMapper;

    @Override
    @Cacheable(key = "'role_deptId_'+#deptId")
    public List<Role> findRoleListByDeptId(Integer deptId) {
        if(null == deptId || deptId == 0){
            return null;
        }
        List<Role> roles = roleMapper.findRoleListByDeptId(deptId);
        return roles;
    }

    @Override
    @Cacheable(key = "role_list")
    public List<Role> getRoleList() {
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    @Cacheable(key = "'page_role_'+#p0.currentPage+'_'+#p0.pageSize+'_'+#p1.roleCode")
    public PageInfo<RoleDeptBean> findAll(PageParams pageParams, Role role) {
        PageBean<RoleDeptBean> pageBean = new PageBean<>();
        if(0 == pageParams.getCurrentPage()&& 0 == pageParams.getPageSize()){
            return new PageInfo<RoleDeptBean>();
        }
        PageHelper.startPage(pageParams.getCurrentPage(),pageParams.getPageSize());
        Map<String,Object> param = new HashMap<String,Object>();
        if(null != role.getRoleId() && 0 != role.getRoleId()){
            param.put("roleId",role.getRoleId());
        }
        if(StringUtils.isNotBlank(role.getRoleCode())){
            param.put("roleCode",role.getRoleCode());
        }
        if(StringUtils.isNotBlank(role.getRoleName())){
            param.put("roleName",role.getRoleName());
        }
        List<RoleDeptBean> roles = roleMapper.selectRoleDeptByParam(param);
        PageInfo<RoleDeptBean> p = new PageInfo<>(roles);
        return p;
    }

    @Override
    public Role saveOrUpdate(Role role) {
        if(role.getRoleId() != null && role.getRoleId() != 0){
            roleMapper.updateByPrimaryKey(role);
        }else{
            roleMapper.insert(role);
        }
        return role;
    }

    @Override
    public Role saveRoleAndDept(Role role,Integer deptId) {
        if(role == null || role.getRoleId()==0 || deptId == null || deptId == 0){
            return null;
        }
        this.saveOrUpdate(role);
        roleDeptMapper.deleteByRoleId(role.getRoleId());
        RoleDept roleDept = new RoleDept();
        roleDept.setDeptId(deptId);
        roleDept.setRoleId(role.getRoleId());
        roleDeptMapper.insert(roleDept);
        return role;
    }

    @Override
    public boolean delById(Integer roleId) {
         if(roleId == null || roleId >=0 ){
             return Boolean.FALSE;
         }
         Role role = new Role();
         role.setRoleId(roleId);
         role.setStatu(CommonConstant.STATUS_DEL.shortValue());
         int delstatus = roleMapper.updateByPrimaryKeySelective(role);
        return delstatus>0;
    }

    @Override
    @Cacheable(key = "'role_code'+#roleCode")
    public Role findRoleByCode(String roleCode) {
       RoleExample exp = new RoleExample();
       exp.createCriteria().andRoleCodeEqualTo(roleCode);
        List<Role> roles = roleMapper.selectByExample(new RoleExample());
        return CollectionUtil.isEmpty(roles)? null : roles.get(0);
    }

    @Override
    @Cacheable(key = "'role_userId_'+#userId")
    public List<Role> findRoleListByUserId(Integer userId) {
        if(null == userId || userId == 0){
            return null;
        }
        List<Role> roles = roleMapper.findRoleListByUserId(userId);
        return roles;
    }
}
