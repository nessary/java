package com.java.lock.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ness on 2017/7/5.
 */
public class Test {

    public static void main(String[] args) {
        int passFactor = 1;

        int size = 5;
        Chopstick[] chopsticks = new Chopstick[size];


        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();

        }


        ExecutorService executorService =

                Executors.newCachedThreadPool();


        for (int i = 0; i < size; i++) {
//            executorService.execute(new Philosopers(chopsticks[i], chopsticks[i % size], i, passFactor));
            if(i<(size-1)){
                executorService.execute(new Philosopers(chopsticks[i],chopsticks[(i+1) % size],i,passFactor));
            }
            else{
                executorService.execute(new Philosopers(chopsticks[(i+1) % size],chopsticks[i],i,passFactor));
            }

//            if(i<(size-1))
//                exec.execute(new Philosopher(sticks[i],sticks[i+1], i, ponder));
//            else
//                exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));

        }

        executorService.shutdown();



    }

//    public static void main(String[] args) throws Exception{
//        int ponder=2;
//        if(args.length>0)
//            ponder=Integer.parseInt(args[0]);
//
//        int size=5;
//        if(args.length>1)
//            size=Integer.parseInt(args[1]);
//        ExecutorService exec=Executors.newCachedThreadPool();
//        Chopstick[] sticks=new Chopstick[size];
//        for (int i = 0; i < size; i++) {
//            sticks[i]=new Chopstick();
//        }
//        for (int i = 0; i < size; i++) {
//            if(i<(size-1))
//                exec.execute(new Philosopers(sticks[i],sticks[i+1], i, ponder));
//            else
//                exec.execute(new Philosopers(sticks[0], sticks[i], i, ponder));
//        }
//
//        exec.shutdown();
//    }

}
