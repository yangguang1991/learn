package com.example.java.optional;

import java.util.Optional;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/3/21  11:36
 */
public class TestOption {

    public static void main(String[] args) {

//        Optional<User> emptyOpt = Optional.empty();
//        emptyOpt.get();

        User u1 = new User("yang", "123");
        User u2 = null;
        User u3 = new User("wang", "123");
        Optional<User> opt = Optional.ofNullable(u2);
        System.out.println(opt.isPresent());

        //若是非空的对象传进去，orElse依然创建对象，orElseGet不在创建
        User u4 = Optional.ofNullable(u1).orElse(new User("44", ""));
        User u5 = Optional.ofNullable(u1).orElseGet(() -> new User("55", ""));

        //若是空的对象传进去，orElse和orElseGet都会创建
        User u6 = Optional.ofNullable(u1).orElse(new User("44", ""));
        User u7 = Optional.ofNullable(u1).orElseGet(() -> new User("55", ""));






    }
}
