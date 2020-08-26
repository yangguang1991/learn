import java.util.*;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/8/26 16:00
 */
public class Main16 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] arr = str.split("");
        method(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
    }


    public static List<String> method(int a, int b) {
        Map<Integer, List<String>> map = new HashMap();

        map.put(2, Arrays.asList("abc".split("")));
        map.put(3, Arrays.asList("def".split("")));
        map.put(4, Arrays.asList("ghi".split("")));
        map.put(5, Arrays.asList("jkl".split("")));
        map.put(6, Arrays.asList("mno".split("")));
        map.put(7, Arrays.asList("pqrs".split("")));
        map.put(8, Arrays.asList("tuv".split("")));
        map.put(9, Arrays.asList("wxyz".split("")));
        List<String> temp1 = map.get(a);
        List<String> temp2 = map.get(b);

        List<String> temp3 = new ArrayList<>();
        for (int i = 0; i < temp1.size(); i++) {
            for (int j = 0; j < temp2.size(); j++) {
                temp3.add(temp1.get(i) + temp2.get(j));
            }
        }
        return temp3;
    }
}
