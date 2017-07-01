package com.java.io.channel2;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 客户端链接服务端--->服务端
 * 先发一个消息给服务端
 * 服务端得到响应后 发一个消息回给---->客户端
 * <p>
 * 聊天服务端
 * Created by Ness on 2017/7/1.
 */
public class ChattingServer {


    public static void main(String[] args) throws IOException {
        getServerByNoneBlocking();

    }


    /**
     * 阻塞的服务
     */
    public static void startServerByBIO() throws IOException {


        //现获取一个channel
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        //内网---192.168.1.11  外网IP 120.140.146.120 默认是任意的
        socketChannel.bind(new InetSocketAddress(8048));


        System.out.println("I`m waitting ");
        //一直阻塞 等待客户端到来
        SocketChannel socketCh = socketChannel.accept();


        System.out.println("client have arrived ");


        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len = 0;


        //处理客户端请求数据
        while ((len = socketCh.read(buffer)) > 0) {

            //切换读的形式
            buffer.flip();

            System.out.println(new String(buffer.array(), 0, buffer.limit()));
            //清空 继续读
            buffer.clear();

        }

        //强制性断开读流
        socketCh.shutdownInput();


        //发消息给客户端

        buffer.put("hello client".getBytes());


        //切换写的模式
        buffer.flip();


        socketCh.write(buffer);


        //强制性发送写的流
        socketCh.shutdownOutput();


        //---

    }

    /**
     * 非阻塞的NIO服务
     */

    public static void getServerByNoneBlocking() throws IOException {

        //现获取一个channel
        ServerSocketChannel socketChannel = ServerSocketChannel.open();


        //设置成非阻塞
        socketChannel.configureBlocking(false);


        //内网---192.168.1.11  外网IP 120.140.146.120 默认是任意的
        socketChannel.bind(new InetSocketAddress(8048));


        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_ACCEPT);


        int len = 0;

        while ((len = selector.select()) > 0) {


            SelectionKey selectionKey;
            //删除
            for (Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {

                selectionKey = iterator.next();

                if (selectionKey == null) {
                    continue;

                }

                if (selectionKey.isAcceptable()) {

//                    SocketChannel channel = socketChannel.accept();

                    SocketChannel channel = ((ServerSocketChannel) selectionKey.channel()).accept();

                    channel.configureBlocking(false);

                    //进行读取数据
                    channel.register(selector, SelectionKey.OP_READ);


                } else if (selectionKey.isReadable()) {

                    SocketChannel channel = (SocketChannel) selectionKey.channel();


                    channel.configureBlocking(false);

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int lens = 0;


                    //处理客户端请求数据
                    while ((lens = channel.read(buffer)) > 0) {

                        //切换读的形式
                        buffer.flip();
                      //byte[] 和String
//                        "".getBytes();
//                        String string = new String(new byte[1], 0, 2);

                        System.out.println(new String(buffer.array(), 0, buffer.limit()));
                        //清空 继续读
                        buffer.clear();

                    }


                    //写的操作
                    channel.register(selector, SelectionKey.OP_WRITE);

                } else if (selectionKey.isWritable()) {


                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    //切换非阻塞
                    channel.configureBlocking(false);

                    ByteBuffer buffer = ByteBuffer.allocate(1024);


                    buffer.put("hello client2".getBytes());

                    //切换状态
                    buffer.flip();

                    channel.write(buffer);


                }


                iterator.remove();

            }


        }


    }


}
