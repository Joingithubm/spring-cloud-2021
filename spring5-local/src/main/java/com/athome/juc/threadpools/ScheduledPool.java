package com.athome.juc.threadpools;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  定时执行的线程
 * @Author Zengfc
 * @Date 2021/11/1 15:36
 * @Version 1.0
 */
public class ScheduledPool {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        for (int i = 0; i <10; i++) {
            int cnt = i;
            executor.schedule(()->{
                System.out.println(Thread.currentThread().getName()+"时间："+new Date()+" "+cnt);
            },5,TimeUnit.SECONDS);
        }

        executor.schedule(()-> System.out.println(Thread.currentThread().getName()+" 时间："+new Date()+"4")
        ,4,TimeUnit.SECONDS);

    }
}
