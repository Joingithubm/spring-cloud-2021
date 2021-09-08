package com.athome.springcloud.controller;

import com.athome.springcloud.lb.MyLoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Resource
    RestTemplate restTemplate;
    @Resource
    MyLoadBalance myLoadBalance;
    @Resource
    DiscoveryClient discoveryClient;

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @PostMapping("/consumer/payment/create")
    public com.athome.springcloud.entities.CommonResult create(@RequestBody com.athome.springcloud.entities.Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, com.athome.springcloud.entities.CommonResult.class);
    }
    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public com.athome.springcloud.entities.CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id, com.athome.springcloud.entities.CommonResult.class);
    }

    @GetMapping("/consumer/own/payment/getPaymentById/{id}")
    public com.athome.springcloud.entities.CommonResult getPaymentById1(@PathVariable("id") Long id){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = myLoadBalance.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri+"/payment/getPaymentById/"+id, com.athome.springcloud.entities.CommonResult.class);
    }

}
