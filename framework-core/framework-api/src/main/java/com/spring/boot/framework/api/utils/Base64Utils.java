package com.spring.boot.framework.api.utils;

import java.util.Base64;

/**
 * 通用接口返回类型
 * Created by yingying on 18-5-6.
 */
public class Base64Utils {
    /**
     * 加密
     *
     * @param code
     * @return
     */
    public static String encode(String code) {
        byte[] str = Base64.getEncoder().encode(code.getBytes());
        return new String(str);
    }

    /**
     * 解码
     *
     * @param encryCode
     * @return
     */
    public static String decode(String encryCode) {
        byte[] str = Base64.getDecoder().decode(new String(encryCode));
        return new String(str);
    }

}
