package com.athome.spring5.service;

import com.athome.spring5.aspect.LogAspect;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.SpringVersion;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/19 14:47
 * @Version 1.0
 */
public class User {
    public void add(){
        System.out.println(this.getClass().getName());
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        UserDao userDao = (UserDao) context.getBean("userDao");
        System.out.println(SpringVersion.getVersion());
        System.out.println(userDao.add(1,2));
     //   System.out.println(userDao.update("234"));
    }
}
