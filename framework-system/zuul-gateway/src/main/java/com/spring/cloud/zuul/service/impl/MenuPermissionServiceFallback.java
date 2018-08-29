package com.spring.cloud.zuul.service.impl;

import com.spring.boot.framework.api.beans.AuthPermission;
import com.spring.cloud.zuul.service.MenuPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by yingying on 18-5-12.
 */

@Service
public class MenuPermissionServiceFallback implements MenuPermissionService {

    Logger log = LoggerFactory.getLogger(MenuPermissionServiceFallback.class);


    @Override
    public Set<AuthPermission> findMenuByRole(String roleCode) {
        log.error("调用{}异常:{}", "findMenuByRole", roleCode);
        return null;
    }


}
