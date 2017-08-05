package com.java.lock;

import java.util.List;
import java.util.Vector;

/**
 * Created by Ness on 2017/7/4.
 */
public class NonBiasedLockTest {
    public static List<Integer> numberList = new Vector<Integer>();

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
//    JVM Opts: -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
//JVM Opts: -XX:-UseBiasedLocking


//    偏向锁，顾名思义，它会偏向于第一个访问锁的线程，如果在接下来的运行过程中，该锁没有被其他的线程访问，则持有偏向锁的线程将永远不需要触发同步。
//    如果在运行过程中，遇到了其他线程抢占锁，则持有偏向锁的线程会被挂起，JVM会尝试消除它身上的偏向锁，将锁恢复到标准的轻量级锁。
//            (偏向锁只能在单线程下起作用)

//    机制：每个锁都关联一个请求计数器和一个占有他的线程，当请求计数器为0时，这个锁可以被认为是unhled的，
//    当一个线程请求一个unheld的锁时，JVM记录锁的拥有者，并把锁的请求计数加1，如果同一个线程再次请求这个锁时，请求计数器就会增加，
//    当该线程退出syncronized块时，计数器减1，当计数器为0时，锁被释放（这就保证了锁是可重入的，不会发生死锁的情况）。


}
