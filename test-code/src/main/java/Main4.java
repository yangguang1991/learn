import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class Main4 {
    //  547   朋友圈
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = new String(sc.nextLine());
            char[] arr = s.toCharArray();
            int i = 0;
            List<Integer> list = new ArrayList<>();
            Stack<Character> stack = new Stack<>();
            while (i <= arr.length - 1) {
                if (!stack.empty() && arr[i] == ')') {
                    list.add(stack.size() + 1);
                    stack.pop();
                } else {
                    list.add(stack.size());
                    stack.push(arr[i]);
                }
                i++;
            }
            System.out.println(list);
        }
    }
}
