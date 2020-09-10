package leetcode;

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


}