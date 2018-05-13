package com.spring.cloud.zuul.service;

import com.spring.boot.framework.api.beans.AuthPermission;
import com.spring.cloud.zuul.service.impl.MenuPermissionServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * Created by yingying on 18-5-12.
 */
@FeignClient(name = "authentic-service",fallback = MenuPermissionServiceFallback.class)
public interface MenuPermissionService {

    @GetMapping(value = "/authentic/api/findMenuByRole/{roleCode}")
    Set<AuthPermission> findMenuByRole(@PathVariable String roleCode);
}
