package com.example.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/8/15 9:53
 */
public class TestServer {
    public String port = "6678";

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8800));//绑定端口和地址
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//服务器注册进selector中
            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(1024);
            writeBuff.put("received".getBytes());
            writeBuff.flip();

            while (true) {
                int nReady = selector.select();//这是一个阻塞方法，只能有至少一个客户端的channel连接的时候才会返回，
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();//
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                System.out.println("============="+selectionKeySet.size());
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();//selectedKeys    因为selector不会主动删除，所有需要手动删除
                    System.out.println("selectionKey=" + selectionKey);
                    if (selectionKey.isAcceptable()) { //接收连接的时候
                        System.out.println("接收连接的时候");
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);//将这个通道注册进selector
                        System.out.println(socketChannel.getLocalAddress());
                        selectionKey.attach("object " + System.currentTimeMillis());
                    }
                    if (selectionKey.isReadable()) { // 可读的时候
                        System.out.println("可读的时候");
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        readBuff.flip();
                        System.out.println("received : " + new String(readBuff.array()));
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                    }
                    if (selectionKey.isWritable()) { // 可写的时候
                        System.out.println("可写的时候");
                        writeBuff.clear();
                        writeBuff.put((System.currentTimeMillis() + "").getBytes());
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        socketChannel.write(writeBuff);
                        Object lastAttach = selectionKey.attachment();

                        if (null != lastAttach) {
                            System.out.println("321321=" + new String(lastAttach.toString()));
                        }
                        long t=System.currentTimeMillis();
                        System.out.println("t="+t);
                        selectionKey.attach(t);
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
