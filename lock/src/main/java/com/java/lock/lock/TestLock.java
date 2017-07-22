package com.java.lock.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ness on 2017/7/16.
 */
public class TestLock {
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
    public static void main(String[] args) {

        System.out.println(EXCLUSIVE_MASK & 6666666666666l);
//        ReadWriteLock lock = new ReentrantReadWriteLock().l;


//        ReentrantReadWriteLock.ReadLock

    }

}
