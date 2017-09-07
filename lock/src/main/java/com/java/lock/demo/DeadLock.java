package com.java.lock.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DeadLock  {


    private synchronized void sleep(){
        try {
            TimeUnit.MINUTES.sleep(new Random().nextLong() * 20);
        } catch (InterruptedException e) {

        }
    }

    synchronized void payA(){
        sleep();//模拟沉睡时间
        payB();
        System.out.println("payA end");

    }


    synchronized void payB()  {
        sleep();//模拟沉睡时间
        payA();
        System.out.println("pay end");

    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock lock = new DeadLock();
        new Thread(() -> lock.payA()).start();
        new Thread(() -> lock.payB()).start();

    }

}
