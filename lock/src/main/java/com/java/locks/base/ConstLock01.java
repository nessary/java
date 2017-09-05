package com.java.locks.base;

import java.util.concurrent.locks.LockSupport;

/**
 * 常量池锁
 */
public class ConstLock01 {


    public static void main(String[] args) {


        ConstLock lock = new ConstLock();
        int len = 10;
        Thread[] threads = new Thread[len];

        for (int i = 0; i < len; i++) {
            int index = i;

            threads[i] = new Thread(() -> {
                lock.testConstat("abc", index);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                LockSupport.parkNanos(200);

            });
        }
//        threads[len-1].setPriority(Thread.MAX_PRIORITY);


        for (int i = 0; i < 10; i++) {

            threads[i].start();
//            threads[i].interrupt();
//            try {
//
//                //最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
//                Thread.sleep(1);
//
//
                LockSupport.parkNanos(1);
////                this.wait();
////                this.notify();
//
//            } catch (InterruptedException e) {
//
//            }

        }


    }


}
