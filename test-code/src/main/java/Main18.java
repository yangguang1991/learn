import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main18 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            char[] arr = s.toCharArray();
            char result = arr[0];
            int index = 0;
            Set<String> set =new TreeSet<>();


//aabfffb
//aabfffb

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] <= result) {
                    result = arr[i];
                    index = i;
                }
            }
            //把二者的位置替换
            char temp;
            for (int i = 0; i < arr.length && index != 0; i++) {
                if (arr[i] > result && index > i) {
                    temp = result;
                    arr[index] = arr[i];
                    arr[i] = temp;
                    break;
                }
            }
            System.out.println(new String(arr));
        }
    }
}
