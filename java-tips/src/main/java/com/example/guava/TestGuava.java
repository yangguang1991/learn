package com.example.guava;

import com.google.common.collect.Iterables;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yangguang
 * @Title: TestGuava
 * @ProjectName learn
 * @Description: TODO
 * @date 2019/12/9 13:57
 * @Version 1.0.0
 */
public class TestGuava {


    //对元素进行遍历,判断是否存在
    public static boolean isHiddenConfig(Set<String> hiddenSet, String name) {
        return Iterables.any(hiddenSet, hiddenVar -> name.startsWith(hiddenVar));
    }

    public static void main(String[] args) {

        Set<String> hiddenSet = new HashSet<>();
        hiddenSet.add("11");

        hiddenSet.add("2");

        System.out.println(isHiddenConfig(hiddenSet, "1"));


    }
}
