package com.spring.cloud.zuul.fullback;

import com.spring.cloud.framework.utils.constant.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Admin 模块异常回调
 * 
 * @author liuweijw
 */
@Slf4j
@Component
public class UserFallbackProvider implements FallbackProvider {


	@Override
	public String getRoute() {
		return SecurityConstant.ADMIN_SERVICE;
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		log.info(route);
		return new ClientHttpResponse() {

			@Override
			public HttpStatus getStatusCode() {
				return HttpStatus.SERVICE_UNAVAILABLE;
			}

			@Override
			public int getRawStatusCode() {
				return HttpStatus.SERVICE_UNAVAILABLE.value();
			}

			@Override
			public String getStatusText() {
				return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
			}

			@Override
			public void close() {
			}

			@Override
			public InputStream getBody() {
				if (cause != null && cause.getMessage() != null) {
					log.error("调用:{} 异常：{}", getRoute(), cause.getMessage());
					return new ByteArrayInputStream(cause.getMessage().getBytes());
				} else {
					log.error("调用:{} 异常：{}", getRoute(), SecurityConstant.BUSINESS_ADMIN_NOTSUPPORT);
					return new ByteArrayInputStream(
							SecurityConstant.BUSINESS_ADMIN_NOTSUPPORT
									.getBytes());
				}
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
		};
	}
}
