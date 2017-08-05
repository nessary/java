package com.java.locks.base;

/**
 * 常量池锁
 */
public class ConstLock01 {


    public static void main(String[] args) {
        ConstLock01 lock = new ConstLock01();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            int index = i;

            threads[i] =new Thread(() -> {
                lock.testConstat("abc", index);
            });
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }

        }



    }

    public void testConstat(String lock, int i) {

        synchronized (this) {
            System.out.println("i:=" + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {

            }
        }


    }

}
