package com.athome.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/10 16:55
 * @Version 1.0
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("route_one",r->r.path("/guonei"). uri("http://news.baidu.com/"));

        return routes.build();
    }
}
