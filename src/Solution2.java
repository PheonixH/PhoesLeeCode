import java.lang.reflect.Array;
import java.util.*;

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
    public int integerBreak1(int n){
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


    public static void main(String[] argc) {
        Solution2 solution = new Solution2();
        List<Integer> p1 = new ArrayList<>();
        int[] brr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 2333};
        for (int i : brr) {
            p1.add(i);
        }
        List<Integer> p3 = new ArrayList<>();
        int[] drr = {3, 2};
        for (int i : drr) {
            p3.add(i);
        }
        List<List<Integer>> p2 = new ArrayList<>();
        int[][] crr = {{3, 0, 5}, {1, 2, 10}};
        for (int i = 0; i < crr.length; i++) {
            List<Integer> pp = new ArrayList<>();
            for (int ii : crr[i]) {
                pp.add(ii);
            }
            p2.add(pp);
        }
        char[][] map = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.print(solution.canCross(brr));
    }
}
