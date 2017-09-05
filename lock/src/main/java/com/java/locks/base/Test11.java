package com.java.locks.base;

import java.io.IOException;

/**
 * synchronized锁延伸---字符串的常量池
 */
public class Test11 {

    //==比较的是地址  equals比较的是内容
    public static void main(String[] args) throws IOException {

        String abc1 = new String("ab") + new String("c");

        abc1 = abc1.intern();

        String abc = "abc";
        String abc2 = "abc";


        System.out.println(abc == abc1);
        System.out.println(abc == abc2);


        //使用的时候
        //很多场景 KTV 酒吧
        String bar = "酒吧".intern();//JDK1.8后效率提升 JDK1.6之前慎用


    }
}
