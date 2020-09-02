package com.example.thread;

public class TestVolatile {

    public static boolean flag = false;
    //不加volatile的话，程序一直不结束，说明即使t2改变了flag的值，t1也感知不到这种变化，t1自己缓存的flag
    //加了以后volatile，程序立刻结束，说明volatile可以使线程工作内存的变量失效，从而确保可见性

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=======t1 start======");
                while (!flag) {
                }
                System.out.println("=======t1 end=====");
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=======t2 start======");
                flag = true;
                System.out.println("t2 flag=" + flag);
                System.out.println("=======t2 end=====");
            }
        }).start();
    }
}
