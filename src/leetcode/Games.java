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

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        };
        Arrays.sort(boxTypes, comparator);
        int ans = 0;
        int t = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            if (t + boxTypes[i][0] < truckSize) {
                ans += boxTypes[i][0] * boxTypes[i][1];
                t += boxTypes[i][0];
            } else {
                ans += (truckSize - t) * boxTypes[i][1];
                break;
            }
        }
        return ans;
    }

    public int countPairs(int[] deliciousness) {
        int n = deliciousness.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int del : deliciousness) {
            int t = map.getOrDefault(del, 0) + 1;
            map.put(del, t);
        }
        int[] arr = new int[22];
        arr[0] = 1;
        for (int i = 1; i < 22; i++) {
            arr[i] = 2 * arr[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 21; j >= 0; j--) {
                int key = arr[j] - deliciousness[i];
                if (key < 0) {
                    break;
                }
                int value = map.getOrDefault(key, 0);
                if (key == deliciousness[i]) {
                    value--;
                }
                ans += value;
                ans = ans % 1000000007;
            }
            int t = map.get(deliciousness[i]) - 1;
            if (t == 0) {
                map.remove(deliciousness[i]);
            } else {
                map.put(deliciousness[i], t);
            }
        }
        return ans;
    }

    public int waysToSplit(int[] nums) {
        long sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if (sum == 0) {
            return (int) ((long) (n - 1) * (long) (n - 2))  % 1000000007/ 2;
        }
        int ans = 0;
        int valC = (int) (sum / 3);
        int r = nums.length - 1;
        int vac = 0;
        for (; r >= 2; r--) {
            vac += nums[r];
            if (vac >= valC) {
                break;
            }
        }
        if (vac < valC) {
            return 0;
        }
        for (; r > 1; r--) {
            int vab = (int) (sum - vac);
            int va = 0;
            for (int i = 0; i < r - 1; i++) {
                va += nums[i];
                if (vab - va <= vac && va <= vab - va) {
                    ans++;
                    ans = ans % 1000000007;
                }
                if (vab - va < va) {
                    break;
                }
            }
            vac += nums[r - 1];
        }
        return ans;
    }

    public int minOperations(int[] target, int[] arr) {
        return target.length - minOperationsAss(target, arr);
    }

    public int minOperationsAss(int[] target, int[] arr) {
        if (target == null || target.length <= 0 || arr == null || arr.length <= 0) {
            return 0;
        }
        int length1 = target.length;
        int length2 = arr.length;
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (target[i - 1] == arr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return target.length - dp[length1][length2];
    }


    int[] pre = new int[100005];
    int[] last = new int[100005];

    public int minOperationsAss2(int[] target, int[] arr) {
        if (target == null || target.length <= 0 || arr == null || arr.length <= 0) {
            return 0;
        }

        int length1 = target.length;
        int length2 = arr.length;
        int[] pre = new int[length2 + 1];
        int[] last = new int[length2 + 1];

        boolean preIspre = true;

        for (int i = 1; i <= length1; i++) {
            if (preIspre) {
                for (int j = 1; j <= length2; j++) {
                    if (target[i - 1] == arr[j - 1]) {
                        last[j] = pre[j - 1] + 1;
                    } else {
                        last[j] = Math.max(pre[j], last[j - 1]);
                    }
                    pre = last.clone();
                }
                preIspre = false;
            } else {
                for (int j = 1; j <= length2; j++) {
                    if (target[i - 1] == arr[j - 1]) {
                        pre[j] = last[j - 1] + 1;
                    } else {
                        pre[j] = Math.max(last[j], pre[j - 1]);
                    }
                    last = pre.clone();
                }
                preIspre = true;
            }
        }

        int t = preIspre ? last[length2] : pre[length2];
        return target.length - t;
    }

}
