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
        List<List<String>> q = games.displayTable(or);
        String[] s = {"kaido", "kaido(1)", "kaido", "kaido(1)", "kaido(2)"};
        int[] us = {1, 0, 0, 0, 2, 2};
        int[] p = games.avoidFlood(us);
        System.out.println(p);

    }
}

