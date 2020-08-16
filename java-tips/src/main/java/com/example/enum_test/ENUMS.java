package com.example.enum_test;

import java.util.Random;

public class ENUMS {

    public static Random random = new Random();//random的应用，可以获取随机数

    public static <T extends Enum<T>> T randomSelect(Class<T> ec) {//通过泛型进行了归一化，实现了代码的整合
        return values(ec.getEnumConstants());
    }


    public static <T> T values(T[] values) {
        return values[random.nextInt(values.length)];
    }


}
