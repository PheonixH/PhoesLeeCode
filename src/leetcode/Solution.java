package leetcode;

import Template.Trie;
import leetcode.datestruct.Worker;

import java.util.*;
import java.util.stream.Collectors;

import leetcode.datestruct.*;

import static java.lang.Math.log10;
import static java.lang.Math.min;

/**
 * @ProjectName: LeeCode
 * @Package: PACKAGE_NAME
 * @ClassName: LeetCode.Solution
 * @Description: LeeCode Learning
 * @Author: Pheonix
 * @CreateDate: 2018/11/19 9:03
 * @Version: 1.0
 */
public class Solution {
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
    public List<List<Integer>> threeSum0(int[] nums) {
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

    //15: --- 11145ms --- 0.99%
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> positive = new LinkedList<>();
        List<Integer> negative = new LinkedList<>();
        int zero = 0;
        for (int i : nums) {
            if (i > 0) {
                positive.add(i);
            } else if (i < 0) {
                negative.add(i);
            } else {
                zero++;
            }
        }
        List<List<Integer>> res = new ArrayList();
        if (zero > 2) {
            List<Integer> re = new ArrayList<>();
            re.add(0);
            re.add(0);
            re.add(0);
            res.add(re);
        }
        if (positive.size() == 0 || negative.size() == 0) {

            return res;
        }
        Collections.sort(positive, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Collections.sort(negative, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        Set<String> set = new HashSet<>();
        int i = 0;
        int j = 0;
        if (zero != 0) {
            while (i < positive.size() && j < negative.size()) {
                int p = positive.get(i);
                int q = negative.get(j);
                int m = 0 - p - q;
                if (m > 0) {
                    i++;
                } else if (m < 0) {
                    j++;
                } else {
                    i++;
                    j++;
                    if (zero != 0) {
                        StringBuffer str = new StringBuffer();
                        str.append(p).append(0).append(q);
                        if (!set.contains(str.toString())) {
                            set.add(str.toString());
                            List<Integer> re = new ArrayList<>();
                            re.add(0);
                            re.add(p);
                            re.add(q);
                            res.add(re);
                        }
                    }
                }
            }
        }
        i = 0;
        while (i < positive.size()) {
            int p = positive.get(i);
            for (int k = 0; k < negative.size(); k++) {
                int q = negative.get(k);
                int m = 0 - p - q;
                if (m >= 0) {
                    break;
                }
                if (negative.contains(m)) {
                    if (m < q) {
                        continue;
                    }
                    if (m == q) {
                        int q1 = k - 1 >= 0 ? negative.get(k - 1) : 1;
                        int q2 = k + 1 < negative.size() ? negative.get(k + 1) : 1;
                        if (q1 == 1 && q2 == 1) {
                            continue;
                        }
                        if (q1 != 1 && q1 != q && q2 != 1 && q2 != p) {
                            continue;
                        }
                        if (q1 != 1 && q1 != q && q2 == 1) {
                            continue;
                        }
                        if (q1 == 1 && q2 != 1 && q2 != q) {
                            continue;
                        }
                    }
                    StringBuffer str = new StringBuffer();
                    str.append(p).append(m).append(q);
                    if (!set.contains(str.toString())) {
                        set.add(str.toString());
                        List<Integer> re = new ArrayList<>();
                        re.add(p);
                        re.add(q);
                        re.add(m);
                        res.add(re);
                    }
                }
            }
            i++;
        }
        j = 0;
        while (j < negative.size()) {
            int q = negative.get(j);
            for (int k = 0; k < positive.size(); k++) {
                int p = positive.get(k);
                int m = 0 - p - q;
                if (m <= 0) {
                    break;
                }
                if (positive.contains(m)) {
                    if (m > p) {
                        continue;
                    }
                    if (m == p) {
                        int p1 = k - 1 >= 0 ? positive.get(k - 1) : -1;
                        int p2 = k + 1 < positive.size() ? positive.get(k + 1) : -1;
                        if (p1 == -1 && p2 == -1) {
                            continue;
                        }
                        if (p1 != -1 && p1 != p && p2 != -1 && p2 != p) {
                            continue;
                        }
                        if (p1 != -1 && p1 != p && p2 == -1) {
                            continue;
                        }
                        if (p1 == -1 && p2 != -1 && p2 != p) {
                            continue;
                        }
                    }
                    StringBuffer str = new StringBuffer();
                    str.append(p).append(m).append(q);
                    if (!set.contains(str.toString())) {
                        set.add(str.toString());
                        List<Integer> re = new ArrayList<>();
                        re.add(p);
                        re.add(q);
                        re.add(m);
                        res.add(re);
                    }
                }
            }
            j++;
        }
        return res;
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
    @SuppressWarnings("AlibabaUndefineMagicConstant")
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
    @SuppressWarnings("AlibabaUndefineMagicConstant")
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
    @SuppressWarnings("AlibabaUndefineMagicConstant")
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
            if ("C".equals(ops[i])) {
                if (!stack.empty()) {
                    sum = sum - stack.pop();
                }
            } else if ("D".equals(ops[i])) {
                if (!stack.empty()) {
                    sum = sum + stack.peek() * 2;
                    stack.push(stack.peek() * 2);
                }
            } else if ("+".equals(ops[i])) {
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
    public String addBinary0(String a, String b) {
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

    //220 -- 624ms -- 4.31%
    public boolean containsNearbyAlmostDuplicate0(int[] nums, int k, int t) {
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

    //220 -- 55ms -- 29.08%
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0 || k < 1) {
            return false;
        }
        SortedSet<Long> binSet = new TreeSet<Long>();
        int start = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            SortedSet<Long> sub = binSet.subSet((long) nums[i] - t, (long) nums[i] + t + 1);
            if (!sub.isEmpty()) {
                return true;
            }
            if (i - start >= k) {
                binSet.remove((long) nums[start++]);
            }
            binSet.add((long) nums[i]);
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
                for (int y = 0; y < cheers.length; y++) {
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

    //328
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode p = head;
        if (p.next == null) {
            return head;
        }
        ListNode q = head.next;
        ListNode resq = head.next;
        if (q.next == null) {
            return head;
        }
        ListNode t = p;
        while ((p != null && p.next != null) || (q != null && q.next != null)) {
            if (p.next != null) {
                p.next = p.next.next;
                t = p;
                p = p.next;
            }
            if (q.next != null) {
                q.next = q.next.next;
                q = q.next;
            }
        }
        if (p != null) {
            p.next = resq;
        } else {
            t.next = resq;
        }
        return head;
    }

    //725
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] listNodes = new ListNode[k];
        ListNode p = root;
        int i = 0;
        while (p != null) {
            i++;
            p = p.next;
        }
        int a = i % k;
        int b = i / k;
        ListNode q = root;
        for (int j = 0; j < k; j++) {
            if (q != null) {
                ListNode t = q;
                ListNode tt = t;
                int c = a > 0 ? b + 1 : b;
                while (c > 1) {
                    tt = tt.next;
                    q = q.next;
                    c--;
                }
                q = q.next;
                tt.next = null;
                listNodes[j] = t;
                a--;
            } else {
                listNodes[j] = q;
            }
        }
        return listNodes;
    }

    //61
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int n = 1;
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
            n++;
        }
        k = k % n;
        k = (n - k) % n;
        if (0 == k) {
            return head;
        }
        ListNode q = head;
        while (k > 1) {
            q = q.next;
            k--;
        }
        p.next = head;
        head = q.next;
        q.next = null;
        return head;
    }

    //189
    public void rotate(int[] nums, int k) {
        if (0 == nums.length) {
            return;
        }
        k = k % nums.length;
        if (0 == k) {
            return;
        }
        rotateAss(nums, 0, nums.length - k - 1);
        rotateAss(nums, nums.length - k, nums.length - 1);
        rotateAss(nums, 0, nums.length - 1);
        return;
    }

    public static void rotateAss(int[] nums, int b, int e) {
        while (b < e) {
            int t = nums[b];
            nums[b] = nums[e];
            nums[e] = t;
            b++;
            e--;
        }
    }

    //414
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int thirdMax(int[] nums) {
        if (0 == nums.length) {
            return 0;
        }
        if (1 == nums.length) {
            return nums[0];
        }
        if (2 == nums.length) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        int x = nums[0];
        int y = 0, z = 0;
        boolean by = false;
        boolean bz = false;
        int k = 1;
        while (k < nums.length) {
            if (nums[k] != x) {
                int t = nums[k];
                if (x < nums[k]) {
                    t = x;
                    x = nums[k];
                }
                if (!by) {
                    y = t;
                    by = true;
                } else if (!bz && t != y) {
                    if (y < t) {
                        int tt = t;
                        t = y;
                        y = tt;
                    }
                    z = t;
                    bz = true;
                } else if (bz && t != y) {
                    int tt = t;
                    if (y < t) {
                        tt = y;
                        y = t;
                    }
                    z = tt > z ? tt : z;
                }
            }
            k++;
        }
        if (!bz) {
            return x;
        } else {
            return z;
        }
    }

    //513
    public int findBottomLeftValue(TreeNode root) {
        List<TreeNode> list1 = new LinkedList<>();
        List<TreeNode> list2 = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        list1.add(root);
        int res = root.val;
        while (!list1.isEmpty() || !list2.isEmpty()) {
            if (list2.isEmpty()) {
                TreeNode t = list1.get(0);
                res = t.val;
                while (!list1.isEmpty()) {
                    t = list1.get(0);
                    list1.remove(0);
                    if (t.left != null) {
                        list2.add(t.left);
                    }
                    if (t.right != null) {
                        list2.add(t.right);
                    }
                }
            } else {
                TreeNode t = list2.get(0);
                res = t.val;
                while (!list2.isEmpty()) {
                    t = list2.get(0);
                    list2.remove(0);
                    if (t.left != null) {
                        list1.add(t.left);
                    }
                    if (t.right != null) {
                        list1.add(t.right);
                    }
                }
            }
        }
        return res;
    }

    //168
    public String convertToTitle(int n) {
        StringBuffer res = new StringBuffer();
        if (1 > n) {
            return null;
        }
        int x = n % 26;
        n = n / 26;
        Stack<Character> stack = new Stack<>();
        while (x != 0 || n != 0) {
            if (x == 0) {
                x = 26;
                n = n - 1;
            }
            stack.push((char) ('A' + x - 1));
            x = n % 26;
            n = n / 26;
        }
        while (!stack.empty()) {
            res.append(stack.pop());
        }
        return res.toString();
    }

    //169
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        Iterator iterator = map.entrySet().iterator();
        int key = 0;
        int value = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry) iterator.next();
            if (entry.getValue() > value) {
                key = entry.getKey();
                value = entry.getValue();
            }
        }
        return key;
    }

    //171
    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int res = 0;
        int i = 1;
        for (int j = chars.length - 1; j >= 0; j--) {
            res = res + i * (int) (chars[j] - 'A' + 1);
            i = i * 26;
        }
        return res;
    }

    //558
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf && quadTree1.val) {
            return quadTree1;
        } else if (quadTree2.isLeaf && quadTree2.val) {
            return quadTree2;
        } else if (quadTree1.isLeaf && !quadTree1.val) {
            return quadTree2;
        } else if (quadTree2.isLeaf && !quadTree2.val) {
            return quadTree1;
        } else {
            quadTree1.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
            quadTree1.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
            quadTree1.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
            quadTree1.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
            if (quadTree1.topLeft.isLeaf && quadTree1.bottomRight.isLeaf
                    && quadTree1.topRight.isLeaf && quadTree1.bottomRight.isLeaf
                    && quadTree1.bottomRight.val == quadTree1.bottomLeft.val
                    && quadTree1.topLeft.val == quadTree1.bottomLeft.val
                    && quadTree1.topRight.val == quadTree1.bottomLeft.val) {
                quadTree1.val = quadTree1.topLeft.val;
                quadTree1.isLeaf = true;
            }
            return quadTree1;
        }
    }

    //695
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 1) {
            if (grid[0].length == 1) {
                return grid[0][0] == 1 ? 1 : 0;
            } else {
                int max = 0;
                for (int j = 0; j < grid[0].length; j++) {
                    int m = 0;
                    while (j < grid[0].length && grid[0][j] == 1) {
                        m++;
                        j++;
                    }
                    max = max > m ? max : m;
                }
                return max;
            }
        }
        if (grid[0].length == 1) {
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                int m = 0;
                while (i < grid.length && grid[i][0] == 1) {
                    m++;
                    i++;
                }
                max = max > m ? max : m;
            }
            return max;
        }
        boolean[][] booleans = new boolean[grid.length][grid[0].length];
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!booleans[i][j] && 1 == grid[i][j]) {
                    booleans[i][j] = true;
                    int m = maxAreaOfIslandAss(grid, booleans, i, j, 1);
                    max = max > m ? max : m;
                }
            }
        }
        return max;
    }

    public static int maxAreaOfIslandAss(int[][] grid, boolean[][] booleans, int i, int j, int re) {
        boolean visit = false;
        if (i == 0) {
            if (!booleans[i + 1][j] && 1 == grid[i + 1][j]) {
                booleans[i + 1][j] = true;
                re = maxAreaOfIslandAss(grid, booleans, i + 1, j, re + 1);
            }
        } else if (i == grid.length - 1) {
            if (!booleans[i - 1][j] && 1 == grid[i - 1][j]) {
                booleans[i - 1][j] = true;
                re = maxAreaOfIslandAss(grid, booleans, i - 1, j, re + 1);
            }
        } else {
            if (!booleans[i - 1][j] && 1 == grid[i - 1][j]) {
                booleans[i - 1][j] = true;
                re = maxAreaOfIslandAss(grid, booleans, i - 1, j, re + 1);
            }
            if (!booleans[i + 1][j] && 1 == grid[i + 1][j]) {
                visit = true;
                booleans[i + 1][j] = true;
                re = maxAreaOfIslandAss(grid, booleans, i + 1, j, re + 1);
            }
        }
        if (j == 0) {
            if (!booleans[i][j + 1] && 1 == grid[i][j + 1]) {
                booleans[i][j + 1] = true;
                re = maxAreaOfIslandAss(grid, booleans, i, j + 1, re + 1);
            }
        } else if (j == grid[0].length - 1) {
            if (!booleans[i][j - 1] && 1 == grid[i][j - 1]) {
                booleans[i][j - 1] = true;
                re = maxAreaOfIslandAss(grid, booleans, i, j - 1, re + 1);
            }
        } else {
            if (!booleans[i][j - 1] && 1 == grid[i][j - 1]) {
                booleans[i][j - 1] = true;
                re = maxAreaOfIslandAss(grid, booleans, i, j - 1, re + 1);
            }
            if (!booleans[i][j + 1] && 1 == grid[i][j + 1]) {
                booleans[i][j + 1] = true;
                re = maxAreaOfIslandAss(grid, booleans, i, j + 1, re + 1);
            }
        }
        return re;
    }

    //79
    public boolean exist(char[][] board, String word) {
        if ("".equals(word)) {
            return true;
        }
        if (board == null) {
            return false;
        }
        boolean[][] booleans = new boolean[board.length][board[0].length];
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chars[0]) {
                    booleans[i][j] = true;
                    boolean b = existAss(board, chars, booleans, 1, i, j, false);
                    if (b) {
                        return b;
                    } else {
                        booleans[i][j] = false;
                    }
                }
            }
        }
        return false;
    }

    public boolean existAss(char[][] board, char[] chars, boolean[][] booleans, int k, int x, int y, boolean e) {
        if (k >= chars.length) {
            return true;
        }
        if (!e && x < board.length - 1 && !booleans[x + 1][y]) {
            if (board[x + 1][y] == chars[k]) {
                booleans[x + 1][y] = true;
                e = existAss(board, chars, booleans, k + 1, x + 1, y, e);
                if (!e) {
                    booleans[x + 1][y] = false;
                }
            }

        }
        if (!e && x > 0 && !booleans[x - 1][y]) {
            if (board[x - 1][y] == chars[k]) {
                booleans[x - 1][y] = true;
                e = existAss(board, chars, booleans, k + 1, x - 1, y, e);
                if (!e) {
                    booleans[x - 1][y] = false;
                }
            }
        }
        if (!e && y < board[0].length - 1 && !booleans[x][y + 1]) {
            if (board[x][y + 1] == chars[k]) {
                booleans[x][y + 1] = true;
                e = existAss(board, chars, booleans, k + 1, x, y + 1, e);
                if (!e) {
                    booleans[x][y + 1] = false;
                }
            }
        }
        if (!e && y > 0 && !booleans[x][y - 1]) {
            if (board[x][y - 1] == chars[k]) {
                booleans[x][y - 1] = true;
                e = existAss(board, chars, booleans, k + 1, x, y - 1, e);
                if (!e) {
                    booleans[x][y - 1] = false;
                }
            }
        }
        return e;
    }

    //404
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        Stack<TreeNode> stackl = new Stack<>();
        Stack<TreeNode> stackr = new Stack<>();
        stackr.add(root);
        int res = 0;
        while (!stackr.empty() || !stackl.empty()) {
            if (!stackr.empty()) {
                TreeNode tr = stackr.pop();
                if (tr.left != null) {
                    stackl.add(tr.left);
                }
                if (tr.right != null) {
                    stackr.add(tr.right);
                }
            }
            if (!stackl.empty()) {
                TreeNode tl = stackl.pop();
                if (tl.left != null) {
                    stackl.add(tl.left);
                }
                if (tl.right != null) {
                    stackr.add(tl.right);
                }
                if (tl.left == null && tl.right == null) {
                    res = res + tl.val;
                }
            }
        }
        return res;
    }

    //455
    public int findContentChildren(int[] g, int[] s) {
        if (0 == s.length || 0 == g.length) {
            return 0;
        }
        quickSort(g, 0, g.length - 1);
        quickSort(s, 0, s.length - 1);
        int res = 0;
        int x = 0, y = 0;
        while (x < g.length && y < s.length) {
            if (g[x] > s[y]) {
                x++;
            } else if (g[x] <= s[y]) {
                x++;
                y++;
                res++;
            }
        }
        return res;
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

    //876
    public ListNode middleNode(ListNode head) {
        if (null == head) {
            return null;
        }
//        if(null == head.next||null == head.next.next){
//            return null;
//        }
        ListNode p = head, q = head;
        while (q.next != null) {
            if (q.next != null) {
                q = q.next;
            }
            p = p.next;
            if (q.next != null) {
                q = q.next;
            }
        }
        return p;
    }

    //859
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public boolean buddyStrings(String A, String B) {
        if (A == null && B != null) {
            return false;
        }
        if (B == null && A != null) {
            return false;
        }
        char[] charAs = A.toCharArray();
        char[] charBs = B.toCharArray();
        if (charAs.length != charBs.length) {
            return false;
        }
        char[] charCs = new char[4];
        int k = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < charAs.length; i++) {
            set.add(charAs[i]);
            if (charAs[i] != charBs[i]) {
                k++;
                if (k > 2) {
                    return false;
                }
                charCs[k * 2 - 2] = charAs[i];
                charCs[k * 2 - 1] = charBs[i];
            }
        }
        if (k == 2 && charCs[0] == charCs[3] && charCs[1] == charCs[2]) {
            return true;
        }
        if (k == 0 && set.size() < charAs.length) {
            return true;
        }
        return false;
    }

    //142
    public ListNode detectCycle(ListNode head) {
//        //解法一
//        if(head == null||head.next == null){
//            return null;
//        }
//        Set<LeetCode.datestruct.ListNode> set = new HashSet<>();
//        Set<LeetCode.data.ListNode> set = new HashSet<>();
//        set.add(head);
//        LeetCode.datestruct.ListNode p = head;
//        LeetCode.data.ListNode p = head;
//        int i = 1;
//        while (p.next != null&&set.size() == i){
//            p = p.next;
//            set.add(p);
//            i++;
//        }
//        if(p.next == null){
//            return null;
//        }
//        return p;

        //解法二,不占空间
        if (head == null || head.next == null) {
            return null;
        }
        ListNode p1 = head, p2 = head;
        boolean hasCycle = false;
        while (!hasCycle && p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                hasCycle = true;
            }
        }
        if (!hasCycle) {
            return null;
        }
        ListNode q = head;
        while (p1 != q) {
            p1 = p1.next;
            q = q.next;
        }
        return q;
    }

    //657
    public boolean judgeCircle(String moves) {
        if (moves == "") {
            return false;
        }
        char[] chars = moves.toCharArray();
        int x = 0;
        int y = 0;
        for (char c : chars) {
            switch (c) {
                case 'R':
                    x++;
                    break;
                case 'L':
                    x--;
                    break;
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
            }
        }
        if (x == 0 && y == 0) {
            return true;
        }
        return false;
    }

    //337
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return robAss(root, false, 0);
    }

    //失败
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int rob1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> s1 = new Stack<>();
        s1.add(root);
        Stack<TreeNode> s2 = new Stack<>();
        List<Integer> list = new ArrayList<>();
        int s = 0;
        while (!s1.empty() || !s2.empty()) {
            s = 0;
            while (!s1.empty()) {
                TreeNode t = s1.pop();
                if (t.left != null) {
                    s2.push(t.left);
                }
                if (t.right != null) {
                    s2.push(t.right);
                }
                s = s + t.val;
            }
            list.add(s);
            s = 0;
            while (!s2.empty()) {
                TreeNode t = s2.pop();
                if (t.left != null) {
                    s1.push(t.left);
                }
                if (t.right != null) {
                    s1.push(t.right);
                }
                s = s + t.val;
            }
            list.add(s);
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        boolean[] booleans = new boolean[list.size()];
        for (int i = 1; i < list.size() - 2; i++) {
            if (list.get(i) >= list.get(i - 1) + list.get(i + 1)) {
                booleans[i] = true;
            }
        }
        if (list.size() > 1 && list.get(0) > list.get(1)) {
            booleans[0] = true;
        }
        if (list.size() > 1 && list.get(list.size() - 1) > list.get(list.size() - 2)) {
            booleans[list.size() - 1] = true;
        }
        int r1 = 0, r2 = 0, res = 0;
        for (int i = 0; i < list.size(); i++) {
            r1 = 0;
            r2 = 0;
            while (i + 1 < list.size() && booleans[i + 1] != true) {
                if (i % 2 == 0) {
                    r1 = r1 + list.get(i);
                } else {
                    r2 = r2 + list.get(i);
                }
                i++;
            }
            if (i + 1 == list.size()) {
                if (i % 2 == 0) {
                    r1 = r1 + list.get(i);
                } else {
                    r2 = r2 + list.get(i);
                }
            }
            res = r1 > r2 ? res + r1 : res + r2;
            i++;
            if (i < list.size() && booleans[i] == true) {
                res = res + list.get(i);
            }
            i++;
        }
        return res;
    }

    //超时
    public int robAss(TreeNode root, boolean b, int s) {
        int sl = 0;
        int sr = 0;
        if (!b) {
            sl = sl + root.val;
        }
        if (root.left != null) {
            sl = sl + robAss(root.left, true, sl);
        }
        if (root.right != null) {
            sl = sl + robAss(root.right, true, sl);
        }
        if (root.left != null) {
            sr = sr + robAss(root.left, false, sr);
        }
        if (root.right != null) {
            sr = sr + robAss(root.right, false, sr);
        }
        return sl > sr ? sl : sr;
    }

    //成功
    public int robAss2(TreeNode root) {
        int a = 0;
        int b = root.val;
        if (root.left == null && root.right == null) {
            return root.val;
        }
        if (root.left != null) {
            a = a + robAss2(root.left);
            if (root.left.left != null) {
                b = b + robAss2(root.left.left);
            }
            if (root.left.right != null) {
                b = b + robAss2(root.left.right);
            }
        }
        if (root.right != null) {
            a = a + robAss2(root.right);
            if (root.right.left != null) {
                b = b + robAss2(root.right.left);
            }
            if (root.right.right != null) {
                b = b + robAss2(root.right.right);
            }
        }
        return a > b ? a : b;
//        if(root == null) {
//            return 0;
//        }
//        int s0 = 0;
//        int s1 = root.val;
//
//        s0 = robAss2(root.left) + robAss2(root.right);
//
//        if(root.left != null) {
//            s1 += robAss2(root.left.left) + robAss2(root.left.right);
//        }
//        if(root.right != null) {
//            s1 += robAss2(root.right.left) + robAss2(root.right.right);
//        }
//        return Math.max(s0,s1);
    }

    //679
    public boolean judgePoint24(int[] nums) {
        List<Float> list = new LinkedList<>();
        list.add((float) nums[0]);
        list.add((float) nums[1]);
        list.add((float) nums[2]);
        list.add((float) nums[3]);
        boolean res = judgePoint24Ass(list);
        return res;
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public boolean judgePoint24Ass(List<Float> list) {
        if (list.size() == 2) {
            for (int i = 0; i < 6; i++) {
                if (Math.abs(24.0 - judgePoint24Ass(list.get(0), list.get(1), i)) < 0.1) {
                    return true;
                }
            }
            return false;
        }
        boolean b = false;
        for (int i = 0; i < list.size() && !b; i++) {
            for (int j = i + 1; j < list.size() && !b; j++) {
                for (int s = 0; s < 6 && !b; s++) {
                    List<Float> list1 = new ArrayList<>();
                    list1.add(judgePoint24Ass(list.get(i), list.get(j), s));
                    int k = 0;
                    while (k < list.size()) {
                        if (k != i && k != j) {
                            list1.add(list.get(k));
                        }
                        k++;
                    }
                    b = judgePoint24Ass(list1);
                }
            }
        }
        return b;
    }

    public float judgePoint24Ass(float x, float y, int which) {
        switch (which) {
            case 0 -> {
                return x + y;
            }
            case 1 -> {
                return x - y;
            }
            case 2 -> {
                return x * y;
            }
            case 3 -> {
                return x / y;
            }
            case 4 -> {
                return y / x;
            }
            case 5 -> {
                return y - x;
            }
            default -> {
                return 0;
            }
        }
    }

    //464
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        //如果目标数<=选择数,一次就赢,返回true
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        //获得总数之和,选择数总数比目标数小,结果不可达,返回false
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }
        Boolean[] dp = new Boolean[1 << maxChoosableInteger];
        return doResolve(0, maxChoosableInteger, desiredTotal, dp);
    }

    private boolean doResolve(int flag, int chooseArea, int total, Boolean[] dp) {
        if (total <= 0) {
            return false;
        }
        //dp代表已经使用了哪个数字情况下是否能赢
        if (dp[flag] == null) {
            dp[flag] = false;
            int comp = 1;
            for (int i = 1; i <= chooseArea; i++) {
                int used = flag | comp;
                //如果,我能用这个数,且用了这个数对方赢不了
                if (used != flag && !doResolve(used, chooseArea, total - i, dp)) {
                    dp[flag] = true;
                    break;
                }
                comp <<= 1;
            }
        }
        return dp[flag];
    }

    //955
    public int minDeletionSize(String[] A) {
        List<List<Character>> list = new ArrayList<>();
        for (String str : A) {
            List<Character> list1 = new LinkedList<>();
            char[] chars = str.toCharArray();
            for (char c : chars) {
                list1.add(c);
            }
            list.add(list1);
        }
        int res = 0;
        return minDeletionSizeAss(res, list);
    }

    public int minDeletionSizeAss(int res, List<List<Character>> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int k = isRight(list.get(i), list.get(i + 1));
            if (k != -1) {
                for (int j = 0; j < list.size(); j++) {
                    list.get(j).remove(k);
                }
                res = minDeletionSizeAss(res + 1, list);
                break;
            }
        }
        return res;
    }

    public int isRight(List<Character> list1, List<Character> list2) {
        int alen = list1.size();
//        int blen = b.length;
        int i = 0;
        while (i < alen) {
            if (list1.get(i) < list2.get(i)) {
                return -1;
            } else if (list1.get(i) > list2.get(i)) {
                return i;
            }
            i++;
        }
        return -1;
//        if(i >= alen){
//            return -1;
//        }
//        return i;
    }

    //956 - 暴力
    public int tallestBillboard(int[] rods) {
        int total = 0;
        for (int i : rods) {
            total += i;
        }
        return tallestBillboardAss(rods, 0, 0, 0, total, 0);
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int tallestBillboardAss(int[] rods, int left, int right, int i, int total, int re) {
        if (i == rods.length && left == right) {
            return left;
        } else if (i == rods.length && left != right) {
            return 0;
        } else if (Math.abs(left - right) > total) {
            return 0;
        } else if (re > (left + right + total) / 2) {
            return 0;
        }
        int b = tallestBillboardAss(rods, left + rods[i], right, i + 1, total - rods[i], re);
        re = b > re ? b : re;
        int c = tallestBillboardAss(rods, left, right + rods[i], i + 1, total - rods[i], re);
        re = re > c ? re : c;
        int a = tallestBillboardAss(rods, left, right, i + 1, total - rods[i], re);
        return Math.max(re, a);
    }

    //956 - 动态规划
    public int tallestBillboard2(int[] rods) {
        int len = rods.length;
        int[][] dp = new int[len + 1][5001];
        for (int i = 0; i < len + 1; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -5001;
            }
        }
        int sum = 0;
        dp[0][0] = 0;
        for (int i = 1; i <= len; i++) {
            sum += rods[i - 1];
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                if (j + rods[i - 1] <= sum) {
                    dp[i][j + rods[i - 1]] = Math.max(dp[i - 1][j], dp[i][j + rods[i - 1]]);
                }

                dp[i][Math.abs(j - rods[i - 1])] = Math.max(dp[i][Math.abs(j - rods[i - 1])], dp[i - 1][j] + Math.min(j, rods[i - 1]));
            }
        }
        return dp[len][0];
    }

    //605
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n > flowerbed.length / 2 + 1) {
            return false;
        } else if (flowerbed.length == 0) {
            return false;
        } else if (flowerbed.length == 1) {
            return n == 0 || (n == 1 && flowerbed[0] == 0);
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (i == 0) {
                if (flowerbed[1] == 0 && flowerbed[i] == 0) {
                    flowerbed[0] = 1;
                    n--;
                }
            } else if (i == flowerbed.length - 1) {
                if (flowerbed[flowerbed.length - 2] == 0 && flowerbed[i] == 0) {
                    flowerbed[flowerbed.length - 1] = 1;
                    n--;
                }
            } else {
                if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0 && flowerbed[i] == 0) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
            if (n == 0) {
                return true;
            }
        }
        if (n <= 0) {
            return true;
        } else {
            return false;
        }
    }

    //389 - map:低效
    public char findTheDifference1(String s, String t) {
        if ("".equals(s)) {
            return t.charAt(0);
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        for (char c : chs) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        for (char c : cht) {
            if (!map.containsKey(c)) {
                return c;
            } else {
                int i = map.get(c) - 1;
                if (i < 0) {
                    return c;
                }
                map.put(c, i);
            }
        }
        return s.charAt(0);
    }

    //389 - 异或：高效
    public char findTheDifference(String s, String t) {
        char ci = t.charAt(0);
        for (int i = 0; i < s.length(); ++i) {
            ci ^= s.charAt(i);
            ci ^= t.charAt(i + 1);
        }
        return ci;
    }

    //963
    public double minAreaFreeRect(int[][] points) {
        Map<String, List<int[]>> map = new HashMap<>();
        double result = 0.0;
        boolean bb = false;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double[] ints = new double[2];
                double len = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                ints[0] = ((double) (points[i][0] + points[j][0])) / 2;
                ints[1] = ((double) (points[i][1] + points[j][1])) / 2;
                String str = "" + ints[0] + ints[1] + len;
                if (map.containsKey(str)) {
                    List<int[]> list = map.get(str);
                    for (int k = 0; k < list.size(); k++) {
                        double a = Math.sqrt(Math.pow(list.get(k)[0] - points[i][0], 2) + Math.pow(list.get(k)[1] - points[i][1], 2));
                        double b = Math.sqrt(Math.pow(list.get(k)[0] - points[j][0], 2) + Math.pow(list.get(k)[1] - points[j][1], 2));
                        double area = a * b;
                        if (!bb) {
                            result = area;
                            bb = true;
                        } else {
                            result = result > area ? area : result;
                        }
                    }
                    list.add(points[i]);
                } else {
                    List<int[]> list = new LinkedList<>();
                    list.add(points[i]);
                    map.put(str, list);
                }
            }
        }
        return result;
    }


    //783
    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 0;
        }
        List<Integer> list = new LinkedList<Integer>();
        list.add(root.val);
        minDiffInBSTAss(root, list);
        Collections.sort(list);
        int res = list.get(1) - list.get(0);
        for (int i = 1; i < list.size() - 1; i++) {
            res = res < list.get(i + 1) - list.get(i) ? res : list.get(i + 1) - list.get(i);
        }
        return res;
    }

    public void minDiffInBSTAss(TreeNode root, List<Integer> list) {
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            list.add(root.left.val);
            minDiffInBSTAss(root.left, list);
        }
        if (root.right != null) {
            list.add(root.right.val);
            minDiffInBSTAss(root.right, list);
        }
        return;
    }

    //530
    public int getMinimumDifference(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack();
        int res = 0, x = 0;
        boolean y = false, resy = false;
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                root = stack.pop();
                if (!y) {
                    x = root.val;
                    y = true;
                } else if (!resy) {
                    res = root.val - x;
                    x = root.val;
                    resy = true;
                } else {
                    x = root.val - x;
                    res = res < x ? res : x;
                    x = root.val;
                }
                root = root.right;
            }
        }
        return res;
    }

    //557 -- 低效 -- StringBuffer: append() 的效率远高于 String: +
    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuffer res = new StringBuffer();
        for (int j = 0; j < strings.length; j++) {
            String re = "";
            for (int i = strings[j].length() - 1; i >= 0; i--) {
                re = re + strings[j].charAt(i);
            }
            if (j == strings.length - 1) {
                res.append(re);
            } else {
                res.append(re + " ");
            }
        }
        return res.toString();
    }

    //557 -- 高效
    public String reverseWords0(String s) {
        StringBuilder res = new StringBuilder();
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            char[] chars = str.toCharArray();
            int front = 0;
            int tail = chars.length - 1;
            for (int j = 0; j < chars.length; j++) {
                if (front > tail) {
                    break;
                }
                char temp = chars[front];
                chars[front] = chars[tail];
                chars[tail] = temp;
                front++;
                tail--;
            }
            res.append(new String(chars) + (i == split.length - 1 ? "" : " "));
        }
        return res.toString();
    }

    //316
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public String removeDuplicateLetters0(String s) {
        if ("".equals(s)) {
            return "";
        }
        int[] ints = new int[26];
        for (int i = 0; i < 26; i++) {
            ints[i] = -1;
        }
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - 'a';
            if (ints[x] != -1) {
                int j = ints[x];
                if (list.size() == j + 1 || list.get(j) - list.get(j + 1) < 0) {
                    continue;
                } else {
                    list.remove(j);
                    ints[x] = list.size();
                    list.add(s.charAt(i));
                }
            } else {
                ints[x] = list.size();
                list.add(s.charAt(i));
            }
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            str.append(list.get(i));
        }
        return str.toString();
    }

    //316
    public String removeDuplicateLetters(String s) {
        //计算26字母数量
        int[] charsCount = new int[26];
        //标记字母是否已经入栈
        boolean[] visited = new boolean[26];
        int len = s.length();
        char[] sChars = s.toCharArray();
        for (char c : sChars) {
            charsCount[c - 'a']++;
        }
        Stack<Character> stack = new Stack<>();
        //最终字符的长度
        int index = 0;
        for (int count : charsCount) {
            if (count > 0) {
                index++;
            }
        }
        char[] res = new char[index];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            //有小字符的且满足其前面的字符在小字符后还有同样字符的，则出栈
            while (!stack.isEmpty() && c < stack.peek()
                    && charsCount[stack.peek() - 'a'] > 1 && !visited[c - 'a']) {
                Character pop = stack.pop();
                visited[pop - 'a'] = false;
                charsCount[pop - 'a']--;
            }
            if (visited[c - 'a']) {
                //重复的字符根据游标往后移动，数量减一
                charsCount[c - 'a']--;
                continue;
            }
            stack.push(c);
            visited[c - 'a'] = true;
        }

        while (!stack.isEmpty()) {
            res[--index] = stack.pop();
        }
        return String.valueOf(res);
    }

    //450
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                root = null;
                return root;
            } else if (root.left != null) {
                TreeNode p = root;
                TreeNode q = root.left;
                boolean b = false;
                while (q.right != null) {
                    p = q;
                    q = q.right;
                    b = true;
                }
                if (b) {
                    p.right = q.left;
                } else {
                    p.left = q.left;
                }
                root.val = q.val;
                return root;
            } else {
                root = root.right;
                return root;
            }
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    //864:DFS:failed
    public int shortestPathAllKeys1(String[] grid) {
        int res = 0;
        boolean[] bls = new boolean[6];
        char[][] chars = new char[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            chars[i] = grid[i].toCharArray();
        }
        int bx = 0, by = 0;
        int end = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (chars[i][j] == '@') {
                    bx = i;
                    by = j;
                    chars[i][j] = '.';
//                    break;
                } else if (chars[i][j] >= 'a' && chars[i][j] <= 'f') {
                    end++;
                }
            }
        }
        int[] have = new int[2];
        have[0] = 0;
        have[1] = end;
        res = shortestPathAllKeysDFS(chars, bx, by, bls, have, 0, 0);
        return have[0] != have[1] ? -1 : res;
    }

    //from 0:起点, 1:左, 2:右, 3:上,4:下
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int shortestPathAllKeysDFS(char[][] chars, int x, int y, boolean[] keys, int[] have, int walks, int from) {
        if (have[0] == have[1]) {
            return walks;
        } else {
            int h = have[0];
            if (y > 0 && chars[x][y - 1] != '#' && from != 1 && have[0] != have[1]) {
                walks = shortestPathAllKeysDFSAss(chars, x, y - 1, keys, have, walks, 2);
            }
            if (y < chars[0].length - 1 && chars[x][y + 1] != '#' && from != 2 && have[0] != have[1]) {
                walks = shortestPathAllKeysDFSAss(chars, x, y + 1, keys, have, walks, 1);
            }
            if (x > 0 && chars[x - 1][y] != '#' && from != 3 && have[0] != have[1]) {
                walks = shortestPathAllKeysDFSAss(chars, x - 1, y, keys, have, walks, 4);
            }
            if (x < chars.length - 1 && chars[x + 1][y] != '#' && from != 4 && have[0] != have[1]) {
                walks = shortestPathAllKeysDFSAss(chars, x + 1, y, keys, have, walks, 3);
            }
//            if (have[0] != h) {
//                return walks+1;
//            }
            return walks;
        }
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int shortestPathAllKeysDFSAss(char[][] chars, int x, int y, boolean[] keys, int[] have, int walks, int from) {
        if (chars[x][y] != '.') {
            if (chars[x][y] >= 'a') {
                keys[chars[x][y] - 'a'] = true;
                have[0]++;
                walks = shortestPathAllKeysDFS(chars, x, y, keys, have, walks + 1, from);
            } else if (keys[chars[x][y] - 'A']) {
                walks = shortestPathAllKeysDFS(chars, x, y, keys, have, walks + 1, from);
            } else {
                return walks;
            }
        } else {
            walks = shortestPathAllKeysDFS(chars, x, y, keys, have, walks + 1, from);
        }
        return have[0] == have[1] ? walks : walks + 1;
    }

    //864:BFS
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int totalkeys = 0;
        int havekeys = 0;
        int[][][] status = new int[m][n][64];
        char[][] chars = new char[m][n];
        for (int i = 0; i < m; i++) {
            chars[i] = grid[i].toCharArray();
        }
        int[] keys = new int[6];
        int x = 0, y = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = chars[i][j];
                if (c == '@') {
                    x = i;
                    y = j;
                } else if (c >= 'a' && c <= 'f') {
                    keys[c - 'a'] = 1;
                    totalkeys = totalkeys | (1 << c - 'a');
                }
            }
        }

        Deque<int[]> deque = new ArrayDeque();
        int[] point = new int[3];
        point[0] = x;
        point[1] = y;
        point[2] = havekeys;
        deque.add(point);
        int[][] dirs = new int[4][2];
        dirs[0][0] = 0;
        dirs[0][1] = 1;
        dirs[1][0] = 0;
        dirs[1][1] = -1;
        dirs[2][0] = 1;
        dirs[2][1] = 0;
        dirs[3][0] = -1;
        dirs[3][1] = 0;
        int step = 0;
        while (!deque.isEmpty()) {
            int nq = deque.size();
            while (nq > 0) {
                nq--;
                int[] p = deque.pop();
                havekeys = p[2];
                if (havekeys == totalkeys) {
                    return step;
                }
                for (int i = 0; i < 4; i++) {
                    x = p[0] + dirs[i][0];
                    y = p[1] + dirs[i][1];
                    havekeys = p[2];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    char c = chars[x][y];
                    if (c == '#') {
                        continue;
                    }
                    if (c >= 'A' && c <= 'F') {
                        if ((havekeys & (1 << (c - 'A'))) == 0) {
                            continue;
                        }
                    }
                    if (c >= 'a' && c <= 'f') {
                        keys[c - 'a'] = 2;
                        havekeys = havekeys | (1 << c - 'a');
                    }
                    if (status[x][y][havekeys] == 1) {
                        continue;
                    }
                    int[] newpoint = new int[3];
                    newpoint[0] = x;
                    newpoint[1] = y;
                    newpoint[2] = havekeys;
                    deque.add(newpoint);
                    status[x][y][havekeys] = 1;
                }
            }
            step = step + 1;
        }
        return -1;
    }

    //507: ****  求约数的好解法 ****
    public boolean checkPerfectNumber(int num) {
        if (num == 0) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                list.add(i);
                if (num != i * i) {
                    list.add(num / i);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != num) {
                res += list.get(i);
            }
        }
        return res == num;
    }

    //857: -- 2285ms -- 0%
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker ws = new Worker();
        for (int i = 0; i < wage.length; i++) {
            ws.add(quality[i], wage[i]);
        }
        ws.sortByPay(2);
        double res = 0.0;
        double pay = ws.qua.get(K - 1).get(2);
        int totalq = 0;
        Worker wuse = new Worker();
        for (int i = 0; i < K; i++) {
            totalq += ws.qua.get(i).get(0);
            wuse.add(ws.qua.get(i));
        }
        res = pay * (double) totalq;
        for (int i = K; i < wage.length; i++) {
            double r = 0;
            wuse.sortByPay(0);
            wuse.qua.remove(wuse.qua.size() - 1);
            wuse.add(ws.qua.get(i));
            pay = ws.qua.get(i).get(2);
            r = (double) wuse.sum(K) * pay;
            res = res < r ? res : r;
        }
        return res;
    }

    //733
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length) {
            return image;
        }
        if (image[sr][sc] == newColor) {
            return image;
        }
        int key = image[sr][sc];
        image[sr][sc] = newColor;
        int[][] dir = new int[4][2];
        dir[0][0] = 0;
        dir[0][1] = 1;
        dir[1][0] = 0;
        dir[1][1] = -1;
        dir[2][0] = 1;
        dir[2][1] = 0;
        dir[3][0] = -1;
        dir[3][1] = 0;
        int[] point = new int[2];
        point[0] = sr;
        point[1] = sc;
        Queue<int[]> q = new LinkedList<>();
        q.add(point);
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = p[0] + dir[i][0];
                int y = p[1] + dir[i][1];
                if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
                    continue;
                }
                if (image[x][y] != key) {
                    continue;
                }
                image[x][y] = newColor;
                int[] np = new int[2];
                np[0] = x;
                np[1] = y;
                q.add(np);
            }
        }
        return image;
    }

    //837 -- 超时
    public double new21Game0(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        double pickOne = 1 / (double) W;
        Map<Integer, Double> map = new HashMap<>();
        int min = 1;
        for (int i = 1; i <= W; i++) {
            map.put(i, pickOne);
        }
        while (min < K) {
            double p = map.get(min);
            map.remove(min);
            for (int i = 1; i <= W; i++) {
                int k = min + i;
                if (map.containsKey(k)) {
                    map.put(k, map.get(k) + p * pickOne);
                } else {
                    map.put(k, p * pickOne);
                }
            }
            min++;
        }
        int n = K;
        double res = 0.0;
        while (map.containsKey(n) && n <= N) {
            res += map.get(n);
            n++;
        }
        return res;
    }

    //837 -- 251ms -- 0%
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1;
        }
        if (K <= 1) {
            return (double) N / (double) W;
        }
        if (N < K) {
            return 0.0;
        }
        double res = 0.0;
        int min = 1;
        double rate = 1 / (double) W;
        List<Double> list = new LinkedList<>();
        list.add(1.0);
        double st = rate;
        list.add(st);
        while (min < W && min < K) {
            st = st * (1 + rate);
            list.add(st);
            min++;
        }
        int sr = 0;
        while (min >= W && min < K) {
            st = st * (1 + rate) - list.get(sr) / W;
            list.add(st);
            sr++;
            min++;
        }
