package com.spring.cloud.zuul.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限验证核心操作类
 */
public interface PermissionService {

    //验证操作是否有权限
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
