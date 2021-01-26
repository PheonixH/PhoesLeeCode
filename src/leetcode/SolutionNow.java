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
     * 947. 移除最多的同行或同列石头
     * <p>
     * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
     * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
     * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，
     * 返回 可以移除的石子 的最大数量。
     * <p>
     * 执行用时：39 ms, 在所有 Java 提交中击败了29.85% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了22.84% 的用户
     *
     * @param stones 石头所在整数坐标点
     * @return 可以移除的石子 的最大数量
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    unionFind.union(i, j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (unionFind.root(i) == i) {
                ans++;
            }
        }
        return n - ans;
    }

    /**
     * 1128. 等价多米诺骨牌对的数量
     * <p>
     * 给你一个由一些多米诺骨牌组成的列表 dominoes。
     * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
     * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
     * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
     * <p>
     * 执行用时：31 ms, 在所有 Java 提交中击败了8.78% 的用户
     * 内存消耗：48 MB, 在所有 Java 提交中击败了8.64% 的用户
     *
     * @param dominoes 多米诺骨牌
     * @return 等价的骨牌对 (i, j) 的数量
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<String, Integer> map = new HashMap<>();
        int ans = 0;
        for (int[] domino : dominoes) {
            Arrays.sort(domino);
            String key = domino[0] + "," + domino[1];
            int value = map.getOrDefault(key, 0) + 1;
            map.put(key, value);
            ans += value - 1;
        }
        return ans;
    }

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
     * <p>
     * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
     * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
     * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
     * <p>
     * 执行用时：64 ms, 在所有 Java 提交中击败了35.42% 的用户
     * 内存消耗：88.8 MB, 在所有 Java 提交中击败了12.50% 的用户
     *
     * @param s     字符串
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


    /**
     * 207. 课程表
     * <p>
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     * <p>
     * 执行用时：49 ms, 在所有 Java 提交中击败了7.36% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了55.46% 的用户
     *
     * @param numCourses    课程数量
     * @param prerequisites 先修课程
     * @return 是否可能完成所有课程的学习
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            List<Integer> tmp = map.getOrDefault(prerequisite[1], new ArrayList<>());
            tmp.add(prerequisite[0]);
            map.put(prerequisite[1], tmp);
        }
        boolean[] finished = new boolean[numCourses];
        while (!map.isEmpty()) {
            int bfSize = map.size();
            Set<Map.Entry<Integer, List<Integer>>> entries = map.entrySet();
            Iterator<Map.Entry<Integer, List<Integer>>> iteratorMap = entries.iterator();
            while (iteratorMap.hasNext()) {
                Map.Entry<Integer, List<Integer>> next = iteratorMap.next();
                List<Integer> val = next.getValue();
                val.removeIf(a -> finished[a]);
                if (val.size() == 0) {
                    finished[next.getKey()] = true;
                    iteratorMap.remove();
                }
            }
            int afSize = map.size();
            if (bfSize == afSize) {
                return false;
            }
        }
        return true;
    }

    /**
     * 684. 冗余连接
     * <p>
     * 在本问题中, 树指的是一个连通且无环的无向图。
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了87.99% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了33.69% 的用户
     *
     * @param edges 以边组成的二维数组
     * @return 一条可以删去的边
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.connect(edge[0] - 1, edge[1] - 1)) {
                return edge;
            }
            uf.union(edge[0] - 1, edge[1] - 1);
        }
        return new int[]{};
    }

    /**
     * 1018. 可被 5 整除的二进制前缀
     * <p>
     * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
     * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了92.76% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了65.22% 的用户
     *
     * @param arr 数组 A
     * @return 布尔值列表 answer
     */
    public List<Boolean> prefixesDivBy5(int[] arr) {
        int now = 0;
        List<Boolean> ans = new ArrayList<>();
        for (int a : arr) {
            now = now * 2 + a;
            now = now % 5;
            ans.add(now == 0);
        }
        return ans;
    }

    /**
     * 674. 最长连续递增序列
     *
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，
     * 如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
     * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.91% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了21.29% 的用户
     * @param nums 未经排序的整数数组
     * @return 连续递增子序列最大长度
     */
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int now = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                now++;
            } else {
                ans = Math.max(ans, now);
                now = 1;
            }
        }
        ans = Math.max(ans, now);
        return Math.min(ans, n);
    }
}
