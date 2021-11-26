package com.athome.juc.threadpools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:  单线程的线程池
 * @Author Zengfc
 * @Date 2021/11/1 15:54
 * @Version 1.0
 */
public class SinglePool {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        List<Object> objects = Collections.synchronizedList(new ArrayList<>());
        objects.add(1);

        for (int i = 0; i < 10; i++) {
            executor.execute(()-> System.out.println(Thread.currentThread().getName()+" 时间："+new Date()));
        }
    }
}
