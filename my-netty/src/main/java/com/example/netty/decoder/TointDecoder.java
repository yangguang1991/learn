package com.example.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.List;

public class TointDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        ByteBuf  byteBuf=(ByteBuf)in  ;
        System.out.println("TointDecoder readableBytes=" + in.readableBytes());
        byte[] dst = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(dst);
        System.out.println("client read =" + new String(dst));
        out.add(new String(dst));
    }

    //需要重写该方法    来对心跳的事件进行处理
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("READER_IDLE");
                    break;
                case WRITER_IDLE:
                    System.out.println("WRITER_IDLE");
                    break;
                case ALL_IDLE:
                    System.out.println("ALL_IDLE");
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        }
    }
}
