/**
 * @Author: kevin yang
 * @Description: 中序和后序获得前序
 * @Date: create in 2020/8/21 17:19
 */
public class Main1 {

    public static void main(String[] args) {
        String b_str = "DEBFCA";
        String m_str = "DBEACF";
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

        // 通过后序可以获得最后一个点就是根节点
        String root = b_str.substring(b_str.length() - 1);
        System.out.println(root);
        String left_m = m_str.substring(0, m_str.indexOf(root));
        String left_b = b_str.substring(0, left_m.length());
        method(left_b, left_m);
        String right_m = m_str.substring(m_str.indexOf(root) + 1);
        String right_b = b_str.substring(left_b.length(), left_b.length() + right_m.length());
        method(right_b, right_m);
    }
}
