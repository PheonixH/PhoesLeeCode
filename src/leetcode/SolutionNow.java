package leetcode;

import Template.UnionFind;

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
}
