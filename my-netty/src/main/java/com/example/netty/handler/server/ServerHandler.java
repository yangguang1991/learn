package com.example.netty.handler.server;

import com.example.netty.TestServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg + ",EchoHandler server=" + System.currentTimeMillis());
        final ByteBuf time = ctx.alloc().buffer(88); // (2)
        time.writeBytes("success".getBytes());
        System.out.println("ServerHandler  ctx=" + ctx);
        ctx.write(time);
        TestServer.contextMap.put("123", ctx);//有可能会过期
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught..........");
        cause.printStackTrace();
        ctx.close();
    }
}
