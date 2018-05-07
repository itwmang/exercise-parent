package com.spring.cloud.authentic.controller;

import com.spring.boot.framework.api.BaseController;
import com.spring.boot.framework.api.BaseResponse;
import com.spring.cloud.authentic.dto.AuthResponse;
import com.spring.cloud.authentic.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yingying on 18-5-6.
 */
@RestController
public class AuthController extends BaseController {


    @Autowired
    private AuthService authService;

    /**
     * 用户登录,用户名,密码,验证码(暂时不用)
     *
     * @param account
     * @param passwd
     * @param capcha
     * @return
     */
    @PostMapping(value = "/login")
    public BaseResponse login(@RequestParam String account, @RequestParam String passwd, @RequestParam String capcha) {
        //验证码
        AuthResponse res = authService.login(account, passwd);
        if (res.isStatus()) {
            return super.success(res.getToken());
        } else {
            return super.error(res.getCode(), res.getMsg());
        }
    }
}
