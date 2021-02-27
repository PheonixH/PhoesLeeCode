package leetcode;

import leetcode.dataStruct.ListNode;
import leetcode.dataStruct.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * @program: PhoesLeeCode
 * @className: Main
 * @description: Run Test
 * @author: lov.moran
 * @date 2020-09-10 16:06
 */
public class Main {

    public static ListNode createListNode(int[] listNodeValue) {
        //ListNode
        int listNodeLen = listNodeValue.length;
        ListNode head = new ListNode(listNodeValue[0]);
        ListNode listNodeTmp = head;
        for (int i = 1; i < listNodeLen; i++) {
            listNodeTmp.next = new ListNode(listNodeValue[i]);
            listNodeTmp = listNodeTmp.next;
        }
        return head;
    }

    public static TreeNode createTreeNode(int[] treeNodeValue) {
        //TreeNode
        //-101 is null
        Queue<TreeNode> createTreeNodeStack = new LinkedList<>();
        if (treeNodeValue.length <= 0 || -101 == treeNodeValue[0]) {
            return null;
        }
        TreeNode root = new TreeNode(treeNodeValue[0]);
        createTreeNodeStack.add(root);
        for (int i = 1; i < treeNodeValue.length; i++) {
            if (createTreeNodeStack.isEmpty()) {
                break;
            }
            TreeNode tmp = createTreeNodeStack.poll();
            if (treeNodeValue[i] == -1) {
                tmp.left = null;
            } else {
                tmp.left = new TreeNode(treeNodeValue[i]);
                createTreeNodeStack.add(tmp.left);
            }
            i++;
            if (i >= treeNodeValue.length) {
                break;
            }
            if (treeNodeValue[i] == -1) {
                tmp.right = null;
            } else {
                tmp.right = new TreeNode(treeNodeValue[i]);
                createTreeNodeStack.add(tmp.right);
            }
        }

        return root;
    }

    public static List<List<Integer>> createListListInteger(int[][] listListIntegerArray) {
        List<List<Integer>> integerListList = new LinkedList<>();
        for (int[] listIntegerArray : listListIntegerArray) {
            List<Integer> collect = Arrays.stream(listIntegerArray).boxed().collect(Collectors.toList());
            integerListList.add(collect);
        }
        return integerListList;
    }

    public static <T> List<List<T>> createListList(T[][] listListArray) {
        List<List<T>> stringListList = new LinkedList<>();
        for (T[] listStringArray : listListArray) {
            List<T> collect = Arrays.stream(listStringArray).collect(Collectors.toList());
            stringListList.add(collect);
        }
        return stringListList;
    }


    public static void main(String[] args) {

        //Arrays
        String[] oneDimensionalStringArray = {"bella", "label", "roller"};
        int[] oneDimensionalArrayA = {-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648};
        int[] oneDimensionalArrayB = {14, 12, 10, 8, 6, 4, 2};
        int[][] twoDimensionalArray = {{0, 2, 2}, {4, 2, 4}, {2, 13, 1000000000}};
        char[] oneDimensionalCharArray = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'};
        char[][] twoDimensionalCharArray = {
                {'0', '0', '0', '0', '0', '0', '1'},
                {'0', '0', '0', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1'},
                {'0', '0', '0', '1', '1', '1', '1'}};


        //List<List<Integer>>
        int[][] listListIntegerArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<List<Integer>> intListList = createListListInteger(listListIntegerArray);

        //List<List<String>> 非Integer
        String[][] listListStringArray = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<List<String>> stringListList = createListList(listListStringArray);

        int[] listNodeValue = {0, 1, 2, 3, 4};
        ListNode head = createListNode(listNodeValue);

        int[] listNodeValue2 = {11, 12, 13, 14};
        ListNode head2 = createListNode(listNodeValue);

        int[] treeNodeValue = {1, -2, -3, 1, 3, -2, -101, -1};
        TreeNode root = createTreeNode(treeNodeValue);

        // 手动输入数组 ---  测试数据太长了
//        Scanner sc = new Scanner(System.in);
//        String string = sc.next();
//        String[] arr = string.split(",");
//        int[] brr = new int[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            brr[i] = Integer.valueOf(arr[i]);
//        }
        String[] arr = {"hot", "dot", "dog"};
        List<String> list = new ArrayList<>();
        for (String a : arr) {
            list.add(a);
        }

        SolutionNow solution = new SolutionNow();
        solution.longestSubstring("ababbc", 2);
//        SolitionUbuntu solitionUbuntu = new SolitionUbuntu();
//        solitionUbuntu.isIsomorphic("ad", "aa");

        Games games = new Games();
        games.checkPartitioning("aabad");
    }
}
