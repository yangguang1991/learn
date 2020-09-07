package com.example.leetcode;

import java.util.concurrent.Semaphore;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/3 17:41
 */
public class Test1114_s {


    public class Foo {
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        public Foo() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            semaphore2.release();//释放一个通道
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // printSecond.run() outputs "second". Do not change or remove this line.

            semaphore2.acquire();//等到一个通道的打开
            printSecond.run();
            semaphore3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            // printThird.run() outputs "third". Do not change or remove this line.
            semaphore3.acquire();
            printThird.run();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        Test1114_s test1114 = new Test1114_s();
        final Test1114_s.Foo foo = test1114.new Foo();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("first");
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("second");
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("third");
            }
        };

        foo.first(runnable1);
        foo.second(runnable2);
        foo.third(runnable3);


    }
}
