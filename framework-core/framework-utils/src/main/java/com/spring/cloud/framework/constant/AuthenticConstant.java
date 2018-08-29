package com.spring.cloud.framework.constant;

/**
 * Created by yingying on 18-5-22.
 */
public class AuthenticConstant {
    /*刷新token标识*/
    public static final String REFRESH_TOKEN = "refresh_token";
    /*密码标识*/
    public static final String PASSWORD = "passwork";
    /*认证标识*/
    public static final String AUTHORIZATION_CODE = "authorization_code";
    /*许可证标识*/
    public static final String LICENSE = "Copyright (c) 2018 wmang";

    public static final String REDIS_PREFIX = "authentic_service_redis_store";

    /**
     * 锁定
     */
    public static final int STATUS_LOCK = 9;

    /**
     * 启用
     */
    public static final int STATUS_NORMAL = 0;

    /**
     * Token-AuthUser
     */
    public static final String TOKEN_USER_DETAIL = "token-user-detail";
    //授权标识
    public static final String AUTHORIZATION = "Authorization";
    //客户端授权
    public static final java.lang.String BASIC = "Basic";
    //手机端获取token
    public static final java.lang.String MOBILE_TOKEN_URL = "/mobile/token";
}