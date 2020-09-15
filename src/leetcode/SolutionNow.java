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
}
