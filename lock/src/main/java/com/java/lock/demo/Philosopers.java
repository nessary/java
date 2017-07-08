package com.java.lock.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ness on 2017/7/5.
 */
public class Philosopers implements Runnable {

    /**
     * 左边的筷子
     */
    private Chopstick left;


    /**
     * 右边的筷子
     */
    private Chopstick right;

    /**
     * 筷子的Id
     */
    private int id;


    /**
     * 等待的因子
     */
    private int pauseFactor;

    public Philosopers(Chopstick left, Chopstick right, int id, int pauseFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.pauseFactor = pauseFactor;
    }


    private Random random = new Random();

    private void pass() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(random.nextInt(pauseFactor * 100));
    }

    @Override
    public void run() {


        System.out.println("I`m  think");

        try {
            pass();

            left.take();
            System.out.println("获得左边的筷子");

            right.take();

            System.out.println("获得右边的筷子");


            pass();

            System.out.println("吃饭中");

            pass();


            left.drop();
            System.out.println("放下左边的筷子");


            right.drop();


            System.out.println("放下右边的筷子");


        } catch (InterruptedException e) {

        }


    }

    @Override
    public String toString() {
        return "ID为: " + id + "的哲学家";
    }

}
