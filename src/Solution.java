import com.sun.source.tree.Tree;
import datestruct.ListNode;
import datestruct.Node;
import datestruct.TreeNode;
import datestruct.Worker;

import java.util.*;

import static java.lang.Math.floorMod;
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
            if (!sub.isEmpty()) return true;
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
        if (word.equals("")) {
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
//        set.add(head);
//        datestruct.ListNode p = head;
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
            case 0:
                return x + y;
            case 1:
                return x - y;
            case 2:
                return x * y;
            case 3:
                return x / y;
            case 4:
                return y / x;
            case 5:
                return y - x;
        }
        return 0;
    }

    //464
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
        if (s.equals("")) {
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
    public String removeDuplicateLetters0(String s) {
        if (s.equals("")) {
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
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp =  t - t % 100 + t % 100 / 10;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 5: {
                    int newp = t - t / 10000 % 10 * 9990;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp =  t - t / 100 % 10 * 90;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t + t % 10 * 9;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 4: {
                    int newp = t - t / 100000 * 99900;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t + t / 10 % 10 * 90;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 3: {
                    int newp = t - t % 100000 + t % 1000 + t % 100000 / 10000 * 1000;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp =  t - t % 10 + t % 10 * 1000;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 2: {
                    int newp =  t - t / 100000 * 90000;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp = t + t % 10000 / 1000 * 9000;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp =  t + t % 100 / 10 * 9990;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
                case 1: {
                    int newp =  t + t / 10000 * 90000;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    newp =  t - t / 100 % 10 * 100 + t / 100 % 10 * 100000;
                    if(!set.contains(newp)) {
                        set.add(newp);
                        q.add((k + 1) * 1000000 + newp);
                    }
                    break;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode p = new ListNode(1);
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(3);
        ListNode p3 = new ListNode(4);
        ListNode p4 = new ListNode(5);
        ListNode p5 = new ListNode(6);
        ListNode p6 = new ListNode(7);
        ListNode p7 = new ListNode(8);
        p.next = p1;
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;
        p7.next = p2;
        TreeNode t = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t.left = t1;
        t.right = t2;
        t1.left = t3;
        t3.left = t4;
        t3.right = t5;
        solution.postorderTraversal(t1);
        String[] strings = {".#@..", "#.##.", ".#...", "A...#", ".#.#a"};
        String[] strings2 = {".#.#..#.b...............#.#..#", ".#..##.........#......d.......", "..#...e.#.##....##.....#.....#",
                "..#..#.#.#.##..........#.....#", "...#...##....#.....#..........", "#........###....#..#.........f",
                "...............#......#...#...", "..........##.#...#.E..#......#", ".#...##...#.##.D....##..#.....",
                ".......#...........#....#..##.", "...#..........##.....#.......#", ".F#....#......#...............",
                "..##.#.#.....#..##...#.#.....#", ".............##..##..#.#......", "#..@..#.#.......#..........#..",
                ".........##..................#", ".#.......##...##..#.......#...", ".......#.#...A.a......#.##.#..",
                ".......#......##..#.###.#.....", ".##.#....##...#.#.....#.#.....", ".#.....#.c..#.....#......#..##",
                "##.....##........B.#.......#.#", ".....#...#....#..##...........", "#.#.##.#....#.#...............",
                ".#.#..#.####............#.....", "#.#..........###.#........#...", "..#..#.........#.......#..#.##",
                "..#..#C#...............#......", ".........#.##.##......#.#.....", "..#........##.#..##.#.....#.#."};
        int[] arr = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        int[] brr = {4, 8, 2, 2, 7};
        int[][] crr = {{3, 1}, {1, 1}, {0, 1}, {2, 1}, {3, 3}, {3, 2}, {0, 2}, {2, 3}};
//        System.out.print(solution.rotate(arr,9));
        int[][] ins = {
                {1, 3, 4, 0, 3, 2},
                {4, 1, 2, 7, 1, 3},
                {3, 4, 2, 1, 4, 1},
                {0, 5, 3, 6, 1, 5}
        };
        int[][] ints = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        int[][] is = {
                {0, 1, 4},
                {5, 2, 3}
        };
        char[][] boards = {};
        System.out.print(solution.slidingPuzzle(is));
    }

}

