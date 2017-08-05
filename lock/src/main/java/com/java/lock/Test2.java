package com.java.lock;

/**
 * Created by Ness on 2017/7/4.
 */
public class Test2 {
    //资源类
    static class Service {
        public void print(String stringParam) {
            try {
                synchronized (stringParam) {
                    while (true) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //线程A
    static class ThreadA extends Thread {
        private Service service;

        public ThreadA(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.print("AA");
        }
    }

    //线程B
    static class ThreadB extends Thread {
        private Service service;

        public ThreadB(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.print("AA");
        }
    }


    public static void main(String[] args) {

        //临界资源
        Service service = new Service();

        //创建并启动线程A
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        //创建并启动线程B
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();


    }
}
