//import jdk.nashorn.api.tree.Tree;

import data.ListNode;
import data.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.*;

/**
 * @author ：Pheonix
 * @version ：1.0
 * @date ：Created in 上午10:30 18年11月14日
 * @description ：LeetCode
 * @modified By ：
 */
public class SolitionUbuntu {
    /**
     * 103
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> re = new ArrayList<>();
        Stack<TreeNode> st1 = new Stack<TreeNode>();
        Stack<TreeNode> st2 = new Stack<TreeNode>();
        if (root != null) {
            if (root.left != null) {
                st1.push(root.left);
            }
            if (root.right != null) {
                st1.push(root.right);
            }
            List<Integer> rr = new ArrayList<>();
            rr.add(root.val);
            re.add(rr);
            int useL = 1;
            while (!st1.isEmpty() || !st2.isEmpty()) {
                List<Integer> rr2 = new ArrayList<>();
                if (useL == 1) {
                    while (!st1.isEmpty()) {
                        TreeNode t = (TreeNode) st1.pop();
                        rr2.add(t.val);
                        if (t.right != null) {
                            st2.push(t.right);
                        }
                        if (t.left != null) {
                            st2.push(t.left);
                        }
                    }
                    useL = 0;
                } else {
                    while (!st2.isEmpty()) {
                        TreeNode t = (TreeNode) st2.pop();
                        rr2.add(t.val);
                        if (t.left != null) {
                            st1.push(t.left);
                        }
                        if (t.right != null) {
                            st1.push(t.right);
                        }
                    }
                    useL = 1;
                }
                re.add(rr2);
            }
        }
        return re;
    }

    public static int findDeep(TreeNode root, int deep) {
        if (root.left == null && root.right == null) {
            return deep;
        }
        if (root.left != null) {
            deep = findDeep(root.left, deep + 1);
        }
        if (root.right != null) {
            deep = findDeep(root.right, deep + 1);
        }
        return deep;
    }

    /**
     * 227
     */
    public int nthMagicalNumber(int N, int A, int B) {
        //求最大公约数
        long a = A > B ? A : B;
        long b = A > B ? B : A;
        long C = a % b;
        if (C == 0) {
            C = b;
        } else {
            while (C != 0) {
                a = b;
                b = C;
                C = a % b;
            }
            C = b;
        }
        //求遇见一次最小公倍数有几次神奇数字
        long p = A / C + B / C - 1;
        long q = N % p;
        long s = N / p;
        //求第q个神奇数字
        long ss = 0;
        a = A > B ? A : B;
        b = A > B ? B : A;
        if (0 == q) {
            ss = 0;
        } else if (1 == q) {
            ss = b;
        } else {
            long c = a;
            long d = b;
            char f = 'd';
            for (int i = 1; i < q; i++) {
                if (c > d && c < d + b) {
                    c = c + a;
                    f = 'e';
                } else if (d + b > c && d + b < c + a) {
                    d = d + b;
                    f = 'd';
                } else if (d + b < c) {
                    d = d + b;
                    f = 'd';
                } else if (d + b > c + a) {
                    c = c + a;
                    f = 'c';
                }
            }
            if (f == 'e') {
                ss = c - a;
            } else if ('c' == f) {
                ss = c;
            } else {
                ss = d;
            }
        }
        //取模
        long sss = ss;
        for (int i = 0; i < s; i++) {
            sss = sss + A / C * B;
            if (sss > 1000000007) {
                sss = sss % 1000000007;
            }
        }
        return (int) sss;
    }

