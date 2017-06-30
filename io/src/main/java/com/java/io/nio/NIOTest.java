package com.java.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by Ness on 2017/6/25.
 */
public class NIOTest {

    public static void main(String[] args) throws FileNotFoundException {
        String str = "1234";

        //赋值
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("init..................");
        System.out.println("mark:" + buffer.mark());
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("init..................");


        buffer.put(str.getBytes());

        System.out.println("put..................");
        System.out.println("mark:" + buffer.mark());
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("put..................");


        //切换读的模式
        buffer.flip();

//        byte[] cache = new byte[buffer.limit()];
        byte[] cache = new byte[2];


        buffer.get(cache);

        buffer.mark();

        System.out.println("read byte:.." + new String(cache, 0, cache.length));
        System.out.println("get..................");
//        System.out.println("mark:" + buffer.mark());
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("get..................");


//        buffer.put("3445".getBytes());
//
//        System.out.println("put..again................");
//
//        System.out.println("mark:" + buffer.mark());
//
//        System.out.println("position:" + buffer.position());
//
//        System.out.println("limit:" + buffer.limit());
//
//        System.out.println("capacity:" + buffer.capacity());
//
//        System.out.println("put..again...............");

        buffer.get(cache);

        System.out.println("read byte 2:.." + new String(cache, 0, cache.length));

        System.out.println("get...again..............");
//        System.out.println("mark:" + buffer.mark());
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("get....again...........");






        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        System.out.println(buffer.hasRemaining());


        buffer.reset();

        System.out.println("____________________________________");

        System.out.println(buffer.hasRemaining());


        buffer.get(cache);
        System.out.println("read byte 32:.." + new String(cache, 0, cache.length));


        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("read byte 32:.." + new String(cache, 0, cache.length));


//

        //NIO
        new FileInputStream(new File("")).getChannel();

        new RandomAccessFile("", "rw").getChannel();

        //AIO----
//        FileChannel fileChannel = FileChannel.open();



//        SocketChannel;---ServerSocketChannel
//
//
//        DatagramChannel;
//        buffer.clear()


    }

}
