package com.spring.cloud.authentic.dto;

import com.spring.boot.framework.api.BaseResponse;

/**
 * Created by yingying on 18-5-6.
 */
public class AuthResponse extends BaseResponse{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
