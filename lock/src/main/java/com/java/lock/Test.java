package com.java.lock;

import java.util.ArrayList;

/**
 * Created by Ness on 2017/7/3.
 */
public class Test {
    public static void main(String[] args)  {
        final InsertData insertData = new InsertData();
        // 启动线程 1
        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            };
        }.start();

        // 启动线程 2
        new Thread() {
            public void run() {
                insertData.insert1(Thread.currentThread());
            };
        }.start();
    }
}

// this 监视器
class InsertData {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public void insert(Thread thread){
        synchronized (object) {
            for(int i=0;i<100;i++){
                System.out.println(thread.getName()+"在插入数据"+i);
                arrayList.add(i);
            }
        }
    }
    private  Object object = new Object();

    public void insert1(Thread thread){
        synchronized (object) {
            for(int i=0;i<100;i++){
                System.out.println(thread.getName()+"在插入数据"+i);
                arrayList.add(i);
            }
        }
    }
}


