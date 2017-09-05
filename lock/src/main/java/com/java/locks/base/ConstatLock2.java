package com.java.locks.base;

import java.util.concurrent.locks.LockSupport;

public class ConstatLock2 {
    public static void main(String[] args) {

        ConstLock lock = new ConstLock();
        int len = 5;
        Thread[] threads = new Thread[len];

        for (int i = 0; i < len; i++) {
            int index = i;

            threads[i] = new Thread(() -> {
                lock.testConstat("abc", index);

            });
        }


        threads[0].setPriority(Thread.MIN_PRIORITY);
        threads[2].setPriority(Thread.MAX_PRIORITY);


        for (int i = 0; i < len; i++) {

            threads[i].start();



        }


    }

}
