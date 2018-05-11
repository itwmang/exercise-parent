package com.spring.cloud.zuul.config;

import com.spring.cloud.zuul.filters.CapchaFilter;
import com.spring.cloud.zuul.handler.AccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private FilterUrlsConfig filterUrls;

    @Autowired
    private OAuth2WebSecurityExpressionHandler exceptionHandler;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CapchaFilter capchaFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //校验验证码,在校验用户密码之前
        http.addFilterBefore(capchaFilter, UsernamePasswordAuthenticationFilter.class);
        // 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        for (String url : filterUrls.getCollects()) {
            registry.antMatchers(url).permitAll();
        }
        registry.anyRequest().access("@permissionService.hasPermission(request,authentication)");
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(exceptionHandler);
        resources.accessDeniedHandler(accessDeniedHandler);
        super.configure(resources);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置解决 spring-security-oauth问题 https://github.com/spring-projects/spring-security-oauth/issues/730
     *
     * @param applicationContext ApplicationContext
     * @return OAuth2WebSecurityExpressionHandler
     */
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(
            ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }
}
