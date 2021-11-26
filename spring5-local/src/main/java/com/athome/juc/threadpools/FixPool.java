package com.athome.juc.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 固定数量的线程池
 * @Author Zengfc
 * @Date 2021/11/1 15:33
 * @Version 1.0
 */
public class FixPool {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            int cnt = i;
            executor.execute(()->{
                System.out.println(cnt);
            });
        }
    }
}
