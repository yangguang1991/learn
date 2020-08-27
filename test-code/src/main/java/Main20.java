import java.util.Arrays;
import java.util.Scanner;

public class Main20 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            char[] arr = s.toCharArray();
//aabfffb
//aabfffb
            char[] oldArr = Arrays.copyOf(arr, arr.length);
            Arrays.sort(arr);
            int beginIndex = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != oldArr[i]) {
                    beginIndex = i;
                    break;
                }
            }

            char result = arr[beginIndex];
            int index = beginIndex;
            for (int i = beginIndex; i < oldArr.length; i++) {
                if (oldArr[i] <= result) {
                    result = oldArr[i];
                    index = i;
                }
            }
            //把二者的位置替换
            char temp;
            temp = oldArr[index];
            oldArr[index] = oldArr[beginIndex];
            oldArr[beginIndex] = temp;

            System.out.println(new String(oldArr));


        }
    }
}
