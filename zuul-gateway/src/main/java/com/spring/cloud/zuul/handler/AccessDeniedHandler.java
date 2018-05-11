package com.spring.cloud.zuul.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.framework.api.BaseResponse;
import com.spring.boot.framework.constant.CommonConstant;
import com.spring.cloud.zuul.enums.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 授权拒绝处理器,默认覆盖OAuth2AccessDeniedHandler包装失败信息到DeniedExceptio
 */
public class AccessDeniedHandler extends OAuth2AccessDeniedHandler {

    private Logger log = LoggerFactory.getLogger(AccessDeniedHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
        log.info("授权失败,禁止访问");
        response.setCharacterEncoding(CommonConstant.chatset);
        response.setContentType(CommonConstant.contentType);
        response.setStatus(AuthException.auth_acesss_denied_error.getErrorCode());
        PrintWriter pw = response.getWriter();
        pw.print(objectMapper.writeValueAsString(BaseResponse.error(AuthException.auth_acesss_denied_error.getErrorCode(), AuthException.auth_acesss_denied_error.getErroMsg())));
    }
}
