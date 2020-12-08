package leetcode;

import Template.BinaryIndexedTree;
import leetcode.dataStruct.ListNode;
import leetcode.dataStruct.TreeNode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
     * 456. 132模式
     * <p>
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
     * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     * 注意：n 的值小于15000。
     * <p>
     * 执行用时：185 ms, 在所有 Java 提交中击败了20.31% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了96.26% 的用户
     *
     * @param nums 整数序列
     * @return 验证这个序列中是否含有132模式的子序列。
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] leftMin = new int[n];
        int min = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = min;
            min = Math.min(min, nums[i]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i] && nums[j] > leftMin[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 537. 复数乘法
     * <p>
     * 给定两个表示复数的字符串。
     * 返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了78.74% 的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了36.87% 的用户
     *
     * @param a 复数的字符串
     * @param b 复数的字符串
     * @return 它们乘积的字符串
     */
    public String complexNumberMultiply(String a, String b) {
        String[] arr = a.split("\\+");
        String[] brr = b.split("\\+");
        arr[1] = arr[1].substring(0, arr[1].length() - 1);
        brr[1] = brr[1].substring(0, brr[1].length() - 1);
        arr[1] = arr[1].length() == 0 ? "1" : arr[1];
        brr[1] = brr[1].length() == 0 ? "1" : brr[1];
        int ansOne = Integer.parseInt(arr[0]) * Integer.parseInt(brr[0]) -
                Integer.parseInt(arr[1]) * Integer.parseInt(brr[1]);
        int ansTwo = Integer.parseInt(arr[0]) * Integer.parseInt(brr[1]) +
                Integer.parseInt(arr[1]) * Integer.parseInt(brr[0]);
        return ansOne + "+" + ansTwo + "i";
    }

    /**
     * 1171. 从链表中删去总和值为零的连续节点
     * <p>
     * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
     * 删除完毕后，请你返回最终结果链表的头节点。
     * 你可以返回任何满足题目要求的答案。
     * （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了15.76% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了87.73% 的用户
     *
     * @param head 链表的头节点 head
     * @return 反复删去链表中由 总和 值为 0 的连续节点组成的序列
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        int sum = 0;
        ListNode p = head;
        while (p != null) {
            sum += p.val;
            if (sum == 0) {
                head = p.next;
            } else {
                ListNode q = head;
                int qSum = 0;
                while (q != p) {
                    qSum += q.val;
                    if (qSum == sum) {
                        q.next = p.next;
                        break;
                    }
                    q = q.next;
                }
            }
            p = p.next;
        }
        return sum == 0 ? null : head;
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     * <p>
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     * <p>
     * 执行用时：52 ms, 在所有 Java 提交中击败了19.67% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了78.77% 的用户
     *
     * @param S 小写字母组成的字符串
     * @return 在完成所有重复项删除操作后返回最终的字符串
     */
    public String removeDuplicates(String S) {
        String newS = S;
        for (char c = 'a'; c <= 'z'; c++) {
            String remove = "" + c + c;
            newS = newS.replace(remove, "");
        }
        return newS.equals(S) ? S : removeDuplicates(newS);
    }
}
