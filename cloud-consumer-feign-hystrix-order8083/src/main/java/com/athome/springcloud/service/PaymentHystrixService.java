package com.athome.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zengfc
 * @Desc
 * @data 2021/9/8 23:03
 */
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT/payment")
public interface PaymentHystrixService {

    @GetMapping("/hystrix/ok")
    public String ok();

    @GetMapping("/hystrix/timeout")
    public String hystrixTimeout();
}
