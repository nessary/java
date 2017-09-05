package com.java.locks.second;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 单例模式
 * <p>
 * 1 构造函数私有化
 * 2 类的加载只能一次
 */
public class Test1 {


    /**
     * 饿汉式 线性安全 缺点就是  类初始化就加载（浪费资源）
     */
    protected static  class Single1 {

        private Single1() {

        }


        private static  Single1 single = new Single1();

        public static Single1 instance() {
            return single;
        }
    }

    /**
     * 懒汉式 线性不安全
     */
    protected static class Single2 {

        private Single2() {

        }

        private static Single2 single = null;

        public static Single2 instance() {
            if (single == null) {
                single = new Single2();
            }
            return single;
        }
    }


    /**
     * 双重double-check
     */
    protected static class Single3 {
        private Single3() {

        }

        private static volatile   Single3 single = null;

        public static Single3 instance() {

            if (single == null) {
                //锁的对象是类锁 是利用类只加载一次
                synchronized (Single3.class) {
                    //再次校验原因 是防止 已经赋值的线程进来后 重新赋值
                    if (single == null) {
                        single = new Single3();
                        //jdk  --预留
                    }

                    //single ==null
                    //single 初始化
                    //single 赋值

                    //single ==null
                    //single 赋值
                    //single 初始化0


                }

            }
            return single;
        }
    }


    /**
     * 静态内部类
     */
    protected static class Single4 {
        private Single4() {

        }

        /**
         * 利用的原理是 类只加载一次  还有就是 这个内部类只有被外表使用到了才进行加载
         */
        private static class InnerSingle {

            private static Single4 instance = new Single4();


        }

        public static Single4 instance() {
            return InnerSingle.instance;
        }
    }


    /**
     * 枚举
     */
    private static class Single5 {
        private Single5() {

        }
    }

    private static enum Enums {
        INSTANCE;
        private static Single5 instance = null;

        public static Single5 getStance() {
            if (instance == null) {
                instance = new Single5();
            }
            return instance;
        }


    }

//
//    /**
//     * 双重校验的遗留问题
//     */
    protected static class Single6 {
        private Single6() {

        }

        private static Single6 single = null;

        public static Single6 instance() {

            if (single == null) {

                //锁的对象是类锁 是利用类只加载一次
                synchronized (Single6.class) {

                    Single6 _temp = single;
                    if (_temp == null) {
                        _temp = new Single6();
                    }

                    single = _temp;


                }

            }
            return single;
        }
    }


    public static void main(String[] args) throws InterruptedException {

        //饿汉式
      /*  for (int i = 0; i < 8; i++) {
            new Thread(() -> System.out.println(Single1.instance())).start();
        }
*/
        //懒汉式
        Set<Single3> treeSet = new HashSet<>();
        int len = 100;
        CountDownLatch latch = new CountDownLatch(len);
        for (int i = 0; i < len; i++) {

            int index = i;
            new Thread(() -> {
                treeSet.add(Single3.instance());
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("set的大小" + treeSet);


       /* *//**
         * 静态内部类
         *//*
        for (int i = 0; i < 20; i++) {
            new Thread(() -> System.out.println(Single4.instance())).start();
        }*/

        /**
         * 枚举
         */
       /* for (int i = 0; i < 20; i++) {
            new Thread(() -> System.out.println(Enums.getStance())).start();
        }*/

        /**
         * 双重检验
         */
//        for (int i = 0; i < 60; i++) {
//            new Thread(() -> System.out.println(Single6.instance())).start();
//        }

    }


}
