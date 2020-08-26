import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: kevin yang
 * @Description:  替换
 * @Date: create in
 */
public class Main9 {
    public static void main(String[] args) {
        //给定三个数 找到其中所有组合，2可以当做5， 6可以当做9
        //改变之后  数据是肯定变大的
        method();
    }

    public static void method() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        List<Integer> resultList = new ArrayList<>();
        String[] arr = str.split(" ");
        //有重复的直接返回-1
        int i = 0;
        int j = 0;
        int k = 0;
        for (i = 0; i < arr.length; i++)
            for (j = 0; (j < arr.length); j++)
                for (k = 0; (k < arr.length); k++) {
                    if (k != i && j != k && i != j) {
                        resultList.add(Integer.valueOf(arr[i] + arr[j] + arr[k]));
                    }
                }
        System.out.println(resultList);
        //把2替换成5
        for (i = 0; i < resultList.size(); i++) {
            String temp = (resultList.get(i) + "").replace('2', '5');
            resultList.set(i, Integer.valueOf(temp));
        }
        resultList.sort((a, b) -> b - a);//这种表达式真牛逼，简洁
        System.out.println(resultList);

    }
}
