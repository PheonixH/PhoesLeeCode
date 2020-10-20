package leetcode;

import java.util.*;

import leetcode.datestruct.TreeNode;

/**
 * PhoesLeeCode
 * 18-12-16 上午10:28
 * ${description}
 *
 * @author pheonix
 * @since
 **/
public class Games {


    // 周赛 9-20
    public String reorderSpaces(String text) {
        int n1 = 0, n2 = 0;
        char[] chars = text.toCharArray();
        boolean b = true;
        for (char c : chars) {
            if (c == ' ') {
                n1++;
                b = true;
            } else {
                if (b) {
                    n2++;
                }
                b = false;
            }
        }
        int last = n1;
        if (n2 > 1) {
            last = n1 % (n2 - 1);
            n1 = n1 / (n2 - 1);
        } else {
            n1 = 0;
        }
        String[] strings = text.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        int t = 1;
        for (String s : strings) {
            if (!"".equals(s)) {
                stringBuilder.append(s);
                if (t != n2) {
                    for (int i = 0; i < n1; i++) {
                        stringBuilder.append(" ");
                    }
                }
                t++;
            }
        }
        for (int i = 0; i < last; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }


    public int maxUniqueSplit(String s) {
        StringBuilder sb = new StringBuilder(s);
        String afterreverse = sb.reverse().toString();
        return Math.max(maxUniqueSplitAss(s), maxUniqueSplitAss(afterreverse));
    }

    public int maxUniqueSplitAss(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int t = 0;
        Set<String> strings = new HashSet<>();
        while (t < len) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chars[t++]);
            while (strings.contains(stringBuilder.toString())) {
                if (t < len) {
                    stringBuilder.append(chars[t++]);
                } else {
                    break;
                }
            }
            strings.add(stringBuilder.toString());
        }
        return strings.size();
    }

    public int maxProductPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long[][][] dp = new long[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    if (j == 0) {
                        dp[0][0][0] = grid[0][0];
                        dp[0][0][1] = grid[0][0];
                        continue;
                    }
                    dp[i][j][0] = Math.min(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j]);
                    dp[i][j][1] = Math.max(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j]);
                    continue;
                }
                if (j == 0) {
                    dp[i][j][0] = Math.min(dp[i - 1][j][0] * grid[i][j], dp[i - 1][j][1] * grid[i][j]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][0] * grid[i][j], dp[i - 1][j][1] * grid[i][j]);
                    continue;
                }
                long t1 = Math.min(dp[i - 1][j][0] * grid[i][j], dp[i - 1][j][1] * grid[i][j]);
                long t2 = Math.max(dp[i - 1][j][0] * grid[i][j], dp[i - 1][j][1] * grid[i][j]);
                long t3 = dp[i][j][0] = Math.min(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j]);
                long t4 = Math.max(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j]);
                dp[i][j][0] = Math.min(t1, t3);
                dp[i][j][1] = Math.max(t2, t4);
            }
        }
        return dp[n - 1][m - 1][1] >= 0 ? (int) (dp[n - 1][m - 1][1] % 1000000007) : -1;
    }

    public int maxProductPath2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    if (j == 0) {
                        dp[0][0][0] = grid[0][0];
                        dp[0][0][1] = grid[0][0];
                        continue;
                    }
                    dp[i][j][0] = Math.min(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j]);
                    dp[i][j][1] = Math.max(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j]);
                    continue;
                }
                if (j == 0) {
                    dp[i][j][0] = Math.min(dp[i - 1][j][0] * grid[i][j], dp[i - 1][j][1] * grid[i][j]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][0] * grid[i][j], dp[i - 1][j][1] * grid[i][j]);
                    continue;
                }
                int t1 = Math.min(dp[i - 1][j][0] * grid[i][j], dp[i - 1][j][1] * grid[i][j]);
                int t2 = Math.max(dp[i - 1][j][0] * grid[i][j], dp[i - 1][j][1] * grid[i][j]);
                int t3 = dp[i][j][0] = Math.min(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j]);
                int t4 = Math.max(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j]);
                dp[i][j][0] = Math.min(t1, t3);
                dp[i][j][1] = Math.max(t2, t4);
            }
        }
        return dp[n - 1][m - 1][1] >= 0 ? dp[n - 1][m - 1][1] % 1000000007 : -1;
    }


    public int connectTwoGroups(List<List<Integer>> cost) {
        int nArr = cost.size();
        int nBrr = cost.get(0).size();
        int res = 0;
        List<List<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < nArr; i++) {
            arr.add(new ArrayList<>());
        }
        int[][] brr = new int[nBrr][2];
        for (int i = 0; i < nBrr; i++) {
            brr[i][1] = Integer.MAX_VALUE;
        }
        int[] crr = new int[nBrr];
        for (int i = 0; i < nArr; i++) {
            int tmp = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < nBrr; j++) {
                int t = cost.get(i).get(j);
                if (min > t) {
                    min = t;
                    tmp = j;
                }
                if (brr[j][1] > t) {
                    brr[j][1] = t;
                    brr[j][0] = i;
                }
            }
            res += min;
            arr.get(i).add(tmp);
            crr[tmp]++;
        }
        for (int i = 0; i < nBrr; i++) {
            if (crr[i] == 0) {
                res += brr[i][1];
                arr.get(brr[i][0]).add(i);
                crr[i]++;
            }
        }
        int[] drr = new int[nBrr];
        for (int i = 0; i < nArr; i++) {
            List<Integer> list = arr.get(i);
            if (list.size() > 1) {
                for (int j : list) {
                    if (crr[j] > 1) {
                        drr[j]++;
                        res -= cost.get(i).get(j);
                    }
                }
            }
        }
        for (int i = 0; i < nBrr; i++) {
            if (drr[i] != 0 && drr[i] == crr[i]) {
                res += brr[i][1];
            }
        }
        return res;
    }


    public int minOperations(String[] logs) {
        int deep = 0;
        for (String str : logs) {
            if ("../".equals(str)) {
                deep = Math.max(deep - 1, 0);
            } else if ("./".equals(str)) {
                continue;
            } else {
                deep++;
            }
        }
        return deep;
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int max = 0;
        int maxI = -1;
        int now = 0;
        int tmp = 0;
        int i = 0;
        while (now > 0 || i < customers.length) {
            if (i < customers.length) {
                now += customers[i];
            }
            tmp = tmp - runningCost;
            if (now >= 4) {
                tmp += 4 * boardingCost;
                now -= 4;
            } else {
                tmp += now * boardingCost;
                now = 0;
            }
            if (tmp > max) {
                max = tmp;
                maxI = i + 1;
            }
            i++;
        }
        return maxI;
    }

    public int maximumRequests(int n, int[][] requests) {
        int[] change = new int[n];
        int len = requests.length;
        List<Map<Integer, Integer>> lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lists.add(new HashMap<>());
        }
        for (int i = 0; i < len; i++) {
            int[] tmp = requests[i];
            change[tmp[0]]--;
            change[tmp[1]]++;
            Map<Integer, Integer> map = lists.get(0);
            map.putIfAbsent(tmp[1], 0);
            int t = map.get(tmp[1]) + 1;
            map.put(tmp[1], t);
        }
        boolean[] visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (change[i] == 0) {
                continue;
            }
            Stack<int[]> stack = new Stack<>();
            int p = i;
            while (true) {
                Map<Integer, Integer> map = lists.get(i);
                for (int j : map.keySet()) {
                    if (change[i] > 0) {
                    }
                }
            }
        }
        return 0;
    }

    // 2020-10-18 第211周赛
    public int maxLengthBetweenEqualCharacters(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j > i; j--) {
                if (chars[i] == chars[j]) {
                    max = Math.max(max, j - i - 1);
                    break;
                }
            }
        }
        return max;
    }

    public String findLexSmallestString(String s, int a, int b) {
        char[] chars = s.toCharArray();
        int len = s.length();
        int[] charsValue = new int[len];
        if (b % 2 == 0) {
            for (int i = 0; i < len; i++) {
                if (i % 2 == 0) {
                    charsValue[i] = chars[i] - '0';
                } else {
                    boolean[] booleans = new boolean[10];
                    int min = chars[i] - '0';
                    int tmp = chars[i] - '0';
                    while (!booleans[tmp]) {
                        booleans[tmp] = true;
                        tmp = (tmp + a) % 10;
                        min = Math.min(tmp, min);
                    }
                    charsValue[i] = min;
                }
            }
        } else {
            for (int i = 0; i < len; i++) {
                boolean[] booleans = new boolean[10];
                int min = chars[i] - '0';
                int tmp = chars[i] - '0';
                while (!booleans[tmp]) {
                    booleans[tmp] = true;
                    tmp = (tmp + a) % 10;
                    min = Math.min(tmp, min);
                }
                charsValue[i] = min;
            }
        }
        int min = Integer.MAX_VALUE;
        String res = "";
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i = i + b) {
            int tmp = 0;
            String str = "";
            int key = i;
            int time = 0;
            while (time < len) {
                str = str + charsValue[key];
                tmp = tmp * 10 + charsValue[key];
                key = (key + 1) % len;
                time++;
            }
            if (set.add(tmp)) {
                if (min > tmp) {
                    min = tmp;
                    res = str;
                }
            }
        }
        return String.valueOf(min);
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        };
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(comparator);

        int len = ages.length;

        for (int i = 0; i < ages.length; i++) {
            int[] tmp = new int[]{scores[i], ages[i]};
            priorityQueue.add(tmp);
        }

        int[][] arr = new int[ages.length][2];
        for (int i = 0; i < ages.length; i++) {
            arr[i] = priorityQueue.poll();
        }

        boolean[][] booleans = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
