package com.wmang.system.api.auth.hystrix;

import com.spring.boot.framework.api.beans.AuthPermission;
import com.wmang.system.api.auth.api.PermissionFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class PermissionFeignApiHystrix implements PermissionFeignApi {

	@Override
	public Set<AuthPermission> findMenuByRole(String roleCode) {
		log.error("调用{}异常:{}", "findMenuByRole", roleCode);
		return null;
	}

}
