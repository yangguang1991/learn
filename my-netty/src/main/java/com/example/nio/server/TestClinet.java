package com.example.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/8/15 11:28
 */
public class TestClinet {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8800));
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put(("hello from client"+System.currentTimeMillis()).getBytes());
            writeBuffer.flip();
            while (true) {
                socketChannel.write(writeBuffer);
                readBuffer.clear();
                socketChannel.read(readBuffer);
                System.out.println("readBuffer="+new String(readBuffer.array()));
                writeBuffer.flip();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
