package com.java.io;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Ness on 2017/7/1.
 */
public class Test2 extends /*SimpleChannelInboundHandle*/  MessageToByteEncoder {
    /*@Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.read();
    }*/

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

    }
}
