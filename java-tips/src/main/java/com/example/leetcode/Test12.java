package com.example.leetcode;

import java.util.*;

/**
 * @Author: kevin yang
 * @Description: 12
 * @Date: create in 2020/9/3 9:41
 */
public class Test12 {
    public static String intToRoman(int num) {
        Map<Integer, String> symbolMap = new LinkedHashMap<>();//放进去是什么顺序，读取出来也是什么顺序
        symbolMap.put(1000, "M");
        symbolMap.put(500, "D");
        symbolMap.put(100, "C");
        symbolMap.put(50, "L");
        symbolMap.put(10, "X");
        symbolMap.put(5, "V");
        symbolMap.put(1, "I");
        StringBuilder resultBuilder = new StringBuilder();
        Set<Integer> set = symbolMap.keySet();
        Iterator<Integer> iterator = set.iterator();
        String preSymbol = "";
        String currentSymbol = "";
        int temp = 0;
        while (iterator.hasNext()) {
            preSymbol = temp == 0 ? preSymbol = "" : symbolMap.get(temp);
            temp = iterator.next();
            currentSymbol = symbolMap.get(temp);

            if (num >= temp && num / temp != 0) {
                int shang = num / temp;

                if (temp == 500 || temp == 50 || temp == 5) {

                    if (num / (temp / 5) == 9) {
                        resultBuilder.append(symbolMap.get(temp / 5));
                        resultBuilder.append(preSymbol);
                        num = num - 9 * (temp / 5);
                        continue;
                    }
                }

                if (shang < 4) {
                    for (int i = 0; i < shang; i++) {
                        resultBuilder.append(currentSymbol);
                    }
                } else if (shang > 0) {
                    resultBuilder.append(currentSymbol);
                    resultBuilder.append(preSymbol);
                }
                num = num - (num / temp) * temp;
            } else {
                continue;
            }
        }
        return resultBuilder.toString();
    }


    public static void main(String[] args) {
        intToRoman(3);
    }
}
