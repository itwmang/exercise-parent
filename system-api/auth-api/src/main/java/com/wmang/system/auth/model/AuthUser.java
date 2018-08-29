package com.wmang.system.auth.model;

import com.spring.boot.framework.api.beans.AuthRole;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息类
 * Created by yingying on 2018/6/6.
 */
public class AuthUser implements Serializable {
    //用户id
    private int userId;
    //用户名
    private String userName;
    //密码
    private String password;
    //0 为正常，-1为非正常
    private int status = 0;
    //头像
    private String pictureUrl;

    //角色列表
    private List<AuthRole> roles = new ArrayList<>();


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<AuthRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AuthRole> roles) {
        this.roles = roles;
    }
}
