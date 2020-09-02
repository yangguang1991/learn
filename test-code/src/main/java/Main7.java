import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

//迷宫路径
public class Main7 {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int n = in.nextInt();
//            int m = in.nextInt();
//            int[][] maze = new int[n][m];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    maze[i][j] = in.nextInt();
//                }
//            }
//            findBestWay(maze);
//        }
//        in.close();
        int[][] arr = {{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 1, 1, 1}, {0, 0, 0, 0}};
        findBestWay(arr);
    }

    private static void findBestWay(int[][] maze) {
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        map.put(0, 0);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == 0 && j == 0) {
                    System.out.println("(" + i + "," + j + ")");
                    continue;
                }
                if (maze[i][j] == 0) {//不能else maze[i][j]!=0的情况，因为，这样会出问题。
                    //判断当前点的相邻点是否已输出（即为上一个路径零点）
                    if ((map.containsKey(i - 1) && map.get(i - 1) == j || map.containsKey(i) && map.get(i) == j - 1)) {
                        map.put(i, j);//map会被覆盖，但是我只需要它存储上以个值，即可
                        System.out.println("(" + i + "," + j + ")");
                    }
                }
            }
        }
    }
}
