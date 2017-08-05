package com.java.locks.base;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;

public class Test05 {

    public static void main(String[] args) {
        //关于fin 开头有三个
        final int i = 0;

        /*try-with-resource*/

        try {

        } finally {

        }


        //
//        Runtime.getRuntime().gc();
        System.gc();//




        CopyOnWriteArrayList s;//内部使用了双向 数组
//        Arrays.copyOf()
//System.arraycopy();
        int k = 0;
        k++;

        //k赋值 k+1 k的引用赋值

//        ArrayList




    }

    @Override
    protected void finalize() throws Throwable {

        super.finalize();
    }
}
