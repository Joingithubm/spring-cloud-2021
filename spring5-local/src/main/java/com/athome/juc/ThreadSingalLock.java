package com.athome.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 两个线程轮询打印 使用Lock
 * @Author Zengfc
 * @Date 2021/10/26 14:21
 * @Version 1.0
 */
public class ThreadSingalLock {

    public static void main(String[] args) {

        ShareLock share = new ShareLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                share.incr();
            }, "AA").start();

            new Thread(() -> {
                share.decr();
            }, "BB").start();
        }
    }
}

class ShareLock {

    private int status = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void incr() {
        lock.lock();
        try {
            while (status != 0){
                    condition.await();
            }

            status++;
            System.out.println(Thread.currentThread().getName() + " :: " + status);
            condition.signalAll();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decr() {
        lock.lock();
        try {
            while (status != 1) {
                condition.await();
            }
            status--;
            System.out.println(Thread.currentThread().getName() + " :: " + status);
            condition.signalAll();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}