//        while (min < W + K) {
//            if (min >= W) {
//                st = st - list.get(sr) / W;
//                sr++;
//            }
//            list.add(st);
//            min++;
//        }
//        for(int i = K;i < list.size();i++){
//            res = res + list.get(i);
//        }
        res = 0.0;
        while (min >= K && min <= N && sr < list.size()) {
            res = res + st;
            if (min >= W) {
                st = st - list.get(sr) / W;
                list.add(st);
                sr++;
            }
            min++;
        }
        return res > 1 ? 1 : res;
    }

    //818 -- BFS + Set -- 2053ms -- 0.00%
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int racecar0(int target) {
        if (0 == target) {
            return 0;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        Set<String> had = new HashSet<>();
        int[] stp = new int[3];
        stp[0] = 0;
        stp[1] = 1;
        stp[2] = 0;
        queue.add(stp);
        String str = "s=" + stp[0] + "v=" + stp[1];
        had.add(str);
        while (!queue.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                int s = queue.peek()[0];
                int v = queue.peek()[1];
                int t = queue.peek()[2] + 1;
                if (i == 0) {
                    s += v;
                    v *= 2;
                } else {
                    v = v > 0 ? -1 : 1;
                }
                if (s == target) {
                    return t;
                }
                int[] po = new int[3];
                po[0] = s;
                po[1] = v;
                po[2] = t;
                str = "s=" + s + "v=" + v;
                if (had.contains(str)) {
                    continue;
                }
                had.add(str);
                queue.add(po);
            }
            queue.poll();
        }
        return 0;
    }

    //818 -- 动态规划 -- 16ms -- 53.53%
    public int racecar(int target) {
        //加2防止target过小，省去写多个if-return
        int[] dp = new int[target + 2];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 4;

        for (int i = 3; i <= target; i++) {
            //注意这里的技巧，32 - numberOfLeadingZeros(i)返回i的最高位从右往左对应的下标
            //例如11返回2
            int k = 32 - Integer.numberOfLeadingZeros(i);
            //最好的情况，直接等于k
            if ((1 << k) - 1 == i) {
                dp[i] = k;
                continue;
            }

            for (int j = 0; j < k - 1; j++) {
                dp[i] = Math.min(dp[i], dp[i - (1 << (k - 1)) + (1 << j)] + k - 1 + j + 2);
            }
            if ((1 << k) - 1 - i < i) {
                dp[i] = Math.min(dp[i], dp[(1 << k) - 1 - i] + k + 1);
            }
        }

        return dp[target];
    }

    //264 -- 暴力 -- 885ms --0.95%
    public int nthUglyNumber0(int n) {
        if (1 == n) {
            return 1;
        }
        int vall1 = 2;
        int vall2 = 3;
        int vall3 = 5;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        List<Integer> list = new LinkedList<>();
        list.add(1);
        for (int i = 1; i < n; ) {
            int current = Math.min(vall1, Math.min(vall2, vall3));
            if (!list.contains(current)) {
                list.add(current);
                i++;
            }
            if (current == vall1) {
                i1 += 1;
                vall1 = list.get(i1) * 2;
            } else if (current == vall2) {
                i2 += 1;
                vall2 = list.get(i2) * 3;
            } else if (current == vall3) {
                i3 += 1;
                vall3 = list.get(i3) * 5;
            }
        }
        return list.get(n - 1);
    }

    //264 -- 10ms -- 65%
    public int nthUglyNumber1(int n) {
        int[] num = new int[n];
        num[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int[] result = new int[n];
        result[0] = 1;

        int begin = 1;
        while (begin < n) {
            result[begin] = Math.min(result[index2] * 2, Math.min(result[index3] * 3, result[index5] * 5));
            if (result[begin] == result[index2] * 2) {
                index2++;
            }
            if (result[begin] == result[index3] * 3) {
                index3++;
            }
            if (result[begin] == result[index5] * 5) {
                index5++;
            }
            begin++;
        }
        return result[--begin];
    }

    //264 -- 11ms -- 56.43%
    public int nthUglyNumber(int n) {
        int[] res = new int[n + 1];
        int i2 = 0, i3 = 0, i5 = 0;
        res[0] = 1;
        int k = 0;
        while (k < n) {
            int m2 = res[i2] * 2, m3 = res[i3] * 3, m5 = res[i5] * 5;
            int mn = Math.min(Math.min(m2, m3), m5);
            if (m2 == mn) {
                i2 += 1;
            }
            if (m3 == mn) {
                i3 += 1;
            }
            if (m5 == mn) {
                i5 += 1;
            }
            res[++k] = mn;
        }
        return res[k - 1];
    }

    //649 -- 518ms -- 9.33%
    public String predictPartyVictory(String senate) {
        List<Integer> radiant = new LinkedList<>();
        List<Integer> dire = new LinkedList<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                radiant.add(i);
            } else {
                dire.add(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int x = 0;
            int y = 0;
            while (x < radiant.size() && y < dire.size()) {
                int a = radiant.get(x);
                int b = dire.get(y);
                if (a < b) {
                    dire.remove(y);
                    x++;
                } else {
                    radiant.remove(x);
                    y++;
                }
            }
            while (!dire.isEmpty() && x < radiant.size()) {
                dire.remove(0);
                x++;
            }
            while (!radiant.isEmpty() && y < dire.size()) {
                radiant.remove(0);
                y++;
            }
        }
        if (radiant.isEmpty()) {
            return "Dire";
        } else {
            return "Radiant";
        }
    }

    //865 -- 7ms -- 36.59%
    public TreeNode subtreeWithAllDeepest0(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        Map<TreeNode, TreeNode> m = new HashMap<>();
        queue.add(root);
        int l = 0;
        while (!queue.isEmpty()) {
            int i = queue.size();
            l++;
            while (i > 0) {
                TreeNode t = queue.poll();
                if (t.left == null && t.right == null) {
                    map.put(t, l);
                } else if (t.left != null) {
                    queue.add(t.left);
                    m.put(t.left, t);
                }
                if (t.right != null) {
                    queue.add(t.right);
                    m.put(t.right, t);
                }
                i--;
            }
        }
        Set<TreeNode> set = new HashSet<>();
        Iterator<Map.Entry<TreeNode, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<TreeNode, Integer> entry = it.next();
            if (l == entry.getValue()) {
                set.add(entry.getKey());
            }
        }
        while (set.size() != 1) {
            Set<TreeNode> st = new HashSet<>();
            for (TreeNode t : set) {
                TreeNode p = m.get(t);
                st.add(p);
            }
            set.removeAll(set);
            set.addAll(st);
            st.removeAll(st);
        }
        for (TreeNode t : set) {
            return t;
        }
        return null;
    }

    //865 -- 4ms -- 95.12%
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        /**
         根据题意描述, 是要找到一个节点, 以该节点为根的树中包含所有最大深度节点
         那么只要左子树的最大深度等于右子树的最大深度, 就说明左右子树都包含最大
         深度节点, 此时该节点就是满足条件的节点. 否则进入深度较大的那侧继续判断
         **/
        if (root == null) {
            return null;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if (left == right) {
            return root;
        }
        if (left > right) {
            return subtreeWithAllDeepest(root.left);
        } else {
            return subtreeWithAllDeepest(root.right);
        }
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    //888 -- 358ms -- 26.96%
    public int[] fairCandySwap0(int[] A, int[] B) {
        int sumA = 0;
        for (int i : A) {
            sumA += i;
        }
        int sumB = 0;
        for (int i : B) {
            sumB += i;
        }
        int sub = (sumA - sumB) / 2;
        int[] res = new int[2];
        for (int i : A) {
            for (int j : B) {
                if (i == sub + j) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    //888 -- 35ms -- 83.37%
    public int[] fairCandySwap(int[] A, int[] B) {
        Arrays.sort(B);
        //每个人的总量：
        int t = 0;
        int ta = 0;
        for (int n : A) {
            t += n;
            ta += n;
        }
        for (int n : B) {
            t += n;
        }
        t /= 2;
        for (int x : A) {
            int y = ta - x;
            if (Arrays.binarySearch(B, t - y) >= 0) {
                return new int[]{x, t - y};
            }
        }
        return new int[]{};
    }

    //407 -- 115ms -- 13.95% -- not me
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int trapRainWater(int[][] heightMap) {
        //一个单元格用一个Cell来表示
        class Cell {
            int x, y, h;

            Cell(int x, int y, int height) {
                this.x = x;
                this.y = y;
                h = height;
            }
        }
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int m = heightMap.length;
        int n = heightMap[0].length;
        //优先队列，每次按照优先度输出队列，而不是按照顺序，这里是每次输出最矮的哪一个
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.h));
        boolean[][] visited = new boolean[m][n];
        //将四周初始化为访问过的，周围的一边是怎么都没法盛水的
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            pq.offer(new Cell(0, i, heightMap[0][i]));
            pq.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
        }
        for (int i = 1; i < m - 1; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
        }
        //四个方向
        int[] xs = {0, 0, 1, -1};
        int[] ys = {1, -1, 0, 0};
        int sum = 0;
        //开始计算收集到的雨水，每次取出符合条件最矮的按个，然后计算差值，就是当前单元格可以容纳的了
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cell.x + xs[i];
                int ny = cell.y + ys[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    sum += Math.max(0, cell.h - heightMap[nx][ny]);
                    pq.offer(new Cell(nx, ny, Math.max(heightMap[nx][ny], cell.h)));
                }
            }
        }
        return sum;
    }

    //20 -- 11ms -- 60.42%
    public boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        } else if (s.length() == 1) {
            return false;
        }
        char[] cs = s.toCharArray();
        Stack<Character> st = new Stack<>();
        for (char c : cs) {
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
                continue;
            }
            if (c == ')') {
                if (st.empty()) {
                    return false;
                }
                char t = st.pop();
                if (t != '(') {
                    return false;
                }
                continue;
            }
            if (c == ']') {
                if (st.empty()) {
                    return false;
                }
                char t = st.pop();
                if (t != '[') {
                    return false;
                }
                continue;
            }
            if (c == '}') {
                if (st.empty()) {
                    return false;
                }
                char t = st.pop();
                if (t != '{') {
                    return false;
                }
                continue;
            }
        }
        if (st.empty()) {
            return true;
        } else {
            return false;
        }
    }

    //290 -- 2ms -- 70.40%
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null && str == null) {
            return true;
        } else if (pattern == null && str != null) {
            return false;
        } else if (pattern != null && str == null) {
            return false;
        }
        String[] sts = str.split(" ");
        char[] cs = pattern.toCharArray();
        if (cs.length != sts.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
//        Set<String> set = new HashSet<>();
        for (int i = 0; i < cs.length; i++) {
            if (map.containsKey(cs[i])) {
                if (!map.get(cs[i]).equals(sts[i])) {
                    return false;
                }
            } else if (map.containsValue(sts[i])) {
                return false;
            } else {
                map.put(cs[i], sts[i]);
//                set.add(sts[i]);
            }
        }
        return true;
    }

    //773 -- 13ms -- 80.00%
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int slidingPuzzle(int[][] board) {
        int r1 = 123450;
        int r2 = 123540;
        int b = 0;
        for (int[] i : board) {
            for (int j : i) {
                b = b * 10 + j;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        q.add(b);
        int k = 0;
        while (!q.isEmpty()) {
            int p = q.poll();
            k = p / 1000000;
            int t = p % 1000000;
            if (t == r2) {
                return -1;
            }
            if (t == r1) {
                return k;
            }
            int i = 6;
            int tt = t;
            while (tt / 10 * 10 != tt) {
                tt = tt / 10;
                i--;
            }
            switch (i) {
                case 6: {
                    int newp = t - t % 10000 + t % 1000 + t % 10000 / 1000;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t - t % 100 + t % 100 / 10;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 5: {
                    int newp = t - t / 10000 % 10 * 9990;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t - t / 100 % 10 * 90;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t + t % 10 * 9;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 4: {
                    int newp = t - t / 100000 * 99900;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t + t / 10 % 10 * 90;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 3: {
                    int newp = t - t % 100000 + t % 1000 + t % 100000 / 10000 * 1000;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t - t % 10 + t % 10 * 1000;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 2: {
                    int newp = t - t / 100000 * 90000;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t + t % 10000 / 1000 * 9000;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t + t % 100 / 10 * 9990;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 1: {
                    int newp = t + t / 10000 * 90000;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t - t / 100 % 10 * 100 + t / 100 % 10 * 100000;
                    if (!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
            }
        }
        return -1;
    }

    //29 -- 25ms -- 87.50%
    public int divide(int dividend, int divisor) {
        //处理异常
        if (divisor == 0) {
            return 0;
        }
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        //处理最大最小值取模的情况。
        long divid = (long) dividend;
        long divi = (long) divisor;
        //减少重复运算
        if (divi == 1) {
            return (int) divid;
        }
        if (divi == -1) {
            //处理溢出
            if (divid <= min) {
                return max;
            }
            return (int) -divid;
        }
        boolean flag = true;
        //处理符号
        if (divid < 0) {
            divid = -divid;
            if (divi < 0) {
                divi = -divi;
            } else {
                flag = false;
            }
        } else if (divi < 0) {
            divi = -divi;
            flag = false;
        }
        long res = 0;
        long tmp = 0;
        long cnt = 1;
        while (divi <= divid) {
            //2^n次方
            cnt = 1;
            tmp = divi;
            //找到第一个大于被除数的2^n次方
            while (tmp <= divid) {
                tmp <<= 1;
                cnt <<= 1;
            }
            res += (cnt >> 1);
            //减去基数的前一个数
            divid -= (tmp >> 1);
        }
        return flag ? (int) res : (int) -res;
    }

    //35 -- 5ms -- 70.89%
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    //763 -- 32ms -- 14.84%
    public List<Integer> partitionLabels0(String S) {
        char[] cs = S.toCharArray();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < cs.length; i++) {
            if (map.containsKey(cs[i])) {
                List list = map.get(cs[i]);
                list.add(i);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(i);
                map.put(cs[i], list);
            }
        }
        List<Integer> res = new LinkedList<>();

        int n = 0;
        int end = cs.length;
        int k = 0;
        int m = -1;
//        if(map.get(cs[0]).size() == 1){
//            res.add(1);
//            m++;
//        }
        while (n < end) {
            List<Integer> l = map.get(cs[n]);
            int kk = l.get(l.size() - 1);
            k = kk > k ? kk : k;
            if (n == k) {
                res.add(n - m);
                m = n;
            }
            n++;
        }
        return res;
    }

    //763 -- 13ms -- 79.68%
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int index, i, len = S.length();
        int[] cache = new int[26];
        for (i = 0; i < len; i++) {
            cache[S.charAt(i) - 'a'] = i;
        }
        i = 0;
        while (i < len) {
            index = cache[S.charAt(i) - 'a'];
            for (int j = i + 1; j < index && j < len; j++) {
                if (cache[S.charAt(j) - 'a'] > index) {
                    index = cache[S.charAt(j) - 'a'];
                }
            }
            res.add(index - i + 1);
            i = index + 1;
        }
        return res;
    }

    //632 -- 734ms -- 0.00%
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        int[] q = new int[nums.size()];
        List<Integer> p = new ArrayList<>();
        for (List<Integer> l : nums) {
            p.add(l.get(0));
        }
        int[] m = findMin(p, nums, q);
        int min = m[0];
        int max = m[1];
        res[0] = nums.get(min).get(q[min]);
        res[1] = nums.get(max).get(q[max]);
        while (true) {
            int mi = nums.get(min).get(q[min]);
            int ma = nums.get(max).get(q[max]);
            if (res[1] - res[0] > ma - mi) {
                res[1] = ma;
                res[0] = mi;
            }
            q[min]++;
            if (q[min] >= nums.get(min).size()) {
                break;
            }
            p.set(min, nums.get(min).get(q[min]));
            m = findMin(p, nums, q);
            min = m[0];
            max = m[1];
        }
        return res;
    }

    public int[] findMin(List<Integer> l, List<List<Integer>> nums, int[] q) {
        int min = 0;
        int max = 0;
        for (int i = 0; i < l.size(); i++) {
            int mi = l.get(min);
            int ma = l.get(max);
            int ii = l.get(i);
            min = mi > ii ? i : min;
            max = ma < ii ? i : max;
//            if (mi == ii) {
//                int mmi = nums.get(mi).get(q[min]);
//                int iii = nums.get(ii).get(q[ii]);
//                min = mmi > iii ? i : min;
//            }
        }
        int[] m = new int[2];
        m[0] = min;
        m[1] = max;
        return m;
    }

    //537 -- 5ms -- 90.87%
    public String complexNumberMultiply(String a, String b) {
        String[] sa = a.split("\\+");
        String[] sb = b.split("\\+");
        int sa1 = Integer.valueOf(sa[0]);
        int sb1 = Integer.valueOf(sb[0]);
        int sa2 = Integer.valueOf(sa[1].substring(0, sa[1].length() - 1));
        int sb2 = Integer.valueOf(sb[1].substring(0, sb[1].length() - 1));
        int res1 = sa1 * sb1 - sa2 * sb2;
        int res2 = sa1 * sb2 + sa2 * sb1;
        StringBuffer res = new StringBuffer();
        res.append(res1).append("+").append(res2).append("i");
        return res.toString();
    }

    //59 -- 2ms -- 83.92%
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        boolean[][] bs = new boolean[n][n];
        int dir = 0;
        int k = 1;
        int nn = n * n;
        int x = 0, y = -1;
        while (k <= nn) {
            switch (dir) {
                case 0: {
                    y++;
                    while (y < n && !bs[x][y]) {
                        res[x][y] = k;
                        bs[x][y] = true;
                        y++;
                        k++;
                    }
                    y--;
                    break;
                }
                case 1: {
                    x++;
                    while (x < n && !bs[x][y]) {
                        res[x][y] = k;
                        bs[x][y] = true;
                        x++;
                        k++;
                    }
                    x--;
                    break;
                }
                case 2: {
                    y--;
                    while (y >= 0 && !bs[x][y]) {
                        res[x][y] = k;
                        bs[x][y] = true;
                        y--;
                        k++;
                    }
                    y++;
                    break;
                }
                case 3: {
                    x--;
                    while (x > 0 && !bs[x][y]) {
                        res[x][y] = k;
                        bs[x][y] = true;
                        x--;
                        k++;
                    }
                    x++;
                    break;
                }
            }
            dir = (dir + 1) % 4;
        }
        return res;
    }

    //167 -- 6ms -- 31.06%
    public int[] twoSum0(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int j = target - numbers[i];
            if (map.containsKey(j)) {
                int[] res = new int[2];
                res[0] = map.get(j) + 1;
                res[1] = i + 1;
                return res;
            }
            map.put(numbers[i], i);
        }
        return null;
    }

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0;
        int h = numbers.length;
        while (l < h) {
            int key = (l + h) / 2;
            int j = target - numbers[key];
            if (map.containsKey(j)) {
                int[] res = new int[2];
                res[0] = map.get(j) + 1;
                res[1] = key + 1;
                return res;
            }
            map.put(numbers[key], key);
            if (j > 0) {
                h = key;
            } else {
                l = key;
            }
        }
        return null;
    }

    //915 -- 5ms -- 98.36%
    public int partitionDisjoint(int[] A) {
        int i = 0, len = A.length, j = len - 1;
        int k = 0;
        while (k < j) {
            while (A[i] <= A[j]) {
                j--;
                if (j == k) {
                    return k + 1;
                }
            }
            while (k < j) {
                if (A[i] < A[k]) {
                    i = k;
                }
                k++;
            }
            j = len - 1;
        }
        return 0;
    }

    //70 -- 3ms -- 81.01%
    public int climbStairs(int n) {
        double d = Math.sqrt(5);
        n = n + 1;
        return (int) ((Math.pow((1 + d) / 2, n) - Math.pow((1 - d) / 2, n)) / d + 0.5);
    }

    //807 -- 8ms -- 96.35%
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] l = new int[grid.length];
        int[] d = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                l[i] = l[i] > grid[i][j] ? l[i] : grid[i][j];
                d[j] = d[j] > grid[i][j] ? d[j] : grid[i][j];
            }
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int max = l[i] < d[j] ? l[i] : d[j];
                res = res + max - grid[i][j];
            }
        }
        return res;
    }

    //299 -- 4ms -- 79.55%
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public String getHint(String secret, String guess) {
        int[] se = new int[10];
        int[] gu = new int[10];
        int nA = 0, nB = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                nA++;
            } else {
                se[secret.charAt(i) - '0']++;
                gu[guess.charAt(i) - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            nB += se[i] < gu[i] ? se[i] : gu[i];
        }
        StringBuffer s = new StringBuffer();
        s.append(nA).append('A').append(nB).append('B');
        return s.toString();
    }

    //101 -- 17ms -- 22.01%
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null && root.right != null) {
            return false;
        }
        if (root.left != null && root.right == null) {
            return false;
        }
        Stack<TreeNode> ls = new Stack<>();
        Stack<TreeNode> rs = new Stack<>();
        ls.add(root.left);
        rs.add(root.right);
        while (!ls.empty() && !rs.empty()) {
            TreeNode l = ls.pop();
            TreeNode r = rs.pop();
            if (l.val != r.val) {
                return false;
            }
            if (l.left != null && r.right == null) {
                return false;
            }
            if (l.left == null && r.right != null) {
                return false;
            }
            if (l.right != null && r.left == null) {
                return false;
            }
            if (l.right == null && r.left != null) {
                return false;
            }
            if (l.left != null) {
                ls.add(l.left);
                rs.add(r.right);
            }
            if (l.right != null) {
                ls.add(l.right);
                rs.add(r.left);
            }
        }
        return true;
    }

    //113 -- 67ms -- 1.13%
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        List<Integer> li = new LinkedList<>();
        li.add(root.val);
        int s = root.val;
        List<Object> l = new ArrayList<>();
        l.add(root);
        l.add(li);
        l.add(s);
        Stack<List<Object>> stack = new Stack();
        stack.add(l);
        while (!stack.isEmpty()) {
            List<Object> p = stack.pop();
            TreeNode pt = (TreeNode) p.get(0);
            List<Integer> pl = (List<Integer>) p.get(1);
            int ps = (int) p.get(2);
            if (pt.left != null) {
                TreeNode nt = pt.left;
                List<Integer> nl = new LinkedList<>();
                pl.forEach(i -> nl.add(i));
                nl.add(nt.val);
                int ns = ps + nt.val;
                List<Object> n = new ArrayList<>();
                n.add(nt);
                n.add(nl);
                n.add(ns);
                stack.add(n);
            }
            if (pt.right != null) {
                TreeNode nt = pt.right;
                List<Integer> nl = new LinkedList<>();
                pl.forEach(i -> nl.add(i));
                nl.add(nt.val);
                int ns = ps + nt.val;
                List<Object> n = new ArrayList<>();
                n.add(nt);
                n.add(nl);
                n.add(ns);
                stack.add(n);
            }
            if (pt.left == null && pt.right == null) {
                if (ps == sum) {
                    res.add(pl);
                }
            }
        }
        return res;
    }

    //815 : 超时
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int r : routes[i]) {
                if (map.containsKey(r)) {
                    List<Integer> p = map.get(r);
                    p.add(i);
                } else {
                    List<Integer> p = new LinkedList<>();
                    p.add(i);
                    map.put(r, p);
                }
            }
        }
        if (!map.containsKey(T)) {
            return -1;
        }
        Map<Integer, Integer> ints = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] bools = new boolean[routes.length];
        int step = 0;
        ints.put(S, 0);
        queue.add(S);
        while (!queue.isEmpty()) {
            int key = queue.poll();
            step = ints.get(key);
            List<Integer> list = map.get(key);
            for (int ii = 0; ii < list.size(); ii++) {
                for (int j = 0; j < routes[list.get(ii)].length; j++) {
                    int i = routes[list.get(ii)][j];
                    if (i == T) {
                        return step + 1;
                    } else if (ints.containsKey(i)) {
                        continue;
                    } else {
                        ints.put(i, step + 1);
                        queue.add(i);
                    }
                }
            }
        }
        return -1;
    }

    //377 -- 动态规划 -- 2ms -- 92.67% -- 19.3MB -- 75.76%
    public int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target + 1];
        memo[0] = 1;
        for (int i = 0; i < target; i++) {
            for (int num : nums) {
                if (i + num <= target) {
                    memo[i + num] += memo[i];
                }
            }
        }
        return memo[target];
    }

    //377 -- 简单递归 -- 超时
    public int combinationSum4_1(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += combinationSum4_1(nums, target - num);
            }
        }
        return res;
    }

    //377 -- 记忆化搜索 -- 1ms -- 100% -- 20.5MB -- 19.69%
    private int[] memo;

    public int combinationSum4_2(int[] nums, int target) {
        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return search(nums, target);
    }

    private int search(int[] nums, int target) {
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += search(nums, target - num);
            }
        }
        memo[target] = res;
        return res;
    }

    //39 -- 递归 -- 21ms -- 58.65% -- 32.8MB -- 30.35%
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> listAll = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        //排序
        Arrays.sort(candidates);
        find(listAll, list, candidates, target, 0);
        return listAll;
    }

    public void find(List<List<Integer>> listAll, List<Integer> tmp, int[] candidates, int target, int num) {
        //递归的终点
        if (target == 0) {
            listAll.add(tmp);
            return;
        }
        if (target < candidates[0]) {
            return;
        }
        for (int i = num; i < candidates.length && candidates[i] <= target; i++) {
            //深拷贝
            List<Integer> list = new ArrayList<>(tmp);
            list.add(candidates[i]);
            //递归运算，将i传递至下一次运算是为了避免结果重复。
            find(listAll, list, candidates, target - candidates[i], i);
        }
    }

    //81 -- 2ms -- 65.20% -- 22.2MB -- 81.61%
    public boolean search81(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int p = nums[0];
        boolean isOver = false;
        for (int num : nums) {
            if (num == target) {
                return true;
            }
            if (p > num) {
                isOver = true;
            } else if (isOver && num > target) {
                return false;
            }
        }
        return false;
    }

    //33 -- 15ms -- 55.59% -- 26MB -- 61.44%
    public int search33_1(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int p = nums[0];
        boolean isOver = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
            if (p > nums[i]) {
                isOver = true;
            } else if (isOver && nums[i] > target) {
                return -1;
            }
        }
        return -1;
    }

    //33 -- 二分法 -- 11ms -- 76.61% -- 26.9MB -- 14.59%
    public int search33_2(int[] nums, int target) {
        return search33_2Ass2(0, nums.length - 1, nums, target);
    }

    //常规二分
    public int search33_2Ass(int b, int e, int[] nums, int target) {
        if (b > e) {
            return -1;
        }
        if (b == e) {
            return nums[b] == target ? b : -1;
        }
        if (nums[b] == target) {
            return b;
        }
        if (nums[e] == target) {
            return e;
        }
        int k = (b + e) / 2;
        int res = 0;
        if (target == nums[k]) {
            return k;
        } else if (nums[k] > target) {
            res = search33_2Ass(b + 1, k - 1, nums, target);
        } else {
            res = search33_2Ass(k + 1, e - 1, nums, target);
        }
        return res;
    }

    //非常规二分
    public int search33_2Ass2(int b, int e, int[] nums, int target) {
        if (b > e) {
            return -1;
        }
        if (b == e) {
            return nums[b] == target ? b : -1;
        }
        if (nums[b] == target) {
            return b;
        }
        if (nums[e] == target) {
            return e;
        }
        int k = (b + e) / 2;
        if (target == nums[k]) {
            return k;
        }
        int res = -1;
        if (nums[b] < nums[k]) {
            res = search33_2Ass(b + 1, k - 1, nums, target);
            if (res != -1) {
                return res;
            }
            res = search33_2Ass2(k + 1, e - 1, nums, target);
            if (res != -1) {
                return res;
            }
        } else {
            res = search33_2Ass2(b + 1, k - 1, nums, target);
            if (res != -1) {
                return res;
            }
            res = search33_2Ass(k + 1, e - 1, nums, target);
            if (res != -1) {
                return res;
            }
        }
        return res;
    }

    //50 -- 17ms -- 74.69% -- 27.3MB -- 51.25%
    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    //767 -- 14ms -- 54.14% -- 26.7MB -- 36.36%
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public String reorganizeString(String S) {
        char[] chars = S.toCharArray();
        int[] res = new int[26];
        List<Integer> list = new LinkedList<>();
        int max = 0;
        for (char c : chars) {
            res[c - 'a']++;
            if (res[c - 'a'] > max) {
                max = res[c - 'a'];
            }
            if (!list.contains(c - 'a')) {
                list.add(c - 'a');
            }
        }
        if (max > (S.length() + 1) / 2) {
            return "";
        }
        Object[] objects = list.toArray();
        StringBuffer stb = new StringBuffer();
        int m = -1;
        for (int i = 0; i < S.length(); i++) {
            int ma = m == 0 ? 1 : 0;
            for (Object jj : objects) {
                int j = (int) jj;
                if (j != m) {
                    ma = res[j] > res[ma] ? j : ma;
                }
            }
            stb.append((char) (ma + 'a'));
            res[ma]--;
            m = ma;
        }
        return stb.toString();
    }

    //980 -- 3ms -- 97.83% -- 26.5MB -- 100%
    public int uniquePathsIII(int[][] grid) {
        int[] start = new int[2];
        int[] end = new int[2];
        int p = 1;
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                }
                if (grid[i][j] == 2) {
                    end[0] = i;
                    end[1] = j;
                }
                if (grid[i][j] == 0) {
                    p++;
                }
            }
        }
        return dfs(start[0], start[1], p, row, col, grid, end);
    }

    public int dfs(int x, int y, int p, int row, int col, int[][] grid, int[] end) {
        boolean b = 0 <= x && x < row && 0 <= y && y < col && grid[x][y] >= 0;
        if (!b) {
            return 0;
        }
        if (end[0] == x && end[1] == y && p == 0) {
            return 1;
        }
        grid[x][y] = -1;
        int res = dfs(x + 1, y, p - 1, row, col, grid, end) +
                dfs(x, y + 1, p - 1, row, col, grid, end) +
                dfs(x - 1, y, p - 1, row, col, grid, end) +
                dfs(x, y - 1, p - 1, row, col, grid, end);
        grid[x][y] = 0;
        return res;
    }


    //699 -- 88ms -- 40% -- 37.6MB -- 100%
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new LinkedList<>();
        List<int[]> status = new ArrayList<>();
        int totalmax = 0;
        for (int[] p : positions) {
            int x = p[0];
            int l = p[1];
            int max = 0;
            int y = x + l;
            List<Integer> rem = new ArrayList<>();
            for (int j = 0; j < status.size(); j++) {
                int[] st = status.get(j);
                if (st[0] >= x && st[1] <= y) {
                    rem.add(j);
                    max = max > st[2] ? max : st[2];
                } else if (st[0] >= x && st[1] > y && st[0] < y) {
                    st[0] = y;
                    max = max > st[2] ? max : st[2];
                } else if (st[0] < x && st[1] <= y && st[1] > x) {
                    st[1] = x;
                    max = max > st[2] ? max : st[2];
                } else if (st[0] < x && st[1] > y) {
                    int[] newst = new int[3];
                    newst[0] = y;
                    newst[1] = st[1];
                    newst[2] = st[2];
                    status.add(newst);
                    st[1] = x;
                    max = max > st[2] ? max : st[2];
                }
                totalmax = totalmax > st[2] ? totalmax : st[2];
            }
            max += l;
            for (int j = rem.size() - 1; j >= 0; j--) {
                int k = rem.get(j);
                status.remove(k);
            }
            int[] newst = new int[3];
            newst[0] = x;
            newst[1] = y;
            newst[2] = max;
            status.add(newst);
            totalmax = totalmax > max ? totalmax : max;
            res.add(totalmax);
        }
        return res;
    }


    //958 -- LinkedBlockingQueue
    //执行用时 : 22 ms, 在Check Completeness of a Binary Tree的Java提交中击败了2.19% 的用户
    //内存消耗 : 37.8 MB, 在Check Completeness of a Binary Tree的Java提交中击败了0.00% 的用户
    public boolean isCompleteTree0(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (flag) {
                if (t.left != null || t.right != null) {
                    return false;
                }
            } else {
                if (t.left != null) {
                    queue.add(t.left);
                } else {
                    flag = true;
                }
                if (t.right != null) {
                    if (flag) {
                        return false;
                    } else {
                        queue.add(t.right);
                    }
                } else {
                    flag = true;
                }
            }
        }
        return true;
    }

    //958 -- LinkList
    //执行用时 : 11 ms, 在Check Completeness of a Binary Tree的Java提交中击败了66.42% 的用户
    //内存消耗 : 37.4 MB, 在Check Completeness of a Binary Tree的Java提交中击败了0.00% 的用户
    public boolean isCompleteTree1(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (flag) {
                if (t.left != null || t.right != null) {
                    return false;
                }
            } else {
                if (t.left != null) {
                    queue.add(t.left);
                } else {
                    flag = true;
                }
                if (t.right != null) {
                    if (flag) {
                        return false;
                    } else {
                        queue.add(t.right);
                    }
                } else {
                    flag = true;
                }
            }
        }
        return true;
    }

    //958 -- LinkList
    //执行用时 : 7 ms, 在Check Completeness of a Binary Tree的Java提交中击败了83.21% 的用户
    //内存消耗 : 37.5 MB, 在Check Completeness of a Binary Tree的Java提交中击败了0.00% 的用户
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        TreeNode cur;
        q.addLast(root);
        while ((cur = q.removeFirst()) != null) {
            q.addLast(cur.left);
            q.addLast(cur.right);
        }
        while (!q.isEmpty()) {
            if (q.removeLast() != null) {
                return false;
            }
        }
        return true;
    }

    //64 -- 真正最短路径（无规定）
    public int minPathSum(int[][] grid) {
        int x = grid.length - 1;
        int y = grid[0].length - 1;
        int[] p = new int[3];
        p[0] = x;
        p[1] = y;
        p[2] = grid[x][y];
        int[][] status = new int[x + 1][y + 1];
        for (int i = 0; i < status.length; i++) {
            for (int j = 0; j < status[i].length; j++) {
                status[i][j] = Integer.MAX_VALUE;
            }
        }
        int[][] dir = new int[4][2];
        dir[0][0] = 0;
        dir[0][1] = 1;
        dir[1][0] = 0;
        dir[1][1] = -1;
        dir[2][0] = 1;
        dir[2][1] = 0;
        dir[3][0] = -1;
        dir[3][1] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(p);
        int res = Integer.MAX_VALUE;

        //打印路径
        Map<String, List<int[]>> road = new HashMap<>();
        List<int[]> r = new LinkedList<>();
        r.add(p);
        String str = "x=" + p[0] + ",y=" + p[1];
        road.put(str, r);
        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            int tx = t[0];
            int ty = t[1];
            int tv = t[2];
            if (tx == 0 && ty == 0) {
                res = res > tv ? tv : res;
            }
            for (int[] di : dir) {
                if (tx + di[0] < 0 || tx + di[0] > x) {
                    continue;
                }
                if (ty + di[1] < 0 || ty + di[1] > y) {
                    continue;
                }
                if (tv + grid[tx + di[0]][ty + di[1]] >= status[tx + di[0]][ty + di[1]]) {
                    continue;
                }
                status[tx + di[0]][ty + di[1]] = tv + grid[tx + di[0]][ty + di[1]];
                int[] np = new int[3];
                np[0] = tx + di[0];
                np[1] = ty + di[1];
                np[2] = tv + grid[tx + di[0]][ty + di[1]];
                queue.add(np);

                //添加路径
                String nstr = "x=" + (tx + di[0]) + ",y=" + (ty + di[1]);
                String ostr = "x=" + tx + ",y=" + ty;
                List<int[]> oldr = road.get(ostr);
                List<int[]> newr = new LinkedList<>();
                for (int[] i : oldr) {
                    newr.add(i);
                }
                newr.add(np);

                road.put(nstr, newr);


            }
        }
        str = "x=" + 0 + ",y=" + 1;
        String str2 = "x=" + 1 + ",y=" + 0;
        List<int[]> resr1 = road.get(str);
        List<int[]> resr2 = road.get(str2);
        return res;
    }

    //64 -- 只能向下或者向右（向上或者向左）
    //执行用时 : 10 ms, 在Minimum Path Sum的Java提交中击败了41.68% 的用户
    //内存消耗 : 43.4 MB, 在Minimum Path Sum的Java提交中击败了0.87% 的用户
    public int minPathSum1(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        //dp[i][j]表示到(i,j)的最小和
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i != 0 || j != 0) {
                    if (i == 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                    }
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    //581
    //执行用时 : 25 ms, 在Shortest Unsorted Continuous Subarray的Java提交中击败了73.47% 的用户
    //内存消耗 : 48.5 MB, 在Shortest Unsorted Continuous Subarray的Java提交中击败了9.26% 的用户
    public int findUnsortedSubarray(int[] nums) {
        int min = nums[0];
        int max = nums[nums.length - 1];
        boolean mi = false;
        boolean ma = false;
        int res = nums.length;
        int bb = nums.length - 1;
        int ee = 0;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                mi = true;
                min = nums[i];
                int b = 0;
                for (int j : nums) {
                    if (j <= min) {
                        b++;
                    } else {
                        break;
                    }
                }
                bb = b > bb ? bb : b;
            } else if (!mi) {
                min = nums[i];
            }
            if (max < nums[nums.length - i - 1]) {
                ma = true;
                max = nums[nums.length - i - 1];
                int e = nums.length - 1;
                for (int j = nums.length - 1; j >= 0; j--) {
                    if (nums[j] >= max) {
                        e--;
                    } else {
                        break;
                    }
                }
                ee = e < ee ? ee : e;
            } else if (!ma) {
                max = nums[nums.length - i - 1];
            }
        }
        return ee - bb > 0 ? ee - bb + 1 : 0;
    }

    //951
    //执行用时 : 5 ms, 在Flip Equivalent Binary Trees的Java提交中击败了58.54% 的用户
    //内存消耗 : 36.8 MB, 在Flip Equivalent Binary Trees的Java提交中击败了0.00% 的用户
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else {
            TreeNode t1l = root1.left;
            TreeNode t1r = root1.right;
            TreeNode t2l = root2.left;
            TreeNode t2r = root2.right;
            if (t1l != null && t1r != null) {
                if (t2l == null || t2r == null) {
                    return false;
                }
                if (t1l.val != t1r.val) {
                    if (t1l.val == t2l.val && t1r.val == t2r.val) {
                        return flipEquiv(t1l, t2l) && flipEquiv(t1r, t2r);
                    } else if (t1l.val == t2r.val && t1r.val == t2l.val) {
                        return flipEquiv(t1l, t2r) && flipEquiv(t1r, t2l);
                    } else {
                        return false;
                    }
                } else {
                    boolean b1 = flipEquiv(t1l, t2l) && flipEquiv(t1r, t2r);
                    boolean b2 = flipEquiv(t1l, t2r) && flipEquiv(t1r, t2l);
                    return b1 || b2;
                }
            } else if (t1l == null && t1r == null) {
                if (t2l != null || t2r != null) {
                    return false;
                } else {
                    return true;
                }
            } else if (t1l != null) {
                if (t2l == null && t2r == null) {
                    return false;
                } else if (t2l != null && t2r != null) {
                    return false;
                } else if (t2l != null) {
                    return flipEquiv(t1l, t2l);
                } else {
                    return flipEquiv(t1l, t2r);
                }
            } else {
                if (t2l == null && t2r == null) {
                    return false;
                } else if (t2l != null && t2r != null) {
                    return false;
                } else if (t2l != null) {
                    return flipEquiv(t1r, t2l);
                } else {
                    return flipEquiv(t1r, t2r);
                }
            }
        }
    }

    //951
    //执行用时 : 6 ms, 在Flip Equivalent Binary Trees的Java提交中击败了57.72% 的用户
    //内存消耗 : 38.4 MB, 在Flip Equivalent Binary Trees的Java提交中击败了0.00% 的用户
    public boolean flipEquiv0(TreeNode root1, TreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return (flipEquiv0(root1.left, root2.left)
                && flipEquiv0(root1.right, root2.right))
                || (flipEquiv0(root1.left, root2.right)
                && flipEquiv0(root1.right, root2.left));
    }

    //268 -- 快速排序
    //执行用时 : 1029 ms, 在Missing Number的Java提交中击败了0.99% 的用户
    //内存消耗 : 48.1 MB, 在Missing Number的Java提交中击败了1.00% 的用户
    public int missingNumber0(int[] nums) {
        quickSort0(nums, 0, nums.length - 1);
        int b = -1;
        for (int i : nums) {
            if (i - b != 1) {
                return i - 1;
            }
            b = i;
        }
        return b + 1;
    }

    public void quickSort0(int[] nums, int b, int e) {
        if (b >= e) {
            return;
        }
        int bb = b;
        int ee = e;
        int t = nums[bb];
        while (bb < ee) {
            while (nums[ee] >= t && bb < ee) {
                ee--;
            }
            nums[ee] = t ^ nums[ee];
            t = t ^ nums[ee];
            nums[ee] = t ^ nums[ee];
            while (nums[bb] <= t && bb < ee) {
                bb++;
            }
        }
        quickSort0(nums, b, bb - 1);
        quickSort0(nums, ee + 1, e);
    }

    //268
    //执行用时 : 3 ms, 在Missing Number的Java提交中击败了32.50% 的用户
    //内存消耗 : 51.5 MB, 在Missing Number的Java提交中击败了1.00% 的用户
    public int missingNumber1(int[] nums) {
        int[] map = new int[nums.length + 1];
        for (int i : nums) {
            map[i] = i;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] != i) {
                return i;
            }
        }
        return 0;
    }

    //268
    //执行用时 : 1 ms, 在Missing Number的Java提交中击败了99.53% 的用户
    //内存消耗 : 48.5 MB, 在Missing Number的Java提交中击败了1.00% 的用户
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ i ^ nums[i];
        }
        return res;
    }

    //3
    //执行用时 : 33 ms, 在Longest Substring Without Repeating Characters的Java提交中击败了92.27% 的用户
    //内存消耗 : 37.3 MB, 在Longest Substring Without Repeating Characters的Java提交中击败了24.05% 的用户
    public int lengthOfLongestSubstring0(String s) {
        char[] chars = s.toCharArray();
        int maxl = 0;
        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        int least = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], i);
                l++;
            } else {
                maxl = Math.max(l, maxl);
                least = map.get(chars[i]) > least ? map.get(chars[i]) : least;
                l = i - least;
                map.put(chars[i], i);
            }
        }
        maxl = Math.max(l, maxl);
        return maxl;
    }

    //1000 -- failed
    public int mergeStones0(int[] stones, int K) {
        if (stones.length == 1) {
            return 0;
        }
        List<Integer> list = new LinkedList<>();
        for (int i : stones) {
            list.add(i);
        }
        int s = 0;
        try {
            s = mergeStonesAss(list, K, 0);
        } catch (Exception e) {
            return -1;
        }
        return s;
    }

    public int mergeStonesAss(List<Integer> list, int K, int s) throws Exception {
        if (K == list.size()) {
            int sum = s;
            for (int j = 0; j < K; j++) {
                sum += list.get(j);
            }
            return sum;
        }
        int i = 0;
        int sum = 0;
        int j = 0;
        for (; j < K; j++) {
            sum += list.get(j);
        }
        int min = sum;
        for (; j < list.size(); j++) {
            sum = sum - list.get(j - K) + list.get(j);
            if (min > sum) {
                min = sum;
                i = j - K + 1;
            } else if (min == sum) {
                int a = 0;
                int b = 0;
                for (int jj = j - K; jj < K; jj++) {
                    a += list.get(jj);
                }
                for (int jj = j; jj < K; jj++) {
                    b += list.get(jj);
                }
                i = a > b ? i : j - K + 1;
            }
        }
        for (int jj = i + K - 1; jj >= i; jj--) {
            list.remove(jj);
        }
        list.add(i, min);
        s = s + min;
        s = mergeStonesAss(list, K, s);
        return s;
    }

    //1000
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        //本来就是一堆的话,没有合并成本
        if (len < 2) {
            return 0;
        }
        //无论用什么方式,石头每次减少K-1个,但最终要剩K个石头
        if (len < K || (len - K) % (K - 1) != 0) {
            return -1;
        }
        //sum[index]表示stones[0]加至stones[index]的总和(PS:sum[0]设为0)
        //动态方程:dp[left][right][heap]表示将编号left到right的石头合为heap堆的成本
        int[] sum = new int[32];
        Arrays.fill(sum, 0);
        int[][][] dp = new int[32][32][32];
        for (int[][] i : dp) {
            for (int[] j : i) {
                Arrays.fill(j, 0x3f);
            }
        }
        for (int index = 1; index <= len; index++) {
            sum[index] = sum[index - 1] + stones[index - 1];
            //每个石子自成一堆,没有合并成本
            dp[index][index][1] = 0;
        }

        int span, left, right, mid, heap;
        //问题由小递推至大,因此先两个两个石头考虑,再三个三个石头考虑,通过span控制left与right的间距,从而递增考虑石子的个数
        for (span = 1; span < len; span++) {
            for (left = 1; left <= len - span; left++) {
                right = left + span;
                //决定在哪将要考虑的石子分为两部分
                for (mid = left; mid < right; mid++) {
                    //先考虑分为两堆,三堆,仍然是递增考虑,堆数不可能超过石子数
                    for (heap = 2; heap <= span + 1; heap++) {
                        //将本次考虑的所有石子合并为heap堆的成本,就是左部分是heap-1堆而右部分1堆相加的成本,反复更新dp[left][right][heap]以获取最小值
                        dp[left][right][heap] = Math.min(dp[left][right][heap], dp[left][mid][heap - 1] + dp[mid + 1][right][1]);
                    }
                }
                //将本次考虑的所有石子合并为一堆的代价,就是将K堆石子合并起来的代价,反复更新dp[left][right][1]以获取最小值
                dp[left][right][1] = Math.min(dp[left][right][1], dp[left][right][K] + sum[right] - sum[left - 1]);
            }
        }
        //将编号为1到len的石子合成一堆的成本即最终答案
        return dp[1][len][1];
    }


    //515
    //执行用时 : 8 ms, 在Find Largest Value in Each Tree Row的Java提交中击败了78.01% 的用户
    //内存消耗 : 40.7 MB, 在Find Largest Value in Each Tree Row的Java提交中击败了0.00% 的用户
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        int a = 1, b = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            a--;
            max = t.val > max ? t.val : max;
            if (t.left != null) {
                queue.add(t.left);
                b++;
            }
            if (t.right != null) {
                queue.add(t.right);
                b++;
            }
            if (a == 0) {
                a = b;
                b = 0;
                res.add(max);
                max = Integer.MIN_VALUE;
            }
        }
        return res;
    }

    //324
    //执行用时 : 6 ms, 在Wiggle Sort II的Java提交中击败了71.50% 的用户
    //内存消耗 : 49.6 MB, 在Wiggle Sort II的Java提交中击败了0.00% 的用户
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length / 2;
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        int j = 0;
        int k = nums.length - 1;
        if ((nums.length & 1) == 0) {
            for (int i = n - 1; i >= 0; i--) {
                nums[j++] = nums2[i];
                nums[j++] = nums2[k];
                k--;
            }
        } else {
            for (int i = n; i > 0; i--) {
                nums[j++] = nums2[i];
                nums[j++] = nums2[k];
                k--;
            }
            nums[j] = nums2[0];
        }
    }

    //215
    //执行用时 : 6 ms, 在Kth Largest Element in an Array的Java提交中击败了86.33% 的用户
    //内存消耗 : 38.3 MB, 在Kth Largest Element in an Array的Java提交中击败了0.88% 的用户
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    //973
    //执行用时 : 98 ms, 在K Closest Points to Origin的Java提交中击败了44.88% 的用户
    //内存消耗 : 66.5 MB, 在K Closest Points to Origin的Java提交中击败了100.00% 的用户
    public int[][] kClosest0(int[][] points, int K) {
        Map<Double, List<int[]>> map = new HashMap<>();
        int k = 0;
        List<Double> mm = new LinkedList<>();
        double x = Double.MIN_VALUE;
        for (int[] p : points) {
            double m = Math.pow(p[0], 2) + Math.pow(p[1], 2);
            if (k > K && m > x) {
                continue;
            }
            mm.add(m);
            if (map.containsKey(m)) {
                List l = map.get(m);
                l.add(p);
            } else {
                List<int[]> l = new LinkedList<>();
                l.add(p);
                map.put(m, l);
            }
            k++;
            if (k <= K) {
                x = x > m ? x : m;
            }
        }
        double[] mmm = new double[mm.size()];
        int ij = 0;
        for (double d : mm) {
            mmm[ij++] = d;
        }
        Arrays.sort(mmm);
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            List<int[]> l = map.get(mmm[i]);
            for (int[] li : l) {
                res[i++] = li;
            }
            i--;
        }
        return res;
    }

    //973
    //执行用时 : 24 ms, 在K Closest Points to Origin的Java提交中击败了91.86% 的用户
    //内存消耗 : 82.8 MB, 在K Closest Points to Origin的Java提交中击败了100.00% 的用户
    private double dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][];
        int k = 0;
        int l = 0, r = points.length - 1;
        while (l <= r) {
            int p = partition(points, l, r);
            if (p <= K - 1) {
                // 从l到p的所有加入结果集
                for (int i = l; i <= p; i++) {
                    res[k++] = points[i];
                }
                l = p + 1;
            } else {
                r = p - 1;
            }
        }
        return res;
    }

    private int partition(int[][] points, int l, int r) {
        // 可以取中间值或随机位置
        // int m = (l + r) / 2;
        int m = l + (int) (Math.random() * (r - l));
        // 把最小的放在第一个位置
        if (dist(points[m]) < dist(points[l])) {
            int[] t = points[m];
            points[m] = points[l];
            points[l] = t;
        }
        // 也可以直接取第一个, 上面可以省略
        int[] key = points[l];
        while (l < r) {
            while (dist(points[r]) >= dist(key) && l < r) {
                r--;
            }
            points[l] = points[r];
            while (dist(points[l]) <= dist(key) && l < r) {
                l++;
            }
            points[r] = points[l];
        }
        points[r] = key;
        return r;
    }

    //118
    //执行用时 : 0 ms, 在Pascal's Triangle的Java提交中击败了100.00% 的用户
    //内存消耗 : 32.6 MB, 在Pascal's Triangle的Java提交中击败了0.93% 的用户
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> r = new LinkedList<>();
            int number = 1;
            //打印空格字符串
