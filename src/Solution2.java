
import java.lang.reflect.Array;
import java.util.*;

import datestruct.ListNode;

import javax.swing.text.MutableAttributeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    public int calculateMinimumHP(int[][] dungeon) {
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
                if (minNum%10 == 0){
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



    public static void main(String[] argc) {
        Solution2 solution = new Solution2();
//        List<Integer> p1 = new ArrayList<>();
//        int[] brr = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
//        for (int i : brr) {
//            p1.add(i);
//        }
//        List<Integer> p3 = new ArrayList<>();
//        int[] drr = {3, 2};
//        for (int i : drr) {
//            p3.add(i);
//        }
//        List<List<Integer>> p2 = new ArrayList<>();
//        int[][] crr = {{3, 0, 5}, {1, 2, 10}};
//        for (int i = 0; i < crr.length; i++) {
//            List<Integer> pp = new ArrayList<>();
//            for (int ii : crr[i]) {
//                pp.add(ii);
//            }
//            p2.add(pp);
//        }
//        char[][] map = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };
//        System.out.print(solution.canCross(brr));
//        int[] nums1 = {1, 3, 3, 7, 8, 8};
//        int[] nums2 = {2, 5, 6};
//        int[][] frr = {{0, 0}, {9, 9}, {2, 10}, {0, 3}, {0, 5}, {3, 4}, {6, 10}, {1, 2}, {4, 7}, {5, 6}};
//        ListNode p1 = new ListNode(-7);
//        ListNode p2 = new ListNode(-1);
//        ListNode p3 = new ListNode(-10);
//        ListNode q1 = new ListNode(9);
//        ListNode q2 = new ListNode(-4);
//        ListNode q3 = new ListNode(1);
//        ListNode q4 = new ListNode(6);
//        ListNode q5 = new ListNode(2);
//        p1.next = p2;
//        p2.next = p3;
//        p3.next = q1;
//        q1.next = q2;
//        q2.next = q3;
//        q3.next = q4;
//        q4.next = q5;
//        solution.sortList(p1);
        List<Integer> r = solution.sequentialDigits(28932835,
                733240848);
        for (int k : r) {
            System.out.println(k);
        }
    }
}
