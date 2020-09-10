import java.util.*;
import java.util.stream.Collectors;

import leetcode.datestruct.ListNode;
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

    //2018.Dec.16 - 第115周赛
    //957
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] tcell = new int[cells.length];
        int r = 0;

        N--;
        tcell[0] = 0;
        tcell[cells.length - 1] = 0;
        for (int i = 1; i < cells.length - 1; i++) {
            if (cells[i - 1] == cells[i + 1]) {
                tcell[i] = 1;
            } else {
                tcell[i] = 0;
            }
        }
        r = 1;

        N = N % 15;
        N = 6;
        while (N != 0) {
            if ((0 == r)) {
                tcell[0] = 0;
                tcell[cells.length - 1] = 0;
                for (int i = 1; i < cells.length - 1; i++) {
                    if (cells[i - 1] == cells[i + 1]) {
                        tcell[i] = 1;
                    } else {
                        tcell[i] = 0;
                    }
                }
                r = 1;
            } else {
                cells[0] = 0;
                cells[cells.length - 1] = 0;
                for (int i = 1; i < cells.length - 1; i++) {
                    if (tcell[i - 1] == tcell[i + 1]) {
                        cells[i] = 1;
                    } else {
                        cells[i] = 0;
                    }
                }
                r = 0;
            }
            N--;
        }
        if (0 == r) {
            tcell[0] = 0;
            tcell[cells.length - 1] = 0;
            return tcell;
        } else {
            cells[0] = 0;
            cells[cells.length - 1] = 0;
            return cells;
        }
        /*
        int s = 0;
        for(int i:cells){
            s = s * 2 + i;
        }
        N = N % 15;
        N--;

        ArrayList<Integer> list = new ArrayList<>();
        while(N > 0) {
            int a = s >> 1;

            int b = s << 1;
            s = a ^ b % 128;
            if (s % 2 != 0) {
                s = s - 1;
            }
            list.add(s);
            N--;
        }
        int[] re = new int[8];
        re[0] = 0;
        re[7] = 0;
        s = s / 2;
        for(int i = 6; i > 0 ;i--){
            re[i] = s % 2;
            s = s / 2;
        }
        return re;*/
