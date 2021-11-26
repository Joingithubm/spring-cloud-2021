package com.athome.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: AA 打印1 5次 -> BB 打印2 10 -> CC 打印3 15次
 * @Author Zengfc
 * @Date 2021/10/26 14:45
 * @Version 1.0
 */
public class ThreadSingalCustomer {

    public static void main(String[] args) {

        ShareCustemer custemer = new ShareCustemer();

        for (int i = 0; i < 3; i++) {

            new Thread(()->{
                custemer.incr();
            },"AAA").start();
            new Thread(()->{
                custemer.incr();
            },"BBB").start();
            new Thread(()->{
                custemer.incr();
            },"CCC").start();
        }


    }
}
class ShareCustemer{

    private int status = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr(){
        lock.lock();
        try{
            while (status != 0){
                condition.await();
            }
            status ++;
            System.out.println(Thread.currentThread().getName()+" :: "+status);
            condition.signalAll();

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void reset(){
        lock.lock();
        try{
            while (status != 4){
                condition.await();
            }
            status = 0;
            System.out.println(Thread.currentThread().getName()+" :: "+status);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}