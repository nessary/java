package com.java.io.example;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Ness on 2017/6/24.
 */
public class Test03 {
    public static void main(String[] args) throws IOException {

        RandomAccessFile accessFile = new RandomAccessFile("E:\\JAVA\\java\\io\\test\\111.txt", "r");

        //-----

        accessFile.readByte();
        System.out.println(accessFile.length());



    }
}
