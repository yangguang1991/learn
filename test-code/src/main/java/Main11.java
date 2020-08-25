import java.util.Scanner;

/**
 * @Author: kevin yang
 * @Description: 求阶乘
 * @Date: create in 2020/8/25 17:52
 */
public class Main11 {

    public static void main(String[] args) {

        Scanner scan =new Scanner(System.in);
        int n =scan.nextInt();
        int s=1,sum=0;
        for(int i=1;i<=n;i++){
            s*=i;           //求出阶乘
            sum+=s;     //阶乘之和
        }
        System.out.println(n+"的阶乘之和为："+sum);
    }
}
