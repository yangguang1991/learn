package com.example.leetcode;

import javax.sound.midi.Soundbank;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/3 13:46
 */
public class Test13 {


    public static int romanToInt(String s) {

        Map<Character, Integer> symbolMap = new LinkedHashMap<>();
        symbolMap.put('M', 1000);
        symbolMap.put('D', 500);
        symbolMap.put('C', 100);
        symbolMap.put('L', 50);
        symbolMap.put('X', 10);
        symbolMap.put('V', 5);
        symbolMap.put('I', 1);
        char[] arr = s.toCharArray();
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 < arr.length && symbolMap.get(arr[i]) >= symbolMap.get(arr[i + 1])) {//等于或者
                result = result + symbolMap.get(arr[i]);
                continue;
            }
            if (i + 1 < arr.length && symbolMap.get(arr[i]) < symbolMap.get(arr[i + 1])) {
                result = result + symbolMap.get(arr[i + 1]) - symbolMap.get(arr[i]);
                i++;
                continue;
            }

            if (i + 1 == arr.length) {//最后一个直接加
                result = result + symbolMap.get(arr[i]);
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {

        String s = "III";
        System.out.println(romanToInt(s));

    }
}
