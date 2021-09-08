package com.athome.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zengfc
 * @Desc
 * @data 2021/9/8 22:27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystrixMain8005 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8005.class,args);
    }
}
