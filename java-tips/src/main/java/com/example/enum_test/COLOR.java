package com.example.enum_test;

public enum COLOR {
    RED("rerererere"), GREEN("ggggg"), BLUE("bbbbbb");


    String string;

    COLOR(String string) {//定义构造器，为枚举类增加描述

        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
