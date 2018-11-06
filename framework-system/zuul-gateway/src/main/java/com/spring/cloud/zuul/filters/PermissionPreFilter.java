package com.spring.cloud.zuul.filters;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yingying on 18-5-4.
 */
//@Component
//public class PermissionPreFilter extends ZuulFilter {
public class PermissionPreFilter {

    private final static String split_mark = ",";
    @Value("${zuulFilter.permissionFilter}")
    private boolean execPermissionFilter;


    @Value("${zuulFilter.rules.whiteurl}")
    private String whiteurl;

    Logger log = LoggerFactory.getLogger(PermissionPreFilter.class);

    //    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //    @Override
    public int filterOrder() {
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

    //    @Override
    public boolean shouldFilter() {
        return execPermissionFilter;
    }

    //    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime", System.currentTimeMillis());
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        log.info("Zuul Filter PermissionPreFilter uri:" + requestURI);

        if (hasHandleReq(requestURI)) {
            String token = request.getHeader("testToken");

            if (StringUtils.isNotBlank(token)) {
                ctx.addZuulRequestHeader("Authorization", token);
                //如果有说明权限通过,则继续转发路由
                ctx.setSendZuulResponse(true);
                ctx.setResponseStatusCode(200);
                ctx.set("token", token);
                log.info("Zuul Filter PermissionPreFilter validate success!");
            } else {
                //如果权限验证不成功,则不再转发路由
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(201);
                ctx.setResponseBody("{\"result\":\"token is not correct!\"}");
                log.info("Zuul Filter PermissionPreFilter validate error!");
            }
        } else {
            //如果有说明权限通过,则继续转发路由
            ctx.setSendZuulResponse(true);
        }


        return null;
    }

    private boolean hasHandleReq(String requestURI) {
        boolean ishandle = true;
        String[] whiteurls = whiteurl.split(split_mark);
        for (int i = 0; i < whiteurls.length; i++) {
            if (requestURI.contains(whiteurls[i])) {
                ishandle = false;
                break;
            }
        }
        return ishandle;
    }
}
