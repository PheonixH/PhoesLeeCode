package leetcode;

import leetcode.datestruct.ListNode;
import leetcode.datestruct.TreeNode;

import java.util.*;

/**
 * @ProjectName: PhoesLeeCode
 * @Package: PACKAGE_NAME
 * @ClassName: LeetCode.Solution2
 * @Description:
 * @Author: Pheonix
 * @CreateDate: 2019/4/25 15:05
 * @Version: 1.0
 */
public class SolutionNow {

    /**
     * 面试题 17.21. 直方图的水量
     * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?
     * 直方图的宽度为 1。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了18.24% 的用户
     *
     * @param height 直方图
     * @return 直方图水量
     */
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int maxValue = height[0];
        int maxKeyLeft = 0, maxKeyRight = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] > maxValue) {
                maxKeyLeft = i;
                maxKeyRight = i;
                maxValue = height[i];
            } else if (height[i] == maxValue) {
                maxKeyRight = i;
            }
        }
        int sum = maxValue * height.length;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < maxKeyLeft) {
            leftMax = Math.max(leftMax, height[left]);
            sum = sum - maxValue + leftMax - height[left++];
        }
        while (right > maxKeyRight) {
            rightMax = Math.max(rightMax, height[right]);
            sum = sum - maxValue + rightMax - height[right--];
        }
        while (maxKeyLeft <= maxKeyRight) {
            sum -= height[maxKeyLeft++];
        }
        return sum;
    }

    /**
     * 141. 环形链表
     * 给定一个链表，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了27.15% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了58.84% 的用户
     *
     * @param head 链表
     * @return 链表是否有环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            if (fast.equals(slow)) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。
     * 说明：不允许修改给定的链表。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了65.84% 的用户
     * @param head 链表
     * @return 链表中的环的入口
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        boolean isFind = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                isFind = true;
                break;
            }
        }
        if (!isFind) {
            return null;
        }
        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
