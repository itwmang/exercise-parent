package com.spring.cloud.service.exception;

import com.spring.boot.framework.api.BaseException;

import java.text.MessageFormat;

/**
 * 用户操作异常信息枚举类
 */
public enum UserException {

    user_insert_error("10001", "新增用户失败:[{0}]"),
    user_get_error("10002", "获取用户信息失败:[{0}]"),
    user_insert_not_account_error("10003", "用户账号为空,创建用户失败"),
    user_insert_not_passwd_error("10004", "用户密码为空,创建用户失败"),
    ;
    private String errorCode;
    private String erroMsg;

    public static final String profix = "user-service";

    UserException(String errorCode, String erroMsg) {
        this.errorCode = errorCode;
        this.erroMsg = erroMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
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
