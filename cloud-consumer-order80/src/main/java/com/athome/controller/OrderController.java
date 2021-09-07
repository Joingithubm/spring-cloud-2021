package com.athome.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    RestTemplate restTemplate;

    public static final String PAYMENT_URL = "http://localhost:8001";

    @PostMapping("/consumer/payment/create")
    public com.athome.springcloud.entities.CommonResult create(@RequestBody com.athome.springcloud.entities.Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, com.athome.springcloud.entities.CommonResult.class);
    }
    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public com.athome.springcloud.entities.CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id, com.athome.springcloud.entities.CommonResult.class);
    }

}
