package Template;

/**
 * @program: PhoesLeeCode
 * @description: 并查集模板
 * @author: Feng.H
 * @create: 2020-12-12 16:44
 **/
public class UnionFind {
    /**
     * 当 父节点 == 自己 的时候就是一个根
     */
    static int[] parent;

    /**
     * 表示 每个节点为根的时候的层数：
     * 为了合并之后的并查集层数低
     */
    static int[] rank;

    public UnionFind(int n){
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void init(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void union(int x, int y) {
        int a = root(x);
        int b = root(y);
        if (a == b) {
            return;
        }
        if (rank[a] > rank[b]) {
            parent[b] = a;
        } else if (rank[b] > rank[a]) {
            parent[a] = b;
        } else {
            parent[a] = b;
            rank[b]++;
        }
    }


    public int root(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = root(parent[x]);
    }

    /**
     * 判断是否属于同一个集合：find(que[0]) == find(que[1])
     */
    public boolean isConnect(int i, int j) {
        return root(i) == root(j);
    }
}
////《算法四》路径压缩带权重并查集模板
//class Union {
//    int[] id;
//    int[] sz;
//
//    public Union(int n) {
//        this.id = new int[n];
//        this.sz = new int[n];
//        for (int i = 0; i < n; i++) {
//            id[i] = i;
//            sz[i] = 1;
//        }
//    }
//
//    public void union(int i, int j) {
//        int fi = root(i);
//        int fj = root(j);
//        if (fi == fj) {
//            return;
//        }
//        if (sz[fi] > sz[fj]) {
//            sz[fi] += sz[fj];
//            id[fj] = fi;
//        } else {
//            sz[fj] += sz[fi];
//            id[fi] = fj;
//        }
//    }
//
//    //        public int root(int i) {
////            return id[i] == i ? i : root(id[i]);
////        }
//    public int root(int i) {
//        while (id[i] != i) {
//            id[i] = id[id[i]];
//            i = id[i];
//        }
//        return i;
//    }
//
//    public boolean connect(int i, int j) {
//        return root(i) == root(j);
//    }
//}
