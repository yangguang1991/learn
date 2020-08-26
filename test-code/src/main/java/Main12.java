import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//区间合并
public class Main12 {

    public static void main(String[] args) {

        //区间进行排序然后如果，上一个的最大值小于第二个那么就可以进行合并
       // int[][] intervals = {{1, 3}, {2, 6}, {15, 18}, {8, 10}};
        int[][] intervals = {{1, 4}, {4, 4}, {5,9}, {8, 10}};
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        System.out.println(intervals);

        for (int i = 0; i < intervals.length; i++)
            for (int j = 0; j < intervals[0].length; j++) {
                System.out.println(intervals[i][j]);
            }

        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        int pre0 = intervals[0][0];
        int pre1 = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //
            int current0 = intervals[i][0];
            int current1 = intervals[i][1];

            if (current0 >= pre0 && current0 <= pre1) {
                int temp = Math.max(current1, pre1);
                intervals[i - 1][0] = pre0;
                intervals[i - 1][1] = temp;
                pre0 = current0;
                pre1 = temp;
            } else {
                list.add(intervals[i]);
                pre0 = intervals[i][0];
                pre1 = intervals[i][1];

            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i)[0] + "  " + list.get(i)[1]);
        }

    }
}
