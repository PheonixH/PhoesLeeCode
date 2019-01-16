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

    public static void main(String[] args) {
        Games games = new Games();
        int[] arr = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        int[] brr = {6, 0, 8, 2, 1, 5};
        int[] crr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        games.numsSameConsecDiff(2, 1);
        TreeNode t = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(6);
        t.left = t1;
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
//        t4.left = t5;
        games.minCameraCover(t);
    }
}