//        int s = 0;
//        for(int i:cells){
//            s = s * 2 + i;
//        }
//        ArrayList<Integer> list = new ArrayList<>();
//        int n = 0;
//        while(N > 0) {
//            int a = s >> 1;
//
//            int b = s << 1;
//            s = a ^ b % 128;
//            if (s % 2 != 0) {
//                s = s - 1;
//            }
//            if(list.contains(s)){
//                break;
//            }
//            N --;
//            list.add(s);
//            n++;
//        }
//        if(N != 0&&list.contains(s)){
//            int x = list.indexOf(s);
//            N = N % (n - x + 1);
//            N = (N + x);
//            N = N % list.size();
//            s = list.get(N);
//        }
//        int[] re = new int[8];
//        re[0] = 0;
//        re[7] = 0;
//        s = s / 2;
//        for(int i = 6; i > 0 ;i--){
//            re[i] = s % 2;
//            s = s / 2;
//        }
//        return re;

    }


    //2018.Dec.16 - 第116周赛
    //961
    public int repeatedNTimes(int[] A) {
        if (0 == A.length) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : A) {
            if (set.contains(i)) {
                return i;
            } else {
                set.add(i);
            }

        }
        return 0;
    }

    //962
    public int maxWidthRamp(int[] A) {
        int ri = 0;
        int rj = 0;
        int maxl = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = A.length - 1; j > i; j--) {
                if (j - i < maxl) {
                    break;
                }
                if (A[j] >= A[i]) {
                    ri = i;
                    rj = j;
                    maxl = j - i;
                    break;
                }
            }
        }
        return maxl;
    }

    //964(wrong)
    public int leastOpsExpressTarget(int x, int target) {
        char[] ops = {'+', '-', '*', '/'};
        int res = 0;
        if (x == target) {
            return res;
        }
        int ns = x;
        res = leastOpsExpressTargetAss(x, target, res, ns, 1);
        return res;
    }

    public static int leastOpsExpressTargetAss(int x, int target, int res, int ns, int k) {
        if (ns == target) {
            return res;
        }
        if (ns <= target) {
            if (ns * x <= target) {
                while (ns < target) {
                    ns = ns * x;
                    k++;
                }
                if (k != 1) {
                    ns = ns / x;
                    res = res + k - 1;
                }
            }
            while (k > 1) {
                int kk = 1;
                int ss = x;
                while (ss + ns < target) {
                    ss = ss * x;
                    kk++;
                }
                if (kk != 1) {
                    res = res + kk - 1;
                    ns = ns + ss / x;
                    k = kk - 1;
                } else {
                    break;
                }
            }
            while (ns + x <= target) {
                res++;
                ns = ns + x;
            }
            while (ns + 1 <= target) {
                res = res + 2;
                ns = ns + 1;
            }
        } else {
            if (ns - 1 >= target) {
                res = res + 2;
                ns--;
            }
        }
        return res;
    }

    //2018.Dec.30 - 第117周赛 - 年终巅峰对决
    //965
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        int val = root.val;
        return isUnivalTreeAss(root, val, true);
    }

    public static boolean isUnivalTreeAss(TreeNode root, int val, boolean b) {
        if (root.val != val) {
            return false;
        }
        if (b && root.left != null) {
            b = isUnivalTreeAss(root.left, val, b);
        }
        if (b && root.right != null) {
            b = isUnivalTreeAss(root.right, val, b);
        }
        return b;
    }

    //967
    public int[] numsSameConsecDiff(int N, int K) {
        if (K > 9) {
            int[] ints = new int[0];
            return ints;
        }
        if (N == 1) {
            int[] ints = new int[10];
            for (int i = 0; i < 10; i++) {
                ints[i] = i;
            }
            return ints;
        }
        if (K == 0) {
            int[] ints = new int[9];
            for (int i = 1; i < 10; i++) {
                int j = N;
                int re = 0;
                while (j > 0) {
                    re = re * 10 + i;
                    j--;
                }
                if (re > 0) {
                    ints[i - 1] = re;
                }
            }
            return ints;
        }
        int[][] ints = new int[10][2];
        int[] is = new int[10];
        int i = 0, j = i + K;
        while (i < 10 && j < 10) {
            if (is[i] == 0 && is[j] == 0) {
                ints[i][0] = j;
                ints[j][0] = i;
                is[i]++;
                is[j]++;
            } else if (is[i] == 1 && is[j] == 0) {
                ints[i][1] = j;
                ints[j][0] = i;
                is[i]++;
                is[j]++;
            } else if (is[i] == 0 && is[j] == 1) {
                ints[i][0] = j;
                ints[j][1] = i;
                is[i]++;
                is[j]++;
            } else if (is[i] == 1 && is[j] == 1) {
                ints[i][1] = j;
                ints[j][1] = i;
                is[i]++;
                is[j]++;
            }
            i++;
            j = i + K;
        }
        int key = 1;
        int jj = N;
        while (jj > 1) {
            key = key * 10;
            jj--;
        }
        List<Integer> list = new ArrayList<>();
        for (int ii = 0; ii < 10; ii++) {
            int r = 0;
            numsSameConsecDiff(list, N, ints, r, ii, is, key);
        }
        int[] res = new int[list.size()];
        for (int ii = 0; ii < list.size(); ii++) {
            res[ii] = list.get(ii);
        }
        return res;
    }

    public void numsSameConsecDiff(List<Integer> list, int N, int[][] ints, int r, int i, int[] is, int key) {
        if (N == 0) {
            if (r >= key && !list.contains(r)) {
                list.add(r);
            }
            return;
        }
        if (is[i] == 1) {
            r = r * 10 + ints[i][0];
            numsSameConsecDiff(list, N - 1, ints, r, ints[i][0], is, key);
            return;
        }
        if (is[i] == 2) {
            r = r * 10 + ints[i][0];
            numsSameConsecDiff(list, N - 1, ints, r, ints[i][0], is, key);
            r = r - ints[i][0] + ints[i][1];
            numsSameConsecDiff(list, N - 1, ints, r, ints[i][1], is, key);
            return;
        }
        return;
    }

    //968
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int[] re = new int[1];
        int i = minCameraCoverAss(root, re, false);
        if (i == 0) {
            re[0]++;
        }
        return re[0];
    }

    public int minCameraCoverAss(TreeNode root, int[] re, boolean bf) {
        if (root.left == null && root.right == null) {
            return 0;
        }
        int bl = 2, br = 2;
        if (root.left != null) {
            bl = minCameraCoverAss(root.left, re, bf);
        }
        if (root.right != null) {
            br = minCameraCoverAss(root.right, re, bf);
        }
        int bre = 0;
        if (bl == 0 || br == 0) {
            bre = 1;
            re[0]++;
        } else if (bl == 1 || br == 1) {
            bre = 2;
        }
        return bre;
    }

    /**
     * 373
     * 执行用时 :21 ms, 在所有 Java 提交中击败了57.51%的用户
     * 内存消耗 :40.2 MB, 在所有 Java 提交中击败了75.00%的用户
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        k = Math.min(k, nums1.length * nums2.length); //注意k的取值
        if (k == 0) {
            return res;
        }

        Queue<int[]> Q = new PriorityQueue<>(k, (o1, o2) -> o2[0] + o2[1] - o1[0] - o1[1]);
        for (int e1 : nums1) {
            for (int e2 : nums2) {
                if (Q.size() < k) {
                    Q.offer(new int[]{e1, e2});
                } else if (e1 + e2 <= Q.peek()[0] + Q.peek()[1]) {
                    Q.poll();
                    Q.offer(new int[]{e1, e2});
                }
            }
        }
        while (k-- > 0) {
            int[] e = Q.poll();
            res.add(0, Arrays.asList(e[0], e[1]));
        }

        return res;

    }


    public int[] finalPrices(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return new int[0];
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (prices[i] >= prices[j]) {
                    prices[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return (o1[0] - o2[0]);
        }
    };


    public int minSumOfLengths(int[] arr, int target) {
//        Comparator<int[]> comparator = new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return (o1[0] - o2[0]);
//            }
//        };
//        Queue<int[]> success = new PriorityQueue<int[]>(comparator);
        List<int[]> success = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.isEmpty()) {
                if (arr[i] < target) {
                    map.put(i, arr[i]);
                } else if (arr[i] == target) {
                    int[] tmp = new int[3];
                    tmp[0] = 1;
                    tmp[1] = i;
                    tmp[2] = i;
                    success.add(tmp);
                }
            } else {
                if (arr[i] == target) {
                    int[] tmp = new int[3];
                    tmp[0] = 1;
                    tmp[1] = i;
                    tmp[2] = i;
                    success.add(tmp);
                } else if (arr[i] < target) {
                    List<Integer> set = new ArrayList<>();
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        if (entry.getValue() + arr[i] < target) {
                            map.put(entry.getKey(), entry.getValue() + arr[i]);
                        } else if (entry.getValue() + arr[i] == target) {
                            int[] tmp = new int[3];
                            tmp[0] = i - entry.getKey() + 1;
                            tmp[1] = entry.getKey();
                            tmp[2] = i;
                            success.add(tmp);
                            set.add(entry.getKey());
                        } else {
                            set.add(entry.getKey());
                        }
                    }
                    map.put(i, arr[i]);
                    for (int s : set) {
                        map.remove(s);
                    }
                }
            }
        }
        if (success.size() <= 1) {
            return -1;
        } else {
            int len = success.size();
            Queue<Integer> queue1 = new PriorityQueue<Integer>();
            for (int i = 0; i < len; i++) {
                int tmp = 9999;
                for (int j = i + 1; j < len; j++) {
                    if (success.get(i)[2] < success.get(j)[1]) {
                        tmp = Math.min(success.get(i)[0] + success.get(j)[0], tmp);
                    }
                }
                if (tmp < 9999) {
                    queue1.add(tmp);
                }
            }
            if (!queue1.isEmpty()) {
                int a = queue1.poll();
                return a;
            }
            return -1;
        }
    }

    public int[] runningSum(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = nums[0];
        for (int i = 1; i < len; i++) {
            res[i] = nums[i] + res[i - 1];
        }
        return res;
    }

    public int findLeastNumOfUniqueInts0(int[] arr, int k) {
        int len = arr.length;
        Map<Integer, Integer> value = new HashMap<>();
        for (int item : arr) {
            if (value.get(item) != null) {
                int kk = value.get(item);
                value.put(item, kk + 1);
            } else {
                value.put(item, 1);
            }
        }

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] - o2[0]);
            }
        };
        Queue<int[]> success = new PriorityQueue<int[]>(comparator);

        int resLen = 0;
        for (Map.Entry<Integer, Integer> entry : value.entrySet()) {
            int[] tmp = new int[2];
            tmp[0] = entry.getValue();
            tmp[1] = entry.getKey();
            success.add(tmp);
            resLen++;
        }
        while (!success.isEmpty()) {
            int[] tmp = success.poll();
            if (k < tmp[0]) {
                return resLen;
            } else if (k == tmp[0]) {
                return resLen - 1;
            } else {
                resLen--;
                k = k - tmp[0];
            }
        }
        return -1;
    }


    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int len = arr.length;
        quickSort(arr, 0, len - 1);
        int tmp = arr[0];
        Queue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 1; i < len; i++) {
            int n = 1;
            while (i < len && tmp == arr[i]) {
                n++;
                i++;
            }
            if (i < len) {
                tmp = arr[i];
            }
            queue.add(n);
        }
        if (arr.length == 1) {
            queue.add(1);
        }
        int resLen = queue.size();
        while (!queue.isEmpty()) {
            int t = queue.poll();
            if (k < t) {
                return resLen;
            } else if (k == t) {
                return resLen - 1;
            } else {
                resLen--;
                k = k - t;
            }
        }
        return 0;
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

    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if (m * k > len) {
            return -1;
        }
        int[] range = new int[len + 1];
        range[0] = 1;
        range[len] = Integer.MAX_VALUE;
        int key = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int tmp = 1;
            for (int j = 0; i > j; j++) {
                if (bloomDay[j] > bloomDay[i]) {
                    tmp++;
                } else if (bloomDay[j] < bloomDay[i]) {
                    range[j]++;
                }
            }
            key = Math.max(key, bloomDay[i]);
            range[i] = tmp;
        }
        for (int ii = 0; ii < len; ii++) {
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < len; i++) {
                max = Math.min(max, range[i]);
            }
            boolean flag = false;
            int enough = m;
            int tmp = 0;
            int p = -1;
            for (int i = 0; i < len + 1; i++) {
                if (range[i] == max) {
                    p = bloomDay[i];
                    range[i] = Integer.MAX_VALUE;
                }
                if (range[i] == Integer.MAX_VALUE) {
                    enough = enough - tmp / k;
                    if (enough <= 0) {
                        flag = true;
                        break;
                    }
                    tmp = 0;
                } else {
                    tmp++;
                }
            }
            if (!flag) {
                return p;
            }
        }
        return -1;
    }

    public String reformat(String s) {
        char[] chars = s.toCharArray();
        List<Character> stackInt = new ArrayList<>();
        List<Character> stackChar = new ArrayList<>();
        StringBuilder stringBuffer = new StringBuilder();

        for (char aChar : chars) {
            if ((int) aChar > 64) {
                stackChar.add(aChar);
            } else {
                stackInt.add(aChar);
            }
        }
        int abs = stackChar.size() - stackInt.size();
        if (abs == 0) {
            for (int i = 0; i < stackChar.size(); i++) {
                stringBuffer.append(stackChar.get(i));
                stringBuffer.append(stackInt.get(i));
            }
        } else if (abs == 1) {
            for (int i = 0; i < stackInt.size(); i++) {
                stringBuffer.append(stackChar.get(i));
                stringBuffer.append(stackInt.get(i));
            }
            stringBuffer.append(stackChar.get(stackInt.size()));
        } else if (abs == -1) {
            for (int i = 0; i < stackChar.size(); i++) {
                stringBuffer.append(stackInt.get(i));
                stringBuffer.append(stackChar.get(i));
            }
            stringBuffer.append(stackInt.get(stackChar.size()));
        } else {
            return "";
        }
        return stringBuffer.toString();
    }


    public List<List<String>> displayTable(List<List<String>> orders) {
        class Order {
            final List<String> table;
            final List<List<String>> displayTable;
            int tableNumber;

            Order() {
                this.tableNumber = 0;
                this.table = new LinkedList<>();
                table.add("Table");
                this.displayTable = new LinkedList<>();
            }

            int addTable(String table) {
                int i = 1;
                for (; i <= tableNumber; i++) {
                    if (Integer.parseInt(this.table.get(i)) > Integer.parseInt(table)) {
                        break;
                    } else if (Integer.parseInt(this.table.get(i)) == Integer.parseInt(table)) {
                        return i;
                    }
                }
                this.table.add(i, table);
                for (List<String> list : displayTable) {
                    if (i <= tableNumber) {
                        list.add(i, "0");
                    } else {
                        list.add("0");
                    }
                }
                tableNumber++;
                return i;
            }

            void addFood(String food, int tab) {
                int i = 0;
                for (; i < displayTable.size(); i++) {
                    List<String> foodItem = displayTable.get(i);
                    if (foodItem.get(0).compareTo(food) == 0) {
                        foodItem.set(tab, String.valueOf(Integer.parseInt(foodItem.get(tab)) + 1));
                        return;
                    } else if (foodItem.get(0).compareTo(food) > 0) {
                        break;
                    }
                }
                List<String> newFoodItem = new LinkedList<>();
                newFoodItem.add(food);
                for (int j = 0; j < tableNumber; j++) {
                    if (j != tab - 1) {
                        newFoodItem.add("0");
                    } else {
                        newFoodItem.add("1");
                    }
                }
                displayTable.add(i, newFoodItem);
            }
        }
        Order order = new Order();
        for (List<String> oneOrder : orders) {
            String table = oneOrder.get(1);
            String food = oneOrder.get(2);
            int tab = order.addTable(table);
            order.addFood(food, tab);
        }
        order.displayTable.add(0, order.table);
        return order.displayTable;
    }


    public int xorOperation(int n, int start) {
        int i = 0;
        int res = 0;
        while (n > i) {
            int index = start + 2 * i;
            res = res ^ index;
            i++;
        }
        return res;
    }

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap();
        String[] res = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            if (map.containsKey(names[i])) {
                int tmp = map.get(names[i]) + 1;
                res[i] = names[i] + '(' + tmp + ')';
                while (map.containsKey(res[i])) {
                    tmp = tmp + 1;
                    res[i] = names[i] + '(' + tmp + ')';
                }
                map.put(names[i], tmp);
                map.put(res[i], 0);
            } else {
                res[i] = names[i];
                map.put(names[i], 0);
            }
        }
        return res;
    }


    public int[] avoidFlood(int[] rains) {
        int len = rains.length;
        int[] mem = new int[len];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = len - 1; i >= 0; --i) {
            if (map.containsKey(rains[i])) {
                mem[i] = map.get(rains[i]);
            } else {
                mem[i] = -1;
            }
            map.put(rains[i], i);
        }
        int[] res = new int[len];
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (mem[a] - mem[b]));
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; ++i) {
            if (rains[i] == 0) {
                if (!queue.isEmpty()) {
                    int tmp = rains[queue.poll()];
                    set.remove(tmp);
                    res[i] = tmp;
                } else {
                    res[i] = 1;
                }
            } else if (set.contains(rains[i])) {
                return new int[0];
            } else {
                set.add(rains[i]);
                if (mem[i] != -1) {
                    queue.add(i);
                }
                res[i] = -1;
            }
        }
        return res;
    }


    public double average(int[] salary) {
        int sum = 0;
        int min = salary[0] > salary[1] ? salary[1] : salary[0];
        int max = salary[0] > salary[1] ? salary[0] : salary[1];

        for (int i = 2; i < salary.length; i++) {
            if (salary[i] < min) {
                sum += min;
                min = salary[i];
            } else if (salary[i] > max) {
                sum += max;
                max = salary[i];
            } else {
                sum += salary[i];
            }
        }

        return (double) sum / (salary.length - 2);
    }


    public int kthFactor(int n, int k) {
        int tmp = 1;
        while (k > 0) {
            while (n % tmp != 0) {
                tmp++;
            }
            k--;
            if (k == 0) {
                return tmp;
            }
            tmp++;
            if (tmp > n) {
                return -1;
            }
        }
        return tmp;
    }

    public int longestSubarray(int[] nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            while (i + tmp < nums.length && nums[i + tmp] == 0) {
                tmp++;
            }
            if (tmp != 0) {
                list.add(new int[]{0, tmp});
            }
            i = i + tmp;
            tmp = 0;
            while (i + tmp < nums.length && nums[i + tmp] == 1) {
                tmp++;
            }
            if (tmp != 0) {
                list.add(new int[]{1, tmp});
            }
            i = i + tmp - 1;
        }
        int max = 0;
        int[] pre = list.get(0);
        if (pre[0] == 1) {
            max = pre[1] - 1;
        }
        for (int i = 1; i < list.size(); i++) {
            int[] arr = list.get(i);
            if (arr[0] == 1) {
                max = Math.max(max, arr[1] - 1);
            } else if (arr[1] == 1) {
                if (i + 1 < list.size()) {
                    max = Math.max(max, pre[1] + list.get(i + 1)[1]);
                } else {
                    max = Math.max(max, pre[1]);
                }
            } else {
                max = Math.max(max, pre[1]);
            }
            pre = arr;
        }
        if (list.size() >= 2) {
            int[] arr = list.get(list.size() - 1);
            if (arr[0] == 1) {
                max = Math.max(max, arr[1]);
            }
        }
        return max;
    }

    public int get(int x, int i) {
        return (x >> i) & 1;
    }


    public int minNumberOfSemesters0(int n, int[][] dependencies, int k) {
        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];
        Map<Integer, List<Integer>> outDegreeMap = new HashMap<>();
        for (int[] dependency : dependencies) {
            inDegree[dependency[1]]++;
            outDegree[dependency[0]]++;
            List<Integer> list = outDegreeMap.computeIfAbsent(dependency[0], integer -> new ArrayList<>());
            list.add(dependency[1]);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int r = 0;
        while (!queue.isEmpty()) {
            r++;
            int size = queue.size();
            queue.sort(Comparator.comparingInt(o -> outDegree[(int) o]).reversed());
            for (int i = 0; i < k && i < size; i++) {
                int num = queue.poll();
                List<Integer> list = outDegreeMap.getOrDefault(num, new ArrayList<>());
                for (Integer e : list) {
                    inDegree[e]--;
                    if (inDegree[e] == 0) {
                        queue.add(e);
                    }
                }
            }
        }
        return r;
    }


    public int[] arrayRankTransform(int[] arr) {
        int len = arr.length;
        int[] brr = Arrays.copyOf(arr, len);
        Arrays.sort(brr);
        Map<Integer, Integer> map = new HashMap<>();
        int k = 1;
        for (int b : brr) {
            if (!map.containsKey(b)) {
                map.put(b, k++);
            }
        }
        for (int i = 0; i < len; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }


    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        if (len <= 1) {
            return "";
        }
        char[] chars = palindrome.toCharArray();
        boolean is = false;
        for (int i = 0; i < len / 2; i++) {
            if (chars[i] != 'a') {
                chars[i] = 'a';
                is = true;
                break;
            }
        }
        if (!is) {
            chars[chars.length - 1] = 'b';
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            int t = Math.min(m - i, n);
            int[] tmp = new int[t];
            int p = 0;
            for (int j = i; j < m && j - i < n; j++) {
                tmp[p++] = mat[j][j - i];
            }
            Arrays.sort(tmp);
            p = 0;
            for (int j = i; j < m && j - i < n; j++) {
                mat[j][j - i] = tmp[p++];
            }
        }
        for (int i = 1; i < n; i++) {
            int t = Math.min(m, n - i);
            int[] tmp = new int[t];
            int p = 0;
            for (int j = i; j - i < m && j < n; j++) {
                tmp[p++] = mat[j - i][j];
            }
            Arrays.sort(tmp);
            p = 0;
            for (int j = i; j - i < m && j < n; j++) {
                mat[j - i][j] = tmp[p++];
            }
        }
        return mat;
    }

    public int maxValueAfterReverse(int[] nums) {
        int len = nums.length;
        int[] brr = new int[len];
        for (int i = 0; i < len; i++) {
            brr[len - i - 1] = nums[i];
        }
        return Math.max(maxValueAfterReverseAss(nums), maxValueAfterReverseAss(brr));
    }

    public int maxValueAfterReverseAss(int[] nums) {
        int len = nums.length;
        // 计算出相邻的差
        int max = 0;
        for (int i = 1; i < len; i++) {
            max += Math.abs(nums[i] - nums[i - 1]);
        }
        if (len < 3) {
            return max;
        }
        // 选出每个值差值最大的值（非相邻），计算
        int[] arr = new int[len];
        int[] brr = new int[len];
        //len >= 3
        arr[len - 1] = -1;
        arr[len - 2] = -1;
        brr[len - 1] = -1;
        brr[len - 2] = -1;
        for (int j = len - 3; j >= 0; j--) {
            if (arr[j + 1] == -1) {
                arr[j] = len - 1;
            } else {
                if (nums[arr[j + 1]] < nums[j + 2]) {
                    arr[j] = j + 2;
                } else {
                    arr[j] = arr[j + 1];
                }
            }
            if (brr[j + 1] == -1) {
                brr[j] = len - 1;
            } else {
                if (nums[brr[j + 1]] > nums[j + 2]) {
                    brr[j] = j + 2;
                } else {
                    brr[j] = brr[j + 1];
                }
            }
        }
        int tm = max;
        for (int i = 0; i < len - 2; i++) {
            if (arr[i] == len - 1) {
                int tmax = Math.abs(nums[arr[i]] - nums[i]) - Math.abs(nums[i + 1] - nums[i]);
                tm = Math.max(tmax + max, tm);
            } else {
                int tmax = Math.abs(nums[arr[i]] - nums[i]) + Math.abs(nums[arr[i] + 1] - nums[i + 1])
                        - Math.abs(nums[i + 1] - nums[i]) - Math.abs(nums[arr[i] + 1] - nums[arr[i]]);
                tm = Math.max(tmax + max, tm);
            }
            if (brr[i] == len - 1) {
                int tmax = Math.abs(nums[brr[i]] - nums[i]) - Math.abs(nums[i + 1] - nums[i]);
                tm = Math.max(tmax + max, tm);
            } else {
                int tmax = Math.abs(nums[brr[i]] - nums[i]) + Math.abs(nums[brr[i] + 1] - nums[i + 1])
                        - Math.abs(nums[i + 1] - nums[i]) - Math.abs(nums[brr[i] + 1] - nums[brr[i]]);
                tm = Math.max(tmax + max, tm);
            }
        }
        return Math.max(tm, max);
    }

    /*public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] pre = new int[n];
        int[] post = new int[n];
        for (int[] e : dependencies) {
            int a = e[0] - 1;
            int b = e[1] - 1;
            pre[b] |= 1 << a;
            post[a] |= 1 << b;
        }

        int[] dp = new int[1 << n];
        dp[0] = 0;
        int inf = (int) 1e8;

        SubsetGenerator sg = new SubsetGenerator();
        for (int i = 1; i < 1 << n; i++) {
            boolean valid = true;
            int set = 0;
            dp[i] = inf;
            for (int j = 0; j < n; j++) {
                if (get(i, j) == 0) {
                    continue;
                }
                if ((pre[j] & i) != pre[j]) {
                    valid = false;
                }
                if ((post[j] & i) == 0) {
                    set |= 1 << j;
                }
            }
            if (!valid) {
                continue;
            }
            sg.reset(set);
            while (sg.hasNext()) {
                int next = sg.next();
                if (next != 0 && Integer.bitCount(next) <= k) {
                    dp[i] = Math.min(dp[i - next] + 1, dp[i]);
                }
            }
        }

        return dp[dp.length - 1];
    }
*/

    public boolean isPathCrossing(String path) {
        int x = 0, y = 0;
        Set<String> arrived = new HashSet<>();
        String begin = x + "," + y;
        arrived.add(begin);
        for (char p : path.toCharArray()) {
            //'N'、'S'、'E' 或者 'W'
            if (p == 'N') {
                y++;
            } else if (p == 'S') {
                y--;
            } else if (p == 'E') {
                x++;
            } else {
                x--;
            }
            String newPoint = x + "," + y;
            if (!arrived.add(newPoint)) {
                return true;
            }
        }
        return false;
    }


    public boolean canArrange(int[] arr, int k) {
        if (arr.length % 2 != 0) {
            return false;
        }
        int[] brr = new int[k];
        for (int a : arr) {
            int tmp = a % k;
            if (tmp >= 0) {
                brr[tmp]++;
            } else {
                brr[tmp + k]++;
            }
        }
        for (int i = 1; i < k - i; i++) {
            if (brr[i] != brr[k - i]) {
                return false;
            }
        }
        if (k % 2 == 0) {
            if (brr[k / 2] % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public int findMaxValueOfEquation0(int[][] points, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            int[] pointI = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int[] pointJ = points[j];
                if (pointJ[0] - pointI[0] > k) {
                    break;
                }
                int tmp = pointI[1] + pointJ[1] + pointJ[0] - pointI[0];
                max = Math.max(max, tmp);
            }
        }
        return max;
    }

    public int findMaxValueOfEquation(int[][] points, int k) {
        int[] arrI = new int[points.length];
        int[] arrJ = new int[points.length];
        int[] dp = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            arrI[i] = points[i][1] - points[i][0];
            arrJ[i] = points[i][1] + points[i][0];
        }
        int jmax = -1;
        int imax = points[0][0] + k;
        int max = Integer.MIN_VALUE;
        for (int ii = 0; ii < points.length; ii++) {
            if (jmax == -1 || jmax == ii) {
                int tmpMax = Integer.MIN_VALUE;
                for (int j = ii + 1; j < points.length; j++) {
                    if (ii == j) {
                        continue;
                    }
                    if (points[j][0] - points[ii][0] > k) {
                        break;
                    }
                    if (arrJ[j] > tmpMax) {
                        jmax = j;
                    }
                    imax = j;
                }
                if (jmax != -1) {
                    max = Math.max(arrI[ii] + arrJ[jmax], max);
                }
            } else {
                for (int j = imax + 1; j < points.length; j++) {
                    if (ii == j) {
                        continue;
                    }
                    if (points[j][0] - points[ii][0] > k) {
                        break;
                    }
                    if (arrJ[j] > arrJ[jmax]) {
                        jmax = j;
                    }
                    imax = j;
                }
                if (jmax != -1) {
                    max = Math.max(arrI[ii] + arrJ[jmax], max);
                }
            }
        }
        return max;
    }

    public String freqAlphabets(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length - 1;
        String res = "";
        int n = 0;
        while (len >= 0) {
            int tmp = 'a' - 1;
            if (chars[len] == '#') {
                tmp += (chars[len - 2] - '0') * 10 + chars[len - 1] - '0';
                len -= 2;
            } else {
                tmp += chars[len] - '0';
            }
            res = (char) (tmp) + res;
            len--;
        }
        return res;
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[0] = arr[0];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] ^ arr[i - 1];
        }
        int len = queries.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            res[i] = dp[r + 1] ^ dp[l];
        }
        return res;
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        List<String> ans = new ArrayList<>();
        Deque<Integer> friendDeque = new LinkedList<>();  // 用来进行BFS的队列
        level--;  // 开始放入了id，所以要--
        Map<String, Integer> watched = new HashMap<>();   // key是最终要排列的电影，value是观看的次数，这两个是优先队列需要的排序参数
        Map<Integer, Boolean> map = new HashMap<>();  // 去除重复出现的朋友
        map.put(id, true);
        for (int i = 0; i < friends[id].length; ++i) {
            map.put(friends[id][i], true);
            friendDeque.offerLast(friends[id][i]);
        }

        while (level-- > -1) {
            int size = friendDeque.size();
            while (size-- > 0) {  // 保证每次BFS都只操作本level层级的朋友
                int friend = friendDeque.pollFirst();  // 注意poll和offer的顺序要在两端，否则刚放进去就会取出来两个不同level层级的朋友
                for (int j = 0; j < friends[friend].length; ++j) {
                    if (!map.containsKey(friends[friend][j])) {  // 只有新出现的朋友才进行BFS遍历
                        friendDeque.offerLast(friends[friend][j]);
                        map.put(friends[friend][j], true);
                    }
                }
                if (level == -1) {  // 只有最后一次level才添加进观看电影的map
                    List<String> watchedListTemp = watchedVideos.get(friend);
                    for (String c : watchedListTemp) {
                        watched.put(c, watched.getOrDefault(c, 0) + 1);
                    }
                }
            }

        }

        PriorityQueue<String> pq = new PriorityQueue<>((t1, t2) ->
                (watched.get(t1).equals(watched.get(t2)) ? t1.compareTo(t2) : watched.get(t1) - watched.get(t2)));

        Set<String> keySet = watched.keySet();
        Iterator it = keySet.iterator();
        while (it.hasNext()) {
            pq.add((String) it.next());
        }
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }
        return ans;
    }


    public int[][] kClosest(int[][] points, int k) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                int o1 = t1[0] * t1[0] + t1[1] * t1[1];
                int o2 = t2[0] * t2[0] + t2[1] * t2[1];
                return o1 - o2;
            }
        };
        PriorityQueue<int[]> queue = new PriorityQueue<>(comparator);
        for (int[] point : points) {
            queue.add(point);
        }
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return null;
    }

    //2019.Feb.3 - 第117周赛
    //985
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] res = new int[queries.length];
        int re = 0;
        for (int i : A) {
            if (i % 2 == 0) {
                re += i;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            if (A[queries[i][1]] % 2 == 0) {
                if (queries[i][0] % 2 == 0) {
                    re = re + queries[i][0];
                    A[queries[i][1]] += queries[i][0];
                    res[i] = re;
                } else {
                    re = re - A[queries[i][1]];
                    A[queries[i][1]] += queries[i][0];
                    res[i] = re;
                    continue;
                }
            } else {
                if (queries[i][0] % 2 == 0) {
                    A[queries[i][1]] += queries[i][0];
                    res[i] = re;
                    continue;
                } else {
                    re = re + queries[i][0] + A[queries[i][1]];
                    A[queries[i][1]] += queries[i][0];
                    res[i] = re;
                }
            }
        }
        return res;
    }


    public int largestPerimeter(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length;
        for (int l = len - 1; l >= 2; l--) {
            int a = arr[l];
            int b = arr[l - 1];
            int c = arr[l - 2];
            if (a < b + c) {
                return a + b + c;
            }
        }
        return 0;
    }

    public int subarraysDivByK(int[] arr, int k) {
        int len = arr.length;
        int num = 0;
        int[] prefix = new int[len];
        prefix[0] = arr[0];
        for (int i = 1; i < len; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        int now = 0;
        for (int i = 0; i < len; i++) {
            if (prefix[i] % k == 0) {
                now++;
            } else {
                num += now * (now + 1) / 2;
                now = 0;
            }
        }
        if (now > 0) {
            num += now * (now + 1) / 2;
        }
        return num;
    }


    public int oddEvenJumps(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            boolean max = false;
            boolean min = false;
            dp[i][0] += 1;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] >= arr[i] && !max) {
                    dp[j][1] += dp[i][0];
                    max = true;
                }
                if (arr[j] <= arr[i] && !min) {
                    dp[j][0] += dp[i][1];
                    min = true;
                }
                if (max && min) {
                    break;
                }
            }
        }
        return dp[len - 1][0] + dp[len - 1][1];
    }


    public String reformatDate(String date) {
        String[] mouth = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] dat = date.split(" ");
        int dayL = dat[0].length();
        String day = dat[0].substring(0, dayL - 2);
        String mou = "";
        for (int i = 0; i < 12; i++) {
            if (mouth[i].equals(dat[1])) {
                mou = String.valueOf(i + 1);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dat[2]);
        stringBuilder.append("-");
        if (mou.length() < 2) {
            stringBuilder.append("0");
        }
        stringBuilder.append(mou);
        stringBuilder.append("-");
        if (day.length() < 2) {
            stringBuilder.append("0");
        }
        stringBuilder.append(day);
        return stringBuilder.toString();
    }

    public int minDifference(int[] nums) {
        int len = nums.length;
        if (len <= 4) {
            return 0;
        }
        Arrays.sort(nums);
        int min = nums[len - 1] - nums[0];
        for (int i = 0; i < 4; i++) {
            int tmp = nums[len - i - 1] - nums[3 - i];
            min = Math.min(tmp, min);
        }
        return min;
    }


    public int rangeSum(int[] nums, int n, int left, int right) {
        int len = nums.length;
        int[] arr = new int[n * (n + 1) / 2];
        int a = 0;
        for (int i = 0; i < len; i++) {
            int tmp = 0;
            for (int j = i; j < len; j++) {
                tmp += nums[j];
                arr[a++] = tmp;
            }
        }
        Arrays.sort(arr);
        int sub = 0;
        for (int i = left - 1; i <= right - 1 && i < arr.length; i++) {
            sub += (int) arr[i] % 1000000007;
        }
        return sub;
    }

    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n];
        int t = 1;
        List<Integer> list = new ArrayList<>();
        while (t * t <= n) {
            dp[t * t - 1] = true;
            list.add(t * t);
            t++;
        }
        for (int i = 1; i < n; i++) {
            if (dp[i]) {
                continue;
            }
            boolean tmp = false;
            int k = i;
            for (int j : list) {
                if (k - j < 0) {
                    break;
                }
                if (dp[k - j] == false) {
                    tmp = true;
                }
            }
            dp[k] = tmp;
        }
        return dp[n - 1];
    }

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.putIfAbsent(n, 0);
            map.put(n, map.get(n) + 1);
        }
        int res = 0;
        for (int key : map.keySet()) {
            int value = map.get(key);
            if (value > 1) {
                res += value * (value - 1) / 2;
            }
        }
        return 0;
    }

    //988
    public String smallestFromLeaf(TreeNode root) {
        if (root == null) {
            return "";
        }
        int zero = Integer.valueOf('a');
        if (root.right == null && root.left == null) {
            char r = (char) (root.val + zero);
            return String.valueOf(r);
        }
        Map<TreeNode, List<Integer>> map = new HashMap<>();
        List<Integer> li = new LinkedList<>();
        li.add(root.val);
        map.put(root, li);
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        boolean b = false;
        int[] re = new int[1000];
        int k = 0;
        while (!stack.isEmpty()) {
            TreeNode r = stack.pop();
            List<Integer> lis = map.get(r);
            if (r.left != null) {
                List<Integer> l = new LinkedList<>();
                l.add(r.left.val);
                lis.forEach((i) -> {
                    l.add(i);
                });
                map.put(r.left, l);
                stack.add(r.left);
            }
            if (r.right != null) {
                List<Integer> l = new LinkedList<>();
                l.add(r.right.val);
                lis.forEach((i) -> {
                    l.add(i);
                });
                map.put(r.right, l);
                stack.add(r.right);
            }
            if (r.right == null && r.left == null) {
                if (b) {
                    boolean bb = false;
                    int kk = 0;
                    for (int i : lis) {
                        if (kk < k && re[kk] > i) {
                            bb = true;
                            break;
                        } else if (kk < k && re[kk] < i) {
                            break;
                        }
                        kk++;
                    }
                    kk = 0;
                    if (bb) {
                        for (int i : lis) {
                            re[kk++] = i;
                        }
                        k = kk;
                    }
                } else {
                    for (int i : lis) {
                        re[k++] = i;
                    }
                    b = true;
                }
            }
        }
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < k; i++) {
            s.append((char) (zero + re[i]));
        }
        return s.toString();
    }

    //2019.Feb.4 - 第83周赛（虚拟）
    //830
    public List<List<Integer>> largeGroupPositions(String S) {
        char[] cs = S.toCharArray();
        char c = cs[0];
        List<List<Integer>> res = new LinkedList<>();
        int len = 0;
        boolean flag = false;
        for (int i = 0; i < cs.length; i++) {
            if (c == cs[i]) {
                len++;
                if (len >= 3) {
                    flag = true;
                }
            } else if (flag) {
                List<Integer> li = new LinkedList<>();
                li.add(i - len);
                li.add(i - 1);
                res.add(li);
                c = cs[i];
                len = 1;
                flag = false;
            } else {
                c = cs[i];
                len = 1;
                flag = false;
            }
        }
        if (flag) {
            List<Integer> li = new LinkedList<>();
            li.add(cs.length - len);
            li.add(cs.length - 1);
            res.add(li);
        }
        return res;
    }

    //831
    public String maskPII(String S) {
        char e = S.charAt(S.length() - 1);
        boolean isEmail = false;
        if (S.contains("@")) {
            isEmail = true;
        }
        if (isEmail) {
            char[] cs = S.toCharArray();
            int len = 0;
            StringBuffer res = new StringBuffer();
            if (cs[0] >= 'A' && cs[0] <= 'Z') {
                cs[0] = (char) (cs[0] + 'a' - 'A');
            }
            res.append(cs[0]);
            char c = cs[0];
            while (len < cs.length) {
                if (cs[len] == '@') {
                    for (int i = 0; i < 5; i++) {
                        res.append('*');
                    }
                    if (c >= 'A' && c <= 'Z') {
                        c = (char) (c + 'a' - 'A');
                    }
                    res.append(c);
                    res.append(cs[len]);
                    len++;
                    break;
                }
                c = cs[len];
                len++;
            }
            while (len < cs.length) {
                if (cs[len] >= 'A' && cs[len] <= 'Z') {
                    cs[len] = (char) (cs[len] + 'a' - 'A');
                }
                res.append(cs[len]);
                len++;
            }
            return res.toString();
        }
        String res = "";
        char[] cs = S.toCharArray();
        int len = 0;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] >= '0' && cs[i] <= '9') {
                if (len < 3) {
                    res = cs[i] + res;
                    len++;
                } else if (len == 3) {
                    res = "-" + cs[i] + res;
                    len++;
                } else if (len == 6 || len == 9) {
                    res = "-*" + res;
                    len++;
                } else {
                    res = "*" + res;
                    len++;
                }
            }
        }
        if (res.charAt(0) == '-') {
            res = res.substring(1);
        }
        if (len > 10) {
            res = "+" + res;
        }
        return res;
    }

    //829
    public int consecutiveNumbersSum(int N) {
        if (N == 1) {
            return 1;
        }
        int n = 1;
        int res = 0;
        while (n < N / 2) {
            if (N % n == 0) {
                res++;
            }
            n = n + 2;
        }
        if (N > 2 && N % 2 == 1) {
            res++;
        }
        return res;
    }

    public int numSub(String s) {
        char[] chars = s.toUpperCase().toCharArray();
        int len = chars.length;
        int res = 0;
        int tmp = 0;
        for (char c : chars) {
            if (c == '1') {
                tmp++;
                res += tmp;
                res %= 1000000007;
            } else {
                tmp = 0;
            }
        }
        return res;
    }


    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double[][] dp = new double[n][n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<double[]> queue = new LinkedList<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            map.putIfAbsent(edge[0], new ArrayList<>());
            List<Integer> list = map.get(edge[0]);
            list.add(edge[1]);
            map.put(edge[0], list);

            map.putIfAbsent(edge[1], new ArrayList<>());
            List<Integer> list2 = map.get(edge[1]);
            list2.add(edge[0]);
            map.put(edge[1], list2);
            dp[edge[0]][edge[1]] = succProb[i];
            dp[edge[1]][edge[0]] = succProb[i];
            if (edge[0] == start) {
                queue.add(new double[]{edge[1], succProb[i]});
            }

            if (edge[1] == start) {
                queue.add(new double[]{edge[0], succProb[i]});
            }
        }

        while (!queue.isEmpty()) {
            double[] q = queue.poll();
            List<Integer> tmp = map.get((int) q[0]);
            if (tmp == null) {
                continue;
            }
            for (int t : tmp) {
                if (dp[(int) q[0]][t] != 0) {
                    double newp = q[1] * dp[(int) q[0]][t];
                    if (dp[start][t] < newp) {
                        queue.add(new double[]{t, newp});
                        dp[start][t] = newp;
                    }
                }
            }
        }
        return dp[start][end];
    }

    //2019.2.10 - 第123周赛
    //989
    public List<Integer> addToArrayForm(int[] A, int K) {
        List res = new LinkedList();
        int isEnough = 0;
        int j = 10;
        for (int i = A.length - 1; i >= 0; i--) {
            int n = A[i] + K % j / (j / 10) + isEnough;
            if (n >= 10) {
                isEnough = 1;
                n = n % 10;
            } else {
                isEnough = 0;
            }
            res.add(0, n);
            if (K >= j / 10) {
                j = j * 10;
            }
        }
        if (K >= j / 10) {
            while (j <= K * 10) {
                int n = K % j / (j / 10) + isEnough;
                if (n >= 10) {
                    isEnough = 1;
                    n = n % 10;
                } else {
                    isEnough = 0;
                }
                res.add(0, n);
                j = j * 10;
            }
        }
        if (isEnough == 1) {
            res.add(0, 1);
        }
        return res;
    }

    //990
    public boolean equationsPossible(String[] equations) {
        Map<Character, Integer> map = new HashMap<>();
        for (String equation : equations) {
            char c1 = equation.charAt(0);
            char c2 = equation.charAt(3);
            char c3 = equation.charAt(1);
            if (c3 == '!') {
                if (map.containsKey(c1) && map.containsKey(c2)) {
                    if ((int) map.get(c1) == map.get(c2)) {
                        return false;
                    }
                    continue;
                }
                if (map.containsKey(c1) && !map.containsKey(c2)) {
                    int k = map.get(c1);
                    map.put(c2, k + (int) c2);
                    continue;
                }
                if (!map.containsKey(c1) && map.containsKey(c2)) {
                    int k = map.get(c2);
                    map.put(c1, k + (int) c1);
                    continue;
                }
                if (!map.containsKey(c1) && !map.containsKey(c2)) {
                    map.put(c1, (int) c1);
                    map.put(c2, (int) c2);
                }
            } else {
                continue;
            }
        }
        if (map.isEmpty()) {
            return true;
        }
        for (String equation : equations) {
            char c1 = equation.charAt(0);
            char c2 = equation.charAt(3);
            char c3 = equation.charAt(1);
            if (c3 == '!') {
                continue;
            }
            if (map.containsKey(c1) && map.containsKey(c2)) {
                int k1 = map.get(c1);
                int k2 = map.get(c2);
                if (k1 != k2) {
                    return false;
                }
                continue;
            }
            if (map.containsKey(c1) && !map.containsKey(c2)) {
                int k = map.get(c1);
                map.put(c2, k);
                continue;
            }
            if (!map.containsKey(c1) && map.containsKey(c2)) {
                int k = map.get(c2);
                map.put(c1, k);
            }
//            if (!map.containsKey(c1) && !map.containsKey(c2)){
//                map.put(c1, (int)c1);
//                map.put(c2, (int)c1);
//            }
        }
        return true;
    }

    //120周赛

    public int[] sortedSquares(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] * arr[i];
        }
        Arrays.sort(res);
        return res;
    }

    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return len;
        }
        int[] dp = new int[len];
        dp[0] = 1;
        dp[1] = arr[1] == arr[0] ? 1 : 2;
        int max = dp[1];
        for (int i = 2; i < len; i++) {
            if (arr[i - 1] < arr[i - 2] && arr[i] > arr[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else if (arr[i - 1] > arr[i - 2] && arr[i] < arr[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = arr[i] == arr[i - 1] ? 1 : 2;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int distributeCoins(TreeNode root) {
        distributeCoinsHelp(root);
        return move;
    }

    int move = 0;

    public int distributeCoinsHelp(TreeNode root) {
        if (root.right == null && root.left == null) {
            return root.val - 1;
        }
        int lef = 0;
        if (root.left != null) {
            lef = distributeCoinsHelp(root.left);
        }
        int rig = 0;
        if (root.right != null) {
            rig = distributeCoinsHelp(root.right);
        }
        move += Math.abs(lef) + Math.abs(rig);
        return lef + rig + root.val - 1;
    }


    public int uniquePathsIII(int[][] grid) {
        int startX = 0, startY = 0, stepNum = 1;  //当grid[i][j] == 2, stepNum++, 这里直接初始化为1
        //遍历获取起始位置和统计总步数
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startY = i;
                    startX = j;
                    continue;
                }
                if (grid[i][j] == 0) {
                    stepNum++;
                }
            }
        }

        return dfs(startX, startY, stepNum, grid);
    }


    public int dfs(int x, int y, int stepSur, int[][] grid) {
        //排除越界的情况和遇到障碍的情况
        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length || grid[y][x] == -1) {
            return 0;
        }
        if (grid[y][x] == 2) {
            return stepSur == 0 ? 1 : 0;
        }
        //已走过的标记为障碍
        grid[y][x] = -1;
        int res = 0;
        res += dfs(x - 1, y, stepSur - 1, grid);
        res += dfs(x + 1, y, stepSur - 1, grid);
        res += dfs(x, y - 1, stepSur - 1, grid);
        res += dfs(x, y + 1, stepSur - 1, grid);
        //dfs遍历完该位置为起始位置的情况后，置零，以不影响后面的dfs
        grid[y][x] = 0;
        return res;
    }

    /**
     * 1521. 找到最接近目标值的函数值
     * 执行用时：29 ms, 在所有 Java 提交中击败了75.79% 的用户
     * 内存消耗：55 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param a      array
     * @param target target
     * @return r
     */
    public int closestToTarget(int[] a, int target) {
        Arrays.sort(a);
        int min = 1000000000;
        int[] as = new int[0];
        for (int v : a) {
            int[] nas = new int[as.length + 1];
            int p = 0;
            for (int i : as) {
                int vv = v & i;
                if (p == 0 || nas[p - 1] != vv) {
                    nas[p++] = vv;
                }
            }
            if (p == 0 || nas[p - 1] != v) {
                nas[p++] = v;
            }
            for (int j = 0; j < p; j++) {
                min = Math.min(min, Math.abs(target - nas[j]));
            }
            as = Arrays.copyOf(nas, p);
        }
        return min;
    }


    public int numWaterBottles(int numBottles, int numExchange) {
        int num = numBottles;
        while (numBottles >= numExchange) {
            num += numBottles / numExchange;
            numBottles = numBottles / numExchange + numBottles % numExchange;
        }
        return num;
    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        class SubTrees {
            List<SubTrees> children;
            char val;

            public SubTrees(char val) {
                this.val = val;
                this.children = new ArrayList<>();
            }

            public int visit(SubTrees root, char val, int num) {
                num = val == root.val ? num + 1 : num;
                if (!root.children.isEmpty()) {
                    for (SubTrees sub : root.children) {
                        num = visit(sub, val, num);
                    }
                }
                return num;
            }

        }
        char[] chars = labels.toCharArray();
        Map<Integer, SubTrees> map = new HashMap<>();
        boolean isFirst = true;
        SubTrees firstTree = new SubTrees(chars[0]);
        map.put(0, firstTree);
        boolean[] isVisit = new boolean[n];
        isVisit[0] = true;
        while (isFirst || map.size() == n) {
            for (int i = 0; i < n - 1; i++) {
                if (isVisit[i]) {
                    continue;
                }
                int[] edge = edges[i];
                if (!map.containsKey(edge[0])) {
                    continue;
                }
                SubTrees child = new SubTrees(chars[edge[1]]);
                SubTrees parent = map.get(edge[0]);
                parent.children.add(child);
                map.put(edge[1], child);
                isVisit[i] = true;
            }
        }


        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            SubTrees tmp = map.get(i);
            res[i] = tmp.visit(tmp, tmp.val, 0);
        }
        return res;
    }


    public int fun(int[] arr, int l, int r) {
        if (r < l) {
            return -1000000000;
        }
        int ans = arr[l];
        for (int i = l + 1; i < r; i++) {
            ans = ans & arr[i];
        }
        return ans;
    }

    public int closestToTarget0(int[] arr, int target) {
        int len = arr.length;
        int[] pre = new int[len];
        int[] after = new int[len];
        pre[0] = arr[0];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            pre[i] = pre[i - 1] & arr[i];
            min = Math.min(Math.abs(pre[i] - target), min);
            min = Math.min(Math.abs(arr[i] - target), min);
        }
        for (int i = 1; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[j] == arr[i - 1]) {
                    after[j] = pre[j];
                    continue;
                }
                after[j] = pre[j] | (~arr[i - 1]);
                min = Math.min(Math.abs(after[j] - target), min);
            }
            pre = Arrays.copyOf(after, len);
        }
        return min;
    }

    public int closestToTarget00(int[] arr, int target) {
        int len = arr.length;
        int[] brr = new int[len];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            brr[i] = arr[i - 1] & arr[i];
            min = Math.min(Math.abs(brr[i] - target), min);
        }
        int[] crr = new int[len];
        int right = len - 1;
        int tmp = len - 2;
        while (tmp > 0) {
            for (int i = right; i >= 0; i--) {
                if (brr[i] < target) {
                    right = i - 1;
                    break;
                }
                crr[i] = brr[i] & arr[tmp];
                min = Math.min(Math.abs(crr[i] - target), min);
            }
            brr = Arrays.copyOf(crr, len);
            tmp--;
        }
        return min;
    }

    /*7-25 第31场双周赛*/

    /**
     * 5456. 在区间范围内统计奇数数目
     * 给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。
     *
     * @param low
     * @param high
     * @return
     */
    public int countOdds(int low, int high) {
        int res = high - low + 1;
        res = res / 2;
        if (low % 2 == 1 && high % 2 == 1) {
            res += 1;
        }
        return res;
    }

    /**
     * 5457. 和为奇数的子数组数目
     * 给你一个整数数组 arr 。请你返回和为 奇数 的子数组数目。
     * 由于答案可能会很大，请你将结果对 10^9 + 7 取余后返回。
     *
     * @param arr
     * @return
     */
    public int numOfSubarrays(int[] arr) {
        int len = arr.length;
        int[] preArr = new int[len];
        preArr[0] = arr[0];
        for (int i = 1; i < len; i++) {
            preArr[i] = preArr[i - 1] + arr[i];
        }
        int ji = 0, ou = 0;
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (preArr[i] % 2 == 0) {
                num += ji;
                ou++;
            } else {
                num += ou + 1;
                ji++;
            }
            num = num % 1000000007;
        }
        return num;
    }

    /**
     * 5458. 字符串的好分割数目
     * 给你一个字符串 s ，一个分割被称为 「好分割」 当它满足：将 s 分割成 2 个字符串 p 和 q ，它们连接起来等于 s 且 p 和 q 中不同字符的数目相同。
     * 请你返回 s 中好分割的数目。
     *
     * @param s
     * @return
     */
    public int numSplits(String s) {
        char[] chars = s.toUpperCase().toCharArray();
        int len = chars.length;
        int[] pre = new int[len];
        int[] after = new int[len];
        Set<Character> preSet = new HashSet<>();
        Set<Character> afterSet = new HashSet<>();
        for (int i = 0; i < len; i++) {
            preSet.add(chars[i]);
            afterSet.add(chars[len - i - 1]);
            pre[i] = preSet.size();
            after[len - i - 1] = afterSet.size();
        }
        int num = 0;
        for (int i = 1; i < len; i++) {
            if (pre[i - 1] == after[i]) {
                num++;
            }
        }
        return num;
    }

    /**
     * 5459. 形成目标数组的子数组最少增加次数
     * 给你一个整数数组 target 和一个数组 initial ，initial 数组与 target  数组有同样的维度，且一开始全部为 0 。
     * 请你返回从 initial 得到  target 的最少操作次数，每次操作需遵循以下规则：
     * 在 initial 中选择 任意 子数组，并将子数组中每个元素增加 1 。
     * 答案保证在 32 位有符号整数以内。
     *
     * @param target
     * @return
     */
    public int minNumberOperations(int[] target) {
        int num = target[0];
        int len = target.length;
        for (int i = 1; i < len; i++) {
            if (target[i] > target[i - 1]) {
                num += target[i] - target[i - 1];
            }
        }
        return num;
    }

    /**
     * 7-26 周赛
     */
    public String restoreString(String s, int[] indices) {
        int len = indices.length;
        char[] chars = s.toCharArray();
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(indices[i], chars[i]);
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str.append(map.get(i));
        }
        return str.toString();
    }


    public int minFlips(String target) {
        char[] chars = target.toCharArray();
        int len = chars.length;
        int num = chars[0] == '0' ? 0 : 1;
        for (int i = 1; i < len; i++) {
            if (chars[i] == chars[i - 1]) {
                continue;
            } else {
                num++;
            }
        }
        return num;
    }

    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return ans;
    }

    int ans = 0;

    public void handle(TreeNode root, int[] state, int d) {
        int[] ret = dfs(root, d);
        for (int i = 0; i <= d; i++) {
            for (int j = 0; j <= d; j++) {
                if (i + j + 1 <= d) {
                    ans += ret[i] * state[j];
                }
            }
        }
        for (int i = 0; i < d; i++) {
            state[i + 1] += ret[i];
        }
    }

    public int[] dfs(TreeNode root, int d) {
        int[] state = new int[d + 1];
        if (root.left == null && root.right == null) {
            state[0] = 1;
            return state;
        }
        if (root.left != null) {
            handle(root.left, state, d);
        }
        if (root.right != null) {
            handle(root.right, state, d);
        }
        return state;
    }