//            booleans[i][i] = true;
            for (int j = 0; j < i; j++) {
                if (arr[j][0] >= arr[i][0] && arr[j][1] < arr[i][1]) {
                    booleans[i][j] = true;
                    booleans[j][i] = true;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            int tmp = 0;
            for (int j = 0; j < len; j++) {
                if (booleans[i][j]) {
                    tmp += arr[j][0];
                }
            }
            max = Math.max(max, tmp);
        }

        return max;
    }
    /**
     * 5545. 无矛盾的最佳球队
     * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
     * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
     * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
     *
     * 执行用时：63 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了100.00% 的用户
     * @param scores 分数
     * @param ages 年龄
     * @return 最高值
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        int len = scores.length;
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        };

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(comparator);
        for (int i = 0; i < len; i++) {
            priorityQueue.add(new int[]{ages[i], scores[i]});
        }

        int[][] data = new int[len + 1][2];
        for (int i = 1; i < len + 1; i++) {
            data[i] = priorityQueue.poll();
        }

        //0: pick, 1: unpick
        int[] dp = new int[len + 1];
        dp[1] = data[1][1];

        int ans = data[1][0];

        for (int i = 2; i <= len; i++) {
            int j = i;
            int tmp = 0;
            while (j > 0) {
                j--;
                if (data[i][0] > data[j][0] && data[i][1] < data[j][1]) {
                    continue;
                }
                tmp = Math.max(tmp, dp[j]);
            }
            dp[i] = tmp + data[i][1];
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }


}
