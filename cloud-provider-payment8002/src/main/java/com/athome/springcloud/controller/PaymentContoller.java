package com.athome.springcloud.controller;

import com.athome.springcloud.entities.CommonResult;
import com.athome.springcloud.entities.Payment;
import com.athome.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/7 18:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/payment")
public class PaymentContoller {

    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

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

    @GetMapping("/feign/timeout")
    public String timeout() {
       try {
           TimeUnit.SECONDS.sleep(3);
       }catch (Exception exception){

       }

        return serverPort;
    }

}
