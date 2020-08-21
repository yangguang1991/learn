import java.util.Scanner;

/**
 * @Author: kevin yang
 * @Description:  德州扑克
 * @Date: create in 2020/8/21 15:17
 */
public class Main {

    public static void main(String[] args) {
        method();
    }

    public static void method() {
        Scanner s = new Scanner(System.in);
        String a = s.nextLine();
        Scanner ss = new Scanner(System.in);
        String b = ss.nextLine();

        int len = b.length();
        int i;

        for (i = 0; i < a.length(); i++) {
            if (len == 1) {
                if (a.charAt(i) > b.charAt(0)) {//单张如果出现大的  直接
                    System.out.println("YES");
                    return;
                }
            } else if (len == 2) {
                if (a.charAt(i) > b.charAt(0) && a.charAt(i) == a.charAt(i + 1)) {  //两个要相同还比他大
                    System.out.println("YES");
                    return;
                }
            } else if (len == 3) {
                if (a.charAt(i) > b.charAt(0) && a.charAt(i) == a.charAt(i + 1)
                        && a.charAt(i) == a.charAt(i + 2)) { //   三个一样的
                    System.out.println("YES");
                    return;
                }
            } else if (len == 4) {
                if (a.charAt(i) > b.charAt(0) && a.charAt(i) == a.charAt(i + 1)
                        && a.charAt(i) == a.charAt(i + 2)
                        && a.charAt(i) == a.charAt(i + 3)) {  //
                    System.out.println("YES");
                    return;
                }
            } else if (len == 5) {
                if (a.charAt(i) > b.charAt(0)
                        && (a.indexOf(a.charAt(i) + 1)) != -1
                        && (a.indexOf(a.charAt(i) + 2)) != -1
                        && (a.indexOf(a.charAt(i) + 3)) != -1
                        && (a.indexOf(a.charAt(i) + 4)) != -1) {
                    System.out.println("YES");
                    return;
                }
            }
            if (i == (a.length() - len) && b.length() != 4) {
                System.out.println("NO");
                return;
            }

            //4的话也是可以的了
            if (i + 3 <= a.length() - 1 && b.length() != 4 && a.charAt(i) == a.charAt(i + 1)
                    && a.charAt(i) == a.charAt(i + 2)
                    && a.charAt(i) == a.charAt(i + 3)) {  //小于4的也可以用炸弹
                System.out.println("YES");
                return;
            }
        }
    }
}
