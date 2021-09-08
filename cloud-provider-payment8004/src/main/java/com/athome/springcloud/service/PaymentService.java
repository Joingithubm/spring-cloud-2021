package com.athome.springcloud.service;

import com.athome.springcloud.entities.Payment;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/7 17:56
 * @Version 1.0
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById( Long id);
}
