package com.example.netty.send;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestSend implements Runnable {


    //记录客户端的信息和
    public Map<String, ChannelHandlerContext> contextMap = null;

    public TestSend(Map contextMap) {
        this.contextMap = contextMap;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                String key = "123";
                if (contextMap.containsKey(key)) {
                    ChannelHandlerContext ctx = contextMap.get(key);
                    final ByteBuf time = ctx.alloc().buffer(88); // (2)
                    time.writeBytes("success".getBytes());
                    ctx.write(time);
                    ctx.flush();
                    System.out.println("发了  ctx=" + ctx);
                    System.out.println("发了  ctx22=" + ctx.channel().remoteAddress().toString().substring(0,5));



                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
