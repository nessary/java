package com.java.locks.base;

public class Test09 {
    public static void main(String[] args) {
        ConstLock lock = new ConstLock();
        for (int i = 0; i < 10; i++) {
            int index = i;
            new Thread(() -> lock.testConstat("abc", index)).start();
        }


    }
}
