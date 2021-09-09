package com.athome.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.athome.springcloud.dao.PaymentDao;
import com.athome.springcloud.entities.Payment;
import com.athome.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/7 17:56
 * @Version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }
    @Override
    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }

    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+ "  paymentInfo_Ok, id :{}"+id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOuthandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        // int i = 10 /0;
/*        Integer time = 1;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "线程池："+Thread.currentThread().getName()+ "  paymentInfo_Ok, id :{}"+id;
    }

    public String paymentInfo_TimeOuthandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+ "  paymentInfo_Ok 出错啦, id :{}"+id;
    }


    @Override
    public String paymentInfo_Error(Integer id) {
        return null;
    }

    // =================服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")  // 成功率
    })
    @Override
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t" + "调用成功，流水号："+s;
    }

    public String paymentCircuitBreakerHandler(Integer id){
        return "id 不能为负数啦....";
    }
}
