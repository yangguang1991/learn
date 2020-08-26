import java.util.*;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/8/26 9:39
 */
public class Main13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext("0")) {
            String str = sc.nextLine();
            char[] chars = str.toCharArray();
            TreeMap<Character, Integer> tm = new TreeMap<>();//treeMap保证顺序不变
            for (char key : chars) {
                if (!tm.containsKey(key)) {
                    tm.put(key, 1);
                } else {
                    tm.put(key, tm.get(key) + 1);
                }
            }

            //value的集合
            Collection<Integer> collection = tm.values();
            // 通过Collections工具找出values集合的最值
            int minValue = Collections.min(collection);//找到次数最小的
            StringBuilder sb = new StringBuilder();
            for (char key : chars) {
                if (tm.get(key) != minValue) {//value是最小的话，那么就不添加
                    sb.append(key);
                }
            }
            System.out.println(sb);
        }
    }
}