    /**
     * 508
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        ArrayList tlist = new ArrayList();

        //后序遍历树求子树元素和
        Stack<TreeNode> stack = new Stack<TreeNode>();
        HashMap map = new HashMap();
        stack.push(root);
        if (root == null) {
            int[] zx = {};
            return zx;
        }
        if (root.left == null && root.right == null) {
            int[] zx = {root.val};
            return zx;
        }
        int value = 0;
        while (!stack.isEmpty()) {
            TreeNode t = stack.peek();

            if (t.right != null && !tlist.contains(t.right)) {
                stack.push(t.right);
            }
            if (t.left != null && !tlist.contains(t.left)) {
                stack.push(t.left);
            }

            if (t.left == null && t.right == null) {
                tlist.add(t);

                if (map.containsKey(t.val)) {
                    map.put(t.val, (int) map.get(t.val) + 1);
                    if ((int) map.get(t.val) > value) {
                        value = (int) map.get(t.val);
                    }
                } else {
                    map.put(t.val, 1);
                    value = value == 0 ? 1 : value;
                }
                stack.pop();
            } else if (t.left == null && tlist.contains(t.right)) {
                t.val = t.val + t.right.val;
                tlist.add(t);

                if (map.containsKey(t.val)) {
                    map.put(t.val, (int) map.get(t.val) + 1);
                    if ((int) map.get(t.val) > value) {
                        value = (int) map.get(t.val);
                    }
                } else {
                    map.put(t.val, 1);
                    value = value == 0 ? 1 : value;
                }
                stack.pop();
            } else if (tlist.contains(t.left) && t.right == null) {
                t.val = t.val + t.left.val;
                tlist.add(t);

                if (map.containsKey(t.val)) {
                    map.put(t.val, (int) map.get(t.val) + 1);
                    if ((int) map.get(t.val) > value) {
                        value = (int) map.get(t.val);
                    }
                } else {
                    map.put(t.val, 1);
                    value = value == 0 ? 1 : value;
                }
                stack.pop();
            } else if (tlist.contains(t.left) && tlist.contains(t.right)) {
                t.val = t.val + t.left.val + t.right.val;
                tlist.add(t);

                if (map.containsKey(t.val)) {
                    map.put(t.val, (int) map.get(t.val) + 1);
                    if ((int) map.get(t.val) > value) {
                        value = (int) map.get(t.val);
                    }

                } else {
                    map.put(t.val, 1);
                    value = value == 0 ? 1 : value;
                }
                stack.pop();
            }
        }
        //求子树元素和出现次数最多的数
        List keyList = new ArrayList();
        for (Object getKey : map.keySet()) {
            if (map.get(getKey).equals(value)) {
                keyList.add(getKey);
            }
        }
        int[] arr = new int[keyList.size()];
        for (int i = 0; i < keyList.size(); i++) {
            arr[i] = (int) keyList.get(i);
        }
        return arr;

    }

    /**
     * 872
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        boolean re = true;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        } else if (root2 == null) {
            return false;
        } else {
            s1.push(root1);
            s2.push(root2);
            List lp = new ArrayList();
            int j = 0;
            while (!s1.isEmpty()) {
                TreeNode p = s1.pop();
                if (p.right != null) {
                    s1.push(p.right);
                }
                if (p.left != null) {
                    s1.push(p.left);
                }
                if (p.left == null && p.right == null) {
                    lp.add(p.val);
                }
            }
            while (!s2.isEmpty()) {
                TreeNode p = s2.pop();
                if (p.right != null) {
                    s2.push(p.right);
                }
                if (p.left != null) {
                    s2.push(p.left);
                }
                if (p.left == null && p.right == null) {
                    if (p.val != (int) lp.get(j) || j >= lp.size()) {
                        re = false;
                        break;
                    }
                    j++;
                }
            }
            return re;
        }
    }

    /**
     * 709
     */
    public String toLowerCase(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] + 32);
            }
        }
        return ch.toString();
    }

    /**
     * 461
     */
    public int hammingDistance(int x, int y) {
        int res = x ^ y;
        int resu = 0;
        while (res != 0) {
            if (res % 2 == 1) {
                resu++;
            }
            res = res / 2;
        }
        return resu;
    }

    /**
     * 771
     */
    public int numJewelsInStones(String J, String S) {
//        char strJ[] = J.toCharArray();
//        char strS[] = S.toCharArray();
//        int result = 0;
////        List<Character> listJ= new ArrayList<Character>(Arrays.asList(strJ));
////        List<Character> listS= new ArrayList<Character>(Arrays.asList(strS));
//        for(int i = 0;i < strS.length;i++){
//            for(int j = 0; j < strJ.length;j++){
//                if(strS[i] == strJ[j]){
//                    result++;
//                    break;
//                }
//            }
//        }
//        return result;
        char[] cj = J.toCharArray();
        char[] cs = S.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : cj) {
            set.add(c);
        }
        int re = 0;
        for (char c : cs) {
            if (set.contains(c)) {
                re++;
            }
        }
        return re;
    }

    /**
     * 237
     */
    public void deleteNode(ListNode node) {
        if (node.next != null) {
            ListNode p = node.next;
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    /**
     * 338
     */
    public int[] countBits(int num) {
        int[] re = new int[num + 1];
        if (num > 2) {
            re[0] = 0;
            re[1] = 1;
            re[2] = 1;
            int k = 2;
            int j = 3;
            while (j <= num) {
                if (j == k * 2) {
                    k = k * 2;
                }
                re[j] = 1 + re[j - k];
                j++;
            }
        } else if (num == 0) {
            re[0] = 0;
        } else if (1 == num) {
            re[0] = 0;
            re[1] = 1;
        } else if (2 == num) {
            re[0] = 0;
            re[1] = 1;
            re[2] = 1;
        }
        return re;
    }

    /**
     * 476
     */
    public int findComplement(int num) {
        int[] arr = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535,
                131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863,
                134217727, 268435455, 536870911, 1073741823, 2147483647};
        int i = 0;
        for (; i < 32; i++) {
            if (num <= arr[i]) {
                break;
            }
        }
        return arr[i] - num;
    }

    /**
     * 728
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = left; i <= right; i++) {
            int j = i;
            boolean b = true;
            int k = j % 10;
            while (j / 10 != 0) {
                if (k == 0 || i % k != 0) {
                    b = false;
                    break;
                }
                j = j / 10;
                k = j % 10;
            }
            if (k == 0 || i % k != 0) {
                b = false;
            }
            if (b == true) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 463
     */
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) {
                        res++;
                    }
                    if (i == grid.length - 1 || grid[i + 1][j] == 0) {
                        res++;
                    }
                    if (j == 0 || grid[i][j - 1] == 0) {
                        res++;
                    }
                    if (j == grid[i].length - 1 || grid[i][j + 1] == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    //390
//    public int lastRemaining(int n) {
//        int flag = 1;//->
//        int i = n;
//        int j = 0;
//        while(true){
//            i = i / 2;
//            flag = flag == 1? 0 : 1;
//            j++;
//        }
//        if(flag == 1){
//
//        }
//        else{
//
//        }
//    }
//    public static int[] res(int [] sou, int flag){
//        int []re = new int [(sou.length)/2];
//        if(flag == 1) {
//            for (int i = 0; i < re.length; i++) {
//                re[i] = sou[2 * i];
//            }
//        }
//        else {
//            for (int i = 0; i < re.length; i++) {
//                re[i] = sou[2 * i];
//            }
//        }
//        return re;}

    /**
     * 810
     */
    public boolean xorGame(int[] nums) {
        int x = 0, n = nums.length;
        for (int num : nums) {
            x ^= num;
        }
        return x == 0 || n % 2 == 0;
    }

    /**
     * 331
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder == null) {
            return false;
        }
        if (preorder.equals("#")) {
            return true;
        }
        String[] strings = preorder.split(",");
        if (strings[0].equals("#")) {
            return false;
        }
        TreeNode root = new TreeNode(Integer.valueOf(strings[0]));
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int i = 1;
        while (!stack.empty() && i < strings.length) {
            TreeNode tt = stack.pop();
            if (tt == null) {
                if (!strings[i].equals("#")) {
                    tt = new TreeNode(Integer.valueOf(strings[i]));
                    stack.push(tt.right);
                    stack.push(tt.left);
                }
                i++;
            } else {
                stack.push(tt.right);
                stack.push(tt.left);
            }
        }
        if (stack.empty() && i == strings.length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 786:超时
     */
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        if (A.length <= 1) {
            int[] res = new int[1];
            return res;
        }
        double[] res = new double[K];
        List<int[]> list = new LinkedList();
        for (int i = 0; i < K; i++) {
            if (K < A.length) {
                res[i] = 1.0 / (double) A[A.length - K];
                int[] tt = {1, A[A.length - K]};
                list.add(tt);
            } else {
                res[i] = 1.0;
                int[] tt = {1, 1};
                list.add(tt);
            }
        }
        double key = res[0];
        int x = 0, y = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                double t = (double) A[i] / (double) A[j];
                if (t < res[K - 1]) {
                    int k = K - 1;
                    while (k >= 0) {
                        if (t > res[k]) {
                            break;
                        }
                        k--;
                    }
                    k++;
                    for (int ii = K - 1; ii > k; ii--) {
                        res[ii] = res[ii - 1];
                        list.set(ii, list.get(ii - 1));
                    }
                    res[k] = t;
                    int[] tt = {A[i], A[j]};
                    list.set(k, tt);
                }
            }
        }
        return list.get(K - 1);
    }


    /**
     * 258
     */
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        int m = num % 9;
        if (m != 0) {
            return m;
        }
        return 9;
    }

    /**
     * 260
     */
    public int[] singleNumber(int[] nums) {
        int[] re = new int[2];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum ^= nums[i];
        }

        int flag = sum & (~(sum - 1));

        for (int num : nums) {
            if ((num & flag) == 0) {
                re[0] ^= num;
            } else {
                re[1] ^= num;
            }
        }

        return re;

    }


    /**
     * 125. 验证回文串
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.91%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了7.14%的用户
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len <= 1) {
            return true;
        }
        int i = 0, j = len - 1;
        while (i <= j) {
            while (i <= j && allToInteger(chars[i]) == -1) {
                i++;
            }
            while (i <= j && allToInteger(chars[j]) == -1) {
                j--;
            }
            if (i <= j) {
                if (allToInteger(chars[i]) != allToInteger(chars[j])) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }

    public int allToInteger(char a) {
        if (48 <= (int) a && (int) a <= 57) {
            return a;
        } else if (65 <= (int) a && (int) a <= 90) {
            return a;
        } else if (97 <= (int) a && (int) a <= 122) {
            return a - 32;
        } else {
            return -1;
        }
    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        dp[0][0] = true;
        // here's the p's length, not s's
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                // here's y axis should be i+1
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                //如果是任意元素 或者是对于元素匹配
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    //如果前一个元素不匹配 且不为任意元素
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                            /*
                            dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
                            or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                            or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                             */

                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 题目：42. 接雨水
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了12.86%的用户
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        int i = 0, j = len - 1;
        int sum = 0;
        int all = 0;
        int max = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                sum = sum + height[i];
                max = height[i] > max ? height[i] : max;
                all = all + max;
                i++;
            } else {
                sum = sum + height[j];
                max = height[j] > max ? height[j] : max;
                all = all + max;
                j--;
            }
        }
        return all - sum;
    }


    public void quickSort(int[] a, int l, int h) {
        if (l > h) {
            return;
        }
        int b = l, e = h;
        int k = a[l];
        while (b < e) {
            while (b < e && a[e] <= k) {
                e--;
            }
            if (b < e) {
                a[b] = a[e];
                b++;
            }
            while (a[b] >= k && b < e) {
                b++;
            }
            if (b < e) {
                a[e] = a[b];
                e--;
            }
        }
        /*此时i==j*/
        a[b] = k;

        /*递归调用，把key前面的完成排序*/
        this.quickSort(a, l, b - 1);


        /*递归调用，把key后面的完成排序*/
        this.quickSort(a, e + 1, h);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        TreeNode r2 = new TreeNode(3);
        TreeNode r3 = new TreeNode(4);
        TreeNode r4 = new TreeNode(5);
//        data.TreeNode r5 = new data.TreeNode(15);
        root.left = r1;
        r1.left = r2;
        r2.left = r3;
        r3.left = r4;
        SolitionUbuntu solution = new SolitionUbuntu();
        String[] strings = {"9", "3", "4", "#", "#", "1", "#", "#,", "#", "6", "#", "#"};
        int[] ints = {0, 1, 0, 9};
        System.out.println(solution.singleNumber(ints));
//        int[] arr = solution.findFrequentTreeSum(root);
        //List l = solution.zigzagLevelOrder(root);
//        System.out.print(solution.nthMagicalNumber(1,2,3));
    }
}
