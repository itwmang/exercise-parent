package com.spring.cloud.service.controller.feignClient;

import com.spring.boot.framework.api.aop.PrePermissions;
import com.spring.boot.framework.api.BaseController;
import com.spring.boot.framework.api.Module;
import com.spring.cloud.service.service.UserService;
import com.wmang.system.api.auth.api.UserFeignApi;
import com.wmang.system.api.auth.model.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangmian
 * @Date: 2018/9/4 10:14
 * @Description:
 */
@RestController
@PrePermissions(value = Module.API,required = false)
@Api(value = "API - UserFeignApi",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserFeignApiClient extends BaseController implements UserFeignApi {
    @Autowired
    private UserService userService;

    /**
     * 通过用户名查询用户及角色信息
     * @param username
     * @return
     */
    @Override
    @ApiOperation(httpMethod = GET , value = "通过用户名获取用户信息")
    @ApiImplicitParam(name = "username" , value = "用户名" , required = true , dataType = "String" , paramType = "path")
    public AuthUser findUserByUsername(@PathVariable("username") String username) {
        if(StringUtils.isBlank(username)){
            return null;
        }
        return userService.findUserByUsername(username);
    }

    @Override
    @ApiOperation(httpMethod = GET , value = "通过手机号获取用户信息")
    @ApiImplicitParam(name = "mobile" , value = "手机号" , required = true , dataType = "String" , paramType = "path")
    public AuthUser findUserByMobile(@PathVariable("mobile") String mobile) {
        if(StringUtils.isBlank(mobile)){
            return null;
        }
        return userService.findUserByMobile(mobile);
    }
}
