package leetcode.dataStruct;

import java.util.List;

/**
 * @ Author     ：Pheonix
 * @ Date       ：Created in 下午3:18 19-4-22
 * @ Description：
 */
public class Nodes {
    public int val;
    public List<Nodes> children;

    public Nodes() {}

    public Nodes(int _val,List<Nodes> _children) {
        val = _val;
        children = _children;
    }
}
