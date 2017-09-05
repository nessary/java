package com.java.locks.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test10 {
    public static void main(String[] args) throws InterruptedException {
        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = inheritableThreadLocal.get();
        System.out.println(o);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                System.out.println("========");
//                System.out.println(inheritableThreadLocal.get());;
//                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
//                System.out.println(inheritableThreadLocal.get());;
                System.out.println("========");
                Span span=  inheritableThreadLocal.get();
                System.out.println(span);
                span.name="liuliuliu";//修改父引用为liuliuliu
                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                System.out.println(inheritableThreadLocal.get());
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("========");
        Span span = inheritableThreadLocal.get();
        System.out.println(span);
    }


static class Span {
    public String name;
    public int age;
    public Span(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Span{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
}
