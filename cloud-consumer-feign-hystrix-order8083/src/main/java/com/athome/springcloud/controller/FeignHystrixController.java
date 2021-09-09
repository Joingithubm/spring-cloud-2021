package com.athome.springcloud.controller;

import com.athome.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zengfc
 * @Desc
 * @data 2021/9/8 23:05
 */
@RestController
@RequestMapping("/consumer")
//@DefaultProperties(defaultFallback = "hystrixDefualtHandler")
public class FeignHystrixController {

    @Resource
    PaymentHystrixService paymentHystrixService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/ok")
//    @HystrixCommand
    public String ok() {
        return paymentHystrixService.ok();
    }

    @GetMapping("/hystrix/timeout")
//    @HystrixCommand(fallbackMethod = "hystrixTimeouthandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    public String hystrixTimeout() {
        return paymentHystrixService.hystrixTimeout();
    }
    public String hystrixTimeouthandler() {
        return "feign调用接口异常，请重试";
    }
    public String hystrixDefualtHandler() {
        return "全局fallback~~~";
    }
}
