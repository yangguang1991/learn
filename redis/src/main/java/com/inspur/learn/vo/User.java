package com.inspur.learn.vo;


/**
 * @Description:
 * @user: yangguang_lc
 * @Time: 2018/7/17  14:58
 */


import java.io.Serializable;

public class User implements Serializable {

    int age;
    String name;

    public User(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
