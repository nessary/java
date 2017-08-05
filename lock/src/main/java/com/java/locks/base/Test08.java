package com.java.locks.base;

import java.util.concurrent.CountDownLatch;

/**
 * 伪代码共享的问题
 */
public class Test08 {


    public static void main(String[] args) throws InterruptedException {


//        long start = System.currentTimeMillis();
        long start = System.nanoTime();
        ;

        testShare();


        System.out.println(System.nanoTime() - start);

    }


    private static void testShare() throws InterruptedException {
        int len = 10;

        //一个含有特定大小的数组
        Sub[] longs = new Sub[len];

        // 状态减少标识
        CountDownLatch count = new CountDownLatch(len);

        for (int i = 0; i < len; i++) {

            int index = i;

            new Thread(() -> {
                //new出对象
                Sub sub = new Sub();
                //这里是取出对象 进行操作  我这里只是简单的 做了一个赋值操作
                sub.A = index;
                longs[index] = sub;

                count.countDown();

            }).start();

        }

        //等所有线程 执行完后 执行下面的操作
        count.await();

    }

    //    @Contended//方法二 使用注解 用来填充缓冲行
    private static class Sub {


        //803640538  //未填充 缓存行 可能存在多个数据
        //404105045  //填充缓存行

        private long A = 12;
        //方法一 字节计算 填充剩余缓冲行
//        private long a, b, c, d, e;


    }

}
