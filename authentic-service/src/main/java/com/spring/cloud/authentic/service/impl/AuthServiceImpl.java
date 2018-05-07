package com.spring.cloud.authentic.service.impl;

import com.spring.cloud.authentic.dto.AuthResponse;
import com.spring.cloud.authentic.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * Created by yingying on 18-5-6.
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse login(String account, String passwd) {

        return null;
    }
}
