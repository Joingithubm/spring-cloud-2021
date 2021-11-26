package com.athome.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:    两个线程轮询打印 1、0
 * @Author Zengfc
 * @Date 2021/10/26 14:08
 * @Version 1.0
 */
public class ThreadSingalSys {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Share share = new Share();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                share.incr();
            },"AA").start();

            new Thread(()->{
                share.decr();
            },"BB").start();

        }

    }
}

/**
 *  共享资源类
 *  三步走：
 *   1. 先判断
 *   2. 然后干活
 *   3. 最后通知
 */
class Share{

    private int status = 0;

    /**
     * 打印 1
     * @throws InterruptedException
     */
    public synchronized void incr() {
        // 1. 判断
        if(status != 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 2. 干活
        status++;
        System.out.println(Thread.currentThread().getName()+" :: "+status);

        // 3. 通知
        this.notifyAll();
    }

    /**
     *  打印 0
     */
    public synchronized void decr() {

        // 1. 先判断
        if (status != 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. 干活
        status--;
        System.out.println(Thread.currentThread().getName()+" :: "+status);

        // 3.通知
        this.notifyAll();
    }


}

