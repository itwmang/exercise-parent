package com.spring.cloud.authentic.service;

import com.spring.cloud.authentic.dto.AuthResponse;

/**
 * Created by yingying on 18-5-6.
 */
public interface AuthService {


    AuthResponse login(String account, String passwd);

}
