package leetcode;

import Template.UnionFind;
import leetcode.dataStruct.TreeNode;

import java.util.*;

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
            if (uf.isConnect(edge[0] - 1, edge[1] - 1)) {
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
     * <p>
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，
     * 如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
     * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.91% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了21.29% 的用户
     *
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

    /**
     * 1579. 保证图可完全遍历
     * <p>
     * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：
     * 类型 1：只能由 Alice 遍历。
     * 类型 2：只能由 Bob 遍历。
     * 类型 3：Alice 和 Bob 都可以遍历。
     * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
     * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
     * <p>
     * 执行用时：13 ms, 在所有 Java 提交中击败了98.97% 的用户
     * 内存消耗：96.5 MB, 在所有 Java 提交中击败了79.38% 的用户
     *
     * @param n     无向图，其中包含 n 个节点
     * @param edges 边
     * @return 以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufA = new UnionFind(n + 1);
        UnionFind ufB = new UnionFind(n + 1);
        int ans = 0;
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (ufA.isConnect(edge[1], edge[2])) {
                    ans++;
                    continue;
                }
                ufA.union(edge[1], edge[2]);
                ufB.union(edge[1], edge[2]);
            }
        }
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (ufA.isConnect(edge[1], edge[2])) {
                    ans++;
                    continue;
                }
                ufA.union(edge[1], edge[2]);
            } else if (edge[0] == 2) {
                if (ufB.isConnect(edge[1], edge[2])) {
                    ans++;
                    continue;
                }
                ufB.union(edge[1], edge[2]);
            }
        }
        int rightA = 0, rightB = 0;
        for (int i = 1; i <= n; i++) {
            if (ufA.parent[i] == i) {
                rightA++;
                if (rightA > 1) {
                    return -1;
                }
            }
            if (ufB.parent[i] == i) {
                rightB++;
                if (rightB > 1) {
                    return -1;
                }
            }
        }
        return ans;
    }

    /**
     * 1154. 一年中的第几天
     * <p>
     * 给你一个按 YYYY-MM-DD 格式表示日期的字符串 date，请你计算并返回该日期是当年的第几天。
     * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了69.09% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了12.53% 的用户
     *
     * @param date 表示日期的字符串
     * @return 期是当年的第几天
     */
    public int dayOfYear(String date) {
        String[] time = date.split("-");
        int year = Integer.parseInt(time[0]);
        int month = Integer.parseInt(time[1]);
        int day = Integer.parseInt(time[2]);
        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
            monthDays[1] = 29;
        }
        int ans = 0;
        for (int i = 0; i < month - 1; i++) {
            ans += monthDays[i];
        }
        return ans + day;
    }

    private int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 724. 寻找数组的中心索引
     * <p>
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
     * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了55.66% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了39.04% 的用户
     *
     * @param nums 数组
     * @return 数组的中心索引
     */
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n - 1;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum - nums[0] == 0) {
            return 0;
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            left += nums[i - 1];
            if (left == sum - left - nums[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 1432. 改变一个整数能得到的最大差值
     * <p>
     * 给你一个整数 num 。你可以对它进行如下步骤恰好 两次 ：
     * 选择一个数字 x (0 <= x <= 9).
     * 选择另一个数字 y (0 <= y <= 9) 。数字 y 可以等于 x 。
     * 将 num 中所有出现 x 的数位都用 y 替换。
     * 得到的新的整数 不能 有前导 0 ，得到的新整数也 不能 是 0 。
     * 令两次对 num 的操作得到的结果分别为 a 和 b 。
     * 请你返回 a 和 b 的 最大差值 。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.13% 的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了93.75% 的用户
     *
     * @param num 整数
     * @return 改变一个整数能得到的最大差值
     */
    public int maxDiff(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        int minN = 0;
        int maxN = 0;
        if ('1' == str.charAt(0)) {
            char minChange = '-';
            minN = 1;
            maxN = 9;
            for (int i = 1; i < n; i++) {
                if ('-' == minChange && '1' != str.charAt(i) && '0' != str.charAt(i)) {
                    minChange = str.charAt(i);
                }
                if (str.charAt(i) == minChange) {
                    minN = minN * 10;
                } else {
                    minN = minN * 10 + str.charAt(i) - '0';
                }
                if ('1' == str.charAt(i)) {
                    maxN = maxN * 10 + 9;
                } else {
                    maxN = maxN * 10 + str.charAt(i) - '0';
                }
            }
        } else if ('9' == str.charAt(0)) {
            char maxChange = '-';
            minN = 1;
            maxN = 9;
            for (int i = 1; i < n; i++) {
                if ('-' == maxChange && '9' != str.charAt(i)) {
                    maxChange = str.charAt(i);
                }
                if (str.charAt(i) == maxChange) {
                    maxN = maxN * 10 + 9;
                } else {
                    maxN = maxN * 10 + str.charAt(i) - '0';
                }
                if ('9' == str.charAt(i)) {
                    minN = minN * 10 + 1;
                } else {
                    minN = minN * 10 + str.charAt(i) - '0';
                }
            }
        } else {
            char change = str.charAt(0);
            for (int i = 0; i < n; i++) {
                if (change == str.charAt(i)) {
                    minN = minN * 10 + 1;
                    maxN = maxN * 10 + 9;
                } else {
                    minN = minN * 10 + str.charAt(i) - '0';
                    maxN = maxN * 10 + str.charAt(i) - '0';
                }
            }
        }
        return maxN - minN;
    }

    /**
     * 面试题 16.19. 水域大小
     * <p>
     * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。
     * 池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
     * <p>
     * 执行用时：15 ms, 在所有 Java 提交中击败了59.24% 的用户
     * 内存消耗：62.9 MB, 在所有 Java 提交中击败了62.57% 的用户
     *
     * @param land 一片土地的整数矩阵land
     * @return 所有池塘的大小，返回值需要从小到大排序
     */
    public int[] pondSizes(int[][] land) {
        int row = land.length;
        int col = land[0].length;
        List<Integer> pondSizeType = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (land[i][j] == 0) {
                    pondSizeType.add(pondSizesDFS(land, i, j));
                }
            }
        }
        int[] ans = pondSizeType.stream().mapToInt(Integer::valueOf).toArray();
        Arrays.sort(ans);
        return ans;
    }

    private int[][] pondDir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    private int pondSizesDFS(int[][] land, int x, int y) {
        land[x][y] = 1;
        int ans = 1;
        for (int[] dir : pondDir) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx < 0 || nx >= land.length || ny < 0 || ny >= land[0].length || land[nx][ny] > 0) {
                continue;
            }
            ans += pondSizesDFS(land, nx, ny);
        }
        return ans;
    }


    /**
     * 888. 公平的糖果棒交换
     * <p>
     * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
     * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
     * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
     * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
     * <p>
     * 执行用时：26 ms, 在所有 Java 提交中击败了28.10% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了66.32% 的用户
     *
     * @param A 糖果棒
     * @param B 糖果棒
     * @return 交换的糖果棒
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int dir = (sumA - sumB) / 2;
        for (int i : A) {
            if (i - dir > 0) {
                int target = i - dir;
                int l = 0;
                int r = B.length - 1;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (B[mid] < target) {
                        l = mid + 1;
                    } else if (B[mid] > target) {
                        r = mid - 1;
                    } else {
                        return new int[]{i, target};
                    }
                }
            }
        }
        return null;
    }

    /**
     * 424. 替换后的最长重复字符
     * <p>
     * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
     * 注意：字符串长度 和 k 不会超过 104。
     * <p>
     * 执行用时：765 ms, 在所有 Java 提交中击败了5.02% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了25.29% 的用户
     *
     * @param s 字符串
     * @param k 字符串
     * @return 替换后的最长重复字符长度
     */
    public int characterReplacement(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] num = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (i <= k) {
                num[i] = i + 1;
            } else {
                int t = 0;
                int l = i - 1;
                while (l >= 0 && (t < k || chars[l] == chars[i])) {
                    if (chars[l] != chars[i]) {
                        t++;
                    }
                    l--;
                }
                num[i] = i - l + (k - t);
            }
            max = Math.max(max, num[i]);
        }
        return Math.min(max, n);
    }

    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        boolean f = false;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                if (f) {
                    return false;
                } else {
                    f = true;
                    nums[i] = nums[i - 1];
                }
            }
        }
        return true;
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int n = nums.length;
        int m = nums[0].length;
        if (n * m == r * c) {
            int[][] ans = new int[r][c];
            int t = 0;
            int p = 0;
            for (int[] ns : nums) {
                for (int nu : ns) {
                    ans[t][p++] = nu;
                    if (p == c) {
                        p = 0;
                        t++;
                    }
                }
            }
            return ans;
        }
        return nums;
    }

    /**
     * 480. 滑动窗口中位数
     * <p>
     * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
     * 例如：
     * [2,3,4]，中位数是 3
     * [2,3]，中位数是 (2 + 3) / 2 = 2.5
     * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
     * 示例：
     * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
     * <p>
     * 执行用时：20 ms, 在所有 Java 提交中击败了98.61% 的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了5.20% 的用户
     *
     * @param nums 数组
     * @param k    滑动窗口长度
     * @return 滑动窗口中位数
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k == 1) {
            double[] ans = new double[n];
            for (int i = 0; i < n; i++) {
                ans[i] = nums[i];
            }
            return ans;
        }
        boolean isEven = k % 2 == 0;
        // 左最大堆， 右最小堆；
        // 查中位数： 当左边数目等于右边数目的时候，取平均数
        // 删除一个数： 延迟删除，
        //            每次取出顶点的数的时候判断下是否被删除，如果被删除，则取下一个数；
        //            分别用两个map记录左右被删除的数但是实际上没有被删除的数
        // 插入一个数： 将新的数和中间数排序，插入删除的数和中间数的位置??
        PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        PriorityQueue<Integer> right = new PriorityQueue<>();
        HashMap<Integer, Integer> lDelete = new HashMap<>();
        HashMap<Integer, Integer> rDelete = new HashMap<>();

        // 初始化
        int i = 0;
        for (; i < (k + 1) / 2; i++) {
            left.add(nums[i]);
        }
        for (; i < k; i++) {
            left.add(nums[i]);
            int tmp = left.poll();
            right.add(tmp);
        }

        double[] ans = new double[n - k + 1];
        if (isEven) {
            int le = medianSlidingWindowGetRealValue(left, lDelete);
            int ri = medianSlidingWindowGetRealValue(right, rDelete);
            ans[i - k] = (double) (le) / 2 + (double) (ri) / 2;
        } else {
            ans[i - k] = medianSlidingWindowGetRealValue(left, lDelete);
        }
        for (; i < n; i++) {
            int lmax = medianSlidingWindowGetRealValue(left, lDelete);
            if (nums[i - k] > lmax) {
                rDelete.put(nums[i - k], rDelete.getOrDefault(nums[i - k], 0) + 1);
                if (nums[i] >= lmax) {
                    right.add(nums[i]);
                } else {
                    right.add(lmax);
                    left.poll();
                    left.add(nums[i]);
                }
            } else {
                int rmin = medianSlidingWindowGetRealValue(right, rDelete);
                lDelete.put(nums[i - k], lDelete.getOrDefault(nums[i - k], 0) + 1);
                if (nums[i] <= rmin) {
                    left.add(nums[i]);
                } else {
                    left.add(rmin);
                    right.poll();
                    right.add(nums[i]);
                }
            }
            if (isEven) {
                int le = medianSlidingWindowGetRealValue(left, lDelete);
                int ri = medianSlidingWindowGetRealValue(right, rDelete);
                ans[i - k + 1] = (double) (le) / 2 + (double) (ri) / 2;
            } else {
                ans[i - k + 1] = medianSlidingWindowGetRealValue(left, lDelete);
            }
        }
        return ans;
    }

    private int medianSlidingWindowGetRealValue(PriorityQueue<Integer> priorityQueue, HashMap<Integer, Integer> map) {
        int ans = priorityQueue.peek();
        while (map.containsKey(ans)) {
            int v = map.get(ans);
            v--;
            if (v == 0) {
                map.remove(ans);
            } else {
                map.put(ans, v);
            }
            priorityQueue.poll();
            ans = priorityQueue.peek();
        }
        return ans;
    }

    /**
     * 643. 子数组最大平均数 I
     * <p>
     * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了44.03% 的用户
     *
     * @param nums n 个整数
     * @param k    长度为 k
     * @return 最大平均数
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int i = 0;
        for (; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for (; i < nums.length; i++) {
            sum = sum + nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        return (double) max / k;
    }

    /**
     * 1208. 尽可能使字符串相等
     * <p>
     * 给你两个长度相同的字符串，s 和 t。
     * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了94.88% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了58.76% 的用户
     *
     * @param s       字符串
     * @param t       字符串
     * @param maxCost 开销
     * @return 转化后最长相同子字符串
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = Math.abs(t.charAt(i) - s.charAt(i));
        }
        int max = 0;
        // length : >= l, < r;
        int l = 0, r = 0;
        int now = 0;
        while (r < n) {
            if (now + cost[r] <= maxCost) {
                now += cost[r];
                r++;
            } else {
                max = Math.max(r - l, max);
                now -= cost[l];
                l++;
            }
        }
        if (now <= maxCost) {
            max = Math.max(max, r - l);
        }
        return max;
    }

    /**
     * 1208. 尽可能使字符串相等
     * <p>
     * 给你两个长度相同的字符串，s 和 t。
     * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了94.88% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了58.76% 的用户
     *
     * @param s       字符串
     * @param t       字符串
     * @param maxCost 开销
     * @return 转化后最长相同子字符串
     */
    public int equalSubstring0(String s, String t, int maxCost) {
        int left = 0;   // 窗口左边界
        int len = 0;    // 窗口最大长度
        int cost = 0;   // 当前窗口消耗
        // i作为窗口右边界
        for (int i = 0; i < s.length(); i++) {
            cost += Math.abs(s.charAt(i) - t.charAt(i));
            // 如果当前窗口消耗大于总开销，则左边界++，缩减窗口
            if (cost > maxCost) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
        }
        return s.length() - left;
    }

    /**
     * 520. 检测大写字母
     * <p>
     * 给定一个单词，你需要判断单词的大写使用是否 正确。
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     * 全部字母都是大写，比如"USA"。
     * 单词中所有字母都不是大写，比如"leetcode"。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
     * 否则，我们定义这个单词没有正确使用大写字母。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了86.32% 的用户
     *
     * @param word 单词
     * @return 单词的大写使用是否 正确
     */
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        int num = 0;
        for (char c : word.toCharArray()) {
            if (c <= 'Z') {
                num++;
            }
        }
        return num == 0 || num == n || (num == 1 && word.charAt(0) <= 'Z');
    }

    /**
     * 995. K 连续位的最小翻转次数
     * <p>
     * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
     * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了83.23% 的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了16.25% 的用户
     *
     * @param A 数组
     * @param K 整数
     * @return 连续位的最小翻转次数
     */
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i + K];
            }
        }
        return ans;
    }

    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了76.04% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了7.07% 的用户
     *
     * @param s 字符串
     * @param t 字符串
     * @return 最小覆盖子串
     */
    public String minWindow(String s, String t) {
        int ns = s.length();
        int[] arr = new int[58];
        int[] brr = new int[58];
        int type = 0;
        for (char c : t.toCharArray()) {
            if (brr[c - 'A'] == 0) {
                type++;
            }
            brr[c - 'A']++;
        }

        int l = 0, r = 0;
        String min = "";
        int now = 0;
        char[] ss = s.toCharArray();
        while (r < ns || now >= type) {
            if (now >= type) {
                if ("".equals(min) || min.length() > r - l) {
                    min = s.substring(l, r);
                }
                // l 右移
                arr[ss[l] - 'A']--;
                if (arr[ss[l] - 'A'] < brr[ss[l] - 'A']) {
                    now--;
                }
                l++;
            } else {
                // r 右移
                arr[ss[r] - 'A']++;
                if (arr[ss[r] - 'A'] == brr[ss[r] - 'A']) {
                    now++;
                }
                r++;
            }

        }
        return min;
    }

    /**
     * 998. 最大二叉树 II
     * 最大树定义：一个树，其中每个节点的值都大于其子树中的任何其他值。
     * 给出最大树的根节点 root。
     * 就像之前的问题那样，给定的树是从列表 A（root = Construct(A)）递归地使用下述 Construct(A) 例程构造的：
     * 如果 A 为空，返回 null
     * 否则，令 A[i] 作为 A 的最大元素。创建一个值为 A[i] 的根节点 root
     * root 的左子树将被构建为 Construct([A[0], A[1], ..., A[i-1]])
     * root 的右子树将被构建为 Construct([A[i+1], A[i+2], ..., A[A.length - 1]])
     * 返回 root
     * 请注意，我们没有直接给定 A，只有一个根节点 root = Construct(A).
     * 假设 B 是 A 的副本，并在末尾附加值 val。题目数据保证 B 中的值是不同的。
     * 返回 Construct(B)。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了73.06% 的用户
     *
     * @param root 树
     * @param val  值
     * @return 新的树
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val >= root.val) {
            TreeNode ans = new TreeNode(val);
            ans.left = root;
            return ans;
        }
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }

    /**
     * 1304. 和为零的N个唯一整数
     * <p>
     * 给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了64.06% 的用户
     *
     * @param n 整数
     * @return 和为零的N个唯一整数
     */
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        for (int i = 0; i < n / 2; i++) {
            ans[i] = -(i + 1);
            ans[i + n / 2] = (i + 1);
        }
        return ans;
    }

    /**
     * 1753. 移除石子的最大得分
     * <p>
     * 你正在玩一个单人游戏，面前放置着大小分别为 a​​​​​​、b 和 c​​​​​​ 的 三堆 石子。
     * 每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。
     * 给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了84.91% 的用户
     *
     * @param a 数
     * @param b 数
     * @param c 数
     * @return 最大得分
     */
    public int maximumScore(int a, int b, int c) {
        int max = Math.max(Math.max(a, b), c);
        int sum = a + b + c;
        if (max > sum / 2) {
            return sum - max;
        } else {
            return sum / 2;
        }
    }

    /**
     * 1004. 最大连续1的个数 III
     * <p>
     * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     * 返回仅包含 1 的最长（连续）子数组的长度。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了94.07% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了48.11% 的用户
     *
     * @param A 数组
     * @param K 数
     * @return 包含 1 的最长（连续）子数组的长度
     */
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        if (K >= n) {
            return n;
        }
        int l = 0, r = 0;
        int ans = 0;
        int now = 0;
        while (r < n) {
            if (A[r] == 0 && now < K) {
                now++;
                r++;
            } else if (A[r] == 1) {
                r++;
            } else {
                ans = Math.max(ans, r - l);
                while (A[l] == 1) {
                    l++;
                }
                l++;
                r++;
            }
        }
        return Math.max(ans, r - l);
    }

    /**
     * 1005. K 次取反后最大化的数组和
     * <p>
     * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
     * 以这种方式修改数组后，返回数组可能的最大和。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了28.28% 的用户
     *
     * @param A 数组
     * @param K 数
     * @return 数组可能的最大和
     */
    public int largestSumAfterKNegations(int[] A, int K) {
        //-100 <= A[i] <= 100,这个范围的大小是201
        int[] number = new int[201];
        for (int t : A) {
            //将[-100,100]映射到[0,200]上
            number[t + 100]++;
        }
        int i = 0;
        while (K > 0) {
            //找到A[]中最小的数字
            while (number[i] == 0) {
                i++;
            }
            //此数字个数-1
            number[i]--;
            //其相反数个数+1
            number[200 - i]++;
            //若原最小数索引>100,则新的最小数索引应为200-i.(索引即number[]数组的下标)
            if (i > 100) {
                i = 200 - i;
            }
            K--;
        }
        int sum = 0;
        //遍历number[]求和
        for (int j = i; j < number.length; j++) {
            //j-100是数字大小,number[j]是该数字出现次数.
            sum += (j - 100) * number[j];
        }
        return sum;
    }

    /**
     * 797. 所有可能的路径
     *
     * 给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
     * 二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点（译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a ）空就是没有下一个结点了。
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了87.04% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了36.76% 的用户
     * @param graph 有向无环图
     * @return 所有从 0 到 n-1 的路径
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        allPathsSourceTargetDFS(graph, visited, 0, n - 1, list);
        return allPathsSourceTargetRes;
    }

    private List<List<Integer>> allPathsSourceTargetRes = new ArrayList<>();

    private void allPathsSourceTargetDFS(int[][] graph, boolean[] visited, int now, int target, List<Integer> list) {
        if (now == target) {
            List<Integer> newList = new ArrayList<>();
            newList.addAll(list);
            allPathsSourceTargetRes.add(newList);
            return;
        }
        for (int i : graph[now]) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            list.add(i);
            allPathsSourceTargetDFS(graph, visited, i, target, list);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