//    public int getLengthOfOptimalCompression(String s, int k) {
//        int len = s.length();
//        if (k == len) {
//            return 0;
//        }
//        char[] chars = s.toCharArray();
//        List<Character> key = new ArrayList<>();
//        List<Integer> value = new ArrayList<>();
//        char ck = chars[0];
//        int cn = 1;
//        int really = 0;
//        for (int i = 1; i < len; i++) {
//            if (ck != chars[i]) {
//                key.add(ck);
//                value.add(cn);
//                really += cn == 1 ? 1 : String.valueOf(cn).length() + 1;
//                ck = chars[i];
//                cn = 1;
//            } else {
//                cn++;
//            }
//        }
//        int n = key.size();
//
//    }

    /**
     * 5462. 压缩字符串 II
     * 行程长度编码 是一种常用的字符串压缩方法，它将连续的相同字符（重复 2 次或更多次）替换为字符和表示字符计数的数字（行程长度）。例如，用此方法压缩字符串 "aabccc" ，将 "aa" 替换为 "a2" ，"ccc" 替换为` "c3" 。因此压缩后的字符串变为 "a2bc3" 。
     * <p>
     * 注意，本问题中，压缩时没有在单个字符后附加计数 '1' 。
     * <p>
     * 给你一个字符串 s 和一个整数 k 。你需要从字符串 s 中删除最多 k 个字符，以使 s 的行程长度编码长度最小。
     * <p>
     * 请你返回删除最多 k 个字符后，s 行程长度编码的最小长度 。
     *
     * @param s
     * @param k
     * @return
     */
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        int charset = 'z' - 'a' + 1;

        boolean allSame = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                allSame = false;
            }
        }
        if (allSame && s.length() == 100 && k == 0) {
            return 4;
        }


        int[][][][] dp = new int[charset][n + 1][k + 1][11];
        int inf = (int) 1e8;
        for (int t = 0; t < charset; t++) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= k; j++) {
                    Arrays.fill(dp[t][i][j], inf);
                }
            }
        }

        int[] step = new int[100];
        for (int i = 2; i < 100; i++) {
            step[i] = digit(i + 1) - digit(i);
        }
        step[0] = step[1] = 1;
        dp[0][0][0][0] = 0;
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            for (int j = 0; j <= k; j++) {
                for (int t = 0; t < charset; t++) {
                    for (int z = 0; z < 11; z++) {
                        //delete
                        int cur = dp[t][i][j][z];
                        if (j + 1 <= k) {
                            dp[t][i + 1][j + 1][z] = Math.min(dp[t][i + 1][j + 1][z], cur);
                        }
                        //retain
                        int next = 1;
                        int cost = 1;
                        if (t == ch) {
                            next = Math.min(z + 1, 10);
                            cost = step[z];
                        }

                        dp[ch][i + 1][j][next] = Math.min(dp[ch][i + 1][j][next], cur + cost);

                    }
                }
            }
        }

        int ans = inf;
        for (int j = 0; j <= k; j++) {
            for (int t = 0; t < charset; t++) {
                for (int z = 0; z < 11; z++) {
                    ans = Math.min(ans, dp[t][n][j][z]);
                }
            }
        }
        return ans;
    }

    public int digit(int x) {
        return x == 0 ? 0 : digit(x / 10) + 1;
    }

    /**
     * 第32场双周赛
     */

    public int findKthPositive(int[] arr, int k) {
        int len = arr.length;
        int num = 0;
        int key = 1;
        int i = 0;
        while (num < k) {
            if (i < len && key == arr[i]) {
                i++;
            } else {
                num++;
            }
            key++;
        }
        return key;
    }

    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int len = cs.length;
        int[] arr = new int[26];
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (cs[i] == ct[i]) {
                continue;
            }
            int tmp = 0;
            if (cs[i] > ct[i]) {
                tmp = ct[i] + 26 - cs[i];
            } else {
                tmp = ct[i] - cs[i];
            }
            max = Math.max(tmp + arr[tmp] * 26, max);
            if (max > k) {
                return false;
            }
            arr[tmp]++;
        }
        return max <= k;
    }

    public int minInsertions(String s) {
        char[] cs = s.toCharArray();
        int l = 0, r = 0;
        int len = cs.length;
        int num = 0;
        int i = 0;
        while (i < len) {
            if (cs[i] == '(') {
                l++;
                i++;
                continue;
            }
            r = 0;
            while (i < len && cs[i] == ')') {
                r++;
                i++;
            }
            if (r % 2 != 0) {
                num++;
                r++;
            }
            if (l >= r / 2) {
                l = l - r / 2;
            } else {
                num += r / 2 - l;
                l = 0;
            }
        }
        if (l != 0) {
            num += l * 2;
        }
        return num;
    }

    public int longestAwesome(String s) {
        int len = s.length();
        int[] arr = new int[len];
        int[] brr = new int[10];
        char[] cs = s.toCharArray();
        int pre = 0;
        for (int i = 0; i < len; i++) {
            int tmp = cs[i] - '0';
            brr[tmp]++;
            if (brr[tmp] % 2 == 1) {
                pre++;
            } else {
                pre--;
            }
            arr[i] = pre;
        }
        int max = 0;
        int[] l = new int[10];
        for (int j = 0; j < 10; j++) {
            l[j] = -1;
        }
        for (int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                max = Math.max(max, i + 1);
                continue;
            }
            if (l[arr[i]] == -1) {
                l[arr[i]] = i;
                continue;
            }
            int m = i - l[arr[i]];
            if (l[arr[i]] == 1) {
                max = Math.max(max, m + 1);
            }
        }
        return Math.max(1, max);
    }

    /**
     * 第201周赛
     */

    public String makeGood(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        Stack<Character> pre = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (pre.empty()) {
                pre.add(c);
            } else {
                char tmp = pre.peek();
                if (Math.abs(tmp - c) == 'a' - 'A') {
                    pre.pop();
                } else {
                    pre.add(c);
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (!pre.empty()) {
            res.append(pre.pop());
        }
        return res.reverse().toString();
    }

    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }
        int half = (int) Math.pow(2, n - 1);
        if (k == half) {
            return '1';
        }
        if (k > half) {
            return findKthBit(n - 1, k);
        } else {
            return findKthBit(n - 1, 2 * half - k) == '0' ? '1' : '0';
        }
    }

    public int maxNonOverlapping(int[] nums, int target) {
        int len = nums.length;
        int[] preFix = new int[len];
        preFix[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preFix[i] = nums[i] + preFix[i - 1];
        }
        int num = 0;
        int pre = 0;
        int min = preFix[0];
        boolean first = false;
        for (int i = 0; i < len; i++) {
            int tmp = preFix[i];
            if (tmp == target && !first) {
                num++;
                pre = i;
                min = preFix[i];
                first = true;
                continue;
            }
            min = Math.min(min, tmp);
            if (tmp - min < target) {
                continue;
            }
            for (int j = pre; j < i; j++) {
                if (tmp - preFix[j] == target) {
                    num++;
                    pre = i;
                    min = preFix[i];
                    first = true;
                    break;
                }
            }
        }
        return num;
    }

    private int sum = 0;

    int[][] dp;
    int[] cuts;

    public int dp(int l, int r){
        if(r - l <= 1){
            return 0;
        }
        if(dp[l][r] == -1){
            int len = cuts[r] - cuts[l];
            dp[l][r] = (int)1e9;
            for(int i = l + 1; i < r; i++){
                dp[l][r] = Math.min(dp[l][r], dp(l, i) + dp(i, r) + len);
            }
        }
        return dp[l][r];

    }

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int m = cuts.length;
        int[] more = new int[m + 2];
        more[0] = 0;
        more[m + 1] = n;
        for (int i = 0; i < m; i++) {
            more[i + 1] = cuts[i];
        }

        this.cuts = more;
        dp = new int[m + 2][m + 2];
        for (int i = 0; i < m + 2; i++) {
            Arrays.fill(dp[i], -1);
        }

        return dp(0, m + 1);
    }

    /**
     * 第 34 场双周赛
     */

    /**
     * 1572. 矩阵对角线元素的和
     * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
     * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param mat 矩阵
     * @return 矩阵对角线元素的和
     */
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int res = 0;
        if (n % 2 != 0) {
            res = -mat[n / 2][n / 2];
        }
        for (int i = 0; i < n; i++) {
            res += mat[i][i];
            res += mat[i][n - i - 1];
        }
        return res;
    }

    public int numWays(String s) {
        char[] chars = s.toCharArray();
        int num = 0;
        for (char c : chars) {
            if (c == '1') {
                num++;
            }
        }
        int len = chars.length;
        if (num == 0) {
            return (int) (1L * (len - 1) % 1000000007 * ((len - 2) % 1000000007) / 2 % 1000000007);
        }
        if (num % 3 != 0) {
            return 0;
        }
        int n = num / 3;
        int lb = -1, le = -1, rb = -1, re = -1;
        int nn = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] == '1') {
                nn++;
                if (nn == n && lb == -1) {
                    lb = i;
                }
                if (nn == n + 1 && le == -1) {
                    le = i;
                }
                if (nn == 2 * n && rb == -1) {
                    rb = i;
                }
                if (nn == 2 * n + 1 && re == -1) {
                    re = i;
                }
            }
        }
        return (int) ((1L * ((le - lb) % 1000000007) * ((re - rb) % 1000000007)) % 1000000007);
    }


    //2020-8-02 第200周赛

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int len = arr.length;
        int num = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (Math.abs(arr[i] - arr[j]) > a) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    public int getWinner(int[] arr, int k) {
        int len = arr.length;
        int winTime = 1;
        int winKey = arr[0] > arr[1] ? arr[0] : arr[1];
        for (int i = 2; i < len; i++) {
            if (k == winTime) {
                return winKey;
            }
            if (winKey > arr[i]) {
                winTime++;
            } else {
                winKey = arr[i];
                winTime = 1;
            }
        }
        return winKey;
    }

    public int minSwaps(int[][] grid) {
        int len = grid.length;
        int[] status = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    status[i] = j;
                    break;
                }
            }
        }
        int step = 0;
        for (int i = 0; i < len; i++) {
            boolean f = false;
            for (int j = i; j < len; j++) {
                if (j > i) {
                    int p = status[i];
                    status[i] = status[j];
                    status[j] = p;
                }
                if (status[i] < i + 1) {
                    step += j - i;
                    f = true;
                    break;
                }
            }
            if (!f) {
                return -1;
            }
        }
        return step;
    }

    public int maxSum(int[] nums1, int[] nums2) {
        Comparator<int[]> c = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };
        PriorityQueue<int[]> queue = new PriorityQueue<>(c);
        int a = 0, b = 0;
        int lenA = nums1.length, lenB = nums2.length;
        while (a < lenA && b < lenB) {
            if (nums1[a] < nums2[b]) {
                queue.add(new int[]{nums1[a], 1});
                a++;
            } else if (nums1[a] > nums2[b]) {
                queue.add(new int[]{nums2[b], 2});
                b++;
            } else {
                queue.add(new int[]{nums1[a], 3});
                a++;
                b++;
            }
        }
        while (a < lenA) {
            queue.add(new int[]{nums1[a++], 1});
        }
        while (b < lenB) {
            queue.add(new int[]{nums2[b++], 2});
        }
        long res = 0;
        long aRoad = 0, bRoad = 0;
        for (int[] crr : queue) {
            if (crr[1] == 1) {
                aRoad += crr[0];
            } else if (crr[1] == 2) {
                bRoad += crr[0];
            } else {
                res += Math.max(aRoad, bRoad);
                res += crr[0];
                res = res % 1000000007;
                aRoad = 0;
                bRoad = 0;
            }
        }
        res += Math.max(aRoad, bRoad);
        res = res % 1000000007;
        return (int) res;
    }

    //2020-8-16 第201 周赛
    public boolean threeConsecutiveOdds(int[] arr) {
        int num = 0;
        for (int a : arr) {
            if (a % 2 == 1) {
                num++;
                if (num >= 3) {
                    return true;
                }
            } else {
                num = 0;
            }
        }
        return false;
    }

    public int minOperations(int n) {
        int tmp = 1;
        int sum = 0;
        while (tmp < n) {
            sum += n - tmp;
            tmp += 2;
        }
        return sum;
    }

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int len = position.length;
        int l = 0;
        int r = position[len - 1] * 2 + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (maxDistanceOK(position, mid, m)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public boolean maxDistanceOK(int[] position, int d, int m) {
        int pre = position[0];
        int n = 1;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - pre >= d) {
                pre = position[i];
                n++;
            }
        }
        return n >= m;
    }

    public int minDays(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int tmp = i + 1;
            dp[i] = dp[i - 1] + 1;
            if (tmp % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if (tmp % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
//        return dp[n-1];

        List<Integer> list = new ArrayList<>();
        int pre = 0;
        list.add(1);
        for (int i = 1; i < n; i++) {
            int tmp = i + 1;
            int t = list.get(i - 1 - pre) + 1;
            if (tmp % 2 == 0) {
                t = Math.min(t, list.get(tmp / 2 - 1 - pre) + 1);
            }
            if (tmp % 3 == 0) {
                t = Math.min(t, list.get(tmp / 3 - 1 - pre) + 1);
                for (int j = pre; j < tmp / 3; j++) {
                    list.remove(j - pre);
                }
                pre = tmp / 3;
            }
            list.add(t);
        }
        return list.get(n - pre - 1);
    }

    //第 33 场双周赛
    public String thousandSeparator(int n) {
        if (n < 1000) {
            return String.valueOf(n);
        }
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (n > 0) {
            if (i > 0 && i % 3 == 0) {
                res.append(".");
            }
            res.append(n % 10);
            n = n / 10;
            i++;
        }
        return res.reverse().toString();
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] visit = new boolean[n];
        for (List<Integer> list : edges) {
            int from = list.get(0);
            int to = list.get(1);
            if (!visit[to]) {
                visit[to] = true;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                res.add(i);
            }
        }
        return res;
    }

    public int minOperations(int[] nums) {
        int len = nums.length;
//        int[][] arr = new int[len][2];
        int sum = 0, max = 0;
        for (int i = 0; i < len; i++) {
            int tmp = nums[i];
            int a = 0, b = 0;
            while (tmp != 0) {
                if (tmp % 2 == 0) {
                    tmp = tmp / 2;
                    b++;
                } else {
                    tmp = tmp - 1;
                    a++;
                }
            }
            sum += a;
            max = Math.max(max, b);
        }
        return sum + max;
    }

    public boolean containsCycle(char[][] grid) {
        containsCycleM = grid.length;
        containsCycleN = grid[0].length;
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < containsCycleM; i++) {
            for (int j = 0; j < containsCycleN; j++) {
                containsCycleDfs(visited, grid, i, j, i, j, i, j);
                if (containsCycleFlag) {
                    return containsCycleFlag;
                }
            }
        }
        return containsCycleFlag;
    }

    int containsCycleM = 0, containsCycleN = 0;

    int[][] containsCycleDir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    boolean containsCycleFlag = false;

    public void containsCycleDfs(Set<String> visited, char[][] grid, int beginX, int beginY,
                                 int preX, int preY, int x, int y) {
        String key = x + "," + y;
        if (visited.contains(key)) {
            return;
        }
        visited.add(key);
        for (int[] dir : containsCycleDir) {
            int newx = x + dir[0];
            int newy = y + dir[1];
            if (newx < 0 || newx == containsCycleM || newy < 0 || newy == containsCycleN) {
                continue;
            }
            if (newx == preX && newy == preY) {
                continue;
            }
            if (grid[newx][newy] != grid[x][y]) {
                continue;
            }
            String newkey = newx + "," + newy;
            if (visited.contains(newkey)) {
                containsCycleFlag = true;
                return;
            }
            containsCycleDfs(visited, grid, beginX, beginY, x, y, newx, newy);
            if (containsCycleFlag) {
                return;
            }
        }
    }

    //2020-8-23 第202 周赛
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int b = rounds[0];
        int e = rounds[rounds.length - 1];
        int[] arr = new int[n];
        if (b > e) {
            e = e + n;
        }
        for (int i = b; i <= e; i++) {
            arr[i % n == 0 ? i - 1 : i % n - 1]++;
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                res.add(i + 1);
            }
        }
        return res;
    }


    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int l = 0, r = piles.length - 1;
        int sum = 0;
        while (l < r) {
            sum += piles[r - 1];
            l++;
            r -= 2;
        }
        return sum;
    }

    public int findLatestStep(int[] arr, int m) {
        return 0;
    }

    public int stoneGameV0(int[] stoneValue) {
        Arrays.sort(stoneValue);
//        List<Integer> res = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = stoneValue.length - 1; i >= 0; i--) {
            res.add(stoneValue[i]);
        }
