package com.spring.cloud.framework.utils.constant;

/**
 * 常量
 */
public class SecurityConstant {

    /**
     * 刷新token url
     */
    public static final String refresh_token_url = "refresh_token";
    /**
     * oauth token url
     */
    public static final String oauth_token_url = "/oauth/token";
    /**
     * mobile token url
     */
    public static final String mobile_token_url = "/mobile/token";

    /*密码标识*/
    public static final String PASSWORD = "password";
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
    public static final String BASIC = "Basic";
    //手机端获取token
    public static final String MOBILE_TOKEN_URL = "/mobile/token";



    /*token分隔符*/
    public static final String TOKEN_SPILT = "Bearer";

    /*threadlocal userAccount 标识*/
    public static final String USER_ACCOUNT = "userAccount";

    /*threadlocal userRoles 标识*/
    public static final String USER_ROLES = "userRoles";

    /*请求头部获取token标识*/
    public static final String REQUEST_AUTHENCATION = "Authorization";

    public static final String KEY_USER = "user";
    public static final String TOKEN_PREFIX = "authorization";


    public static final String SYSTEM_AUTH_NOTSUPPORT = "授权模块不可用";

    public static final String BUSINESS_ADMIN_NOTSUPPORT = "权限管理模块不可用";

    public static final String COMMONS_AUTH_NOTSUPPORT = "授权失败，禁止访问";

    /**
     * 认证服务的SERVICEID（zuul 配置的对应）
     */
    public static final String AUTH_SERVICE = "authentic-service";

    /**
     * ADMIN模块
     */
    public static final String ADMIN_SERVICE = "user-service";
    /**
     * 用户信息头
     */
    public static final String USER_HEADER = "request-user-header";
    /**
     * 角色信息头
     */
    public static final String ROLE_HEADER = "request-role-header";
}
