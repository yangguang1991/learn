package com.example.leetcode;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/2/27  18:45
 */
public class Test6 {

    public static String convert(String s, int numRows) {

        if (s.length() == 0||numRows==1) {
            return s;
        }

        int batch = 2 * numRows - 2;
        int col = (s.length() / (batch) + 1) * (numRows - 1);
        String[][] arr = new String[numRows][col];
        int x = 0;
        int y = 0;
        int temp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (temp < numRows - 1) {
                arr[x][y] = s.charAt(i) + "";
                x++;
            } else if (temp <= batch - 1) {
                arr[x][y] = s.charAt(i) + "";
                x--;
                y++;
            }
            temp = temp == batch - 1 ? 0 : ++temp;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                stringBuilder.append(arr[i][j] == null ? "" : arr[i][j]);
            }
            stringBuilder = i == arr.length - 1 ? stringBuilder : stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        convert("LEETCODEISHIRING", 4);
    }
}
