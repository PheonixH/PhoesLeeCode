import datestruct.TreeNode;

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
        if(i == 0){
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
        }
        else if (bl == 1 || br == 1) {
            bre = 2;
        }
        return bre;
    }

    //2019.Feb.3 - 第117周赛
    //985
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] res = new int[queries.length];
        int re = 0;
        for(int i:A){
            if(i % 2 == 0){
                re += i;
            }
        }
        for(int i = 0;i < queries.length;i++){
            if(A[queries[i][1]] % 2 == 0){
                if(queries[i][0] % 2 == 0){
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
                if(queries[i][0] % 2 == 0){
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

    //988
    public String smallestFromLeaf(TreeNode root) {
        if(root == null){
            return "";
        }
        int zero = Integer.valueOf('a');
        if(root.right == null&&root.left == null){
            char r = (char)(root.val + zero);
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
        while (!stack.isEmpty()){
            TreeNode r = stack.pop();
            List<Integer> lis = map.get(r);
            if(r.left != null){
                List<Integer> l = new LinkedList<>();
                l.add(r.left.val);
                lis.forEach((i) ->{
                    l.add(i);
                });
                map.put(r.left, l);
                stack.add(r.left);
            }
            if(r.right != null){
                List<Integer> l = new LinkedList<>();
                l.add(r.right.val);
                lis.forEach((i) ->{
                    l.add(i);
                });
                map.put(r.right, l);
                stack.add(r.right);
            }
            if(r.right == null&&r.left == null){
                if(b){
                    boolean bb = false;
                    int kk = 0;
                    for(int i : lis){
                        if(kk < k&&re[kk] > i){
                            bb = true;
                            break;
                        } else if(kk < k&&re[kk] < i){
                            break;
                        }
                        kk++;
                    }
                    kk = 0;
                    if(bb) {
                        for (int i : lis) {
                            re[kk++] = i;
                        }
                        k = kk;
                    }
                } else {
                    for(int i :lis){
                        re[k++] = i;
                    }
                    b = true;
                }
            }
        }
        StringBuffer s = new StringBuffer();
        for(int i = 0;i < k;i++){
            s.append((char)(zero + re[i]));
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
        for(int i = 0;i < cs.length; i++){
            if(c == cs[i]){
                len++;
                if(len >= 3){
                    flag = true;
                }
            } else if(flag){
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
        if(flag){
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
        if(S.contains("@")){
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
                }  else if (len == 3){
                    res = "-" + cs[i] + res;
                    len++;
                }else if (len == 6||len == 9) {
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
        if(N == 1){
            return 1;
        }
        int n = 1;
        int res = 0;
        while (n  < N / 2){
            if(N % n == 0){
                res++;
            }
            n = n + 2;
        }
        if(N > 2&&N%2 == 1){
            res++;
        }
        return res;
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
                if (!map.containsKey(c1) && !map.containsKey(c2)){
                    map.put(c1, (int)c1);
                    map.put(c2, (int)c2);
                }
            } else {
                continue;
            }
        }
        if(map.isEmpty()){
            return true;
        }
        for(String equation: equations){
            char c1 = equation.charAt(0);
            char c2 = equation.charAt(3);
            char c3 = equation.charAt(1);
            if(c3 == '!'){
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

    public static void main(String[] args) {
        Games games = new Games();
        int[] arr = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        int[] brr = {6, 0, 8, 2, 1, 5};
        int[] crr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        games.numsSameConsecDiff(2, 1);
        TreeNode t = new TreeNode(25);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(0);
        TreeNode t6 = new TreeNode(2);
        t.left = t1;
        t.right = t2;
        t1.left = t3;
        t1.right = t4;
        t2.left = t5;
        t2.right = t6;
        int[] drr = {3,2};
        int[][] err = {{4,0},{3,0}};
        games.consecutiveNumbersSum(85);
    }
}
