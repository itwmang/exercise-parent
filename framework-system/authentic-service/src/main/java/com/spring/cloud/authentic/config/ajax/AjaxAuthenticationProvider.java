package com.spring.cloud.authentic.config.ajax;

import com.spring.cloud.authentic.service.impl.UserDetailsImpl;
import com.wmang.system.auth.api.UserFeignApi;
import com.wmang.system.auth.model.AuthUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by yingying on 2018/8/22.
 *
 * 自定义参数验证 <br/>
 * 可以在自定义登录界面添加登录时需要的参数，如多个验证码等、可以修改默认登录名称和密码的参数名 整体流程：<br/>
 * 1.用户登录时，先经过自定义的passcard_filter过滤器，该过滤器继承了AbstractAuthenticationProcessingFilter，并且绑定了登录失败和成功时需要的处理器(跳转页面使用)<br/>
 * 2.执行attemptAuthentication方法，可以通过request获取登录页面传递的参数，实现自己的逻辑，并且把对应参数set到AbstractAuthenticationToken的实现类中<br/>
 * 3.验证逻辑走完后，调用 this.getAuthenticationManager().authenticate(token);方法，执行AuthenticationProvider的实现类的supports方法<br/>
 * 4.如果返回true则继续执行authenticate方法<br/>
 * 5.在authenticate方法中，首先可以根据用户名获取到用户信息，再者可以拿自定义参数和用户信息做逻辑验证，如密码的验证<br/>
 * 6.自定义验证通过以后，获取用户权限set到User中，用于springSecurity做权限验证<br/>
 * 7.this.getAuthenticationManager().authenticate(token)方法执行完后，会返回Authentication，如果不为空，则说明验证通过<br/>
 * 8.验证通过后，可实现自定义逻辑操作，如记录cookie信息<br/>
 * 9.attemptAuthentication方法执行完成后，由springSecuriy来进行对应权限验证，成功于否会跳转到相对应处理器设置的界面。<br/>
 *
 * @author liuweijw
 */
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    private UserFeignApi userFeignApi;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AjaxAuthenticationToken ajaxToken = (AjaxAuthenticationToken) authentication;
        AuthUser user = userFeignApi.findUserByMobile((String) ajaxToken.getPrincipal());
        if(null == user){
            throw new UsernameNotFoundException("登录账号：【"+ajaxToken.getPrincipal()+"】 不存在");
        }
        UserDetailsImpl userDetail =buildUserDetails(user);
        if(null == userDetail){
            throw new InternalAuthenticationServiceException("登录账号：【"+ajaxToken.getPrincipal()+"】 不存在");
        }
        AjaxAuthenticationToken authToken = new AjaxAuthenticationToken(userDetail,userDetail.getAuthorities());
        authToken.setDetails(ajaxToken.getDetails());
        return authToken;
    }

    private UserDetailsImpl buildUserDetails(AuthUser user) {
        return new UserDetailsImpl(user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    public UserFeignApi getUserFeignApi() {
        return userFeignApi;
    }

    public void setUserFeignApi(UserFeignApi userFeignApi) {
        this.userFeignApi = userFeignApi;
    }
}
