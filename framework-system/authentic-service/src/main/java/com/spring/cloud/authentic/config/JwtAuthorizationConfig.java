package com.spring.cloud.authentic.config;

import com.spring.boot.framework.config.JwtConfig;
import com.spring.boot.framework.config.JwtRedisTokenStore;
import com.spring.cloud.framework.utils.constant.SecurityConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务器认证实现逻辑
 * Created by yingying on 18-5-22.
 */
@Configuration
@Order(Integer.MIN_VALUE)
@EnableAuthorizationServer
public class JwtAuthorizationConfig extends AuthorizationServerConfigurerAdapter {


    public static final Logger log = LoggerFactory.getLogger(JwtAuthorizationConfig.class);

    @Autowired
    private AuthServerConfig authServerConfig;
    @Autowired
    private JwtConfig jwtConfig;
    //    @Autowired
//    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier(value = "userDetailService")
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(authServerConfig.getClientId()).secret(authServerConfig.getClientSecret())
                .authorizedGrantTypes(SecurityConstant.refresh_token_url, SecurityConstant.PASSWORD, SecurityConstant.AUTHORIZATION_CODE)
                .scopes(authServerConfig.getScope())
                //true 直接跳转客户端 false 跳转到用户确认授权页面
                .autoApprove(true);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tec = new TokenEnhancerChain();
        tec.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
        endpoints.tokenStore(redisTokenStore())
                .tokenEnhancer(tec)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService);


//        endpoints.tokenServices(tokenServices());

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
//          获取JWt加密key: /oauth/token_key 采用RSA非对称加密时候使用。对称加密禁止访问
//          .tokenKeyAccess("isAuthenticated")
                .checkTokenAccess("permitAll()");
    }


//    @Bean
//    @Primary
//    public AuthorizationServerTokenServices tokenServices() {
//        // 配置TokenServices参数
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(redisTokenStore());
//        tokenServices.setTokenEnhancer(tokenEnhancer());
//        tokenServices.setAccessTokenValiditySeconds(30);
//        tokenServices.setRefreshTokenValiditySeconds(7200);
//        return tokenServices;
//    }

    @Bean
    @Primary
    public TokenStore redisTokenStore() {
//        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//        redisTokenStore.setPrefix(SecurityConstant.REDIS_PREFIX);
        JwtRedisTokenStore redisTokenStore = new JwtRedisTokenStore();
        return redisTokenStore;
    }

    /**
     * Jwt 生成token 定制化处理
     *
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(1);
            additionalInfo.put("license", SecurityConstant.LICENSE);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jat = new JwtAccessTokenConverter();
        jat.setSigningKey(jwtConfig.getJwtKey());
        log.info("Initializing jwt with public key:\n" + authServerConfig.getPublicKey());
        // 采用RSA非对称加密
        // JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//         jwtAccessTokenConverter.setSigningKey(authServerConfiguration.getPrivateKey());
//         jwtAccessTokenConverter.setVerifierKey(authServerConfiguration.getPublicKey());
        return jat;
    }
}
