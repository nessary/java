package com.java.locks.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Test02 extends Object {




    public  static void main(String[] args) {

        //happens-before(先天发生)
        //-------------------------------------------------------------
        //1.单核系统  所有的代码执行顺序都是按照 从上到下执行
        long a = 3;
        long b = 4;
        long c = 5;
        System.out.println(a + b + c);
        //-------------------------------------------------------------


        //-------------------------------------------------------------
        ReentrantLock lock = new ReentrantLock();

        Integer i = null;
        try {
            //before 条件
            i += 1;
            lock.lock();

            //do something

        } catch (Exception e) {



        } finally {

            if (lock.isLocked()) {
                lock.unlock();
            }


        }


        //lock 的
        //-------------------------------------------------------------

        //-------------------------------------------------------------

        //3 volatile 的写操作 发生在读操作之前
        //-------------------------------------------------------------


        //-------------------------------------------------------------


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        thread.start();
        thread.interrupt();
        thread.setPriority(Thread.MAX_PRIORITY);


        //4 线程 start 发生在 任何操作之前
        //-------------------------------------------------------------


        //-------------------------------------------------------------


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("222");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                }
            }
        });


        try {

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
            System.out.println("333");
        } catch (InterruptedException e) {

        }


        //4 线程  终止前所有操作全部结束
        //-------------------------------------------------------------


        //-------------------------------------------------------------


        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("44444");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //do something


                System.out.println("6666");


            }
        });

        thread3.start();

//        thread3.interrupt();//中断
        thread3.setDaemon(Boolean.TRUE);

        //4 线程  终止前所有操作全部结束
        //-------------------------------------------------------------

        ExecutorService service = Executors.newCachedThreadPool();
        service.shutdown();
        service.shutdownNow();


    }


}
