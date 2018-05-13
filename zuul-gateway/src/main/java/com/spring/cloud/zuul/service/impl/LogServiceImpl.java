package com.spring.cloud.zuul.service.impl;

import com.netflix.zuul.context.RequestContext;
import com.spring.boot.framework.api.beans.AuthLog;
import com.spring.boot.framework.api.beans.Log;
import com.spring.boot.framework.api.constants.CommonConstant;
import com.spring.boot.framework.api.constants.MqQueueConstant;
import com.spring.cloud.zuul.service.LogService;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.io.IoUtil;
import com.xiaoleilu.hutool.util.URLUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Component
public class LogServiceImpl implements LogService {
    private Logger log = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void send(RequestContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        Log syslog = new Log();
        syslog.setType(CommonConstant.STATUS_NORMAL);
        syslog.setRemoteAddr(HttpUtil.getClientIP(request));
        syslog.setRequestUrl(URLUtil.getPath(requestUri));
        syslog.setOptMethod(method);
        syslog.setUserAgent(request.getHeader("user-agent"));
        syslog.setOptParams(HttpUtil.toParams(request.getParameterMap()));
        Long startTime = (Long) requestContext.get("startTime");
        syslog.setOptTime(System.currentTimeMillis() - startTime);
        Date currentDate = new Date();
        syslog.setCreateTime(currentDate);
        if (requestContext.get(CommonConstant.SERVICE_ID) != null) {
            syslog.setServiceId(requestContext.get(CommonConstant.SERVICE_ID).toString());
        }

        // 正常发送服务异常解析
        if (requestContext.getResponseStatusCode() != HttpStatus.SC_OK
                && null != requestContext.getResponseDataStream()) {
            InputStream responseStream = requestContext.getResponseDataStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream inputStream = null;
            InputStream responseDataStream = null;
            byte[] buffer = IoUtil.readBytes(responseStream);
            try {
                baos.write(buffer);
                baos.flush();
                inputStream = new ByteArrayInputStream(baos.toByteArray());
                responseDataStream = new ByteArrayInputStream(baos.toByteArray());
                String response = IoUtil.read(inputStream, CommonConstant.chatset);
                syslog.setType(CommonConstant.STATUS_LOCK);
                syslog.setExceptionInfo(response);
                requestContext.setResponseDataStream(responseDataStream);
            } catch (IOException e) {
                log.error("响应流解析异常：", e);
                throw new RuntimeException(e);
            } finally {
                IoUtil.close(responseDataStream);
                IoUtil.close(baos);
                IoUtil.close(responseStream);
            }
        }

        // 网关内部异常
        Throwable throwable = requestContext.getThrowable();
        if (throwable != null) {
            log.error("网关异常", throwable);
            syslog.setExceptionInfo(throwable.getMessage());
        }

        AuthLog authLog = new AuthLog();
        // 保存发往MQ（只保存授权）
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && StringUtils.isNotBlank(authentication.getName())) {
            syslog.setCreateBy(authentication.getName());
            authLog.setLog(syslog);
            rabbitTemplate.convertAndSend(MqQueueConstant.LOG_QUEUE, authLog);
        }
    }

}
