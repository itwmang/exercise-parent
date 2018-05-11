package com.spring.cloud.zuul.service;


import com.netflix.zuul.context.RequestContext;

public interface LogService {
    /**
     * 往rabbitmq通道发送日志消息
     */
    void send(RequestContext requestContext);
}
