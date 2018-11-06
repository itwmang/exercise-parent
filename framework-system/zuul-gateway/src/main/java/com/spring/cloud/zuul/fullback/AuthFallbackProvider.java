package com.spring.cloud.zuul.fullback;

import com.spring.cloud.framework.utils.constant.SecurityConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wmang Auth 模块异常回调
 */
@Component
public class AuthFallbackProvider implements FallbackProvider {
    Logger log = LoggerFactory.getLogger(AuthFallbackProvider.class);

    @Override
    public String getRoute() {
        return SecurityConstant.AUTH_SERVICE;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String s, Throwable cause) {
        log.info(s);
        return new ClientHttpResponse() {

            @Override
            public InputStream getBody() throws IOException {
                if (cause != null && cause.getMessage() != null) {
                    log.error("调用:{} 异常：{}", getRoute(), cause.getMessage());
                    return new ByteArrayInputStream("invoke service fail,client exception".getBytes());
                } else {
                    log.error("调用:{} 异常：{}", getRoute(), SecurityConstant.SYSTEM_AUTH_NOTSUPPORT);
                    return new ByteArrayInputStream(SecurityConstant.SYSTEM_AUTH_NOTSUPPORT
                            .getBytes());
                }
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() {

            }

        };
    }

}
