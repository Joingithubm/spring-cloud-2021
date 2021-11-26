package com.athome.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/11/16 10:35
 * @Version 1.0
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 8; i++) {
            new Worker(i,semaphore).start();
        }

    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num, Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+num +"获取到资源");
                TimeUnit.SECONDS.sleep(4);
                semaphore.release();
                System.out.println("工人"+num +"释放资源");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
