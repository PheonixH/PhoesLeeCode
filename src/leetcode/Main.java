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

    public static void main(String[] args) {

        //ListNode
        int[] listNodeValue = {1, 2, 3, 4, 5};
        int listNodeLen = listNodeValue.length;
        ListNode head = new ListNode(listNodeValue[0]);
        ListNode listNodeTmp = head;
        for (int i = 1; i < listNodeLen; i++) {
            listNodeTmp.next = new ListNode(listNodeValue[i]);
            listNodeTmp = listNodeTmp.next;
        }

        //TreeNode
        //-1 is null
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
        int[] oneDimensionalArrayA = {5, 6, 7, 3, 4, 6, 7, 4, 8};
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
        List<List<Integer>> integerListList = new LinkedList<>();
        for (int[] listIntegerArray : listListIntegerArray) {
            List<Integer> collect = Arrays.stream(listIntegerArray).boxed().collect(Collectors.toList());
            integerListList.add(collect);
        }

        //List<List<String>>
        String[][] listListStringArray = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<List<String>> stringListList = new LinkedList<>();
        for (String[] listStringArray : listListStringArray) {
            List<String> collect = Arrays.stream(listStringArray).collect(Collectors.toList());
            stringListList.add(collect);
        }


        SolutionNow solution = new SolutionNow();
        Games games = new Games();

        System.out.println(solution.largestNumber(oneDimensionalArrayA, 29));

        System.out.println(games.addToArrayForm(oneDimensionalArrayA, 29));
    }
}
