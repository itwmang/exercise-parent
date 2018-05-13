package com.spring.cloud.authentic.controller;

import com.spring.boot.framework.api.BaseController;
import com.spring.boot.framework.api.BaseResponse;
import com.spring.boot.framework.api.beans.AuthPermission;
import com.spring.boot.framework.utils.Base64Utils;
import com.spring.cloud.authentic.dto.AuthResponse;
import com.spring.cloud.authentic.enums.AuthEnums;
import com.spring.cloud.authentic.service.AuthService;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingying on 18-5-6.
 */
@RestController
public class AuthController extends BaseController {
    Logger log = LoggerFactory.getLogger(AuthController.class);

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
        StopWatch sp = new StopWatch();
        sp.start();
        account = Base64Utils.decode(account);
        passwd = Base64Utils.decode(passwd);
        AuthResponse res = authService.login(account, passwd);
        sp.stop();

        if (null == res) {
            if(log.isInfoEnabled()){
                log.info(MessageFormat.format("account[{0}]登录接口调用失败,耗时[{1}]毫秒",account,sp.getTime()));
            }
            return super.error(AuthEnums.AuthLoginEnums.auth_error.getCode(), AuthEnums.AuthLoginEnums.auth_error.getMsg());
        }
        if (null != res && res.isStatus()) {
            if (log.isInfoEnabled()) {
                log.info(MessageFormat.format("account[{0}]登录验证成功,耗时[{1}]毫秒", account, sp.getTime()));
            }
            return super.success(res.getToken());
        } else {
            if (log.isInfoEnabled()) {
                log.info(MessageFormat.format("account[{0}]登录验证失败,耗时[{1}]毫秒", account, sp.getTime()));
            }
            return super.error(res.getCode(), res.getMsg());
        }
    }



    @GetMapping(value = "/authentic/api/findMenuByRole/{roleCode}")
    public Set<AuthPermission> findMenuByRole(@PathVariable String roleCode){
        Set<AuthPermission> permissionSet = new HashSet<>();
        log.info("class:[AuthController-findMenuByRole]roleCode[{}]",roleCode);
        if(roleCode == "getuser"){
            AuthPermission auth = new AuthPermission();
            auth.setUrl("/authentic-service/getUser");
            permissionSet.add(auth);
        }
        return permissionSet;
    }

}
