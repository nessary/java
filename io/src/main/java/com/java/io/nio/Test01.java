package com.java.io.nio;

import java.io.*;

/**
 * Created by Ness on 2017/6/25.
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        //BIO //blocking IO---- NIO (new IO /none Blocking IO )-----AIO(NIO2)
        //BIO---Java 堆之上的
        //NIO----java堆 --HeadBuffered
        //AIO---映射文件


        File file = new File("");

        try (BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(new File(""))))) {

        } catch (IOException e) {

        }


        file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        });

        //同步和异步  阻塞和非阻塞

        //ajax---

        //IO---


    }
}
