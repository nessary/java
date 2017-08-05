package com.java.lock;

public class Test1 implements Runnable {

//    // 可重入锁测试
//    public synchronized void get() {
//        System.out.println(Thread.currentThread().getName());
//        set();
//    }
//
//    public synchronized void set() {
//        System.out.println(Thread.currentThread().getName());
//    }
//
//    @Override
//    public void run() {
//        get();
//    }
//
//    public static void main(String[] args) {
//        Test1 test = new Test1();
//        new Thread(test,"Thread-0").start();
//        new Thread(test,"Thread-1").start();
//        new Thread(test,"Thread-2").start();
//    }


    // 可重入锁测试
    public void get() {
        synchronized (this) {
            System.out.println("get:"+Thread.currentThread().getName());
            set();
        }

    }

    public void set() {
        synchronized (this) {

            System.out.println("set:"+Thread.currentThread().getName());
        }

    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test1 test = new Test1();
        new Thread(test, "Thread-0").start();
        new Thread(test, "Thread-1").start();
        new Thread(test, "Thread-2").start();
    }

}