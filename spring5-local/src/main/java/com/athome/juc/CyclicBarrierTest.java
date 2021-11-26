package com.athome.juc;

import jdk.internal.org.objectweb.asm.tree.FrameNode;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/11/16 10:03
 * @Version 1.0
 */
public class CyclicBarrierTest {

    static Random random =  new Random(300);


    public static void main(String[] args) {

        int N= 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);

        for (int i = 0; i < N; i++) {
            new Writer(cyclicBarrier).start();

        }


        System.out.println("回到主线程乐");

    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run(){
            try{
                System.out.println(Thread.currentThread().getName()+"准备写入数据");
                Thread.sleep(random.nextInt());
                System.out.println("线程"+Thread.currentThread().getName()+"写入完毕"+new Date());
                cyclicBarrier.await();
            }catch (Exception exception){
                System.out.println(exception);
            }

            System.out.println(Thread.currentThread().getName()+"所有线程写入完毕"+new Date());
        }
    }

}
