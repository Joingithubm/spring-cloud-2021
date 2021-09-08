package com.athome.springcloud.controller;

import com.athome.springcloud.service.PaymentHystrixService;
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
public class FeignHystrixController {

    @Resource
    PaymentHystrixService paymentHystrixService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/ok")
    public String ok() {
        return paymentHystrixService.ok();
    }

    @GetMapping("/hystrix/timeout")
    public String hystrixTimeout() {
        return paymentHystrixService.hystrixTimeout();
    }
}
