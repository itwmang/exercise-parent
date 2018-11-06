package com.spring.cloud.zuul.jwt;

/**
 * Created by yingying on 18-5-12.
 */

import com.alibaba.ttl.TransmittableThreadLocal;
import com.spring.cloud.framework.utils.constant.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 用户操作类
 */
public class JwtUtils {

    private final static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private static final ThreadLocal<String> THREAD_LOCAL = new TransmittableThreadLocal<>();


    /**
     * 设置用户信息
     *
     * @param userAccount
     */
    public static void setUser(String userAccount) {
        THREAD_LOCAL.set(userAccount);
        MDC.put(SecurityConstant.KEY_USER, userAccount);
    }

    /**
     * 获取登录用户
     *
     * @param userAccount
     * @return
     */
    public static String getUser(String userAccount) {
        return THREAD_LOCAL.get();
    }

    /***
     * 获取登录用户,如果没有登录则获取为null
     * @param userAccount
     * @return
     */
    public static String getUserAccount(String userAccount) {
        return THREAD_LOCAL.get();
    }


    /**
     * 通过token获取用户名
     *
     * @param token
     * @param jwtkey
     * @return
     */
    public static String setUserAccount(String token, String jwtkey) {
        Claims claims = buildClaims(buildToken(token), jwtkey);
        if (null == claims) {
            return "";
        }
        return claims.get(SecurityConstant.USER_ACCOUNT).toString();
    }

    /**
     * 根据用户请求中的token获取用户账号
     *
     * @param request
     * @param jwtKey
     * @return
     */

    public static String getUserAccount(HttpServletRequest request, String jwtKey) {
        Claims claims = buildClaims(buildToken(getToken(request)), jwtKey);
        if (null == claims) {
            return "";
        }
        return claims.get(SecurityConstant.USER_ACCOUNT).toString();
    }

    /**
     * 根据token和
     *
     * @param token
     * @param jwtKey
     * @return
     */
    public static List<String> getRole(String token, String jwtKey) {
        Claims claims = buildClaims(buildToken(token), jwtKey);
        if (null == claims) {
            return new ArrayList<String>();
        }
        return (List<String>) claims.get(SecurityConstant.USER_ROLES);
    }


    /**
     * 从用户请求中获取token
     *
     * @param request
     */
    public static String getToken(HttpServletRequest request) {
        String authorization = request.getHeader(SecurityConstant.REQUEST_AUTHENCATION);
        return buildToken(authorization);
    }

    /**
     * jwt解密
     *
     * @param token
     * @param jwtkey
     * @return
     */
    private static Claims buildClaims(String token, String jwtkey) {

        if (StringUtils.isBlank(token) || StringUtils.isBlank(jwtkey)) {
            return null;
        }

        String key = "";
        try {
            key = Base64.getEncoder().encodeToString(jwtkey.getBytes());
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("用户token解析异常,token:{},key:{}", token, key);
            return null;
        }
    }

    private static String buildToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        if (!token.contains(SecurityConstant.TOKEN_SPILT)) {
            return token;
        }
        return StringUtils.substringAfter(token, SecurityConstant.TOKEN_SPILT);
    }
}
