package com.java.locks.base;

import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

public class Test07 {

    private final static int NUM_THREADS = 4; // change
    private final static long ITERATIONS = 100L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public Test07(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.nanoTime();
        runTest();

        System.out.println("duration = " + (System.nanoTime() - start));
    }
    private static   CountDownLatch count = new CountDownLatch( NUM_THREADS);



    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(String.valueOf(new Test07(i)));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }

    }

    public void run() {
//        long i = ITERATIONS + 1;
//        while (--i > 0) {
            longs[arrayIndex].value = 10;
//        }



    }

    @Contended
    public final static class VolatileLong {
        public volatile long value = 0L;
//        1232377
        //2520473
//        public long p1, p2, p3, p4, p5, p6;
//        public long p1, p2, p3, p4, p5;
    }
}

