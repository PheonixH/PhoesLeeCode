import Template.Trie;
import data.ListNode;
import data.TreeNode;
import sun.misc.Queue;

import java.util.*;

import static java.lang.Math.min;


/**
 * @program: PhoesLeeCode
 * @className: SolutionVmware
 * @description:
 * @author: lov.moran
 * @date 2020-06-15 14:46
 */
public class SolutionVmware {
    //面试题 08.01

    /**
     * 执行用时 :33 ms, 在所有 Java 提交中击败了65.74%的用户
     * 内存消耗 :45.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int waysToStep(int n) {
        long[] dp = new long[n];
        dp[0] = 1;
        if (n == 1) {
            return (int) dp[0];
        }
        dp[1] = 2;
        if (n == 2) {
            return (int) dp[1];
        }
        dp[2] = 4;
        if (n == 3) {
            return (int) dp[2];
        }
        for (int i = 3; i < n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000007;
        }
        return (int) dp[n - 1];
    }

    /**
     * 执行用时 :11 ms, 在所有 Java 提交中击败了8.79%的用户
     * 内存消耗 :38.2 MB, 在所有 Java 提交中击败了5.00%的用户
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        int len = min(first.length, last.length);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (first[i] != last[i]) {
                break;
            }
            res.append(first[i]);
        }
        return res.toString();
    }

    /**
     * 执行用时 :46 ms, 在所有 Java 提交中击败了82.99%的用户
     * 内存消耗 :65.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[][] multiSearch(String big, String[] smalls) {
        Trie0 tree = new Trie0(smalls);
        for (int i = 0; i < smalls.length; i++) {
            tree.insert(smalls[i], i);
        }
        char[] chars = big.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            tree.update(big.substring(i, len), i);
        }

        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < ans.length; i++) {
            List<Integer> list = tree.lists[i];
            ans[i] = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                ans[i][j] = list.get(j);
            }
        }
        return ans;

    }

    static class Trie0 {
        class Node {
            int id;
            boolean flag;
            Node[] children;

            public Node() {
                id = -1;
                flag = false;
                children = new Node[26];
            }
        }

        Node root;
        List<Integer>[] lists;

        public Trie0(String[] strings) {
            root = new Node();
            int len = strings.length;
            lists = new List[len];
            for (int i = 0; i < len; i++) {
                lists[i] = new ArrayList<>();
            }
        }

        public void insert(String word, int id) {
            Node p = root;
            char[] chars = word.toCharArray();
            int len = chars.length;
            for (char aChar : chars) {
                int c = aChar - 97;
                if (p.children[c] == null) {
                    p.children[c] = new Node();
                }
                p = p.children[c];
            }
            p.flag = true;
            p.id = id;
        }

        public void update(String word, int offset) {
            Node p = root;
            char[] chars = word.toCharArray();
            int len = chars.length;
            for (char aChar : chars) {
                int c = aChar - 97;

                if (p.children[c] == null) {
                    return;
                }
                p = p.children[c];
                if (p.flag) {
                    lists[p.id].add(offset);
                }
            }
        }
    }

    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if (len == 0 || len == 1) {
            return true;
        }
        int tag = A[0] > A[len - 1] ? 0 : 1;
        if (tag == 0) {
            for (int i = 1; i < len; i++) {
                if (A[i] > A[i - 1]) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < len; i++) {
                if (A[i] < A[i - 1]) {
                    return false;
                }
            }
        }
        return true;
    }


    public TreeNode increasingBST(TreeNode root) {
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();
        assert p != null;
        while (p.left == null) {
            p = p.left;
        }
        TreeNode newRoot = p;
        while (p.right != null) {
            p = p.right;
        }
        p.right = root;
        root.left = null;
        newRoot.right = root;
        return newRoot;
    }


    /**
     * 1014. 最佳观光组合
     * 执行用时 :9 ms, 在所有 Java 提交中击败了5.64%的用户
     * 内存消耗 :47.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxScoreSightseeingPair(int[] arr) {
        int len = arr.length;
        int[] arrayI = new int[len], arrayJ = new int[len], arrayJmaxI = new int[len];
        arrayI[0] = arr[0];
        arrayJmaxI[0] = -1;
        int max = 0;
        for (int i = 1; i < len; i++) {
            arrayI[i] = arr[i] + i;
            arrayJ[i] = arr[i] - i;
            arrayJmaxI[i] = Math.max(arrayI[i - 1], arrayJmaxI[i - 1]);
            max = Math.max(max, arrayJ[i] + arrayJmaxI[i]);
        }
        return max;
    }

    /**
     * 执行用时 :4 ms, 在所有 Java 提交中击败了87.77%的用户
     * 内存消耗 :48.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxScoreSightseeingPair0(int[] arr) {
        int len = arr.length;
        int keyI = arr[0], keyJ = 0, maxI = -1;
        int max = 0;
        for (int i = 1; i < len; i++) {
            maxI = Math.max(keyI, maxI);
            keyI = arr[i] + i;
            keyJ = arr[i] - i;
            max = Math.max(max, keyJ + maxI);
        }
        return max;
    }

    /**
     * 面试题 16.11. 跳水板
     * 执行用时 :2 ms, 在所有 Java 提交中击败了95.48%的用户
     * 内存消耗 :47.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        }
        if (shorter == longer) {
            return new int[]{k * shorter};
        }
        int[] res = new int[k + 1];
        res[0] = shorter * k;
        for (int i = 0; i < k; i++) {
            res[i + 1] = res[i] + longer - shorter;
        }
        return res;
    }

    /**
     * 面试题 08.02. 迷路的机器人
     * 执行用时 :44 ms, 在所有 Java 提交中击败了5.14%的用户
     * 内存消耗 :47.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        int endI = obstacleGrid.length - 1;
        int endJ = obstacleGrid[0].length - 1;
        if (endI == 0 && endJ == 0) {
            if (obstacleGrid[0][0] == 0) {
                List<Integer> dfsPoint = new ArrayList<Integer>();
                dfsPoint.add(0);
                dfsPoint.add(0);
                List<List<Integer>> dfs = new ArrayList<>();
                dfs.add(dfsPoint);
                return dfs;
            } else {
                return new ArrayList<>();
            }
        } else if (obstacleGrid[endI][endJ] == 1 || obstacleGrid[0][0] == 1) {
            return new ArrayList<>();
        }
        List<Integer> dfsPoint = new ArrayList<Integer>();
        Set<String> visited = new HashSet<>();
        int[][] goAhead = new int[][]{{0, 1}, {1, 0}};
        visited.add("0,0");
        dfsPoint.add(0);
        dfsPoint.add(0);
        List<List<Integer>> dfs = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        HashMap<String, List<List<Integer>>> load = new HashMap<>();
        dfs.add(dfsPoint);
        stack.push(dfsPoint);
        load.put("0:0", dfs);

        while (!stack.isEmpty()) {
            List<Integer> p = stack.pop();
            String key = p.get(0) + ":" + p.get(1);
            List<List<Integer>> preLoad = load.get(key);
            for (int i = 0; i < 2; i++) {
                int x = p.get(0) + goAhead[i][0];
                int y = p.get(1) + goAhead[i][1];
                if (x == endI + 1) {
                    continue;
                } else if (y == endJ + 1) {
                    continue;
                } else if (x == endI && y == endJ) {
                    List<Integer> newPoint = new ArrayList<>();
                    newPoint.add(x);
                    newPoint.add(y);
                    preLoad.add(newPoint);
                    return preLoad;
                }
                String newKey = x + ":" + y;
                if (visited.contains(newKey)) {
                } else if (obstacleGrid[x][y] == 1) {
                    visited.add(newKey);
                } else {
                    List<Integer> newPoint = new ArrayList<>();
                    newPoint.add(x);
                    newPoint.add(y);
                    stack.push(newPoint);
                    List<List<Integer>> newList = new ArrayList<>(preLoad);
                    newList.add(newPoint);
                    load.put(newKey, newList);
                    visited.add(newKey);
                }
            }
        }
        return new ArrayList<>();
    }


    /**
     * 面试题03. 数组中重复的数字
     * 执行用时 :9 ms, 在所有 Java 提交中击败了28.48%的用户
     * 内存消耗 :49 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return n;
            }
            set.add(n);
        }
        return -1;
    }

    /**
     * 面试题03. 数组中重复的数字
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :47.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findRepeatNumber0(int[] nums) {
        int key = 0;
        while (true) {
            if (nums[key] == key) {
                key++;
                continue;
            } else if (nums[key] == nums[nums[key]]) {
                return nums[key];
            }
            int tmp = nums[key];
            nums[key] = nums[key] ^ nums[tmp];
            nums[tmp] = nums[key] ^ nums[tmp];
            nums[key] = nums[key] ^ nums[tmp];
        }
    }

    /**
     * 面试题04. 二维数组中的查找
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :45.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 1028. 从先序遍历还原二叉树
     * 执行用时 :16 ms, 在所有 Java 提交中击败了23.36%的用户
     * 内存消耗 :40.3 MB, 在所有 Java 提交中击败了25.00%的用户
     */
    public TreeNode recoverFromPreorder(String S) {
        String[] chars = S.split("-");
        TreeNode tree = new TreeNode(Integer.parseInt(chars[0]));
        List<TreeNode> map = new ArrayList<>();
        int father = -1;
        map.add(tree);
        for (int i = 1; i < chars.length; i++) {
            int k = i;
            for (; i < chars.length; i++) {
                if (!"".equals(chars[i])) {
                    break;
                }
            }
            if (i == chars.length) {
                break;
            }
            k = i - k;
            TreeNode fatherTree = map.get(k);
            TreeNode newTree = new TreeNode(Integer.parseInt(chars[i]));
            if (k > father) {
                fatherTree.left = newTree;
                father = k;
                if (map.size() <= k + 1) {
                    map.add(newTree);
                } else {
                    map.set(k + 1, newTree);
                }
            } else {
                fatherTree.right = newTree;
                father = k;
                map.set(k + 1, newTree);
            }
        }
        return tree;
    }


