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

    /**
     * 1619. 删除某些元素后的数组均值
     * <p>
     * 给你一个整数数组 arr ，请你删除最小 5% 的数字和最大 5% 的数字后，剩余数字的平均值。
     * 与 标准答案 误差在 10-5 的结果都被视为正确结果。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.81% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了76.22% 的用户
     *
     * @param arr 整数数组
     * @return 删除最小 5% 的数字和最大 5% 的数字后，剩余数字的平均值
     */
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int l = (int) (n * 0.05);
        int r = (int) (n * 0.95);
        int sum = 0;
        for (int i = l; i < r; i++) {
            sum += arr[i];
        }
        return (double) sum / (r - l);
    }

    /**
     * 1209. 删除字符串中的所有相邻重复项 II
     * <p>
     * 给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
     * 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
     * 在执行完所有删除操作后，返回最终得到的字符串。
     * 本题答案保证唯一。
     * <p>
     * 执行用时：17 ms, 在所有 Java 提交中击败了49.20% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了63.48% 的用户
     *
     * @param s 小写字母组成的字符串
     * @param k 整数
     * @return 在完成所有重复项删除操作后返回最终的字符串
     */
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.push(incremented);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 860. 柠檬水找零
     * <p>
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
     * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * 注意，一开始你手头没有任何零钱。
     * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.72% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了22.85% 的用户
     *
     * @param bills 账单
     * @return 能否成功给每位顾客正确找零
     */
    public boolean lemonadeChange(int[] bills) {
        int money5 = 0, money10 = 0;
        for (int bill : bills) {
            switch (bill) {
                case 20 -> {
                    if (money10 > 0) {
                        money10--;
                        money5--;
                    } else {
                        money5 -= 3;
                    }
                    if (money5 < 0) {
                        return false;
                    }
                }
                case 10 -> {
                    if (money5 > 0) {
                        money5--;
                        money10++;
                    } else {
                        return false;
                    }
                }
                case 5 -> {
                    money5++;
                }
            }
        }
        return true;
    }

    /**
     * 1344. 时钟指针的夹角
     * 给你两个数 hour 和 minutes 。请你返回在时钟上，由给定时间的时针和分针组成的较小角的角度（60 单位制）。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了49.09% 的用户
     * @param hour 数
     * @param minutes 数
     * @return 时钟指针的夹角
     */
    public double angleClock(int hour, int minutes) {
        double m = minutes * 6;
        double h = (double) hour % 12 * 30 + (double) minutes / 2;
        double r = Math.abs(m - h);
        return Math.min(r, 360 - r);
    }
}
