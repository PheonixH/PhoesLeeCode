package leetcode;//import jdk.nashorn.api.tree.Tree;

import leetcode.datestruct.Nodes;

import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.*;

import leetcode.datestruct.TreeNode;
import leetcode.datestruct.ListNode;

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
        if ("#".equals(preorder)) {
            return true;
        }
        String[] strings = preorder.split(",");
        if ("#".equals(strings[0])) {
            return false;
        }
        TreeNode root = new TreeNode(Integer.valueOf(strings[0]));
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int i = 1;
        while (!stack.empty() && i < strings.length) {
            TreeNode tt = stack.pop();
            if (tt == null) {
                if (!"#".equals(strings[i])) {
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

    //786 -- 14ms -- 89.29%
    public int[] kthSmallestPrimeFraction1(int[] A, int K) {
        double lo = 0.0, hi = 1.0;
        int[] ans = {-1, -1};

        while (hi - lo > 1e-9) {
            // 一直压缩精确度到1e-9
            double mid = lo + (hi - lo) / 2.0;
            int[] res = under(A, mid);
            if (res[2] < K) {
                lo = mid;
            } else {
                hi = mid;
                ans[0] = res[0];
                ans[1] = res[1];
            }
        }

        return ans;
    }

    private int[] under(int[] A, double bound) {
        // 计算有多少数小于bound
        int mol = 0, deno = 1, count = 0, i = -1;
        for (int j = 1; j < A.length; j++) {
            while (A[i + 1] < bound * A[j]) {
                i++;
            }

            count += i + 1;

            if (i >= 0 && mol * A[j] < deno * A[i]) {
                mol = A[i];
                deno = A[j];
            }
        }
        return new int[]{mol, deno, count};
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

    //14 -- 16ms -- 18.25% -- 横向比较
    public String longestCommonPrefix0(String[] strs) {
        String res = "";
        if (strs.length == 0) {
            return res;
        }
        String begin = strs[0];
        if (strs.length == 1) {
            return begin;
        }
        for (int i = 1; i < strs.length; i++) {
            int bl = begin.length();
            String s = strs[i];
            int k = s.length() > bl ? bl : s.length();
            StringBuffer r = new StringBuffer();
            for (int j = 0; j < k; j++) {
                if (s.charAt(j) == begin.charAt(j)) {
                    r.append(s.charAt(j));
                } else {
                    break;
                }
            }
            begin = r.toString();
            if (begin.equals(res)) {
                return res;
            }
        }
        return begin;
    }

    //14 -- 8ms -- 83.66% -- 纵向比较
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder sb = new StringBuilder();
        if (strs.length > 1) {
            int len = strs[0].length();
            for (int i = 0; i < len; i++) {
                char curr = strs[0].charAt(i);
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].length() <= i || strs[j].charAt(i) != curr) {
                        return sb.toString();
                    }
                    if (strs[j].charAt(i) == curr && j == strs.length - 1) {
                        sb.append(curr);
                    }
                }
            }
        }
        return sb.toString();
    }

    //917 -- 13ms -- 18.45%
    public String reverseOnlyLetters(String S) {
        String res = "";
        char[] cs = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : cs) {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                stack.add(c);
            }
        }
        for (char c : cs) {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                res = res + stack.pop();
            } else {
                res = res + c;
            }
        }
        return res;
    }

    //378 -- 39ms -- 37.86%
    public int kthSmallest0(int[][] matrix, int k) {
        int[] ints = new int[matrix.length];
        int[] is = new int[matrix.length];
        int j = 0;
        for (int[] i : matrix) {
            ints[j] = i[0];
            is[j++] = 0;
        }
        j = 0;
        int res = matrix[0][0];
        while (j < k) {
            int min = 0;
            int m = ints[0];
            for (int i = 1; i < ints.length; i++) {
                if (m > ints[i]) {
                    min = i;
                    m = ints[i];
                }
            }
            res = ints[min];
            if (is[min] + 1 >= matrix[min].length) {
                ints[min] = Integer.MAX_VALUE;
            } else {
                ints[min] = matrix[min][++is[min]];
            }
            j++;
        }
        return res;
    }

    //378 -- 最大堆 -- 26ms -- 51.96%
    public int kthSmallest(int[][] m, int k) {
        /*
        26ms
        int[] a = new int[m.length * m.length];
        for(int i = 0;i < m.length;i++){
            for(int j = 0;j < m[i].length;j++){
                a[i * m.length + j] = m[i][j];
            }
        }
        Arrays.sort(a);
        return a[k - 1];
        */
        //堆排序 15ms (建堆整理堆真的是好麻烦啊。。。)
        if (k == m.length * m[0].length) {
            return m[m.length - 1][m[0].length - 1];
        }
        int[] heap = generateHeap(m);
        int count = 1;
        while (count <= k) {
            heapSort(heap, count);
            //System.out.println(heap[heap.length - count]);
            count++;
        }
        return heap[heap.length - count + 1];
    }

    private int[] generateHeap(int[][] m) {
        int[] heap = new int[m.length * m.length + 1];
        heap[0] = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                heap[i * m[0].length + j + 1] = m[i][j];
            }
        }
        /*
        建堆，向上调整，可能包含向下调整
        */
        for (int i = (heap.length - 1) / 2; i > 0; i--) {
            int l = 2 * i, r = 2 * i + 1;
            if (r >= heap.length - 1) {
                if (heap[l] < heap[i]) {
                    swap(heap, l, i);
                    downToHeap(heap, l, 1);
                }
            } else if (heap[l] < heap[r] && heap[l] < heap[i]) {
                swap(heap, l, i);
                downToHeap(heap, l, 1);
            } else if (heap[r] <= heap[l] && heap[r] < heap[i]) {
                swap(heap, r, i);
                downToHeap(heap, r, 1);
            }
        }
        /*
        for(int num:heap){
            System.out.print(num + " ");
        }
        System.out.println();
        */
        return heap;
    }

    /*
    向下调整，在建堆和堆整理过程中都可能会用到
    */
    private void downToHeap(int[] heap, int i, int count) {
        while (i * 2 < heap.length - count) {
            int l = 2 * i, r = 2 * i + 1;
            if (r == heap.length - count) {
                if (heap[l] <= heap[i]) {
                    swap(heap, l, i);
                    i = l;
                } else {
                    break;
                }
            } else {
                if (heap[l] < heap[r] && heap[l] <= heap[i]) {
                    swap(heap, l, i);
                    i = l;
                } else if (heap[r] <= heap[l] && heap[r] < heap[i]) {
                    swap(heap, r, i);
                    i = r;
                } else {
                    break;
                }

            }
        }
    }

    private void heapSort(int[] heap, int count) {
        swap(heap, 1, heap.length - count);
        //System.out.println("ok " + heap[heap.length - count]);
        downToHeap(heap, 1, count);
    }

    private void swap(int[] a, int i1, int i2) {
        int tmp = a[i1];
        a[i1] = a[i2];
        a[i2] = tmp;
    }

    //136 -- 1ms -- 99.46%
    public int singleNumber1(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    //137 -- 7ms -- 43.92%
    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                set1.add(n);
            } else {
                set.add(n);
            }
        }
        int res = 0;
        for (int n : nums) {
            if (set1.contains(n)) {
                continue;
            } else {
                res = n;
                break;
            }
        }
        return res;
    }

    //137 -- 类似异或 -- 1ms -- 96.76%
    /*
    * 【笔记】网上大佬曾经说，如果能设计一个状态转换电路，使得一个数出现3次时能自动抵消为0，最后剩下的就是只出现1次的数。

开始设计：一个二进制位只能表示0或者1。也就是天生可以记录一个数出现了一次还是两次。

x ^ 0 = x;
x ^ x = 0;
要记录出现3次，需要两个二进制位。那么上面单独的x就不行了。我们需要两个变量，每个变量取一位：

ab ^ 00 = ab;
ab ^ ab = 00;
这里，a、b都是32位的变量。我们使用a的第k位与b的第k位组合起来的两位二进制，表示当前位出现了几次。也就是，一个8位的二进制x就变成了16位来表示。

x = x[7] x[6] x[5] x[4] x[3] x[2] x[1] x[0]

x = (a[7]b[7]) (a[6]b[6]) ... (a[1]b[1]) (a[0]b[0])

于是，就有了这一幕....

它是一个逻辑电路，a、b变量中，相同位置上，分别取出一位，负责完成00->01->10->00，也就是开头的那句话，当数字出现3次时置零。*/
    public int singleNumber0(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            a = (a ^ x) & ~b;
            b = (b ^ x) & ~a;
        }
        return a;
    }


    //877
    //执行用时 : 0 ms, 在Stone Game的Java提交中击败了100.00% 的用户
    //内存消耗 : 34.2 MB, 在Stone Game的Java提交中击败了7.50% 的用户
    public boolean stoneGame(int[] piles) {
        return true;
    }

    //877
    //
    public boolean stoneGame1(int[] piles) {
        int length = piles.length;
        //results[i][j]存储的是piles中第i个数到第j个数组成序列的最佳拿取方式下的得分
        int[][] results = new int[length][length];
        //当集合中只有一个堆的时候，拿的那个人直接得分
        for (int i = 0; i < length; i++) {
            results[i][i] = piles[i];
        }
        //当集合中有两个数的时候，先选的人肯定是拿较大数,分数为max-min
        for (int i = 0; i < length - 1; i++) {
            results[i][i + 1] = Math.abs(piles[i] - piles[i + 1]);
        }
        /**当集合中元素大于2时，先选的人从序列两头拿，可以分成两种情况
         *以ABC为例，可以拿A，剩余BC，后手会选择BC的最佳拿取方式，
         *所以先手得分为A-BC得分，即：results[i][j]=piles[i]-results[i+1][j]；
         *也可以拿C，剩余AB，同理有results[i][j]=piles[j]-results[i][j-1]；
         *选择分值较大的那个即可。上面两个式子都要求我们在求results[i][j]的时候知道
         *它的下面和左边一个格子的值，所以我们从下到上，从左到右计算填表。
         */
        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                results[i][j] = Math.max(piles[i] - results[i + 1][j], piles[j] - results[i][j - 1]);
            }
        }
        return results[0][length - 1] > 0;
    }

    //877
    public boolean stoneGame2(int[] piles) {
        //dp其实就是存储了递归过程中的数值
        //dps[i][j]代表从i到j所能获得的最大的绝对分数
        //（比如为1就说明亚历克斯从i到j可以赢李1分）
        //如何计算dps[i][j]呢:max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]);
        //这里减去dps数组是因为李也要找到最大的
        //最后dps=[5 2 4 1]
        //        [0 3 1 4]
        //        [0 0 4 1]
        //        [0 0 0 5]
        int n = piles.length;
        int[][] dps = new int[n][n];
        //dps[i][i]存储当前i的石子数
        for (int i = 0; i < n; i++) {
            dps[i][i] = piles[i];
        }
        //d=1,其实代表，先算两个子的时候
        for (int d = 1; d < n; d++) {
            //有多少组要比较
            for (int j = 0; j < n - d; j++) {
                //比较j到d+j
                dps[j][d + j] = Math.max(piles[j] - dps[j + 1][d + j], piles[d + j] - dps[j][d + j - 1]);
            }
        }
        return dps[0][n - 1] > 0;
    }

    //312
    //执行用时 : 24 ms, 在Burst Balloons的Java提交中击败了9.71% 的用户
    //内存消耗 : 36.2 MB, 在Burst Balloons的Java提交中击败了0.00% 的用户
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] nums2 = new int[n + 2];
        nums2[0] = 1;
        nums2[n + 1] = 1;
        for (int i = 1; i < n + 1; i++) {
            nums2[i] = nums[i - 1];
        }
        //dp[i][j] = max(dp[i][k-1]+dp[k+1][j]+nums2[i-1]*nums2[j+1]*nums[k])
        for (int i = 1; i < n + 1; i++) {
            dp[i][i] = nums2[i - 1] * nums2[i] * nums2[i + 1];
        }
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < n + 1 - j; i++) {
                int max;
                for (int k = i; k <= i + j; k++) {
                    int left = (k - 1 >= i) ? dp[i][k - 1] : 0;
                    int right = (k + 1 <= i + j) ? dp[k + 1][i + j] : 0;
                    dp[i][i + j] = Math.max(left + right + nums2[i - 1] * nums2[j + i + 1] * nums2[k], dp[i][i + j]);
                }
            }
        }
        return dp[1][n];
    }

    //983
    //执行用时 : 2 ms, 在Minimum Cost For Tickets的Java提交中击败了100.00% 的用户
    //内存消耗 : 33.7 MB, 在Minimum Cost For Tickets的Java提交中击败了100.00% 的用户
    public int mincostTickets(int[] days, int[] costs) {
        // 将从新年到某一天的花过的所有钱数全部记录起来。
        int[] lastAllDaysCost = new int[366];
        //  days的下标，确保遍历365天时，以便于知道下次旅游的日期。
        int dayIdx = 0;
        // 日，月，年的花费。
        int ticketDay = costs[0];
        int ticketWeek = costs[1];
        int ticketMonth = costs[2];
        // 因为是第一天，所以过去的总花费为0
        lastAllDaysCost[0] = 0;
        // lastAllCost[i] 是截至到今年的第 i 天的总花费.

        // 模拟新年的第一天跑到旅行的最后一天。
        for (int today = 1; today <= 365; today++) {
            if (dayIdx >= days.length) {
                break;
            }
            // 判断今天是否属于旅行日。
            if (days[dayIdx] != today) {
                // 如果这一天不旅行那么直接把上一天的过去总花费拿过来直接使用。
                lastAllDaysCost[today] = lastAllDaysCost[today - 1];
                continue;
            }
            // 开始等待下一个待旅行的日子到来。
            dayIdx++;
            // 如果一月前，买了月票，会不会更便宜？
            // 如果一周前，买了周票，会不会更便宜？
            // 如果都不会的话，那我暂时先买日票试试呗。
            lastAllDaysCost[today] = Math.min(Math.min(
                    lastAllDaysCost[Math.max(0, today - 1)] + ticketDay,
                    lastAllDaysCost[Math.max(0, today - 7)] + ticketWeek),
                    lastAllDaysCost[Math.max(0, today - 30)] + ticketMonth);
        }
        return lastAllDaysCost[days[days.length - 1]];
    }

    //714
    //执行用时 : 38 ms, 在Best Time to Buy and Sell Stock with Transaction Fee的Java提交中击败了8.76% 的用户
    //内存消耗 : 67.7 MB, 在Best Time to Buy and Sell Stock with Transaction Fee的Java提交中击败了0.00% 的用户
    public int maxProfit(int[] prices, int fee) {
        //n个交易日
        int n = prices.length;
        //截止第i天的资金 + 是否持有股票
        int[][] res = new int[n][2];
        //一开始无收益
        res[0][0] = 0;
        res[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            // 今日非持有股票：昨天没买 || 卖了昨天买的；
            res[i][0] = Math.max(res[i - 1][0], res[i - 1][1] + prices[i] - fee);
            // 今日持有股票：昨天买了 || 昨天没有但是今日买入；
            res[i][1] = Math.max(res[i - 1][1], res[i - 1][0] - prices[i]);
        }
        //返回最后一天不持有收益
        return res[n - 1][0];
    }

    //978
    //执行用时 : 12 ms, 在Longest Turbulent Subarray的Java提交中击败了92.13% 的用户
    //内存消耗 : 42.6 MB, 在Longest Turbulent Subarray的Java提交中击败了92.00% 的用户
    public int maxTurbulenceSize(int[] A) {
        int l = A.length;
        if (l == 0) {
            return 0;
        }
        int x0 = 1;
        int x1 = 1;
        int max0 = 1;
        int max1 = 1;
        for (int i = 1; i < l; i++) {
            int t0 = A[i] > A[i - 1] ? x1 + 1 : 1;
            x1 = A[i] < A[i - 1] ? x0 + 1 : 1;
            max0 = Math.max(max0, t0);
            max1 = Math.max(max1, x1);
            x0 = t0;
        }
        return Math.max(max0, max1);
    }


    //1012 -- 不懂
    //执行用时 : 3 ms, 在Numbers With Repeated Digits的Java提交中击败了85.53% 的用户
    //内存消耗 : 32.9 MB, 在Numbers With Repeated Digits的Java提交中击败了32.88% 的用户
    public int numDupDigitsAtMostN(int N) {
        String nowNstr = String.valueOf(N);
        char[] nowNchar = nowNstr.toCharArray();
        int nowNlen = nowNchar.length;
        boolean flag = false;
        int count = 0;
        int dp9 = 0;

        int firstN = Integer.parseInt(nowNchar[0] + "");//第一位
        dp9 = (int) ((firstN + 1) * Math.pow(10, nowNlen - 1) - 1);

        if (nowNlen == 1) {
            return N - N;
        }

        for (int i = 1; i < nowNlen; i++) {

            int indexN = Integer.parseInt(nowNchar[i] + "");//当前位

            if (flag) {
                break;
            }

            int re = 9 - indexN;//当前位的取值范围

            if (i == nowNlen - 1) {//最后一位和前几位比较
                for (int k = 0; k < nowNlen - 1; k++) {
                    int a = Integer.parseInt(nowNchar[k] + "");
                    if (indexN < a && a != 0) {
                        re--;
                    }
                }
                count += re;
                return N - dp9(dp9) + count;
            }

            for (int k = 0; k < i; k++) {//当前位和前几位比较,有几个减几
                int a = Integer.parseInt(nowNchar[k] + "");
                if (indexN < a && a != 0) {
                    re--;
                }
            }

            for (int j = i; j < nowNlen - 1; j++) {//当前位是i i=1时 开始为7
                re *= (9 - j);
            }

            count += re;

            for (int k = 0; k < i; k++) {//本次的数字前几位出现过
                int a = Integer.parseInt(nowNchar[k] + "");
                if (indexN == a) {
                    flag = true;
                    break;
                }
            }

        }

        return N - dp9(dp9) + count;
    }

    public int dp9(int N) {
        String nowNstr = String.valueOf(N);
        char[] nowNchar = nowNstr.toCharArray();
        int nowNlen = nowNchar.length;

        if (nowNlen == 1) {
            return N;
        } else {
            int first = Integer.parseInt(nowNchar[0] + "");
            int thisNum = first;

            for (int i = 1; i < nowNlen; i++) {
                thisNum *= (10 - i);
            }

            int newN = (int) (N - first * Math.pow(10, nowNlen - 1));

            return thisNum + dp9(newN);
        }
    }

    //188
    //执行用时 : 7 ms, 在Best Time to Buy and Sell Stock IV的Java提交中击败了64.16% 的用户
    //内存消耗 : 37.4 MB, 在Best Time to Buy and Sell Stock IV的Java提交中击败了38.35% 的用户
    public int maxProfit4(int k, int[] prices) {
        /**
         当k大于等于数组长度一半时, 问题退化为贪心问题此时采用 买卖股票的最佳时机 II
         的贪心方法解决可以大幅提升时间性能, 对于其他的k, 可以采用 买卖股票的最佳时机 III
         的方法来解决, 在III中定义了两次买入和卖出时最大收益的变量, 在这里就是k租这样的
         变量, 即问题IV是对问题III的推广, t[i][0]和t[i][1]分别表示第i比交易买入和卖出时
         各自的最大收益
         **/
        if (k < 1) {
            return 0;
        }
        if (k >= prices.length / 2) {
            return greedy(prices);
        }
        int[][] t = new int[k][2];
        for (int i = 0; i < k; ++i) {
            t[i][0] = Integer.MIN_VALUE;
        }
        for (int p : prices) {
            t[0][0] = Math.max(t[0][0], -p);
            t[0][1] = Math.max(t[0][1], t[0][0] + p);
            for (int i = 1; i < k; ++i) {
                t[i][0] = Math.max(t[i][0], t[i - 1][1] - p);
                t[i][1] = Math.max(t[i][1], t[i][0] + p);
            }
        }
        return t[k - 1][1];
    }

    private int greedy(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }


    //94
    //执行用时 : 1 ms, 在Binary Tree Inorder Traversal的Java提交中击败了94.73% 的用户
    //内存消耗 : 35.8 MB, 在Binary Tree Inorder Traversal的Java提交中击败了38.69% 的用户
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inorderTraversal(root, list);
        return list;
    }

    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorderTraversal(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, list);
        }
        return;
    }

    //112
    //执行用时 : 1 ms, 在Path Sum的Java提交中击败了94.48% 的用户
    //内存消耗 : 36.9 MB, 在Path Sum的Java提交中击败了81.79% 的用户
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.right == null && root.left == null && root.val == sum) {
            return true;
        }
        if (root.val >= sum) {
            return false;
        }
        boolean res = false;
        if (root.left != null) {
            root.left.val += root.val;
            res = hasPathSum(root.left, sum);
            if (res) {
                return res;
            }
        }
        if (root.right != null) {
            root.right.val += root.val;
            res = hasPathSum(root.right, sum);
            if (res) {
                return res;
            }
        }
        return res;
    }

    //112
    //执行用时 : 1 ms, 在Path Sum的Java提交中击败了94.48% 的用户
    //内存消耗 : 39.1 MB, 在Path Sum的Java提交中击败了23.33% 的用户
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum1(root.left, sum - root.val)
                || hasPathSum1(root.right, sum - root.val);
    }

    //236:LCA问题  ST算法(自我实现的ST算法不够简洁，效率不高）
    //执行用时 : 727 ms, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了5.01% 的用户
    //内存消耗 : 34.4 MB, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了85.15% 的用户
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> dpsR = new LinkedList<>();
        List<Integer> deep = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        HashMap<TreeNode, Integer> dp = new HashMap<>();
        TreeNode t = root;
        int d = 0;
        while (t != null || !stack.isEmpty()) {
            if (t != null) {
                stack.push(t);
                dpsR.add(t);
                deep.add(d);
                dp.put(t, d);
                d++;
                t = t.left;
            } else {
                t = stack.pop();
                d = dp.get(t);
                dpsR.add(t);
                deep.add(d);
                t = t.right;
                d++;
            }
        }
        int xp = -1, xq = -1;
        for (int i = 0; i < dpsR.size(); i++) {
            if (p == dpsR.get(i)) {
                xp = i;
            }
            if (q == dpsR.get(i)) {
                xq = i;
            }
            if (xp > 0 && xq > 0) {
                break;
            }
        }
        int min = Integer.MAX_VALUE;
        int key = -1;
        if (xq < xp) {
            xq = xp ^ xq;
            xp = xp ^ xq;
            xq = xp ^ xq;
        }
        for (int i = xp; i <= xq; i++) {
            if (deep.get(i) < min) {
                min = deep.get(i);
                key = i;
            }
        }
        return dpsR.get(key);
    }

    //236:LCA问题  递归解决
    //执行用时 : 15 ms, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了77.64% 的用户
    //内存消耗 : 36.5 MB, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了70.85% 的用户
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    //236
    //执行用时 : 16 ms, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了48.14% 的用户
    //内存消耗 : 35.8 MB, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了80.59% 的用户
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        /**
         注意p,q必然存在树内, 且所有节点的值唯一!!!
         递归思想, 对以root为根的(子)树进行查找p和q, 如果root == null || p || q 直接返回root
         表示对于当前树的查找已经完毕, 否则对左右子树进行查找, 根据左右子树的返回值判断:
         1. 左右子树的返回值都不为null, 由于值唯一左右子树的返回值就是p和q, 此时root为LCA
         2. 如果左右子树返回值只有一个不为null, 说明只有p和q存在与左或右子树中, 最先找到的那个节点为LCA
         3. 左右子树返回值均为null, p和q均不在树中, 返回null
         **/
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        } else if (left != null && right != null) {
            return root;
        } else {
            return left == null ? right : left;
        }
    }

    //617
    //执行用时 : 2 ms, 在Merge Two Binary Trees的Java提交中击败了96.85% 的用户
    //内存消耗 : 47 MB, 在Merge Two Binary Trees的Java提交中击败了75.92% 的用户
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    //654
    //执行用时 : 4 ms, 在Maximum Binary Tree的Java提交中击败了98.64% 的用户
    //内存消耗 : 38.7 MB, 在Maximum Binary Tree的Java提交中击败了94.24% 的用户
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int b, int e) {
        if (nums.length <= 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int max = nums[b];
        int j = b;
        for (int i = b + 1; i <= e; i++) {
            if (max < nums[i]) {
                max = nums[i];
                j = i;
            }
        }
        TreeNode nt = new TreeNode(max);
        if (b < j) {
            nt.left = constructMaximumBinaryTree(nums, b, j - 1);
        }
        if (j < e) {
            nt.right = constructMaximumBinaryTree(nums, j + 1, e);
        }
        return nt;
    }

    //1008
    //执行用时 : 2 ms, 在Construct Binary Search Tree from Preorder Traversal的Java提交中击败了91.20% 的用户
    //内存消耗 : 33.7 MB, 在Construct Binary Search Tree from Preorder Traversal的Java提交中击败了99.64% 的用户
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, 0, preorder.length);
    }

    public TreeNode bstFromPreorder(int[] preorder, int b, int e) {
        if (preorder.length <= 0 || b > e) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        TreeNode nt = new TreeNode(preorder[b]);
        int key = b;
        while (key < e && preorder[key] <= preorder[b]) {
            key++;
        }
        if (b + 1 < key) {
            nt.left = bstFromPreorder(preorder, b + 1, key);
        }
        if (key < e) {
            nt.right = bstFromPreorder(preorder, key, e);
        }
        return nt;
    }

    //226
    //执行用时 : 1 ms, 在Invert Binary Tree的Java提交中击败了38.17% 的用户
    //内存消耗 : 33.2 MB, 在Invert Binary Tree的Java提交中击败了93.58% 的用户
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode t = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(t);
        return root;
    }

    //104
    //执行用时 : 1 ms, 在Maximum Depth of Binary Tree的Java提交中击败了89.15% 的用户
    //内存消耗 : 35.9 MB, 在Maximum Depth of Binary Tree的Java提交中击败了86.01% 的用户
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    public int maxDepth(TreeNode root, int deep) {
        if (root == null) {
            return deep;
        }
        deep++;
        deep = Math.max(maxDepth(root.left, deep), maxDepth(root.right, deep));
        return deep;
    }

    //814
    //执行用时 : 1 ms, 在Binary Tree Pruning的Java提交中击败了89.56% 的用户
    //内存消耗 : 33.4 MB, 在Binary Tree Pruning的Java提交中击败了99.30% 的用户
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    //894
    //执行用时 : 13 ms, 在All Possible Full Binary Trees的Java提交中击败了47.06% 的用户
    //内存消耗 : 51.5 MB, 在All Possible Full Binary Trees的Java提交中击败了58.90% 的用户
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<>();
        if (N % 2 == 0) {
            return ans;
        }
        if (N == 1) {
            TreeNode head = new TreeNode(0);
            ans.add(head);
            return ans;
        }
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - 1 - i);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode head = new TreeNode(0);
                    head.left = l;
                    head.right = r;
                    ans.add(head);
                }
            }
        }
        return ans;
    }

    //589
    //执行用时 : 3 ms, 在N-ary Tree Preorder Traversal的Java提交中击败了96.13% 的用户
    //内存消耗 : 59.8 MB, 在N-ary Tree Preorder Traversal的Java提交中击败了14.75% 的用户
    public List<Integer> preorder(Nodes root) {
        List<Integer> res = new LinkedList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(Nodes root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Nodes t : root.children) {
            preorder(t, res);
        }
        return;
    }

    //590
    //执行用时 : 3 ms, 在N-ary Tree Postorder Traversal的Java提交中击败了96.45% 的用户
    //内存消耗 : 56.2 MB, 在N-ary Tree Postorder Traversal的Java提交中击败了45.56% 的用户
    public List<Integer> postorder(Nodes root) {
        List<Integer> res = new LinkedList<>();
        postorder(root, res);
        return res;
    }

    public void postorder(Nodes root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Nodes t : root.children) {
            postorder(t, res);
        }
        res.add(root.val);
        return;
    }

    //700
    //执行用时 : 0 ms, 在Search in a Binary Search Tree的Java提交中击败了100.00% 的用户
    //内存消耗 : 48.3 MB, 在Search in a Binary Search Tree的Java提交中击败了37.67% 的用户
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    //559
    //执行用时 : 3 ms, 在Maximum Depth of N-ary Tree的Java提交中击败了85.59% 的用户
    //内存消耗 : 54.8 MB, 在Maximum Depth of N-ary Tree的Java提交中击败了37.78% 的用户
    public int maxDepth(Nodes root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0, 0);
    }

    private int dfs(Nodes root, int count, int maxDepth) {
        int index = 0;
        count++;
        if (root.children.isEmpty()) {
            return count;
        }
        while (index < root.children.size()) {
            maxDepth = Math.max(maxDepth, dfs(root.children.get(index++), count, maxDepth));
        }
        return maxDepth;
    }

    //701
    //执行用时 : 0 ms, 在Insert into a Binary Search Tree的Java提交中击败了100.00% 的用户
    //内存消耗 : 40.2 MB, 在Insert into a Binary Search Tree的Java提交中击败了90.48% 的用户
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    //979
    //执行用时 : 2 ms, 在Distribute Coins in Binary Tree的Java提交中击败了91.28% 的用户
    //内存消耗 : 37 MB, 在Distribute Coins in Binary Tree的Java提交中击败了72.73% 的用户
    int sum = 0;

    public int distributeCoins(TreeNode root) {
        getSum(root);
        return sum;
    }

    int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //负数表示左节点需要扣除的金币，需要从根节点搬金币下去；正数表示多出的金币，需要将多出的金币搬上去；
        int l = getSum(root.left);
        //负数表示右节点需要扣除的金币，需要从根节点搬金币下去；正数表示多出的金币，需要将多出的金币搬上去；
        int r = getSum(root.right);
        //计算路径（金币数的绝对值就是路径）
        sum += Math.abs(l) + Math.abs(r);
        //返回节点的金币量（已经扣除本身以及左、右子节点需要的）
        return l + r + root.val - 1;
    }

    //230
    //执行用时 : 2 ms, 在Kth Smallest Element in a BST的Java提交中击败了57.19% 的用户
    //内存消耗 : 39.1 MB, 在Kth Smallest Element in a BST的Java提交中击败了65.81% 的用户
    int preVisit = 0;
    boolean bpre = false;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        kthSmallest(root.left, k);
        if (!bpre) {
            preVisit++;
        }
        if (preVisit == k && !bpre) {
            preVisit = root.val;
            bpre = true;
        }
        kthSmallest(root.right, k);
        return preVisit;
    }

    //108
    //执行用时 : 2 ms, 在Convert Sorted Array to Binary Search Tree的Java提交中击败了37.98% 的用户
    //内存消耗 : 37.8 MB, 在Convert Sorted Array to Binary Search Tree的Java提交中击败了65.98% 的用户
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST(int[] nums, int b, int e) {
        if (b > e) {
            return null;
        }
        if (b + 1 == e) {
            return new TreeNode(nums[b]);
        }
        int i = (e + b) / 2;
        TreeNode nt = new TreeNode(nums[i]);
        if (b < i) {
            nt.left = sortedArrayToBST(nums, b, i);
        }
        if (i + 1 < e) {
            nt.right = sortedArrayToBST(nums, i + 1, e);
        }
        return nt;
    }

    //109
    //执行用时 : 7 ms, 在Convert Sorted List to Binary Search Tree的Java提交中击败了6.42% 的用户
    //内存消耗 : 41.3 MB, 在Convert Sorted List to Binary Search Tree的Java提交中击败了39.48% 的用户
    public TreeNode sortedListToBST0(ListNode head) {
        List<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] arr = new int[list.size()];
        int j = 0;
        for (int i : list) {
            arr[j++] = i;
        }
        return sortedArrayToBST(arr);
    }

    //109
    //执行用时 : 3 ms, 在Convert Sorted List to Binary Search Tree的Java提交中击败了54.81% 的用户
    //内存消耗 : 39.4 MB, 在Convert Sorted List to Binary Search Tree的Java提交中击败了96.22% 的用户
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode preNode = preMiddleNode(head);
        ListNode midNode = preNode.next;
        preNode.next = null;
        TreeNode root = new TreeNode(midNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(midNode.next);
        return root;
    }

    public ListNode preMiddleNode(ListNode head) {
        // slow表示中间节点(偶数取后一个), pre表示中间节点的前一个节点
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
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

    /**
     * 剑指 Offer 24. 反转链表
     * 执行用时：1 ms, 在所有 Java 提交中击败了6.37%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param head 初始链表
     * @return 反转链表
     */
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        ListNode newHead = stack.pop();
        ListNode p = newHead;
        while (!stack.isEmpty()) {
            ListNode tmp = stack.pop();
            p.next = tmp;
            p = tmp;

        }
        p.next = null;
        return newHead;
    }

    /**
     * 剑指 Offer 25. 合并两个排序的链表
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.36%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 合并两个排序的链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                p = p.next;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = p.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            p.next = l1;
        } else if (l2 != null) {
            p.next = l2;
        }
        return head.next;
    }

    public static ListNode createListNode(int[] array) {
        ListNode list = new ListNode(array[0]);
        ListNode p = list;
        for (int i = 1; i < array.length; i++) {
            ListNode tmp = new ListNode(array[i]);
            p.next = tmp;
            p = p.next;
        }
        return list;
    }

    /**
     * 96. 不同的二叉搜索树
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了7.69%的用户
     *
     * @param n n个结点
     * @return 几种不同的二叉搜索树
     */
    public int numTrees(int n) {
        if (n == 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    /**
     * 97. 交错字符串
     * 执行用时：5 ms, 在所有 Java 提交中击败了58.20%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了14.29%的用户
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @param s3 字符串3
     * @return 字符串3是否由字符串1和字符串2交错而成
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 1374. 生成每种字符都是奇数个的字符串
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.12%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param n int n
     * @return String
     */
    public String generateTheString(int n) {
        String res = "";
        if (n % 2 == 1) {
            char[] chars = new char[n];
            Arrays.fill(chars, 'a');
            res = new String(chars);
        } else {
            char[] chars = new char[n - 1];
            Arrays.fill(chars, 'a');
            res = new String(chars) + 'b';
        }
        return res;
    }


    /**
     * 327. 区间和的个数
     * <p>
     * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
     * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
     * <p>
     * 执行用时：188 ms, 在所有 Java 提交中击败了15.33% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了97.00% 的用户
     *
     * @param nums  整数数组 nums
     * @param lower 最小边界
     * @param upper 最大边界
     * @return 区间和在 [lower, upper] 之间的个数
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n <= 0) {
            return 0;
        }
        int num = 0;
        long[] pre = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                long tmp = pre[j] - pre[i];
                if (tmp >= lower && tmp <= upper) {
                    num++;
                }
            }
        }
        return num;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[n - 1][1];
    }

    /**
     * 1122. 数组的相对排序
     * 给你两个数组，arr1 和 arr2，
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
     * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了39.49% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了19.69% 的用户
     *
     * @param arr1 数组
     * @param arr2 数组
     * @return 数组的相对排序
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr2) {
            map.put(a, 0);
        }
        PriorityQueue<Integer> list = new PriorityQueue<>();
        for (int a : arr1) {
            if (map.containsKey(a)) {
                map.put(a, map.get(a) + 1);
            } else {
                list.add(a);
            }
        }
        int t = 0;
        for (int i = 0; i < arr2.length; i++) {
            int tmp = map.get(arr2[i]);
            for (int j = 0; j < tmp; j++) {
                arr1[t++] = arr2[i];
            }
        }
        while (!list.isEmpty()) {
            arr1[t++] = list.poll();
        }
        return arr1;
    }


    /**
     * 1402. 做菜顺序
     * <p>
     * 一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
     * 一道菜的 「喜爱时间」系数定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
     * 请你返回做完所有菜 「喜爱时间」总和的最大值为多少。
     * 你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了82.45% 的用户
     *
     * @param satisfaction n 道菜的满意程度 satisfaction
     * @return 返回做完所有菜 「喜爱时间」总和的最大值为多少
     */
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int sum = 0;
        int done = 0;
        int n = satisfaction.length;
        for (int i = n - 1; i >= 0; i--) {
            int tmp = sum + done + satisfaction[i];
            if (tmp >= sum) {
                done += satisfaction[i];
                sum = tmp;
            } else {
                break;
            }
        }
        return sum;
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            int t = map.getOrDefault(a, 0) + 1;
            map.put(a, t);
        }
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if (!set.add(m.getValue())) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        pathSumDFS(root, sum, 0, new ArrayList<>());
        return pathSumResult;
    }

    List<List<Integer>> pathSumResult = new ArrayList<>();

    private void pathSumDFS(TreeNode root, int target, int now, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.val + now == target) {
            if (root.left == null && root.right == null) {
                List<Integer> cop = new ArrayList<>(List.copyOf(list));
                cop.add(root.val);
                pathSumResult.add(cop);
                return;
            }
        }
        now = root.val + now;
        list.add(root.val);
        pathSumDFS(root.left, target, now, list);
        pathSumDFS(root.right, target, now, list);
        list.remove(list.size() - 1);
    }

    public int largestIsland(int[][] grid) {
        int key = 10;
        Map<Integer, Integer> area = new HashMap<>();
        int n = grid.length;
        int m = grid[0].length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int a = largestIslandAres(grid, new int[]{i, j}, key);
                    area.put(key, a);
                    key++;
                    max = Math.max(a, max);
                }
            }
        }
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int tmp = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int[] d : dir) {
                        int nx = d[0] + i;
                        int ny = d[1] + j;
                        if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length) {
                            continue;
                        }
                        if (area.containsKey(grid[nx][ny]) && !set.contains(grid[nx][ny])) {
                            set.add(grid[nx][ny]);
                            tmp += area.get(grid[nx][ny]);
                        }
                    }
                    max = Math.max(max, tmp);
                }
            }
        }
        return max;
    }

    private int largestIslandAres(int[][] grid, int[] begin, int key) {
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Stack<int[]> stack = new Stack<>();
        stack.push(begin);
        int area = 0;
        while (!stack.isEmpty()) {
            int[] tmp = stack.pop();
            int x = tmp[0];
            int y = tmp[1];
            if (grid[x][y] != 1) {
                continue;
            }
            grid[x][y] = key;
            area++;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length) {
                    continue;
                }
                if (grid[nx][ny] == 1) {
                    stack.push(new int[]{nx, ny});
                }
            }
        }
        return area;
    }
}