    /**
     * 面试题 08.10. 颜色填充
     * 执行用时 :11 ms, 在所有 Java 提交中击败了16.10%的用户
     * 内存消耗 :40.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[][] maybeAround = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Set<String> isVisited = new HashSet<>();
        Stack<int[]> isAround = new Stack<>();
        int oldColor = image[sr][sc];
        int[] point = {sr, sc};
        isAround.push(point);
        isVisited.add(point[0] + ":" + point[1]);
        int maxRow = image.length, maxCol = image[0].length;
        while (!isAround.isEmpty()) {
            int[] tmpPoint = isAround.pop();
            image[tmpPoint[0]][tmpPoint[1]] = newColor;
            for (int[] m : maybeAround) {
                if (m[0] + tmpPoint[0] >= 0 && m[0] + tmpPoint[0] < maxRow && m[1] + tmpPoint[1] >= 0 && m[1] + tmpPoint[1] < maxCol) {
                    if (image[m[0] + tmpPoint[0]][m[1] + tmpPoint[1]] == oldColor) {
                        int[] newPoint = {m[0] + tmpPoint[0], m[1] + tmpPoint[1]};
                        if (!isVisited.contains(newPoint[0] + ":" + newPoint[1])) {
                            isAround.push(newPoint);
                            isVisited.add(newPoint[0] + ":" + newPoint[1]);
                        }
                    }
                }
            }
        }
        return image;
    }

    /**
     * 面试题 08.10. 颜色填充
     * 执行用时 :1 ms, 在所有 Java 提交中击败了93.96%的用户
     * 内存消耗 :41 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[][] floodFill0(int[][] image, int sr, int sc, int newColor) {
        int x = image.length;
        int y = image[0].length;
        boolean b = (sr < 0 || sr >= x) || (sc < 0 || sc >= y);
        if (b) {
            return image;
        }
        int oldColor = image[sr][sc];
        return floodFill(image, sr, sc, oldColor, newColor);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int oloColor, int newColor) {
        int x = image.length;
        int y = image[0].length;
        boolean b = (sr < 0 || sr >= x) || (sc < 0 || sc >= y);
        if (b) {
            return image;
        }
        if (image[sr][sc] == oloColor && image[sr][sc] != newColor) {
            image[sr][sc] = newColor;
            image = floodFill(image, sr - 1, sc, oloColor, newColor);
            image = floodFill(image, sr + 1, sc, oloColor, newColor);
            image = floodFill(image, sr, sc - 1, oloColor, newColor);
            image = floodFill(image, sr, sc + 1, oloColor, newColor);
        }
        return image;
    }

    /**
     * 面试题 08.11. 硬币
     * 执行用时 :42 ms, 在所有 Java 提交中击败了71.94%的用户
     * 内存消耗 :43.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int waysToChange(int n) {
        int[] coins = {1, 5, 10, 25};
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int coin : coins) {
            if (n + 1 > coin) {
                for (int i = coin; i <= n; i++) {
                    dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
                }
            }
        }
        return dp[n];
    }


    /**
     * 面试题 01.01. 判定字符是否唯一
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isUnique(String str) {
        for (int i = 0; i < str.length(); i++) {
            String s = str;
            s = s.replace(String.valueOf(s.charAt(i)), "");
            if (s.length() != str.length() - 1) {
                return false;
            }
        }
        return true;
    }


    /**
     * 面试题 01.01. 判定字符是否唯一
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isUnique0(String str) {
        for (char ch : str.toCharArray()) {
            if (str.indexOf(ch) != str.lastIndexOf(ch)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 面试题 01.02. 判定是否互为字符重排
     * 执行用时：1 ms, 在所有 Java 提交中击败了23.82%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map1.put(s1.charAt(i), map1.get(s1.charAt(i)) != null ? map1.get(s1.charAt(i)) + 1 : 0);
            map2.put(s2.charAt(i), map2.get(s2.charAt(i)) != null ? map2.get(s2.charAt(i)) + 1 : 0);
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (!map1.get(s1.charAt(i)).equals(map2.get(s1.charAt(i)))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 面试题 01.02. 判定是否互为字符重排
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean CheckPermutation0(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        quickSortChars(chars1, 0, s1.length() - 1);
        quickSortChars(chars2, 0, s2.length() - 1);
        for (int i = 0; i < s1.length(); i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }


    public void quickSortChars(char[] chars, int begin, int end) {
        if (begin >= end) {
            return;
        }
        char tmp = chars[begin];
        int b = begin;
        int e = end;
        while (b < e) {
            while (b < e && chars[e] >= tmp) {
                e--;
            }
            chars[b] = chars[e];
            while (b < e && chars[b] <= tmp) {
                b++;
            }
            chars[e] = chars[b];
        }
        chars[b] = tmp;
        quickSortChars(chars, begin, b - 1);
        quickSortChars(chars, b + 1, end);
    }

    /**
     * 面试题 01.02. 判定是否互为字符重排
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean CheckPermutation1(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] shorts = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            shorts[s1.charAt(i) - 32]++;
            shorts[s2.charAt(i) - 32]--;
        }
        for (int i = 0; i < 128; i++) {
            if (shorts[i] != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 面试题 01.03. URL化
     * 执行用时：22 ms, 在所有 Java 提交中击败了37.82%的用户
     * 内存消耗：47.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String replaceSpaces0(String str, int length) {
        char[] chars = str.toCharArray();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars[i];
            if (' ' == c) {
                stringBuffer.append("%20");
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }


    /**
     * 面试题 01.03. URL化
     * 执行用时：22 ms, 在所有 Java 提交中击败了37.82%的用户
     * 内存消耗：47.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String replaceSpaces(String str, int length) {
        int num = 0;
        for (int i = 0; i < length; i++) {
            if (' ' == str.charAt(i)) {
                num++;
            }
        }
        String news = str.replace(" ", "%20");
        return news.substring(0, length + 2 * num);
    }


    /**
     * 5. 最长回文子串
     * 执行用时：165 ms, 在所有 Java 提交中击败了21.65%的用户
     * 内存消耗：43.2 MB, 在所有 Java 提交中击败了14.28%的用户
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        // 枚举子串的长度 l+1
        for (int l = 0; l < n; l++) {
            //#枚举子串的起始位置 i，这样可以通过 j = i + l 得到子串的结束位置
            for (int i = 0; i < n; i++) {
                int j = i + l;
                if (j >= n) {
                    break;
                }
                if (l == 0) {
                    dp[i][j] = true;
                } else {
                    boolean b = s.charAt(i) == s.charAt(j);
                    if (l == 1) {
                        dp[i][j] = b;
                    } else {
                        dp[i][j] = (dp[i + 1][j - 1] && b);
                    }
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }


    /**
     * 面试题 01.04. 回文排列
     * 执行用时：1 ms, 在所有 Java 提交中击败了73.49%的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean canPermutePalindrome(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.merge(c, 1, Integer::sum);
        }
        int num = 0;
        for (Character key : map.keySet()) {
            if (map.get(key) % 2 != 0) {
                num++;
            }
        }
        return num < 2;
    }


    /**
     * 面试题 01.04. 回文排列
     * 执行用时：1 ms, 在所有 Java 提交中击败了73.49%的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean canPermutePalindrome0(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                set.remove(c);
            }
        }
        return set.size() <= 1;
    }


    /**
     * 面试题 01.05. 一次编辑
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.38%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean oneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        int firstIndex = first.length() - 1;
        int secondIndex = second.length() - 1;
        if (Math.abs(firstIndex - secondIndex) > 1) {
            return false;
        }
        int i = 0;
        while (i <= firstIndex && i <= secondIndex && first.charAt(i) == second.charAt(i)) {
            i++;
        }
        while (0 <= firstIndex && 0 <= secondIndex && first.charAt(firstIndex) == second.charAt(secondIndex)) {
            firstIndex--;
            secondIndex--;
        }
        return (firstIndex - i < 1 && secondIndex - i < 1);
    }

    /**
     * 面试题 01.05. 一次编辑
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.38%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean oneEditAway1(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        int len1 = first.length();
        int len2 = second.length();
        if (Math.abs(len1 - len2) > 1) {
            return false;
        }
        if (len2 > len1) {
            return oneEditAway(second, first);
        }

        // 保持第一个比第二个长
        for (int i = 0; i < len2; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                // 如果是长度相同字符串，那就比较下一个，如果长度不一样，那就从该字符开始进行比较。
                return first.substring(i + 1).equals(second.substring(len1 == len2 ? i + 1 : i));
            }
        }
        return true;
    }

    /**
     * 面试题 01.06. 字符串压缩
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.81%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String compressString(String S) {
        int len = S.length();
        if (len <= 2) {
            return S;
        }
        char[] chars = S.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int tmp = 1;
            stringBuilder.append(chars[i]);
            while (i + tmp < len && chars[i + tmp] == chars[i]) {
                tmp++;
            }
            stringBuilder.append(tmp);
            if (i + tmp >= len) {
                break;
            } else {
                i = i + tmp - 1;
            }
        }
        String str = stringBuilder.toString();
        return str.length() >= len ? S : str;
    }


    /**
     * 面试题 01.07. 旋转矩阵:原地旋转
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 面试题 01.07. 旋转矩阵:翻转
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public void rotate0(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix[i][j] ^ matrix[n - i - 1][j];
                matrix[n - i - 1][j] = matrix[i][j] ^ matrix[n - i - 1][j];
                matrix[i][j] = matrix[i][j] ^ matrix[n - i - 1][j];
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                matrix[j][i] = matrix[i][j] ^ matrix[j][i];
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
            }
        }
    }


    /**
     * 面试题 01.08. 零矩阵
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return;
        }
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }
        for (int[] li : list) {
            int pi = li[0];
            int pj = li[1];
            for (int i = 0; i < row; i++) {
                matrix[i][pj] = 0;
            }
            for (int j = 0; j < col; j++) {
                matrix[pi][j] = 0;
            }
        }
    }


    /**
     * 面试题 01.08. 零矩阵
     * 执行用时：3 ms, 在所有 Java 提交中击败了19.83%的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public void setZeroes0(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return;
        }
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }
        for (int ro : rowSet) {
            for (int j = 0; j < col; j++) {
                matrix[ro][j] = 0;
            }
        }
        for (int co : colSet) {
            for (int i = 0; i < row; i++) {
                matrix[i][co] = 0;
            }
        }
    }


    /**
     * 67. 二进制求和
     * 直接二进制求和
     */
    public String addBinary0(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }


