package com.java.locks.base;

import java.util.concurrent.CopyOnWriteArrayList;

public class Test06 {
    public static void main(String[] args) {

        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        list.add("1");

        new Thread(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

            System.out.println("result:  " + list.get(0));

        }).start();

        new Thread(() -> {


            list.set(0, "t");
            System.out.println("执行了");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            }

        }).start();




    }
}
