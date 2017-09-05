package com.java.locks.second;

/**
 * 开始逃逸分析
 * <p>
 * -server  (这是表示启动jvm是在服务端 -client)
 * -XX:+PrintGC (打印GC日志)             ====>>> -verbose:gc
 * -XX:+PrintGCDetails(打印GC日志详情)    ====>>> -verbose:gc
 * -XX:+PrintGCTimeStamps(打印GC日志时间戳)
 * -XX:+DoEscapeAnalysis(开启逃逸分析)
 */
public class Test3 {

    private int A;


    public static void main(String[] args) {


        long start = System.nanoTime();

        for (int i = 0; i < 10000000L; i++) {
            Test3 test3 = new Test3();
            test3.A = 4;
        }

        System.out.println(System.nanoTime() - start);

    }


    /**
     * 开启逃逸分析后 时间缩短了
     *  开始后 没用堆内存回收
     */

}
