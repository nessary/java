package com.java.locks.base;

public class Test03 {
    public static void main(String[] args) {

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


//        thread3.interrupt();//中断
        thread3.setDaemon(Boolean.TRUE);

        thread3.setPriority(Thread.MIN_PRIORITY);

        thread3.start();





        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1111");



            }
        });
        thread2.setPriority(Thread.MAX_PRIORITY);

        thread2.start();

        System.out.println("main  end");
    }
}
