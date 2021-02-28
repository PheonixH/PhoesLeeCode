package leetcode;

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

    // 2021-02-28 第230周赛
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int ans = 0;
        if (ruleKey.equals("type")) {
            for (List<String> item : items) {
                if (item.get(0).equals(ruleValue)) {
                    ans++;
                }
            }
        } else if (ruleKey.equals("color")) {
            for (List<String> item : items) {
                if (item.get(1).equals(ruleValue)) {
                    ans++;
                }
            }
        } else if (ruleKey.equals("name")) {
            for (List<String> item : items) {
                if (item.get(2).equals(ruleValue)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int n = baseCosts.length;
        int m = toppingCosts.length;
        closestCostDfs(toppingCosts, 0, 0, target);
        int size = closestCost.size();
        int[] realToppingCost = new int[size];
        int j = 0;
        for (int i : closestCost) {
            realToppingCost[j++] = i;
        }
        Arrays.sort(realToppingCost);
        Arrays.sort(baseCosts);
        int ans = baseCosts[0];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < size; k++) {
                int tmp = baseCosts[i] + realToppingCost[k];
                if (tmp > ans && Math.abs(tmp - target) > Math.abs(ans - target)) {
                    break;
                } else if (Math.abs(tmp - target) < Math.abs(ans - target)) {
                    ans = tmp;
                } else if (Math.abs(tmp - target) == Math.abs(ans - target)) {
                    ans = Math.min(ans, tmp);
                }
            }
        }
        return ans;
    }

    private Set<Integer> closestCost = new HashSet<>();

    private void closestCostDfs(int[] toppingCost, int l, int val, int target) {
        if (l >= toppingCost.length) {
            closestCost.add(val);
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (val + toppingCost[l] * i > target) {
                closestCost.add(val + toppingCost[l] * i);
            } else {
                closestCostDfs(toppingCost, l + 1, val + toppingCost[l] * i, target);
            }
        }
    }

    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (n * 6 < m || n > m * 6) {
            return -1;
        }
        int sumA = 0;
        int[] arr = new int[6];
        for (int item : nums1) {
            arr[item - 1]++;
            sumA += item;
        }

        int sumB = 0;
        int[] brr = new int[6];
        for (int value : nums2) {
            brr[value - 1]++;
            sumB += value;
        }

        if (sumA == sumB) {
            return 0;
        }

        int target = Math.abs(sumA - sumB);
        int[] crr = new int[6];
        if (sumB > sumA) {
            for (int i = 0; i < 6; i++) {
                crr[i] = arr[5 - i] + brr[i];
            }
        } else {
            for (int i = 0; i < 6; i++) {
                crr[i] = brr[5 - i] + arr[i];
            }
        }

        int ans = 0;
        int t = 0;
        for (int i = 5; i >= 0; i--) {
            if (t + i * crr[i] < target) {
                t = t + i * crr[i];
                ans += crr[i];
            } else if (t + i * crr[i] == target) {
                ans += crr[i];
                break;
            } else {
                int tmp = target - t;
                if (tmp % i > 0) {
                    ans += tmp / i + 1;
                } else {
                    ans += tmp / i;
                }
                break;
            }
        }
        return ans;
    }

    /**
     * 5692. 车队 II
     * 在一条单车道上有 n 辆车，它们朝着同样的方向行驶。给你一个长度为 n 的数组 cars ，
     * 其中 cars[i] = [positioni, speedi] ，
     * 它表示： positioni 是第 i 辆车和道路起点之间的距离（单位：米）。
     * 题目保证 positioni < positioni+1 。
     * speedi 是第 i 辆车的初始速度（单位：米/秒）。
     * 简单起见，所有车子可以视为在数轴上移动的点。当两辆车占据同一个位置时，我们称它们相遇了。
     * 一旦两辆车相遇，它们会合并成一个车队，这个车队里的车有着同样的位置和相同的速度，速度为这个车队里 最慢 一辆车的速度。
     * 请你返回一个数组 answer ，
     * 其中 answer[i] 是第 i 辆车与下一辆车相遇的时间（单位：秒），如果这辆车不会与下一辆车相遇，则 answer[i] 为 -1 。
     * 答案精度误差需在 10-5 以内。
     * <p>
     * 执行结果： 通过 显示详情 执行用时： 149 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 96.1 MB , 在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param cars 车队
     * @return 数组
     */
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        ans[n - 1] = -1.0000;
        for (int i = n - 2; i >= 0; i--) {
            if (ans[i + 1] == -1.0000 && cars[i][1] <= cars[i + 1][1]) {
                ans[i] = -1.0000;
            } else {
                double tmp = -1.0000;
                if (cars[i][1] > cars[i + 1][1]) {
                    tmp = (double) (cars[i + 1][0] - cars[i][0]) / (double) (cars[i][1] - cars[i + 1][1]);
                }
                int p = 1;
                while (ans[i + p - 1] != -1.0000) {
                    if (tmp > ans[i + p - 1] || tmp <= 0) {
                        if (cars[i][1] > cars[i + p][1]) {
                            tmp = (double) (cars[i + p][0] - cars[i][0]) / (double) (cars[i][1] - cars[i + p][1]);
                        }
                    } else {
                        break;
                    }
                    p++;
                }
                ans[i] = tmp;
            }
        }
        return ans;
    }
}
