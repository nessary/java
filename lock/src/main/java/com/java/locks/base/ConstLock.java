package com.java.locks.base;

/**
 * 常量池锁
 */
public class ConstLock {


//    private Integer locks=140;

    public static void main(String[] args) {
        ConstLock lock = new ConstLock();

        Thread[] threads = new Thread[10];


        for (int i = 0; i < 10; i++) {
            int index = i;

            threads[i] = new Thread(() -> {
                lock.testConstat("abc", index);
            });
        }


        for (int i = 0; i < 10; i++) {
            threads[i].start();
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//
//            }

        }


    }


    public void testConstat(String lock, int i) {
//        synchronized (ConstLock.class) {
        System.out.println("I`m :=" + i + " into".intern());

        synchronized (lock.intern()) {



//        synchronized (locks) {
            System.out.println("i:=" + i);


        }
        System.out.println("I`m :=" + i + " leave");

    }


}
