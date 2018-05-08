package com.spring.cloud.authentic.controller;

import com.spring.boot.framework.api.BaseController;
import com.spring.boot.framework.api.BaseResponse;
import com.spring.boot.framework.utils.Base64Utils;
import com.spring.cloud.authentic.dto.AuthResponse;
import com.spring.cloud.authentic.enums.AuthEnums;
import com.spring.cloud.authentic.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

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
//        Base64.Decoder();
        account = Base64Utils.decode(account);
        passwd = Base64Utils.decode(passwd);
        AuthResponse res = authService.login(account, passwd);
        if(null == res){
            return super.error(AuthEnums.AuthLoginEnums.auth_error.getCode(), AuthEnums.AuthLoginEnums.auth_error.getMsg());
        }
        if (null != res && res.isStatus()) {
            return super.success(res.getToken());
        } else {
            return super.error(res.getCode(), res.getMsg());
        }
    }



}