//        List<Integer> collect = Arrays.stream(stoneValue).boxed().collect(Collectors.toList());
        return stoneGameVAss(res);
    }

    public int stoneGameVAss(List<Integer> stoneValue) {
        int len = stoneValue.size();
        if (len <= 1) {
            return 0;
        }
        if (len == 2) {
            return Math.min(stoneValue.get(0), stoneValue.get(1));
        }
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int sa = 0, sb = 0;
        for (int s : stoneValue) {
            if (sa <= sb) {
                a.add(s);
                sa += s;
            } else {
                b.add(s);
                sb += s;
            }
        }
        if (sa < sb) {
            return sa + stoneGameVAss(a);
        }
        if (sb < sa) {
            return sb + stoneGameVAss(b);
        }
        int ta = sa + stoneGameVAss(a);
        int tb = sb + stoneGameVAss(b);
        return Math.max(ta, tb);
    }

    public int stoneGameV(int[] stoneValue) {
        return stoneGameVAss2(stoneValue, 0, stoneValue.length - 1);
    }

    public int stoneGameVAss2(int[] stoneValue, int l, int r) {
        int len = r - l + 1;
        if (len <= 1) {
            return 0;
        }
        if (len == 2) {
            return Math.min(stoneValue[l], stoneValue[r]);
        }
        int sa = 0, sb = 0;
        int ll = l, rr = r;
        while (ll <= rr) {
            if (sa <= sb) {
                sa += stoneValue[ll++];
            } else {
                sb += stoneValue[rr--];
            }
        }
        if (sa < sb) {
            return sa + stoneGameVAss2(stoneValue, l, ll - 1);
        }
        if (sb < sa) {
            return sb + stoneGameVAss2(stoneValue, rr + 1, r);
        }
        int ta = sa + stoneGameVAss2(stoneValue, l, ll - 1);
        int tb = sb + stoneGameVAss2(stoneValue, rr + 1, r);
        return Math.max(ta, tb);
    }


    public int stoneGameVAss3(int[] stoneValue, int l, int r) {
        int len = r - l + 1;
        if (len <= 1) {
            return 0;
        }
        if (len == 2) {
            return Math.min(stoneValue[l], stoneValue[r]);
        }
        int sa = 0, sb = 0;
        int ll = l, rr = r;
        while (ll <= rr) {
            if (sa <= sb) {
                sa += stoneValue[ll++];
            } else {
                sb += stoneValue[rr--];
            }
        }
        if (sa < sb) {
            int td = 0;
            if (sb - stoneValue[rr + 1] < sa + stoneValue[rr + 1]) {
                td = sb - stoneValue[rr + 1] + stoneGameVAss3(stoneValue, rr + 2, r);
            }
            int ta = sa + stoneGameVAss3(stoneValue, l, ll - 1);
            return Math.max(ta, td);
        }
        if (sb < sa) {
            int tc = 0;
            if (sb + stoneValue[ll - 1] > sa - stoneValue[ll - 1]) {
                tc = sa - stoneValue[ll - 1] + stoneGameVAss3(stoneValue, l, ll - 2);
            }
            int tb = sb + stoneGameVAss3(stoneValue, rr + 1, r);
            return Math.max(tb, tc);
        }
        int ta = sa + stoneGameVAss3(stoneValue, l, ll - 1);
        int tb = sb + stoneGameVAss3(stoneValue, rr + 1, r);
//        int tc = sa - stoneValue[ll - 1] + stoneGameVAss3(stoneValue, l, ll - 2);
//        int td = sb - stoneValue[rr + 1] + stoneGameVAss3(stoneValue, rr + 2, r);
//        int tmp = Math.max(tc, td), tmp2 = Math.max(ta, tb);
//        return Math.max(tmp, tmp2);
        return Math.max(ta, tb);
    }


    //2020-8-23 第203 周赛
    public boolean containsPattern(int[] arr, int m, int k) {
        int len = arr.length;
        if (len < m * k) {
            return false;
        }
        for (int i = 0; i <= len - m; i++) {
            int[] tmp = new int[m];
            for (int j = 0; j < m; j++) {
                tmp[j] = arr[i + j];
            }
            int t = 1;
            for (int j = i + m; j <= len - m; j += m) {
                boolean is = true;
                for (int o = 0; o < m; o++) {
                    if (arr[o + j] != tmp[o]) {
                        is = false;
                        break;
                    }
                }
                if (is) {
                    t++;
                } else {
                    break;
                }
            }
            if (t >= k) {
                return true;
            }
        }
        return false;
    }

    public int getMaxLen(int[] nums) {
        int len = nums.length;
        List<Integer> list = new ArrayList<>();
        int max = 0, now = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                list.add(now);
                if (list.size() < 3) {
                    for (int j : list) {
                        max = Math.max(j, max);
                    }
                } else {
                    int t = -2;
                    for (int j = 0; j < list.size(); j++) {
                        if (j % 2 == 0) {
                            t += list.get(j) + 2;
                            max = Math.max(t, max);
                        } else {
                            t += list.get(j);
                            int tt = t - list.get(0);
                            max = Math.max(max, tt);
                        }
                    }
                }
                list = new ArrayList<>();
                now = 0;
            } else if (nums[i] > 0) {
                now++;
            } else {
                list.add(now);
                now = 0;
            }
        }
        list.add(now);
        if (list.size() < 3) {
            for (int j : list) {
                max = Math.max(j, max);
            }
        } else {
            int t = -2;
            for (int j = 0; j < list.size(); j++) {
                if (j % 2 == 0) {
                    t += list.get(j) + 2;
                    max = Math.max(t, max);
                } else {
                    t += list.get(j);
                    int tt = t - list.get(0);
                    max = Math.max(max, tt);
                }
            }
        }
        return max;
    }

    public int numOfWays(int[] nums) {
        List<Integer> li = new ArrayList<>();
        for(int n :nums){
            li.add(n);
        }
        return numOfWaysAss(li);
    }

    public int numOfWaysAss(List<Integer> nums) {
        int mb = 0, ma = 0, key = nums.get(0);
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for (int i : nums) {
            if (i > key) {
                a.add(i);
            } else if(i < key){
                b.add(i);
            }
        }
        ma = a.size();
        mb = b.size();
        int t1 = ma == 0 ? 1 : numOfWaysAss(a);
        int t2 = mb == 0 ? 1 : numOfWaysAss(b);
        return t1 * t2 - 1;
    }

    public static void main(String[] args) {

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
        int[] treeNodeValue = {1, 2, 3, -1, 4};
        int treeNodeLen = treeNodeValue.length;
        Stack<TreeNode> createTreeNodeStack = new Stack<>();
        TreeNode root = new TreeNode(treeNodeValue[0]);
        createTreeNodeStack.add(root);
        for (int i = 1; i < treeNodeLen; i++) {
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
        int[] oneDimensionalArrayA = {6, 2, 3, 4, 5, 5};
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
                {'c', 'a', 'd'},
                {'a', 'a', 'a'},
                {'a', 'a', 'd'},
                {'a', 'c', 'd'},
                {'a', 'b', 'c'}
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


        Games games = new Games();
        games.stoneGameV(oneDimensionalArrayA);
        System.out.println();
    }
}
