package com.example.netty.test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class Test1 {

    public static void main(String[] args) {

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("nihaoyak".toCharArray());
        charBuffer.flip();
        while (charBuffer.hasRemaining()) {
            System.out.println("1  " + charBuffer.position());
            charBuffer.mark();
            char a = charBuffer.get();
            char b = charBuffer.get();
            charBuffer.reset();
            charBuffer.put(b).put(a);
            System.out.println("2  " + charBuffer.position());
        }
        charBuffer.flip();
        System.out.println(charBuffer );
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            byteBuffer.put("huihio21312".getBytes("UTF-16BE"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byteBuffer.flip();
        charBuffer = byteBuffer.asCharBuffer();
        System.out.println(charBuffer);
        System.out.println(charBuffer.position());
        System.out.println(charBuffer.limit());
//        while (charBuffer.hasRemaining())
//            System.out.println(charBuffer.position() + " = " + charBuffer.get());


    }
}
