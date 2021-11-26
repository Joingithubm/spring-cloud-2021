package com.athome.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:  有返回值的线程
 * @Author Zengfc
 * @Date 2021/11/1 11:01
 * @Version 1.0
 */
public class CallableFuture {

    public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 2. 创建任务
        List<Future> list = new ArrayList<>();
        System.out.println("执行开始----");
        // 3. 执行任务
        for (int i = 0; i < 10; i++) {
            Callable callable = new MyCallabe(i);
            Future future = pool.submit(callable);
            list.add(future);
        }
        System.out.println("执行结束-----");
        // 4. 获取返回结果
        list.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("获取结果结束----");


    }

    static class MyCallabe implements Callable<String> {

        private int markId;
        public MyCallabe(int markId){
            this.markId = markId;
        }
        @Override
        public String call() throws Exception {
            return "当前值"+markId;
        }
    }
}
