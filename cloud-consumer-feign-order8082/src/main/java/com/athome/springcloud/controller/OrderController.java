package com.athome.springcloud.controller;

import com.athome.springcloud.entities.CommonResult;
import com.athome.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {


    @Resource
    PaymentFeignService paymentFeignService;

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @PostMapping("/consumer/payment/create")
    public CommonResult create(@RequestBody com.athome.springcloud.entities.Payment payment){

        return null;
    }
    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/own/payment/getPaymentById/{id}")
    public CommonResult getPaymentById1(@PathVariable("id") Long id){
        return null;
    }

    @GetMapping("/consumer/feign/timeout")
    public String timeout(){
        return paymentFeignService.timeout();
    }

}
