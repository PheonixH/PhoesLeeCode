import data.TreeNode;

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

    /**
     * 964(wrong)
     */
    public int leastOpsExpressTarget(int x, int target) {
        char[] ops = {'+', '-', '*', '/'};
        int res = 0;
        if (x == target) {
            return res;
        }
        res = leastOpsExpressTargetAss(x, target, res, x, 1);
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

    /**
     * 2018.Dec.30 - 第117周赛 - 年终巅峰对决
     * 965
     */
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
            return new int[0];
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
        }
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
                (watched.get(t1) == watched.get(t2) ? t1.compareTo(t2) : watched.get(t1) - watched.get(t2)));

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

    public static void main(String[] args) {
        Games games = new Games();
        List<List<String>> or = new ArrayList<>();
        List<String> or1 = new ArrayList<>();
        or1.add("James");
        or1.add("12");
        or1.add("Fried Chicken");
        or.add(or1);
        List<String> or2 = new ArrayList<>();
        or2.add("Ratesh");
        or2.add("1");
        or2.add("Fried Chicken");
        or.add(or2);
        List<String> or3 = new ArrayList<>();
        or3.add("Amadeus");
        or3.add("12");
        or3.add("Fried Chicken");
        or.add(or3);
        List<String> or4 = new ArrayList<>();
        or4.add("Adam");
        or4.add("1");
        or4.add("Canadian Waffles");
        or.add(or4);
        List<String> or5 = new ArrayList<>();
        or5.add("Brianna");
        or5.add("11");
        or5.add("Canadian Waffles");
        or.add(or5);
        List<List<String>> q = new LinkedList<>();
        List<String> q1 = new LinkedList<>();
        q1.add("A");
        q1.add("B");
        List<String> q2 = new LinkedList<>();
        q1.add("C");
        List<String> q3 = new LinkedList<>();
        q1.add("C");
        q1.add("B");
        List<String> q4 = new LinkedList<>();
        q1.add("D");
        q.add(q1);
        q.add(q2);
        q.add(q3);
        q.add(q4);
        int[][] arrays = new int[][]{{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        int[] brr = new int[]{1, 2, 3, 4};
        int p = games.rangeSum(brr, 4, 2, 3);
        System.out.println(p);
    }
}
