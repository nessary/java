package com.java.io.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Ness on 2017/6/27.
 */
public class ChartClient {

    public static void main(String[] args) throws IOException {


        getConnCLientByNo();
    }


    public static void client() throws IOException {
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8048));

        //2. 切换非阻塞模式
        sChannel.configureBlocking(false);

        //3. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put(("hello server").getBytes());
        buf.flip();
        sChannel.write(buf);
        //5. 关闭通道
        sChannel.close();
    }

    public static void getConnCLientByNo() throws IOException {

        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8048));

        channel.configureBlocking(false);


        Selector selector = Selector.open();


        channel.register(selector, SelectionKey.OP_WRITE);


        while (selector.select() > 0) {

            SelectionKey key;

            for (Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {

                key = iterator.next();

                if (key.isReadable()) {

                    ByteBuffer buffer = ByteBuffer.allocate(1024);


                    SocketChannel chan = (SocketChannel) key.channel();


                    int len = 0;


                    while ((len = chan.read(buffer)) > 0) {

                        buffer.flip();

                        System.out.println(new String(buffer.array(), 0, len));

                        buffer.clear();


                    }


                    chan.register(selector, SelectionKey.OP_WRITE);


                } else if (key.isWritable()) {

                    SocketChannel chan = (SocketChannel) key.channel();
                    chan.configureBlocking(false);


                    Scanner scanner = new Scanner(System.in);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    while (scanner.hasNext()) {

                        buffer.put(scanner.next().getBytes());
                        buffer.flip();
                        chan.write(buffer);
                        buffer.clear();
                        chan.register(selector, SelectionKey.OP_READ);
                    }


                }

                iterator.remove();
            }


        }


    }


    public static void getConnClient() throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8048));

        ByteBuffer buffer = ByteBuffer.allocate(1024);


        buffer.put("hello server ".getBytes());
        buffer.flip();
        channel.write(buffer);

        channel.shutdownOutput();


        int len = 0;

        while ((len = channel.read(buffer)) != -1) {

            buffer.flip();
            System.out.println(new String(buffer.array(), 0, len));
            buffer.clear();

        }

        channel.shutdownInput();


    }

}
