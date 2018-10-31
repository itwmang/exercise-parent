package com.spring.cloud.authentic.config;

import com.spring.boot.framework.config.FilterUrlsConfig;
import com.spring.cloud.authentic.ajax.AjaxSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangmian
 * @Date: 2018/9/4 21:19
 * @Description:  认证服务器开发接口配置
 */
@Order(SecurityProperties.BASIC_AUTH_ORDER - 4)
@Configuration
@EnableWebSecurity
public class AuthResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FilterUrlsConfig filterUrlsConfig;
    @Autowired
    private AjaxSecurityConfig ajaxSecurityConfig;
    @Autowired
    @Qualifier(value = "userDetailService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.formLogin()
                //可以经过授权登陆访问
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/signin")
                .and()
                .authorizeRequests();


        //同一时间只允许一个账号登录
//        .sessionManagement()
//                .maximumSessions(1)
//                .expiredUrl("/login?expired");


        for (String url :filterUrlsConfig.getCollects()) {
            registry.antMatchers(url).permitAll();
        }

        registry.anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
        http.apply(ajaxSecurityConfig);
    }


    @Bean(name=BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //注入userDetailsService的实现类
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        authenticationProviders.add(daoAuthenticationProvider);
//        return new ProviderManager(authenticationProviders);
//    }

}
