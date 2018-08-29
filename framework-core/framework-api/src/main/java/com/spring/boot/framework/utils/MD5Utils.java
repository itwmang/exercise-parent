package com.spring.boot.framework.utils;

import java.security.MessageDigest;

/**
 * Created by yingying on 18-5-8.
 */
public class MD5Utils {

    /**
     * md5标准加密计算
     *
     * @param s
     * @return
     */

    public static String encry(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * 根据用户民密码做md5加密
     *
     * @param account
     * @param passwd
     * @return
     */
    public static String encryPasswd(String account, String passwd) {
        return MD5Utils.encry(MD5Utils.encry(account.substring(0, account.length() / 2))
                .concat(MD5Utils.encry(Base64Utils.encode(passwd)))
                .concat(MD5Utils.encry(account.substring(account.length() / 2, account.length()))));
    }
}
