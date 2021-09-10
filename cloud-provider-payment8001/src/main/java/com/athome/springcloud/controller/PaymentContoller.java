package com.athome.springcloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.athome.springcloud.entities.CommonResult;
import com.athome.springcloud.entities.Payment;
import com.athome.springcloud.service.PaymentService;
import io.micrometer.core.instrument.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/7 18:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentContoller {

    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);

        if (i > 0 ){
            return new CommonResult(200,"插入数据库成功:"+serverPort,payment);
        }else {
            return new CommonResult(400,"插入数据库失败:"+serverPort);
        }
    }
    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        return new CommonResult<Payment>(200,"查询结果:"+serverPort,paymentById);
    }

    @GetMapping("/discoveryClient")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        log.info("----------service----------");
        for (String service : services) {
            log.info("**********{}*********",service);
        }
        log.info("---------instance-----------");
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                log.info("instance: host:{} >> port:{} >> uri:{}",instance.getHost(),instance.getPort(),instance.getUri());
            }

        }

        return discoveryClient;
    }

    @GetMapping("/feign/timeout")
    public String timeout() {
        return serverPort;
    }
}
