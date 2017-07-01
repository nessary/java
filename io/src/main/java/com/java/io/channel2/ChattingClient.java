package com.java.io.channel2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 聊天客户端
 * Created by Ness on 2017/7/1.
 */
public class ChattingClient {


    public static void main(String[] args) throws IOException {
        conClientByNoneBlocking();
    }


    /**
     * 客户端的链接服务端
     */
    public static void conClient() throws IOException {

        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8048));


        ByteBuffer buffer = ByteBuffer.allocate(1024);


        buffer.put("12465".getBytes());

        //切换写的模式 Io需要写进流中
        buffer.flip();

        channel.write(buffer);

        //强制性发送写流
        channel.shutdownOutput();


        buffer.clear();


        int len = 0;

        while ((len = channel.read(buffer)) > 0) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, buffer.limit()));
            buffer.clear();
        }


        //强制性关闭输入流
        channel.shutdownInput();

        channel.close();


    }

    /**
     * 非阻塞的客户端
     * @throws IOException
     */

    public static void conClientByNoneBlocking() throws IOException {

        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8048));

        channel
                .configureBlocking(false);


        Selector selector = Selector.open();


        channel.register(selector, SelectionKey.OP_WRITE);


        int len = 0;


        while ((len = selector.select()) > 0) {

            SelectionKey selectionKey;

            for (Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {
                selectionKey = iterator.next();

                if (selectionKey == null) {
                    continue;

                }


                if (selectionKey.isWritable()) {

                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    buffer.put("hello server".getBytes());

                    buffer.flip();
                    SocketChannel socketChannel =
                            (SocketChannel) selectionKey.channel();

                    socketChannel.configureBlocking(false);

                    socketChannel.write(buffer);


                    socketChannel.register(selector, SelectionKey.OP_READ);



                } else if (selectionKey.isReadable()) {

                    SocketChannel socketChannel =
                            (SocketChannel) selectionKey.channel();

                    socketChannel.configureBlocking(false);


                    ByteBuffer buffer = ByteBuffer.allocate(1024);


                    int lens = 0;


                    while ((lens = socketChannel.read(buffer)) > 0) {

                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, buffer.limit()));
                        buffer.clear();


                    }



//                    channel.close();
                    socketChannel.register(selector, SelectionKey.OP_CONNECT);


                } else if (selectionKey.isConnectable()) {


//                    selectionKey.channel().close();

                    channel.close();

                }

                iterator.remove();

            }


        }


    }


}