//            System.out.format("%"+(rows-i)*2+"s","");
            for (int j = 0; j <= i; j++) {
                r.add(number);
                number = number * (i - j) / (j + 1);
            }
            res.add(r);
        }
        return res;
    }

    //119
    public List<Integer> getRow(int rowIndex) {
        rowIndex = 3;
        List<Integer> r = new LinkedList<>();
        int number = 1;
        for (int j = 0; j <= rowIndex; j++) {
            r.add(number);
            number = number / (j + 1) * (rowIndex - j);
        }
        return r;
    }

    //162
    //执行用时 : 5 ms, 在Find Peak Element的Java提交中击败了62.57% 的用户
    //内存消耗 : 37.8 MB, 在Find Peak Element的Java提交中击败了0.52% 的用户
    public int findPeakElement(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (i == 0) {
                if (nums[i] > nums[i + 1]) {
                    return 0;
                }
            } else if (i == nums.length - 1) {
                if (nums[i] > nums[i - 1]) {
                    return i;
                }
            } else if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    //162
    //执行用时 : 5 ms, 在Find Peak Element的Java提交中击败了62.57% 的用户
    //内存消耗 : 39.7 MB, 在Find Peak Element的Java提交中击败了0.52% 的用户
    public int findPeakElement1(int[] nums) {
        int lo = -1, hi = nums.length;
        int mid;
        while (true) {
            if (hi - lo == 2) {
                return lo + 1;
            }
            mid = (lo + hi) / 2;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid;
            } else {
                hi = mid + 1;
            }
        }
    }

    //852
    public int peakIndexInMountainArray(int[] A) {
        return findPeakElement(A);
    }

    //124 --- 非自己所做的
    //执行用时 : 3 ms, 在Binary Tree Maximum Path Sum的Java提交中击败了58.49% 的用户
    //内存消耗 : 38.3 MB, 在Binary Tree Maximum Path Sum的Java提交中击败了0.00% 的用户
    private int ret = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        /**
         对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
         1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
         2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
         **/
        getMax(root);
        return ret;
    }

    private int getMax(TreeNode r) {
        if (r == null) {
            return 0;
        }
        int left = Math.max(0, getMax(r.left)); // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(r.right));
        ret = Math.max(ret, r.val + left + right); // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        return Math.max(left, right) + r.val;
    }

    //129
    //执行用时 : 2 ms, 在Sum Root to Leaf Numbers的Java提交中击败了31.41% 的用户
    //内存消耗 : 36.6 MB, 在Sum Root to Leaf Numbers的Java提交中击败了0.99% 的用户
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode t = stack.pop();
            if (t.right != null) {
                t.right.val = t.val * 10 + t.right.val;
                stack.push(t.right);
            }
            if (t.left != null) {
                t.left.val = t.val * 10 + t.left.val;
                stack.push(t.left);
            }
            if (t.left == null && t.right == null) {
                res += t.val;
            }
        }
        return res;
    }

    //996
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int numSquarefulPerms(int[] A) {
        int[][] status = new int[A.length][A.length + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int k = (int) Math.sqrt(A[i] + A[j]);
                if (k == Math.sqrt(A[i] + A[j])) {
                    status[i][++status[i][0]] = j;
                    status[j][++status[j][0]] = i;
                }
            }
        }
        int[] b = new int[2];
        int bn = 0;
        try {
            for (int i = 0; i < A.length; i++) {
                if (status[i][0] == 0) {
                    return 0;
                } else if (status[i][0] == 1) {
                    b[bn] = i;
                    bn++;
                }
            }
        } catch (Exception e) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        if (bn >= 1) {
            List<Integer> r = new LinkedList<>();
            boolean[] bs = new boolean[A.length];
            bs[b[0]] = true;
            numSquarefulPermsAss(b[0], r, status, set, bs, A);
        } else {
            for (int i = 0; i < A.length; i++) {
                List<Integer> r = new LinkedList<>();
                boolean[] bs = new boolean[A.length];
                bs[i] = true;
                numSquarefulPermsAss(i, r, status, set, bs, A);
            }
            return set.size();
        }
        if (bn == 2) {
            List<Integer> r = new LinkedList<>();
            boolean[] bs = new boolean[A.length];
            bs[b[1]] = true;
            numSquarefulPermsAss(b[1], r, status, set, bs, A);
        }
        return set.size();
    }

    private void numSquarefulPermsAss(int nr, List<Integer> r, int[][] status, Set set, boolean[] bs, int[] A) {
        if (r.size() >= A.length) {
            String s = "";
            for (int i : r) {
                s += i + ",";
            }
            set.add(s);
            return;
        } else if (r.size() == A.length - 1) {
            r.add(A[nr]);
            numSquarefulPermsAss(nr, r, status, set, bs, A);
            r.remove(r.size() - 1);
            return;
        } else {
            int[] i = status[nr];
            if (i[0] < 1) {
                return;
            }
            r.add(A[nr]);
            for (int j = 1; j <= i[0]; j++) {
                if (bs[i[j]]) {
                    continue;
                }
                bs[i[j]] = true;
                numSquarefulPermsAss(i[j], r, status, set, bs, A);
                bs[i[j]] = false;
            }
            r.remove(r.size() - 1);
            return;
        }
    }

    //996
    //执行用时 : 16 ms, 在Number of Squareful Arrays的Java提交中击败了100.00% 的用户
    //内存消耗 : 36.5 MB, 在Number of Squareful Arrays的Java提交中击败了100.00% 的用户
    public int numSquarefulPerms0(int[] A) {
        Map<Integer, HashSet<Integer>> points = new HashMap<>();
        Map<Integer, Integer> weights = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            if (weights.keySet().contains(A[i])) {
                weights.put(A[i], weights.get(A[i]) + 1);
            } else {
                weights.put(A[i], 1);
                for (int j = i + 1; j < A.length; j++) {
                    if (!points.keySet().contains(A[i])) {
                        points.put(A[i], new HashSet<>());
                    }
                    if (!points.keySet().contains(A[j])) {
                        points.put(A[j], new HashSet<>());
                    }
                    if (isSqrt(A[i] + A[j])) {
                        points.get(A[j]).add(A[i]);
                        if (A[i] != A[j]) {
                            points.get(A[i]).add(A[j]);
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int i : weights.keySet()) {
            Map<Integer, HashSet<Integer>> ps = new HashMap<>(points);
            Map<Integer, Integer> ws = new HashMap<>(weights);
            res = res + calculateSquare(ps, ws, i);
        }

        return res;

    }

    public int calculateSquare(Map<Integer, HashSet<Integer>> points,
                               Map<Integer, Integer> weights,
                               int p) {
        int res = 0;
        if (!weights.keySet().contains(p)) {
            return 0;
        }
        if (weights.size() == 1 && weights.get(p) == 1) {
            return 1;
        }
        if (weights.get(p) == 1) {
            weights.remove(p);
        } else {
            weights.put(p, weights.get(p) - 1);
        }
        for (int i : points.get(p)) {
            Map<Integer, HashSet<Integer>> ps = new HashMap<>(points);
            Map<Integer, Integer> ws = new HashMap<>(weights);
            res = res + calculateSquare(ps, ws, i);
        }
        return res;
    }

    public boolean isSqrt(int x) {
        double d = Math.sqrt(x);
        return d - (int) d == 0;
    }

    //503
    //执行用时 : 74 ms, 在Next Greater Element II的Java提交中击败了56.19% 的用户
    //内存消耗 : 53.7 MB, 在Next Greater Element II的Java提交中击败了5.00% 的用户
    public int[] nextGreaterElements(int[] nums) {
        int[] p = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int jj = i;
            p[i] = -1;
            for (int j = 0; j < nums.length; j++) {
                jj++;
                if (jj >= nums.length) {
                    jj = 0;
                }
                if (nums[jj] > nums[i]) {
                    p[i] = nums[jj];
                    break;
                }
            }
        }

        return p;
    }

    //198
    //执行用时 : 1 ms, 在House Robber的Java提交中击败了99.62% 的用户
    //内存消耗 : 35.9 MB, 在House Robber的Java提交中击败了0.83% 的用户
    public int rob0(int[] nums) {
        int n = nums.length;
        if (n <= 0) {
            return 0;
        }
        int[][] status = new int[n][2];
        status[0][0] = 0;
        status[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            status[i][0] = Math.max(status[i - 1][0], status[i - 1][1]);
            status[i][1] = Math.max(status[i - 1][0] + nums[i], status[i - 1][1] - nums[i - 1] + nums[i]);
        }
        return Math.max(status[n - 1][0], status[n - 1][1]);
    }

    //213
    //执行用时 : 1 ms, 在House Robber II的Java提交中击败了99.28% 的用户
    //内存消耗 : 33.7 MB, 在House Robber II的Java提交中击败了0.00% 的用户
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];
        dp1[1] = nums[0]; //从第1个房屋开始偷
        dp2[1] = nums[1]; //从第2个房屋开始偷
        for (int i = 2; i < nums.length; i++) {
            dp1[i] = Math.max(dp1[i - 2] + nums[i - 1], dp1[i - 1]);
            dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
        }
        return Math.max(dp1[nums.length - 1], dp2[nums.length - 1]);
    }

    //931
    //执行用时 : 6 ms, 在Minimum Falling Path Sum的Java提交中击败了96.55% 的用户
    //内存消耗 : 36.5 MB, 在Minimum Falling Path Sum的Java提交中击败了0.00% 的用户
    public int minFallingPathSum(int[][] A) {
        int min = Integer.MAX_VALUE;
        int l = A.length;
        int r = A[0].length;
        int[][] road = new int[l][r + 2];
        for (int i = 0; i < r; i++) {
            road[0][i + 1] = A[0][i];
        }
        for (int i = 0; i < l; i++) {
            road[i][0] = Integer.MAX_VALUE / 2;
            road[i][r + 1] = Integer.MAX_VALUE / 2;
        }
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < r + 1; j++) {
                int t = A[i][j - 1];
                road[i][j] = Math.min(Math.min(road[i - 1][j - 1], road[i - 1][j]), road[i - 1][j + 1]);
                road[i][j] += t;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 1; j < r + 1; j++) {
            res = res < road[l - 1][j] ? res : road[l - 1][j];
        }
        return res;
    }

    //746
    //执行用时 : 4 ms, 在Min Cost Climbing Stairs的Java提交中击败了87.38% 的用户
    //内存消耗 : 35.2 MB, 在Min Cost Climbing Stairs的Java提交中击败了3.51% 的用户
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[][] road = new int[n][2];
        road[0][1] = cost[0];
        road[0][0] = 0;
        road[1][1] = cost[1];
        road[1][0] = 0;
        for (int i = 2; i < n; i++) {
            road[i][0] = road[i - 1][1];
            road[i][1] = Math.min(road[i - 2][1] + cost[i], road[i - 1][1] + cost[i]);
        }
        int res = Math.min(road[n - 1][0], road[n - 1][1]);
        return res;
    }

    //309
    //执行用时 : 2 ms, 在Best Time to Buy and Sell Stock with Cooldown的Java提交中击败了99.69% 的用户
    //内存消耗 : 34.1 MB, 在Best Time to Buy and Sell Stock with Cooldown的Java提交中击败了0.00% 的用户
    public int maxProfit(int[] prices) {
        /*三个状态的对应利润
        1.s0可买入
        2.s1可卖出
        3.s2冷冻
        maxlr=max(s0,s2);
        */
        if (prices.length == 0) {
            return 0;
        }
        int s0 = 0;
        int s1 = -prices[0];
        int s2 = 0;
        int pre0 = s0;
        int pre1 = s1;
        int pre2 = s2;
        for (int i = 1; i < prices.length; i++) {
            //不用开辟数组了，直接用变量记录上一个值
            pre0 = s0;
            pre1 = s1;
            pre2 = s2;
            //☆重点！！！状态转移方程
            s0 = Math.max(pre0, pre2);//当前可买入状态利润=max(保持原状态，从冷冻状态转换过来)
            s1 = Math.max(pre1, pre0 - prices[i]);//当前可卖出状态利润=max(保持原状态，买入物品后)
            s2 = pre1 + prices[i];//当前冷冻状态利润=可卖出状态+卖出股票
        }
        return Math.max(s0, s2);
    }

    //309
    //执行用时 : 3 ms, 在Best Time to Buy and Sell Stock with Cooldown的Java提交中击败了95.37% 的用户
    //内存消耗 : 36.9 MB, 在Best Time to Buy and Sell Stock with Cooldown的Java提交中击败了0.00% 的用户
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        //0：不持有 1：持有 2：冷冻
        int[][] best = new int[n][3];
        best[0][0] = 0;
        best[0][1] = -prices[0];
        best[0][2] = 0;
        for (int i = 1; i < n; i++) {
            best[i][0] = Math.max(best[i - 1][0], best[i - 1][2]);
            best[i][1] = Math.max(best[i - 1][1], best[i - 1][0] - prices[i]);
            best[i][2] = best[i - 1][1] + prices[i];
        }
        return Math.max(best[n - 1][0], best[n - 1][2]);
    }

    //62
    //执行用时 : 1 ms, 在Unique Paths的Java提交中击败了83.10% 的用户
    //内存消耗 : 32.1 MB, 在Unique Paths的Java提交中击败了61.91% 的用户
    public int uniquePaths(int m, int n) {
        int[][] road = new int[m][n];
        road[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                road[i][j] += i > 0 ? road[i - 1][j] : 0;
                road[i][j] += j > 0 ? road[i][j - 1] : 0;
            }
        }
        return road[m - 1][n - 1];
    }

    //741 -- failed  没考虑：
    public int cherryPickup0(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] droad = new int[m][n];
        droad[0][0] = grid[0][0];
        Map<String, List<int[]>> map = new HashMap<>();
        String sbegin = "x=0,y=0";
        int[] p = new int[2];
        p[0] = 0;
        p[1] = 0;
        List road = new LinkedList();
        road.add(p);
        map.put(sbegin, road);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (grid[i][j] == -1) {
                    droad[i][j] = Integer.MIN_VALUE;
                    String sr = "x=" + i + ",y=" + j;
                    List<int[]> np = new LinkedList<>();
                    map.put(sr, np);
                    continue;
                }
                int t1 = 0, t2 = 0;
                if (i - 1 >= 0) {
                    t1 = grid[i - 1][j] == -1 ? -1 : grid[i][j] + droad[i - 1][j];
                }
                if (j - 1 >= 0) {
                    t2 = grid[i][j - 1] == -1 ? -1 : grid[i][j] + droad[i][j - 1];
                }
                droad[i][j] = Math.max(t1, t2);
                String sr = "x=" + i + ",y=" + j;
                List<int[]> np = new LinkedList<>();
