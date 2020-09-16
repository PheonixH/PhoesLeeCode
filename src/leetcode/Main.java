package leetcode;

import leetcode.datestruct.ListNode;
import leetcode.datestruct.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<TreeNode> createTreeNodeStack = new ArrayList<>();
        TreeNode root = new TreeNode(treeNodeValue[0]);
        createTreeNodeStack.add(root);
        for (int i = 1; i < treeNodeValue.length; i++) {
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
        String[] oneDimensionalStringArray = {"eat", "tea", "tan", "ate", "nat", "bat"};
        int[] oneDimensionalArrayA = {1, 0, 0, 0, 1, 0, 0, 1};
        int[] oneDimensionalArrayB = {5, 2, 2, 5, 3, 5};
        int[][] twoDimensionalArray = {
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
        int[][] listListIntegerArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<List<Integer>> intListList = createListListInteger(listListIntegerArray);

        //List<List<String>> 非Integer
        String[][] listListStringArray = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<List<String>> stringListList = createListList(listListStringArray);

        int[] listNodeValue = {1, 2, 3, 3, 2, 1};
        ListNode head = createListNode(listNodeValue);

        int[] treeNodeValue = {6, 2, 8, 0, 4, 7, 9, -1, -1, 3, 5};
        TreeNode root = createTreeNode(treeNodeValue);

        SolutionNow solution = new SolutionNow();
        System.out.println(solution.inorderSuccessor(root, root.left));

        Games games = new Games();
//        System.out.println(games.addToArrayForm(oneDimensionalArrayA, 29));
    }
}
