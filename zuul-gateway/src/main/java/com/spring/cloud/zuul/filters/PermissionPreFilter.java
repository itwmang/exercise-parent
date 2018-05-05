package com.spring.cloud.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yingying on 18-5-4.
 */
//@Component
public class PermissionPreFilter extends ZuulFilter {

    @Value("${zuulFilter.permissionFilter}")
    private boolean execPermissionFilter;

    Logger log = LoggerFactory.getLogger(PermissionPreFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return execPermissionFilter;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime",System.currentTimeMillis());
        HttpServletRequest request = ctx.getRequest();

        log.info("Zuul Filter PermissionPreFilter uri:"+request.getRequestURI());


        String token = request.getParameter("testToken");

        if(StringUtils.isNotBlank(token)){


            ctx.addZuulRequestHeader("Authorization",token);
            //如果有说明权限通过,则继续转发路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("token",token);
            log.info("Zuul Filter PermissionPreFilter validate success!");
        }else{
            //如果全县验证不成功,则不再转发路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(201);
            ctx.setResponseBody("{\"result\":\"token is not correct!\"}");
            log.info("Zuul Filter PermissionPreFilter validate error!");
        }
        return null;
    }
}
