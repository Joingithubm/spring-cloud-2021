package com.athome.spring5;

import com.athome.spring5.service.PaymentService;
import com.athome.spring5.service.impl.PaymentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/22 16:24
 * @Version 1.0
 */
public class BankMain {

    public static void main(String[] args) throws InterruptedException {

        ///ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
       // PaymentService bean = (PaymentService) context.getBean("paymentServiceImpl");
        //bean.tranMoney("李四","张三",500);
         ArrayBlockingQueue<Map<String,String>> incidentQueue = new ArrayBlockingQueue<>(10000);
        for (int i = 0; i < 5; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put(String.valueOf(i),String.valueOf(i));
            incidentQueue.add(map);
        }
        System.out.println(incidentQueue.toString());

        Map<String, String> take = incidentQueue.take();
        System.out.println(take);


        Map<String,Object> map = new HashMap<>();
        map.put("张三","李四");
        map.put("李四","张三");

        System.out.println(map);
        map.remove("张三");
        System.out.println(map);
    }
}
