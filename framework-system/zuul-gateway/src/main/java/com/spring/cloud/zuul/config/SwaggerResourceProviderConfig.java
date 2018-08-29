package com.spring.cloud.zuul.config;

import com.spring.cloud.framework.constant.ServiceIdConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingying on 2018/8/20.
 */
public class SwaggerResourceProviderConfig implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;

    public SwaggerResourceProviderConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> {
            // swagger排除 auth 模块
            if (!StringUtils.contains(route.getId(), ServiceIdConstant.AUTH_SERVICE)) {
                resources.add(buildSwaggerResource(route.getId(), route.getFullPath().replace("**",
                        "v2/api-docs")));
            }
        });

        return resources;
    }

    private SwaggerResource buildSwaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
