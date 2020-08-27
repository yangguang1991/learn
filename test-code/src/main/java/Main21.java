import java.util.Scanner;
import java.util.Stack;

public class Main21 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            char[] arr = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < arr.length; i++) {
                //遇到')'   那么就返回
                if (!")".equalsIgnoreCase(arr[i] + "")) {
                    stack.push(arr[i]);
                } else { // 若是')',那么就把当前的操作符出栈

                    String pa = "";
                    while (stack.peek() != '(') {
                        pa = stack.pop() + pa;
                    }
                    stack.pop();//将'C' 取出
                    //对pa进行计算，然后在入栈
                    String[] comArr = pa.split(" ");

                    if (comArr[0].equalsIgnoreCase("add")) {
                        int result = Integer.valueOf(comArr[1]) +
                                Integer.valueOf(comArr[2]);
                        char[] temp = (result + "").toCharArray();
                        for (int j = 0; j < temp.length; j++) {
                            stack.push(temp[j]);
                        }
                    }

                    if (comArr[0].equalsIgnoreCase("sub")) {
                        int result = Integer.valueOf(comArr[1]) -
                                Integer.valueOf(comArr[2]);
                        char[] temp = (result + "").toCharArray();
                        for (int j = 0; j < temp.length; j++) {
                            stack.push(temp[j]);
                        }
                    }
                    if (comArr[0].equalsIgnoreCase("mul")) {
                        int result = Integer.valueOf(comArr[1]) *
                                Integer.valueOf(comArr[2]);
                        char[] temp = (result + "").toCharArray();
                        for (int j = 0; j < temp.length; j++) {
                            stack.push(temp[j]);
                        }
                    }

                    if (comArr[0].equalsIgnoreCase("div")) {
                        if (Integer.valueOf(comArr[2]) == 0) {
                            System.out.println("error");
                            break;
                        }
                        int result = Integer.valueOf(comArr[1]) /
                                Integer.valueOf(comArr[2]);

                        double doubleResult = Double.valueOf(comArr[1]) /
                                Double.valueOf(comArr[2]);

                        if (doubleResult < result) {
                            result = result - 1;
                        }
                        char[] temp = (result + "").toCharArray();
                        for (int j = 0; j < temp.length; j++) {
                            stack.push(temp[j]);
                        }
                    }
                }
            }
            String str = "";
            while (!stack.empty()) {
                str = stack.pop() + str;
            }
            if (!str.equalsIgnoreCase("")) {
                System.out.println(str);
            }

        }
    }
}
