package com.athome.springcloud.controller;

import com.athome.springcloud.entities.CommonResult;
import com.athome.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/7 18:00
 * @Version 1.0
 */
@RestController
public class PaymentContoller {

    @Resource
    PaymentService paymentService;


}
