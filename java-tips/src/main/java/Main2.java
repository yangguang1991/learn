import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kevin yang
 * @Description:  通过后序 中序获得层次遍历的
 * @Date: create in
 */
public class Main2 {

    public static void main(String[] args) {
//        String b_str = "DEBFCA";
//        String m_str = "DBEACF";
        String b_str = "DBFCA";
        String m_str = "DBACF";
        method(b_str, m_str);
    }

    public static void method(String b_str, String m_str) {
        if (b_str.length() == 1 || b_str.equals("")) {
            System.out.println(b_str);
            return;
        }
        if (m_str.length() == 1 || m_str.equals("")) {
            return;
        }
        List<String> bList = new ArrayList<>();
        List<String> mList = new ArrayList<>();
        bList.add(b_str);
        mList.add(m_str);
        int n = 0;
        while (n <= bList.size() - 1) {
            String temp_b = bList.get(n);
            String temp_m = mList.get(n);
             n++;
            if (temp_b.length() == 0 || temp_b.length() == 0) {
                continue;
            }
            String root = temp_b.substring(temp_b.length() - 1);
            String left_m = temp_m.substring(0, temp_m.indexOf(root));
            String left_b = temp_b.substring(0, left_m.length());
            String right_m = temp_m.substring(temp_m.indexOf(root) + 1);
            String right_b = temp_b.substring(left_b.length(), left_b.length() + right_m.length());
            System.out.println(root);
            mList.add(left_m);//中序的左子树
            mList.add(right_m);//中序的右子树
            bList.add(left_b);
            bList.add(right_b);
        }
    }
}
