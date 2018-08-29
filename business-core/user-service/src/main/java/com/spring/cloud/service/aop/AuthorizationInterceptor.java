package com.spring.cloud.service.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.framework.redis.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能权限切面验证
 * Created by yingying on 2018/8/23.
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{
    private JwtConfig jwtConfig;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }
}
