package com.example.leetcode;

import java.util.Scanner;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/3 16:51
 */
public class Test1114 {

    public class Foo {

        public volatile int flag = 0;

        public Foo() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag = 2;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // printSecond.run() outputs "second". Do not change or remove this line.
            while (flag != 2) {
            }
            printSecond.run();
            flag = 3;
        }

        public void third(Runnable printThird) throws InterruptedException {
            // printThird.run() outputs "third". Do not change or remove this line.
            while (flag != 3) {
            }
            printThird.run();
        }


    }

    public static void main(String[] args) throws InterruptedException {

        Test1114 test1114 = new Test1114();
        final Foo foo = test1114.new Foo();
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
