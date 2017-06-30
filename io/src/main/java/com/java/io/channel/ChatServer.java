package com.java.io.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Ness on 2017/6/27.
 */
public class ChatServer {


    public static void main(String[] args) throws IOException {
        getConnByNO();
    }


    public static void server() throws IOException{
        //1. 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2. 切换非阻塞模式
        ssChannel.configureBlocking(false);

        //3. 绑定连接
        ssChannel.bind(new InetSocketAddress(8048));

        //4. 获取选择器
        Selector selector = Selector.open();

        //5. 将通道注册到选择器上, 并且指定“监听接收事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        while(selector.select() > 0){

            //7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while(it.hasNext()){
                //8. 获取准备“就绪”的是事件
                SelectionKey sk = it.next();

                //9. 判断具体是什么事件准备就绪
                if(sk.isAcceptable()){
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();

                    //11. 切换非阻塞模式
                    sChannel.configureBlocking(false);

                    //12. 将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    //14. 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while((len = sChannel.read(buf)) > 0 ){
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }

                //15. 取消选择键 SelectionKey
                it.remove();
            }
        }
    }

    public static void getConnByNO() throws IOException {

        ServerSocketChannel channel = null;
        SocketChannel accept = null;
        try {
            channel = ServerSocketChannel.open();


            channel.configureBlocking(false);

            channel.bind(new InetSocketAddress( 8048));


            Selector selector = Selector.open();


            channel.register(selector, SelectionKey.OP_ACCEPT);


            while (selector.select() > 0) {

                SelectionKey key;

                for (Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {

                    key = iterator.next();

                    if (key.isAcceptable()) {

                        accept = channel.accept();

                        accept.configureBlocking(false);

                        accept.register(selector, SelectionKey.OP_READ);


                    } else if (key.isReadable()) {


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


                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        buffer.put("hello client".getBytes());

                        buffer.flip();
                        chan.write(buffer);


                        chan.register(selector, SelectionKey.OP_READ);


                    }


                    iterator.remove();

                }




            }


        } finally {

            if (channel != null) {
                channel.close();


            }


            if (accept != null) {
                accept.close();

            }


        }





}


    public static void getConnByBlock() throws IOException {
        ServerSocketChannel channel = null;
        SocketChannel accept = null;
        try {
            channel = ServerSocketChannel.open();


            channel.bind(new InetSocketAddress("localhost", 8048));


            accept = channel.accept();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


            int len = 0;

            while ((len = accept.read(byteBuffer)) != -1) {

                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, len));
                byteBuffer.clear();

            }

            accept.shutdownInput();


            byteBuffer.put("hello client ".getBytes());
            byteBuffer.flip();
            accept.write(byteBuffer);

            accept.shutdownOutput();


        } finally {

            if (channel != null) {
                channel.close();


            }


            if (accept != null) {
                accept.close();

            }


        }


    }

}
