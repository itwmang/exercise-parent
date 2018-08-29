package com.spring.cloud.authentic.service.impl;

import com.spring.boot.framework.api.beans.AuthRole;
import com.spring.cloud.framework.constant.AuthenticConstant;
import com.wmang.system.auth.model.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yingying on 2018/6/6.
 */
public class UserDetailsImpl implements UserDetails {


    public static final Logger log = LoggerFactory.getLogger(UserDetailsImpl.class);

    private String username;
    private String password;
    private int status = 0;//角色列表
    private List<AuthRole> roles = new ArrayList<>();

    public UserDetailsImpl(List<AuthRole> roles, String username, String password, int status) {
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public UserDetailsImpl(AuthUser user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.roles = user.getRoles();
    }




    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (AuthRole role : roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        return authorityList;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return AuthenticConstant
                .STATUS_LOCK == this.status ? false : true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return AuthenticConstant.STATUS_NORMAL == this.status ? false : true;
    }


    public static Logger getLog() {
        return log;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AuthRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AuthRole> roles) {
        this.roles = roles;
    }
}
