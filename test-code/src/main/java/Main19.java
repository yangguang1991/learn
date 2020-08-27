import java.util.Scanner;

public class Main19 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            char[] arr = s.toCharArray();

            String pa = "aeiouAEIOU";
            boolean flag = false;
            int num = 0;
            int result = 0;

            for (int i = 0; i < arr.length; i++) {
                if (pa.contains(arr[i] + "")) {
                    num++;
                } else {
                    if (num > result) {
                        result = num;
                    }
                    num = 0;
                }
            }
            if (num > result) {
                result = num;
            }
            System.out.println(result);
        }
    }
}
