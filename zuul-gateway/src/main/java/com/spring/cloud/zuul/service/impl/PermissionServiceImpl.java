package com.spring.cloud.zuul.service.impl;

import com.spring.boot.framework.api.beans.AuthPermission;
import com.spring.cloud.zuul.constant.SecurityConstant;
import com.spring.cloud.zuul.jwt.JwtUtils;
import com.spring.cloud.zuul.service.MenuPermissionService;
import com.spring.cloud.zuul.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    public static final Logger log = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private MenuPermissionService menuPermissionService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> authoritesList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

        boolean hasPermission = false;

        if(null == principal){
            return hasPermission;
        }

        if(CollectionUtils.isEmpty(authoritesList)){
            return hasPermission;
        }
        String token = JwtUtils.getToken(request);
        if(StringUtils.isBlank(token)){
            log.error("=======>[gateway|PermissionService|hasPermission][获取request Header Authorization]");
            return hasPermission;
        }

        if(!"anonymousUser".equals(principal.toString())){
            RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
            redisTokenStore.setPrefix(SecurityConstant.TOKEN_PREFIX);
            OAuth2AccessToken accessToken = redisTokenStore.readAccessToken(token);
            if(null == accessToken || accessToken.isExpired()){
                log.error("=======>[gateway|PermissionService|hasPermission][token 过期或者不存在]");
                return hasPermission;
            }
        }

        //角色接口层面做了缓存处理.后续可以扩展处理
        Set<AuthPermission> permissionSet = new HashSet<>();
        for (SimpleGrantedAuthority sga : authoritesList) {
            permissionSet.addAll(menuPermissionService.findMenuByRole(sga.getAuthority()));
        }

        //网关处理是否拥有,菜单下的功能权限校验由调用子模块负责
        String requestUri = request.getRequestURI();
        for (AuthPermission menu : permissionSet ) {
            if(StringUtils.isBlank(menu.getUrl()) && antPathMatcher.match(menu.getUrl(),requestUri)){
                hasPermission = true;
                break;
            }
        }

        return hasPermission;
    }
}
