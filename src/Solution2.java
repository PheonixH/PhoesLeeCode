
import java.lang.reflect.Array;
import java.util.*;

import datestruct.ListNode;
import data.Node;
import datestruct.TreeNode;

import javax.swing.text.MutableAttributeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: PhoesLeeCode
 * @Package: PACKAGE_NAME
 * @ClassName: Solution2
 * @Description:
 * @Author: Pheonix
 * @CreateDate: 2019/4/25 15:05
 * @Version: 1.0
 */
public class Solution2 {
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
    public boolean canCross(int[] stones) {
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
    public int minPathSum(int[][] grid) {
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
    public int majorityElement(int[] nums) {
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
    public int rob(TreeNode root) {
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

    public Node cloneGraph(Node node) {
        if (node != null) {
            Set<Node> set = new HashSet<>();
            Node res = new Node();
            cloneGraph(node, res, set);
            return res;
        } else {
            return null;
        }
    }

    public void cloneGraph(Node node, Node newNode, Set<Node> visit) {
        if (node.neighbors != null) {
            if (newNode.neighbors == null) {
                newNode.neighbors = new ArrayList<>();
            }
            for (Node n : node.neighbors) {
                if (visit.add(n)) {
                    Node newN = new Node();
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
    public boolean isValid(String s) {
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
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        memo.put(n, Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3)));
        return memo.get(n);
    }

    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

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
    public void rotate(int[][] matrix) {
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

    public List<String> findItinerary(List<List<String>> tickets) {
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
    public List<List<Integer>> threeSum(int[] nums) {
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
    public List<List<Integer>> threeSum0(int[] nums) {
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
     *
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * 执行用时：4 ms, 在所有 Java 提交中击败了60.09% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了57.97% 的用户
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

    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        //ListNode
        int[] listNodeValue = {1, 2, 3, 4};
        int listNodeLen = listNodeValue.length;
        ListNode head = new ListNode(listNodeValue[0]);
        ListNode listNodeTmp = head;
        for (int i = 1; i < listNodeLen; i++) {
            listNodeTmp.next = new ListNode(listNodeValue[i]);
            listNodeTmp = listNodeTmp.next;
        }

        //TreeNode -1 is null TreeNode;
        int[] treeNodeValue = {2, -1, 3, -1, 4, -1, 5, -1, 6};
        List<TreeNode> createTreeNodeStack = new ArrayList<>();
        TreeNode root = new TreeNode(treeNodeValue[0]);
        createTreeNodeStack.add(root);
        for (int i = 1; i < listNodeLen; i++) {
            TreeNode tmp = createTreeNodeStack.remove(0);
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
        String[] oneDimensionalStringArray = {"eat", "tea", "tan", "ate", "nat", "bat"};
        int[] oneDimensionalArrayA = {93997, 2877, -93018, -76995, -70679};
        int[] oneDimensionalArrayB = {5, 2, 2, 5, 3, 5};
        int[][] twoDimensionalArrayA = {{0, 1}};
        int[][] twoDimensionalArrayB = {
                {3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}
        };
        char[] oneDimensionalCharArray = {'A', 'B'};
        char[][] twoDimensionalCharArray = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};


        //List<List<Integer>>
        int[][] listListIntegerArray = {{4, 14, 24, 34, 40}, {12, 14, 25, 38, 41}, {9, 19, 20, 26, 50}};
        List<List<Integer>> integerListList = new LinkedList();
        for (int[] listIntegerArray : listListIntegerArray) {
            List<Integer> collect = Arrays.stream(listIntegerArray).boxed().collect(Collectors.toList());
            integerListList.add(collect);
        }

        //List<List<String>>
        String[][] listListStringArray = {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};
        //{{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<List<String>> stringListList = new LinkedList();
        for (String[] listStringArray : listListStringArray) {
            List<String> collect = Arrays.stream(listStringArray).collect(Collectors.toList());
            stringListList.add(collect);
        }

        System.out.println(solution.maxValueAfterReverse(oneDimensionalArrayA));
        return;
    }

}
