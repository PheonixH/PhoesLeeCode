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

    public static void main(String[] args){
        Games games = new Games();
        int[] arr = {9,8,1,0,1,9,4,0,4,1};
        int[] brr ={6,0,8,2,1,5};
        int[] crr = {9,8,7,6,5,4,3,2,1};
        games.leastOpsExpressTarget(3,19);
    }

    //2018.Dec.16 - 第115周赛
    //957
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] tcell = new int[cells.length];
        int r = 0;

        N--;
        tcell[0] = 0;
        tcell[cells.length - 1] = 0;
        for (int i = 1;i < cells.length - 1;i++){
            if(cells[i - 1] == cells[i + 1]) {
                tcell[i] = 1;
            }
            else {
                tcell[i] = 0;
            }
        }
        r = 1;

        N = N % 15;
        N = 6;
        while (N != 0){
            if ((0 == r)) {
                tcell[0] = 0;
                tcell[cells.length - 1] = 0;
                for (int i = 1;i < cells.length - 1;i++){
                    if(cells[i - 1] == cells[i + 1]) {
                        tcell[i] = 1;
                    }
                    else {
                        tcell[i] = 0;
                    }
                }
                r = 1;
            }
            else {
                cells[0] = 0;
                cells[cells.length - 1] = 0;
                for (int i = 1;i < cells.length - 1;i++){
                    if(tcell[i - 1] == tcell[i + 1]) {
                        cells[i] = 1;
                    }
                    else {
                        cells[i] = 0;
                    }
                }
                r = 0;
            }
            N--;
        }
        if(0 == r){
            tcell[0] = 0;
            tcell[cells.length - 1] = 0;
            return tcell;
        }
        else {
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


    //2018.Dec.16 - 第115周赛
    //961
    public int repeatedNTimes(int[] A) {
        if(0 == A.length){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for(int i : A){
            if (set.contains(i)) {
                return i;
            }
            else {
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
        for(int i = 0;i < A.length; i++){
            for(int j = A.length -1;j > i;j--){
                if(j - i < maxl){
                    break;
                }
                if(A[j] >= A[i]){
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
        if(x == target){
            return res;
        }
        int ns = x;
        res = leastOpsExpressTargetAss(x,target,res,ns,1);
        return res;
    }
    public static int leastOpsExpressTargetAss(int x, int target, int res,int ns, int k){
        if(ns == target){
            return res;
        }
        if(ns <= target){
            if(ns * x <= target){
                while (ns < target){
                    ns = ns * x;
                    k++;
                }
                if(k != 1) {
                    ns = ns/ x;
                    res = res + k - 1;
                }
            }
            while (k > 1){
                int kk = 1;
                int ss = x;
                while (ss + ns < target){
                    ss = ss * x;
                    kk++;
                }
                if(kk != 1) {
                    res = res + kk -1;
                    ns = ns + ss / x;
                    k = kk - 1;
                }
                else {
                    break;
                }
            }
            while (ns + x <= target){
                res++;
                ns = ns + x;
            }
            while (ns + 1 <= target){
                res = res + 2;
                ns = ns + 1;
            }
        }
        else{
            if(ns - 1 >= target){
                res = res + 2;
                ns--;
            }
        }
        return res;
    }
}
