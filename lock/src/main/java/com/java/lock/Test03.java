package com.java.lock;

/**
 * Created by Ness on 2017/7/4.
 */
public class Test03 {


    public static void main(String[] args) {
        VolatileFeaturesExample s = new VolatileFeaturesExample();
    }

    static class VolatileFeaturesExample {
        volatile long vl = 0L;  //使用volatile声明64位的long型变量

        public void set(long l) {
            vl = l;   //单个volatile变量的写
        }

        public void getAndIncrement() {
            vl++;    //复合（多个）volatile变量的读/写
        }

        public long get() {
            return vl;   //单个volatile变量的读
        }
    }

    //    假设有多个线程分别调用上面程序的三个方法，这个程序在语意上和下面程序等价：
    class VolatileFeaturesExample2 {
        long vl = 0L;               // 64位的long型普通变量

        public synchronized void set(long l) {     //对单个的普通 变量的写用同一个监视器同步
            vl = l;
        }

        public void getAndIncrement() { //普通方法调用
            long temp = get();           //调用已同步的读方法
            temp += 1L;                  //普通写操作
            set(temp);                   //调用已同步的写方法
        }

        public synchronized long get() {
            //对单个的普通变量的读用同一个监视器同步
            return vl;
        }

    }


}