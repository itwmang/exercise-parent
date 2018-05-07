package com.spring.boot.framework.api;

/**
 * 通用接口返回类型
 * Created by yingying on 18-5-6.
 */
public class BaseResponse {

    //接口返回状态
    private boolean status;
    //接口返回状态码
    private int code;
    //接口返回消息内容
    private String msg;
    //接口返回具体内容
    private Object content;

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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isStatus() {

        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
