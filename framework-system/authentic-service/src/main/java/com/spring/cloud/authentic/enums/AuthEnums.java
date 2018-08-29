package com.spring.cloud.authentic.enums;

/**
 * Created by yingying on 18-5-6.
 */
public enum AuthEnums {
    ;

    AuthEnums() {
    }

    public enum AuthLoginEnums{
        auth_login_fail_notfount_account(404,"用户名不存在"),
        auth_login_fail_passwd_error(401,"密码错误"),
        auth_error(401,"用户密码接口请求失败"),
        ;

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        AuthLoginEnums() {
        }

        AuthLoginEnums(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
