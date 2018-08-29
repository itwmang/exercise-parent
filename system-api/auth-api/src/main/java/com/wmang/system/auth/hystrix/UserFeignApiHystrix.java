package com.wmang.system.auth.hystrix;

import com.wmang.system.auth.api.UserFeignApi;
import com.wmang.system.auth.model.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by yingying on 2018/8/23.
 */
@Slf4j
@Service
public class UserFeignApiHystrix implements UserFeignApi {
    @Override
    public AuthUser findUserByUsername(String username) {
        log.error("调用{}异常:{}", "findUserByUsername", username);
        return null;
    }

    @Override
    public AuthUser findUserByMobile(String mobile) {
        log.error("调用{}异常:{}", "findUserByMobile", mobile);
        return null;
    }
}
