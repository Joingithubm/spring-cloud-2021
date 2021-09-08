package com.athome.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/8 15:02
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMainZk8004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMainZk8004.class,args);
    }
}
