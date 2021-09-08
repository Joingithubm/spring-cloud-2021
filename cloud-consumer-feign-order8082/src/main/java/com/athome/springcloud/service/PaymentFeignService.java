package com.athome.springcloud.service;

import com.athome.springcloud.entities.CommonResult;
import com.athome.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zengfc
 * @Desc
 * @data 2021/9/8 21:14
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE/payment")
public interface PaymentFeignService {

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/feign/timeout")
    public String timeout();
}
