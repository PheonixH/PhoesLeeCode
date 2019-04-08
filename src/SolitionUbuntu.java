import datestruct.ListNode;
import datestruct.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.*;
/**
 * @ Author     ：Pheonix
 * @ Date       ：Created in 下午4:31 18年12月12日
 * @ Description：
 */

/**
 * @ Author     ：Pheonix
 * @ Date       ：Created in 上午10:30 18年11月14日
 * @ Description：Leecode 103
 * @ Modified By：
 * @ Version    ：1.0
 */
public class SolitionUbuntu {
    //103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List re = new ArrayList();
        Stack<TreeNode> st1 = new Stack<TreeNode>();
        Stack<TreeNode> st2 = new Stack<TreeNode>();
        if (root != null) {
            if (root.left != null) {
                st1.push(root.left);
            }
            if (root.right != null) {
                st1.push(root.right);
            }
            List rr = new ArrayList();
            rr.add(root.val);
            re.add(rr);
            int useL = 1;
            while (!st1.isEmpty() || !st2.isEmpty()) {
                List rr2 = new ArrayList();
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

    //227
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
            if (sss > 1000000007)
                sss = sss % 1000000007;
        }
        return (int) sss;
    }

    //508
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

    //872
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        boolean re = true;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        if (root1 == null && root2 == null)
            return true;
        else if (root1 == null && root2 != null)
            return false;
        else if (root1 != null && root2 == null)
            return false;
        else {
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

    //709
    public String toLowerCase(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] + 32);
            }
        }
        return ch.toString();
    }

    //461
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

    //771
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

    //237
    public void deleteNode(ListNode node) {
        if (node.next != null) {
            ListNode p = node.next;
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    //338
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

    //476
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

    //728
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

    //463
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) res++;
                    if (i == grid.length - 1 || grid[i + 1][j] == 0) res++;
                    if (j == 0 || grid[i][j - 1] == 0) res++;
                    if (j == grid[i].length - 1 || grid[i][j + 1] == 0) res++;
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

    //810
    public boolean xorGame(int[] nums) {
        int x = 0, n = nums.length;
        for (int num : nums) {
            x ^= num;
        }
        return x == 0 || n % 2 == 0;
    }

    //331
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

    //786:超时
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

    //258
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

    //260
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
        return res[n-1][0];
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        TreeNode r2 = new TreeNode(3);
        TreeNode r3 = new TreeNode(4);
        TreeNode r4 = new TreeNode(5);
//        datestruct.TreeNode r5 = new datestruct.TreeNode(15);
        root.left = r1;
        r1.left = r2;
        r2.left = r3;
        r3.left = r4;
        SolitionUbuntu solution = new SolitionUbuntu();
        String[] strings = {"9", "3", "4", "#", "#", "1", "#", "#,", "#", "6", "#", "#"};
        int[] ints = {1, 7, 23, 29, 47};
        System.out.println(solution.reverseOnlyLetters("7_28]"));
//        int[] arr = solution.findFrequentTreeSum(root);
        //List l = solution.zigzagLevelOrder(root);
//        System.out.print(solution.nthMagicalNumber(1,2,3));
    }
}
