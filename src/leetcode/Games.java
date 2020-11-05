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
     *
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     *     每次转换只能改变一个字母。
     *     转换过程中的中间单词必须是字典中的单词。
     *
     * 说明:
     *     如果不存在这样的转换序列，返回 0。
     *     所有单词具有相同的长度。
     *     所有单词只由小写字母组成。
     *     字典中不存在重复的单词。
     *     你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     *
     * 执行用时：28 ms, 在所有 Java 提交中击败了87.03% 的用户
     * 内存消耗：46.4 MB, 在所有 Java 提交中击败了17.96% 的用户
     * @param beginWord 单词
     * @param endWord 单词
     * @param wordList 字典
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

}
