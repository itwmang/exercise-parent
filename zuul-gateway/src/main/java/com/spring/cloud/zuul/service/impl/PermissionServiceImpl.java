package com.spring.cloud.zuul.service.impl;

import com.spring.cloud.zuul.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        return false;
    }
}
