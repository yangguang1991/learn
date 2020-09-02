/**
 * @Author: kevin yang
 * @Description: 通过前序和中序获得后序遍历的
 * @Date: create in 2020/8/25 11:14
 */
public class Main8 {

    public static void main(String[] args) {
        String pre_str = "ABDECF";
        String m_str = "DBEACF";
        method(pre_str, m_str);
    }

    public static void method(String pre_str, String m_str) {
        if (pre_str.length() == 1 || m_str.length() == 0) {
            System.out.println(pre_str);
            return;
        }
        //通过前序获得根节点
        String root = pre_str.substring(0, 1);
        //通过根节点获得左子树的中序
        String left_m = m_str.substring(0, m_str.indexOf(root));
        String left_pre = pre_str.substring(1, 1 + left_m.length());
        method(left_pre, left_m);
        String right_m = m_str.substring(m_str.indexOf(root) + 1);
        String right_pre = pre_str.substring(pre_str.length() - right_m.length());
        method(right_pre, right_m);
        System.out.println(root);
    }
}
