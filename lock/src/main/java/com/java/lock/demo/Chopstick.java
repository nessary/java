package com.java.lock.demo;

/**
 * Created by Ness on 2017/7/5.
 */
public class Chopstick {

    /**
     * 是否使用
     */
    private boolean taken=false;


    public synchronized void take() throws InterruptedException {
        if (taken) {
            wait();
        }
        taken = true;


    }


    public synchronized void drop() {
        taken = false;
        notifyAll();

    }






}

