import java.util.Scanner;


//将其中数字用的*号替换
public class Main5 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String a = s.nextLine();
        StringBuffer sb = new StringBuffer();
        s.close();
        a = "y" + a + "c";
        for (int i = 0; i < a.length() - 1; i++) {
            if ((!isNum(a.charAt(i)) && isNum(a.charAt(i + 1))) || (isNum(a.charAt(i)) && !isNum(a.charAt(i + 1)))) {
                sb.append(a.charAt(i)).append("*");
            } else {
                sb.append(a.charAt(i));
            }
        }
        System.out.println(sb.substring(1));
    }

    public static boolean isNum(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        } else {
            return false;
        }
    }
}
