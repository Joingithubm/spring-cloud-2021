package com.athome.juc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/11/22 15:12
 * @Version 1.0
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<String> list = Arrays.asList();
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            list.add("张三");
        });


        TimeUnit.SECONDS.sleep(2);
        System.out.println(list);
    }
}
