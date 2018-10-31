package com.spring.boot.framework.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(
    description = "请求返回封装"
)
public class R<T> implements Serializable {
    private static final long serialVersionUID = -6043162591645086533L;
    @ApiModelProperty("返回提示语信息，默认'success'")
    private String msg = "success";
    @ApiModelProperty("返回编码：0 成功")
    private int code = 0;
    @ApiModelProperty("返回业务数据")
    private T data;

    public R() {
    }

    public R<T> success() {
        this.code = 0;
        return this;
    }

    public R<T> success(String msg) {
        this.code = 0;
        this.msg = msg;
        return this;
    }

    public R<T> failure() {
        this.code = -1;
        return this;
    }

    public R<T> failure(String msg) {
        this.code = -1;
        this.msg = msg;
        return this;
    }

    public R<T> failure(Throwable e) {
        this.msg = e.getMessage();
        this.code = -1;
        return this;
    }

    public R<T> code(int code) {
        this.code = code;
        return this;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public static R<String> of(String msg, int code) {
        return (new R()).code(code).failure(msg);
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
