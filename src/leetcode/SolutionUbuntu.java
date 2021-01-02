package leetcode;//import jdk.nashorn.api.tree.Tree;

import Template.BinaryIndexedTree;
import leetcode.dataStruct.Nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.*;

import leetcode.dataStruct.TreeNode;
import leetcode.dataStruct.ListNode;

/**
 * @author ：Pheonix
 * @version ：1.0
 * @date ：Created in 上午10:30 18年11月14日
 * @description ：LeetCode
 * @modified By ：
 */
public class SolutionUbuntu {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<Integer> set = new HashSet<>();
        int n = cells.length;
        int i = 0;
        int tmp = 0;
        List<Integer> list = new ArrayList<>();
        for (; i < N; i++) {
            tmp = 0;
            int pre = cells[0];
            cells[0] = 0;
            for (int j = 1; j < n - 1; j++) {
                int t = cells[j];
                cells[j] = pre == cells[j + 1] ? 1 : 0;
                pre = t;
                tmp = tmp * 2 + cells[j];
            }
            cells[n - 1] = 0;
            tmp = tmp * 2;
            if (!set.add(tmp)) {
                break;
            }
            list.add(list.size(), tmp);
        }

        if (i != N) {
            int tt = N % list.size();
            if (tt != 0) {
                tmp = list.get(tt);
            } else {
                tmp = list.get(list.size() - 1);
            }
        }
        int[] ans = new int[n];
        for (int j = n - 1; j >= 0; j--) {
            ans[j] = tmp % 2;
            tmp = tmp / 2;
        }
        return ans;
    }


    /**
     * 605. 种花问题
     * <p>
     * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。
     * 可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
     * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了67.15% 的用户
     *
     * @param flowerbed 花坛
     * @param n         数
     * @return 能否在不打破种植规则的情况下种d                                              b入 n 朵花？
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int addNum = 0;
        int len = flowerbed.length;
        if (len < n) {
            return false;
        }
        if (len == 0) {
            return true;
        }
        if (len < 2 && flowerbed[0] == 1) {
            return n == 0;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0
                    && (i == 0 || flowerbed[i - 1] == 0)
                    && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                addNum++;
            }
            if (addNum >= n) {
                return true;
            }
        }
        return false;
    }

    /**
     * 239. 滑动窗口最大值
     * <p>
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     * <p>
     * 执行用时：326 ms, 在所有 Java 提交中击败了9.64% 的用户
     * 内存消耗：53.2 MB, 在所有 Java 提交中击败了32.33% 的用户
     *
     * @param nums 整数数组
     * @param k 整数
     * @return 滑动窗口中的最大值。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Map<Integer, Integer> removed = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int i = 0;
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (; i < k; i++) {
            queue.add(nums[i]);
        }
        ans[0] = queue.peek();
        for (; i < n; i++) {
            int t = removed.getOrDefault(nums[i - k], 0) + 1;
            removed.put(nums[i - k], t);
            int tmp = 0;
            queue.add(nums[i]);
            boolean f = false;
            while (!removed.isEmpty()) {
                tmp = queue.poll();
                if (removed.containsKey(tmp)) {
                    int tt = removed.get(tmp);
                    f = true;
                    if (tt > 1) {
                        removed.put(tmp, tt - 1);
                    } else {
                        removed.remove(tmp);
                    }
                } else {
                    f = false;
                    break;
                }
            }
            if (!f) {
                queue.add(tmp);
            }
            ans[i - k + 1] = queue.peek();
        }
        return ans;
    }
}
