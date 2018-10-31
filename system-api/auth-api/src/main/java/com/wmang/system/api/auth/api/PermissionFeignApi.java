package com.wmang.system.api.auth.api;

import com.spring.boot.framework.api.beans.AuthPermission;
import com.wmang.system.api.auth.hystrix.PermissionFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @author liuweijw
 */
@FeignClient(name = "user-service", fallback = PermissionFeignApiHystrix.class)
public interface PermissionFeignApi {

	/**
	 * 通过角色名查询菜单
	 */
	@GetMapping(value = "/api/findMenuByRole/{roleCode}")
	Set<AuthPermission> findMenuByRole(@PathVariable("roleCode") String roleCode);

}
