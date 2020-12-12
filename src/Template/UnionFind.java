package Template;

/**
 * @program: PhoesLeeCode
 * @description: 并查集模板
 * @author: Feng.H
 * @create: 2020-12-12 16:44
 **/
public class UnionFind {
    static int[] parent;
    static int[] rank;

    public void init(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void Union(int x, int y) {
        int a = find(x);
        int b = find(y);
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

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
