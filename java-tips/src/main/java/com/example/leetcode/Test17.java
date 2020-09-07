package com.example.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/5 22:37
 */
public class Test17 {


    public static List<String> letterCombinations(String digits) {
        List<String> tempList = new ArrayList<>();//所有要组合的
        List<String> resultList = new ArrayList<>();//所有要组合的
        if (digits.length() == 0) {
            return resultList;
        }
        Map<String, String[]> map = new HashMap<>();
        map.put("2", new String[]{"a", "b", "c"});
        map.put("3", new String[]{"d", "e", "f"});
        map.put("4", new String[]{"g", "h", "i"});
        map.put("5", new String[]{"j", "k", "l"});
        map.put("6", new String[]{"m", "n", "o"});
        map.put("7", new String[]{"p", "q", "r", "s"});
        map.put("8", new String[]{"t", "u", "v"});
        map.put("9", new String[]{"w", "x", "y", "z"});
        char[] arr = digits.toCharArray();

        resultList.add("");
        String[] arr2 = null;//所有要组合的
        for (int i = 0; i < arr.length; i++) {
            arr2 = map.get("" + arr[i]);
            for (int k = 0; k < arr2.length; k++) {
                for (int j = 0; j < resultList.size(); j++) {
                    tempList.add(resultList.get(j) + arr2[k]);
                }
            }
            resultList.clear();
            resultList.addAll(tempList);
            tempList.clear();
        }
        return resultList;
    }


    public static void main(String[] args) {
        String digits = "";
        System.out.println(letterCombinations(digits));

    }
}
