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
        int n = cells.length;
        Map<String, Integer> map = new HashMap<>();
        int day = 0;
        String status = cells.toString();
        while (!map.containsKey(status)) {
            map.put(status, day);
            day++;
            int pre = cells[0];
            cells[0] = 0;
            for (int i = 1; i < cells.length - 1; i++) {
                int tmp = cells[i];
                cells[i] = pre == cells[i + 1] ? 0 : 1;
                pre = tmp;
            }
            cells[cells.length - 1] = 0;
            status = cells.toString();
            if (day == N) {
                char[] chars = status.toCharArray();
                int[] ans = new int[chars.length];
                for (int j = 0; j < n; j++) {
                    ans[j] = chars[j] - '0';
                }
                return ans;
            }
        }
        int b = map.get(status);
        int e = day;
        day = (N - day) % (e - b + 1) + b;
        for (String sta : map.keySet()) {
            if (map.get(sta) == day) {
                char[] chars = sta.toCharArray();
                int[] ans = new int[chars.length];
                for (int j = 0; j < n; j++) {
                    ans[j] = chars[j] - '0';
                }
                return ans;
            }
        }
        return null;
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
     * @param k    整数
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

    /**
     * 86. 分隔链表
     * <p>
     * 给你一个链表和一个特定值 x ，请你对链表进行分隔，
     * 使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
     * 你应当保留两个分区中每个节点的初始相对位置。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了60.28% 的用户
     *
     * @param head 链表
     * @param x    整数
     * @return 分隔后的链表
     */
    public ListNode partition(ListNode head, int x) {
        ListNode big = new ListNode(0);
        ListNode small = new ListNode(0);
        ListNode tb = big;
        ListNode ts = small;
        ListNode tmp = head;
        while (tmp != null) {
            if (tmp.val >= x) {
                tb.next = tmp;
                tb = tb.next;
            } else {
                ts.next = tmp;
                ts = ts.next;
            }
            tmp = tmp.next;
        }
        ts.next = big.next;
        tb.next = null;
        return small.next;
    }

    /**
     * 1232. 缀点成线
     *
     * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
     * 其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
     * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了80.76% 的用户
     *
     * @param coordinates 点
     * @return 这些点是否在该坐标系中属于同一条直线上
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        if (n <= 2) {
            return true;
        }
        int[] co0 = coordinates[0];
        int[] co1 = coordinates[1];
        if (co0[1] == co1[1]) {
            for (int i = 2; i < n; i++) {
                if (coordinates[i][1] != co0[1]) {
                    return false;
                }
            }
        } else {
            double k = (double) (co0[0] - co1[0]) / (double) (co0[1] - co1[1]);
            for (int i = 2; i < n; i++) {
                if (co0[1] - coordinates[i][1] == 0) {
                    return false;
                }
                if ((double) (co0[0] - coordinates[i][0]) / (double) (co0[1] - coordinates[i][1]) != k) {
                    return false;
                }
            }
        }
        return true;
    }
}