//                if (t1 == -1 && t2 == -1) {
//                    map.put(sr, np);
//                    continue;
//                }
                String spre = t1 > t2 ? "x=" + (i - 1) + ",y=" + j : "x=" + i + ",y=" + (j - 1);
                List<int[]> tp = map.get(spre);

                if (tp != null) {
                    for (int[] ts : tp) {
                        np.add(ts);
                    }
                }
                int[] ts = new int[2];
                ts[0] = i;
                ts[1] = j;
                np.add(ts);
                map.put(sr, np);
            }
        }
        String ds = "x=" + (m - 1) + ",y=" + (n - 1);
        List<int[]> list = map.get(ds);
        for (int[] t : list) {
            int x = t[0];
            int y = t[1];
            if (grid[x][y] == -1) {
                return -1;
            }
            grid[x][y] = 0;
        }
        int res = droad[m - 1][n - 1];
        int[][] uroad = new int[m][n];
        uroad[m - 1][n - 1] = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int t1 = 0, t2 = 0;
                if (i + 1 < m) {
                    t1 = grid[i + 1][j] == -1 ? -2 : grid[i][j] + uroad[i + 1][j];
                }
                if (j + 1 < n) {
                    t2 = grid[i][j + 1] == -1 ? -2 : grid[i][j] + uroad[i][j + 1];
                }
                uroad[i][j] = Math.max(t1, t2);
            }
        }
        res += uroad[0][0];
        return res > 0 ? res : 0;
    }

    //741
    //执行用时 : 51 ms, 在Cherry Pickup的Java提交中击败了66.67% 的用户
    //内存消耗 : 41 MB, 在Cherry Pickup的Java提交中击败了85.71% 的用户
    public int cherryPickup(int[][] grid) {
        int n = grid.length, mx = 2 * n - 1;
        int[][] dp = new int[n][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int k = 1; k < mx; ++k) {
            for (int i = n - 1; i >= 0; --i) {
                for (int p = n - 1; p >= 0; --p) {
                    int j = k - i, q = k - p;
                    if (j < 0 || j >= n || q < 0 || q >= n || grid[i][j] < 0 || grid[p][q] < 0) {
                        dp[i][p] = -1;
                        continue;
                    }
                    if (i > 0) {
                        dp[i][p] = Math.max(dp[i][p], dp[i - 1][p]);
                    }
                    if (p > 0) {
                        dp[i][p] = Math.max(dp[i][p], dp[i][p - 1]);
                    }
                    if (i > 0 && p > 0) {
                        dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1]);
                    }
                    if (dp[i][p] >= 0) {
                        dp[i][p] += grid[i][j] + (i != p ? grid[p][q] : 0);
                    }
                }
            }
        }
        return Math.max(dp[n - 1][n - 1], 0);
    }

    //403
    public boolean canCross(int[] stones) {
        if (stones.length == 0) {
            return false;
        }
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            m.put(stones[i], new HashSet<>());
        }
        m.get(0).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (Integer now : m.get(stones[i])) {
                for (int j = now - 1; j <= now + 1; j++) {
                    if (j > 0 && m.containsKey(stones[i] + j)) {
                        m.get(stones[i] + j).add(j);
                    }
                }
            }
        }
        return !m.get(stones[stones.length - 1]).isEmpty();
    }

    //871
    //执行用时 : 37 ms, 在Minimum Number of Refueling Stops的Java提交中击败了26.56% 的用户
    //内存消耗 : 44.5 MB, 在Minimum Number of Refueling Stops的Java提交中击败了61.02% 的用户
    public int minRefuelStops0(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }
        int res = 0;
        int n = stations.length;
        List<Integer> pass = new LinkedList<>();
        int arrive = startFuel;
        for (int i = 0; i < n; i++) {
            if (arrive >= target) {
                return res;
            }
            if (stations[i][0] <= arrive) {
                pass.add(stations[i][1]);
            } else {
                int max = 0;
                int maxj = -1;
                for (int j = 0; j < pass.size(); j++) {
                    if (max <= pass.get(j)) {
                        maxj = j;
                        max = pass.get(j);
                    }
                }
                if (maxj == -1) {
                    return -1;
                }
                arrive += max;
                pass.remove(maxj);
                res++;
                if (stations[i][0] <= arrive) {
                    pass.add(stations[i][1]);
                } else {
                    i--;
                }
            }
        }
        if (arrive >= target) {
            return res;
        }
        while (!pass.isEmpty()) {
            int max = 0;
            int maxj = -1;
            for (int j = 0; j < pass.size(); j++) {
                if (max <= pass.get(j)) {
                    maxj = j;
                    max = pass.get(j);
                }
            }
            res++;
            arrive += max;
            pass.remove(maxj);
            if (arrive >= target) {
                return res;
            }
        }
        return -1;
    }

    //871
    //执行用时 : 11 ms, 在Minimum Number of Refueling Stops的Java提交中击败了74.22% 的用户
    //内存消耗 : 44.2 MB, 在Minimum Number of Refueling Stops的Java提交中击败了67.80% 的用户
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer e1, Integer e2) {
                return e2 - e1;
            }
        });
        int currentFuel = startFuel;
        int times = 0;
        int currentPosition = 0;
        int stationsnums = stations.length;
        while (currentFuel < target) {
            while (currentPosition < stationsnums && stations[currentPosition][0] <= currentFuel) {
                priorityQueue.add(stations[currentPosition++][1]);

            }
            if (priorityQueue.isEmpty()) {
                return -1;
            }
            currentFuel += priorityQueue.poll();
            times++;
        }
        return times;
    }

    //787
    //执行用时 : 12 ms, 在Cheapest Flights Within K Stops的Java提交中击败了84.62% 的用户
    //内存消耗 : 38.4 MB, 在Cheapest Flights Within K Stops的Java提交中击败了96.88% 的用户
    public int findCheapestPrice0(int n, int[][] flights, int src, int dst, int K) {
        /**
         基于最短路径Dijkstra算法, 加上了k中转的约束, 利用一个优先队列按费用最低保存到达不同目标站
         所需的中间站数和费用, 每次从优先队列中poll出一个当前最小花费的站, 如果该站为dst则直接返回
         对应的费用(优先队列保证花费最低). 为了便于更新, 利用一个map保存不同的始发站可以到达的不同
         下一站的和费用

         Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
         for(int[] flight : flights) {
         if(!map.containsKey(flight[0]))
         map.put(flight[0], new HashMap<>(0));
         map.get(flight[0]).put(flight[1], flight[2]);
         }

         // 数组内元素依次为: 到达站的最低费用(fee)、到达站(tar)和经过中转的站数(k)
         PriorityQueue<int[]> pq = new PriorityQueue<>(
         (a, b) -> (a[0]-b[0])
         );
         pq.offer(new int[]{0, src, 0});

         while(!pq.isEmpty()) {
         int[] temp = pq.poll();
         int fee = temp[0];
         int tar = temp[1];
         int k = temp[2];
         if(tar == dst) return fee;
         if(k <= K) {
         // 防止下一站城市不在map中
         Map<Integer, Integer> nextHops = map.getOrDefault(tar, new HashMap<>());
         for(Map.E***y<Integer, Integer> e***y : nextHops.e***ySet())
         pq.offer(new int[]{fee + e***y.getValue(), e***y.getKey(), k+1});
         }
         }

         return -1;
         **/

        /**
         动态规划解法, dp[i][k]表示经过k个中转站后到达站i的最低费用
         初始除了dp[src][0]~dp[src][k]之外所有的元素置为无穷大inf
         则状态方程为: 对于所有目标地位i的航班(flight[1] = i)
         只要dp[flight[0]][k-1] != inf就更新dp[i][k]
         dp[i][k] = Math.min(dp[i][k], dp[flight[0]][k-1])
         **/

        int[][] dp = new int[n][K + 2];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int k = 0; k <= K + 1; ++k) {
            dp[src][k] = 0;
        }
        for (int k = 1; k <= K + 1; ++k) {
            for (int[] flight : flights) {
                if (dp[flight[0]][k - 1] != Integer.MAX_VALUE) {
                    dp[flight[1]][k] = Math.min(dp[flight[1]][k], dp[flight[0]][k - 1] + flight[2]);
                }
            }
        }
        return dp[dst][K + 1] == Integer.MAX_VALUE ? -1 : dp[dst][K + 1];
    }


    //787
    //执行用时 : 13 ms, 在Cheapest Flights Within K Stops的Java提交中击败了78.20% 的用户
    //内存消耗 : 41.2 MB, 在Cheapest Flights Within K Stops的Java提交中击败了50.00% 的用户
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[n][K + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        Arrays.fill(dp[src], 0);

        for (int[] flight : flights) {
            int s = flight[0];
            int d = flight[1];
            int p = flight[2];
            if (s == src) {
                dp[d][0] = p;
            }
        }

        for (int i = 1; i < K + 1; i++) {
            for (int[] flight : flights) {
                int s = flight[0];
                int d = flight[1];
                int p = flight[2];
                if (dp[s][i - 1] != Integer.MAX_VALUE) {
                    dp[d][i] = Math.min(dp[d][i], dp[s][i - 1] + p);
                }
            }
        }
        return dp[dst][K] == Integer.MAX_VALUE ? -1 : dp[dst][K];
    }

    //55
    //执行用时 : 3 ms, 在Jump Game的Java提交中击败了95.28% 的用户
    //内存消耗 : 40.5 MB, 在Jump Game的Java提交中击败了84.53% 的用户
    //进行下一个挑战：
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int t = 1;
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != 0) {
                t = (t - 1) > nums[i] ? (t - 1) : nums[i];
            } else {
                t--;
            }
            if (t == 0) {
                break;
            }
        }
        return i >= nums.length - 1;
    }

    //45
    // 超出时间限制
    public int jump0(int[] nums) {
        int n = nums.length;
        int[] teps = new int[n];
        for (int i = 0; i < n; i++) {
            teps[i] = Integer.MAX_VALUE;
        }
        teps[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = nums[i]; j >= 1; j--) {
                if (i + j >= n) {
                    continue;
                }
                if (teps[i] > teps[i + j]) {
                    break;
                }
                teps[i + j] = Math.min(teps[i] + 1, teps[i + j]);
            }
        }
        return teps[n - 1];
    }

    //45
    //执行用时 : 4 ms, 在Jump Game II的Java提交中击败了97.05% 的用户
    //内存消耗 : 43.1 MB, 在Jump Game II的Java提交中击败了49.14% 的用户
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int reach = 0;
        int nextreach = nums[0];
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            nextreach = Math.max(i + nums[i], nextreach);
            if (nextreach >= nums.length - 1) {
                return (step + 1);
            }
            if (i == reach) {
                step++;
                reach = nextreach;
            }
        }
        return step;
    }

    //40
    //执行用时 : 32 ms, 在Combination Sum II的Java提交中击败了33.07% 的用户
    //内存消耗 : 45 MB, 在Combination Sum II的Java提交中击败了27.34% 的用户
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();

        quickSort(candidates, 0, candidates.length - 1);
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            }
            List<Integer> re = new LinkedList<>();
            re.add(candidates[i]);
            if (candidates[i] == target) {
                PriorityQueue<Integer> r = new PriorityQueue();
                for (int k : re) {
                    r.add(k);
                }
                String key = "";
                for (int ii : r) {
                    key += ii + ",";
                }
                map.put(key, r);
                re.remove(re.size() - 1);
                continue;
            }
            combinationSum2Ass(map, candidates, target - candidates[i], i, re);
            re.remove(0);
        }
        for (PriorityQueue<Integer> p : map.values()) {
            List<Integer> l = new LinkedList<>();
            for (int i : p) {
                l.add(i);
            }
            res.add(l);
        }
        return res;
    }

    public void combinationSum2Ass(Map<String, PriorityQueue<Integer>> map, int[] candidates,
                                   int target, int i, List<Integer> re) {
        for (int j = i + 1; j < candidates.length; j++) {
            if (candidates[j] > target) {
                continue;
            }
            re.add(candidates[j]);
            if (candidates[j] == target) {
                PriorityQueue<Integer> r = new PriorityQueue();
                for (int k : re) {
                    r.add(k);
                }
                String key = "";
                for (int ii : r) {
                    key += ii + ",";
                }
                map.put(key, r);
                re.remove(re.size() - 1);
                continue;
            }
            combinationSum2Ass(map, candidates, target - candidates[j], j, re);
            re.remove(re.size() - 1);
        }
        return;
    }

    //40
    //     递归回溯，同时要去重。
    //为啥能通过比较相同然后移动扫描下标就能去重？
    //假设[l, r]区间中要找一个和为target。通过函数backTracking(v, l, r， target)就能求出来解。
    //而如果[l+1, r]区间有好几个值跟v[l]相等，但是此区间==v[l]元素的个数一定比v[l, r]区间==v[l]元素的个数少；
    //所以对于"含有v[l]的解"中，前者的答案一定包含后者，所以我们只需要求一次就行；
    //后面相同的元素直接跳过去。
    //
    //图示：假设这个相同的数为3
    //
    //3 [3......3333.....3(第k个3)]4677899....p
    //l l+1                                   r
    //
    //既然区间[l+1, r]能够求出和为target的组合，其中包含了[l+1, r]区间所有含3的解的情况。
    //而区间[l, r]3的个数比[l+1, r]3的个数更多，那么毫无疑问，[l, r]的解将覆盖[l+1, r]解中含有3的情况。

    /*
    class LeetCode.Solution {
        private:
        vector<vector<int>> ans;
        vector<int> curComb;
        public:

        vector<vector<int>> combinationSum2(vector<int>&candidates, int target) {
            vector<int> c = candidates;//拷贝vector，保证算法对输入数据不产生修改。
            sort(c.begin(), c.end());//排序，方便后续操作
            int start = 0, end = 0;//排序后，排除尾部所有>target的数
            for (; end < c.size(); end++) {
                if (c[end] > target) {
                    break;
                }
            }
            backTracking(c, start, end == c.size() ? end - 1 : end, target);
            return ans;
        }

        private:

        void backTracking(vector<int>&c, int l, int r, int target) {

            if (target < 0) {
                return;//target<0
            }
            if (!target) {//target==0
                ans.push_back(curComb);
                return;
            }
            //target > 0
            for (int i = l; i <= r && c[i] <= target; i++) {
                if (i != l && c[i] == c[i - 1]) {
                    continue;//remove duplicate solution.
                }
                curComb.push_back(c[i]);
                backTracking(c, i + 1, r, target - c[i]);
                curComb.pop_back();
            }
        }
    }
    */

    //141 -- set
    //执行用时 : 13 ms, 在Linked List Cycle的Java提交中击败了19.19% 的用户
    //内存消耗 : 43.9 MB, 在Linked List Cycle的Java提交中击败了5.04% 的用户
    public boolean hasCycle0(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (set.contains(p)) {
                return true;
            }
            set.add(p);
            p = p.next;
        }
        return false;
    }

    //141 -- 快慢指针
    //执行用时 : 1 ms, 在Linked List Cycle的Java提交中击败了90.53% 的用户
    //内存消耗 : 40.7 MB, 在Linked List Cycle的Java提交中击败了34.58% 的用户
    public boolean hasCycle(ListNode head) {
        ListNode p = head;
        ListNode q = head;
        while (p != null && p.next != null) {
            p = p.next.next;
            q = q.next;
            if (p.equals(q)) {
                return true;
            }
        }
        return false;
    }

    //23 -- 分治
    // 执行用时 : 6 ms, 在Merge k Sorted Lists的Java提交中击败了98.10% 的用户
    // 内存消耗 : 42.2 MB, 在Merge k Sorted Lists的Java提交中击败了75.71% 的用户
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }

        int mid = lists.length / 2;
        ListNode[] l1 = new ListNode[mid];
        for (int i = 0; i < mid; i++) {
            l1[i] = lists[i];
        }

        ListNode[] l2 = new ListNode[lists.length - mid];
        for (int i = mid, j = 0; i < lists.length; i++, j++) {
            l2[j] = lists[i];
        }

        return mergeTwoLists(mergeKLists(l1), mergeKLists(l2));

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    //53 -- 动态规划
    //执行用时 : 2 ms, 在Maximum Subarray的Java提交中击败了99.59% 的用户
    //内存消耗 : 42.2 MB, 在Maximum Subarray的Java提交中击败了56.96% 的用户
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    //4
    //执行用时 : 15 ms, 在Median of Two Sorted Arrays的Java提交中击败了90.45% 的用户
    //内存消耗 : 49.4 MB, 在Median of Two Sorted Arrays的Java提交中击败了83.49% 的用户
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] arrays = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            arrays[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            arrays[i + nums1.length] = nums2[i];
        }
        int len = arrays.length;
        double d = 0.0;
        quickSort(arrays, 0, len - 1);
        if (len % 2 == 0) {
            d = d + arrays[len / 2] + arrays[len / 2 - 1];
            d = d / 2;
        } else {
            d = d + arrays[len / 2];
        }
        return d;
    }

    //4 -- 归并
    //执行用时 : 14 ms, 在Median of Two Sorted Arrays的Java提交中击败了92.15% 的用户
    //内存消耗 : 51 MB, 在Median of Two Sorted Arrays的Java提交中击败了76.36% 的用户
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public double findMedianSortedArrays0(int[] nums1, int[] nums2) {
        int[] array = merge(nums1, nums2);
        int length = array.length;
        if (length % 2 != 0) {
            return (double) array[length / 2];
        } else {
            return ((double) (array[length / 2] + array[length / 2 - 1])) / 2;
        }
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] array = new int[len1 + len2];
        int pointer1 = 0;
        int pointer2 = 0;
        int pointer = 0;
        while (pointer1 < len1 && pointer2 < len2) {
            if (nums1[pointer1] < nums2[pointer2]) {
                array[pointer++] = nums1[pointer1++];
            } else {
                array[pointer++] = nums2[pointer2++];
            }
        }
        while (pointer1 < len1) {
            array[pointer++] = nums1[pointer1++];
        }
        while (pointer2 < len2) {
            array[pointer++] = nums2[pointer2++];
        }
        return array;
    }

    //4 -- 递归（官方）
    //执行用时 : 13 ms, 在Median of Two Sorted Arrays的Java提交中击败了94.89% 的用户
    //内存消耗 : 53.8 MB, 在Median of Two Sorted Arrays的Java提交中击败了65.21% 的用户
    public double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    //120
    //执行用时 : 5 ms, 在Triangle的Java提交中击败了91.35% 的用户
    //内存消耗 : 39.3 MB, 在Triangle的Java提交中击败了25.04% 的用户
    public int minimumTotal0(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] status = new int[len][len];
        status[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    status[i][j] = triangle.get(i).get(0) + status[i - 1][0];
                    continue;
                }
                if (j == i) {
                    status[i][j] = triangle.get(i).get(i) + status[i - 1][i - 1];
                    continue;
                }
                status[i][j] = Math.min(status[i - 1][j - 1], status[i - 1][j]);
                status[i][j] += triangle.get(i).get(j);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, status[len - 1][i]);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    //120 -- n空间复杂度
    // 执行用时 : 4 ms, 在Triangle的Java提交中击败了98.66% 的用户
    // 内存消耗 : 39.7 MB, 在Triangle的Java提交中击败了20.58% 的用户
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // 只需要记录每一层的最小值即可
        int[] dp = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> curTr = triangle.get(i);
            for (int j = 0; j < curTr.size(); j++) {
                //这里的dp[j] 使用的时候默认是上一层的，赋值之后变成当前层
                dp[j] = Math.min(dp[j], dp[j + 1]) + curTr.get(j);
            }
        }
        return dp[0];
    }

    //63
    //执行用时 : 2 ms, 在Unique Paths II的Java提交中击败了19.13% 的用户
    //内存消耗 : 34.8 MB, 在Unique Paths II的Java提交中击败了63.15% 的用户
    public int uniquePathsWithObstacles0(int[][] obstacleGrid) {
        int x = obstacleGrid.length;
        int y = obstacleGrid[0].length;
        int[][] roads = new int[x][y];
        if (obstacleGrid[0][0] == 1 || obstacleGrid[x - 1][y - 1] == 1) {
            return 0;
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 0 && j == 0) {
                    roads[0][0] = 1;
                    continue;
                }
                if (i == 0) {
                    if (obstacleGrid[i][j] == 1) {
                        roads[i][j] = 0;
                        continue;
                    }
                    roads[i][j] = roads[i][j - 1];
                    continue;
                }
                if (j == 0) {
                    if (obstacleGrid[i][j] == 1) {
                        roads[i][j] = 0;
                        continue;
                    }
                    roads[i][j] = roads[i - 1][j];
                    continue;
                }
                if (obstacleGrid[i][j] == 1) {
                    roads[i][j] = 0;
                    continue;
                }
                roads[i][j] = roads[i - 1][j] + roads[i][j - 1];
            }
        }
        return roads[x - 1][y - 1];
    }

    //63
    //执行用时 : 1 ms, 在Unique Paths II的Java提交中击败了96.69% 的用户
    //内存消耗 : 34.4 MB, 在Unique Paths II的Java提交中击败了87.10% 的用户
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        int barrier = 1;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == barrier) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == barrier) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == barrier) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    //152
    //执行用时 : 5 ms, 在Maximum Product Subarray的Java提交中击败了28.73% 的用户
    //内存消耗 : 38.7 MB, 在Maximum Product Subarray的Java提交中击败了7.23% 的用户
    public int maxProduct0(int[] nums) {
        int len = nums.length;
        int[][] results = new int[len][2];
        results[0][0] = nums[0];
        results[0][1] = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] == 0) {
                results[i][0] = 0;
                results[i][1] = 0;
                continue;
            }
            if (nums[i] > 0) {
                results[i][0] = Math.max(results[i - 1][0] * nums[i], nums[i]);
                results[i][1] = results[i - 1][1] * nums[i];
            } else {
                results[i][1] = Math.min(results[i - 1][0] * nums[i], nums[i]);
                results[i][0] = results[i - 1][1] * nums[i];
            }
            max = Math.max(max, results[i][0]);
        }
        return max;
    }

    //152
    //执行用时 : 2 ms, 在Maximum Product Subarray的Java提交中击败了98.37% 的用户
    //内存消耗 : 35.5 MB, 在Maximum Product Subarray的Java提交中击败了78.64% 的用户
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] B = new int[nums.length];
        int j = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            B[j++] = nums[i];
        }
        int maxA = nums[0];
        int maxB = B[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == 0) {
                nums[i - 1] = 1;
            }
            nums[i] *= nums[i - 1];
            if (B[i - 1] == 0) {
                B[i - 1] = 1;
            }
            B[i] *= B[i - 1];
            maxA = Math.max(maxA, nums[i]);
            maxB = Math.max(B[i], maxB);
        }
        return Math.max(maxA, maxB);
    }

    //664 -- ！！！非自己做的
    //执行用时 : 52 ms, 在Strange Printer的Java提交中击败了65.22% 的用户
    //内存消耗 : 38.6 MB, 在Strange Printer的Java提交中击败了68.75% 的用户
    public int strangePrinter(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        for (int[] i : dp) {
            Arrays.fill(i, 0x3f3f3f3f);
        }
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
        }
        for (int p = 1; p < n; ++p) {
            for (int i = 0; i < n - p; ++i) {
                int j = i + p;
                for (int k = i; k < j; ++k) {
                    int w = dp[i][k] + dp[k + 1][j];
                    if (s.charAt(i) == s.charAt(k + 1)) {
                        --w; // 第一段的起点 和第二段的起点一样 执行--
                    }
                    dp[i][j] = Math.min(dp[i][j], w);
                }
            }
        }
        return dp[0][n - 1];
    }

    //860
    //执行用时 : 4 ms, 在Lemonade Change的Java提交中击败了90.12% 的用户
    //内存消耗 : 48 MB, 在Lemonade Change的Java提交中击败了51.29% 的用户
    public boolean lemonadeChange(int[] bills) {
        int[] have = new int[3];
        Arrays.fill(have, 0);
        for (int b : bills) {
            switch (b) {
                case 5: {
                    have[0]++;
                    break;
                }
                case 10: {
                    have[0]--;
                    have[1]++;
                    break;
                }
                case 20: {
                    if (have[1] > 0) {
                        have[1]--;
                        have[0]--;
                    } else {
                        have[0] = have[0] - 3;
                    }
                    break;
                }
            }
            if (have[0] < 0) {
                return false;
            }
        }
        return true;
    }

    //134
    //执行用时 : 64 ms, 在Gas Station的Java提交中击败了40.25% 的用户
    //内存消耗 : 36.9 MB, 在Gas Station的Java提交中击败了88.09% 的用户
    public int canCompleteCircuit0(int[] gas, int[] cost) {
//        int[] status = new int[gas.length];
//        for(int i = 0;i < gas.length;i++){
//            status[i] = gas[i] - cost[i];
//        }
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            int j = i;
            int re = 0;
            boolean b = true;
            for (int k = 0; k < len; k++) {
                if (j >= len) {
                    j = 0;
                }
                re += gas[j] - cost[j];
                j++;
                if (re < 0) {
                    b = false;
                    break;
                }
            }
            if (b) {
                return i;
            }
        }
        return -1;
    }

    //134
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int[][] dps = new int[len][len];
        for (int i = 0; i < len; i++) {
            dps[0][0] = gas[0] - cost[0];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                dps[i][j] = Math.max(dps[i - 1][j], dps[i - 1][j] + gas[i] - cost[i]);
            }
        }
        return 0;
    }

    //82
    //执行用时 : 9 ms, 在Remove Duplicates from Sorted List II的Java提交中击败了5.84% 的用户
    //内存消耗 : 38.8 MB, 在Remove Duplicates from Sorted List II的Java提交中击败了6.38% 的用户
    public ListNode deleteDuplicates2(ListNode head) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> setHave = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (set.contains(p.val)) {
                setHave.add(p.val);
            } else {
                set.add(p.val);
            }
            p = p.next;
        }
        p = head;

        while (head != null && setHave.contains(head.val)) {
            head = head.next;
        }
        ListNode pre = head;
        if (pre != null) {
            p = pre.next;
            while (p != null) {
                if (setHave.contains(p.val)) {
                    pre.next = p.next;
                    p = p.next;
                } else {
                    pre = pre.next;
                    p = p.next;
                }

            }
        }
        return head;
    }

    //32
    //执行用时 : 2 ms, 在Remove Duplicates from Sorted List II的Java提交中击败了78.81% 的用户
    //内存消耗 : 35.7 MB, 在Remove Duplicates from Sorted List II的Java提交中击败了85.62% 的用户
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;

        ListNode end = head;
        ListNode node = head;
        while (node != null && node.next != null) {
            if (node.val == node.next.val) {

                while (node.next != null && node.val == node.next.val) {
                    node = node.next;
                    end = node;
                }
                if (pre == null) {
                    head = end.next;
                    node = head;
                } else {
                    pre.next = end.next;
                    node = end.next;
                }
            } else {
                pre = node;
                node = node.next;
            }
        }
        return head;
    }

    //11
    //执行用时 : 4 ms, 在Container With Most Water的Java提交中击败了98.95% 的用户
    //内存消耗 : 44.6 MB, 在Container With Most Water的Java提交中击败了65.84% 的用户
    public int maxArea(int[] height) {
        int len = height.length;
        int b = 0;
        int e = len - 1;
        int bl = height[b], el = height[e];
        int res = Math.min(bl, el) * (e - b);
        while (b < e) {
            if (bl > el) {
                while (bl > el) {
                    e--;
                    if (height[e] > el) {
                        break;
                    }
                }
                el = height[e];
                res = Math.max(Math.min(bl, el) * (e - b), res);
            } else {
                while (b < e) {
                    b++;
                    if (height[b] > bl) {
                        break;
                    }
                }
                bl = height[b];
                res = Math.max(Math.min(bl, el) * (e - b), res);
            }
        }
        return res;
    }

    //42
    //执行用时 : 3 ms, 在Trapping Rain Water的Java提交中击败了97.65% 的用户
    //内存消耗 : 38.4 MB, 在Trapping Rain Water的Java提交中击败了76.70% 的用户
    public int trap(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        int b = 0, e = len - 1;
//        while (b < e) {
//            if (height[b + 1] < height[b]) {
//                break;
//            }
//            b++;
//        }
//        while (b < e) {
//            if (height[e - 1] < height[e]) {
//                break;
//            }
//            e--;
//        }
        int bl = height[b], el = height[e];
        int res = 0;
        while (b < e) {
            if (bl < el) {
                while (b < e) {
                    if (height[b] > bl) {
                        bl = height[b];
                        break;
                    }
                    res += Math.max(bl - height[b], 0);
                    b++;
                }
            } else {
                while (b < e) {
                    if (height[e] > el) {
                        el = height[e];
                        break;
                    }
                    res += Math.max(el - height[e], 0);
                    e--;
                }
            }
        }
        return res;
    }

    //238
    //执行用时 : 8 ms, 在Product of Array Except Self的Java提交中击败了10.01% 的用户
    //内存消耗 : 53.3 MB, 在Product of Array Except Self的Java提交中击败了8.22% 的用户
    public int[] productExceptSelf0(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return nums;
        }
        int[] al = new int[len];
        int[] ar = new int[len];
        int[] res = new int[len];
        for (int i = 0; i < len - 1; i++) {
            if (i == 0) {
                al[i] = nums[i];
                ar[len - i - 1] = nums[len - i - 1];
            } else {
                al[i] = al[i - 1] * nums[i];
                ar[len - i - 1] = ar[len - i] * nums[len - i - 1];
            }
            if (len - i - 1 <= i) {
                res[len - i - 1] = al[len - i - 2] * ar[len - i];
                res[i] = al[i - 1] * ar[i + 1];
            }
        }
        res[0] = ar[1];
        res[len - 1] = al[len - 2];
        return res;
    }

    //238
    //执行用时 : 2 ms, 在Product of Array Except Self的Java提交中击败了94.87% 的用户
    //内存消耗 : 43.5 MB, 在Product of Array Except Self的Java提交中击败了91.01% 的用户
    public int[] productExceptSelf(int[] nums) {
        int left = 1;
        int right = 1;
        int len = nums.length;
        int[] output = new int[len];
        for (int i = 0; i < len; i++) {
            output[i] = left;
            left *= nums[i];
        }
        for (int j = len - 1; j >= 0; j--) {
            output[j] *= right;
            right *= nums[j];
        }
        return output;
    }

    //349
    //执行用时 : 4 ms, 在Intersection of Two Arrays的Java提交中击败了94.53% 的用户
    //内存消耗 : 37.3 MB, 在Intersection of Two Arrays的Java提交中击败了36.50% 的用户
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }
        int[] res = new int[set2.size()];
        int j = 0;
        for (int i : set2) {
            res[j++] = i;
        }
        return res;
    }

    //287 -- set
    //执行用时 : 9 ms, 在Find the Duplicate Number的Java提交中击败了21.53% 的用户
    //内存消耗 : 41.7 MB, 在Find the Duplicate Number的Java提交中击败了5.13% 的用户
    public int findDuplicate0(int[] nums) {
//        int res = 0;
        Set set = new HashSet();
        for (int i : nums) {
            if (set.contains(i)) {
                return i;
            }
            set.add(i);
//            res = res & i;
        }
        return 0;
    }

    //287 -- 快慢指针
    //执行用时 : 1 ms, 在Find the Duplicate Number的Java提交中击败了97.06% 的用户
    //内存消耗 : 38.3 MB, 在Find the Duplicate Number的Java提交中击败了68.01% 的用户
    public int findDuplicate(int[] nums) {
        /**
         快慢指针思想, fast 和 slow 是指针, nums[slow] 表示取指针对应的元素
         注意 nums 数组中的数字都是在 1 到 n 之间的(在数组中进行游走不会越界),
         因为有重复数字的出现, 所以这个游走必然是成环的, 环的入口就是重复的元素,
         即按照寻找链表环入口的思路来做
         **/
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (slow == fast) {
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }

    // 704
    // 执行用时 : 1 ms, 在Binary Search的Java提交中击败了96.76% 的用户
    // 内存消耗 : 48.5 MB, 在Binary Search的Java提交中击败了61.75% 的用户
    public int search0(int[] nums, int target) {
        return search(nums, target, 0, nums.length);
    }

    public int search(int[] nums, int target, int b, int e) {
        if (b >= e) {
            return -1;
        }
        if (b == e - 1) {
            return nums[b] == target ? b : -1;
        }
        int len = (b + e) / 2;
        if (target == nums[len]) {
            return len;
        } else if (target < nums[len]) {
            return search(nums, target, b, len);
        } else {
            return search(nums, target, len + 1, e);
        }
    }

    //778
    //执行用时 : 11 ms, 在Swim in Rising Water的Java提交中击败了93.48% 的用户
    //内存消耗 : 39.1 MB, 在Swim in Rising Water的Java提交中击败了76.92% 的用户
    public int swimInWater0(int[][] grid) {
        int xlen = grid.length;
        int ylen = grid[0].length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] i : grid) {
            for (int j : i) {
                min = Math.min(j, min);
                max = Math.max(j, max);
            }
        }
        while (min < max) {
            if (min == max - 1) {
                boolean[][] visit = new boolean[xlen][ylen];
                dps(grid, 0, 0, visit, min);
                if (!visit[xlen - 1][ylen - 1]) {
                    min = max;
                }
                break;
            }
            int m = (min + max) / 2;
            boolean[][] visit = new boolean[xlen][ylen];
            dps(grid, 0, 0, visit, m);
            if (visit[xlen - 1][ylen - 1]) {
                max = m;
            } else {
                min = m;
            }
        }
        return min;
    }

    public void dps(int[][] grid, int x, int y, boolean[][] visit, int key) {
        int xlen = grid.length;
        int ylen = grid[0].length;
        if (x < 0 || y < 0 || x > xlen - 1 || y > ylen - 1 || visit[x][y] || grid[x][y] > key) {
            return;
        }
        visit[x][y] = true;
        dps(grid, x - 1, y, visit, key);
        dps(grid, x + 1, y, visit, key);
        dps(grid, x, y - 1, visit, key);
        dps(grid, x, y + 1, visit, key);
        return;
    }


    //778
    //执行用时 : 68 ms, 在Swim in Rising Water的Java提交中击败了60.87% 的用户
    //内存消耗 : 44.6 MB, 在Swim in Rising Water的Java提交中击败了34.61% 的用户
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int res = grid[0][0];
        int left = res, right = n * n - 1;
        int mid = (left + right) / 2;

        while (left <= right) {
            res = mid;

            int[][] vis = new int[n][n];
            PriorityQueue<Pair> qt = new PriorityQueue<>();
            qt.offer(new Pair(0, 0, res));
            vis[0][0] = 1;
            int flag = 0;
            while (!qt.isEmpty()) {
                Pair tmp = qt.poll();
                int x = tmp.x;
                int y = tmp.y;
//                System.out.println(x+" "+y+" "+res);
                for (int i = 0; i < 4; i++) {
                    int tx = x + dx[i];
                    int ty = y + dy[i];
                    if (tx < 0 || ty < 0 || tx >= n || ty >= n || vis[tx][ty] == 1
                            || grid[tx][ty] > res) {
                        continue;
                    }
                    vis[tx][ty] = 1;
                    if (tx == n - 1 && ty == n - 1) {
                        flag = 1;
                        break;
                    }
                    qt.offer(new Pair(tx, ty, grid[tx][ty]));
                }
            }
            if (flag == 1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return left;
    }


    /**
     * 面试题 02.01. 移除重复节点
     * 执行用时：5 ms, 在所有 Java 提交中击败了91.68%的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return head;
        }
        Set<Integer> set = new HashSet<>();
        ListNode p = head;
        set.add(p.val);
        ListNode q = head.next;
        while (q != null) {
            if (set.add(q.val)) {
                p = p.next;
                q = q.next;
            } else {
                q = q.next;
                p.next = q;
            }
        }
        return head;
    }


    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int kthToLast(ListNode head, int k) {
        ListNode q = head;
        while (k > 0) {
            q = q.next;
            k--;
        }
        ListNode p = head;
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        return p.val;
    }


    /**
     * 面试题 02.03. 删除中间节点
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 785. 判断二分图
     * 7.16每日一题
     * 执行用时：2 ms, 在所有 Java 提交中击败了49.70%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了75.00%的用户
     *
     * @param graph 无向图
     * @return 是否为二分图
     */
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        //0:未分配 1:分配1图 2:分配2图
        int[] div = new int[len];
        Stack<Integer> gs1 = new Stack<>();
        Stack<Integer> gs2 = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (div[i] == 0) {
                gs1.add(i);
                div[i] = 1;
                while (!gs1.empty() || !gs2.empty()) {
                    while (!gs1.empty()) {
                        int t = gs1.pop();
                        for (int j : graph[t]) {
                            if (div[j] == 0) {
                                gs2.add(j);
                                div[j] = 2;
                            } else if (div[j] == 1) {
                                return false;
                            }
                        }
                    }
                    while ((!gs2.empty())) {
                        int t = gs2.pop();
                        for (int j : graph[t]) {
                            if (div[j] == 0) {
                                gs1.add(j);
                                div[j] = 1;
                            } else if (div[j] == 2) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        int len = 0;
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        Set<String> num = new HashSet<>();
        Map<String, PriorityQueue<String>> plan = new HashMap<>();
        tickets.forEach((x) -> {
            String from = x.get(0);
            String to = x.get(1);
            plan.putIfAbsent(from, new PriorityQueue<String>());
            PriorityQueue<String> queue = plan.get(from);
            queue.add(to);
            plan.put(from, queue);
            num.add(from);
            num.add(to);
        });
        Set<String> visited = new HashSet<>();
        String now = "JFK";
        visited.add(now);
        int n = 1;
        Stack<Queue<String>> back = new Stack<>();
        List<String> res = new LinkedList<>();
        res.add("JFK");
        boolean isBack = false;
        len = num.size();
        while (n < len) {
            if (!isBack) {
                PriorityQueue<String> tmp = plan.get(now);
                Queue<String> queue = new LinkedList<>();
                tmp.forEach(x -> {
                    if (!visited.contains(x)) {
                        queue.add(x);
                    }
                });
                if (queue.isEmpty()) {
                    isBack = true;
                    continue;
                }
                now = queue.peek();
                res.add(now);
                visited.add(now);
                back.add(queue);
            } else {
                Queue<String> queue = back.pop();
                now = queue.poll();
                visited.remove(now);
                res.remove(now);
                n--;
                if (queue.isEmpty()) {
                    continue;
                }
                now = queue.peek();
                res.add(now);
                visited.add(now);
                isBack = false;
            }
            n++;
        }
        return res;
    }

    /**
     * 35. 搜索插入位置
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了33.33%的用户
     *
     * @param nums   array
     * @param target int
     * @return int
     */
    public int searchInsert1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 1342. 将数字变成 0 的操作次数
     * 执行用时：471 ms, 在所有 Java 提交中击败了20.36%的用户
     * 内存消耗：45.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param num array
     * @return times
     */
    public int numberOfSteps(int num) {
        if (num == 0) {
            return 0;
        }
        int[] dp = new int[num];
        dp[0] = 1;
        for (int i = 1; i < num; i++) {
            if (i % 2 != 0) {
                dp[i] = dp[i / 2] + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp[num - 1];
    }

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了7.67%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums  array value
     * @param index array index
     * @return new array
     */
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> target = new LinkedList<>();
        int len = index.length;
        for (int i = 0; i < len; i++) {
            target.add(index[i], nums[i]);
        }

        Integer[] arr = target.toArray(new Integer[target.size()]);
        return Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 执行用时：1 ms, 在所有 Java 提交中击败了95.52%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了6.67%的用户
     *
     * @param numbers 按照升序排列 的有序数组
     * @param target  目标数
     * @return 两个数的下标
     */
    public int[] twoSum1(int[] numbers, int target) {
        int len = numbers.length;
        int l = 0, r = len - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[]{};
    }

    /**
     * 1365. 有多少小于当前数字的数字
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     * 执行用时：9 ms, 在所有 Java 提交中击败了61.53%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums 数组
     * @return 统计数组中比它小的所有数字的数目
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        List<int[]> tmp = new ArrayList<>();
        int[] vec = new int[n];
        for (int i = 0; i < n; ++i) {
            tmp.add(new int[]{nums[i], i});
        }
        Comparator<int[]> c = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };
        tmp.sort(c);
        int preKey = tmp.get(0)[0];
        int preNum = 0;
        int sameNum = 1;
        for (int i = 1; i < n; i++) {
            int[] ints = tmp.get(i);
            if (preKey != ints[0]) {
                preKey = ints[0];
                preNum += sameNum;
                sameNum = 1;
                vec[ints[1]] = preNum;
            } else {
                vec[ints[1]] = preNum;
                sameNum++;
            }
        }
        return vec;
    }

    /**
     * 1365. 有多少小于当前数字的数字
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums 数组
     * @return 统计数组中比它小的所有数字的数目
     */
    public int[] smallerNumbersThanCurrent0(int[] nums) {
        // 统计出现频率 frequency
        int[] freq = new int[101]; // 索引即数值
        for (int num : nums) {
            freq[num]++;
        }

        // 对频率(而非对原数组nums)从前到后累加
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i] + freq[i - 1];
        }

        // 输出结果
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            if (nums[i] > 0) {
                res[i] = freq[nums[i] - 1];
            }
        }
        return res;
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 执行用时：6 ms, 在所有 Java 提交中击败了85.57% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了5.20% 的用户
     *
     * @param s 1个字符串
     * @return 不含有重复字符的 最长子串 的长度
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int max = 1;
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            int l = dp[i - 1];
            for (int j = i - 1; j >= l; j--) {
                if (chars[j] == chars[i]) {
                    l = j + 1;
                }
            }
            dp[i] = l;
            max = Math.max(max, i - l + 1);
        }
        return max;
    }

    /**
     * 1363. 形成三的最大倍数
     * 给你一个整数数组 digits，你可以通过按任意顺序连接其中某些数字来形成 3 的倍数，请你返回所能得到的最大的 3 的倍数。
     * 执行用时：30 ms, 在所有 Java 提交中击败了25.21% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param digits 一个整数数组
     * @return 所能得到的最大的 3 的倍数
     */
    public String largestMultipleOfThree(int[] digits) {
        List<Integer> zero = new LinkedList<>();
        List<Integer> one = new LinkedList<>();
        List<Integer> two = new LinkedList<>();
        for (int i : digits) {
            if (i % 3 == 0) {
                zero.add(i);
            } else if (i % 3 == 1) {
                one.add(i);
            } else {
                two.add(i);
            }
        }
        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        one.sort(Comparator.comparingInt(Integer::intValue));
        two.sort(Comparator.comparingInt(Integer::intValue));
        int tmp = one.size() - two.size();
        if (Math.abs(tmp) <= 1) {
            if (tmp == -1) {
                two.remove(0);
            } else if (tmp == 1) {
                one.remove(0);
            }
            zero.addAll(one);
            zero.addAll(two);
        } else {
            int o = one.size() % 3;
            int t = two.size() % 3;
            int i = 0;
            if (t < o) {
                while (i < o - t) {
                    one.remove(0);
                    i++;
                }
            } else {
                while (i < t - o) {
                    two.remove(0);
                    i++;
                }
            }
            zero.addAll(one);
            zero.addAll(two);
        }
        if (zero.size() == 0) {
            return "";
        }
        zero.sort(c);
        if (zero.get(0) == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        zero.forEach(res::append);
        return res.toString();
    }

    /**
     * 95. 不同的二叉搜索树 II
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了14.29% 的用户
     *
     * @param n 一个整数 n
     * @return 生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> pre = new ArrayList<TreeNode>();
        if (n == 0) {
            return pre;
        }
        pre.add(null);
        //每次增加一个数字
        for (int i = 1; i <= n; i++) {
            List<TreeNode> cur = new ArrayList<TreeNode>();
            //遍历之前的所有解
            for (TreeNode root : pre) {
                //插入到根节点
                TreeNode insert = new TreeNode(i);
                insert.left = root;
                cur.add(insert);
                //插入到右孩子，右孩子的右孩子...最多找 n 次孩子
                for (int j = 0; j <= n; j++) {
                    TreeNode root_copy = treeCopy(root); //复制当前的树
                    TreeNode right = root_copy; //找到要插入右孩子的位置
                    int k = 0;
                    //遍历 j 次找右孩子
                    for (; k < j; k++) {
                        if (right == null) {
                            break;
                        }
                        right = right.right;
                    }
                    //到达 null 提前结束
                    if (right == null) {
                        break;
                    }
                    //保存当前右孩子的位置的子树作为插入节点的左孩子
                    TreeNode rightTree = right.right;
                    insert = new TreeNode(i);
                    right.right = insert; //右孩子是插入的节点
                    insert.left = rightTree; //插入节点的左孩子更新为插入位置之前的子树
                    //加入结果中
                    cur.add(root_copy);
                }
            }
            pre = cur;

        }
        return pre;
    }


    private TreeNode treeCopy(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = treeCopy(root.left);
        newRoot.right = treeCopy(root.right);
        return newRoot;
    }

    /**
     * 95. 不同的二叉搜索树 II
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了14.29% 的用户
     *
     * @param n 一个整数 n
     * @return 生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     */
    public List<TreeNode> generateTrees1(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return dp[0];
        }
        dp[0].add(null);
        //长度为 1 到 n
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<TreeNode>();
            //将不同的数字作为根节点，只需要考虑到 len
            for (int root = 1; root <= len; root++) {
                int left = root - 1;  //左子树的长度
                int right = len - root; //右子树的长度
                for (TreeNode leftTree : dp[left]) {
                    for (TreeNode rightTree : dp[right]) {
                        TreeNode treeRoot = new TreeNode(root);
                        treeRoot.left = leftTree;
                        //克隆右子树并且加上偏差
                        treeRoot.right = clone(rightTree, root);
                        dp[len].add(treeRoot);
                    }
                }
            }
        }
        return dp[n];
    }

    private TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * 执行用时：2 ms, 在所有 Java 提交中击败了57.41% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了5.55% 的用户
     *
     * @param x 非负整数
     * @return x 的平方根
     */
    public int mySqrt(int x) {
        // 注意：针对特殊测试用例，例如 2147395599
        // 要把搜索的范围设置成长整型
        // 为了照顾到 0 把左边界设置为 0
        long l = 0, r = x;
        while (l < r) {
            long tmp = (l + r) / 2;
            if (tmp * tmp < x) {
                l = tmp + 1;
            } else {
                r = tmp;
            }
        }
        return (int) (l * l == x ? l : l - 1);
        // 注意：针对特殊测试用例，例如 2147395599
        // 要把搜索的范围设置成长整型
        // 为了照顾到 0 把左边界设置为 0
/*        long left = 0;
        // # 为了照顾到 1 把右边界设置为 x // 2 + 1
        long right = x / 2 + 1;
        while (left < right) {
            // 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
            // long mid = left + (right - left + 1) / 2;
            long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // 因为一定存在，因此无需后处理
        return (int) left;*/
    }

    /**
     * 面试题 17.14. 最小K个数
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     * 执行用时：7 ms, 在所有 Java 提交中击败了73.60% 的用户
     * 内存消耗：49.7 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param arr 数组
     * @param k   k个数字
     * @return 以任意顺序返回这k个数
     */
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 面试题 02.04. 分割链表
     * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param head 链表
     * @param x    基准
     * @return 分割链表
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode res = new ListNode(0);
        ListNode max = head.next;
        ListNode pre = head;
        ListNode min = res;
        while (max != null) {
            if (max.val < x) {
                min.next = max;
                pre.next = max.next;
                max = max.next;
                min = min.next;
            } else {
                pre = pre.next;
                max = max.next;
            }
        }
        min.next = head;
        return res.next;
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param numbers 递增排序的数组
     * @return
     */
    public int minArray(int[] numbers) {
        int len = numbers.length;
        for (int i = 1; i < len; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

    /**
     * LCP 12. 小张刷题计划
     * 为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，并计划在 m 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。
     * 在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。
     * 我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。
     * 执行用时：9 ms, 在所有 Java 提交中击败了87.01% 的用户
     * 内存消耗：47.6 MB, 在所有 Java 提交中击败了100.00% 的用户
     * @param time time[i] 的时间完成编号 i 的题目
     * @param m  m 天
     * @return 最小的 T是多少
     */
    public int minTime(int[] time, int m) {
        int len = time.length;
        if (len <= m) {
            return 0;
        }
        int l = 0, r = Integer.MAX_VALUE;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (isOk(time, mid, m)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public boolean isOk(int[] time, int mid, int m) {
        int n = 0;
        int max = time[0];
        int tmp = max;
        int realMax = 0;
        for (int i = 1; i < time.length; i++) {
            tmp += time[i];
            max = Math.max(max, time[i]);
            if (max + mid < tmp) {
                n++;
                max = time[i];
                tmp = max;
            }
        }
        return n < m;
    }

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
            root = new Trie0.Node();
            int len = strings.length;
            lists = new List[len];
            for (int i = 0; i < len; i++) {
                lists[i] = new ArrayList<>();
            }
        }

        public void insert(String word, int id) {
            Trie0.Node p = root;
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
    public int[][] floodFill0(int[][] image, int sr, int sc, int newColor) {
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
    public int[][] floodFill010(int[][] image, int sr, int sc, int newColor) {
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
    public String addBinary00(String a, String b) {
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
    public int numBusesToDestination000(int[][] routes, int S, int T) {
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
    public int numBusesToDestination00(int[][] routes, int S, int T) {
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
    public int findKthLargest0(int[] nums, int k) {
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
    public int uniquePathsWithObstacles00(int[][] obstacleGrid) {
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
    public int minPathSum0(int[][] grid) {
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
    public int maxProfit20(int[] prices) {
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
    public int maxProfit30(int[] prices) {
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
     *
     * @param prices 股票价格数组
     * @return 最大收益
     */
    public int maxProfit0(int[] prices) {
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

    /**
     * 350. 两个数组的交集 II
     * 执行用时：3 ms, 在所有 Java 提交中击败了87.23%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了5.13%的用户
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 两个数组的交集
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> resList = new ArrayList<>();
        int a = 0, b = 0;
        int n = nums1.length, m = nums2.length;
        while (a < n && b < m) {
            if (nums1[a] == nums2[b]) {
                resList.add(nums1[a]);
                a++;
                b++;
            } else if (nums1[a] < nums2[b]) {
                a++;
            } else {
                b++;
            }
        }
        int[] res = new int[resList.size()];
        int i = 0;
        for (int j : resList) {
            res[i++] = j;
        }
        return res;
    }


    /**
     * 1022. 从根到叶的二进制数之和
     * 执行用时：16 ms, 在所有 Java 提交中击败了6.05%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了16.67%的用户
     *
     * @param root 原始树
     * @return 和
     */
    public int sumRootToLeaf(TreeNode root) {
        preVisitNode(root, "");
        return sum;
    }

    private int sum = 0;

    public void preVisitNode(TreeNode root, String pre) {
        if (root.left == null && root.right == null) {
            pre = pre + root.val;
            sum += Biannary2Decimal(pre);
            return;
        }
        pre = pre + root.val;
        if (root.left != null) {
            preVisitNode(root.left, pre);
        }
        if (root.right != null) {
            preVisitNode(root.right, pre);
        }
        return;
    }

    /**
     * 将二进制转换为10进制
     *
     * @param binStr ：待转换的二进制
     * @return 十进制
     */
    public int Biannary2Decimal(String binStr) {
        int sum = 0;
        int len = binStr.length();
        for (int i = 1; i <= len; i++) {
            //第i位 的数字为：
            int dt = Integer.parseInt(binStr.substring(i - 1, i));
            sum += (int) Math.pow(2, len - i) * dt;
        }
        return sum;
    }

    /**
     * 1431. 拥有最多糖果的孩子
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.92%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param candies      candies[i] 代表第 i 个孩子拥有的糖果数目
     * @param extraCandies 额外的 extraCandies 个糖果
     * @return 是否是拥有最多糖果的孩子
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int len = candies.length;
        List<Boolean> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        if (len == 1) {
            res.add(true);
            return res;
        }
        int max = Integer.MIN_VALUE;
        for (int c : candies) {
            max = Math.max(c, max);
        }
        for (int candy : candies) {
            if (candy + extraCandies >= max) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

    /**
     * 1189. “气球” 的最大数量
     * 执行用时：3 ms, 在所有 Java 提交中击败了58.65%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了16.67%的用户
     *
     * @param text 原始字符串
     * @return 最多气球个数
     */
    public int maxNumberOfBalloons(String text) {
        int a = 0, b = 0, n = 0, o = 0, l = 0;
        char[] chars = text.toCharArray();
        for (char c : chars) {
            if ('a' == c) {
                a++;
            } else if ('b' == c) {
                b++;
            } else if ('n' == c) {
                n++;
            } else if ('o' == c) {
                o++;
            } else if ('l' == c) {
                l++;
            }
        }
        int min = Math.min(a, b);
        min = Math.min(min, n);
        min = Math.min(o / 2, min);
        min = Math.min(l / 2, min);
        return min;
    }

    /**
     * LCP 06. 拿硬币
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param coins 数组 coins
     * @return 拿完所有力扣币的最少次数
     */
    public int minCount(int[] coins) {
        int res = 0;
        for (int coin : coins) {
            res += coin / 2;
            if (coin % 2 == 1) {
                res++;
            }
        }
        return res;
    }

    /**
     * 1470. 重新排列数组
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums 数组
     * @param n    数组中有 2n 个元素
     * @return 重排后的数组
     */
    public int[] shuffle(int[] nums, int n) {
        int[] array = new int[2 * n];
        for (int i = 0; i < n; i++) {
            array[2 * i] = nums[i];
            array[2 * i + 1] = nums[n + i];
        }
        return array;
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 执行用时：3 ms, 在所有 Java 提交中击败了29.06%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param s 初始字符串
     * @param n 第几位
     * @return 结果
     */
    public String reverseLeftWords(String s, int n) {
        String s1 = new StringBuffer(s.substring(0, n)).reverse().toString();
        int len = s.length();
        String s2 = new StringBuffer(s.substring(n, len)).reverse().toString();
        return new StringBuffer(s1 + s2).reverse().toString();
    }

    /**
     * 1281. 整数的各位积和之差
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了6.06%的用户
     *
     * @param n 整数 n
     * @return 整数的各位积和之差
     */
    public int subtractProductAndSum(int n) {
        int sum = 0;
        int product = 1;
        int tmp = n % 10;
        while (n > 0) {
            sum += tmp;
            product *= tmp;
            n = n / 10;
            tmp = n % 10;
        }
        return product - sum;
    }

    /**
     * 1108. IP 地址无效化
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了13.04%的用户
     *
     * @param address IP 地址
     * @return IP 地址的无效化版本
     */
    public String defangIPaddr(String address) {
        String[] str = address.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        int len = str.length;
        for (int i = 0; i < len - 1; i++) {
            stringBuilder.append(str[i]).append("[.]");
        }
        if (len >= 1) {
            stringBuilder.append(str[len - 1]);
        }
        return stringBuilder.toString();
    }

    /**
     * 120. 三角形最小路径和
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.02%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了8.70%的用户
     * @param triangle 三角形数组
     * @return 三角形最小路径和
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = new int[i + 1];
        }
        dp[0][0] = triangle.get(0).get(0);
        int min = dp[0][0];
        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            int m = list.size();
            if (i != n - 1) {
                dp[i][0] = dp[i - 1][0] + list.get(0);
                dp[i][m - 1] = dp[i - 1][m - 2] + list.get(m - 1);
                for (int j = 1; j < m - 1; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + list.get(j);
                }
            } else {
                dp[i][0] = dp[i - 1][0] + list.get(0);
                dp[i][m - 1] = dp[i - 1][m - 2] + list.get(m - 1);
                min = Math.min(dp[i][0], dp[i][m - 1]);
                for (int j = 1; j < m - 1; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + list.get(j);
                    min = Math.min(min, dp[i][j]);
                }
            }
        }
        return min;
    }

    /**
     * 创建测试用的数据类型
     *
     * @param array 数组
     * @return 结果： List<List<Integer>>
     */
    public static List<List<Integer>> createList(int[][] array) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] arr : array) {
            List<Integer> tmp = new ArrayList<>();
            for (int a : arr) {
                tmp.add(a);
            }
            res.add(tmp);
        }
        return res;
    }

    //174
    //执行用时 : 13 ms, 在Dungeon Game的Java提交中击败了5.56% 的用户
    //内存消耗 : 39.7 MB, 在Dungeon Game的Java提交中击败了33.82% 的用户
    public int calculateMiniumHP(int[][] dungeon) {
        int xlen = dungeon.length;
        int ylen = dungeon[0].length;
        int min = 0;
        int max = 1;
        for (int[] i : dungeon) {
            for (int j : i) {
                max = j < 0 ? max - j : max;
            }
        }
        int res = 0;
        while (max > min + 1) {
            int m = (min + max) / 2;
            if (dps(dungeon, m)) {
                max = m;
            } else {
                min = m;
            }
        }
        if (dps(dungeon, min)) {
            return min == 0 ? 1 : min;
        } else {
            return max;
        }
    }

    public boolean dps(int[][] grid, int m) {
        int xlen = grid.length;
        int ylen = grid[0].length;
        int[][] dps = new int[xlen][ylen];
        dps[0][0] = grid[0][0] + m;
        if (dps[0][0] <= 0) {
            return false;
        }
        for (int i = 1; i < xlen; i++) {
            dps[i][0] = dps[i - 1][0] + grid[i][0];
            if (dps[i][0] <= 0) {
                dps[i][0] = Integer.MIN_VALUE / 3;
            }
        }
        for (int i = 1; i < ylen; i++) {
            dps[0][i] = dps[0][i - 1] + grid[0][i];
            if (dps[0][i] <= 0) {
                dps[0][i] = Integer.MIN_VALUE / 3;
            }
        }
        for (int i = 1; i < xlen; i++) {
            for (int j = 1; j < ylen; j++) {
                int t = Math.max(dps[i - 1][j], dps[i][j - 1]);
                if (t <= 0) {
                    t = Integer.MIN_VALUE / 3;
                }
                dps[i][j] = grid[i][j] + t;
            }
        }
        return dps[xlen - 1][ylen - 1] > 0;
    }

    //174
    //执行用时 : 4 ms, 在Dungeon Game的Java提交中击败了86.32% 的用户
    //内存消耗 : 37.1 MB, 在Dungeon Game的Java提交中击败了82.73% 的用户
    /*参考了评论中的答案，使用动态规划 核心思路是：每次只能往右边或者下面走，那么如果你知道了往右边走需要的最小生命代价和往下面需要的最小生命代价，那么你就知道了当前需要的最小代价 使用二维数组来保存最小生命代价，life[i][j]表示从i，j开始走到右下角的最小生命代价 从右下开始往上动态规划，初始话最小代价是1，如果当前不需要额外的生命代价的话，那么当前的最小生命代价就是1，如果需要额外的代价的话，那么最小生命代价就是额外需要的代价。 说的比较绕口，代码应该比较好理解，总的思路就是，如果后面不需要额外生命，那么你只需要保证能走到当前就可以了，如果后面需要额外代价，那么你需要保证能走到当前并且+后面的额外生命代价*/
    //求骑士走到右下角的最低生命值
    // 只能右或者下
    // DP
    public int calculateMinimumHP0(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        // life[i][j] 表示i,j的时候需要的最小生命值，肯定不能小于1
        int[][] life = new int[row][col];
        // 初始化
        if (dungeon[row - 1][col - 1] < 0) {
            life[row - 1][col - 1] = -dungeon[row - 1][col - 1] + 1;
        } else {
            life[row - 1][col - 1] = 1;
        }
        // 初始化最后一列
        for (int i = row - 2; i >= 0; i--) {
            // 表示后面的可以自己满足
            if (life[i + 1][col - 1] == 1) {
                life[i][col - 1] = Math.max(1, -dungeon[i][col - 1] + 1);
            } else { // 后面的不可以满足
                life[i][col - 1] = Math.max(life[i + 1][col - 1] - dungeon[i][col - 1], 1);
            }
        }
        // 初始话最后一行
        for (int j = col - 2; j >= 0; j--) {
            if (life[row - 1][j + 1] == 1) {
                life[row - 1][j] = Math.max(1, -dungeon[row - 1][j] + 1);
            } else {
                life[row - 1][j] = Math.max(life[row - 1][j + 1] - dungeon[row - 1][j], 1);
            }
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                int min = Math.min(life[i][j + 1], life[i + 1][j]);
                // 后面可以满足
                if (min == 1) {
                    life[i][j] = Math.max(1, -dungeon[i][j] + 1);
                } else {
                    life[i][j] = Math.max(1, min - dungeon[i][j]);
                }
            }
        }
        return life[0][0];
    }

    //1025 -- 取巧
    //执行用时 : 0 ms, 在Divisor Game的Java提交中击败了100.00% 的用户
    //内存消耗 : 32.6 MB, 在Divisor Game的Java提交中击败了100.00% 的用户
    public boolean divisorGame0(int N) {
        return N % 2 == 0;
    }

    //1025
    //动态规划： (没有采用这个思路)状态转移方程： dp[i,True] = dp[i-j,False] ,其中 i % j==0
    //执行用时 : 8 ms, 在Divisor Game的Java提交中击败了14.50% 的用户
    //内存消耗 : 33.3 MB, 在Divisor Game的Java提交中击败了100.00% 的用户
    public boolean divisorGame(int N) {
        if (N <= 1) {
            return false;
        }
//        int[][] dp = new int[N][2];
//        dp[0][0] = N;
        int[] f = new int[N + 1];
        f[1] = 0;
        f[2] = 1;
        for (int i = 3; i < N + 1; i++) {
            for (int j = 1; j < i / 2; j++) {
                if (i % j == 0 && f[i - j] == 0) {
                    f[i] = 1;
                    break;
                }
            }
        }
        return f[N] == 1;
    }

    //16
    //执行用时 : 215 ms, 在3Sum Closest的Java提交中击败了5.05% 的用户
    //内存消耗 : 35.8 MB, 在3Sum Closest的Java提交中击败了85.17% 的用户
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Integer.MAX_VALUE;
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int key = nums[i] + nums[j] + nums[k] - target;
                    int abkey = Math.abs(key);
                    if (abkey < Math.abs(res[i])) {
                        res[i] = key;
                    }
                }
            }
        }
        int mir = -1000;
        int mar = 1000;
        for (int i : res) {
            if (i > 0) {
                mar = Math.min(mar, i);
            } else if (i < 0) {
                mir = Math.max(mir, i);
            } else {
                return target;
            }
        }
        mir = Math.abs(mir) < mar ? mir : mar;
        return mir + target;
    }

    //16
    //执行用时 : 14 ms, 在3Sum Closest的Java提交中击败了81.75% 的用户
    //内存消耗 : 35.8 MB, 在3Sum Closest的Java提交中击败了85.17% 的用户
    public int threeSumClosests(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = Integer.MAX_VALUE / 2;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int cur = nums[i] + nums[left] + nums[right];
                if (cur == target) {
                    return target;
                }
                if (Math.abs(res - target) > Math.abs(cur - target)) {
                    res = cur;
                }
                if (cur > target) {
                    right -= 1;
                } else if (cur < target) {
                    left += 1;
                }
            }
        }
        return res;
    }

    //977
    //执行用时 : 317 ms, 在Squares of a Sorted Array的Java提交中击败了14.59% 的用户
    //内存消耗 : 51.9 MB, 在Squares of a Sorted Array的Java提交中击败了48.32% 的用户
    public int[] sortedSquares0(int[] A) {
        int[] res = new int[A.length];
        int j = 0;
        for (int i : A) {
            int ii = i * i;
            int k = 0;
            while (k < j) {
                if (res[k] > ii) {
                    break;
                }
                k++;
            }
            int kk = j;
            while (kk > k) {
                res[kk] = res[kk - 1];
                kk--;
            }
            res[k] = ii;
            j++;

        }
        return res;
    }

    //977
    //执行用时 : 4 ms, 在Squares of a Sorted Array的Java提交中击败了98.75% 的用户
    //内存消耗 : 39.2 MB, 在Squares of a Sorted Array的Java提交中击败了99.30% 的用户
    public int[] sortedSquares(int[] A) {
        int i = A.length - 1;
        int j = 0;
        int kk = -1;
        while (A[i] * A[j] < 0 && i > j + 1) {
            int k = (i + j) / 2;
            if (A[k] == 0) {
                i = k;
                j = k;
                break;
            }
            if (A[i] * A[k] < 0) {
                j = k;
            } else {
                i = k;
            }
        }
        if (0 - A[j] < A[i]) {
            i = j;
        }
        int[] res = new int[A.length];
        j = i - 1;
        int k;
        for (k = 0; k < A.length && j >= 0 && i <= A.length - 1; k++) {
            if (A[i] * A[i] > A[j] * A[j]) {
                res[k] = A[j] * A[j];
                j--;
            } else {
                res[k] = A[i] * A[i];
                i++;
            }
        }
        if (k < A.length) {
            while (j >= 0) {
                res[k++] = A[j] * A[j];
                j--;
            }
            while (i < A.length) {
                res[k++] = A[i] * A[i];
                i++;
            }
        }
        return res;
    }

    //638 -- 暴力+剪支
    //执行用时 : 9 ms, 在Shopping Offers的Java提交中击败了96.86% 的用户
    //内存消耗 : 36.6 MB, 在Shopping Offers的Java提交中击败了88.61% 的用户
    /*    暴力解法(dfs) + 减枝

    分为3步：
    1. 每种物品都单独购买，需要money1
    2. 用大礼包进行替换， 需要money2
    3. 取最小值min(money1, money2)

    减枝：
    1. 当礼包中的物品的数量  >  所需物品的数量， 要进行减枝
    2. 为了避免像，礼包1，2 和 礼包2， 1这种情况重复计算两次，可以使用pos来指向当前的位置
    允许获取的礼包的索引只能大于等于pos（这种减枝比较隐蔽）
    */
    public int shoppingOffers0(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shoppingOffers(price, special, needs, 0);
    }

    private int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos) {
        int local_min = directParchase(price, needs);
        for (int i = pos; i < special.size(); i++) {
            List<Integer> tmp = new ArrayList<>();
            List<Integer> offer = special.get(i);
            for (int j = 0; j < needs.size(); j++) {
                if (offer.get(j) > needs.get(j)) {
                    tmp = null;
                    break;
                }
                tmp.add(needs.get(j) - offer.get(j));
            }
            if (tmp != null) {
                local_min = Math.min(local_min, offer.get(offer.size() - 1) + shoppingOffers(price, special, tmp, i));
            }
        }
        return local_min;
    }

    private int directParchase(List<Integer> price, List<Integer> needs) {
        int sum = 0;
        for (int i = 0; i < needs.size(); i++) {
            sum += price.get(i) * needs.get(i);
        }
        return sum;
    }

    //638 -- 动态规划
    //执行用时 : 30 ms, 在Shopping Offers的Java提交中击败了42.14% 的用户
    //内存消耗 : 41 MB, 在Shopping Offers的Java提交中击败了37.98% 的用户
    public int shoppingOffers(List<Integer> price, List<List<Integer>> specials, List<Integer> needs) {
        int res = 0;

        for (int i = 0; i < needs.size(); i++) {
            res += price.get(i) * needs.get(i);
        }

        tag:
        for (int i = 0; i < specials.size(); i++) {
            List<Integer> special = specials.get(i);
            List<Integer> needs_temp = new LinkedList<>();
            for (int j = 0; j < needs.size(); j++) {
                int temp = needs.get(j) - special.get(j);
                if (temp < 0) {
                    continue tag;
                } else {
                    needs_temp.add(temp);
                }
            }
            res = Math.min(res, shoppingOffers(price, specials, needs_temp) + special.get(special.size() - 1));
        }

        return res;
    }

    //85
    //执行用时 : 27 ms, 在Maximal Rectangle的Java提交中击败了66.34% 的用户
    //内存消耗 : 46.7 MB, 在Maximal Rectangle的Java提交中击败了80.00% 的用户
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int x = matrix.length;
        if (x == 0) {
            return 0;
        }
        int y = matrix[0].length;
        int[][] res = new int[x][y];

        int result = Integer.MIN_VALUE / 3;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int pt = 1;
                for (int t = 1; t + j < y; t++) {
                    if (matrix[i][j + t] == '0') {
                        break;
                    }
                    pt++;
                }
                int temp = pt;
                for (int tx = 1; tx + i < x; tx++) {
                    if (matrix[i + tx][j] == '0') {
                        break;
                    }
                    int ty = 1;
                    for (; ty + j < y && ty < pt; ty++) {
                        if (matrix[i + tx][j + ty] == '0') {
                            break;
                        }
                    }
                    temp = Math.max(temp, (tx + 1) * ty);
                    pt = ty;
                }
                res[i][j] = temp;
                result = Math.max(temp, result);
            }
        }
        return result < 0 ? 0 : result;
    }

    //403 -- 超时
    public boolean canCross0(int[] stones) {
        int len = stones.length;
        if (len <= 1) {
            return true;
        }
        if (stones[1] != 1) {
            return false;
        }
//        int[] step = new int[len];
//        step[0] = 1;
        Stack<int[]> stack = new Stack<>();
        int[] st1 = new int[2];
        st1[0] = 1;
        st1[1] = 1;
        stack.push(st1);
        int[] next = new int[3];
        next[0] = -1;
        next[1] = 0;
        next[2] = 1;
        while (!stack.empty()) {
            int[] pt = stack.pop();
            if (pt[0] == len - 1) {
                return true;
            }
            for (int ne : next) {
                int np = ne + pt[1];
                if (np <= 0) {
                    continue;
                }
                int nx = np + stones[pt[0]];
                for (int j = pt[0] + 1; j < len; j++) {
                    if (nx < stones[j]) {
                        break;
                    }
                    if (nx == stones[j]) {
                        int[] ns = new int[2];
                        ns[0] = j;
                        ns[1] = np;
                        stack.push(ns);
                        break;
                    }
                }
            }
        }
        return false;
    }

    //403
    //执行用时 : 129 ms, 在Frog Jump的Java提交中击败了40.80% 的用户
    //内存消耗 : 67.8 MB, 在Frog Jump的Java提交中击败了23.91% 的用户
    public boolean canCross1(int[] stones) {
        //先判断一下简单的情况
        if (stones.length < 3 && stones[stones.length - 1] < 2) {
            return true;
        }
        //建立动态规划状态的二维数组
        int[][] dp = new int[stones.length][];
        //建立索引（相比建立全图，空间用的会更小）
        HashMap<Integer, Integer> map = new HashMap<>();
        //开辟二维空间，减少空间利用率，并且为索引赋值
        for (int i = 0; i < stones.length; i++) {
            dp[i] = new int[i];
            map.put(stones[i], i);
        }
        //初始化条件
        dp[1][0] = 1;
        for (int i = 1; i < stones.length - 1; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = dp[i][j] - 1; k >= 0 && k <= dp[i][j] + 1; k++) {
                    if (k != 0 && map.get(stones[i] + k) != null) {
                        dp[map.get(stones[i] + k)][i] = k;
                        if (stones[i] + k == stones[stones.length - 1]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //343 -- 特解
    //执行用时 : 0 ms, 在Integer Break的Java提交中击败了100.00% 的用户
    //内存消耗 : 32.8 MB, 在Integer Break的Java提交中击败了17.16% 的用户
    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int res = 1;
        while (n > 4) {
            n = n - 3;
            res *= 3;
        }
        if (n == 4) {
            res *= 4;
        } else if (n == 3) {
            res *= 3;
        } else if (n == 2) {
            res *= 2;
        }
        return res;
    }

    //343 -- 动态规划
    //执行用时 : 2 ms, 在Integer Break的Java提交中击败了56.56% 的用户
    //内存消耗 : 32.9 MB, 在Integer Break的Java提交中击败了15.42% 的用户
    public int integerBreak1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        //初始化,1,2,3特殊处理
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }


    //88
    //执行用时 : 1 ms, 在Merge Sorted Array的Java提交中击败了99.29% 的用户
    //内存消耗 : 35.6 MB, 在Merge Sorted Array的Java提交中击败了86.11% 的用户
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            nums1[len--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (m >= 0) {
            nums1[len--] = nums1[m--];
        }
        while (n >= 0) {
            nums1[len--] = nums2[n--];
        }
        return;
    }

    //21
    //执行用时 : 2 ms, 在Merge Two Sorted Lists的Java提交中击败了97.76% 的用户
    //内存消耗 : 35.2 MB, 在Merge Two Sorted Lists的Java提交中击败了91.56% 的用户
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode p, q;
        if (l1.val > l2.val) {
            p = l2;
            q = l1;
        } else {
            p = l1;
            q = l2;
        }
        ListNode head = p;
        while (p != null && q != null) {
            while (p.next != null && p.next.val <= q.val) {
                p = p.next;
            }
            if (p.next == null) {
                break;
            }
            ListNode t = q.next;
            q.next = p.next;
            p.next = q;
            q = t;
        }
        p.next = p.next != null ? p.next : q;
        return head;
    }

    //148
    //执行用时 : 927 ms, 在Sort List的Java提交中击败了6.13% 的用户
    //内存消耗 : 38.9 MB, 在Sort List的Java提交中击败了98.74% 的用户
    public ListNode sortList0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = new ListNode(Integer.MIN_VALUE);
        while (head != null) {
            ListNode p = res;
            while (p.next != null) {
                if (p.next.val < head.val) {
                    p = p.next;
                } else {
                    break;
                }
            }
            ListNode t = p.next;
            p.next = head;
            head = head.next;
            p.next.next = t;
        }
        return res.next;
    }

    //148:归并:failed
    public ListNode sortList(ListNode head) {
        return mergesort(head);
    }

    ListNode mergesort(ListNode node) {
        if (node != null || node.next != null) {
            return node;
        }
        ListNode fast = node;//快指针走两步
        ListNode slow = node;//慢指针走一步
        ListNode brek = node;//断点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            brek = slow;
            slow = slow.next;
        }
        brek.next = null;
        ListNode l1 = mergesort(node);
        ListNode l2 = mergesort(slow);
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l2.next, l1);
            return l2;
        }
    }

    //969
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList();
        int N = A.length;

        Integer[] B = new Integer[N];
        for (int i = 0; i < N; ++i) {
            B[i] = i + 1;
        }
        Arrays.sort(B);

        for (int i : B) {
            for (int f : ans) {
                if (i <= f) {
                    i = f + 1 - i;
                }
            }
            ans.add(i);
            ans.add(N--);
        }

        return ans;
    }

    //1024
    //执行用时 : 4 ms, 在Video Stitching的Java提交中击败了33.54% 的用户
    //内存消耗 : 33.8 MB, 在Video Stitching的Java提交中击败了77.78% 的用户
    public int videoStitching(int[][] clips, int T) {
        int len = clips.length;
        videoSort(clips);
        if (clips[0][0] > 0 || clips[len - 1][1] < T) {
            return -1;
        }
        Stack<int[]> stack = new Stack<>();
        int begin = 0;
        boolean isFirst = true;
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (isFirst) {
                for (; i < len; i++) {
                    if (clips[i][0] != 0) {
                        isFirst = false;
                        break;
                    }
                    begin = clips[i][1];
                }
            } else {
                if (clips[i][0] > begin) {
                    return -1;
                }
                int newbegin = begin;
                for (; i < len; i++) {
                    if (clips[i][0] > begin) {
                        break;
                    }
                    newbegin = Math.max(newbegin, clips[i][1]);
                }
                begin = newbegin;
            }
            i--;
            res++;
            if (begin > T) {
                break;
            }
        }
        return res;
    }

    private void videoSort(int[][] clips) {
        int len = clips.length;
        for (int i = 0; i < len; i++) {
            int[] c = clips[len - 1 - i];
            for (int j = 0; j < len - i - 1; j++) {
                if (c[0] < clips[j][0] || (c[0] == clips[j][0] && c[1] < clips[j][1])) {
                    c[0] = c[0] ^ clips[j][0];
                    clips[j][0] = clips[j][0] ^ c[0];
                    c[0] = c[0] ^ clips[j][0];
                    c[1] = c[1] ^ clips[j][1];
                    clips[j][1] = clips[j][1] ^ c[1];
                    c[1] = c[1] ^ clips[j][1];
                }
            }
        }
    }

    //1024 -- 动态规划
    //执行用时 : 4 ms, 在Video Stitching的Java提交中击败了33.54% 的用户
    //内存消耗 : 34.5 MB, 在Video Stitching的Java提交中击败了47.62% 的用户
    public int videoStitching1(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, T + 1);
        dp[0] = 0;
        for (int i = 0; i < T + 1; i++) {
            for (int[] c : clips) {
                if (c[0] <= i && c[1] >= i) {
                    dp[i] = Math.min(dp[i], dp[c[0]] + 1);
                }
            }
        }
        return dp[T] == T + 1 ? -1 : dp[T];
    }

    //1049
    //执行用时 : 2 ms, 在Last Stone Weight II的Java提交中击败了90.48% 的用户
    //内存消耗 : 33.4 MB, 在Last Stone Weight II的Java提交中击败了100.00% 的用户
    public int lastStoneWeightII0(int[] stones) {
        int sum = 0;
        for (int st : stones) {
            sum += st;
        }
        int half = sum >> 1;
        boolean[] existWeight = new boolean[half + 1];
        for (int i = 0; i < existWeight.length; i++) {
            existWeight[i] = false;
        }
        existWeight[0] = true;
        for (int stoneWeight : stones) {
            for (int i = half - stoneWeight; i >= 0; i--) {
                if (existWeight[i]) {
                    existWeight[i + stoneWeight] = true;
                    if (stoneWeight + i == half) {
                        return sum % 2;
                    }
                }
            }
        }
        for (int i = existWeight.length - 1; i > 0; i--) {
            if (existWeight[i]) {
                return sum - 2 * i;
            }
        }
        return Integer.MAX_VALUE;
    }

    //1049
    //执行用时 : 1 ms, 在Last Stone Weight II的Java提交中击败了100.00% 的用户
    //内存消耗 : 34.1 MB, 在Last Stone Weight II的Java提交中击败了100.00% 的用户
    public int lastStoneWeightII1(int[] stones) {
        int sum = 0;
        for (int st : stones) {
            sum += st;
        }
        for (int i = (sum >> 1); ; i--) {
            if (helper(stones, 0, 0, i)) {
                return sum - 2 * i;
            }
        }
    }

    boolean helper(int[] nums, int idx, int sum, int target) {
        if (sum == target) {
            return true;
        }
        if (sum > target) {
            return false;
        }
        if (idx == nums.length) {
            return false;
        }
        return helper(nums, idx + 1, sum + nums[idx], target)
                || helper(nums, idx + 1, sum, target);
    }

    /*初级算法*/

    //从排序数组中删除重复项
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int n = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[n]) {
                continue;
            } else {
                nums[++n] = nums[i];
            }
        }
        return n + 1;
    }

    /*
     * 题目：1291. 顺次数
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :33.4 MB, 在所有 Java 提交中击败了100.00%的用户
     * */
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> resList = new LinkedList<Integer>();
        char[] lowArr = String.valueOf(low).toCharArray();
        int lowA = lowArr[0] - '0';
        final int len = lowArr.length;
        int minNum = lowA;
        int key = 1;
        boolean b = false;
        int pre = 12;
        int p = 2;
        for (int i = 1; i < len; i++) {
            lowA++;
            p++;
            key = key * 10 + 1;
            pre = pre * 10 + p;
            minNum = minNum * 10 + lowA;
            if (lowA > 9) {
                b = true;
            }
            if (p > 9) {
                return resList;
            }
        }
        if (!b) {
            if (minNum < low) {
                minNum += key;
                if (minNum % 10 == 0) {
                    minNum = pre;
                    p++;
                    pre = pre * 10 + p;
                    key = key * 10 + 1;
                }
            }
        } else {
            minNum = pre;
            key = key * 10 + 1;
        }
        while (minNum < high) {
            resList.add(minNum);
            minNum += key;
            if (minNum % 10 == 0) {
                p++;
                minNum = pre > minNum ? pre : pre * 10 + p;
                pre = pre * 10 + p;
                key = key * 10 + 1;
                if (minNum % 10 == 0) {
                    return resList;
                }
            }
        }
        return resList;
    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.20% 的用户
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了30.30% 的用户
     *
     * @param grid 包含非负整数的 m x n 网格
     * @return 最小路径和
     */
    public int minPathSum11(int[][] grid) {
        int n = grid.length;
        if (n == 0) {
            return 0;
        }
        int m = grid[0].length;
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[m - 1];
    }


    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param nums 数组
     * @return 数组中出现次数超过一半的数字
     */
    public int majorityElement11(int[] nums) {
        int key = nums[0], value = 1;
        for (int i = 1; i < nums.length; i++) {
            if (value == 0) {
                key = nums[i];
                value++;
            } else if (key == nums[i]) {
                value++;
            } else {
                value--;
            }
        }
        return key;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * 执行用时：7 ms, 在所有 Java 提交中击败了69.45% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param arr 整数数组 arr
     * @param k   最小的 k 个数
     * @return 最小的 k 个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] res = new int[k];
        if (k >= 0) {
            System.arraycopy(arr, 0, res, 0, k);
        }
        return res;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * 计数排序
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.49% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param arr 整数数组 arr
     * @param k   最小的 k 个数
     * @return 最小的 k 个数
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++;
        }
        // 根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }

    /**
     * 206. 反转链表
     * 反转一个单链表。
     * 执行用时：3 ms, 在所有 Java 提交中击败了5.06% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了5.06% 的用户
     *
     * @param head 链表
     * @return 反转链表
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.add(head);
            head = head.next;
        }
        head = stack.pop();
        ListNode tmp = head;
        while (!stack.empty()) {
            ListNode l = stack.pop();
            tmp.next = l;
            tmp = tmp.next;
        }
        tmp.next = null;
        return head;
    }

    /**
     * 9. 回文数
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 执行用时：10 ms, 在所有 Java 提交中击败了63.19% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了5.14% 的用户
     *
     * @param x 整数
     * @return 回文数
     */
    public boolean isPalindrome(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        int len = chars.length;
        for (int i = 0; 2 * i < len - 1; i++) {
            if (chars[i] != chars[len - i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 994. 腐烂的橘子
     * 在给定的网格中，每个单元格可以有以下三个值之一：
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
     * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
     * 执行用时：3 ms, 在所有 Java 提交中击败了89.32% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了33.33% 的用户
     *
     * @param grid 网格
     * @return 最小分钟数
     */
    public int orangesRotting(int[][] grid) {
        int now = 0;
        int pre = -1;
        int n = grid.length;
        int m = grid[0].length;
        if (n == 1 && m == 1) {
            return grid[0][0] == 1 ? -1 : 0;
        }
        boolean isFresh = false;
        int time = 0;
        while (now != pre) {
            pre = now;
            int[][] tmp = new int[n][m];
            isFresh = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        if (i > 0 && grid[i - 1][j] == 2) {
                            tmp[i][j] = 2;
                            now++;
                        } else if (i < n - 1 && grid[i + 1][j] == 2) {
                            tmp[i][j] = 2;
                            now++;
                        } else if (j > 0 && grid[i][j - 1] == 2) {
                            tmp[i][j] = 2;
                            now++;
                        } else if (j < m - 1 && grid[i][j + 1] == 2) {
                            tmp[i][j] = 2;
                            now++;
                        } else {
                            tmp[i][j] = 1;
                        }
                        isFresh = isFresh || tmp[i][j] == 1;
                    } else {
                        tmp[i][j] = grid[i][j];
                    }
                }
            }
            grid = tmp.clone();
            time++;
        }
        if (isFresh) {
            return -1;
        } else {
            return time - 1;
        }
    }

    /**
     * 1025. 除数博弈
     * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作:
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
     * 执行用时：8 ms, 在所有 Java 提交中击败了8.72% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了10.00% 的用户
     *
     * @param n 数字N
     * @return 爱丽丝是否在游戏中取得胜利
     */
    public boolean divisorGame00(int n) {
        if (n <= 1) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    /**
     * 1103. 分糖果 II
     * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
     * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
     * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
     * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
     * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了33.33% 的用户
     *
     * @param candies    一些糖果
     * @param num_people n个小朋友
     * @return 糖果的最终分发情况
     */
    public int[] distributeCandies(int candies, int num_people) {
        int last = (1 + num_people) * num_people / 2;
        int m = 0;
        int p = num_people * num_people;
        while (candies > m * p + last) {
            candies -= m * p + last;
            m++;
        }
        int[] res = new int[num_people];
        for (int i = 0; i < num_people; i++) {
            res[i] = (m - 1) * m / 2 * num_people + m * (i + 1);
            if (candies > m * num_people + i + 1) {
                res[i] += m * num_people + i + 1;
                candies -= m * num_people + i + 1;
            } else if (candies > 0) {
                res[i] += candies;
                candies = 0;
            }
        }
        return res;
    }

    /**
     * 1104. 二叉树寻路
     * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
     * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
     * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
     * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了51.00% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了33.33% 的用户
     *
     * @param l 点的标号 label
     * @return 路径
     */
    public List<Integer> pathInZigZagTree(int l) {
        if (l == 1) {
            return new ArrayList<>() {
                {
                    add(1);
                }
            };
        }
        int i = 1;
        while (l >= i) {
            i = i << 1;
        }
        int pin = (l - (i >> 1)) / 2 + 1;
        int resnow = (i >> 1) - pin;
        List<Integer> res = pathInZigZagTree(resnow);
        res.add(l);
        return res;
    }

    /**
     * 返回可以被压缩为长度为 N 的不同消息的数量
     *
     * @param N int整型 数据包的总字节数
     * @return int整型
     */
    public int messageCount(int N) {
        // write code here
        int[] dp = new int[N];
        if (N < 5) {
            return 0;
        }
        dp[5] = 1;
        int tmp = 0;
        for (int i = 6; i < N; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
        return dp[N - 1];
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了22.99% 的用户
     *
     * @param root 原始二叉树
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : maxDepth(root, 1);
    }

    public int maxDepth(TreeNode root, int deep) {
        int max = deep;
        if (root.left != null) {
            max = Math.max(max, maxDepth(root.left, deep + 1));
        }
        if (root.right != null) {
            max = Math.max(max, maxDepth(root.right, deep + 1));
        }
        return max;
    }

    /**
     * 410. 分割数组的最大值
     * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了33.33%的用户
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        long l = 0, r = Integer.MAX_VALUE;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (splitArrayCheck(nums, m, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    public boolean splitArrayCheck(int[] nums, int m, long max) {
        int n = 0;
        long now = 0;
        for (int i = 0; i < nums.length; i++) {
            now += nums[i];
            if (now > max) {
                now = nums[i];
                n++;
                if (nums[i] > max) {
                    return false;
                }
            }
        }
        return n < m;
    }

    /**
     * 343. 整数拆分
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了57.14%的用户
     *
     * @param n 正整数
     * @return 拆分之后最大乘积
     */
    public int integerBreak0(int n) {
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        } else if (n == 4) {
            return 4;
        }
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        res *= n;
        return res;
    }

    /**
     * 851. 喧闹和富有
     * 在一组 N 个人（编号为 0, 1, 2, ..., N-1）中，每个人都有不同数目的钱，以及不同程度的安静（quietness）。
     * 为了方便起见，我们将编号为 x 的人简称为 "person x "。
     * 如果能够肯定 person x 比 person y 更有钱的话，我们会说 richer[i] = [x, y] 。注意 richer 可能只是有效观察的一个子集。
     * 另外，如果 person x 的安静程度为 q ，我们会说 quiet[x] = q 。
     * 现在，返回答案 answer ，其中 answer[x] = y 的前提是，在所有拥有的钱不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
     * <p>
     * 执行用时：7 ms, 在所有 Java 提交中击败了97.22% 的用户
     * 内存消耗：48 MB, 在所有 Java 提交中击败了100.00% 的用户
     */
    ArrayList<Integer>[] graph;
    int[] answer;
    int[] quiet;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int N = quiet.length;
        graph = new ArrayList[N];
        answer = new int[N];
        this.quiet = quiet;

        for (int node = 0; node < N; ++node) {
            graph[node] = new ArrayList<Integer>();
        }

        for (int[] edge : richer) {
            graph[edge[1]].add(edge[0]);
        }

        Arrays.fill(answer, -1);

        for (int node = 0; node < N; ++node) {
            dfs(node);
        }
        return answer;
    }

    public int dfs(int node) {
        if (answer[node] == -1) {
            answer[node] = node;
            for (int child : graph[node]) {
                int cand = dfs(child);
                if (quiet[cand] < quiet[answer[node]]) {
                    answer[node] = cand;
                }
            }
        }
        return answer[node];
    }

    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * 执行用时：8 ms, 在所有 Java 提交中击败了12.55% 的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了5.18% 的用户
     *
     * @param num1 第一个字符串
     * @param num2 第二个字符串
     * @return 字符串的和
     */
    public String addStrings(String num1, String num2) {
        int n = num1.length() - 1;
        int m = num2.length() - 1;
        String res = "";
        boolean add = false;
        while (n >= 0 && m >= 0) {
            int a = num1.charAt(n--) - '0' + num2.charAt(m--) - '0';
            if (add) {
                a += 1;
            }
            add = a >= 10;
            res = String.valueOf(a % 10) + res;
        }
        while (n >= 0) {
            int a = num1.charAt(n--) - '0';
            if (add) {
                a += 1;
            }
            add = a >= 10;
            res = String.valueOf(a % 10) + res;
        }
        while (m >= 0) {
            int a = num2.charAt(m--) - '0';
            if (add) {
                a += 1;
            }
            add = a >= 10;
            res = String.valueOf(a % 10) + res;
        }
        if (add) {
            res = String.valueOf(1) + res;
        }
        return res;
    }

    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;

        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        boolean[][] dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        // 再填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }

    /**
     * 207. 课程表
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     * 执行用时：4 ms, 在所有 Java 提交中击败了93.04% 的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了50.50% 的用户
     *
     * @param numCourses 课程数量
     * @param prerequisites 课程关系
     * @return 是否可以学完
     */
    int[] learned;
    List<List<Integer>> pre = new ArrayList<>();
    boolean canFinish = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        learned = new int[numCourses];
        for (int i : learned) {
            List<Integer> tmp = new ArrayList<>();
            pre.add(tmp);
        }
        for (int[] p : prerequisites) {
            pre.get(p[1]).add(p[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            canFinishDFS(i);
            if (!canFinish) {
                return false;
            }
        }
        return canFinish;
    }

    public void canFinishDFS(int key) {
        learned[key] = 1;
        for (int k : pre.get(key)) {
            if (!canFinish) {
                break;
            }
            if (learned[k] == 0) {
                canFinishDFS(k);
            } else if (learned[k] == 1) {
                canFinish = false;
            }
        }
        learned[key] = 2;
    }

    /**
     * 337. 打家劫舍 III
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     * 执行用时：412 ms, 在所有 Java 提交中击败了33.92% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了67.02% 的用户
     *
     * @param root 街道树
     * @return 最高金额
     */
    public int rob3(TreeNode root) {
        return root == null ? 0 : robAss(root);
    }

    public int robAss(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int max = root.val;
        int sonMax = 0;
        int gradsonMax = 0;
        if (root.left != null) {
            TreeNode l = root.left;
            sonMax += robAss(l);
            if (l.left != null) {
                gradsonMax += robAss(l.left);
            }
            if (l.right != null) {
                gradsonMax += robAss(l.right);
            }
        }
        if (root.right != null) {
            TreeNode r = root.right;
            sonMax += robAss(r);
            if (r.left != null) {
                gradsonMax += robAss(r.left);
            }
            if (r.right != null) {
                gradsonMax += robAss(r.right);
            }
        }
        return Math.max(max + gradsonMax, sonMax);
    }

    /**
     * 337. 打家劫舍 III
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     * 执行用时：5 ms, 在所有 Java 提交中击败了35.86% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了47.09% 的用户
     *
     * @param root 街道树
     * @return 最高金额
     */
    public int rob0(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        int len = words.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                String tmp = words[i] + words[j];
                if (palindromePairsIsOK(tmp)) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j);
                    res.add(l);
                }
                tmp = words[j] + words[i];
                if (palindromePairsIsOK(tmp)) {
                    List<Integer> l = new ArrayList<>();
                    l.add(j);
                    l.add(i);
                    res.add(l);
                }
            }
        }
        return res;
    }

    public boolean palindromePairsIsOK(String str) {
        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    /**
     * 100. 相同的树
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了91.42% 的用户
     *
     * @param p p树
     * @param q q树
     * @return p.q树是否相同
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    /**
     * 130. 被围绕的区域
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.14% 的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了82.86% 的用户
     *
     * @param board 二维的矩阵
     */

    public void solve(char[][] board) {
        solveM = board.length;
        if (solveM == 0) {
            return;
        }
        solveN = board[0].length;
        if (solveM <= 2 || solveN <= 2) {
            return;
        }
        for (int i = 0; i < solveM; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = 'T';
                solveDfs(board, i, 0);
            }
            if (board[i][solveN - 1] == 'O') {
                board[i][solveN - 1] = 'T';
                solveDfs(board, i, solveN - 1);
            }
        }
        for (int i = 1; i < solveN; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = 'T';
                solveDfs(board, 0, i);
            }
            if (board[solveM - 1][i] == 'O') {
                board[solveM - 1][i] = 'T';
                solveDfs(board, solveM - 1, i);
            }
        }
        for (int i = 0; i < solveM; i++) {
            for (int j = 0; j < solveN; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
        return;
    }

    private int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int solveM = 0, solveN = 0;

    public void solveDfs(char[][] board, int x, int y) {
        for (int[] d : dir) {
            int tx = x + d[0];
            int ty = y + d[1];
            if (tx < 0 || tx >= solveM || ty < 0 || ty >= solveN) {
                continue;
            }
            if (board[tx][ty] == 'O') {
                board[tx][ty] = 'T';
                solveDfs(board, tx, ty);
            }
        }
    }

    public leetcode.data.Node cloneGraph(leetcode.data.Node node) {
        if (node != null) {
            Set<leetcode.data.Node> set = new HashSet<>();
            leetcode.data.Node res = new leetcode.data.Node();
            cloneGraph(node, res, set);
            return res;
        } else {
            return null;
        }
    }

    public void cloneGraph(leetcode.data.Node node, leetcode.data.Node newNode, Set<leetcode.data.Node> visit) {
        if (node.neighbors != null) {
            if (newNode.neighbors == null) {
                newNode.neighbors = new ArrayList<>();
            }
            for (leetcode.data.Node n : node.neighbors) {
                if (visit.add(n)) {
                    leetcode.data.Node newN = new leetcode.data.Node();
                    newN.val = n.val;
                    newNode.neighbors.add(newN);
                    cloneGraph(node, newNode, visit);
                }
            }
        }
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.54% 的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了63.62% 的用户
     *
     * @param s 字符串
     * @return 字符串是否有效
     */
    public boolean isValid1(String s) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        if (len % 2 != 0) {
            return false;
        }
        char[] cs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : cs) {
            switch (c) {
                case '(', '{', '[' -> {
                    stack.add(c);
                }
                default -> {
                    if (stack.empty()) {
                        return false;
                    }
                    char tmp = stack.pop();
                    switch (c) {
                        case ')' -> {
                            if (tmp != '(') {
                                return false;
                            }
                        }
                        case '}' -> {
                            if (tmp != '{') {
                                return false;
                            }
                        }
                        case ']' -> {
                            if (tmp != '[') {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return stack.empty();
    }

    /**
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.76% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了92.89% 的用户
     *
     * @param root 二叉树
     * @return 是否是平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明: 叶子节点是指没有子节点的节点。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了47.85% 的用户
     *
     * @param root 二叉树
     * @return 二叉树的最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int tmp = Integer.MAX_VALUE;
        if (root.left != null) {
            int l = minDepth(root.left) + 1;
            tmp = Math.min(tmp, l);
        }
        if (root.right != null) {
            int r = minDepth(root.right) + 1;
            tmp = Math.min(tmp, r);
        }
        return tmp;
    }

    /**
     * 6. Z 字形变换
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.11% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了88.70% 的用户
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        char[] cs = s.toCharArray();
        int len = cs.length;
        if (len == 0) {
            return "";
        }
        if (numRows == 1) {
            return s;
        }
        int k = numRows * 2 - 2;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            if (i == 0 || i == numRows - 1) {
                int n = i;
                while (n < len) {
                    res.append(cs[n]);
                    n += k;
                }
            } else {
                int n = i;
                int t = k - i * 2;
                while (n < len) {
                    res.append(cs[n]);
                    if (n + t < len) {
                        res.append(cs[n + t]);
                    }
                    n += k;
                }
            }
        }
        return res.toString();
    }

    /**
     * 459. 重复的子字符串
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     * 执行用时：8 ms, 在所有 Java 提交中击败了92.46% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了61.22% 的用户
     *
     * @param s 字符串
     * @return 是否有重复的子字符串
     */
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len <= 1) {
            return false;
        }
//        boolean isSuccess = false;
        for (int i = 1; i <= len / 2; i++) {
            if (len % i != 0) {
                continue;
            }
            String tmp = s.substring(0, i);
            int n = i;
            boolean isS = true;
            while (n + i <= len) {
                String t = s.substring(n, n + i);
                if (!tmp.equals(t)) {
                    isS = false;
                    break;
                }
                n += i;
            }
            if (isS) {
                return true;
            }
        }
        return false;
    }


    /**
     * 1553. 吃掉 N 个橘子的最少天数
     * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
     * <p>
     * 吃掉一个橘子。
     * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
     * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
     * <p>
     * 每天你只能从以上 3 种方案中选择一种方案。
     * <p>
     * 请你返回吃掉所有 n 个橘子的最少天数。
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了88.30% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了52.96% 的用户
     *
     * @param n 橘子数量
     * @return 最小天数
     */
    public int minDays(int n) {
        if (n <= 1) {
            return n;
        }
        if (minDaysMap.containsKey(n)) {
            return minDaysMap.get(n);
        }
        minDaysMap.put(n, Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3)));
        return minDaysMap.get(n);
    }

    Map<Integer, Integer> minDaysMap = new HashMap<Integer, Integer>();

    /**
     * 491. 递增子序列
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     * 执行用时：57 ms, 在所有 Java 提交中击败了6.31% 的用户
     * 内存消耗：49.4 MB, 在所有 Java 提交中击败了7.67% 的用户
     *
     * @param nums 数组
     * @return 数组的递增子序列
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return new ArrayList<>();
        }
        Map<Integer, List<String>> map = new HashMap<>();
        List<List<Integer>> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            List<List<Integer>> tmp = new ArrayList<>();
            List<String> newS = new ArrayList<>();
            newS.add(String.valueOf(nums[i]));
            for (int j = 0; j < i; j++) {
                if (nums[i] >= nums[j]) {
                    for (String s : map.get(j)) {
                        String ns = s + ',' + nums[i];
                        if (set.add(ns)) {
                            String[] ss = ns.split(",");
                            List<Integer> newTmp = new ArrayList<>();
                            for (String sss : ss) {
                                newTmp.add(Integer.valueOf(sss));
                            }
                            list.add(newTmp);
                        }
                        newS.add(ns);
                    }
                }
            }
            map.put(i, newS);
        }
        return list;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.73% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了76.74% 的用户
     *
     * @param str 字符串
     * @return 字符串转成整数
     */
    public int myAtoi(String str) {
        int index = 0, sign = 1, ans = 0;

        // 1. 判断是否为空
        if (str == null || str.length() == 0) {
            return 0;
        }

        int len = str.length();

        // 2. 移除空格
        while (index < len && str.charAt(index) == ' ') {
            index++;
        }

        if (index == len) {
            // 去掉前面空格以后到了末尾了
            return 0;
        }

        // 3. 处理正负符号
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // 4. 转换数字，避免溢出
        while (index < len) {
            // 判断是否是数字
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) {
                break;
            }

            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            ans = 10 * ans + digit;
            index++;
        }
        return ans * sign;
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 执行用时：1 ms, 在所有 Java 提交中击败了89.00% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了95.69% 的用户
     *
     * @param digits 电话号码
     * @return 电话号码的字母组合
     */
    public List<String> letterCombinations(String digits) {
        String[][] key = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"},
                {"j", "k", "l"}, {"m", "n", "o"}, {"p", "q", "r", "s"},
                {"t", "u", "v"}, {"w", "x", "y", "z"}};
        List<String> res = new ArrayList<>();
        char[] cs = digits.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int tmp = cs[i] - '2';
            List<String> newRes = new ArrayList<>();
            for (String value : key[tmp]) {
                if (res.size() == 0) {
                    newRes.add(value);
                    continue;
                }
                for (String r : res) {
                    newRes.add(r + value);
                }
            }
            res = newRes;
        }
        return res;
    }

    /**
     * 36. 有效的数独
     * <p>
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 执行用时：3 ms, 在所有 Java 提交中击败了56.47% 的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了5.06% 的用户
     *
     * @param board 数独数组
     * @return 是否是有效的数独
     */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            map.put(i, set);
        }
        for (int i = 0; i < 9; i++) {
            Set<Character> x = new HashSet<>();
            Set<Character> y = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.' && !y.add(board[j][i])) {
                    return false;
                }
                if (board[i][j] != '.') {
                    if (!x.add(board[i][j])) {
                        return false;
                    }
                    if (i <= 2) {
                        if (j <= 2) {
                            if (!map.get(0).add(board[i][j])) {
                                return false;
                            }
                        } else if (j <= 5) {
                            if (!map.get(1).add(board[i][j])) {
                                return false;
                            }
                        } else {
                            if (!map.get(2).add(board[i][j])) {
                                return false;
                            }
                        }
                    } else if (i <= 5) {
                        if (j <= 2) {
                            if (!map.get(3).add(board[i][j])) {
                                return false;
                            }
                        } else if (j <= 5) {
                            if (!map.get(4).add(board[i][j])) {
                                return false;
                            }
                        } else {
                            if (!map.get(5).add(board[i][j])) {
                                return false;
                            }
                        }
                    } else {
                        if (j <= 2) {
                            if (!map.get(6).add(board[i][j])) {
                                return false;
                            }
                        } else if (j <= 5) {
                            if (!map.get(7).add(board[i][j])) {
                                return false;
                            }
                        } else {
                            if (!map.get(8).add(board[i][j])) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 36. 有效的数独 ： 数组替代set
     * <p>
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.43% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了69.71% 的用户
     *
     * @param board 数独数组
     * @return 是否是有效的数独
     */
    public boolean isValidSudoku0(char[][] board) {
        boolean[][] bs = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            boolean[] x = new boolean[9];
            boolean[] y = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    int t = board[j][i] - '1';
                    if (y[t]) {
                        return false;
                    } else {
                        y[t] = true;
                    }
                }
                if (board[i][j] != '.') {
                    int t = board[i][j] - '1';
                    if (x[t]) {
                        return false;
                    } else {
                        x[t] = true;
                    }
                    int tmp = i / 3 * 3 + j / 3;
                    if (bs[tmp][t]) {
                        return false;
                    }
                    bs[tmp][t] = true;
                }
            }
        }
        return true;
    }

    /**
     * 48. 旋转图像
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * 说明：
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了56.48% 的用户
     *
     * @param matrix 二维矩阵表示的一个图像
     */
    public void rotate1(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            int t = len % 2 == 0 ? len / 2 : (len + 1) / 2;
            for (int j = 0; j < t; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - j][i];
                matrix[len - 1 - j][i] = matrix[len - 1 - i][len - 1 - j];
                matrix[len - 1 - i][len - 1 - j] = matrix[j][len - 1 - i];
                matrix[j][len - 1 - i] = tmp;
            }
        }
    }

    /**
     * 38. 外观数列
     * <p>
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
     * 执行用时：3 ms, 在所有 Java 提交中击败了48.11% 的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了41.88% 的用户
     *
     * @param n 正整数
     * @return 外观数列
     */
    public String countAndSay(int n) {
        if (n == 0) {
            return "";
        }
        String s = "1";
        for (int i = 0; i < n - 1; i++) {
            StringBuilder sb = new StringBuilder();
            char t = s.charAt(0);
            int nt = 1;
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) == t) {
                    nt++;
                } else {
                    sb.append(nt).append(t);
                    t = s.charAt(j);
                    nt = 1;
                }
            }
            sb.append(nt).append(t);
            s = sb.toString();
        }
        return s;
    }

    /**
     * 49. 字母异位词分组
     * <p>
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * <p>
     * 执行用时：9 ms, 在所有 Java 提交中击败了79.73% 的用户
     * 内存消耗：42.6 MB, 在所有 Java 提交中击败了75.32% 的用户
     *
     * @param strs 字符串数组
     * @return 分组后的字符串
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            String ss = String.valueOf(cs);
            if (map.containsKey(ss)) {
                map.get(ss).add(str);
            } else {
                List<String> li = new ArrayList<>();
                li.add(str);
                map.put(ss, li);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    /**
     * 44. 通配符匹配
     * <p>
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     * <p>
     * 执行用时：17 ms, 在所有 Java 提交中击败了85.61% 的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了5.06% 的用户
     *
     * @param s 字符串
     * @param p 字符模式
     * @return 是否匹配
     */
    public boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray();
        char[] ps = p.toCharArray();
        int sLen = ss.length;
        int pLen = ps.length;
        if (pLen == 0 && sLen == 0) {
            return true;
        }
        if (pLen == 0) {
            return false;
        } else if (sLen == 0) {
            for (char c : ps) {
                if (c != '*') {
                    return false;
                }
            }
            return true;
        }
        //0：失败 1:成功 2:* 3:* == “”
        int[][] dp = new int[sLen][pLen];
        if (ps[0] == ss[0] || ps[0] == '?') {
            dp[0][0] = 1;
        }
        if (ps[0] == '*') {
            dp[0][0] = 2;
        }
        for (int j = 1; j < pLen; j++) {
            if (dp[0][j - 1] == 2) {
                if (ps[j] == '*') {
                    dp[0][j] = dp[0][j - 1];
                } else if (ps[j] == '?' || ps[j] == ss[0]) {
                    dp[0][j] = 1;
                } else {
                    dp[0][j] = 0;
                }
            } else if (dp[0][j - 1] == 1) {
                if ('*' == ps[j]) {
                    dp[0][j] = 3;
                }
            }
        }
        for (int i = 1; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                if (dp[i - 1][j] == 2) {
                    dp[i][j] = 2;
                    continue;
                }
                if (j > 0 && dp[i - 1][j - 1] != 0) {
                    if (ss[i] == ps[j]) {
                        dp[i][j] = 1;
                        continue;
                    }
                    if ('?' == ps[j]) {
                        dp[i][j] = 1;
                        continue;
                    }
                    if ('*' == ps[j]) {
                        dp[i][j] = 2;
                        continue;
                    }
                }
                if ('*' == ps[j]) {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[sLen - 1][pLen - 1] != 0;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * <p>
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * <p>
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：43.4 MB, 在所有 Java 提交中击败了5.15% 的用户
     *
     * @param nums   数组
     * @param target 目标值
     * @return 目标值在排序数组中查找元素的第一个和最后一个位置
     */
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

        return targetRange;
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    /**
     * 12. 整数转罗马数字
     * <p>
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了87.76% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了49.29% 的用户
     *
     * @param num 数字
     * @return 罗马数字
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int nm = num / 1000;
        num -= nm * 1000;
        for (int i = 0; i < nm; i++) {
            sb.append("M");
        }

        if (num >= 900) {
            num -= 900;
            sb.append("CM");
        }

        nm = num / 500;
        num -= nm * 500;
        for (int i = 0; i < nm; i++) {
            sb.append("D");
        }

        if (num >= 400) {
            num -= 400;
            sb.append("CD");
        }

        nm = num / 100;
        num -= nm * 100;
        for (int i = 0; i < nm; i++) {
            sb.append("C");
        }

        if (num >= 90) {
            num -= 90;
            sb.append("XC");
        }

        nm = num / 50;
        num -= nm * 50;
        for (int i = 0; i < nm; i++) {
            sb.append("L");
        }

        if (num >= 40) {
            num -= 40;
            sb.append("XL");
        }

        nm = num / 10;
        num -= nm * 10;
        for (int i = 0; i < nm; i++) {
            sb.append("X");
        }

        if (num >= 9) {
            num -= 9;
            sb.append("IX");
        }

        nm = num / 5;
        num -= nm * 5;
        for (int i = 0; i < nm; i++) {
            sb.append("V");
        }

        if (num >= 4) {
            num -= 4;
            sb.append("IV");
        }

        for (int i = 0; i < num; i++) {
            sb.append("I");
        }

        return sb.toString();
    }

    /**
     * 19. 删除链表的倒数第N个节点
     * <p>
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了34.55% 的用户
     *
     * @param head 链表
     * @param n    N
     * @return 删除倒数第N个结点后的链表
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        ListNode q = p;
        for (int i = 1; i < n; i++) {
            q = q.next;
        }
        if (q.next == null) {
            return head.next;
        }
        q = q.next;
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return head;
    }

    /**
     * 32. 最长有效括号
     * <p>
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了6.07% 的用户
     *
     * @param s 字符串
     * @return 最长有效字符字串长度
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        char[] ss = s.toCharArray();
        int[] dp = new int[len];
        dp[0] = -1;
        int max = 0;
        for (int i = 1; i < len; i++) {
            if ('(' == ss[i]) {
                dp[i] = -1;
                continue;
            }
            if (dp[i - 1] == -1) {
                if (ss[i - 1] == ss[i]) {
                    dp[i] = -1;
                } else {
                    dp[i] = i - 1;
                    if (i - 1 > 0 && dp[i - 2] != -1) {
                        dp[i] = dp[i - 2];
                    }
                    max = Math.max(max, i - dp[i] + 1);
                }
                continue;
            }
            int t = dp[i - 1];
            if (t == 0) {
                dp[i] = -1;
            } else if (ss[t - 1] == ss[i]) {
                dp[i] = -1;
            } else {
                dp[i] = t - 1;
                if (t - 1 > 0 && dp[t - 2] != -1) {
                    dp[i] = dp[t - 2];
                }
                max = Math.max(max, i - dp[i] + 1);
            }
        }
        return max;
    }

    public List<String> findItinerary2(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            if (!findItineraryMap.containsKey(from)) {
                findItineraryMap.put(from, new PriorityQueue<String>());
            }
            findItineraryMap.get(from).offer(to);
        }
        dfs("JFK");
        Collections.reverse(findItineraryItinerary);
        return findItineraryItinerary;
    }

    Map<String, PriorityQueue<String>> findItineraryMap = new HashMap<String, PriorityQueue<String>>();
    List<String> findItineraryItinerary = new LinkedList<String>();

    public void dfs(String curr) {
        while (findItineraryMap.containsKey(curr) && findItineraryMap.get(curr).size() > 0) {
            String tmp = findItineraryMap.get(curr).poll();
            dfs(tmp);
        }
        findItineraryItinerary.add(curr);
    }


    /**
     * 24. 两两交换链表中的节点
     * <p>
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.4 MB, 在所有 Java 提交中击败了60.79% 的用户
     *
     * @param head 链表
     * @return 交换后的链表
     */
    public ListNode swapPairs(ListNode head) {
        ListNode p = head;
        if (p == null) {
            return p;
        }
        ListNode q = head.next;
        if (q == null) {
            return p;
        }
        p.next = q.next;
        q.next = p;
        if (p.next == null) {
            return q;
        }
        ListNode pre = p;
        head = q;
        p = p.next;
        if (p == null) {
            return head;
        }
        q = p.next;
        while (p != null && q != null) {
            p.next = q.next;
            pre.next = q;
            q.next = p;
            pre = p;
            p = p.next;
            if (p == null) {
                break;
            }
            q = p.next;
        }
        return head;
    }

    /**
     * 15. 三数之和
     * <p>
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * 执行用时：736 ms, 在所有 Java 提交中击败了5.00% 的用户
     * 内存消耗：45.3 MB, 在所有 Java 提交中击败了5.00% 的用户
     *
     * @param nums 包含n个整数的数组
     * @return 三数之和集合链表
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break;
            }
            for (int j = len - 1; j > i; j--) {
                if (nums[j] < 0) {
                    break;
                }
                int l = i, r = j;
                int target = 0 - nums[i] - nums[j];
                if (target < nums[i] || target > nums[j]) {
                    continue;
                }
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (mid == i || mid == j) {
                        break;
                    }
                    if (nums[mid] == target) {
                        String key = nums[i] + "," + nums[mid] + "," + nums[j];
                        if (set.add(key)) {
                            List<Integer> re = new ArrayList<>();
                            re.add(nums[i]);
                            re.add(nums[mid]);
                            re.add(nums[j]);
                            res.add(re);
                        }
                        break;
                    }
                    if (nums[mid] < target) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 15. 三数之和
     * <p>
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * 执行用时：31 ms, 在所有 Java 提交中击败了25.80% 的用户
     * 内存消耗：43.9 MB, 在所有 Java 提交中击败了41.11% 的用户
     *
     * @param nums 包含n个整数的数组
     * @return 三数之和集合链表
     */
    public List<List<Integer>> threeSum3(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 841. 钥匙和房间
     * <p>
     * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
     * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
     * 最初，除 0 号房间外的其余所有房间都被锁住。
     * 你可以自由地在房间之间来回走动。
     * 如果能进入每个房间返回 true，否则返回 false。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了39.43% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了62.40% 的用户
     *
     * @param rooms 对应房间中的钥匙
     * @return 是否全访问
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int len = rooms.size();
        boolean[] visited = new boolean[len];
        Stack<Integer> stack = new Stack<>();
        for (int i : rooms.get(0)) {
            stack.push(i);
        }
        int nums = 1;
        visited[0] = true;
        while (!stack.empty()) {
            int tmp = stack.pop();
            if (visited[tmp]) {
                continue;
            }
            visited[tmp] = true;
            for (int r : rooms.get(tmp)) {
                stack.push(r);
            }
            nums++;
            if (nums == len) {
                break;
            }
        }
        return nums == len;
    }

    /**
     * 257. 二叉树的所有路径
     * <p>
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * 执行用时：4 ms, 在所有 Java 提交中击败了60.09% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了57.97% 的用户
     *
     * @param root 二叉树
     * @return 二叉树的所有路径
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root != null) {
            binaryTreePathsAss(root, new Stack<>(), res);
        }
        return res;
    }

    public void binaryTreePathsAss(TreeNode root, Stack<Integer> list, List<String> res) {
        if (root.right == null && root.left == null) {
            StringBuilder sb = new StringBuilder();
            list.forEach(x -> {
                sb.append(x).append("->");
            });
            String tmp = sb.append(root.val).toString();
            res.add(tmp);
            return;
        }
        list.push(root.val);
        if (root.left != null) {
            binaryTreePathsAss(root.left, list, res);
        }
        if (root.right != null) {
            binaryTreePathsAss(root.right, list, res);
        }
        list.pop();
    }

    /**
     * 347. 前 K 个高频元素
     * <p>
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * 执行用时：19 ms, 在所有 Java 提交中击败了36.11% 的用户
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了82.70% 的用户
     *
     * @param nums 非空的整数数组
     * @param k    k
     * @return 前 K 个高频元素
     */
    public int[] topKFrequent0(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.putIfAbsent(n, 1);
            map.put(n, map.get(n) + 1);
        }
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        };
        PriorityQueue<int[]> p = new PriorityQueue<int[]>(comparator);
        for (int key : map.keySet()) {
            int[] tmp = new int[]{map.get(key), key};
            p.add(tmp);
        }
        int[] res = new int[k];
        while (k > 0) {
            k--;
            res[k] = p.poll()[1];
        }
        return res;
    }

    /**
     * 347. 前 K 个高频元素
     * 执行用时：15 ms, 在所有 Java 提交中击败了87.76% 的用户
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了76.25% 的用户
     *
     * @param nums 非空的整数数组
     * @param k    k
     * @return 前 K 个高频元素
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);

        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }

    /**
     * 306. 累加数
     * <p>
     * 累加数是一个字符串，组成它的数字可以形成累加序列。
     * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
     * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了97.12% 的用户
     *
     * @param num 字符串
     * @return 字符串是否是累加数
     */
    public boolean isAdditiveNumber(String num) {
        char[] chars = num.toCharArray();
        int len = chars.length;
        int[] ints = new int[len];
        for (int i = 0; i < len; i++) {
            ints[i] = chars[i] - '0';
        }
        for (int i = 1; i < len; i++) {
            long ta = 0;
            if (ints[0] != 0) {
                for (int t = 0; t < i; t++) {
                    ta = ta * 10 + ints[t];
                }
            }
            long a = ta;
            for (int j = i + 1; j < len; j++) {
                a = ta;
                long tb = 0;
                if (ints[i] != 0) {
                    for (int t = i; t < j; t++) {
                        tb = tb * 10 + ints[t];
                    }
                }
                long b = tb;
                if (a == 0 && b == 0) {
                    boolean f = true;
                    for (int t : ints) {
                        if (t != 0) {
                            f = false;
                            continue;
                        }
                    }
                    if (f) {
                        return true;
                    }
                } else {
                    boolean f = true;
                    b = tb;
                    for (int t = j; t < len; ) {
                        if (ints[t] == 0) {
                            f = false;
                            break;
                        }
                        long tmp = 0;
                        while (tmp < a + b && t < len) {
                            tmp = tmp * 10 + ints[t];
                            t++;
                        }
                        if (tmp != a + b) {
                            f = false;
                            break;
                        } else {
                            a = b;
                            b = tmp;
                        }
                    }
                    if (f) {
                        return f;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 面试题 17.16. 按摩师
     * <p>
     * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
     * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了76.87% 的用户
     *
     * @param nums 预约请求序列
     * @return 最优的预约集合
     */
    public int massage(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[][] dp = new int[len][2];
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    /**
     * 1576. 替换所有的问号
     * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s<var> </var>，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
     * 注意：你 不能 修改非 '?' 字符。
     * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
     * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param s 仅包含小写英文字母和 '?' 字符的字符串
     * @return 替换所有的问号之后的字符串
     */
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len == 1 && chars[0] == '?') {
            return "a";
        } else if (len == 1) {
            return s;
        }
        for (int i = 0; i < len; i++) {
            if (chars[i] == '?') {
                if (i != 0) {
                    if (chars[i - 1] == 'z') {
                        chars[i] = 'a';
                    } else {
                        chars[i] = (char) (chars[i - 1] + 1);
                    }
                    if (i != len - 1 && chars[i] == chars[i + 1]) {
                        if (chars[i] == chars[i + 1]) {
                            if (chars[i + 1] == 'z') {
                                chars[i] = 'a';
                            } else {
                                chars[i] = (char) (chars[i + 1] + 1);
                            }
                        }
                    }
                } else {
                    if (chars[i + 1] == 'z') {
                        chars[i] = 'a';
                    } else {
                        chars[i] = (char) (chars[i + 1] + 1);
                    }
                }
            }
        }
        String res = String.valueOf(chars);
        return res;
    }

    /**
     * 214. 最短回文串
     * <p>
     * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
     * 执行用时：350 ms, 在所有 Java 提交中击败了31.01% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了55.32% 的用户
     *
     * @param s 字符串
     * @return 最短回文串
     */
    public String shortestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int j = len - i - 1;
            int k = 0;
            boolean could = true;
            while (k < j) {
                if (cs[j] != cs[k]) {
                    could = false;
                    break;
                }
                j--;
                k++;
            }
            if (could && k != 0) {
                for (int jj = len - 1; jj > len - i - 1; jj--) {
                    sb.append(cs[jj]);
                }
                sb.append(s);
                return sb.toString();
            }
        }
        return sb.append(s.substring(1, len)).reverse().toString() + s;
    }

    /**
     * 557. 反转字符串中的单词 III
     * <p>
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了88.46% 的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了24.08% 的用户
     *
     * @param s 待反转字符串
     * @return 反转后的字符串
     */
    public String reverseWords00(String s) {
        String[] cs = s.split(" ");
        if (cs.length == 0) {
            return " ";
        }
        StringBuilder sb = new StringBuilder();
        for (String c : cs) {
            sb.append(new StringBuilder(c).reverse());
            sb.append(" ");
        }
        String res = sb.toString();
        return res.substring(0, res.length() - 1);
    }

    /**
     * 486. 预测赢家
     * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
     * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了33.03% 的用户
     *
     * @param nums 数组
     * @return 赢家是否是1
     */
    public boolean PredictTheWinner(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }
        int n = nums.length;
        int[][] dps = new int[n][n];
        //dps[i][i]为玩家一从i到i赢得，肯定只能是nums[i]
        for (int i = 0; i < n; i++) {
            dps[i][i] = nums[i];
        }
        //d=1,其实代表，先算两个数的时候
        for (int d = 1; d < n; d++) {
            //有多少组要比较
            for (int j = 0; j < n - d; j++) {
                //比较j到d+j
                //其实意思就是比较j到d+j时，玩家1，只能选择两端的，
                //玩家一选择了j时，那么玩家二就从j+1到d+j中选最大的。
                //玩家一选了d+j时，那么玩家二就从j到d+j-1中选最大的
                dps[j][d + j] = Math.max(nums[j] - dps[j + 1][d + j], nums[d + j] - dps[j][d + j - 1]);
            }
        }
        //两个玩家相等，玩家一仍然胜利。
        return dps[0][n - 1] >= 0;
    }

    /**
     * 1460. 通过翻转子数组使两个数组相等
     * 给你两个长度相同的整数数组 target 和 arr 。
     * 每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
     * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
     * 执行用时：3 ms, 在所有 Java 提交中击败了78.06% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了97.37% 的用户
     *
     * @param target 数组1
     * @param arr    数组2
     * @return 两个数组是否相等
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        int tlen = target.length;
        int alen = arr.length;
        if (tlen != alen) {
            return false;
        }
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < tlen; i++) {
            if (target[i] != arr[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
     * 滑动窗口！！
     * 给你一个二进制字符串 s 和一个整数 k 。
     * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 True ，否则请返回 False 。
     * 执行用时：158 ms, 在所有 Java 提交中击败了66.34% 的用户
     * 内存消耗：48.7 MB, 在所有 Java 提交中击败了43.86% 的用户
     *
     * @param s 字符串
     * @param k 数字
     * @return 是否全包含
     */
    public boolean hasAllCodes(String s, int k) {
        HashSet<String> set = new HashSet();
        for (int i = 0; i <= s.length() - k; i++) {
            set.add(s.substring(i, i + k));
        }
        return set.size() == (int) Math.pow(2, k);
    }

    /**
     * 1462. 课程安排 IV
     * 打表！！
     * 你总共需要上 n 门课，课程编号依次为 0 到 n-1 。
     * 有的课会有直接的先修课程，比如如果想上课程 0 ，你必须先上课程 1 ，那么会以 [1,0] 数对的形式给出先修课程数对。
     * 给你课程总数 n 和一个直接先修课程数对列表 prerequisite 和一个查询对列表 queries 。
     * 对于每个查询对 queries[i] ，请判断 queries[i][0] 是否是 queries[i][1] 的先修课程。
     * 请返回一个布尔值列表，列表中每个元素依次分别对应 queries 每个查询对的判断结果。
     * 注意：如果课程 a 是课程 b 的先修课程且课程 b 是课程 c 的先修课程，那么课程 a 也是课程 c 的先修课程。
     * <p>
     * 执行用时：34 ms, 在所有 Java 提交中击败了73.02% 的用户
     * 内存消耗：43.1 MB, 在所有 Java 提交中击败了80.21% 的用户
     *
     * @param n             课程数量
     * @param prerequisites 先修课程
     * @param queries       问题：是否是先修课程
     * @return 答案
     */
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] dp = new boolean[n][n];
        for (int[] p : prerequisites) {
            dp[p[0]][p[1]] = true;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] && dp[k][j]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        List<Boolean> ans = new LinkedList<>();
        for (int i = 0; i < queries.length; i++) {
            ans.add(dp[queries[i][0]][queries[i][1]]);
        }
        return ans;
    }

    /**
     * 1463. 摘樱桃 II
     * <p>
     * 给你一个 rows x cols 的矩阵 grid 来表示一块樱桃地。 grid 中每个格子的数字表示你能获得的樱桃数目。
     * 你有两个机器人帮你收集樱桃，机器人 1 从左上角格子 (0,0) 出发，机器人 2 从右上角格子 (0, cols-1) 出发。
     * 请你按照如下规则，返回两个机器人能收集的最多樱桃数目：
     * 从格子 (i,j) 出发，机器人可以移动到格子 (i+1, j-1)，(i+1, j) 或者 (i+1, j+1) 。
     * 当一个机器人经过某个格子时，它会把该格子内所有的樱桃都摘走，然后这个位置会变成空格子，即没有樱桃的格子。
     * 当两个机器人同时到达同一个格子时，它们中只有一个可以摘到樱桃。
     * 两个机器人在任意时刻都不能移动到 grid 外面。
     * 两个机器人最后都要到达 grid 最底下一行。
     * <p>
     * 执行用时：19 ms, 在所有 Java 提交中击败了57.51% 的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了37.50% 的用户
     *
     * @param grid rows x cols 的矩阵 grid
     * @return 最多摘多少个樱桃
     */
    public int cherryPickup00(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //第k行  分别处于i,j位置上 j>=i
        int[][][] dp = new int[n][m][m];
        for (int[][] dpp : dp) {
            for (int[] dppp : dpp) {
                Arrays.fill(dppp, -1);
            }
        }
        dp[0][0][m - 1] = grid[0][0] + grid[0][m - 1];
        int[] dir = {-1, 0, 1};
        int res = 0;
        for (int k = 1; k < n; k++) {
            for (int i = 0; i < m; i++) {
                for (int d : dir) {
                    int preI = i + d;
                    if (preI >= m || preI < 0) {
                        continue;
                    }
                    for (int j = i; j < m; j++) {
                        int tmp = -1;
                        for (int r : dir) {
                            int preJ = j + r;
                            if (preJ >= m || preJ < 0) {
                                continue;
                            }
                            if (preI > preJ) {
                                continue;
                            }
                            tmp = Math.max(dp[k - 1][preI][preJ], tmp);
                        }
                        if (tmp == -1) {
                            dp[k][i][j] = Math.max(-1, dp[k][i][j]);
                            continue;
                        } else {
                            dp[k][i][j] = Math.max(tmp + grid[k][i] + grid[k][j], dp[k][i][j]);
                        }
                        if (i == j) {
                            dp[k][i][j] -= grid[k][i];
                        }
                        if (k == n - 1) {
                            res = Math.max(res, dp[k][i][j]);
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 1450. 在既定时间做作业的学生人数
     * 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
     * 已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
     * 请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i], endTime[i]]（含）的学生人数。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了69.10% 的用户
     *
     * @param startTime 数组 startTime
     * @param endTime   数组 endTime
     * @param queryTime 整数 queryTime
     * @return 在查询时间 queryTime 时正在做作业的学生人数
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int len = startTime.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 1451. 重新排列句子中的单词
     * 「句子」是一个用空格分隔单词的字符串。给你一个满足下述格式的句子 text :
     * 句子的首字母大写
     * text 中的每个单词都用单个空格分隔。
     * 请你重新排列 text 中的单词，使所有单词按其长度的升序排列。如果两个单词的长度相同，则保留其在原句子中的相对顺序。
     * 请同样按上述格式返回新的句子。
     * <p>
     * 执行用时：22 ms, 在所有 Java 提交中击败了98.44% 的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了61.61% 的用户
     *
     * @param text 原句子
     * @return 新句子
     */
    public String arrangeWords(String text) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        String[] strings = text.split(" ");
        strings[0] = strings[0].toLowerCase();
        for (String s : strings) {
            map.computeIfAbsent(s.length(), k -> new ArrayList<>()).add(s);
        }

        for (int i : map.keySet()) {
            priorityQueue.add(i);
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        while (!priorityQueue.isEmpty()) {
            int tmp = priorityQueue.poll();
            List<String> list = map.get(tmp);
            for (String s : list) {
                if (flag) {
                    sb.append(" ");
                } else {
                    flag = true;
                    if (s.length() > 1) {
                        s = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
                    } else {
                        s = s.toUpperCase();
                    }
                }
                sb.append(s);
            }
        }

        return sb.toString();
    }


    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     *     所有数字（包括 target）都是正整数。
     *     解集不能包含重复的组合。
     * 执行用时：4 ms, 在所有 Java 提交中击败了55.43% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了27.43% 的用户
     * @param candidates 无重复元素的数组 candidates
     * @param target 目标数 target
     * @return candidates 中所有可以使数字和为 target 的组合
     */
    public List<List<Integer>> combinationSum0(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(path, candidates, target, 0, 0);
        return combinationSumRes;
    }

    private List<List<Integer>> combinationSumRes = new ArrayList<>();

    private void backtrack(List<Integer> path, int[] candidates, int target, int sum, int begin) {
        if (sum == target) {
            combinationSumRes.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            int rs = candidates[i] + sum;
            if (rs <= target) {
                path.add(candidates[i]);
                backtrack(path, candidates, target, rs, i);
                path.remove(path.size() - 1);
            } else {
                break;
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        //ListNode
        int[] listNodeValue = {1, 2, 2, 4, 5, 3, 2, 1};
        int listNodeLen = listNodeValue.length;
        ListNode head = new ListNode(listNodeValue[0]);
        ListNode listNodeTmp = head;
        for (int i = 1; i < listNodeLen; i++) {
            listNodeTmp.next = new ListNode(listNodeValue[i]);
            listNodeTmp = listNodeTmp.next;
        }

        //TreeNode -1 is null TreeNode;
        int[] treeNodeValue = {3, 2, -1, 3, 1, 3, -1, 2};
        int treeNodeLen = treeNodeValue.length;
        Stack<TreeNode> createTreeNodeStack = new Stack<>();
        TreeNode root = new TreeNode(treeNodeValue[0]);
        createTreeNodeStack.add(root);
        for (int i = 1; i < listNodeLen; i++) {
            TreeNode tmp = createTreeNodeStack.pop();
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
        String[] oneDimensionalStringArray = {"5", "2", "C", "D", "+"};
        int[] oneDimensionalArrayA = {1, 2, 3, 3};
        int[] oneDimensionalArrayB = {5, 2, 2, 5, 3, 5};
        int[][] twoDimensionalArrayA = {{1, 3,}, {0, 2}, {1, 3}, {0, 2}};
        int[][] twoDimensionalArrayB = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        char[] oneDimensionalCharArray = {'A', 'B'};
        char[][] twoDimensionalCharArray = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        //List<List<Integer>>
        int[][] listListIntegerArray = {{4, 14, 24, 34, 40}, {12, 14, 25, 38, 41}, {9, 19, 20, 26, 50}};
        List<List<Integer>> integerListList = new LinkedList();
        for (int[] listIntegerArray : listListIntegerArray) {
            List<Integer> collect = Arrays.stream(listIntegerArray).boxed().collect(Collectors.toList());
            integerListList.add(collect);
        }

        //List<List<String>>
        String[][] listListStringArray = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<List<String>> stringListList = new LinkedList();
        for (String[] listStringArray : listListStringArray) {
            List<String> collect = Arrays.stream(listStringArray).collect(Collectors.toList());
            stringListList.add(collect);
        }

        System.out.print(solution.minTime(oneDimensionalArrayA, 2));
    }

    class Pair implements Comparable<Pair> {
        public int x, y, val;

        public Pair(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.val > o.val) {
                return -1;
            }
            if (this.val < o.val) {
                return 1;
            }
            return 0;
        }
    }
}



