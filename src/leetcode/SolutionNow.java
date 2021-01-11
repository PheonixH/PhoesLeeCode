package leetcode;

import Template.BinaryIndexedTree;
import Template.UnionFind;
import leetcode.dataStruct.ListNode;
import leetcode.dataStruct.TreeNode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ProjectName: PhoesLeeCode
 * @Package: PACKAGE_NAME
 * @ClassName: LeetCode.Solution2
 * @Description:
 * @Author: Pheonix
 * @CreateDate: 2019/4/25 15:05
 * @Version: 1.0
 */
public class SolutionNow {

    /**
     * 547. 省份数量
     * <p>
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
     * 而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.49% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了96.35% 的用户
     *
     * @param isConnected 一个 n x n 的矩阵 isConnected
     * @return 省份数量
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (uf.root(i) == i) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 1202. 交换字符串中的元素
     *
     * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
     * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
     * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
     *
     * 执行用时：64 ms, 在所有 Java 提交中击败了35.42% 的用户
     * 内存消耗：88.8 MB, 在所有 Java 提交中击败了12.50% 的用户
     * @param s 字符串
     * @param pairs 「索引对」数组
     * @return 在经过若干次交换后，s 可以变成的按字典序最小的字符串
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFind uf = new UnionFind(n);
        pairs.forEach(arr -> uf.union(arr.get(0), arr.get(1)));
        char[] chars = s.toCharArray();
        List<List<Character>> lists = new ArrayList<>();
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            uf.root(i);
            PriorityQueue<Character> tmp = map.getOrDefault(uf.parent[i], new PriorityQueue<>());
            tmp.add(chars[i]);
            map.put(uf.parent[i], tmp);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            PriorityQueue<Character> tmp = map.get(uf.parent[i]);
            sb.append(tmp.poll());
        }
        return sb.toString();
    }
}
