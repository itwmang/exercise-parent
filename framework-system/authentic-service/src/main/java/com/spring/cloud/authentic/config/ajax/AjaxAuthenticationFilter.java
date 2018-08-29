package com.spring.cloud.authentic.config.ajax;

import com.spring.cloud.framework.constant.AuthenticConstant;
import com.spring.cloud.framework.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yingying on 2018/8/22.
 */
public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;

    protected AjaxAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public AjaxAuthenticationFilter() {
        super(new AntPathRequestMatcher(AuthenticConstant.MOBILE_TOKEN_URL,"POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if(postOnly && !httpServletRequest.getMethod().equalsIgnoreCase(HttpMethod.POST.name())){
            throw new AuthenticationServiceException("Authentication method not supportd:"+httpServletRequest.getMethod());
        }
        String mobile = obtainMobile(httpServletRequest);
        if(StringUtils.isBlank(mobile)){
            mobile = "";
        }
        AjaxAuthenticationToken token = new AjaxAuthenticationToken(mobile);
        setDetail(httpServletRequest,token);
        return this.getAuthenticationManager().authenticate(token);
    }

    private void setDetail(HttpServletRequest httpServletRequest, AjaxAuthenticationToken token) {
        token.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));
    }

    private String obtainMobile(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter(CommonConstant.SPRING_SECURITY_FORM_MOBILE_KEY);
    }
}
