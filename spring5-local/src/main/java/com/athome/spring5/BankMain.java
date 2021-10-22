package com.athome.spring5;

import com.athome.spring5.service.PaymentService;
import com.athome.spring5.service.impl.PaymentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/22 16:24
 * @Version 1.0
 */
public class BankMain {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PaymentService bean = (PaymentService) context.getBean("paymentServiceImpl");
        bean.tranMoney("李四","张三",500);
    }
}
