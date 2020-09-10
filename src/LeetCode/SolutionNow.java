package LeetCode;

import LeetCode.datestruct.ListNode;
import LeetCode.datestruct.TreeNode;

import java.util.*;
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
            priorityQueue.add(a);
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
     * @param target 目标数 target
     * @return candidates 中所有可以使数字和为 target 的组合
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum2DFS(new ArrayList<>(), candidates, target, 0, 0);
        return combinationSum2Result;
    }

    Set<String> combinationSum2Set = new HashSet<>();
    List<List<Integer>> combinationSum2Result = new ArrayList<>();

    public void combinationSum2DFS(List<Integer> now, int[] candidates, int target, int sum, int k) {
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
            combinationSum2DFS(now, candidates, target, sum + candidates[i], i + 1);
            now.remove(now.size() - 1);
        }
    }

    public static void main(String[] args) {
        SolutionNow solution = new SolutionNow();

        //ListNode
        int[] listNodeValue = {1, 2, 3, 4, 5};
        int listNodeLen = listNodeValue.length;
        ListNode head = new ListNode(listNodeValue[0]);
        ListNode listNodeTmp = head;
        for (int i = 1; i < listNodeLen; i++) {
            listNodeTmp.next = new ListNode(listNodeValue[i]);
            listNodeTmp = listNodeTmp.next;
        }

        //TreeNode -1 is null TreeNode;
        int[] treeNodeValue = {2, -1, 3, -1, 4, -1, 5, -1, 6};
        List<TreeNode> createTreeNodeStack = new ArrayList<>();
        TreeNode root = new TreeNode(treeNodeValue[0]);
        createTreeNodeStack.add(root);
        for (int i = 1; i < listNodeLen; i++) {
            TreeNode tmp = createTreeNodeStack.remove(0);
            if (tmp == null) {
                i++;
                continue;
            }
            if (treeNodeValue[i] == -1) {
                tmp.left = null;
            } else {
                tmp.left = new TreeNode(treeNodeValue[i]);
            }
            createTreeNodeStack.add(tmp.left);
            i++;
            if (treeNodeValue[i] == -1) {
                tmp.right = null;
            } else {
                tmp.right = new TreeNode(treeNodeValue[i]);
            }
            createTreeNodeStack.add(tmp.right);
        }


        //Arrays
        String[] oneDimensionalStringArray = {"eat", "tea", "tan", "ate", "nat", "bat"};
        int[] oneDimensionalArrayA = {-7, 22, 17, 3};
        int[] oneDimensionalArrayB = {5, 2, 2, 5, 3, 5};
        int[][] twoDimensionalArrayA = {{0, 1}};
        int[][] twoDimensionalArrayB = {
                {0, 8, 7, 10, 9, 10, 0, 9, 6},
                {8, 7, 10, 8, 7, 4, 9, 6, 10},
                {8, 1, 1, 5, 1, 5, 5, 1, 2},
                {9, 4, 10, 8, 8, 1, 9, 5, 0},
                {4, 3, 6, 10, 9, 2, 4, 8, 10},
                {7, 3, 2, 8, 3, 3, 5, 9, 8},
                {1, 2, 6, 5, 6, 2, 0, 10, 0}};
        char[] oneDimensionalCharArray = {'A', 'B'};
        char[][] twoDimensionalCharArray = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};


        //List<List<Integer>>
        int[][] listListIntegerArray = {{0, 8, 7, 10, 9, 10, 0, 9, 6}, {8, 7, 10, 8, 7, 4, 9, 6, 10}, {8, 1, 1, 5, 1, 5, 5, 1, 2}, {9, 4, 10, 8, 8, 1, 9, 5, 0}, {4, 3, 6, 10, 9, 2, 4, 8, 10}, {7, 3, 2, 8, 3, 3, 5, 9, 8}, {1, 2, 6, 5, 6, 2, 0, 10, 0}};
        List<List<Integer>> integerListList = new LinkedList();
        for (int[] listIntegerArray : listListIntegerArray) {
            List<Integer> collect = Arrays.stream(listIntegerArray).boxed().collect(Collectors.toList());
            integerListList.add(collect);
        }

        //List<List<String>>
        String[][] listListStringArray = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<List<String>> stringListList = new LinkedList();
        for (String[] listStringArray : listListStringArray) {
            List<String> collect = Arrays.stream(listStringArray).collect(Collectors.toList());
            stringListList.add(collect);
        }

        System.out.println(solution.getStrongest(oneDimensionalArrayA, 2));
        return;
    }

}
