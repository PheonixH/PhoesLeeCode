import java.util.*;

import static java.lang.Math.log10;

/**
 * @ProjectName: LeeCode
 * @Package: PACKAGE_NAME
 * @ClassName: Solution
 * @Description: LeeCode Learning
 * @Author: Pheonix
 * @CreateDate: 2018/11/19 9:03
 * @Version: 1.0
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode p = new ListNode(1);
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(3);
        ListNode p3 = new ListNode(4);
        ListNode p4 = new ListNode(5);
        ListNode p5 = new ListNode(4);
        p.next = p1;
        //p1.next = p2;

//        p3.next = p4;
//        p4.next = p5;
        TreeNode t1 = new TreeNode(1);
        t1.right = null;
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t2.left = t3;
        t2.right = null;
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t3.left = t4;
        t4.left = t5;
//        solution.postorderTraversal(t1);
        String[] strings = {"5", "2", "C", "D", "+"};
        int[] arr = {1, 2, 3, 4};
        int[] brr = {3, 2, 4, 1};
        int[] crr = {-2, 1, -2, -3};
        System.out.print(solution.solveNQueens(4));
    }

    //709
    public String toLowerCase(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] - 'A' + 'a');
            }
        }
        return String.valueOf(ch);
    }

    //72
    public int minDistance(String word1, String word2) {
        // write your code here
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = dp[i - 1][j] < dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                    dp[i][j] = (dp[i - 1][j - 1] < min ? dp[i - 1][j - 1] : min) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    //15:超时
    public List<List<Integer>> threeSum(int[] nums) {
        List sum0 = new ArrayList<>();
//        List<Integer> list = new ArrayList<>();
//        for(int i:nums){
//            if(!list.contains(i)){
//                list.add(i);
//            }
//        }
//        for(int i = 0;i < list.size(); i++){
//            for(int j = i + 1;j < list.size(); j++){
//                for(int k = j + 1;k < list.size(); k++){
//                    if(list.get(i) + list.get(j) + list.get(k) == 0){
//                        List<Integer> ss = new ArrayList<>();
//                        ss.add(list.get(i));
//                        ss.add(list.get(j));
//                        ss.add(list.get(k));
//                        if(!sum0.contains(ss)) {
//                            sum0.add(ss);
//                        }
//                    }
//                }
//            }
//        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> ss = new ArrayList<>();
                        ss.add(nums[i]);
                        ss.add(nums[j]);
                        ss.add(nums[k]);
                        List<Integer> ss1 = new ArrayList<>();
                        ss1.add(nums[i]);
                        ss1.add(nums[k]);
                        ss1.add(nums[j]);
                        List<Integer> ss2 = new ArrayList<>();
                        ss2.add(nums[j]);
                        ss2.add(nums[i]);
                        ss2.add(nums[k]);
                        List<Integer> ss3 = new ArrayList<>();
                        ss3.add(nums[j]);
                        ss3.add(nums[k]);
                        ss3.add(nums[i]);
                        List<Integer> ss4 = new ArrayList<>();
                        ss4.add(nums[k]);
                        ss4.add(nums[i]);
                        ss4.add(nums[j]);
                        List<Integer> ss5 = new ArrayList<>();
                        ss5.add(nums[k]);
                        ss5.add(nums[j]);
                        ss5.add(nums[i]);
                        if (!sum0.contains(ss) && !sum0.contains(ss1) && !sum0.contains(ss2)
                                && !sum0.contains(ss3) && !sum0.contains(ss4) && !sum0.contains(ss5)) {
                            sum0.add(ss);
                        }
                    }
                }
            }
        }
        return sum0;
    }

    //77
    public List<List<Integer>> combine(int n, int k) {
        if (k == 0 || k > n) {
            return null;
        }
        List result = new ArrayList<>();
        if (k == 1) {
            for (int i = 0; i < n; i++) {
                int[] brr = new int[1];
                brr[0] = i + 1;
                result.add(brr);
            }
            return result;
        }

        List<Integer> list = new ArrayList<>();
        int[] brr = new int[k];

        for (int i = 1; i <= k; i++) {
            list.add(i);
            brr[i - 1] = i;
        }
        result.add(brr);
        if (k == n) {
            return result;
        } else {
            int j = 1;
            while (list.get(0) <= n - k) {
                while (list.get(k - 1) < n) {
                    list.set(k - 1, list.get(k - 1) + 1);
                    int[] arr = new int[k];
                    for (int i = 0; i < k; i++) {
                        arr[i] = list.get(i);
                    }
                    result.add(arr);

                }
                if (list.get(k - j) == n - j + 1) {
                    j++;
                }

                int jj = 1;
                while (list.get(k - jj) >= n - jj + 1) {
                    jj++;
                }
                list.set(k - jj, list.get(k - jj) + 1);

                //arr[k - j]++;
                for (int i = 1; i < jj; i++) {
                    list.set(k - jj + i, list.get(k - jj + i - 1) + 1);
                    //arr[k - j + i] = arr[k - j + i - 1] + 1;
                }
                list.set(k - 1, list.get(k - 1) - 1);
            }
            list.set(k - 1, list.get(k - 1) + 1);
            int[] arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = list.get(i);
            }
            result.add(arr);
            return result;
        }
    }

    //621
    public int leastInterval(char[] tasks, int n) {
//        int result = 0;
//        int[] wait = new int [26];
//        for(int i = 0;i < 26; i++){
//            wait[i] = 0;
//        }
//        for(int i = 0;i < tasks.length; i++){
//            int key = (int)(tasks[i] - 'A');
//            result = result + wait[key] + 1;
//            for(int j = 0;j < 26; j++){
//                if(wait[j] != 0) {
//                    wait[j] = wait[j] - 1;
//                }
//            }
//            wait[key] = 100;
//        }
//        return result;
        int result = 0;
        int[] num = new int[26];
        for (int i = 0; i < 26; i++) {
            num[i] = 0;
        }
        int max = 0;
        int all = 0;
        for (int i = 0; i < tasks.length; i++) {
            int key = (int) (tasks[i] - 'A');
            num[key]++;
            if (max < num[key]) {
                all = 1;
                max = num[key];
            } else if (max == num[key]) {
                all++;
            }
        }
        result = (max - 1) * n + max + all - 1;
        return result > tasks.length ? result : tasks.length;
    }

    //83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode p = head;
            ListNode q = head.next;
            List<Integer> list = new ArrayList<>();
            list.add(p.val);
            while (q.next != null) {
                if (list.contains(q.val)) {
                    p.next = q.next;
                } else {
                    list.add(q.val);
                    p = q;
                }
                q = p.next;
            }
            if (!list.contains(p.val)) {
                list.add(p.val);
            }
            if (q != null) {
                if (list.contains(q.val)) {
                    p.next = q.next;
                }
            }
            return head;
        }
    }

    //145
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        } else if (root.left == null && root.right == null) {
            list.add(root.val);
            return list;
        } else {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            List<TreeNode> ll = new ArrayList<>();
            while (!stack.empty()) {
                TreeNode p = stack.pop();
                if (p.left == null && p.right == null) {
                    list.add(p.val);
                    ll.add(p);
                } else {
                    if (p.left != null && ll.contains(p.left)) {
                        list.add(p.val);
                        ll.add(p);
                    } else if (p.right != null && ll.contains(p.right)) {
                        list.add(p.val);
                        ll.add(p);
                    } else {
                        stack.push((p));
                        if (p.right != null) {
                            stack.push(p.right);
                        }
                        if (p.left != null) {
                            stack.push(p.left);
                        }
                    }
                }
            }
            return list;
        }
    }

    //719 超时。。。
    public int smallestDistancePair(int[] nums, int k) {
        if (nums.length == 2) {
            return (nums[1] - nums[0]) > 0 ? nums[1] - nums[0] : nums[0] - nums[1];
        } else {
            int[][] arr = new int[nums.length][nums.length];
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (i == j) {
                        arr[i][j] = -1;
                    } else {
                        arr[i][j] = Math.abs(nums[i] - nums[j]);
                        //if(!list.contains(arr[i][j])){
                        list.add(arr[i][j]);
                        //}
                    }
                }
            }
            Collections.sort(list);
            int result = 0;
            if (k > list.size()) {
                result = list.get(list.size() - 1);
            } else {
                result = list.get(k - 1);
            }
            return result;
        }
    }

    //922
    public int[] sortArrayByParityII(int[] A) {
        int[] arr = new int[A.length];
        int i = 0;
        int j = 1;
        for (int k = 0; k < A.length; k++) {
            if (A[k] % 2 == 0) {
                arr[i] = A[k];
                i = i + 2;
            } else {
                arr[j] = A[k];
                j = j + 2;
            }
        }
        return arr;
    }

    //342
    public boolean isPowerOfFour(int num) {
        //1).
//        while (1 != num&&num > 0){
//            if(0 != num % 4) {
//                break;
//            }
//            else{
//                num = num / 4;
//            }
//        }
//        if(1 == num){
//            return true;
//        }
//        else {
//            return false;
//        }
        //2).
//        Stack<Integer> stack = new Stack<>();
//        stack.push(num);
//        int a = stack.peek();
//        while (!stack.empty()&&0 < a){
//            a = stack.peek();
//            if(1 == a){
//                stack.pop();
//                break;
//            }
//            else if(0 == a % 4){
//                stack.pop();
//                stack.push(a/4);
//            }
//            else{
//                break;
//            }
//        }
//        return stack.empty();
        //3).
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
        //4).
//        return ((num & (num-1)) == 0) && num > 0 && ((num  & 0x55555555) != 0);
    }

    //231
    public boolean isPowerOfTwo(int n) {
        return 0 == (n & (n - 1)) && n > 0;
    }

    //191
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n = (n + (n >>> 8)) & 0x00ff00ff;
        n = n + (n >>> 16) & 0x0000ffff;
        return n;
    }

    //326
    public boolean isPowerOfThree(int n) {
        double tem = log10(n) / log10(3.0);
        if ((int) (tem) - tem == 0) { // 判断一个数是否为整数的做法，值得学习
            return true;
        } else {
            return false;
        }
    }

    //887:Failed
    //定义组合函数 C(m,n)
    public int C(int m, int n) {
        if (n > m / 2) {
            n = m - n;
        }
        int max = 1, k = 0;
        while (n-- > 0) {
            max = max * (m--) / (++k);
        }
        return max;
    }

    public int superEggDrop(int K, int N) {
        // i 记录了层数信息
        // m 第 i 层的最大节点数
        // s 前 i 层最大节点数之和
        int m = 1, i, sum = 0;
        //前 K 层情况
        for (i = 0; i < K; ++i) {
            sum += m;
            if (N <= sum) {
                return ++i;
            }
            m *= 2;
        }
        // temp 是辅助值
        int temp = m;
        // K 层以后的情况
        while (true) {
            m = temp - C(i, i - K);
            sum += m;
            if (N <= sum) {
                break;
            }
            temp += m;
            ++i;
        }
        return ++i;
    }

    //682
    public int calPoints(String[] ops) {
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        //1）.
        while (i < ops.length) {
            if (ops[i].equals("C")) {
                if (!stack.empty()) {
                    sum = sum - stack.pop();
                }
            } else if (ops[i].equals("D")) {
                if (!stack.empty()) {
                    sum = sum + stack.peek() * 2;
                    stack.push(stack.peek() * 2);
                }
            } else if (ops[i].equals("+")) {
                if (!stack.empty()) {
                    int a = stack.pop();
                    sum = sum + stack.peek() + a;
                    int b = a + stack.peek();
                    stack.push(a);
                    stack.push(b);
                }
            } else {
                int a = Integer.parseInt(ops[i]);
                sum = sum + a;
                stack.push(a);
            }
            i++;
        }
        //2).
//        for(String op : ops) {
//            if (op.equals("+")) {
//                int top = stack.pop();
//                int newtop = top + stack.peek();
//                stack.push(top);
//                stack.push(newtop);
//            } else if (op.equals("C")) {
//                stack.pop();
//            } else if (op.equals("D")) {
//                stack.push(2 * stack.peek());
//            } else {
//                stack.push(Integer.valueOf(op));
//            }
//        }
        return sum;
    }

    //122
    public int maxProfit2(int[] prices) {
        if (0 == prices.length || 1 == prices.length) {
            return 0;
        } else {
            int sum = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i] < prices[i + 1]) {
                    sum = sum + prices[i + 1] - prices[i];
                }
            }
            return sum;
        }
    }

    //123
    public int maxProfit3(int[] prices) {
        if (prices == null || 0 == prices.length || 1 == prices.length) {
            return 0;
        } else {
            int res = 0;
            for (int i = 0; i < prices.length; i++) {
                //第一次买卖
                int min1 = prices[0];
                int max1 = prices[0];
                int re1 = 0;
                for (int j = 0; j < i; j++) {
                    int k = 0;
                    if (max1 < prices[j]) {
                        max1 = prices[j];
                    }
                    if (min1 > prices[j]) {
                        max1 = prices[j];
                        min1 = prices[j];
                    }
                    re1 = re1 > (max1 - min1) ? re1 : (max1 - min1);
                }
                //第二次买卖
                int min2 = prices[i];
                int max2 = prices[i];
                int re2 = 0;
                for (int j = i; j < prices.length; j++) {
                    int k = 0;
                    if (max2 < prices[j]) {
                        max2 = prices[j];
                    }
                    if (min2 > prices[j]) {
                        max2 = prices[j];
                        min2 = prices[j];
                    }
                    re2 = re2 > (max2 - min2) ? re2 : (max2 - min2);
                }
                res = res > (re1 + re2) ? res : (re1 + re2);
            }
            return res;
        }
    }

    //188 Failed
    public int maxProfit4(int k, int[] prices) {
        int[] r = new int[2];
        r[0] = 0;
        r[1] = 0;
        maxProfit4help(k, prices, 0, r);
        return r[0];
    }

    public static void maxProfit4help(int k, int[] prices, int b, int[] r) {
        if (1 == k) {
            int t = maxProfit4help(b, prices.length, prices);
            if (t > 0) {
                r[1] = r[1] + t;
            }
            r[0] = r[0] > r[1] ? r[0] : r[1];
        } else {
            for (int i = b; i < prices.length; i++) {
                int t = maxProfit4help(b, i, prices);
                if (t > 0) {
                    r[1] = r[1] + t;
                }
                k = k - 1;
                maxProfit4help(k, prices, i, r);
            }
        }
    }

    public static int maxProfit4help(int b, int e, int[] prices) {
        int min = prices[b];
        int max = prices[b];
        int re = 0;
        for (int j = b; j < e; j++) {
            int k = 0;
            if (max < prices[j]) {
                max = prices[j];
            }
            if (min > prices[j]) {
                max = prices[j];
                min = prices[j];
            }
            re = re > (max - min) ? re : (max - min);
        }
        return re;
    }

    //2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode re = res;
        int b = 0;
        while (l1 != null && l2 != null) {
            ListNode p = new ListNode(l1.val + l2.val + b >= 10 ? (l1.val + l2.val + b - 10) : (l1.val + l2.val + b));
            b = l1.val + l2.val + b >= 10 ? 1 : 0;
            if (res.val == -1) {
                res = p;
                re = res;
            } else {
                res.next = p;
                res = res.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            while (l1 != null) {
                ListNode p = new ListNode(l1.val + b >= 10 ? (l1.val + b - 10) : (l1.val + b));
                b = l1.val + b >= 10 ? 1 : 0;
                res.next = p;
                res = res.next;
                l1 = l1.next;
            }
        } else if (l2 != null) {
            while (l2 != null) {
                ListNode p = new ListNode(l2.val + b >= 10 ? (l2.val + b - 10) : (l2.val + b));
                b = l2.val + b >= 10 ? 1 : 0;
                res.next = p;
                res = res.next;
                l2 = l2.next;
            }
        }
        if (b != 0) {
            ListNode p = new ListNode(1);
            res.next = p;
        }
        return re;
    }

    //67
    public String addBinary(String a, String b) {
        char[] arr = a.toCharArray();
        char[] brr = b.toCharArray();
        int i = a.length() - 1;
        int j = b.length() - 1;
        char[] re = new char[a.length() > b.length() ? a.length() : b.length()];
        int k = re.length - 1;
        int f = 0;
        while (i >= 0 && j >= 0) {
            int t = (arr[i] == '1' ? 1 : 0) + (brr[j] == '1' ? 1 : 0) + f;
            int p = t >= 2 ? t - 2 : t;
            re[k] = p == 0 ? '0' : '1';
            f = t >= 2 ? 1 : 0;
            k--;
            i--;
            j--;
        }
        while (i >= 0) {
            int t = (arr[i] == '1' ? 1 : 0) + f;
            int p = t >= 2 ? t - 2 : t;
            re[k] = p == 0 ? '0' : '1';
            f = t >= 2 ? 1 : 0;
            k--;
            i--;
        }
        while (j >= 0) {
            int t = (brr[j] == '1' ? 1 : 0) + f;
            int p = t >= 2 ? t - 2 : t;
            re[k--] = p == 0 ? '0' : '1';
            f = t >= 2 ? 1 : 0;
            j--;
        }
        String str = String.valueOf(re);
        if (f == 1) {
            str = '1' + str;
        }
        return str;
    }

    //43
    public String multiply(String num1, String num2) {
        char[] arr = num1.toCharArray();
        char[] brr = num2.toCharArray();
        //int[][] res = new int[num1.length()][num2.length()];
        int[] re = new int[num1.length() + num2.length()];
        for (int i : re) {
            i = 0;
        }
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                //res[i][j] = (int)arr[i] * (int)brr[j];
                int c = Integer.valueOf(arr[i] - '0') * Integer.valueOf(brr[j] - '0');
                re[i + j + 1] = c + re[i + j + 1];
            }
        }
        int i = re.length - 1;
        int b = re[i] / 10;
        re[i] = re[i] % 10;
        while (i > 0) {
            i--;
            int c = (re[i] + b) / 10;
            re[i] = (re[i] + b) % 10;
            b = c;
        }
        //re[0] = re[i] + b;
        String str = "";
        int ii = 0;
        while (ii < re.length - 1 && 0 == re[ii]) {
            ii++;
        }
        for (; ii < re.length; ii++) {
            str = str + String.valueOf(re[ii]);
        }
        return str;
    }

    //735
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        //正向
        boolean b = true;
        for (int i : asteroids) {
            if (stack.empty()) {
                stack.add(i);
                b = i > 0 ? true : false;
            } else if (i > 0 && b) {
                stack.add(i);
                b = true;
            } else if (i > 0 && !b) {
//                int c = stack.pop();
//                while (!stack.empty()&&0 - c <= i){
//                    c = stack.pop();
//                }
//                if(stack.empty()&&0 - c <= i){
//                    stack.push(i);
//                    b = true;
//                }
//                else if(0 - c > i){
//                    stack.push(c);
//                }
                stack.add(i);
                b = true;
            } else if (i < 0 && !b) {
                stack.push(i);
                b = false;
            } else if (i < 0 && b) {
                int c = stack.pop();
                while (!stack.empty() && c < 0 - i && c >= 0) {
                    c = stack.pop();
                }
                if (stack.empty() && c < 0 - i && c > 0) {
                    stack.push(i);
                    b = false;
                } else if (stack.empty() && c == 0 - i) {

                } else if (c < 0) {
                    stack.push(c);
                    stack.push(i);
                    b = false;
                } else if (c == 0 - i) {
                    b = stack.peek() > 0 ? true : false;
                } else if (c > 0 - i) {
                    stack.push(c);
                    b = true;
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    //106
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            TreeNode head;
            return null;
        } else {
            int inob = 0;
            int inoe = inorder.length - 1;
            int posb = 0;
            int pose = postorder.length - 1;
            TreeNode head = new TreeNode(postorder[pose]);
            int i = inob;
            for (; i < inoe; i++) {
                if (inorder[i] == postorder[pose]) {
                    break;
                }
            }
            buildTreeHelp(inorder, postorder, head, inob, i - 1, posb, i - 1, true);
            buildTreeHelp(inorder, postorder, head, i + 1, inoe, i, pose - 1, false);
            return head;
        }
    }

    public static void buildTreeHelp(int[] inorder, int[] postorder, TreeNode root, int inob, int inoe, int posb, int pose, boolean b) {
        if (inob > inoe) {

        } else if (inob == inoe) {
            TreeNode t = new TreeNode(inorder[inob]);
            if (b) {
                root.left = t;
            } else {
                root.right = t;
            }
        } else {
            TreeNode t = new TreeNode(postorder[pose]);
            if (b) {
                root.left = t;
            } else {
                root.right = t;
            }
            int i = inob;
            for (; i < inoe; i++) {
                if (inorder[i] == postorder[pose]) {
                    break;
                }
            }
            buildTreeHelp(inorder, postorder, t, inob, i - 1, posb, i - 1 - inob + posb, true);
            buildTreeHelp(inorder, postorder, t, i + 1, inoe, i + pose - inoe, pose - 1, false);
        }
    }

    //220:超时
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                long l = Math.abs((long) nums[j] - (long) nums[i]);
                if (t >= l && l >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    //51
    public List<List<String>> solveNQueens(int n) {
        List list = new LinkedList();
        char[][] cheers = new char[n][n];
//        for (char[] i : cheers) {
//            for (char j : i) {
//                j = '.';
//            }
//        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cheers[i][j] = '.';
            }
        }
        putQueen(list, cheers, 0);
        return list;
    }
    public static void putQueen(List list, char[][] cheers, int i) {
        if (i == cheers[0].length) {
            //if (isQueenSafety(cheers, i-1)) {
            String[] strings = new String[cheers.length];
            for (int x = 0; x < cheers.length; x++) {
                StringBuffer stringBuffer = new StringBuffer();
                for(int y = 0;y < cheers.length;y++){
                    stringBuffer.append(cheers[x][y]);
                }
                strings[x] = stringBuffer.toString();
            }
            list.add(strings);
            return;
            //}
        } else {
            for (int j = 0; j < cheers[0].length; j++) {
                for (int k = 0; k < cheers[0].length; k++) {
                    cheers[i][k] = '.';
                }
                cheers[i][j] = 'Q';
                if (isQueenSafety(cheers, i)) {
                    putQueen(list, cheers, i + 1);
                }
            }
        }

    }
    public static boolean isQueenSafety(char[][] cheers, int i) {
        if (0 == i) {
            return true;
        }
        int j = 0;
        for (; j < cheers[0].length; j++) {
            if (cheers[i][j] == 'Q') {
                break;
            }
        }
        for (int x = 1; x <= i; x++) {
            if (0 <= j - x && 'Q' == cheers[i - x][j - x]) {
                return false;
            }
            if (cheers[0].length > j + x && 'Q' == cheers[i - x][j + x]) {
                return false;
            }
            if ('Q' == cheers[i - x][j]) {
                return false;
            }
        }
        return true;
    }

    //52
    public int totalNQueens(int n) {
        List list = new LinkedList();
        char[][] cheers = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cheers[i][j] = '.';
            }
        }
        putQueen(list, cheers, 0);
        return list.size();
    }
}

