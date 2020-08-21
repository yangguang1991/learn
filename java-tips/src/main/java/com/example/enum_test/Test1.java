package com.example.enum_test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Random;

public class Test1 {


    public static void analyse(Class<?> enumClass) {

        Type[] types = enumClass.getGenericInterfaces();
        for (Type type : types) {
            System.out.println("interface=" + type.getTypeName());
        }

        Method[] methods = enumClass.getMethods();
        for (Method method : methods) {
            System.out.println("method=" + method.getName());
        }
        enumClass.getEnumConstants();//专门为枚举类来使用的一个方法，若不是枚举类那么这个方法返回是null

        for (Object en : enumClass.getEnumConstants()) {
            System.out.println(en);
        }
    }

    public static void main(String[] args) {
        for (COLOR color : COLOR.values()) {
            System.out.println(color.name());
        }
        analyse(COLOR.class);


        System.out.println(COLOR.GREEN.getString());


        System.out.println(ENUMS.randomSelect(COLOR.class));


        Random random = new Random();//random的应用
        for (int i = 0; i < 10; i++) {

            System.out.println(random.nextInt(10));
        }


    }
}
