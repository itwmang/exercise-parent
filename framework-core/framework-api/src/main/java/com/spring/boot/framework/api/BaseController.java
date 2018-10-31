package com.spring.boot.framework.api;

/**
 * Created by yingying on 18-5-6.
 */
public class BaseController {

    // do anything

    protected static final String	POST	= "POST";

    protected static final String	GET		= "GET";


    private final static int successCode = 200;
    private final static String sucessMsg = "请求成功";
    private final static int errorCode = 400;

    public BaseResponse success(Object object) {
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
    public BaseResponse error(String errorMsg) {
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
    public BaseResponse error(int errorCode_, String errorMsg) {
        BaseResponse res = new BaseResponse();
        res.setCode(errorCode_);
        res.setMsg(errorMsg);
        res.setStatus(false);
        return res;
    }

}
