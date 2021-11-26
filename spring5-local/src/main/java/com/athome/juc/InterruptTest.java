package com.athome.juc;

import javax.swing.table.TableRowSorter;

/**
 * @Description:   所有线程相互等待到达同一位置，然后一次执行
 * @Author Zengfc
 * @Date 2021/11/16 10:18
 * @Version 1.0
 */
public class InterruptTest {

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                if (i == 5){
                    Thread.currentThread().interrupt();
                }
                if (Thread.currentThread().isInterrupted()){
                    return;
                }
                System.out.println(i);
                System.out.println(Thread.currentThread().isInterrupted());
            }
        }).start();
    }
}
