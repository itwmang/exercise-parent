package com.spring.boot.framework.api;

/**
 * 通用接口返回类型
 * Created by yingying on 18-5-6.
 */
public class BaseResponse {
    private final static int successCode = 200;
    private final static String sucessMsg = "请求成功";
    private final static int errorCode = 400;

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

    public static BaseResponse success(Object object) {
        BaseResponse res = new BaseResponse();
        res.setCode(successCode);
        res.setMsg(sucessMsg);
        res.setStatus(true);
        res.setContent(object);
        return res;
    }

    /**
     * 通用返回请求失败编码
     * @param errorMsg
     * @return
     */
    public static BaseResponse error(String errorMsg) {
        BaseResponse res = new BaseResponse();
        res.setCode(errorCode);
        res.setMsg(errorMsg);
        res.setStatus(false);
        return res;
    }

    /**
     * 自定义请求失败编码
     * @param errorCode_
     * @param errorMsg
     * @return
     */
    public static BaseResponse error(int errorCode_, String errorMsg) {
        BaseResponse res = new BaseResponse();
        res.setCode(errorCode_);
        res.setMsg(errorMsg);
        res.setStatus(false);
        return res;
    }

}
