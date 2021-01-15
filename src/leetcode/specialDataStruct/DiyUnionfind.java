package leetcode.specialDataStruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: PhoesLeeCode
 * @className: DiyUnionfind
 * @description: 自己手写并查集
 * @author: lov.moran
 * @date 2021-01-15 10:20
 */
public class DiyUnionfind {

    /**
     * 当 父节点 == 自己 的时候就是一个根
     */
    List<Integer> parent;

    /**
     * 表示 每个节点为根的时候的层数：
     * 为了合并之后的并查集层数低
     */
    List<Integer> rank;

    public DiyUnionfind() {
        parent = new ArrayList<>();
        rank = new ArrayList<>();
    }

    public void union(int x, int y) {
        if (x >= y) {
            parent.set(x, parent.get(y));
        } else {
            parent.set(y, parent.get(x));
        }
    }

    public int root(int x) {
        int t = x;
        while (t != parent.get(t)) {
            t = parent.get(t);
        }
        parent.set(x, t);
        return t;
    }

    public boolean isConnect(int x, int y) {
        return root(x) == root(y);
    }
}
