package com.spring.cloud.zuul.enums;

import com.spring.boot.framework.api.BaseException;

import java.text.MessageFormat;

/**
 * 用户操作异常信息枚举类
 */
public enum AuthException {

    auth_acesss_denied_error(403, "授权失败,禁止访问"),
    ;
    private int errorCode;
    private String erroMsg;

    public static final String profix = "user-service";

    AuthException(int errorCode, String erroMsg) {
        this.errorCode = errorCode;
        this.erroMsg = erroMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErroMsg() {
        return erroMsg;
    }

    public void setErroMsg(String erroMsg) {
        this.erroMsg = erroMsg;
    }


    public BaseException getException(Object... args) {
        return new BaseException(new StringBuilder(profix).append(this.errorCode).toString(), MessageFormat.format(this.getErroMsg(), args));
    }
}
