package com.spring.cloud.zuul.constant;

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

    /*token分隔符*/
    public static final String TOKEN_SPILT = "Bearer";

    /*threadlocal userAccount 标识*/
    public static final String USER_ACCOUNT = "userAccount";

    /*threadlocal userRoles 标识*/
    public static final java.lang.String USER_ROLES = "userRoles";

    /*请求头部获取token标识*/
    public static final String REQUEST_AUTHENCATION = "Authorization";

    public static final String KEY_USER = "user";
    public static final String TOKEN_PREFIX = "authorization";

    public static final String AUTH_SERVICE = "authentic-service";
}
