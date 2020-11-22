package leetcode;

import leetcode.datestruct.ListNode;
import leetcode.datestruct.TreeNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        //-1 is null
        Queue<TreeNode> createTreeNodeStack = new LinkedList<>();
        if (treeNodeValue.length <= 0 || -1 == treeNodeValue[0]) {
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
        int[] oneDimensionalArrayA = {43024, 99908};
        int[] oneDimensionalArrayB = {1864};
        int[][] twoDimensionalArray = {{1, 3}, {2, 4}, {10, 11}, {10, 12}, {8, 9}};
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
        int[][] listListIntegerArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<List<Integer>> intListList = createListListInteger(listListIntegerArray);

        //List<List<String>> 非Integer
        String[][] listListStringArray = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<List<String>> stringListList = createListList(listListStringArray);

        int[] listNodeValue = {1, 2, 3, 4};
        ListNode head = createListNode(listNodeValue);

        int[] treeNodeValue = {5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, 5, 1};
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
//        solution.nextPermutation(new int[]{1, 5, 3, 4, 2});

        SolitionUbuntu solitionUbuntu = new SolitionUbuntu();
        solitionUbuntu.maxSatisfaction(new int[]{4, 3, 2});

        Games games = new Games();
        games.minimumEffort(twoDimensionalArray);
    }

}
