import java.util.*;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in
 */
public class Main14 {

    public static void main(String[] args) {
        method();
    }

    public static void method() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] arr = str.toCharArray();
        Stack<Character> stackA = new Stack<>();
        Stack<Character> stackB = new Stack<>();
        boolean a = true;
        boolean b = true;

        boolean a1 = true;
        boolean b1 = true;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {//说明是A的
                if (a) {
                    stackA.push(arr[i]);
                    a = false;
                } else {
                    stackB.push(arr[i]);
                    a = true;
                }
            }
            if (arr[i] == ')') {//说明是B的
                if (b) {
                    stackA.push(arr[i]);
                    b = false;
                } else {
                    stackB.push(arr[i]);
                    b = true;
                }
            }
            //---------------------
            if (arr[i] == '{') {//说明是A的
                if (a) {
                    stackA.push(arr[i]);
                    a = false;
                } else {
                    stackB.push(arr[i]);
                    a = true;
                }
            }
            if (arr[i] == '}') {//说明是B的
                if (b) {
                    stackA.push(arr[i]);
                    b = false;
                } else {
                    stackB.push(arr[i]);
                    b = true;
                }
            }
        }
        System.out.println("stackA=" + stackA);
        System.out.println("stackB=" + stackB);
        int depA = 0;
        int temp = 0;
        while (!stackA.empty()) {
            char ch = stackA.pop();
            if (ch == ')' || ch == '}') {
                temp++;
            } else {
                temp--;
            }
            depA = temp > depA ? temp : depA;//保存出现过的最大的值，就是最大深度
        }
        int depB = 0;
        temp = 0;
        while (!stackB.empty()) {
            char ch = stackB.pop();
            if (ch == ')' || ch == '}') {
                temp++;
            } else {
                temp--;
            }
            depB = temp > depB ? temp : depB;
        }
        System.out.println("depA=" + depA);
        System.out.println("depB=" + depB);
    }
}
