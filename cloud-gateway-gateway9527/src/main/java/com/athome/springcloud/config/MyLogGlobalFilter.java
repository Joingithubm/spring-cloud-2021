package com.athome.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/15 17:17
 * @Version 1.0
 */
@Component
@Slf4j
public class MyLogGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (StringUtils.isEmpty(uname)){
            log.info("没有用户名非法请求！！！");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        log.info("权限校验成功！！！");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
