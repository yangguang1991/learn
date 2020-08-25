import java.util.*;

//寻找朋友圈的人数
public class Main6 {


    //对这个矩阵，进行遍历然后找出最大的连续长度
    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        method(arr);
    }

    public static void method(int[][] arr) {
        int hang = arr.length - 1;
        int lie = arr.length - 1;
        int j = 0;
        int start = 1;
        Set<Integer> set = new HashSet<Integer>();
        while (j <= hang - 1) {
            for (int i = start; i <= hang; i++) {
                if(arr[j][i]==1){
                    set.add(i);
                    set.add(j);
                }
            }
            j++;
        }
        System.out.println(set);
        List<Integer> list = new ArrayList();
        list.addAll(set);
        Collections.sort(list);
        int result = 1;
        int temp = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) + 1 == list.get(i)) {
                temp++;
            } else {
                if (temp > result) {
                    result = temp;
                }
                temp = 1;
            }
        }
        if (temp > result) {
            result = temp;
        }
        System.out.println("result=" + result);
    }
}
