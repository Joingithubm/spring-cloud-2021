package com.athome.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author zengfc
 * @Desc
 * @data 2021/9/9 23:13
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String ok() {
        return "-----PaymentFallbackService >>. ok";
    }

    @Override
    public String hystrixTimeout() {
        return "----PaymentFallbackService  >> hystrixTimeout";
    }
}
