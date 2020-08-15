package com.example.netty.test;

import io.netty.channel.ChannelException;

public class Test2<T extends Object> {

    private final Class<? extends T> clazz;

    public Test2(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    public T newChannel() {
        try {
            return clazz.newInstance();
        } catch (Throwable t) {
            throw new ChannelException("Unable to create Channel from class " + clazz, t);
        }
    }

    public static void main(String[] args) {


        Test2  t2=new Test2(Test1.class);

        System.out.println(t2.newChannel());


    }
}