    /**
     * 67. 二进制求和
     * 直接二进制求和
     */
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }


    /**
     * 223. 矩形面积
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.73%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了25.00%的用户
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //计算重叠面积
        int overArea = 0;
        if (min(C, G) > Math.max(A, E) && min(D, H) > Math.max(B, F)) {
            //x轴（有可能是负数）：Math.min(C,G) - Math.max(A,E)
            //y轴（有可能是负数）：Math.min(D,H) - Math.max(B,F)
            //最后结果取绝对值
            overArea = Math.abs((min(C, G) - Math.max(A, E)) *
                    (min(D, H) - Math.max(B, F)));
        }
        //第一个矩阵面积
        int firstRecArea = Math.abs((C - A) * (D - B));

        //第二个矩阵面积
        int secondRecArea = Math.abs((G - E) * (H - F));

        //最终的面积=第一个矩阵面积+第二个矩阵面积-重叠面积
        return firstRecArea + secondRecArea - overArea;
    }


    /**
     * 815. 公交路线
     * 执行用时：42 ms, 在所有 Java 提交中击败了66.04%的用户
     * 内存消耗：80.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int numBusesToDestination0(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        //这里用list存会超时
        Map<Integer, Set<Integer>> posMap = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int k : routes[i]) {
                if (!posMap.containsKey(k)) {
                    posMap.put(k, new HashSet<Integer>());
                }
                posMap.get(k).add(i);
            }
        }
        boolean[] visited = new boolean[routes.length];
        Deque<Integer> queue = new ArrayDeque<>(posMap.get(S)); // 加入所有车信息
        for (int k : posMap.get(S)) {
            visited[k] = true;
        }
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int bus = queue.poll();
                for (int k : routes[bus]) { // 当前车经过的站
                    if (k == T) {
                        return res;
                    }
                    for (int busindex : posMap.get(k)) {
                        if (visited[busindex]) {
                            continue;
                        }
                        queue.add(busindex);
                        visited[busindex] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }


    /**
     * 815. 公交路线
     * 执行用时：314 ms, 在所有 Java 提交中击败了26.41%的用户
     * 内存消耗：49.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        Set<Integer> start = null;
        Set<Integer> end = null;

        // 用List构建图上的节点关系，用Set存放一个路线（一个节点）
        List<Set<Integer>> routeList = new LinkedList<>();
        for (int[] route : routes) {
            Set<Integer> s = new HashSet<>();
            for (int i : route) {
                s.add(i);
            }
            if (s.contains(S)) {
                if (s.contains(T)) {
                    return S == T ? 0 : 1;
                }
                if (start == null) {
                    start = s;
                } else {
                    start.addAll(s);
                }
            } else if (s.contains(T)) {
                if (end == null) {
                    end = s;
                    routeList.add(s);
                } else {
                    end.addAll(s);
                }
            } else {
                routeList.add(s);
            }
        }
        // 快速返回
        if (start == null || end == null) {
            return -1;
        }

        // 直接将BFS的每一步的所有节点合并成一个大Set(不用队列)，简化分层查找
        int ret = 1;
        Set<Integer> lastLevel = start;
        while (!lastLevel.isEmpty()) {
            ret++;
            Iterator<Set<Integer>> iter = routeList.iterator();
            Set<Integer> curLevel = new HashSet<>();
            while (iter.hasNext()) {
                Set<Integer> ss = iter.next();
                if (ss.size() == 0) {
                    iter.remove();
                    continue;
                }
                // 去掉交集(交集部分都在本层可以下车)
                Set<Integer> r = removeBfromA(ss, lastLevel);
                if (r.contains(T)) {
                    return ret;
                }
                curLevel.addAll(r);
            }
            // 去下一层找剩余部分的交集
            lastLevel = curLevel;
        }

        return -1;
    }

    private Set<Integer> removeBfromA(Set<Integer> a, Set<Integer> b) {
        int len = a.size();
        a.removeAll(b);
        if (a.size() == len) {
            return new HashSet<Integer>();
        }
        return a;
    }


    /**
     * 1482. 制作 m 束花所需的最少天数
     * 执行用时：23 ms, 在所有 Java 提交中击败了53.96%的用户
     * 内存消耗：48.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        // 最大等待的天数是数组里的最大值
        int max = 0;
        for (int i : bloomDay) {
            max = Math.max(max, i);
        }
        // 最小等待0天
        int min = 0;
        while (min < max) {
            // mid:等待天数
            int mid = min + (max - min) / 2;
            // 等待mid天，有多少组连续的k朵花已经开花🌼了
            int count = getCount(bloomDay, mid, k);
            if (count >= m) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    // 返回等待day天，有多少组连续的k天<=day  这里用的贪心
    private int getCount(int[] arr, int day, int k) {
        int re = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= day) {
                count++;
            } else {
                count = 0;
            }
            //  连续的k朵花🌼开了
            if (count == k) {
                re++;
                count = 0;
            }
        }
        return re;
    }


    /**
     * 1481. 不同整数的最少数目
     * 执行用时：70 ms, 在所有 Java 提交中击败了40.73%的用户
     * 内存消耗：55.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if (arr.length <= k) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] - o2[1]);
            }
        };
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(comparator);
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for (int i : map.keySet()) {
            int j = map.get(i);
            queue.add(new int[]{i, j});
        }
        while (k >= 0) {
            int[] tmp = queue.poll();
            k = k - tmp[1];
        }
        return queue.size() + 1;
    }

    /**
     * 209. 长度最小的子数组
     * 时间复杂度O(n×log(n))
     * 执行用时：4 ms, 在所有 Java 提交中击败了23.26%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了6.67%的用户
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    /**
     * 209. 长度最小的子数组
     * 时间复杂度O(n)
     * 执行用时：2 ms, 在所有 Java 提交中击败了83.23%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了6.67%的用户
     */
    public int minSubArrayLen0(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 210. 课程表 II
     * 执行用时：9 ms, 在所有 Java 提交中击败了55.80%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了93.33%的用户
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] course : prerequisites) {
            int dest = course[1];
            int src = course[0];
            List<Integer> lst = map.getOrDefault(src, new ArrayList<>());
            lst.add(dest);
            map.put(src, lst);
        }

        int[] visited = new int[numCourses];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, map, visited, ans)) {
                return new int[0];
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * node: 当前节点
     * map: 图
     * visited: 0表示未访问, 1表示访问中, 2表示访问完成
     * ans: 储存拓扑排序结果
     * return: 是否无环
     */
    private boolean dfs(int node, Map<Integer, List<Integer>> map, int[] visited, List<Integer> ans) {
        // 已访问
        if (visited[node] == 2) {
            return true;
        }
        // 发现环
        if (visited[node] == 1) {
            return false;
        }
        // 标记访问
        visited[node] = 1;
        // 无出节点, 加入答案，并标记访问完成
        if (!map.containsKey(node)) {
            ans.add(node);
            visited[node] = 2;
            return true;
        }
        // 遍历出节点
        List<Integer> neighbour = map.get(node);
        for (int nei : neighbour) {
            if (!dfs(nei, map, visited, ans)) {
                return false;
            }
        }
        // 当前节点访问完成
        visited[node] = 2;
        ans.add(node);
        return true;
    }


    public int[] findOrder0(int numCourses, int[][] prerequisites) {
        //受课程影响的后续课程
        int[] after = new int[numCourses];
        //课程的前置课程
        int[] before = new int[numCourses];
        for (int[] pre : prerequisites) {
            int a = pre[0];
            int b = pre[1];
            after[b] += 1 << a;
            before[a] += 1 << b;
        }
        //已经上过的课程数量
        int have = 0;
        int[] res = new int[numCourses];
        while (have != numCourses) {
            boolean flag = false;
            for (int i = 0; i < numCourses; i++) {
                if (before[i] == 0) {
                    flag = true;
                    before[i] = -1;
                    res[have++] = i;
                    int tmp = after[i];
                    int t = 0;
                    while (tmp != 0) {
                        if (tmp % 2 == 1) {
                            before[t] = before[t] - (1 << i);
                        }
                        tmp = tmp >> 1;
                        t++;
                    }
                }
            }
            if (!flag) {
                return new int[]{};
            }
        }
        return res;
    }


    /**
     * 215. 数组中的第K个最大元素
     * 执行用时：2 ms, 在所有 Java 提交中击败了92.63%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了6.12%的用户
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


    /**
     * 1227. 飞机座位分配概率
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了25.00%的用户
     */
    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1 : 0.5;
    }


    /**
     * 面试题 17.01. 不用加号的加法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param a 加数a
     * @param b 加数b
     * @return 和
     */
    public int add(int a, int b) {
        while (b != 0) {
            int sum = (a ^ b);
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }

        return a;
    }

    /**
     * 923. 三数之和的多种可能
     * 执行用时：22 ms, 在所有 Java 提交中击败了56.94%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param A      加数
     * @param target 目标值
     * @return 可能数量
     */
    public int threeSumMulti(int[] A, int target) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : A) {
            counts.putIfAbsent(num, 0);
            counts.put(num, counts.get(num) + 1);
        }
        long res = 0;
        int[] nums = counts.keySet().stream().mapToInt(integer -> integer).toArray();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int value = target - nums[i] - nums[j];
                if (value < nums[j]) {
                    break;
                }
                if (counts.get(value) == null) {
                    continue;
                }
                long a = counts.get(nums[i]);
                long b = counts.get(nums[j]);
                long c = counts.get(value);
                if (nums[i] == nums[j] && nums[j] == value) {
                    res += getCni(a, 3);
                } else if (nums[i] == nums[j]) {
                    res += getCni(a, 2) * c;
                } else if (nums[j] == value) {
                    res += getCni(b, 2) * a;
                } else {
                    res += a * b * c;
                }
                res = res % 1000000007;
            }
        }
        return (int) res;
    }

    // 排列组合计算NCi
    private long getCni(long n, int i) {
        if (i > n) {
            return 0;
        }
        long left = n;
        int right = 1;
        long value = 1;
        for (int j = 0; j < i; j++) {
            value = value * (left--) / (right++);
        }
        return value;
    }


    /**
     * 面试题 04.01. 节点间通路
     * 执行用时：59 ms, 在所有 Java 提交中击败了10.22%的用户
     * 内存消耗：85.5 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param n      节点数
     * @param graph  地图
     * @param start  起始点
     * @param target 目标点
     * @return 通路数量
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] g : graph) {
            if (map.containsKey(g[0])) {
                List<Integer> list = map.get(g[0]);
                list.add(g[1]);
                map.put(g[0], list);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(g[1]);
                map.put(g[0], list);
            }
        }
        if (!map.containsKey(start)) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        set.add(start);
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            if (!map.containsKey(tmp)) {
                continue;
            }
            for (int t : map.get(tmp)) {
                if (t == target) {
                    return true;
                } else if (!set.contains(t)) {
                    set.add(t);
                    stack.push(t);
                }
            }
        }
        return false;
    }


    /**
     * 面试题 04.02. 最小高度树
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums 原始数组
     * @return 最小高度树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelp(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTHelp(int[] nums, int b, int e) {
        if (b > e) {
            return null;
        }
        int t = (b + e + 1) / 2;
        TreeNode root = new TreeNode(nums[t]);
        root.left = sortedArrayToBSTHelp(nums, b, t - 1);
        root.right = sortedArrayToBSTHelp(nums, t + 1, e);
        return root;
    }

    /**
     * 面试题 04.03. 特定深度节点链表
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.71%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param tree 树
     * @return 特定深度节点链表
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[]{};
        }
        List<List<Integer>> list = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(tree);
        while (!queue.isEmpty()) {
            Deque<TreeNode> q = new LinkedList<>();
            List<Integer> li = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode t = queue.pollFirst();
                if (t.left != null) {
                    q.addLast(t.left);
                }
                if (t.right != null) {
                    q.addLast(t.right);
                }
                li.add(t.val);
            }
            list.add(li);
            queue = q;
        }
        ListNode[] res = new ListNode[list.size()];
        int j = 0;
        for (List<Integer> li : list) {
            ListNode p = new ListNode(li.get(0));
            ListNode t = p;
            for (int i = 1; i < li.size(); i++) {
                ListNode pp = new ListNode(li.get(i));
                t.next = pp;
                t = pp;
            }
            res[j++] = p;
        }
        return res;
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param n 目标台阶数
     * @return 几种跳法
     */
    public int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n - 1];
    }

    /**
     * 378. 有序矩阵中第K小的元素
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：45.5 MB, 在所有 Java 提交中击败了7.69%的用户
     *
     * @param matrix 有序矩阵
     * @param k      第k个
     * @return 有序矩阵中第K小的元素
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

    /**
     * 378. 有序矩阵中第K小的元素
     * 执行用时：2 ms, 在所有 Java 提交中击败了62.67%的用户
     * 内存消耗：45.4 MB, 在所有 Java 提交中击败了7.69%的用户
     *
     * @param matrix 有序矩阵
     * @param k      第k个
     * @return 有序矩阵中第K小的元素
     */
    public int kthSmallest0(int[][] matrix, int k) {
        int min = matrix[0][0];
        int len = matrix.length;
        int max = matrix[len - 1][len - 1];
        while (min < max) {
            int mid = min + ((max - min) >> 1);
            int cout = 0;
            for (int[] mat : matrix) {
                for (int m : mat) {
                    if (m > mid) {
                        break;
                    }
                    cout++;
                }
            }
            if (cout >= k) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }


    /**
     * 面试题 17.10. 主要元素
     * HashMap()
     * 执行用时：24 ms, 在所有 Java 提交中击败了7.43%的用户
     * 内存消耗：45.1 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums 原始数组
     * @return 数组中的主要元素
     */
    public int majorityElement0(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = -1;
        int maxKey = 0;
        for (int n : nums) {
            map.putIfAbsent(n, 1);
            int tmp = map.get(n) + 1;
            map.put(n, tmp);
            if (max < tmp) {
                max = tmp;
                maxKey = n;
            }
        }
        return max > nums.length / 2 ? maxKey : -1;
    }

    /**
     * 面试题 17.10. 主要元素
     * 摩尔投票
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums 原始数组
     * @return 数组中的主要元素
     */
    public int majorityElement1(int[] nums) {
        int key = nums[0];
        int num = 1;
        for (int i = 1; i < nums.length; i++) {
            if (num == 0) {
                key = nums[i];
                num++;
            } else if (key == nums[i]) {
                num++;
            } else {
                num--;
            }
        }
        if (num == 0) {
            return -1;
        }
        num = 0;
        for (int n : nums) {
            if (n == key) {
                num++;
            }
        }
        return num > nums.length / 2 ? key : -1;
    }

    /**
     * 面试题 17.10. 主要元素
     * 位运算
     * 执行用时：6 ms, 在所有 Java 提交中击败了30.46%的用户
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums 原始数组
     * @return 数组中的主要元素
     */
    public int majorityElement2(int[] nums) {
        int ans = 0;
        int n = nums.length;
        //统计每位数字的第i位二进制
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                //如果第i位为1
                if ((num & (1 << i)) != 0) {
                    cnt++;
                }
            }
            //如果所有数字的二进制数中，第i位1比0多
            if (cnt > n / 2) {
                ans ^= (1 << i);
            }
        }
        int c = 0;
        for (int num : nums) {
            if (num == ans) {
                c++;
            }
        }
        if (c <= n / 2) {
            ans = -1;
        }
        return ans;
    }

    /**
     * 面试题 08.03. 魔术索引
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums 原始数组
     * @return 最小魔术索引
     */
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 周赛2020.07.05
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int tmp = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (tmp != arr[i] - arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int getLastMoment(int n, int[] left, int[] right) {
        int max = 0;
        if (left.length > 0) {
            Arrays.sort(left);
            int leftMax = left[left.length - 1];
            max = Math.max(max, leftMax);
        }
        if (right.length > 0) {
            Arrays.sort(right);
            int minRight = right[0];
            max = Math.max(max, n - minRight);
        }
        return max;
    }

    public int numSubmat0(int[][] mat) {
//        Set<String> set = new HashSet<>();
        int res = 0;
        boolean isVisit = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1 && !isVisit) {
                    isVisit = true;
                    int t = 1;
                    for (int k = j; k < mat.length; k++) {
                        if (mat[i][k] == 0) {
                            break;
                        }
                        t++;
                    }
                    for (int k = 0; k < t; k++) {
                        int y = k + 1;
                        int x = 1;
                        for (int o = i + 1; o < mat.length; o++) {
                            boolean flag = true;
                            for (int p = j; p < y + p; p++) {
                                if (mat[o][p] == 0) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                x++;
                            } else {
                                break;
                            }
                        }
                        res += sum2(x);
                    }

                } else if (mat[i][j] == 0) {
                    isVisit = false;
                }
            }
        }
        return res;
    }

    public int sum(int length, int len) {
        return length - len + 1;
    }

    public int sum2(int length) {
        int res = 0;
        for (int l = 1; l < length; l++) {
            res += sum(length, l);
        }
        return res;
    }

    public int findX(int[][] mat, int x, int y) {
        int res = 0;
        int p = 0;
        for (int j = 0; j < mat[0].length; j++) {
            boolean f = true;
            for (int i = x; i < y; i++) {
                if (mat[i][j] == 0) {
                    f = false;
                    break;
                }
            }
            if (f) {
                p++;
            } else {
                if (p >= y - x + 1) {
                    res += sum2(p);
                }
                p = 0;
            }
        }
        return res;
    }

    public int findY(int[][] mat, int x, int y) {
        int res = 0;
        int p = 0;
        for (int[] ints : mat) {
            boolean f = true;
            for (int i = x; i < y; i++) {
                if (ints[i] == 0) {
                    f = false;
                    break;
                }
            }
            if (f) {
                p++;
            } else {
                if (p < y - x + 1) {
                    res += sum2(p);
                }
                p = 0;
            }
        }
        return res;
    }

    public String minInteger(String num, int k) {
        int[] data = new int[num.length()];
        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            data[i] = chars[i] - '0';
        }
        int tmp = 0;
        while (k >= 0) {
            if (tmp == data.length - 1) {
                break;
            }
            if (k >= data.length) {
                int minI = tmp;
                int minKey = data[tmp];
                for (int i = tmp; i < data.length; i++) {
                    if (minKey > data[i]) {
                        minI = i;
                        minKey = data[i];
                    }
                }
                if (minI - tmp >= 0) {
                    System.arraycopy(data, tmp, data, tmp + 1, minI - tmp);
                }
                data[tmp] = minKey;
                k = k - minI + tmp;
                tmp++;
            } else {
                int minI = tmp;
                int minKey = data[tmp];
                for (int i = tmp; i <= k + tmp && i < data.length; i++) {
                    if (minKey > data[i]) {
                        minI = i;
                        minKey = data[i];
                    }
                }
                if (minI - tmp >= 0) {
                    System.arraycopy(data, tmp, data, tmp + 1, minI - tmp);
                }
                data[tmp] = minKey;
                k = k - minI + tmp;
                tmp++;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int d : data) {
            res.append(d);
        }
        return res.toString();
    }


    /**
     * 面试题 16.22. 兰顿蚂蚁
     * 执行用时：29 ms, 在所有 Java 提交中击败了85.12%的用户
     * 内存消耗：60.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    private static class Position {

        // 横坐标 x 纵坐标 y
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Position)) {
                return false;
            }
            Position o = (Position) obj;
            return x == o.x && y == o.y;
        }

        // 改写哈希算法，使两个 Position 对象可以比较坐标而不是内存地址
        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public List<String> printKMoves(int K) {
        char[] direction = {'L', 'U', 'R', 'D'};
        // 用“向量”记录方向，顺序与上一行方向的字符顺序保持一致，每个元素的后一个元素都是可以90°向右变换得到的
        int[][] offset = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        // 蚂蚁的位置
        Position antPos = new Position(0, 0);
        // 蚂蚁方向的向量序号
        int antDir = 2;
        // 用集合存储所有黑块的坐标，一开始想再定义一个路径的坐标集合，发现可以直接用黑块+蚂蚁位置也能过
        Set<Position> blackSet = new HashSet<>();
        while (K > 0) {
            // 新的坐标对象用于放入集合
            Position t = new Position(antPos.x, antPos.y);
            // 如果黑块集合能存入，说明脚下的块不在集合中，也就意味着是白色，方向序号循环自增1
            if (blackSet.add(t)) {
                antDir = (antDir + 1) % 4;
            } else {
                // 否则说明脚下的块已经在集合中，也就意味着是黑色，方向序号循环自增3，相当于自减1，但是Math.floorMod取模可能消耗大？用+3替代
                antDir = (antDir + 3) % 4;
                // 别忘了删除，即将黑块变白
                blackSet.remove(t);
            }
            // 蚂蚁移动位置
            antPos.x += offset[antDir][0];
            antPos.y += offset[antDir][1];
            K--;
        }
        // 计算边界，即输出网格的行数和列数
        int left = antPos.x, top = antPos.y, right = antPos.x, bottom = antPos.y;
        for (Position pos : blackSet) {
            left = min(pos.x, left);
            top = min(pos.y, top);
            right = Math.max(pos.x, right);
            bottom = Math.max(pos.y, bottom);
        }
        char[][] grid = new char[bottom - top + 1][right - left + 1];
        // 填充白块
        for (char[] row : grid) {
            Arrays.fill(row, '_');
        }
        // 替换黑块
        for (Position pos : blackSet) {
            grid[pos.y - top][pos.x - left] = 'X';
        }
        // 替换蚂蚁
        grid[antPos.y - top][antPos.x - left] = direction[antDir];
        // 利用网格生成字符串列表
        List<String> result = new ArrayList<>();
        for (char[] row : grid) {
            result.add(String.valueOf(row));
        }
        return result;
    }

    /**
     * 63. 不同路径 II
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了48.15%的用户
     *
     * @param obstacleGrid 原地图
     * @return 路径数量
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    /**
     * 64. 最小路径和
     * 执行用时：4 ms, 在所有 Java 提交中击败了30.96%的用户
     * 内存消耗：42.5 MB, 在所有 Java 提交中击败了30.30%的用户
     *
     * @param grid 原地图
     * @return 最小路径和
     */
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 0) {
            return 1;
        }
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = grid[i][j];
                if (i == 0 && j == 0) {
                    dp[i][j] += 0;
                } else if (i == 0) {
                    dp[i][j] += dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] += dp[i - 1][j];
                } else {
                    int t = min(dp[i][j - 1], dp[i - 1][j]);
                    dp[i][j] += t;
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    /**
     * 1504. 统计全 1 子矩形
     * 执行用时：32 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param mat 原始0-1数组
     * @return 数组中全是1矩形个数
     */
    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] prefixAnd = mat.clone();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                prefixAnd[i][j] += prefixAnd[i][j - 1];
            }
        }
        int num = 0;
        for (int l = 0; l < m; l++) {
            for (int r = l; r < m; r++) {
                int tmp = 0;
                for (int t = 0; t < n; t++) {
                    int pre = prefixAnd[t][r];
                    if (l > 0) {
                        pre -= prefixAnd[t][l - 1];
                    }
                    if (pre == (r - l + 1)) {
                        tmp++;
                    } else {
                        num += (tmp * (tmp + 1) / 2);
                        tmp = 0;
                    }
                }
                if (tmp != 0) {
                    num += (tmp * (tmp + 1) / 2);
                }
            }
        }
        return num;
    }


    /**
     * 1505. 最多 K 次交换相邻数位后得到的最小整数
     * 执行用时：41 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param num 初始数
     * @param k   交换k次
     * @return 结果数
     */
    public String minInteger0(String num, int k) {

        class FenwichTree {

            private int[] sums;
            private int[] nums;

            public FenwichTree(int[] nums) {
                this.sums = new int[nums.length + 1];
                this.nums = nums;
                for (int i = 0; i < nums.length; i++) {
                    updateBit(i + 1, nums[i]);
                }
            }

            public void update(int i, int val) {
                updateBit(i + 1, val - nums[i]);
                nums[i] = val;
            }

            private void updateBit(int i, int diff) {
                while (i < sums.length) {
                    sums[i] += diff;
                    i += lowBit(i);
                }
            }

            public int sumRange(int i, int j) {
                return preSum(j + 1) - preSum(i);
            }

            private int preSum(int i) {
                int sum = 0;
                while (i > 0) {
                    sum += sums[i];
                    i -= lowBit(i);
                }
                return sum;
            }

            private int lowBit(int i) {
                return i & (-i);
            }

        }
        // 统计0-9的所有位置
        List<Integer>[] idLists = new List[10];
        for (int i = 0; i < 10; i++) {
            idLists[i] = new ArrayList<>();
        }
        int n = num.length();
        for (int i = 0; i < n; i++) {
            idLists[num.charAt(i) - '0'].add(i);
        }
        // 指向idLists的0-9的当前位置
        int[] ids = new int[10];
        boolean[] seen = new boolean[n];
        StringBuilder res = new StringBuilder();
        // 统计范围内已被使用的下标，计算需要转换的次数时需要去掉已被转换到前面的那些下标
        FenwichTree fwt = new FenwichTree(new int[n]);
        outer:
        for (int i = 0; i < n; i++) {
            // 如果已经被置换过了，跳过
            if (seen[i]) {
                continue;
            }
            int cur = num.charAt(i) - '0';
            // 查找比当前元素小且满足条件的最小值的下标
            for (int j = 0; j < cur; j++) {
                while (ids[j] < idLists[j].size() && idLists[j].get(ids[j]) < i) {
                    ids[j]++;
                }
                if (ids[j] == idLists[j].size()) {
                    continue;
                }
                int index = idLists[j].get(ids[j]);
                int seenNum = fwt.sumRange(0, index - 1);
                if (index - seenNum <= k) {
                    // 找到了满足条件的值，更新状态
                    k -= index - seenNum;
                    ids[j]++;
                    seen[index] = true;
                    fwt.update(index, 1);
                    i--;
                    res.append(j);
                    continue outer;
                }
            }
            // 找不到满足条件且小于当前值的值，更新状态
            seen[i] = true;
            fwt.update(i, 1);
            res.append(cur);
        }
        return res.toString();
    }

    /**
     * 112. 路径总和
     * 执行用时：1 ms, 在所有 Java 提交中击败了14.22%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了6.52%的用户
     *
     * @param root 树
     * @param sum  和
     * @return 是否能达成路径和
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            if (tmp.left == null && tmp.right == null) {
                if (tmp.val == sum) {
                    return true;
                }
                continue;
            }
            if (tmp.left != null) {
                tmp.left.val += tmp.val;
                stack.push(tmp.left);
            }
            if (tmp.right != null) {
                tmp.right.val += tmp.val;
                stack.push(tmp.right);
            }
        }
        return false;
    }


    /**
     * 1498. 满足条件的子序列数目
     * 执行用时：36 ms, 在所有 Java 提交中击败了85.96%的用户
     * 内存消耗：48.6 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums   原始数组
     * @param target 目标值
     * @return 满足条件的子序列数目
     */
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        if (nums[0] * 2 > target) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int res = 0;
        int[] pow = new int[nums.length];
        pow[0] = 1;
        int mode = 1_0000_0000_7;
        for (int i = 1; i < nums.length; i++) {
            pow[i] = pow[i - 1] * 2;
            pow[i] %= mode;
        }
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                res += pow[right - left];
                res %= mode;
                left++;
            } else {
                right--;
            }
        }
        return res;
    }


    /**
     * 870. 优势洗牌
     * 执行用时：35 ms, 在所有 Java 提交中击败了91.54%的用户
     * 内存消耗：43.6 MB, 在所有 Java 提交中击败了11.11%的用户
     *
     * @param arr 待洗的牌数组
     * @param brr 用于对比的牌数组
     * @return 优势牌数组
     */
    public int[] advantageCount(int[] arr, int[] brr) {
        Arrays.sort(arr);
        int[] crr = brr.clone();
        Arrays.sort(brr);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int max = arr.length - 1, min = 0;
        for (int i = brr.length - 1; i >= 0; i--) {
            map.putIfAbsent(brr[i], new LinkedList<>());
            List list = map.get(brr[i]);
            if (arr[max] > brr[i]) {
                list.add(arr[max--]);
            } else {
                list.add(arr[min++]);
            }
        }
        for (int i = brr.length - 1; i >= 0; i--) {
            List<Integer> tmp = map.get(crr[i]);
            crr[i] = tmp.remove(0);
        }
        return crr;
    }

    /**
     * 948. 令牌放置
     * 执行用时：3 ms, 在所有 Java 提交中击败了87.50%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param tokens tokens Array
     * @param p      point
     * @return the best value you can got
     */
    public int bagOfTokensScore(int[] tokens, int p) {
        int value = 0;
        Arrays.sort(tokens);
        int l = 0, r = tokens.length - 1;
        int max = 0;
        while (l <= r) {
            while (l <= r && tokens[l] <= p) {
                value++;
                p -= tokens[l++];
                max = Math.max(max, value);
            }
            if (value == 0) {
                return 0;
            }
            if (l <= r) {
                value--;
                p += tokens[r--];
            }
        }
        return max;
    }

    /**
     * 1277. 统计全为 1 的正方形子矩阵
     * 执行用时：117 ms, 在所有 Java 提交中击败了8.06%的用户
     * 内存消耗：52.6 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] prefixAnd = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    prefixAnd[i][j] = matrix[i][j];
                } else {
                    prefixAnd[i][j] = prefixAnd[i][j - 1] + matrix[i][j];
                }
            }
        }
        int num = 0;
        for (int l = 0; l < m; l++) {
            for (int r = l; r < m; r++) {
                int tmp = r - l + 1;
                int now = 0;
                for (int t = 0; t < n; t++) {
                    int pre = prefixAnd[t][r];
                    if (l > 0) {
                        pre = prefixAnd[t][r] - prefixAnd[t][l - 1];
                    }
                    if (pre == tmp) {
                        now++;
                    } else {
                        num += now >= tmp ? now - tmp + 1 : 0;
                        now = 0;
                    }
                }
                if (now > 0) {
                    num += now >= tmp ? now - tmp + 1 : 0;
                }
            }
        }
        return num;
    }


    /**
     * 1278. 分割回文串 III
     * 执行用时：40 ms, 在所有 Java 提交中击败了20.14%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param s 原始字符串
     * @param k 分割数量
     * @return 最小变换次数
     */
    public int palindromePartition0(String s, int k) {
        int len = s.length();
        // dp[i][j] -> 0-i,划分为j段
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                } else if (i < j) {
                    break;
                } else if (j == 1) {
                    dp[i][j] = cost(s, 0, i - 1);
                } else {
                    // i 大于 j
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int t = j - 1; t <= i - 1; t++) {
                        dp[i][j] = min(dp[i][j], dp[t][j - 1] + cost(s, t, i - 1));
                    }
                }
            }
        }
        return dp[len][k];
    }

    private int cost(String s, int i, int j) {
        int res = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                res++;
            }
            i++;
            j--;
        }
        return res;
    }


    /**
     * 1278. 分割回文串 III
     * 执行用时：7 ms, 在所有 Java 提交中击败了52.52%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param s 原始字符串
     * @param k 分割数量
     * @return 最小变换次数
     */
    public int palindromePartition(String s, int k) {
        int[][] pali = new int[s.length() + 1][s.length() + 1];
        for (int i = s.length(); i >= 1; i--) {
            for (int j = i; j <= s.length(); j++) {
                if (j - i >= 2) {
                    pali[i][j] = pali[i + 1][j - 1];
                }
                if (s.charAt(i - 1) != s.charAt(j - 1)) {
                    pali[i][j]++;
                }
            }
        }

        int[][] dp = new int[k + 1][s.length() + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= s.length(); j++) {
                if (i == 1) {
                    dp[i][j] = pali[i][j];
                } else {
                    dp[i][j] = dp[i - 1][i - 1] + pali[i][j];
                    for (int x = i; x < j; x++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][x] + pali[x + 1][j]);
                    }
                }
            }
        }
        return dp[k][s.length()];
    }


    /**
     * 面试题 17.13. 恢复空格
     * 执行用时：87 ms, 在所有 Java 提交中击败了58.77%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    public int respace0(String[] dictionary, String sentence) {
        int n = sentence.length();

        Trie root = new Trie();
        for (String word : dictionary) {
            root.insert(word);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;

            Trie curPos = root;
            for (int j = i; j >= 1; --j) {
                int t = sentence.charAt(j - 1) - 'a';
                if (curPos.next[t] == null) {
                    break;
                } else if (curPos.next[t].isEnd) {
                    dp[i] = Math.min(dp[i], dp[j - 1]);
                }
                if (dp[i] == 0) {
                    break;
                }
                curPos = curPos.next[t];
            }
        }
        return dp[n];
    }

//    public int respace(String[] dictionary, String sentence) {
//        int len = sentence.length();
//        int num = dictionary.length;
//        int[] dp = new int[num + 1];
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < num; j++) {
//                if ()
//            }
//        }
//    }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices 股票价格数组
     * @return 最大收益
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        //待买入，待卖出，冷冻期
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return dp[len - 1][1];
    }


    /**
     * 123. 买卖股票的最佳时机 III
     * 执行用时：7 ms, 在所有 Java 提交中击败了34.84%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了57.14%的用户
     *
     * @param prices 股票价格数组
     * @return 最大收益
     */
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        //0:第一次购入,1:第一次出售,2:第二次购入,3:第二次出售
        int[][] dp = new int[len][4];
        dp[0][0] = -prices[0];
        for (int i = 0; i < len; i++) {
            dp[i][2] = Integer.MIN_VALUE;
        }
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] + prices[i], dp[i - 1][3]);
        }
        int res = 0;
        for (int i : dp[len - 1]) {
            res = Math.max(i, res);
        }
        return res;
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 执行用时：2 ms, 在所有 Java 提交中击败了22.40%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了11.11%的用户
     * @param prices 股票价格数组
     * @return 最大收益
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 0) {
            return 0;
        }
        //0:购入,1:出售,2:冷冻期
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];
        dp[0][2] = Integer.MIN_VALUE / 3;
        dp[1][2] = Integer.MIN_VALUE / 3;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][0], -prices[i]), dp[i - 1][2] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
            if (i < 2) {
                continue;
            }
            dp[i][2] = Math.max(dp[i - 2][0] + prices[i - 1], dp[i - 1][2]);
        }
        return Math.max(dp[len - 1][1], dp[len - 1][2]);
    }


    public static void main(String[] args) {
        SolutionVmware solutionVmware = new SolutionVmware();
        String[] strings = {"flower", "flow", "flight"};
        int[] arr = {3, 3, 12, 8};
        TreeNode t = new TreeNode(3);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(3);
        t.left = t1;
        t.right = t2;
        t1.right = t3;
        t2.right = t4;
        int[][] goAhead = new int[][]{{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        char[] chars = {'d', 'c', 'e', 'a', 'f', 'g', 'b'};
        int[] brr = {1, 2, 3, 4, 5};
        int f = solutionVmware.maxProfit(brr);
        System.out.println(f);
    }
}
