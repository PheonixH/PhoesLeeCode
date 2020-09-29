package leetcode;

import leetcode.datestruct.ListNode;
import leetcode.datestruct.TreeNode;

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
     * 1471. 数组中的 k 个最强值
     * 注意：题中的中位数是 arr[(n - 1) / 2] 而不是平时的中位数
     * 给你一个整数数组 arr 和一个整数 k 。
     * 设 m 为数组的中位数，只要满足下述两个前提之一，就可以判定 arr[i] 的值比 arr[j] 的值更强：
     * |arr[i] - m| > |arr[j] - m|
     * |arr[i] - m| == |arr[j] - m|，且 arr[i] > arr[j]
     * <p>
     * 请返回由数组中最强的 k 个值组成的列表。答案可以以 任意顺序 返回。
     * 执行用时：138 ms, 在所有 Java 提交中击败了17.03% 的用户
     * 内存消耗：52.1 MB, 在所有 Java 提交中击败了86.84% 的用户
     *
     * @param arr 数组
     * @param k   整数
     * @return 数组中的 k 个最强值
     */
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int m = arr[(n - 1) / 2];

        Comparator<Integer> getStrongestComparable = (o1, o2) -> {
            int a = Math.abs(o1 - m);
            int b = Math.abs(o2 - m);
            if (a == b) {
                return o2 - o1;
            }
            return b - a;
        };
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(getStrongestComparable);
        for (int a : arr) {
            priorityQueue.offer(a);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }


    /**
     * 40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。
     * <p>
     * 执行用时：7 ms, 在所有 Java 提交中击败了22.43% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了27.43% 的用户
     *
     * @param candidates 数组 candidates
     * @param target     目标数 target
     * @return candidates 中所有可以使数字和为 target 的组合
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationsum2Dfs(new ArrayList<>(), candidates, target, 0, 0);
        return combinationSum2Result;
    }

    Set<String> combinationSum2Set = new HashSet<>();
    List<List<Integer>> combinationSum2Result = new ArrayList<>();

    public void combinationsum2Dfs(List<Integer> now, int[] candidates, int target, int sum, int k) {
        if (target == sum) {
            StringBuilder stringBuilder = new StringBuilder();
            now.forEach(x -> stringBuilder.append(x).append(","));
            if (combinationSum2Set.add(stringBuilder.toString())) {
                combinationSum2Result.add(new ArrayList<>(now));
            }
            return;
        }
        for (int i = k; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            now.add(candidates[i]);
            combinationsum2Dfs(now, candidates, target, sum + candidates[i], i + 1);
            now.remove(now.size() - 1);
        }
    }

    /**
     * 1446. 连续字符
     * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
     * 请你返回字符串的能量。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了18.13% 的用户
     *
     * @param s 字符串
     * @return 字符串的能量
     */
    public int maxPower(String s) {
        int now = 0;
        char pre = s.charAt(0);
        int max = 0;
        for (char t : s.toCharArray()) {
            if (pre == t) {
                now++;
            } else {
                max = Math.max(max, now);
                now = 1;
                pre = t;
            }
        }
        return Math.max(max, now);
    }

    /**
     * 1447. 最简分数
     * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
     * <p>
     * 执行用时：33 ms, 在所有 Java 提交中击败了42.93% 的用户
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了40.81% 的用户
     *
     * @param n 整数 n
     * @return 所有0到1之间（不包括0和1）满足分母小于等于n的最简分数
     */
    public List<String> simplifiedFractions(int n) {
        Set<Double> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (set.add((double) j / (double) i)) {
                    ans.add(j + "/" + i);
                }
            }
        }
        return ans;
    }


    /**
     * 1448. 统计二叉树中好节点的数目
     * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
     * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：48.6 MB, 在所有 Java 提交中击败了53.36% 的用户
     *
     * @param root 一棵根为 root 的二叉树
     * @return 二叉树中好节点的数目
     */
    public int goodNodes(TreeNode root) {
        goodNodesDfs(root, -10001);
        return goodNodesNumber;
    }

    int goodNodesNumber = 0;

    public void goodNodesDfs(TreeNode root, int max) {
        if (root == null) {
            return;
        }
        if (root.val >= max) {
            goodNodesNumber++;
            max = root.val;
        }
        goodNodesDfs(root.left, max);
        goodNodesDfs(root.right, max);
    }


    /**
     * 1449. 数位成本和为目标值的最大数字
     * 给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
     * 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
     * 总成本必须恰好等于 target 。
     * 添加的数位中没有数字 0 。
     * 由于答案可能会很大，请你以字符串形式返回。
     * 如果按照上述要求无法得到任何整数，请你返回 "0" 。
     * <p>
     * 执行用时：60 ms, 在所有 Java 提交中击败了61.75% 的用户
     * 内存消耗：50.2 MB, 在所有 Java 提交中击败了75.86% 的用户
     *
     * @param cost   整数数组 cost
     * @param target 整数 target
     * @return 最大 整数
     */
    public String largestNumber(int[] cost, int target) {
        // cst -->  number
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = cost.length - 1; i >= 0; i--) {
            //cost数组中，相同的数字只留下下标大的数
            if (!map.containsKey(cost[i])) {
                map.put(cost[i], i + 1);
            }
        }
        //dp[i]表示cost为i时最大数字
        String[] dp = new String[target + 1];
        dp[0] = "";
        for (int i = 1; i <= target; i++) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int cst = entry.getKey();
                if (cst <= i && dp[i - cst] != null) {
                    // 如果选择花掉这个成本，得到的数字
                    String b = dp[i - cst] + entry.getValue();
                    dp[i] = largestNumberCompare(dp[i], b);
                }
            }
        }
        return dp[target] == null ? "0" : dp[target];
    }

    //比较两个数的大小
    public String largestNumberCompare(String a, String b) {
        if (a == null) {
            return b;
        }
        if (a.length() > b.length()) {
            return a;
        }
        if (a.length() != b.length()) {
            return b;
        }
        if (a.compareTo(b) > 0) {
            return a;
        }
        return b;
    }

    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.4 MB, 在所有 Java 提交中击败了11.10% 的用户
     *
     * @param k 数
     * @param n 数
     * @return 所有相加之和为 n 的 k 个数的组合
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k > 0 && k < 10 && n <= (19 - k) * k / 2 && n >= (k + 1) * k / 2) {
            combinationSum3Dfs(new ArrayList<>(), 1, n, k);
        }
        return combinationSum3Result;
    }

    List<List<Integer>> combinationSum3Result = new ArrayList<>();

    public void combinationSum3Dfs(List<Integer> now, int nowKey, int nowValue, int p) {
        if (now.size() == p - 1) {
            if (nowValue >= nowKey && nowValue <= 9) {
                List<Integer> tmp = new ArrayList<>(now);
                tmp.add(nowValue);
                combinationSum3Result.add(tmp);
            }
            return;
        }
        for (int i = nowKey; i < 10; i++) {
            if (nowValue <= i) {
                break;
            }
            now.add(i);
            combinationSum3Dfs(now, i + 1, nowValue - i, p);
            now.remove(now.size() - 1);
        }
    }


    /**
     * 1436. 旅行终点站
     * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
     * 题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了62.26% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了56.07% 的用户
     *
     * @param paths 旅行线路
     * @return 旅行终点站
     */
    public String destCity(List<List<String>> paths) {
        List<String> to = new ArrayList<>();
        paths.forEach(x -> to.add(x.get(1)));
        paths.forEach(x -> to.remove(x.get(0)));
        return to.isEmpty() ? paths.get(0).get(0) : to.get(0);
    }

    /**
     * 1437. 是否所有 1 都至少相隔 k 个元素
     * 给你一个由若干 0 和 1 组成的数组 nums 以及整数 k。如果所有 1 都至少相隔 k 个元素，则返回 True ；否则，返回 False 。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.77% 的用户
     * 内存消耗：49.7 MB, 在所有 Java 提交中击败了69.50% 的用户
     *
     * @param nums 数组 nums
     * @param k    整数 k
     * @return 是否所有 1 都至少相隔 k 个元素
     */
    public boolean kLengthApart(int[] nums, int k) {
        if (k == 0) {
            return true;
        }
        int now = k;
        for (int n : nums) {
            if (n == 0) {
                now++;
            } else {
                if (now < k) {
                    return false;
                }
                now = 0;
            }
        }
        return true;
    }

    /**
     * 1376. 通知所有员工所需的时间
     * 公司里有 n 名员工，每个员工的 ID 都是独一无二的，编号从 0 到 n - 1。公司的总负责人通过 headID 进行标识。
     * 在 manager 数组中，每个员工都有一个直属负责人，其中 manager[i] 是第 i 名员工的直属负责人。对于总负责人，manager[headID] = -1。题目保证从属关系可以用树结构显示。
     * 公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。
     * 第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播这一消息）。
     * 返回通知所有员工这一紧急消息所需要的 分钟数 。
     * <p>
     * 执行用时：288 ms, 在所有 Java 提交中击败了14.48% 的用户
     * 内存消耗：53.3 MB, 在所有 Java 提交中击败了72.65% 的用户
     *
     * @param n          n名员工
     * @param headID     总负责人
     * @param manager    数组
     * @param informTime 数组
     * @return 通知所有员工所需的时间
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int tmp = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (informTime[i] == 0) {
                int t = i;
                while (t != headID) {
                    t = manager[t];
                    tmp += informTime[t];
                }
                max = Math.max(max, tmp);
                tmp = 0;
            }
        }
        return max;
    }

    /**
     * 1424. 对角线遍历 II
     * 给你一个列表 nums ，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回 nums 中对角线上的整数。
     * <p>
     * 执行用时：170 ms, 在所有 Java 提交中击败了11.83% 的用户
     * 内存消耗：64.1 MB, 在所有 Java 提交中击败了83.09% 的用户
     *
     * @param nums 列表
     * @return 按顺序返回 nums 中对角线上的整数
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int len = 0;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < nums.size(); i++) {
            // 获取最后要返回的数组的长度，即元素个数
            len += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); j++) {
                if (map.containsKey(i + j)) {
                    map.get(i + j).add(nums.get(i).get(j));
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums.get(i).get(j));
                    map.put(i + j, list);
                }
            }
        }
        int[] ans = new int[len];
        int index = 0;
        // 遍历map
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            // 根据题目的输出要求确定生成数组中元素的顺序
            for (int j = list.size() - 1; j >= 0; j--) {
                ans[index] = list.get(j);
                index++;
            }
        }
        return ans;
    }

    /**
     * 1405. 最长快乐字符串
     * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
     * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
     * s 是一个尽可能长的快乐字符串。
     * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
     * s 中只含有 'a'、'b' 、'c' 三种字母。
     * <p>
     * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了93.39% 的用户
     *
     * @param a a个'a'
     * @param b b个'b'
     * @param c c个'c'
     * @return 最长快乐字符串
     */
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        longestDiverseStringArr[0] = a;
        longestDiverseStringArr[1] = b;
        longestDiverseStringArr[2] = c;
        char pre = 'd';
        int preNum = 0;
        while (longestDiverseStringArr[0] != 0 || longestDiverseStringArr[1] != 0 || longestDiverseStringArr[2] != 0) {
            if (preNum == 2) {
                int t = longestDiverseStringMax(longestDiverseStringArr);
                char tmp = (char) (t + 'a');
                if (pre == tmp) {
                    int tt = longestDiverseStringBigger(longestDiverseStringArr, t);
                    longestDiverseStringArr[tt]--;
                    if (longestDiverseStringArr[tt] < 0) {
                        break;
                    }
                    pre = (char) (tt + 'a');
                    sb.append(pre);
                    preNum = 1;
                } else {
                    longestDiverseStringArr[t]--;
                    if (longestDiverseStringArr[t] < 0) {
                        break;
                    }
                    sb.append(tmp);
                    preNum = 1;
                }
            } else {
                int t = longestDiverseStringMax(longestDiverseStringArr);
                char tmp = (char) (t + 'a');
                longestDiverseStringArr[t]--;
                if (longestDiverseStringArr[t] < 0) {
                    break;
                }
                sb.append(tmp);
                if (pre != tmp) {
                    preNum = 0;
                    pre = tmp;
                }
                preNum++;
            }
        }
        return sb.toString();
    }

    private int[] longestDiverseStringArr = new int[3];

    private int longestDiverseStringMax(int[] arr) {
        if (arr[0] >= arr[1] && arr[0] >= arr[2]) {
            return 0;
        } else if (arr[1] >= arr[2]) {
            return 1;
        } else {
            return 2;
        }
    }

    private int longestDiverseStringBigger(int[] arr, int t) {
        if (t == 0) {
            return arr[1] >= arr[2] ? 1 : 2;
        } else if (t == 1) {
            return arr[0] >= arr[2] ? 0 : 2;
        } else {
            return arr[1] >= arr[0] ? 1 : 0;
        }
    }


    /**
     * 37. 解数独
     * 编写一个程序，通过已填充的空格来解决数独问题。
     * 一个数独的解法需遵循如下规则：
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 空白格用 '.' 表示。
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了74.85% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了99.44% 的用户
     *
     * @param board 数组
     */
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' != board[i][j]) {
                    int t = board[i][j] - '1';
                    solveSudokuX[i][t] = true;
                    solveSudokuY[j][t] = true;
                    solveSudokuZ[i / 3][j / 3][t] = true;
                } else {
                    solveSudokuL.add(new int[]{i, j});
                }
            }
        }
        solveSudokuDfs(0, board);
    }

    boolean[][] solveSudokuX = new boolean[9][9];
    boolean[][] solveSudokuY = new boolean[9][9];
    boolean[][][] solveSudokuZ = new boolean[3][3][9];
    List<int[]> solveSudokuL = new ArrayList<>();

    public boolean solveSudokuDfs(int p, char[][] board) {
        if (solveSudokuL.size() == p) {
            return true;
        }
        int[] tmp = solveSudokuL.get(p);
        int xx = tmp[0];
        int yy = tmp[1];
        for (int t = 0; t < 9; t++) {
            if (!solveSudokuX[xx][t] && !solveSudokuY[yy][t] && !solveSudokuZ[xx / 3][yy / 3][t]) {
                solveSudokuX[xx][t] = true;
                solveSudokuY[yy][t] = true;
                solveSudokuZ[xx / 3][yy / 3][t] = true;
                board[xx][yy] = (char) (t + '1');
                if (!solveSudokuDfs(p + 1, board)) {
                    solveSudokuX[xx][t] = false;
                    solveSudokuY[yy][t] = false;
                    solveSudokuZ[xx / 3][yy / 3][t] = false;
                    board[xx][yy] = '.';
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 面试题 02.05. 链表求和
     * 给定两个用链表表示的整数，每个节点包含一个数位。
     * 这些数位是反向存放的，也就是个位排在链表首部。
     * 编写函数对这两个整数求和，并用链表形式返回结果。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了93.50% 的用户
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 链表求和
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        int tmp = 0;
        ListNode p = res;
        while (l1 != null && l2 != null) {
            int a = l1.val;
            int b = l2.val;
            ListNode ll = new ListNode((a + b + tmp) % 10);
            p.next = ll;
            p = p.next;
            tmp = (a + b + tmp) / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int a = l1.val;
            ListNode ll = new ListNode((a + tmp) % 10);
            tmp = (a + tmp) / 10;
            p.next = ll;
            p = p.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int a = l2.val;
            ListNode ll = new ListNode((a + tmp) % 10);
            tmp = (a + tmp) / 10;
            p.next = ll;
            p = p.next;
            l2 = l2.next;
        }
        if (tmp != 0) {
            ListNode ll = new ListNode(tmp);
            p.next = ll;
        }
        return res.next;
    }

    /**
     * 面试题 02.06. 回文链表
     * 编写一个函数，检查输入的链表是否是回文的。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.97% 的用户
     * 内存消耗：42.1 MB, 在所有 Java 提交中击败了94.35% 的用户
     *
     * @param head 链表
     * @return 是否是回文链表
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode p = slow.next;
        ListNode q = null;
        while (p != null) {
            ListNode pp = p.next;
            p.next = q;
            q = p;
            p = pp;
        }
        while (head != null && q != null) {
            if (head.val != q.val) {
                return false;
            }
            q = q.next;
            head = head.next;
        }
        return true;
    }

    /**
     * 面试题 02.07. 链表相交
     * 给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.94% 的用户
     * 内存消耗：42.5 MB, 在所有 Java 提交中击败了73.19% 的用户
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 相交链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        ListNode ha = headA;
        while (ha != null) {
            ha = ha.next;
            lenA++;
        }
        ListNode hb = headB;
        while (hb != null) {
            hb = hb.next;
            lenB++;
        }
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        while (headA != null) {
            if (headA.equals(headB)) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 面试题 02.08. 环路检测
     * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
     * 有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。
     * <p>
     * 解析：要理解的是，当快慢指针相遇时，快指针比慢指针多走了环长度的整数倍，而且快指针比慢指针多走的和慢指针走的一样，
     * 所以慢指针走的步数也是环长度的整数倍！此时让快指针再从头走，速度和慢指针一样，两指针肯定会在环入口相遇
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了16.99% 的用户
     *
     * @param head 指针
     * @return 环路检测结果
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 面试题 04.04. 检查平衡性
     * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了64.06% 的用户
     *
     * @param root 二叉树
     * @return 二叉树是否平衡
     */
    public boolean isBalanced(TreeNode root) {
        isBalancedLen(root);
        return isBalancedFlag;
    }

    private boolean isBalancedFlag = true;

    public int isBalancedLen(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = isBalancedLen(root.left) + 1;
        int r = isBalancedLen(root.right) + 1;
        if (Math.abs(l - r) > 1) {
            isBalancedFlag = false;
        }
        return Math.max(l, r);
    }

    /**
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了90.36% 的用户
     *
     * @param root 二叉树
     * @return 翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 面试题 04.05. 合法二叉搜索树
     * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了42.68% 的用户
     *
     * @param root 二叉树
     * @return 二叉树是否为二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBSTAss(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTAss(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBSTAss(root.left, min, root.val) && isValidBSTAss(root.right, root.val, max);
    }


    /**
     * 面试题 04.06. 后继者
     * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     * 如果指定节点没有对应的“下一个”节点，则返回null。
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了38.00% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了32.46% 的用户
     *
     * @param root 树
     * @param p    节点
     * @return 节点的后继者
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.equals(root)) {
            //TODO
            TreeNode tmp = root.right;
            while (tmp != null && tmp.left != null) {
                tmp = tmp.left;
            }
            return tmp == null ? inorderSuccessorRes : tmp;
        }
        if (p.val < root.val) {
            inorderSuccessorRes = inorderSuccessor(root.left, p);
            if (inorderSuccessorRes == null) {
                inorderSuccessorRes = root;
            }
        } else {
            inorderSuccessorRes = inorderSuccessor(root.right, p);
        }
        return inorderSuccessorRes;
    }

    private TreeNode inorderSuccessorRes = null;


    /**
     * 面试题 04.08. 首个共同祖先
     * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
     * <p>
     * 执行用时：9 ms, 在所有 Java 提交中击败了28.55% 的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了96.80% 的用户
     *
     * @param root 二叉树
     * @param p    节点1
     * @param q    节点2
     * @return 节点的首个共同祖先
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pParents = new ArrayList<>();
        List<TreeNode> qParents = new ArrayList<>();
        lowestCommonAncestorDfs(root, p, pParents);
        lowestCommonAncestorDfs(root, q, qParents);
        int pLen = pParents.size() - 1;
        int qLen = qParents.size() - 1;
        while (pLen > qLen) {
            pParents.remove(pLen--);
        }
        while (pLen < qLen) {
            qParents.remove(qLen--);
        }
        while (!pParents.isEmpty()) {
            TreeNode tmp = pParents.remove(pLen);
            if (tmp.equals(qParents.remove(pLen--))) {
                return tmp;
            }
        }
        return root;
    }

    public boolean lowestCommonAncestorDfs(TreeNode root, TreeNode child, List<TreeNode> parents) {
        if (root.equals(child)) {
            return true;
        }
        if (root.left != null) {
            parents.add(root.left);
            if (lowestCommonAncestorDfs(root.left, child, parents)) {
                return true;
            }
            parents.remove(parents.size() - 1);
        }
        if (root.right != null) {
            parents.add(root.right);
            if (lowestCommonAncestorDfs(root.right, child, parents)) {
                return true;
            }
            parents.remove(parents.size() - 1);
        }
        return false;
    }


    /**
     * 685. 冗余连接 II
     * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
     * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
     * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.70% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了38.76% 的用户
     *
     * @param edges 边集合
     * @return 冗余边
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        findRedundantDirectedConnectionHadRoot = new int[edges.length + 1];
        int[] father = new int[edges.length + 1];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }

        for (int[] edge : edges) {
            findRedundantDirectedConnectionHadRoot[edge[1]]++;
            if (findRedundantDirectedConnectionHadRoot[edge[1]] == 2) {
                findRedundantDirectedConnectionDoubleRoot = edge[1];
                findRedundantDirectedConnectionRootResult[1] = edge;
            } else {
                union(father, edge[1], edge[0]);
            }
        }
        if (findRedundantDirectedConnectionDoubleRoot != 0) {
            for (int[] edge : edges) {
                if (edge[1] == findRedundantDirectedConnectionDoubleRoot) {
                    findRedundantDirectedConnectionRootResult[0] = edge;
                    break;
                }
            }
            int root = 0;
            for (int i = 1; i < father.length; i++) {
                if (root == 0) {
                    root = findXFather(father, i);
                }
                if (root != findXFather(father, i)) {
                    return findRedundantDirectedConnectionRootResult[0];
                }
            }
            return findRedundantDirectedConnectionRootResult[1];
        }
        return findRedundantDirectedConnectionResult;
    }


    int[] findRedundantDirectedConnectionResult = new int[2];
    int findRedundantDirectedConnectionDoubleRoot = 0;
    int[] findRedundantDirectedConnectionHadRoot;
    int[][] findRedundantDirectedConnectionRootResult = new int[2][2];


    public int findXFather(int[] father, int x) {
        while (father[x] != x) {
            father[x] = father[father[x]];
            x = father[x];
        }
        return x;
    }

    public void union(int[] father, int x, int y) {
        int xFather = findXFather(father, x);
        int yFather = findXFather(father, y);
        if (xFather != yFather) {
            father[xFather] = yFather;
        } else {
            findRedundantDirectedConnectionResult[0] = y;
            findRedundantDirectedConnectionResult[1] = x;
        }
    }

    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * <p>
     * 执行用时：88 ms, 在所有 Java 提交中击败了9.48% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了21.92% 的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        permuteUniqueTaked = new boolean[nums.length];
        permuteUnique(nums, new ArrayList<>());
        return permuteUniqueRes;
    }

    List<List<Integer>> permuteUniqueRes = new ArrayList<>();
    boolean[] permuteUniqueTaked;
    Set<String> permuteUniqueSet = new HashSet<>();

    public void permuteUnique(int[] nums, List<Integer> arr) {
        if (arr.size() == nums.length) {
            StringBuilder stringBuilder = new StringBuilder();
            arr.forEach(x -> stringBuilder.append(x));
            if (!permuteUniqueSet.contains(stringBuilder.toString())) {
                permuteUniqueRes.add(new ArrayList<>(arr));
                permuteUniqueSet.add(stringBuilder.toString());
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (permuteUniqueTaked[i]) {
                continue;
            }
            permuteUniqueTaked[i] = true;
            arr.add(nums[i]);
            permuteUnique(nums, arr);
            arr.remove(arr.size() - 1);
            permuteUniqueTaked[i] = false;
        }
    }


    /**
     * 404. 左叶子之和
     * 计算给定二叉树的所有左叶子之和。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了36.56% 的用户
     *
     * @param root 二叉树
     * @return 二叉树的左叶子之和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumOfLeftLeavesAss(root, false);
        return sumOfLeftLeavesRes;
    }

    private int sumOfLeftLeavesRes = 0;

    public void sumOfLeftLeavesAss(TreeNode root, boolean isLeft) {
        if (root.left == null && root.right == null) {
            sumOfLeftLeavesRes += isLeft ? root.val : 0;
            return;
        }
        if (root.left != null) {
            sumOfLeftLeavesAss(root.left, true);
        }
        if (root.right != null) {
            sumOfLeftLeavesAss(root.right, false);
        }
    }

    /**
     * 78. 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.39% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了55.59% 的用户
     *
     * @param nums 整数数组 nums
     * @return 该数组所有可能的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        subsetsAss(nums, new ArrayList<>(), 0);
        return subsetsResList;
    }

    private List<List<Integer>> subsetsResList = new ArrayList<>();

    public void subsetsAss(int[] nums, List<Integer> list, int t) {
        for (int i = t; i < nums.length; i++) {
            list.add(nums[i]);
            subsetsAss(nums, list, i + 1);
            list.remove(list.size() - 1);
        }
        subsetsResList.add(new ArrayList<>(list));
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.82% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了48.48% 的用户
     *
     * @param root 二叉搜索树
     * @return 累加树
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        convertBSTAss(root);
        return root;
    }

    private int convertBSTSum = 0;

    public void convertBSTAss(TreeNode root) {
        if (root.right != null) {
            convertBSTAss(root.right);
        }
        root.val += convertBSTSum;
        convertBSTSum = root.val;
        if (root.left != null) {
            convertBSTAss(root.left);
        }
    }

    /**
     * 968. 监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * 计算监控树的所有节点所需的最小摄像头数量。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了55.79% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了85.08% 的用户
     *
     * @param root 二叉树
     * @return 最小摄像头数量
     */
    public int minCameraCover(TreeNode root) {
        int tmp = minCameraCoverDfs(root);
        if (tmp != 3) {
            minCameraCoverMonitors.add(root);
        }
        return minCameraCoverMonitors.size();
    }

    private Set<TreeNode> minCameraCoverMonitors = new HashSet<>();

    public int minCameraCoverDfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            // 子节点是叶子节点,非监控但是需要被监控
            return 1;
        }
        boolean isMonitor = false;
        int l = 0;
        if (root.left != null) {
            l = minCameraCoverDfs(root.left);
        }
        int r = 0;
        if (root.right != null) {
            r = minCameraCoverDfs(root.right);
        }
        if (r == 1 || l == 1) {
            // 子节点非监控但是需要被监控
            minCameraCoverMonitors.add(root);
            return 2;
        } else if (l == 2 || r == 2) {
            // 2子节点是监控
            return 3;
        } else {
            // 子节点非监控但是不需要被监控
            return 1;
        }
    }

    /**
     * 927. 三等分
     * 给定一个由 0 和 1 组成的数组 arr，将数组分成 3 个非空的部分，使得所有这些部分表示相同的二进制值。
     * 如果可以做到，请返回任何 [i, j]，其中 i+1 < j，这样一来：
     * A[0], A[1], ..., A[i] 组成第一部分；
     * A[i+1], A[i+2], ..., A[j-1] 作为第二部分；
     * A[j], A[j+1], ..., A[A.length - 1] 是第三部分。
     * 这三个部分所表示的二进制值相等。
     * 如果无法做到，就返回 [-1, -1]。
     * 注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体。例如，[1,1,0] 表示十进制中的 6，而不会是 3。此外，前导零也是被允许的，所以 [0,1,1] 和 [1,1] 表示相同的值。
     * <p>
     * 执行用时：239 ms, 在所有 Java 提交中击败了5.88% 的用户
     * 内存消耗：46.5 MB, 在所有 Java 提交中击败了6.25% 的用户
     *
     * @param arr 0 和 1 组成的数组
     * @return 三等分
     */
    public int[] threeEqualParts(int[] arr) {
        int numOfOne = 0;
        for (int a : arr) {
            if (a == 1) {
                numOfOne++;
            }
        }
        if (numOfOne % 3 != 0) {
            return new int[]{-1, -1};
        }
        if (numOfOne == 0) {
            return new int[]{0, 2};
        }
        int tmp = numOfOne / 3;
        StringBuilder key = new StringBuilder();
        int i = 0;
        int n = 0;
        boolean begin = false;
        // 寻找key前半部分
        while (n < tmp) {
            if (!begin && arr[i] == 0) {

            } else {
                key.append(arr[i]);
                begin = true;
            }
            if (arr[i++] == 1) {
                n++;
            }
        }
        // 寻找key后半部分
        int j = arr.length - 1;
        while (arr[j] == 0) {
            j--;
            if (arr[i] == 1) {
                return new int[]{-1, -1};
            }
            key.append(arr[i++]);
        }
        StringBuilder value = new StringBuilder();
        int t = 0;
        int resI = i - 1;
        begin = false;
        for (; i < arr.length; i++) {
            if (!begin && arr[i] == 0) {

            } else {
                value.append(arr[i]);
                begin = true;
            }
            if (value.toString().equals(key.toString())) {
                if (t == 0) {
                    j = i + 1;
                }
                value = new StringBuilder();
                begin = false;
                t++;
            }
        }
        if (t == 2) {
            return new int[]{resI, j};
        } else {
            return new int[]{-1, -1};
        }
    }

    /**
     * 617. 合并二叉树
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了75.13% 的用户
     *
     * @param t1 二叉树
     * @param t2 二叉树
     * @return 合并二叉树
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 != null) {
            t1.val += t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
        }
        return t1;
    }

    /**
     * 645. 错误的集合
     * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.23% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了94.79% 的用户
     * <p>
     * 用异或寻找更佳哦
     *
     * @param nums 数组
     * @return 错误的集合
     */
    public int[] findErrorNums(int[] nums) {
        boolean[] booleans = new boolean[nums.length];
        int[] res = new int[2];
        for (int n : nums) {
            if (!booleans[n - 1]) {
                booleans[n - 1] = true;
            } else {
                res[0] = n;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (!booleans[i]) {
                res[1] = i + 1;
                break;
            }
        }
        return res;
    }


    /**
     * 面试题 16.01. 交换数字
     * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了75.86% 的用户
     *
     * @param numbers 数组
     * @return 交换数字之后的数组
     */
    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }


    /**
     * 面试题 16.05. 阶乘尾数
     * 设计一个算法，算出 n 阶乘有多少个尾随零。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.35% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了28.25% 的用户
     *
     * @param n 数字
     * @return 阶乘尾数
     */
    public int trailingZeroes(int n) {
        if (n == 0) {
            return 0;
        }
        int a = 0;
        while (n > 0) {
            n = n / 5;
            a += n;
        }
        return a;
    }

    /**
     * 面试题 10.11. 峰与谷
     * <p>
     * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
     * 例如，在数组{5, 8, 6, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了88.52% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了5.18% 的用户
     *
     * @param nums 整数数组
     */
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (((i & 1) == 0 && nums[i] < nums[i - 1]) || ((i & 1) == 1 && nums[i] > nums[i - 1])) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
        }
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了27.13% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了41.77% 的用户
     *
     * @param inorder   中序遍历
     * @param postorder 后序遍历
     * @return 构造二叉树
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length - 1,
                0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder, int inorderL, int inorderR,
                              int postorderL, int postorderR) {
        if (inorderL > inorderR) {
            return null;
        }
        if (inorderL == inorderR) {
            return new TreeNode(inorder[inorderL]);
        }
        int i = inorderL;
        for (; i <= inorderR; i++) {
            if (inorder[i] == postorder[postorderR]) {
                break;
            }
        }
        TreeNode treeNode = new TreeNode(postorder[postorderR]);
        treeNode.left = buildTree(inorder, postorder, inorderL, i - 1,
                postorderL, postorderL + i - inorderL - 1);
        treeNode.right = buildTree(inorder, postorder, i + 1, inorderR,
                postorderR + i - inorderR, postorderR - 1);
        return treeNode;
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了14.89% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了35.71% 的用户
     *
     * @param root 二叉树
     * @return 打印出二叉树的每个节点
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            res.add(tmp.val);
            if (tmp.left != null) {
                queue.add(tmp.left);
            }
            if (tmp.right != null) {
                queue.add(tmp.right);
            }
        }
        int[] re = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            re[i] = res.get(i);
        }
        return re;
    }

    /**
     * 面试题 01.09. 字符串轮转
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了23.05% 的用户
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return s2是否为s1旋转而成
     */
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }


    /**
     * 113. 路径总和 II
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了9.55% 的用户
     *
     * @param root 二叉树
     * @param sum  目标和
     * @return 所有从根节点到叶子节点路径总和等于给定目标和的路径s
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return pathSumRes;
        }
        pathSumAss(root, 0, new ArrayList<>(), sum);
        return pathSumRes;
    }

    private List<List<Integer>> pathSumRes = new ArrayList<>();

    public void pathSumAss(TreeNode root, int sum, List<Integer> list, int target) {
        if (root.left == null && root.right == null) {
            if (sum + root.val == target) {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(root.val);
                pathSumRes.add(newList);
            }
            return;
        }
        list.add(root.val);
        sum += root.val;
        if (root.left != null) {
            pathSumAss(root.left, sum, list, target);
        }
        if (root.right != null) {
            pathSumAss(root.right, sum, list, target);
        }

        list.remove(list.size() - 1);
        sum -= root.val;
    }


    /**
     * 1568. 使陆地分离的最少天数
     * 给你一个由若干 0 和 1 组成的二维网格 grid ，其中 0 表示水，而 1 表示陆地。岛屿由水平方向或竖直方向上相邻的 1 （陆地）连接形成。
     * 如果 恰好只有一座岛屿 ，则认为陆地是 连通的 ；否则，陆地就是 分离的 。
     * 一天内，可以将任何单个陆地单元（1）更改为水单元（0）。
     * 返回使陆地分离的最少天数。
     * <p>
     * 执行用时：911 ms, 在所有 Java 提交中击败了5.15% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了5.18% 的用户
     *
     * @param grid 二维网格
     * @return 使陆地分离的最少天数
     */
    public int minDays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int numOfOne = 0;
        int testI = -1;
        int testJ = -1;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    set.add(i + "," + j);
                    numOfOne++;
                    testI = i;
                    testJ = j;
                }
            }
        }
        if (numOfOne <= 1) {
            return numOfOne;
        }
        minDaysDFS(testI, testJ, new HashSet<>(), grid);
        if (minDaysNum != numOfOne) {
            return 0;
        }
        int tesI = testI;
        int tesJ = testJ;
        for (String str : set) {
            String[] strings = str.split(",");
            tesI = Integer.parseInt(strings[0]);
            tesJ = Integer.parseInt(strings[1]);
            if (tesI != testI || tesJ != testJ) {
                break;
            }
        }
        for (String str : set) {
            String[] strings = str.split(",");
            int i = Integer.valueOf(strings[0]);
            int j = Integer.valueOf(strings[1]);
            grid[i][j] = 0;
            minDaysNum = 1;
            if (i != testI || j != testJ) {
                minDaysDFS(testI, testJ, new HashSet<>(), grid);
            } else {
                minDaysDFS(tesI, tesJ, new HashSet<>(), grid);
            }
            grid[i][j] = 1;
            if (minDaysNum != numOfOne - 1) {
                return 1;
            }
        }
        return 2;
    }

    private int[][] minDaysDir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int minDaysNum = 1;

    public void minDaysDFS(int beginI, int beginJ, Set<String> visit, int[][] grid) {
        visit.add(beginI + "," + beginJ);
        for (int i = 0; i < 4; i++) {
            int nowI = beginI + minDaysDir[i][0];
            int nowJ = beginJ + minDaysDir[i][1];
            if (nowI < 0 || nowI >= grid.length || nowJ < 0 || nowJ >= grid[0].length) {
                continue;
            }
            if (grid[nowI][nowJ] == 0) {
                continue;
            }
            if (visit.add(nowI + "," + nowJ)) {
                minDaysNum++;
                minDaysDFS(nowI, nowJ, visit, grid);
            }
        }
    }
}
