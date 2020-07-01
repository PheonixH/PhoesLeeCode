import data.TreeNode;

import java.util.*;


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
        int len = Math.min(first.length, last.length);
        String res = "";
        for (int i = 0; i < len; i++) {
            if (first[i] != last[i]) {
                break;
            }
            res = res + first[i];
        }
        return res;
    }

    /**
     * 执行用时 :46 ms, 在所有 Java 提交中击败了82.99%的用户
     * 内存消耗 :65.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[][] multiSearch(String big, String[] smalls) {
        Trie tree = new Trie(smalls);
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

    class Trie {
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

        public Trie(String[] strings) {
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
            for (int i = 0; i < len; i++) {
                int c = chars[i] - 97;
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
            for (int i = 0; i < len; i++) {
                int c = chars[i] - 97;

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
        root.left = null;
        return newRoot;
    }


    /**
     * 1014. 最佳观光组合
     * 执行用时 :9 ms, 在所有 Java 提交中击败了5.64%的用户
     * 内存消耗 :47.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxScoreSightseeingPair(int[] A) {
        int len = A.length;
        int[] arrayI = new int[len], arrayJ = new int[len], arrayJmaxI = new int[len];
        arrayI[0] = A[0];
        arrayJmaxI[0] = -1;
        int max = 0;
        for (int i = 1; i < len; i++) {
            arrayI[i] = A[i] + i;
            arrayJ[i] = A[i] - i;
            arrayJmaxI[i] = Math.max(arrayI[i - 1], arrayJmaxI[i - 1]);
            max = Math.max(max, arrayJ[i] + arrayJmaxI[i]);
        }
        return max;
    }

    /**
     * 执行用时 :4 ms, 在所有 Java 提交中击败了87.77%的用户
     * 内存消耗 :48.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxScoreSightseeingPair0(int[] A) {
        int len = A.length;
        int keyI = A[0], keyJ = 0, maxI = -1;
        int max = 0;
        for (int i = 1; i < len; i++) {
            maxI = Math.max(keyI, maxI);
            keyI = A[i] + i;
            keyJ = A[i] - i;
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
            if (obstacleGrid[0][0] == 00) {
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
                    List<Integer> newp = new ArrayList<>();
                    newp.add(x);
                    newp.add(y);
                    preLoad.add(newp);
                    return preLoad;
                }
                String newKey = x + ":" + y;
                if (visited.contains(newKey)) {
                    continue;
                } else if (obstacleGrid[x][y] == 1) {
                    visited.add(newKey);
                } else {
                    List<Integer> newp = new ArrayList<>();
                    newp.add(x);
                    newp.add(y);
                    stack.push(newp);
                    List<List<Integer>> newList = new ArrayList<>();
                    for (List<Integer> li : preLoad) {
                        newList.add(li);
                    }
                    newList.add(newp);
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
                if ("".equals(chars[i])) {
                } else {
                    break;
                }
            }
            if (i == chars.length) {
                break;
            }
            k = i - k;
            if (k > father) {
                TreeNode fatherTree = map.get(k);
                TreeNode newTree = new TreeNode(Integer.parseInt(chars[i]));
                fatherTree.left = newTree;
                father = k;
                if (map.size() <= k + 1) {
                    map.add(newTree);
                } else {
                    map.set(k + 1, newTree);
                }
            } else {
                TreeNode fatherTree = map.get(k);
                TreeNode newTree = new TreeNode(Integer.parseInt(chars[i]));
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
        Stack<int[]> isAround = new Stack();
        int oldColor = image[sr][sc];
        int[] point = {sr, sc};
        isAround.push(point);
        isVisited.add(point[0] + ":" + point[1]);
        int maxr = image.length, maxc = image[0].length;
        while (!isAround.isEmpty()) {
            int[] tmpPoint = isAround.pop();
            image[tmpPoint[0]][tmpPoint[1]] = newColor;
            for (int[] m : maybeAround) {
                if (m[0] + tmpPoint[0] >= 0 && m[0] + tmpPoint[0] < maxr && m[1] + tmpPoint[1] >= 0 && m[1] + tmpPoint[1] < maxc) {
                    if (image[m[0] + tmpPoint[0]][m[1] + tmpPoint[1]] == oldColor) {
                        int[] newp = {m[0] + tmpPoint[0], m[1] + tmpPoint[1]};
                        if (!isVisited.contains(newp[0] + ":" + newp[1])) {
                            isAround.push(newp);
                            isVisited.add(newp[0] + ":" + newp[1]);
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
        if ((sr < 0 || sr >= x) || (sc < 0 || sc >= y)) {
            return image;
        }
        int oldColor = image[sr][sc];
        return floodFill(image, sr, sc, oldColor, newColor);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int oloColor, int newColor) {
        int x = image.length;
        int y = image[0].length;
        if ((sr < 0 || sr >= x) || (sc < 0 || sc >= y)) {
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
    public boolean isUnique(String astr) {
        for (int i = 0; i < astr.length(); i++) {
            String s = astr;
            s = s.replace(String.valueOf(s.charAt(i)), "");
            if (s.length() != astr.length() - 1) {
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
    public boolean isUnique0(String astr) {
        for (char ch : astr.toCharArray()) {
            if (astr.indexOf(ch) != astr.lastIndexOf(ch)) {
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
            if (map1.get(s1.charAt(i)) != map2.get(s1.charAt(i))) {
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
    public String replaceSpaces0(String S, int length) {
        char[] chars = S.toCharArray();
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
    public String replaceSpaces(String S, int length) {
        int num = 0;
        for (int i = 0; i < length; i++) {
            if (' ' == S.charAt(i)) {
                num++;
            }
        }
        String news = S.replace(" ", "%20");
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
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j));
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
        if (Math.min(C, G) > Math.max(A, E) && Math.min(D, H) > Math.max(B, F)) {
            //x轴（有可能是负数）：Math.min(C,G) - Math.max(A,E)
            //y轴（有可能是负数）：Math.min(D,H) - Math.max(B,F)
            //最后结果取绝对值
            overArea = Math.abs((Math.min(C, G) - Math.max(A, E)) *
                    (Math.min(D, H) - Math.max(B, F)));
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
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addAll(posMap.get(S)); // 加入所有车信息
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
        for (int i = 0; i < routes.length; i++) {
            HashSet s = new HashSet<Integer>();
            for (int j = 0; j < routes[i].length; j++) {
                s.add(routes[i][j]);
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
                ans = Math.min(ans, bound - (i - 1));
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
                ans = Math.min(ans, end - start + 1);
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
     * @param a
     * @param b
     * @return
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
     * @param A
     * @param target
     * @return
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
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
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
            if(!map.containsKey(tmp)){
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


    public static void main(String[] args) {
        SolutionVmware solutionVmware = new SolutionVmware();
        String[] strings = {"flower", "flow", "flight"};
        int[] arr = {3, 3, 1};
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
        int[][] goAhead = new int[][]{{0, 1}, {1, 2}, {1, 3}, {1, 10}, {1, 11}, {1, 4}, {2, 4}, {2, 6},
                {2, 9}, {2, 10}, {2, 4}, {2, 5}, {2, 10}, {3, 7}, {3, 7}, {4, 5}, {4, 11}, {4, 11}, {4, 10},
                {5, 7}, {5, 10}, {6, 8}, {7, 11}, {8, 10}};
        char[] chars = {'d', 'c', 'e', 'a', 'f', 'g', 'b'};
        int[] brr = {1, 3, 5, 4, 9, 12, 5, 8, 4, 4};
        boolean f = solutionVmware.findWhetherExistsPath(12, goAhead, 2, 3);
        System.out.println(f);
    }
}
