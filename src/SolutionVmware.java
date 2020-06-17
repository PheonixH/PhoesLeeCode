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

    public static void main(String[] args) {
        SolutionVmware solutionVmware = new SolutionVmware();
        String[] strings = {"flower", "flow", "flight"};
        int[] arr = {3, 4, 5, 6, 1, 1, 2, 0};
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
        int[][] goAhead = new int[][]{{0}, {0}, {0}};
        int i = solutionVmware.findRepeatNumber0(arr);
        System.out.println(i);
    }
}
