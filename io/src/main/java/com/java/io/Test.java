package com.java.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Ness on 2017/7/1.
 */
public class Test{



    public static void main(String[] args) throws InterruptedException {


//        ByteBufAllocator
//                CompositeByteBuf
//                DefaultFileRegion

//      使用内存池分配器创建直接内存缓冲区：

//        PooledByteBufAllocator

//        用非堆内存分配器创建的直接内存缓冲区
//        UnpooledByteBufAllocator;

//        UnpooledByteBufAllocator
//        DefaultFileRegion s


        NioEventLoopGroup group = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(2);
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group, work).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                //
            }
        });


        Channel channel =

                bootstrap.bind(8028).sync().channel();


        channel.alloc();



    }

}
