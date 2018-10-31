package com.spring.cloud.service.controller.feignClient;

import com.spring.boot.framework.api.BaseController;
import com.spring.boot.framework.api.Module;
import com.spring.boot.framework.api.aop.PrePermissions;
import com.spring.boot.framework.api.beans.AuthPermission;
import com.wmang.system.api.auth.api.PermissionFeignApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@PrePermissions(value = Module.API, required = false)
@Api(value = "API - PermissionFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PermissionFeignApiClient extends BaseController implements PermissionFeignApi {

//	@Autowired
//	private MenuService	menuService;

	@Override
	@ApiOperation(httpMethod = GET, value = "通过角色获取菜单权限")
	@ApiImplicitParam(name = "roleCode", value = "用户roleCode", required = true, dataType = "string", paramType = "path")
	public Set<AuthPermission> findMenuByRole(@PathVariable("roleCode") String roleCode) {
		Set<AuthPermission> permissions = new HashSet<AuthPermission>();
		if (StringUtils.isBlank(roleCode)) return permissions;

//		Set<AuthMenu> menus = menuService.findMenuByRole(roleCode);
//		if (null == menus || menus.size() == 0) return permissions;
//
//		menus.stream().forEach(r -> {
//			permissions.add(new AuthPermission(r.getUrl()));
//		});

		return permissions;
	}

}
