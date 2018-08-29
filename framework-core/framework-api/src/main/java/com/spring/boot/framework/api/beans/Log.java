package com.spring.boot.framework.api.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志
 */
public class Log implements Serializable {
    /*日志id*/
    private long id;
    /*日志类型*/
    private int type;
    /*标题*/
    private String title;
    /*请求服务模块id*/
    private String serviceId;
    /*请求操作ip地址*/
    private String remoteAddr;
    /*操作人*/
    private String userAgent;
    /*请求地址*/
    private String requestUrl;
    /*操作类*/
    private String optClass;
    /*操作方法*/
    private String optMethod;
    /*操作参数*/
    private String optParams;
    /*操作时间*/
    private long optTime;
    /*异常信息*/
    private String exceptionInfo;

    /*创建人*/
    private String createBy;
    /*创建时间*/
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getOptClass() {
        return optClass;
    }

    public void setOptClass(String optClass) {
        this.optClass = optClass;
    }

    public String getOptMethod() {
        return optMethod;
    }

    public void setOptMethod(String optMethod) {
        this.optMethod = optMethod;
    }

    public String getOptParams() {
        return optParams;
    }

    public void setOptParams(String optParams) {
        this.optParams = optParams;
    }

    public long getOptTime() {
        return optTime;
    }

    public void setOptTime(long optTime) {
        this.optTime = optTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
