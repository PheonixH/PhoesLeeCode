package leetcode;

import Template.BinaryIndexedTree;
import Template.UnionFind;
import leetcode.dataStruct.ListNode;

import java.util.*;

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

    /**
     * 5545. 无矛盾的最佳球队
     * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
     * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
     * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
     * <p>
     * 执行用时：63 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param scores 分数
     * @param ages   年龄
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

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int[] arr = new int[26];
        char[] chars = keysPressed.toCharArray();
        int pre = 0;
        int maxI = 0;
        char maxKey = chars[0];
        for (int i = 0; i < releaseTimes.length; i++) {
            arr[chars[i] - 'a'] = Math.max(arr[chars[i] - 'a'], releaseTimes[i] - pre);
            pre = releaseTimes[i];
            if (arr[maxI] < arr[chars[i] - 'a']) {
                maxI = chars[i] - 'a';
                maxKey = chars[i];
            } else if (arr[maxI] == arr[chars[i] - 'a']) {
                maxKey = maxI < (chars[i] - 'a') ? chars[i] : maxKey;
            }
        }
        return maxKey;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = nums.length;
        int m = l.length;
        List<Boolean> res = new ArrayList<>();
        for (int j = 0; j < m; j++) {
            if (l[j] == r[j] - 1) {
                res.add(true);
                continue;
            }
            int[] arr = Arrays.copyOfRange(nums, l[j], r[j] + 1);
            Arrays.sort(arr);
            boolean b = true;
            int k = arr[1] - arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != arr[i - 1] + k) {
                    b = false;
                    break;
                }
            }
            res.add(b);
        }
        return res;
    }

    public int minimumEffortPath(int[][] heights) {
        minimumEffortPathSet.add("0,0");
        minimumEffortPathBFS(heights, 0, 0, 0);
        return minimumEffortPathRes;
    }

    int minimumEffortPathRes = Integer.MAX_VALUE;
    int[][] minimumEffortPathDirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    Set<String> minimumEffortPathSet = new HashSet<>();

    private void minimumEffortPathBFS(int[][] heights, int x, int y, int now) {
        if (x == heights.length - 1 && y == heights[0].length - 1) {
            minimumEffortPathRes = Math.min(minimumEffortPathRes, now);
            return;
        }
        for (int[] arr : minimumEffortPathDirs) {
            int newX = arr[0] + x;
            int newY = arr[1] + y;
            if (newX >= heights.length || newX < 0 || newY >= heights[0].length || newY < 0) {
                continue;
            }
            String key = newX + "," + newY;
            if (minimumEffortPathSet.contains(key)) {
                continue;
            }
            int newNow = Math.abs(heights[newX][newY] - heights[x][y]);
            if (newNow > minimumEffortPathRes) {
                continue;
            }
            newNow = Math.max(now, newNow);

            minimumEffortPathSet.add(key);
            minimumEffortPathBFS(heights, newX, newY, newNow);
            minimumEffortPathSet.remove(key);
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        Map<Integer, List<int[]>> map = new TreeMap<>();
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map.containsKey(matrix[i][j])) {
                    List<int[]> tmpList = map.get(matrix[i][j]);
                    tmpList.add(new int[]{i, j});
                } else {
                    List<int[]> tmpList = new ArrayList<>();
                    tmpList.add(new int[]{i, j});
                    map.put(matrix[i][j], tmpList);
                }
            }
        }

        int[][] res = new int[n][m];
        int[] row = new int[n];
        int[] rowMax = new int[n];
        Arrays.fill(rowMax, -1);
        int[] col = new int[m];
        int[] colMax = new int[n];
        Arrays.fill(colMax, -1);

        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            int key = (Integer) iterator.next();
            List<int[]> tmpList = map.get(key);
            for (int[] point : tmpList) {
                int x = point[0];
                int y = point[1];
                int max = 0;
                if (rowMax[x] != -1 && matrix[x][rowMax[x]] < matrix[x][y]) {
                    max = Math.max(max, row[x] + 1);
                } else {
                    max = Math.max(max, row[x]);
                }
                if (colMax[y] != -1 && matrix[colMax[y]][y] < matrix[x][y]) {
                    max = Math.max(max, col[y] + 1);
                } else {
                    max = Math.max(max, col[y]);
                }
                if (max == 0) {
                    max = 1;
                }
                res[x][y] = max;
                if (row[x] < max) {
                    row[x] = max;
                    rowMax[x] = y;
                }
                if (col[y] < max) {
                    col[y] = max;
                    colMax[y] = x;
                }
            }
        }
        return res;
    }

    public int[] frequencySort(int[] nums) {
        int len = nums.length;
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o1[1] - o2[1];
            }
        };
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(comparator);

        Map<Integer, int[]> map = new HashMap<>();
        for (int n : nums) {
            map.putIfAbsent(n, new int[]{n, 1});
            int[] tmp = map.get(n);
            tmp[1]++;
            map.put(n, tmp);
        }

        for (int k : map.keySet()) {
            priorityQueue.add(map.get(k));
        }

        int[] res = new int[len];
        int i = 0;
        while (i < len) {
            int[] tmp = priorityQueue.poll();
            int j = 0;
            while (j < tmp[1]) {
                res[i++] = tmp[0];
                j++;
            }
        }
        return res;
    }

    public int maxWidthOfVerticalArea(int[][] points) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int[] p : points) {
            priorityQueue.add(p[0]);
        }

        int max = 0;
        int pre = priorityQueue.poll();
        while (!priorityQueue.isEmpty()) {
            int r = priorityQueue.poll();
            max = Math.max(max, r - pre);
            pre = r;
        }
        return max;
    }

    public int numWays(String[] words, String target) {
        int n = words[0].length();
        int m = target.length();
        if (n < m) {
            return 0;
        }
        int[][] arr = new int[n][26];
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (int j = 0; j < n; j++) {
                arr[j][chars[j] - 'a']++;
            }
        }
        char[] chars = target.toCharArray();
        numWaysDFS(arr, chars, 0, 0, 1);
        return numWaysRes;
    }

    private int numWaysRes = 0;

    private void numWaysDFS(int[][] arr, char[] chars, int now, int nowChars, long s) {
        if (nowChars >= chars.length) {
            numWaysRes = (numWaysRes + (int) s) % 1000000007;
            return;
        }
        if (chars.length - nowChars > arr.length - now) {
            return;
        }
        int key = chars[nowChars] - 'a';
        for (int i = now; i < arr.length; i++) {
            if (arr[i][key] > 0) {
                numWaysDFS(arr, chars, i + 1, nowChars + 1, s * arr[i][key] % 1000000007);
            }
            if (chars.length - nowChars > arr.length - i) {
                return;
            }
        }
    }


    /**
     * 127. 单词接龙
     * <p>
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * <p>
     * 说明:
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * <p>
     * 执行用时：28 ms, 在所有 Java 提交中击败了87.03% 的用户
     * 内存消耗：46.4 MB, 在所有 Java 提交中击败了17.96% 的用户
     *
     * @param beginWord 单词
     * @param endWord   单词
     * @param wordList  字典
     * @return 找到从 beginWord 到 endWord 的最短转换序列的长度
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<Integer>();
        queBegin.offer(beginId);

        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<Integer>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; ++i) {
                int nodeBegin = queBegin.poll();
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edge.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edge.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }

    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }

    public boolean closeStrings(String word1, String word2) {
        int n = word1.length();
        int n2 = word2.length();
        if (n != n2) {
            return false;
        }

        boolean[] booleans = new boolean[26];
        boolean[] booleans2 = new boolean[26];
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[] nums = new int[26];
        int[] nums2 = new int[26];
        for (int i = 0; i < n; i++) {
            booleans[chars1[i] - 'a'] = true;
            booleans2[chars2[i] - 'a'] = true;
            nums[chars1[i] - 'a']++;
            nums2[chars2[i] - 'a']++;
        }
        Arrays.sort(nums);
        Arrays.sort(nums2);

        for (int i = 0; i < 26; i++) {
            if (booleans[i] ^ booleans2[i]) {
                return false;
            }
            if (nums[i] != nums2[i]) {
                return false;
            }
        }
        return true;
    }


    public int minOperations(int[] nums, int x) {
        int l = 0, r = nums.length - 1;
        minOperationsDFS(nums, x, l, r, 0);
        String tmp = l + "," + r;
        if (map.containsKey(tmp)) {
            int t = map.get(tmp);
            return t > Integer.MAX_VALUE / 3 ? -1 : t;
        }
        return -1;
    }

    private Map<String, Integer> map = new HashMap<>();
//    private int minOperationsRes = Integer.MAX_VALUE / 3;

    public int minOperationsDFS(int[] nums, int x, int l, int r, int now) {
        if (nums[l] > x && nums[r] > x) {
            map.put(l + "," + r, -1);
            return -1;
        }
        if (nums[l] == x || nums[r] == x) {
            map.put(l + "," + r, 1);
//            minOperationsRes = Math.min(now + 1, minOperationsRes);
            return 1;
        }
        int tt = Integer.MAX_VALUE / 3;
        if (l < r && x > nums[l]) {
            String tmp = (l + 1) + "," + r;
            if (map.containsKey(tmp) && map.get(tmp) > 0) {
                int t = map.get(tmp);
//                minOperationsRes = Math.min(now + t, minOperationsRes);

                if (t > -1) {
                    tt = t + 1;
                }
            } else {
                int t = minOperationsDFS(nums, x - nums[l], l + 1, r, now + 1);
                if (t >= 0) {
                    tt = t + 1;
                }
            }
        }
        if (l < r && x > nums[r]) {
            String tmp = l + "," + (r - 1);
            if (map.containsKey(tmp) && map.get(tmp) > 0) {
                int t = map.get(tmp);
//                minOperationsRes = Math.min(now + t, minOperationsRes);
                if (t > -1) {
                    tt = Math.min(tt, t + 1);
                }
            } else {
                int t = minOperationsDFS(nums, x - nums[r], l, r - 1, now + 1);
                if (t >= 0) {
                    tt = Math.min(tt, t + 1);
                }
            }
        }
        if (tt == Integer.MAX_VALUE / 3) {
            tt = -1;
        }
        map.put(l + "," + r, tt);
        return tt;
    }

    public int minOperations2(int[] nums, int x) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        dp[0][n - 1] = x;
        int min = -1;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                dp[i][n - 1] = dp[i - 1][n - 1] - nums[i - 1];
            }
            for (int j = n - 2; j > i; j--) {
                dp[i][j] = dp[i][j + 1] - nums[j + 1];
                if (dp[i][j] < 0) {
                    break;
                } else if (dp[i][j] == 0) {
                    min = Math.max(j - i + 1, min);
                }
            }
        }
        return min == -1 ? -1 : n - min;
    }

    public String removeKdigits(String num, int k) {
        char[] chars = num.toCharArray();
        int n = num.length();
        if (n <= k) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int j = 0;
        boolean f = true;
        for (int i = 1; i < n; i++) {
            if (f && chars[i] == '0' && chars[j] == '0') {
                i++;
                continue;
            } else if (f && chars[i] == '0') {
                chars[j] = '#';
                j = i;
                k--;
                continue;
            }
            if (chars[i] < chars[j]) {
                chars[j] = '#';
                j = i;
                k--;
                f = false;
            } else if (chars[i] > chars[j]) {
                chars[i] = '#';
                k--;
                f = false;
            }
            if (k <= 0) {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (chars[i] == '#') {
                continue;
            }
            if (k > 0) {
                k--;
                continue;
            }
            if (stringBuilder.length() == 0 && chars[i] == '0') {
                continue;
            }
            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }

    // 周赛 216
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : word1) {
            stringBuilder.append(s);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        for (String s : word2) {
            stringBuilder2.append(s);
        }
        return stringBuilder.toString().equals(stringBuilder2.toString());
    }

    public String getSmallestString(int n, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        while (k >= n && n > 0) {
            // 3 27
            int p = k - 26 >= n - 1 ? 26 : k - n + 1;
            char c = (char) ('a' + p - 1);
            stringBuilder.append(c);
            k -= p;
            n--;
        }
        return stringBuilder.reverse().toString();
    }

    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[][] pre = new int[n][2];
        pre[0][0] = nums[0];
        pre[0][1] = 0;
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                pre[i][0] = pre[i - 1][0] + nums[i];
                pre[i][1] = pre[i - 1][1];
            } else {
                pre[i][0] = pre[i - 1][0];
                pre[i][1] = pre[i - 1][1] + nums[i];
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int l0 = 0;
            int l1 = 0;
            if (i > 0) {
                l0 = pre[i - 1][0];
                l1 = pre[i - 1][1];
            }
            int r0 = 0;
            int r1 = 0;
            if (i < n - 1) {
                r0 = pre[n - 1][0] - pre[i][0];
                r1 = pre[n - 1][1] - pre[i][1];
            }
            if (l0 + r1 == l1 + r0) {
                res++;
            }
        }
        return res;
    }

    public int minimumEffort(int[][] tasks) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            }
        };

        Comparator<int[]> comparator2 = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int a = o1[0] + o2[1];
                int b = o1[1] + o2[0];
                if (a != b) {
                    return a - b;
                }
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            }
        };

        Arrays.sort(tasks, comparator2);


        int sum = 0;
        for (int[] task : tasks) {
            sum += task[0];
        }
        int need = 0;
        int now = sum;
        for (int[] task : tasks) {
            if (now < task[1]) {
                need += task[1] - now;
                now = task[1];
            }
            now = now - task[0];
        }
        return sum + need;
    }


    public int maxRepeating(String sequence, String word) {
        int ans = 0;
        int n = word.length();
        int sn = sequence.length();
        String str = word;
        while (ans * n < sn) {
            if (!sequence.contains(str)) {
                break;
            }
            ans++;
            str += word;
        }
        return ans;
    }


    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode head = list1;
        int i = 0;
        while (i < a - 1) {
            list1 = list1.next;
            i++;
        }
        ListNode l1 = list1;
        while (i < b) {
            list1 = list1.next;
            i++;
        }
        l1.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = list1.next;
        return head;
    }

    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        // 最长下降子序列
        int ans = 0;
        int[][] dp = new int[n][2];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i - 1] <= nums[i] && nums[i + 1] <= nums[i]) {
                List<Integer> left = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        left.add(nums[j]);
                    }
                }
                if (left.size() > 0) {
                    int[] up = left.stream().mapToInt(Integer::valueOf).toArray();
                    dp[i][0] = getMaxPeachesUp(up);
                }

                List<Integer> right = new ArrayList<>();
                for (int j = i + 1; j < n; j++) {
                    if (nums[i] > nums[j]) {
                        right.add(nums[j]);
                    }
                }
                if (right.size() > 0) {
                    int[] down = right.stream().mapToInt(Integer::valueOf).toArray();
                    dp[i][1] = getMaxPeachesDown(down);
                }
                ans = Math.max(ans, dp[i][0] + 1 + dp[i][1]);
            }
        }
        return n - ans;
    }

    private int getMaxPeachesUp(int[] peaches) {
        // 用于存储子序列的长度
        int[] subSeqLen = new int[peaches.length];
        for (int x = 0; x < peaches.length; x++) {
            subSeqLen[x] = 1; // 初始化最长子序列长度
            for (int y = 0; y < x; y++) { // 找出前x+1项最长的序列
                if (peaches[x] > peaches[y] && subSeqLen[y] + 1 > subSeqLen[x]) {
                    subSeqLen[x] = subSeqLen[y] + 1;
                }
            }
        }
        Arrays.sort(subSeqLen);
        return subSeqLen[subSeqLen.length - 1];
    }

    private int getMaxPeachesDown(int[] peaches) {
        // 用于存储子序列的长度
        int[] subSeqLen = new int[peaches.length];
        for (int x = 0; x < peaches.length; x++) {
            subSeqLen[x] = 1; // 初始化最长子序列长度
            for (int y = 0; y < x; y++) { // 找出前x+1项最长的序列
                if (peaches[x] < peaches[y] && subSeqLen[y] + 1 > subSeqLen[x]) {
                    subSeqLen[x] = subSeqLen[y] + 1;
                }
            }
        }
        Arrays.sort(subSeqLen);
        return subSeqLen[subSeqLen.length - 1];
    }

    //周赛

    public int maximumWealth(int[][] accounts) {
        int ans = 0;
        for (int i = 0; i < accounts.length; i++) {
            int tmp = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                tmp += accounts[i][j];
            }
            ans = Math.max(ans, tmp);
        }
        return ans;
    }

    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[k];
        for (int i = n - k; i < n; i++) {
            arr[i - n + k] = nums[i];
        }
        for (int j = n - k - 1; j >= 0; j--) {
            if (nums[j] > arr[0]) {
                continue;
            }
            int tmp = nums[j];
            for (int i = 0; i < k; i++) {
                if (tmp > arr[i]) {
                    break;
                }
                tmp = arr[i] ^ tmp;
                arr[i] = arr[i] ^ tmp;
                tmp = arr[i] ^ tmp;
            }
        }
        return arr;
    }


    public int minimumDeviation(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int max = Integer.parseInt(String.valueOf(Arrays.stream(nums).max()));
        int min = Integer.parseInt(String.valueOf(Arrays.stream(nums).min()));
        int ans = 0;
        return ans;
    }

    // 218 周赛 -- 阿里
    public String interpret(String command) {
        String res = command.replace("()", "o");
        res = res.replace("(al)", "al");
        return res;
    }

    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int n = map.getOrDefault(num, 0) + 1;
            map.put(num, n);
        }
        int ans = 0;
        Set<Integer> visited = new HashSet();
        for (int key : map.keySet()) {
            if (visited.add(key)) {
                int reKey = k - key;
                if (key == reKey) {
                    ans += map.get(key) / 2;
                } else {
                    visited.add(reKey);
                    ans += Math.min(map.get(key), map.getOrDefault(reKey, 0));
                }
            }
        }
        return ans;
    }

    public int concatenatedBinary(int n) {
        long now = 0;
        for (int i = 1; i <= n; i++) {
            String s = Integer.toBinaryString(i);
            int p = s.length();
            now = now * (long) Math.pow(2, p) + i;
            now = now % 1000000007;
        }
        return (int) now;
    }

    public int minimumIncompatibility0(int[] nums, int k) {
        Arrays.sort(nums);
        minimumIncompatibilityDFS(nums, new boolean[nums.length], nums.length / k,
                0, 0, 0, 0, new ArrayList<Integer>());
        return minimumIncompatibilityRes == Integer.MAX_VALUE ? -1 : minimumIncompatibilityRes;
    }

    private int minimumIncompatibilityRes = Integer.MAX_VALUE;

    private void minimumIncompatibilityDFS(int[] nums, boolean[] visited, int k,
                                           int preKey, int visitedNum,
                                           int sum, int left, List<Integer> list) {
        if (visitedNum == nums.length) {
            minimumIncompatibilityRes = Math.min(minimumIncompatibilityRes, sum);
            return;
        }
        int t = preKey == 0 ? 0 : left;
        for (int i = t; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (preKey == nums[i]) {
                continue;
            }
            if (preKey == 0) {
                visited[i] = true;
                list.add(nums[i]);
                preKey = nums[i];
                visitedNum++;
            } else {
//                if (preKey - first + sum > minimumIncompatibilityRes) {
//                    break;
//                }
                visited[i] = true;
                list.add(nums[i]);
                visitedNum++;
                int first = list.get(list.size() - k);
                if (visitedNum % k == 0) {
                    sum += nums[i] - first;
                    preKey = 0;
                }
            }
            minimumIncompatibilityDFS(nums, visited, k, preKey,
                    visitedNum, sum, i + 1, list);
            if (visitedNum % k == 0) {
                int first = list.get(list.size() - k);
                sum -= nums[i] - first;
            }
            visitedNum--;
            if (visitedNum % k == 0) {
                preKey = 0;
            }
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    // geek-ro

    int res = Integer.MAX_VALUE;
    int size;

    public int minimumIncompatibility(int[] nums, int k) {
        size = nums.length / k;
        if (nums.length % k != 0) {
            return -1;
        }
        int[] arr = new int[20];
        for (int i : nums) {
            arr[i]++;
        }
        for (int i : arr) {
            if (i > k) {
                return -1;
            }
        }

        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            list.add(new LinkedList<>());
        }
        Arrays.sort(nums);
        backtracking(nums, 0, list, 0);

        return res;
    }

    private void backtracking(int[] arr, int idx, List<List<Integer>> list, int num) {
        if (idx == arr.length) {
            res = Math.min(res, num);
            return;
        }
        if (num >= res) {
            return;
        }

        int n = arr[idx];
        int temp = num;

        for (List<Integer> l : list) {
            if (l.size() == size) {
                continue;
            } else if (l.size() > 0 && l.get(l.size() - 1) != n) {
                num -= (l.get(l.size() - 1) - l.get(0));
                l.add(n);
                num += (n - l.get(0));
                backtracking(arr, idx + 1, list, num);
                l.remove(l.size() - 1);
                num = temp;
            } else if (l.size() == 0) {
                l.add(n);
                backtracking(arr, idx + 1, list, num);
                l.remove(l.size() - 1);
                break;
            }
        }
    }

    // 219周赛
    public int numberOfMatches(int n) {
        int ans = 0;
        while (n > 1) {
            ans += n / 2;
            n = (n + 1) / 2;
        }
        return ans;
    }

    public int minPartitions(String n) {
        int ans = 0;
        char[] c = n.toCharArray();
        for (char cc : c) {
            int tmp = cc - '0';
            ans = Math.max(ans, tmp);
        }
        return ans;
    }


    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int left = 0, right = n - 1;
        boolean add = true;
        int ans = 0;
        if (n % 2 == 0) {
            add = false;
            int minL = Math.min(stones[left + 1], stones[right]);
            int minR = Math.min(stones[left], stones[right - 1]);
            if (minL < minR) {
                right--;
            } else {
                left++;
            }
            for (int i = left; i <= right; i++) {
                ans += stones[i];
            }
        }
        stoneGameVII(stones, left, right, false);
        return add ? ans + stoneGameVIIRes : ans - stoneGameVIIRes;
    }

    private int stoneGameVIIRes = 0;

    private void stoneGameVII(int[] stones, int left, int right, boolean boub) {
        if (right - left < 1) {
            return;
        }
        int minL = Math.min(stones[left + 1], stones[right]);
        int minR = Math.min(stones[left], stones[right - 1]);
        if (minL < minR) {
            if (boub) {
                stoneGameVIIRes += stones[right];
            }
            right--;
        } else if (minL > minR) {
            if (boub) {
                stoneGameVIIRes += stones[left];
            }
            left++;
        } else if (stones[right] < stones[left]) {
            if (boub) {
                stoneGameVIIRes += stones[right];
            }
            right--;
        } else {
            if (boub) {
                stoneGameVIIRes += stones[left];
            }
            left++;
        }

        boub = !boub;

        stoneGameVII(stones, left, right, boub);

    }

    // 2 2 3   1 3 2
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        if (n < 1) {
            return 0;
        }
        int m = cuboids[0].length;
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[2] - o2[2];
            }
        };
        Arrays.sort(cuboids, comparator);
        // 2 2 5
        // 1 3 3
        return 0;
    }

    // 周赛
    public String reformatNumber(String number) {
        number = number.replaceAll("-", "");
        number = number.replaceAll(" ", "");
        int n = number.length();
        int t = n;
        StringBuilder sb = new StringBuilder();
        char[] chars = number.toCharArray();
        int i = 0;
        for (; i < n - 4; i++) {
            sb.append(chars[i]).append(chars[i + 1]).append(chars[i + 2]).append("-");
            i = i + 2;
        }
        if (i == n - 4) {
            sb.append(chars[i]).append(chars[i + 1]).append("-").append(chars[i + 2]).append(chars[i + 3]);
        } else if (i == n - 3) {
            sb.append(chars[i]).append(chars[i + 1]).append(chars[i + 2]);
        } else if (i == n - 2) {
            sb.append(chars[i]).append(chars[i + 1]);
        }
        return sb.toString();
    }

    public int maximumUniqueSubarray(int[] nums) {
        int[] arr = new int[10005];
        int max = 0;
        int l = 0, r = 0;
        int n = nums.length;
        int now = 0;
        while (r < n) {
            if (arr[nums[r]] == 0) {
                arr[nums[r]]++;
                now += nums[r];
                r++;
            } else {
                max = Math.max(now, max);
                while (l < r && arr[nums[r]] > 0) {
                    now -= nums[l];
                    arr[nums[l]]--;
                    l++;
                }
                now += nums[r];
                arr[nums[r]] = 1;
                r++;
            }
        }
        max = Math.max(max, now);
        return max;
    }

    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int ans = nums[0];
        int tmp = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= 0) {
                ans += nums[i];
                tmp = i;
            } else {
                // k == 3： 1 -1 -2 -3 -1 -3 -1 1
                int j = i;
                for (; j < n; j++) {
                    if (nums[j] >= 0) {
                        break;
                    }
                }
                j = Math.min(j, n - 1);
                if (j - i >= k) {
                    int[] dp = new int[j - i + 1];
                    Arrays.fill(dp, Integer.MIN_VALUE);
                    dp[0] = nums[i];
                    for (int ii = i; ii <= j; ii++) {
                        for (int jj = ii - k; jj < ii; jj++) {
                            if (jj < i) {
                                continue;
                            }
                            dp[ii - i] = Math.max(dp[jj - i] + nums[ii], dp[ii - i]);
                        }
                    }
                    ans += dp[j - i];
                } else {
                    ans += nums[j];
                }
                i = j;
            }
        }
        return ans;
    }

    /**
     * 5632. 检查边长度限制的路径是否存在
     * <p>
     * 给你一个 n 个点组成的无向图边集 edgeList ，
     * 其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。请注意，两个点之间可能有 超过一条边 。
     * 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，
     * 你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
     * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，
     * 当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
     * <p>
     * 执行用时：170 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：73.1 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param n        n 个点
     * @param edgeList 无向图边集
     * @param queries  查询数组
     * @return 对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
     */
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        UnionFind union = new UnionFind(n);
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        int size = queries.length;
        boolean[] res = new boolean[size];
        int i = 0;
        Map<int[], Integer> map = new HashMap<>();
        for (int[] q : queries) {
            map.put(q, i++);
        }
        Arrays.sort(queries, Comparator.comparingInt(a -> a[2]));
        int index = 0;
        for (int[] arr : queries) {
            while (index < edgeList.length && edgeList[index][2] < arr[2]) {
                union.union(edgeList[index][0], edgeList[index][1]);
                ++index;
            }
            res[map.get(arr)] = union.connect(arr[0], arr[1]);
        }
        return res;
    }


    public boolean halvesAreAlike(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        char[] chars = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char c : chars) {
            set.add(c);
        }
        int numA = 0, numB = 0;
        int half = n / 2;
        for (int i = 0; i < half; i++) {
            if (set.contains(s.charAt(i))) {
                numA++;
            }
            if (set.contains(s.charAt(i + half))) {
                numB++;
            }
        }
        return numA == numB;
    }

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int res = 0;
        for (int i = 0; ; i++) {
            if (i < apples.length && apples[i] > 0) {
                q.offer(new int[]{i + days[i], apples[i]});
            } else if (i >= apples.length && q.isEmpty()) {
                break;
            }
            while (!q.isEmpty() && (q.peek()[0] <= i || q.peek()[1] <= 0)) {
                q.poll();
            }
            if (!q.isEmpty()) {
                q.peek()[1] -= 1;
                res++;
            }
        }
        return res;
    }

    public int[] findBall(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] newGrid = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(newGrid[i], -1);
            int pre = grid[i][0];
            for (int j = 1; j < m; j++) {
                if (pre == 1 && grid[i][j] == 1) {
                    newGrid[i][j - 1] = j;
                } else if (pre == -1 && grid[i][j] == -1) {
                    newGrid[i][j] = j - 1;
                }
                pre = grid[i][j];
            }
        }
        int[] ans = new int[m];
        for (int k = 0; k < m; k++) {
            ans[k] = k;
        }
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < m; k++) {
                if (ans[k] == -1) {
                    continue;
                }
                ans[k] = newGrid[i][ans[k]];
            }
        }
        return ans;
    }

}
