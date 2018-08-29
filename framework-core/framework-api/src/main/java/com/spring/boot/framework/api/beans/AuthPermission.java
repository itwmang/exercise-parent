package com.spring.boot.framework.api.beans;

/**
 * 权限控制url
 * Created by yingying on 18-5-12.
 */
public class AuthPermission {
    /*请求 url */
    private String url;

    public AuthPermission() {
    }

    public AuthPermission(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
