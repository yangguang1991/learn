import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: kevin yang
 * @Description: 自己写的寻找迷宫路径
 * @Date: create in
 */
public class Main17 {
    public static void main(String[] args) {
        method();
    }

    public static void method() {
        int[][] arr = {{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 1, 1, 1}, {0, 0, 0, 0}};

        // int[][] arr = {{0, 0, 0, 1}, {1, 0, 1, 1}, {1, 0, 1, 1}, {1, 0, 0, 0}};
        Stack<String> stack = new Stack<>();//当前的路径
        List<String> list = new ArrayList<>();//访问过的点
        stack.push("00");

        while (!stack.peek().equals("33")) { //字符串判断需要使用
            int prex = Integer.valueOf(stack.peek().substring(0, 1));
            int prey = Integer.valueOf(stack.peek().substring(1));
            if (right(prex, prey, list, stack, arr)) {
                continue;
            }
            if (left(prex, prey, list, stack, arr)) {
                continue;
            }
            if (down(prex, prey, list, stack, arr)) {
                continue;
            }
            if (up(prex, prey, list, stack, arr)) {
                continue;
            }
            stack.pop();//那就出栈
        }
        System.out.println(stack);
        System.out.println(list);
    }


    public static boolean right(int x, int y, List<String> list, Stack<String> stack, int[][] arr) {
        int x0 = ++x;//
        int y0 = y;
        if (isPossible(x0, y0, list, arr)) {
            list.add(x0 + "" + y0);
            stack.push(x0 + "" + y0);
        } else {
            return false;
        }
        return true;
    }

    public static boolean left(int x, int y, List<String> list, Stack<String> stack, int[][] arr) {
        int x0 = --x;//
        int y0 = y;
        if (isPossible(x0, y0, list, arr)) {
            list.add(x0 + "" + y0);
            stack.push(x0 + "" + y0);
        } else {
            return false;
        }
        return true;
    }

    public static boolean up(int x, int y, List<String> list, Stack<String> stack, int[][] arr) {
        int x0 = x;//
        int y0 = --y;
        if (isPossible(x0, y0, list, arr)) {
            list.add(x0 + "" + y0);
            stack.push(x0 + "" + y0);
        } else {
            return false;
        }
        return true;
    }

    public static boolean down(int x, int y, List<String> list, Stack<String> stack, int[][] arr) {
        int x0 = x;//
        int y0 = ++y;
        if (isPossible(x0, y0, list, arr)) {
            list.add(x0 + "" + y0);
            stack.push(x0 + "" + y0);
        } else {
            return false;
        }
        return true;
    }


    public static boolean isPossible(int x0, int y0, List<String> list, int[][] arr) {

        if (x0 <= 3 && x0 >= 0 && 0 <= y0 && y0 <= 3 && !list.contains(x0 + "" + y0) && arr[x0][y0] == 0) {

        } else {
            return false;
        }
        return true;
    }


}






