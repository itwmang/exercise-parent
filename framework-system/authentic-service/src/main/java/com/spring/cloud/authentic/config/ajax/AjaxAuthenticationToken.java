package com.spring.cloud.authentic.config.ajax;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * Created by yingying on 2018/8/22.
 */
public class AjaxAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    public AjaxAuthenticationToken(String mobile) {
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }

    public AjaxAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if(authenticated){
            throw new IllegalArgumentException("cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(authenticated);
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
