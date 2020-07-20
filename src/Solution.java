import datestruct.Worker;

import java.util.*;
import java.util.stream.Collectors;

import datestruct.*;

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
//        Set<datestruct.ListNode> set = new HashSet<>();
//        Set<data.ListNode> set = new HashSet<>();
//        set.add(head);
//        datestruct.ListNode p = head;
//        data.ListNode p = head;
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
    class Solution {
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
        int[] oneDimensionalArrayA = {11, 2, 13, 6, 4, 5, 2, 11, 11};
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

        System.out.print(solution.smallerNumbersThanCurrent0(oneDimensionalArrayA));
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



