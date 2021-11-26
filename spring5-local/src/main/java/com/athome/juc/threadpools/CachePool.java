package com.athome.juc.threadpools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:  可以自动创建线程的线程池
 * @Author Zengfc
 * @Date 2021/11/1 15:26
 * @Version 1.0
 */
public class CachePool {

    public static void main(String[] args) {

        ExecutorService  executor = Executors.newCachedThreadPool();
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int cnt = i;
            Future<Integer> submit = executor.submit(() -> cnt);
            list.add(submit);
        }

            list.forEach(u-> {
                try {
                    System.out.println(u.get());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });


/*        executor.submit(()->{
            System.out.println("结束了");
        });*/
    }
}
