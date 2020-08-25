/**
 * @Author: kevin yang
 * @Description: 用来计算工卡的   英文数字+字母
 * @Date: create in
 */
public class Main10 {
    public static void main(String[] args) {
        method(5, 3);
    }

    public static void method(int n, int m) {
        //字母长度 26
        //数字 10
        // 10位的工号
        int num = 0;
        for (int i = 1; i <= m; i++) {
            num = Double.valueOf(Math.pow(26, i)).intValue();
            num = num * Double.valueOf(Math.pow(10, n - i)).intValue();
            System.out.println(num);
        }
    }
}
