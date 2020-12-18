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
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了49.09% 的用户
     *
     * @param hour    数
     * @param minutes 数
     * @return 时钟指针的夹角
     */
    public double angleClock(int hour, int minutes) {
        double m = minutes * 6;
        double h = (double) hour % 12 * 30 + (double) minutes / 2;
        double r = Math.abs(m - h);
        return Math.min(r, 360 - r);
    }

    /**
     * 649. Dota2 参议院
     * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
     * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
     * 禁止一名参议员的权利：
     * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
     * 宣布胜利：
     * 如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
     * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
     * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
     * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了73.05% 的用户
     *
     * @param senate 参议院
     * @return 胜利的一方
     */
    public String predictPartyVictory(String senate) {
        return predictPartyVictory(senate, 0, 0);
    }

    public String predictPartyVictory(String senate, int radiant, int dire) {
        int n = senate.length();
        StringBuilder stringBuilder = new StringBuilder();
        boolean radiantWin = true;
        boolean direWin = true;
        for (int i = 0; i < n; i++) {
            char c = senate.charAt(i);
            if (c == 'R') {
                if (dire > 0) {
                    dire--;
                } else {
                    radiant++;
                    stringBuilder.append(c);
                    direWin = false;
                }
            } else {
                if (radiant > 0) {
                    radiant--;
                } else {
                    dire++;
                    stringBuilder.append(c);
                    radiantWin = false;
                }
            }
        }
        if (radiantWin) {
            return "Radiant";
        } else if (direWin) {
            return "Dire";
        } else {
            return predictPartyVictory(stringBuilder.toString(), radiant, dire);
        }
    }

    /**
     * 151. 翻转字符串里的单词
     * <p>
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * 说明：
     * 无空格字符构成一个 单词 。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了68.72% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了80.02% 的用户
     *
     * @param s 字符串
     * @return 翻转字符串里的单词
     */
    public String reverseWords(String s) {
        String[] ss = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = ss.length - 1; i >= 0; i--) {
            if (ss[i].length() > 0) {
                stringBuilder.append(ss[i]).append(" ");
            }
        }
        return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
    }

    /**
     * 417. 太平洋大西洋水流问题
     * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
     * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
     * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了93.02% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了88.26% 的用户
     *
     * @param matrix 负整数矩阵
     * @return 既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return new ArrayList<>();
        }
        int col = matrix[0].length;
        if (col == 0) {
            return new ArrayList<>();
        }

        int[][] mix = new int[row][col];
        // left && up
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            pacificAtlantic(matrix, visited, i, 0, mix, true);
        }
        for (int i = 1; i < col; i++) {
            pacificAtlantic(matrix, visited, 0, i, mix, true);
        }
        // right && down
        visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            pacificAtlantic(matrix, visited, i, col - 1, mix, true);
        }
        for (int i = 0; i < col - 1; i++) {
            pacificAtlantic(matrix, visited, row - 1, i, mix, true);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mix[i][j] >= 2) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j);
                    ans.add(l);
                }
            }
        }
        return ans;
    }

    private int[][] pacificAtlanticDirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void pacificAtlantic(int[][] maxtrix, boolean[][] visited, int x, int y, int[][] mix, boolean left) {
        if (!visited[x][y]) {
            mix[x][y]++;
        }
        visited[x][y] = true;
        for (int[] dir : pacificAtlanticDirs) {
            int xx = x + dir[0];
            int yy = y + dir[1];
            if (xx < 0 || xx >= visited.length || yy < 0 || yy >= visited[0].length || visited[xx][yy]) {
                continue;
            }
            if (left && maxtrix[xx][yy] >= maxtrix[x][y]) {
                pacificAtlantic(maxtrix, visited, xx, yy, mix, left);
            }
            if (!left && maxtrix[xx][yy] <= maxtrix[x][y]) {
                pacificAtlantic(maxtrix, visited, xx, yy, mix, left);
            }
        }
    }

    /**
     * 575. 分糖果
     * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。
     * 你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
     * <p>
     * 执行用时：41 ms, 在所有 Java 提交中击败了50.56% 的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了69.98% 的用户
     *
     * @param candyType 偶数长度的数组
     * @return 妹妹可以获得的最大糖果的种类数
     */
    public int distributeCandies(int[] candyType) {
        int n = candyType.length;
        Set<Integer> set = new HashSet<>();
        Arrays.stream(candyType).forEach(set::add);
        return Math.min(n / 2, set.size());
    }

    /**
     * 面试题 17.19. 消失的两个数字
     * <p>
     * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
     * 以任意顺序返回这两个数字均可。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了28.20% 的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了72.19% 的用户
     *
     * @param nums 包含从 1 到 N 所有的整数
     * @return 返回这两个数字均可
     */
    public int[] missingTwo(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[2];
        int n = nums.length;
        int find = 0;
        int t = 1;
        for (int i = 0; i < n; i++) {
            if (t != nums[i]) {
                ans[find++] = t;
                if (find > 1) {
                    break;
                }
                i--;
            }
            t++;
        }
        while (find < 2) {
            ans[find++] = t++;
        }
        return ans;
    }

    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     * <p>
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了88.18% 的用户
     *
     * @param nums 整型数组
     * @return 找出这两个只出现一次的数字
     */
    public int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

    /**
     * 738. 单调递增的数字
     * <p>
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.96% 的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了65.30% 的用户
     *
     * @param N 非负整数 N
     * @return 小于或等于 N 的最大的整数
     */
    public int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int n = strN.length;
        int ans = 0;
        for (int i = n - 1; i > 0; i--) {
            if (strN[i] >= strN[i - 1]) {
                ans = ans + (strN[i] - '0') * (int) Math.pow(10, n - 1 - i);
            } else {
                strN[i - 1]--;
                ans = (int) Math.pow(10, n - i) - 1;
                // 1 2 2 0
            }
        }
        ans = ans + (strN[0] - '0') * (int) Math.pow(10, n - 1);
        return ans;
    }

    /**
     * 290. 单词规律
     * <p>
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     * 这里的 遵循 指完全匹配，
     * 例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.94% 的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了63.26% 的用户
     *
     * @param pattern 规律
     * @param s       字符串
     * @return 判断 str 是否遵循相同的规律
     */
    public boolean wordPattern(String pattern, String s) {
        String[] word = s.split(" ");
        char[] pat = pattern.toCharArray();
        int len = word.length;
        if (pat.length != len) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(pat[i])) {
                if (map.containsValue(word[i])) {
                    return false;
                }
                map.put(pat[i], word[i]);
            } else if (!map.get(pat[i]).equals(word[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 面试题 17.06. 2出现的次数
     * <p>
     * 编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了75.76% 的用户
     *
     * @param n 数字
     * @return 从 0 到 n (含 n) 中数字 2 出现的次数
     */
    public int numberOf2sInRange(int n) {
        int[] arr = new int[]{0, 1, 20, 300, 4000, 50000, 600000, 7000000, 80000000, 900000000};
        int[] brr = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
        int ans = 0;
        char[] chars = String.valueOf(n).toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            int tmp = chars[i] - '0';
            ans += arr[len - i - 1] * tmp;
            if (tmp > 2) {
                ans += brr[len - i - 1];
            } else if (tmp == 2) {
                ans += n % brr[len - i - 1] + 1;
            }
        }
        return ans;
    }

    /**
     * 1406. 石子游戏 III
     * <p>
     * Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
     * Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。
     * 比赛一直持续到所有石头都被拿走。每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。
     * 每个玩家的初始分数都是 0 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
     * 假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。
     * <p>
     * <p>
     * 执行用时：9 ms, 在所有 Java 提交中击败了91.46% 的用户
     * 内存消耗：48 MB, 在所有 Java 提交中击败了61.93% 的用户
     *
     * @param stoneValue 几堆石子排成一行
     * @return 获胜者
     */
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = stoneValue[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suffixSum[i] = suffixSum[i + 1] + stoneValue[i];
        }
        int[] f = new int[n + 1];
        // 边界情况，当没有石子时，分数为 0
        // 为了代码的可读性，显式声明
        f[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int bestj = f[i + 1];
            for (int j = i + 2; j <= i + 3 && j <= n; j++) {
                bestj = Math.min(bestj, f[j]);
            }
            f[i] = suffixSum[i] - bestj;
        }
        int total = suffixSum[0];
        if (f[0] * 2 == total) {
            return "Tie";
        } else {
            return f[0] * 2 > total ? "Alice" : "Bob";
        }
    }

    /**
     * 剑指 Offer 14- I. 剪绳子
     * <p>
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了43.22% 的用户
     *
     * @param n 长度
     * @return 最大乘积
     */
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int ans = 1;
        while (n > 4) {
            ans *= 3;
            n -= 3;
        }
        ans *= n;
        return ans;
    }

    /**
     * 剑指 Offer 14- II. 剪绳子 II
     * <p>
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了79.73% 的用户
     *
     * @param n 长度
     * @return 最大乘积
     */
    public int cuttingRope2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        long ans = 1;
        while (n > 4) {
            ans = ans * 3 % 1000000007;
            n -= 3;
        }
        ans = ans * n % 1000000007;
        return (int) ans;
    }

    /**
     * 剑指 Offer 47. 礼物的最大价值
     * <p>
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了14.99% 的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了34.76% 的用户
     *
     * @param grid 礼物
     * @return 最多能拿到多少价值的礼物
     */
    public int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j - 1 >= 0) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                }
                if (i - 1 >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                }
                dp[i][j] += grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * <p>
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * <p>
     * 执行用时：23 ms, 在所有 Java 提交中击败了27.31% 的用户
     * 内存消耗：47.9 MB, 在所有 Java 提交中击败了44.85% 的用户
     *
     * @param prices 整数数组
     * @param fee    手续费
     * @return 获得利润的最大值
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 0 :不持有股票， 1:持有股票
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - fee + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1] - fee);
    }

    // 2 5 1 1 1 1
    // 0 2 1 2 3 3


    public int minJump(int[] jump) {
        int[] f = new int[10000000 + 7];
        //数组 maxdis[w] 表示 w 步可以跳到的最远位置
        int[] maxdis = new int[10000000 + 7];
        int n = jump.length;
        int w = 0;
        int ans = 1000000000;

        for (int i = 1; i <= n; ++i) {
            f[i] = 1000000000;
            maxdis[i] = 0;
        }
        f[1] = 0;
        maxdis[0] = 1;

        for (int i = 1; i <= n; ++i) {
            if (i > maxdis[w]) { // 更新单调指针
                ++w;
            }
            f[i] = Math.min(f[i], w + 1); // 用 maxdis[w] 更新 f[i]
            int next = i + jump[i - 1]; // 第一步跳跃更新

            if (next > n) {
                ans = Math.min(ans, f[i] + 1);
            } else if (f[next] > f[i] + 1) {
                f[next] = f[i] + 1;
                maxdis[f[next]] = Math.max(maxdis[f[next]], next);
            }
        }
        return ans;
    }

    /**
     * 389. 找不同
     * <p>
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了82.27% 的用户
     *
     * @param s 字符串
     * @param t 字符串
     * @return 在 t 中被添加的字母
     */
    public char findTheDifference(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int n = sc.length;
        int res = tc[n];
        for (int i = 0; i < n; i++) {
            res = res ^ sc[i] ^ tc[i];
        }
        return (char) res;
    }
}
