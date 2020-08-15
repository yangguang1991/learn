package com.example.netty;

import com.example.netty.constant.Constant;
import com.example.netty.decoder.TointDecoder;
import com.example.netty.handler.server.ServerHandler;
import com.example.netty.send.TestSend;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TestServer {

    //记录客户端的信息和ctx
    public static Map<String, ChannelHandlerContext> contextMap = new ConcurrentHashMap();

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列拿到的线程数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持获得的连接状态
                    .localAddress(Constant.SERVER_IP, Constant.SERVER_PORT)
                    .childHandler(new ChannelInitializer<SocketChannel>() {//给每个NIO  设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            //netty提供的一个用于心跳检测的handler
                            //long readerIdleTime,  多长时间没有read 那么就发送心跳检测
                            //long writerIdleTime,  多长时间没有write 那么就发送心跳检测
                            //long allIdleTime,     多次实践既没有read也没有write  那么就发送心跳检测
                            //
                            p.addLast("idleStateHandler",
                                    new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                            p.addLast(new TointDecoder());
                            p.addLast(new ServerHandler());
                            //p.addLast(new ProtobufVarint32FrameDecoder());
                        }
                    });

            // start
            ChannelFuture future = boot.bind(Constant.SERVER_PORT).sync();
            //获取channel 然后发送数据
            Thread t1 = new Thread(new TestSend(TestServer.contextMap));
            t1.start();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
