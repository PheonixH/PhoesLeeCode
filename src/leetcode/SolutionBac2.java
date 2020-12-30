package leetcode;

import Template.BinaryIndexedTree;
import leetcode.dataStruct.ListNode;
import leetcode.dataStruct.Nodes;
import leetcode.dataStruct.TreeNode;

import java.util.*;

/**
 * @program: PhoesLeeCode
 * @description: 备份
 * @author: Feng.H
 * @create: 2020-12-30 10:01
 **/
public class SolutionBac2 {

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
        int[] brr = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 1, 100000000};
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
    public int maxProfit2(int[] prices, int fee) {
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

    /**
     * 1395. 统计作战单位数
     * <p>
     * n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
     * 每 3 个士兵可以组成一个作战单位，分组规则如下：
     * 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
     * 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
     * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
     * <p>
     * 执行用时：24 ms, 在所有 Java 提交中击败了21.28% 的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了92.28% 的用户
     *
     * @param rating 评分
     * @return 按上述条件可以组建的作战单位数量
     */
    public int numTeams0(int[] rating) {
        int n = rating.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (rating[i] > rating[j] && rating[j] > rating[k]) {
                        ans++;
                    }
                    if (rating[i] < rating[j] && rating[j] < rating[k]) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 1395. 统计作战单位数
     * <p>
     * n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
     * 每 3 个士兵可以组成一个作战单位，分组规则如下：
     * 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
     * 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
     * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了58.75% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了5.04% 的用户
     *
     * @param rating 评分
     * @return 按上述条件可以组建的作战单位数量
     */
    public int numTeams(int[] rating) {
        int n = rating.length;

        // 因为rating最多200个且不重复，所以可以压缩
        int[] newR = rating.clone();
        Arrays.sort(newR);
        Map<Integer, Integer> disc = new HashMap<>();
        for (int i = 0; i < n; i++) {
            disc.put(newR[i], i);
        }


        BinaryIndexedTree bit = new BinaryIndexedTree(201);
        for (int value : rating) {
            bit.update(disc.get(value), 1);
        }
        int ans = 0;
        BinaryIndexedTree bit2 = new BinaryIndexedTree(201);
        for (int value : rating) {
            int a = bit.prefixSum(disc.get(value) - 1);
            int b = bit2.sumRange(disc.get(value) + 1, 200);
            ans += a * b;
            a = bit2.prefixSum(disc.get(value) - 1);
            b = bit.sumRange(disc.get(value) + 1, 200);
            ans += a * b;
            bit.update(disc.get(value), -1);
            bit2.update(disc.get(value), 1);
        }
        return ans;
    }

    /**
     * 1456. 定长子串中元音的最大数目
     * <p>
     * 给你字符串 s 和整数 k 。
     * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
     * 英文中的 元音字母 为（a, e, i, o, u）。
     * <p>
     * 执行用时：11 ms, 在所有 Java 提交中击败了89.05% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了62.76% 的用户
     *
     * @param s 字符串
     * @param k 整数
     * @return 定长子串中元音的最大数目
     */
    public int maxVowels(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;
        int r = 0;
        for (; r < k; r++) {
            if (chars[r] == 'a' || chars[r] == 'e' || chars[r] == 'i'
                    || chars[r] == 'o' || chars[r] == 'u') {
                ans++;
            }
        }
        int tmp = ans;
        for (int l = 0; l < n - k; l++) {
            if (chars[l] == 'a' || chars[l] == 'e' || chars[l] == 'i'
                    || chars[l] == 'o' || chars[l] == 'u') {
                tmp--;
            }
            if (chars[r] == 'a' || chars[r] == 'e' || chars[r] == 'i'
                    || chars[r] == 'o' || chars[r] == 'u') {
                tmp++;
            }
            r++;
            ans = Math.max(ans, tmp);
        }
        return ans;
    }

    /**
     * 1380. 矩阵中的幸运数
     * <p>
     * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
     * 幸运数是指矩阵中满足同时下列两个条件的元素：
     * 在同一行的所有元素中最小
     * 在同一列的所有元素中最大
     * <p>
     * 执行用时：11 ms, 在所有 Java 提交中击败了5.36% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了70.47% 的用户
     *
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return new ArrayList<>();
        }
        int col = matrix[0].length;

        Set<String> luckyNum = new HashSet<>();
        for (int i = 0; i < row; i++) {
            int luck = 0;
            for (int j = 1; j < col; j++) {
                if (matrix[i][luck] > matrix[i][j]) {
                    luck = j;
                }
            }
            luckyNum.add(i + "," + luck);
        }

        List<Integer> ans = new ArrayList<>();
        for (int j = 0; j < col; j++) {
            int luck = 0;
            for (int i = 1; i < row; i++) {
                if (matrix[luck][j] < matrix[i][j]) {
                    luck = i;
                }
            }
            if (luckyNum.contains(luck + "," + j)) {
                ans.add(matrix[luck][j]);
            }
        }
        return ans;
    }

    /**
     * 面试题 05.06. 整数转换
     * <p>
     * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了63.27% 的用户
     *
     * @param A 整数
     * @param B 整数
     * @return 需要改变几个位才能将整数A转成整数B
     */
    public int convertInteger(int A, int B) {
        int c = A ^ B;
        int ans = 0;
        while (c != 0) {
            ans++;
            c &= (c - 1);
        }
        return ans;
    }

    /**
     * 面试题 16.06. 最小差
     * <p>
     * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
     * <p>
     * 执行用时：24 ms, 在所有 Java 提交中击败了38.34% 的用户
     * 内存消耗：46.2 MB, 在所有 Java 提交中击败了88.76% 的用户
     *
     * @param a 整数数组
     * @param b 整数数组
     * @return 最小差绝对值的一对数值（每个数组中取一个值）
     */
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long ans = Math.abs((long) a[0] - (long) b[0]);
        int aa = 0, bb = 0;
        int an = a.length;
        int bn = b.length;
        while (aa < an || bb < bn) {
            if (aa >= an) {
                if (b[bb] > a[an - 1]) {
                    break;
                } else {
                    ans = Math.abs((long) a[an - 1] - (long) b[bb]);
                    bb++;
                }
            }
            if (bb >= bn) {
                if (b[bn - 1] < a[aa]) {
                    break;
                } else {
                    ans = Math.abs((long) a[aa] - (long) b[bn - 1]);
                    aa++;
                }
            }
            ans = Math.min(Math.abs((long) a[aa] - (long) b[bb]), ans);
            if (a[aa] < b[bb]) {
                aa++;
            } else if (a[aa] > b[bb]) {
                bb++;
            } else {
                return 0;
            }
        }
        return (int) ans;
    }

    /**
     * 1353. 最多可以参加的会议数目
     * <p>
     * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
     * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
     * 请你返回你可以参加的 最大 会议数目。
     * <p>
     * 执行用时：133 ms, 在所有 Java 提交中击败了5.69% 的用户
     * 内存消耗：80.9 MB, 在所有 Java 提交中击败了82.48% 的用户
     *
     * @param events 数组
     * @return 可以参加的 最大 会议数目
     */
    public int maxEvents(int[][] events) {
        Map<Integer, List<int[]>> eventMap = new TreeMap<>();
        for (int[] event : events) {
            List<int[]> list = eventMap.getOrDefault(event[0], new ArrayList<>());
            list.add(event);
            eventMap.put(event[0], list);
        }

        PriorityQueue<int[]> unDone = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int pre = 0;
        int ans = 0;
        for (Map.Entry<Integer, List<int[]>> entry : eventMap.entrySet()) {
            for (; pre < entry.getKey(); pre++) {
                while (!unDone.isEmpty()) {
                    int[] tmp = unDone.poll();
                    if (tmp[1] >= pre) {
                        ans++;
                        break;
                    }
                }
                if (unDone.isEmpty()) {
                    pre = entry.getKey();
                    break;
                }
            }
            unDone.addAll(entry.getValue());
        }
        while (!unDone.isEmpty()) {
            int[] tmp = unDone.poll();
            if (tmp[1] >= pre) {
                pre++;
                ans++;
            }
        }
        return ans;
    }

    /**
     * 1079. 活字印刷
     * <p>
     * 你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。
     * 注意：本题中，每个活字字模只能使用一次。
     * <p>
     * 执行用时：33 ms, 在所有 Java 提交中击败了20.98% 的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了15.27% 的用户
     *
     * @param tiles 活字字模
     * @return 可以印出的非空字母序列的数目
     */
    public int numTilePossibilities(String tiles) {
        int n = tiles.length();
        numTilePossibilities(tiles.toCharArray(), new boolean[n], "");
        return numTilePossibilitiesSet.size();
    }

    private Set<String> numTilePossibilitiesSet = new HashSet<>();

    private void numTilePossibilities(char[] chars, boolean[] used, String now) {
        if (now != "") {
            numTilePossibilitiesSet.add(now);
        }
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {
                used[i] = true;
                numTilePossibilities(chars, used, now + chars[i]);
                used[i] = false;
            }
        }
    }

    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * <p>
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.65% 的用户
     * 内存消耗：44.8 MB, 在所有 Java 提交中击败了86.45% 的用户
     *
     * @param nums 整型数组
     * @return 所有子数组的和的最大值
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preFix = new int[n + 1];
        preFix[1] = nums[0];
        for (int i = 1; i < n; i++) {
            preFix[i + 1] = preFix[i] + nums[i];
        }
        int min = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, preFix[i] - min);
            min = Math.min(min, preFix[i]);
        }
        return max;
    }

    /**
     * 1641. 统计字典序元音字符串的数目
     * <p>
     * 给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。
     * 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了59.91% 的用户
     *
     * @param n 整数
     * @return 统计字典序元音字符串的数目
     */
    public int countVowelStrings(int n) {
        // dp[i][]: dp[i][a] + dp[i-1][a]
        int[][] dp = new int[n][5];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i][0] + dp[i - 1][1];
            dp[i][2] = dp[i][1] + dp[i - 1][2];
            dp[i][3] = dp[i][2] + dp[i - 1][3];
            dp[i][4] = dp[i][3] + dp[i - 1][4];
        }
        int ans = 0;
        for (int value : dp[n - 1]) {
            ans += value;
        }
        return ans;
    }

    /**
     * 1043. 分隔数组以得到最大和
     * <p>
     * 给你一个整数数组 arr，请你将该数组分隔为长度最多为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
     * 返回将数组分隔变换后能够得到的元素最大和。
     * 注意，原数组和分隔后的数组对应顺序应当一致，也就是说，你只能选择分隔数组的位置而不能调整数组中的顺序。
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了94.16% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了70.35% 的用户
     *
     * @param arr 整数数组
     * @param k   长度
     * @return 分隔数组以得到最大和
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int j = i - 1;
            int max = dp[i];
            while ((i - j) <= k && j >= 0) {
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], dp[j] + (i - j) * max);
                j--;
            }
        }
        return dp[n];
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * <p>
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.42% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了71.18% 的用户
     *
     * @param root 二叉树
     * @return 其节点值的锯齿形层序遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> fromLeft = new LinkedList<>();
        Deque<TreeNode> fromRight = new LinkedList<>();
        if (root != null) {
            fromLeft.add(root);
        }
        List<List<Integer>> ans = new LinkedList<>();

        while (!fromLeft.isEmpty()) {
            List<Integer> left = new ArrayList<>();
            while (!fromLeft.isEmpty()) {
                TreeNode t = fromLeft.pollFirst();
                left.add(t.val);
                if (t.left != null) {
                    fromRight.addLast(t.left);
                }
                if (t.right != null) {
                    fromRight.addLast(t.right);
                }
            }
            ans.add(left);
            List<Integer> right = new ArrayList<>();
            while (!fromRight.isEmpty()) {
                TreeNode t = fromRight.pollLast();
                right.add(t.val);
                if (t.right != null) {
                    fromLeft.addFirst(t.right);
                }
                if (t.left != null) {
                    fromLeft.addFirst(t.left);
                }
            }
            if (right.size() > 0) {
                ans.add(right);
            }
        }
        return ans;
    }

    /**
     * 387. 字符串中的第一个唯一字符
     * <p>
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * <p>
     * 执行用时：8 ms, 在所有 Java 提交中击败了72.75% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了81.02% 的用户
     *
     * @param s 字符串
     * @return 字符串中的第一个唯一字符
     */
    public int firstUniqChar(String s) {
        int n = s.length();
        int[][] d = new int[26][2];
        for (int i = 0; i < n; i++) {
            d[s.charAt(i) - 'a'][0] = i;
            d[s.charAt(i) - 'a'][1]++;
        }
        int ans = Integer.MAX_VALUE;
        for (int[] dd : d) {
            if (dd[1] == 1) {
                ans = Math.min(ans, dd[0]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 135. 分发糖果
     * <p>
     * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     * 每个孩子至少分配到 1 个糖果。
     * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了31.64% 的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了12.43% 的用户
     *
     * @param ratings 评分
     * @return 糖果
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n <= 1) {
            return n;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
            if (ratings[n - i - 1] > ratings[n - i]) {
                right[n - i - 1] = right[n - i] + 1;
            } else {
                right[n - i - 1] = 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    // 12-24
    public int fun01(int n, int m) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int ans = 0;
        while (!queue.isEmpty()) {
            int tmp = Math.min(m, queue.size());
            for (int i = 0; i < tmp; i++) {
                int t = queue.poll();
                int a = (t + 1) / 2;
                int b = (t / 2);
                if (a > 1) {
                    queue.add(a);
                }
                if (b > 1) {
                    queue.add(b);
                }
            }
            ans++;
        }
        return ans;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * LCP 17. 速算机器人
     * <p>
     * 小扣在秋日市集发现了一款速算机器人。店家对机器人说出两个数字（记作 x 和 y），请小扣说出计算指令：
     * "A" 运算：使 x = 2 * x + y；
     * "B" 运算：使 y = 2 * y + x。
     * 在本次游戏中，店家说出的数字为 x = 1 和 y = 0，小扣说出的计算指令记作仅由大写字母 A、B 组成的字符串 s，
     * 字符串中字符的顺序表示计算顺序，请返回最终 x 与 y 的和为多少。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了92.15% 的用户
     *
     * @param s 字符串
     * @return 最终 x 与 y 的和为多少
     */
    public int calculate(String s) {
        int x = 1, y = 0;
        for (char c : s.toCharArray()) {
            switch (c) {
                case 'A' -> x = 2 * x + y;
                case 'B' -> y = 2 * y + x;
            }
        }
        return x + y;
    }

    /**
     * 1621. 大小为 K 的不重叠线段的数目
     * <p>
     * 给你一维空间的 n 个点，其中第 i 个点（编号从 0 到 n-1）位于 x = i 处，请你找到 恰好 k 个不重叠 线段且每个线段至少覆盖两个点的方案数。线段的两个端点必须都是 整数坐标 。这 k 个线段不需要全部覆盖全部 n 个点，且它们的端点 可以 重合。
     * 请你返回 k 个不重叠线段的方案数。由于答案可能很大，请将结果对 109 + 7 取余 后返回。
     * <p>
     * 执行用时：142 ms, 在所有 Java 提交中击败了45.45% 的用户
     * 内存消耗：78.2 MB, 在所有 Java 提交中击败了25.46% 的用户
     *
     * @param n n 个点
     * @param k k 个不重叠线段
     * @return 方案数
     */
    public int numberOfSets(int n, int k) {
        // dp[j][i][1]: 排了j-1条线 以i端点结尾
        // dp[j][i][0]: 排了j-1条线 不以i端点结尾
        long[][][] dp = new long[1010][1010][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], 0);
            }
            dp[i][0][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % 1000000007;
                dp[i][j][1] = (dp[i - 1][j - 1][0] + dp[i - 1][j][1] + dp[i - 1][j - 1][1]) % 1000000007;
            }
        }
        return (int) ((dp[n - 1][k][0] + dp[n - 1][k][1]) % 1000000007);
    }

    /**
     * 330. 按要求补齐数组
     *
     * 给定一个已排序的正整数数组 nums，和一个正整数 n 。
     * 从 [1, n] 区间内选取任意个数字补充到 nums 中，
     * 使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
     * 请输出满足上述要求的最少需要补充的数字个数。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了85.79% 的用户
     * @param nums 正整数数组
     * @param n 正整数
     * @return 最少需要补充的数字个数
     */
    public int minPatches(int[] nums, int n) {
        int ans = 0;
        long now = 0;
        int t = 0;
        for (long i = 1; i <= n; i++) {
            if (t < nums.length && nums[t] <= i) {
                now += nums[t];
                t++;
            }
            if (now < i) {
                now += i;
                ans++;
            }
            i = now;
        }
        return ans;
    }

    /**
     * 103
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> re = new ArrayList<>();
        Stack<TreeNode> st1 = new Stack<TreeNode>();
        Stack<TreeNode> st2 = new Stack<TreeNode>();
        if (root != null) {
            if (root.left != null) {
                st1.push(root.left);
            }
            if (root.right != null) {
                st1.push(root.right);
            }
            List<Integer> rr = new ArrayList<>();
            rr.add(root.val);
            re.add(rr);
            int useL = 1;
            while (!st1.isEmpty() || !st2.isEmpty()) {
                List<Integer> rr2 = new ArrayList<>();
                if (useL == 1) {
                    while (!st1.isEmpty()) {
                        TreeNode t = (TreeNode) st1.pop();
                        rr2.add(t.val);
                        if (t.right != null) {
                            st2.push(t.right);
                        }
                        if (t.left != null) {
                            st2.push(t.left);
                        }
                    }
                    useL = 0;
                } else {
                    while (!st2.isEmpty()) {
                        TreeNode t = (TreeNode) st2.pop();
                        rr2.add(t.val);
                        if (t.left != null) {
                            st1.push(t.left);
                        }
                        if (t.right != null) {
                            st1.push(t.right);
                        }
                    }
                    useL = 1;
                }
                re.add(rr2);
            }
        }
        return re;
    }

    public static int findDeep(TreeNode root, int deep) {
        if (root.left == null && root.right == null) {
            return deep;
        }
        if (root.left != null) {
            deep = findDeep(root.left, deep + 1);
        }
        if (root.right != null) {
            deep = findDeep(root.right, deep + 1);
        }
        return deep;
    }

    /**
     * 227
     */
    public int nthMagicalNumber(int N, int A, int B) {
        //求最大公约数
        long a = A > B ? A : B;
        long b = A > B ? B : A;
        long C = a % b;
        if (C == 0) {
            C = b;
        } else {
            while (C != 0) {
                a = b;
                b = C;
                C = a % b;
            }
            C = b;
        }
        //求遇见一次最小公倍数有几次神奇数字
        long p = A / C + B / C - 1;
        long q = N % p;
        long s = N / p;
        //求第q个神奇数字
        long ss = 0;
        a = A > B ? A : B;
        b = A > B ? B : A;
        if (0 == q) {
            ss = 0;
        } else if (1 == q) {
            ss = b;
        } else {
            long c = a;
            long d = b;
            char f = 'd';
            for (int i = 1; i < q; i++) {
                if (c > d && c < d + b) {
                    c = c + a;
                    f = 'e';
                } else if (d + b > c && d + b < c + a) {
                    d = d + b;
                    f = 'd';
                } else if (d + b < c) {
                    d = d + b;
                    f = 'd';
                } else if (d + b > c + a) {
                    c = c + a;
                    f = 'c';
                }
            }
            if (f == 'e') {
                ss = c - a;
            } else if ('c' == f) {
                ss = c;
            } else {
                ss = d;
            }
        }
        //取模
        long sss = ss;
        for (int i = 0; i < s; i++) {
            sss = sss + A / C * B;
            if (sss > 1000000007) {
                sss = sss % 1000000007;
            }
        }
        return (int) sss;
    }

    /**
     * 508
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        ArrayList tlist = new ArrayList();

        //后序遍历树求子树元素和
        Stack<TreeNode> stack = new Stack<TreeNode>();
        HashMap map = new HashMap();
        stack.push(root);
        if (root == null) {
            int[] zx = {};
            return zx;
        }
        if (root.left == null && root.right == null) {
            int[] zx = {root.val};
            return zx;
        }
        int value = 0;
        while (!stack.isEmpty()) {
            TreeNode t = stack.peek();

            if (t.right != null && !tlist.contains(t.right)) {
                stack.push(t.right);
            }
            if (t.left != null && !tlist.contains(t.left)) {
                stack.push(t.left);
            }

            if (t.left == null && t.right == null) {
                tlist.add(t);

                if (map.containsKey(t.val)) {
                    map.put(t.val, (int) map.get(t.val) + 1);
                    if ((int) map.get(t.val) > value) {
                        value = (int) map.get(t.val);
                    }
                } else {
                    map.put(t.val, 1);
                    value = value == 0 ? 1 : value;
                }
                stack.pop();
            } else if (t.left == null && tlist.contains(t.right)) {
                t.val = t.val + t.right.val;
                tlist.add(t);

                if (map.containsKey(t.val)) {
                    map.put(t.val, (int) map.get(t.val) + 1);
                    if ((int) map.get(t.val) > value) {
                        value = (int) map.get(t.val);
                    }
                } else {
                    map.put(t.val, 1);
                    value = value == 0 ? 1 : value;
                }
                stack.pop();
            } else if (tlist.contains(t.left) && t.right == null) {
                t.val = t.val + t.left.val;
                tlist.add(t);

                if (map.containsKey(t.val)) {
                    map.put(t.val, (int) map.get(t.val) + 1);
                    if ((int) map.get(t.val) > value) {
                        value = (int) map.get(t.val);
                    }
                } else {
                    map.put(t.val, 1);
                    value = value == 0 ? 1 : value;
                }
                stack.pop();
            } else if (tlist.contains(t.left) && tlist.contains(t.right)) {
                t.val = t.val + t.left.val + t.right.val;
                tlist.add(t);

                if (map.containsKey(t.val)) {
                    map.put(t.val, (int) map.get(t.val) + 1);
                    if ((int) map.get(t.val) > value) {
                        value = (int) map.get(t.val);
                    }

                } else {
                    map.put(t.val, 1);
                    value = value == 0 ? 1 : value;
                }
                stack.pop();
            }
        }
        //求子树元素和出现次数最多的数
        List keyList = new ArrayList();
        for (Object getKey : map.keySet()) {
            if (map.get(getKey).equals(value)) {
                keyList.add(getKey);
            }
        }
        int[] arr = new int[keyList.size()];
        for (int i = 0; i < keyList.size(); i++) {
            arr[i] = (int) keyList.get(i);
        }
        return arr;

    }

    /**
     * 872
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        boolean re = true;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        } else if (root2 == null) {
            return false;
        } else {
            s1.push(root1);
            s2.push(root2);
            List lp = new ArrayList();
            int j = 0;
            while (!s1.isEmpty()) {
                TreeNode p = s1.pop();
                if (p.right != null) {
                    s1.push(p.right);
                }
                if (p.left != null) {
                    s1.push(p.left);
                }
                if (p.left == null && p.right == null) {
                    lp.add(p.val);
                }
            }
            while (!s2.isEmpty()) {
                TreeNode p = s2.pop();
                if (p.right != null) {
                    s2.push(p.right);
                }
                if (p.left != null) {
                    s2.push(p.left);
                }
                if (p.left == null && p.right == null) {
                    if (p.val != (int) lp.get(j) || j >= lp.size()) {
                        re = false;
                        break;
                    }
                    j++;
                }
            }
            return re;
        }
    }

    /**
     * 709
     */
    public String toLowerCase(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] + 32);
            }
        }
        return ch.toString();
    }

    /**
     * 461
     */
    public int hammingDistance(int x, int y) {
        int res = x ^ y;
        int resu = 0;
        while (res != 0) {
            if (res % 2 == 1) {
                resu++;
            }
            res = res / 2;
        }
        return resu;
    }

    /**
     * 771
     */
    public int numJewelsInStones(String J, String S) {
//        char strJ[] = J.toCharArray();
//        char strS[] = S.toCharArray();
//        int result = 0;
////        List<Character> listJ= new ArrayList<Character>(Arrays.asList(strJ));
////        List<Character> listS= new ArrayList<Character>(Arrays.asList(strS));
//        for(int i = 0;i < strS.length;i++){
//            for(int j = 0; j < strJ.length;j++){
//                if(strS[i] == strJ[j]){
//                    result++;
//                    break;
//                }
//            }
//        }
//        return result;
        char[] cj = J.toCharArray();
        char[] cs = S.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : cj) {
            set.add(c);
        }
        int re = 0;
        for (char c : cs) {
            if (set.contains(c)) {
                re++;
            }
        }
        return re;
    }

    /**
     * 237
     */
    public void deleteNode(ListNode node) {
        if (node.next != null) {
            ListNode p = node.next;
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    /**
     * 338
     */
    public int[] countBits(int num) {
        int[] re = new int[num + 1];
        if (num > 2) {
            re[0] = 0;
            re[1] = 1;
            re[2] = 1;
            int k = 2;
            int j = 3;
            while (j <= num) {
                if (j == k * 2) {
                    k = k * 2;
                }
                re[j] = 1 + re[j - k];
                j++;
            }
        } else if (num == 0) {
            re[0] = 0;
        } else if (1 == num) {
            re[0] = 0;
            re[1] = 1;
        } else if (2 == num) {
            re[0] = 0;
            re[1] = 1;
            re[2] = 1;
        }
        return re;
    }

    /**
     * 476
     */
    public int findComplement(int num) {
        int[] arr = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535,
                131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863,
                134217727, 268435455, 536870911, 1073741823, 2147483647};
        int i = 0;
        for (; i < 32; i++) {
            if (num <= arr[i]) {
                break;
            }
        }
        return arr[i] - num;
    }

    /**
     * 728
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = left; i <= right; i++) {
            int j = i;
            boolean b = true;
            int k = j % 10;
            while (j / 10 != 0) {
                if (k == 0 || i % k != 0) {
                    b = false;
                    break;
                }
                j = j / 10;
                k = j % 10;
            }
            if (k == 0 || i % k != 0) {
                b = false;
            }
            if (b == true) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 463
     */
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) {
                        res++;
                    }
                    if (i == grid.length - 1 || grid[i + 1][j] == 0) {
                        res++;
                    }
                    if (j == 0 || grid[i][j - 1] == 0) {
                        res++;
                    }
                    if (j == grid[i].length - 1 || grid[i][j + 1] == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    //390
//    public int lastRemaining(int n) {
//        int flag = 1;//->
//        int i = n;
//        int j = 0;
//        while(true){
//            i = i / 2;
//            flag = flag == 1? 0 : 1;
//            j++;
//        }
//        if(flag == 1){
//
//        }
//        else{
//
//        }
//    }
//    public static int[] res(int [] sou, int flag){
//        int []re = new int [(sou.length)/2];
//        if(flag == 1) {
//            for (int i = 0; i < re.length; i++) {
//                re[i] = sou[2 * i];
//            }
//        }
//        else {
//            for (int i = 0; i < re.length; i++) {
//                re[i] = sou[2 * i];
//            }
//        }
//        return re;}

    /**
     * 810
     */
    public boolean xorGame(int[] nums) {
        int x = 0, n = nums.length;
        for (int num : nums) {
            x ^= num;
        }
        return x == 0 || n % 2 == 0;
    }

    /**
     * 331
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder == null) {
            return false;
        }
        if ("#".equals(preorder)) {
            return true;
        }
        String[] strings = preorder.split(",");
        if ("#".equals(strings[0])) {
            return false;
        }
        TreeNode root = new TreeNode(Integer.valueOf(strings[0]));
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int i = 1;
        while (!stack.empty() && i < strings.length) {
            TreeNode tt = stack.pop();
            if (tt == null) {
                if (!"#".equals(strings[i])) {
                    tt = new TreeNode(Integer.valueOf(strings[i]));
                    stack.push(tt.right);
                    stack.push(tt.left);
                }
                i++;
            } else {
                stack.push(tt.right);
                stack.push(tt.left);
            }
        }
        if (stack.empty() && i == strings.length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 786:超时
     */
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        if (A.length <= 1) {
            int[] res = new int[1];
            return res;
        }
        double[] res = new double[K];
        List<int[]> list = new LinkedList();
        for (int i = 0; i < K; i++) {
            if (K < A.length) {
                res[i] = 1.0 / (double) A[A.length - K];
                int[] tt = {1, A[A.length - K]};
                list.add(tt);
            } else {
                res[i] = 1.0;
                int[] tt = {1, 1};
                list.add(tt);
            }
        }
        double key = res[0];
        int x = 0, y = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                double t = (double) A[i] / (double) A[j];
                if (t < res[K - 1]) {
                    int k = K - 1;
                    while (k >= 0) {
                        if (t > res[k]) {
                            break;
                        }
                        k--;
                    }
                    k++;
                    for (int ii = K - 1; ii > k; ii--) {
                        res[ii] = res[ii - 1];
                        list.set(ii, list.get(ii - 1));
                    }
                    res[k] = t;
                    int[] tt = {A[i], A[j]};
                    list.set(k, tt);
                }
            }
        }
        return list.get(K - 1);
    }

    //786 -- 14ms -- 89.29%
    public int[] kthSmallestPrimeFraction1(int[] A, int K) {
        double lo = 0.0, hi = 1.0;
        int[] ans = {-1, -1};

        while (hi - lo > 1e-9) {
            // 一直压缩精确度到1e-9
            double mid = lo + (hi - lo) / 2.0;
            int[] res = under(A, mid);
            if (res[2] < K) {
                lo = mid;
            } else {
                hi = mid;
                ans[0] = res[0];
                ans[1] = res[1];
            }
        }

        return ans;
    }

    private int[] under(int[] A, double bound) {
        // 计算有多少数小于bound
        int mol = 0, deno = 1, count = 0, i = -1;
        for (int j = 1; j < A.length; j++) {
            while (A[i + 1] < bound * A[j]) {
                i++;
            }

            count += i + 1;

            if (i >= 0 && mol * A[j] < deno * A[i]) {
                mol = A[i];
                deno = A[j];
            }
        }
        return new int[]{mol, deno, count};
    }

    /**
     * 258
     */
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        int m = num % 9;
        if (m != 0) {
            return m;
        }
        return 9;
    }

    /**
     * 260
     */
    public int[] singleNumber(int[] nums) {
        int[] re = new int[2];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum ^= nums[i];
        }

        int flag = sum & (~(sum - 1));

        for (int num : nums) {
            if ((num & flag) == 0) {
                re[0] ^= num;
            } else {
                re[1] ^= num;
            }
        }

        return re;

    }

    //14 -- 16ms -- 18.25% -- 横向比较
    public String longestCommonPrefix0(String[] strs) {
        String res = "";
        if (strs.length == 0) {
            return res;
        }
        String begin = strs[0];
        if (strs.length == 1) {
            return begin;
        }
        for (int i = 1; i < strs.length; i++) {
            int bl = begin.length();
            String s = strs[i];
            int k = s.length() > bl ? bl : s.length();
            StringBuffer r = new StringBuffer();
            for (int j = 0; j < k; j++) {
                if (s.charAt(j) == begin.charAt(j)) {
                    r.append(s.charAt(j));
                } else {
                    break;
                }
            }
            begin = r.toString();
            if (begin.equals(res)) {
                return res;
            }
        }
        return begin;
    }

    //14 -- 8ms -- 83.66% -- 纵向比较
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder sb = new StringBuilder();
        if (strs.length > 1) {
            int len = strs[0].length();
            for (int i = 0; i < len; i++) {
                char curr = strs[0].charAt(i);
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].length() <= i || strs[j].charAt(i) != curr) {
                        return sb.toString();
                    }
                    if (strs[j].charAt(i) == curr && j == strs.length - 1) {
                        sb.append(curr);
                    }
                }
            }
        }
        return sb.toString();
    }

    //917 -- 13ms -- 18.45%
    public String reverseOnlyLetters(String S) {
        String res = "";
        char[] cs = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : cs) {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                stack.add(c);
            }
        }
        for (char c : cs) {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                res = res + stack.pop();
            } else {
                res = res + c;
            }
        }
        return res;
    }

    //378 -- 39ms -- 37.86%
    public int kthSmallest0(int[][] matrix, int k) {
        int[] ints = new int[matrix.length];
        int[] is = new int[matrix.length];
        int j = 0;
        for (int[] i : matrix) {
            ints[j] = i[0];
            is[j++] = 0;
        }
        j = 0;
        int res = matrix[0][0];
        while (j < k) {
            int min = 0;
            int m = ints[0];
            for (int i = 1; i < ints.length; i++) {
                if (m > ints[i]) {
                    min = i;
                    m = ints[i];
                }
            }
            res = ints[min];
            if (is[min] + 1 >= matrix[min].length) {
                ints[min] = Integer.MAX_VALUE;
            } else {
                ints[min] = matrix[min][++is[min]];
            }
            j++;
        }
        return res;
    }

    //378 -- 最大堆 -- 26ms -- 51.96%
    public int kthSmallest(int[][] m, int k) {
        /*
        26ms
        int[] a = new int[m.length * m.length];
        for(int i = 0;i < m.length;i++){
            for(int j = 0;j < m[i].length;j++){
                a[i * m.length + j] = m[i][j];
            }
        }
        Arrays.sort(a);
        return a[k - 1];
        */
        //堆排序 15ms (建堆整理堆真的是好麻烦啊。。。)
        if (k == m.length * m[0].length) {
            return m[m.length - 1][m[0].length - 1];
        }
        int[] heap = generateHeap(m);
        int count = 1;
        while (count <= k) {
            heapSort(heap, count);
            //System.out.println(heap[heap.length - count]);
            count++;
        }
        return heap[heap.length - count + 1];
    }

    private int[] generateHeap(int[][] m) {
        int[] heap = new int[m.length * m.length + 1];
        heap[0] = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                heap[i * m[0].length + j + 1] = m[i][j];
            }
        }
        /*
        建堆，向上调整，可能包含向下调整
        */
        for (int i = (heap.length - 1) / 2; i > 0; i--) {
            int l = 2 * i, r = 2 * i + 1;
            if (r >= heap.length - 1) {
                if (heap[l] < heap[i]) {
                    swap(heap, l, i);
                    downToHeap(heap, l, 1);
                }
            } else if (heap[l] < heap[r] && heap[l] < heap[i]) {
                swap(heap, l, i);
                downToHeap(heap, l, 1);
            } else if (heap[r] <= heap[l] && heap[r] < heap[i]) {
                swap(heap, r, i);
                downToHeap(heap, r, 1);
            }
        }
        /*
        for(int num:heap){
            System.out.print(num + " ");
        }
        System.out.println();
        */
        return heap;
    }

    /*
    向下调整，在建堆和堆整理过程中都可能会用到
    */
    private void downToHeap(int[] heap, int i, int count) {
        while (i * 2 < heap.length - count) {
            int l = 2 * i, r = 2 * i + 1;
            if (r == heap.length - count) {
                if (heap[l] <= heap[i]) {
                    swap(heap, l, i);
                    i = l;
                } else {
                    break;
                }
            } else {
                if (heap[l] < heap[r] && heap[l] <= heap[i]) {
                    swap(heap, l, i);
                    i = l;
                } else if (heap[r] <= heap[l] && heap[r] < heap[i]) {
                    swap(heap, r, i);
                    i = r;
                } else {
                    break;
                }

            }
        }
    }

    private void heapSort(int[] heap, int count) {
        swap(heap, 1, heap.length - count);
        //System.out.println("ok " + heap[heap.length - count]);
        downToHeap(heap, 1, count);
    }

    private void swap(int[] a, int i1, int i2) {
        int tmp = a[i1];
        a[i1] = a[i2];
        a[i2] = tmp;
    }

    //136 -- 1ms -- 99.46%
    public int singleNumber1(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    //137 -- 7ms -- 43.92%
    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                set1.add(n);
            } else {
                set.add(n);
            }
        }
        int res = 0;
        for (int n : nums) {
            if (set1.contains(n)) {
                continue;
            } else {
                res = n;
                break;
            }
        }
        return res;
    }

    //137 -- 类似异或 -- 1ms -- 96.76%
    /*
    * 【笔记】网上大佬曾经说，如果能设计一个状态转换电路，使得一个数出现3次时能自动抵消为0，最后剩下的就是只出现1次的数。

开始设计：一个二进制位只能表示0或者1。也就是天生可以记录一个数出现了一次还是两次。

x ^ 0 = x;
x ^ x = 0;
要记录出现3次，需要两个二进制位。那么上面单独的x就不行了。我们需要两个变量，每个变量取一位：

ab ^ 00 = ab;
ab ^ ab = 00;
这里，a、b都是32位的变量。我们使用a的第k位与b的第k位组合起来的两位二进制，表示当前位出现了几次。也就是，一个8位的二进制x就变成了16位来表示。

x = x[7] x[6] x[5] x[4] x[3] x[2] x[1] x[0]

x = (a[7]b[7]) (a[6]b[6]) ... (a[1]b[1]) (a[0]b[0])

于是，就有了这一幕....

它是一个逻辑电路，a、b变量中，相同位置上，分别取出一位，负责完成00->01->10->00，也就是开头的那句话，当数字出现3次时置零。*/
    public int singleNumber0(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            a = (a ^ x) & ~b;
            b = (b ^ x) & ~a;
        }
        return a;
    }


    //877
    //执行用时 : 0 ms, 在Stone Game的Java提交中击败了100.00% 的用户
    //内存消耗 : 34.2 MB, 在Stone Game的Java提交中击败了7.50% 的用户
    public boolean stoneGame(int[] piles) {
        return true;
    }

    //877
    //
    public boolean stoneGame1(int[] piles) {
        int length = piles.length;
        //results[i][j]存储的是piles中第i个数到第j个数组成序列的最佳拿取方式下的得分
        int[][] results = new int[length][length];
        //当集合中只有一个堆的时候，拿的那个人直接得分
        for (int i = 0; i < length; i++) {
            results[i][i] = piles[i];
        }
        //当集合中有两个数的时候，先选的人肯定是拿较大数,分数为max-min
        for (int i = 0; i < length - 1; i++) {
            results[i][i + 1] = Math.abs(piles[i] - piles[i + 1]);
        }
        /**当集合中元素大于2时，先选的人从序列两头拿，可以分成两种情况
         *以ABC为例，可以拿A，剩余BC，后手会选择BC的最佳拿取方式，
         *所以先手得分为A-BC得分，即：results[i][j]=piles[i]-results[i+1][j]；
         *也可以拿C，剩余AB，同理有results[i][j]=piles[j]-results[i][j-1]；
         *选择分值较大的那个即可。上面两个式子都要求我们在求results[i][j]的时候知道
         *它的下面和左边一个格子的值，所以我们从下到上，从左到右计算填表。
         */
        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                results[i][j] = Math.max(piles[i] - results[i + 1][j], piles[j] - results[i][j - 1]);
            }
        }
        return results[0][length - 1] > 0;
    }

    //877
    public boolean stoneGame2(int[] piles) {
        //dp其实就是存储了递归过程中的数值
        //dps[i][j]代表从i到j所能获得的最大的绝对分数
        //（比如为1就说明亚历克斯从i到j可以赢李1分）
        //如何计算dps[i][j]呢:max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]);
        //这里减去dps数组是因为李也要找到最大的
        //最后dps=[5 2 4 1]
        //        [0 3 1 4]
        //        [0 0 4 1]
        //        [0 0 0 5]
        int n = piles.length;
        int[][] dps = new int[n][n];
        //dps[i][i]存储当前i的石子数
        for (int i = 0; i < n; i++) {
            dps[i][i] = piles[i];
        }
        //d=1,其实代表，先算两个子的时候
        for (int d = 1; d < n; d++) {
            //有多少组要比较
            for (int j = 0; j < n - d; j++) {
                //比较j到d+j
                dps[j][d + j] = Math.max(piles[j] - dps[j + 1][d + j], piles[d + j] - dps[j][d + j - 1]);
            }
        }
        return dps[0][n - 1] > 0;
    }

    //312
    //执行用时 : 24 ms, 在Burst Balloons的Java提交中击败了9.71% 的用户
    //内存消耗 : 36.2 MB, 在Burst Balloons的Java提交中击败了0.00% 的用户
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] nums2 = new int[n + 2];
        nums2[0] = 1;
        nums2[n + 1] = 1;
        for (int i = 1; i < n + 1; i++) {
            nums2[i] = nums[i - 1];
        }
        //dp[i][j] = max(dp[i][k-1]+dp[k+1][j]+nums2[i-1]*nums2[j+1]*nums[k])
        for (int i = 1; i < n + 1; i++) {
            dp[i][i] = nums2[i - 1] * nums2[i] * nums2[i + 1];
        }
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < n + 1 - j; i++) {
                int max;
                for (int k = i; k <= i + j; k++) {
                    int left = (k - 1 >= i) ? dp[i][k - 1] : 0;
                    int right = (k + 1 <= i + j) ? dp[k + 1][i + j] : 0;
                    dp[i][i + j] = Math.max(left + right + nums2[i - 1] * nums2[j + i + 1] * nums2[k], dp[i][i + j]);
                }
            }
        }
        return dp[1][n];
    }

    //983
    //执行用时 : 2 ms, 在Minimum Cost For Tickets的Java提交中击败了100.00% 的用户
    //内存消耗 : 33.7 MB, 在Minimum Cost For Tickets的Java提交中击败了100.00% 的用户
    public int mincostTickets(int[] days, int[] costs) {
        // 将从新年到某一天的花过的所有钱数全部记录起来。
        int[] lastAllDaysCost = new int[366];
        //  days的下标，确保遍历365天时，以便于知道下次旅游的日期。
        int dayIdx = 0;
        // 日，月，年的花费。
        int ticketDay = costs[0];
        int ticketWeek = costs[1];
        int ticketMonth = costs[2];
        // 因为是第一天，所以过去的总花费为0
        lastAllDaysCost[0] = 0;
        // lastAllCost[i] 是截至到今年的第 i 天的总花费.

        // 模拟新年的第一天跑到旅行的最后一天。
        for (int today = 1; today <= 365; today++) {
            if (dayIdx >= days.length) {
                break;
            }
            // 判断今天是否属于旅行日。
            if (days[dayIdx] != today) {
                // 如果这一天不旅行那么直接把上一天的过去总花费拿过来直接使用。
                lastAllDaysCost[today] = lastAllDaysCost[today - 1];
                continue;
            }
            // 开始等待下一个待旅行的日子到来。
            dayIdx++;
            // 如果一月前，买了月票，会不会更便宜？
            // 如果一周前，买了周票，会不会更便宜？
            // 如果都不会的话，那我暂时先买日票试试呗。
            lastAllDaysCost[today] = Math.min(Math.min(
                    lastAllDaysCost[Math.max(0, today - 1)] + ticketDay,
                    lastAllDaysCost[Math.max(0, today - 7)] + ticketWeek),
                    lastAllDaysCost[Math.max(0, today - 30)] + ticketMonth);
        }
        return lastAllDaysCost[days[days.length - 1]];
    }

    //714
    //执行用时 : 38 ms, 在Best Time to Buy and Sell Stock with Transaction Fee的Java提交中击败了8.76% 的用户
    //内存消耗 : 67.7 MB, 在Best Time to Buy and Sell Stock with Transaction Fee的Java提交中击败了0.00% 的用户
    public int maxProfit(int[] prices, int fee) {
        //n个交易日
        int n = prices.length;
        //截止第i天的资金 + 是否持有股票
        int[][] res = new int[n][2];
        //一开始无收益
        res[0][0] = 0;
        res[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            // 今日非持有股票：昨天没买 || 卖了昨天买的；
            res[i][0] = Math.max(res[i - 1][0], res[i - 1][1] + prices[i] - fee);
            // 今日持有股票：昨天买了 || 昨天没有但是今日买入；
            res[i][1] = Math.max(res[i - 1][1], res[i - 1][0] - prices[i]);
        }
        //返回最后一天不持有收益
        return res[n - 1][0];
    }

    //978
    //执行用时 : 12 ms, 在Longest Turbulent Subarray的Java提交中击败了92.13% 的用户
    //内存消耗 : 42.6 MB, 在Longest Turbulent Subarray的Java提交中击败了92.00% 的用户
    public int maxTurbulenceSize(int[] A) {
        int l = A.length;
        if (l == 0) {
            return 0;
        }
        int x0 = 1;
        int x1 = 1;
        int max0 = 1;
        int max1 = 1;
        for (int i = 1; i < l; i++) {
            int t0 = A[i] > A[i - 1] ? x1 + 1 : 1;
            x1 = A[i] < A[i - 1] ? x0 + 1 : 1;
            max0 = Math.max(max0, t0);
            max1 = Math.max(max1, x1);
            x0 = t0;
        }
        return Math.max(max0, max1);
    }


    //1012 -- 不懂
    //执行用时 : 3 ms, 在Numbers With Repeated Digits的Java提交中击败了85.53% 的用户
    //内存消耗 : 32.9 MB, 在Numbers With Repeated Digits的Java提交中击败了32.88% 的用户
    public int numDupDigitsAtMostN(int N) {
        String nowNstr = String.valueOf(N);
        char[] nowNchar = nowNstr.toCharArray();
        int nowNlen = nowNchar.length;
        boolean flag = false;
        int count = 0;
        int dp9 = 0;

        int firstN = Integer.parseInt(nowNchar[0] + "");//第一位
        dp9 = (int) ((firstN + 1) * Math.pow(10, nowNlen - 1) - 1);

        if (nowNlen == 1) {
            return N - N;
        }

        for (int i = 1; i < nowNlen; i++) {

            int indexN = Integer.parseInt(nowNchar[i] + "");//当前位

            if (flag) {
                break;
            }

            int re = 9 - indexN;//当前位的取值范围

            if (i == nowNlen - 1) {//最后一位和前几位比较
                for (int k = 0; k < nowNlen - 1; k++) {
                    int a = Integer.parseInt(nowNchar[k] + "");
                    if (indexN < a && a != 0) {
                        re--;
                    }
                }
                count += re;
                return N - dp9(dp9) + count;
            }

            for (int k = 0; k < i; k++) {//当前位和前几位比较,有几个减几
                int a = Integer.parseInt(nowNchar[k] + "");
                if (indexN < a && a != 0) {
                    re--;
                }
            }

            for (int j = i; j < nowNlen - 1; j++) {//当前位是i i=1时 开始为7
                re *= (9 - j);
            }

            count += re;

            for (int k = 0; k < i; k++) {//本次的数字前几位出现过
                int a = Integer.parseInt(nowNchar[k] + "");
                if (indexN == a) {
                    flag = true;
                    break;
                }
            }

        }

        return N - dp9(dp9) + count;
    }

    public int dp9(int N) {
        String nowNstr = String.valueOf(N);
        char[] nowNchar = nowNstr.toCharArray();
        int nowNlen = nowNchar.length;

        if (nowNlen == 1) {
            return N;
        } else {
            int first = Integer.parseInt(nowNchar[0] + "");
            int thisNum = first;

            for (int i = 1; i < nowNlen; i++) {
                thisNum *= (10 - i);
            }

            int newN = (int) (N - first * Math.pow(10, nowNlen - 1));

            return thisNum + dp9(newN);
        }
    }

    //188
    //执行用时 : 7 ms, 在Best Time to Buy and Sell Stock IV的Java提交中击败了64.16% 的用户
    //内存消耗 : 37.4 MB, 在Best Time to Buy and Sell Stock IV的Java提交中击败了38.35% 的用户
    public int maxProfit4(int k, int[] prices) {
        /**
         当k大于等于数组长度一半时, 问题退化为贪心问题此时采用 买卖股票的最佳时机 II
         的贪心方法解决可以大幅提升时间性能, 对于其他的k, 可以采用 买卖股票的最佳时机 III
         的方法来解决, 在III中定义了两次买入和卖出时最大收益的变量, 在这里就是k租这样的
         变量, 即问题IV是对问题III的推广, t[i][0]和t[i][1]分别表示第i比交易买入和卖出时
         各自的最大收益
         **/
        if (k < 1) {
            return 0;
        }
        if (k >= prices.length / 2) {
            return greedy(prices);
        }
        int[][] t = new int[k][2];
        for (int i = 0; i < k; ++i) {
            t[i][0] = Integer.MIN_VALUE;
        }
        for (int p : prices) {
            t[0][0] = Math.max(t[0][0], -p);
            t[0][1] = Math.max(t[0][1], t[0][0] + p);
            for (int i = 1; i < k; ++i) {
                t[i][0] = Math.max(t[i][0], t[i - 1][1] - p);
                t[i][1] = Math.max(t[i][1], t[i][0] + p);
            }
        }
        return t[k - 1][1];
    }

    private int greedy(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }


    //94
    //执行用时 : 1 ms, 在Binary Tree Inorder Traversal的Java提交中击败了94.73% 的用户
    //内存消耗 : 35.8 MB, 在Binary Tree Inorder Traversal的Java提交中击败了38.69% 的用户
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inorderTraversal(root, list);
        return list;
    }

    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorderTraversal(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, list);
        }
        return;
    }

    //112
    //执行用时 : 1 ms, 在Path Sum的Java提交中击败了94.48% 的用户
    //内存消耗 : 36.9 MB, 在Path Sum的Java提交中击败了81.79% 的用户
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.right == null && root.left == null && root.val == sum) {
            return true;
        }
        if (root.val >= sum) {
            return false;
        }
        boolean res = false;
        if (root.left != null) {
            root.left.val += root.val;
            res = hasPathSum(root.left, sum);
            if (res) {
                return res;
            }
        }
        if (root.right != null) {
            root.right.val += root.val;
            res = hasPathSum(root.right, sum);
            if (res) {
                return res;
            }
        }
        return res;
    }

    //112
    //执行用时 : 1 ms, 在Path Sum的Java提交中击败了94.48% 的用户
    //内存消耗 : 39.1 MB, 在Path Sum的Java提交中击败了23.33% 的用户
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum1(root.left, sum - root.val)
                || hasPathSum1(root.right, sum - root.val);
    }

    //236:LCA问题  ST算法(自我实现的ST算法不够简洁，效率不高）
    //执行用时 : 727 ms, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了5.01% 的用户
    //内存消耗 : 34.4 MB, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了85.15% 的用户
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> dpsR = new LinkedList<>();
        List<Integer> deep = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        HashMap<TreeNode, Integer> dp = new HashMap<>();
        TreeNode t = root;
        int d = 0;
        while (t != null || !stack.isEmpty()) {
            if (t != null) {
                stack.push(t);
                dpsR.add(t);
                deep.add(d);
                dp.put(t, d);
                d++;
                t = t.left;
            } else {
                t = stack.pop();
                d = dp.get(t);
                dpsR.add(t);
                deep.add(d);
                t = t.right;
                d++;
            }
        }
        int xp = -1, xq = -1;
        for (int i = 0; i < dpsR.size(); i++) {
            if (p == dpsR.get(i)) {
                xp = i;
            }
            if (q == dpsR.get(i)) {
                xq = i;
            }
            if (xp > 0 && xq > 0) {
                break;
            }
        }
        int min = Integer.MAX_VALUE;
        int key = -1;
        if (xq < xp) {
            xq = xp ^ xq;
            xp = xp ^ xq;
            xq = xp ^ xq;
        }
        for (int i = xp; i <= xq; i++) {
            if (deep.get(i) < min) {
                min = deep.get(i);
                key = i;
            }
        }
        return dpsR.get(key);
    }

    //236:LCA问题  递归解决
    //执行用时 : 15 ms, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了77.64% 的用户
    //内存消耗 : 36.5 MB, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了70.85% 的用户
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    //236
    //执行用时 : 16 ms, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了48.14% 的用户
    //内存消耗 : 35.8 MB, 在Lowest Common Ancestor of a Binary Tree的Java提交中击败了80.59% 的用户
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        /**
         注意p,q必然存在树内, 且所有节点的值唯一!!!
         递归思想, 对以root为根的(子)树进行查找p和q, 如果root == null || p || q 直接返回root
         表示对于当前树的查找已经完毕, 否则对左右子树进行查找, 根据左右子树的返回值判断:
         1. 左右子树的返回值都不为null, 由于值唯一左右子树的返回值就是p和q, 此时root为LCA
         2. 如果左右子树返回值只有一个不为null, 说明只有p和q存在与左或右子树中, 最先找到的那个节点为LCA
         3. 左右子树返回值均为null, p和q均不在树中, 返回null
         **/
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        } else if (left != null && right != null) {
            return root;
        } else {
            return left == null ? right : left;
        }
    }

    //617
    //执行用时 : 2 ms, 在Merge Two Binary Trees的Java提交中击败了96.85% 的用户
    //内存消耗 : 47 MB, 在Merge Two Binary Trees的Java提交中击败了75.92% 的用户
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    //654
    //执行用时 : 4 ms, 在Maximum Binary Tree的Java提交中击败了98.64% 的用户
    //内存消耗 : 38.7 MB, 在Maximum Binary Tree的Java提交中击败了94.24% 的用户
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int b, int e) {
        if (nums.length <= 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int max = nums[b];
        int j = b;
        for (int i = b + 1; i <= e; i++) {
            if (max < nums[i]) {
                max = nums[i];
                j = i;
            }
        }
        TreeNode nt = new TreeNode(max);
        if (b < j) {
            nt.left = constructMaximumBinaryTree(nums, b, j - 1);
        }
        if (j < e) {
            nt.right = constructMaximumBinaryTree(nums, j + 1, e);
        }
        return nt;
    }

    //1008
    //执行用时 : 2 ms, 在Construct Binary Search Tree from Preorder Traversal的Java提交中击败了91.20% 的用户
    //内存消耗 : 33.7 MB, 在Construct Binary Search Tree from Preorder Traversal的Java提交中击败了99.64% 的用户
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, 0, preorder.length);
    }

    public TreeNode bstFromPreorder(int[] preorder, int b, int e) {
        if (preorder.length <= 0 || b > e) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        TreeNode nt = new TreeNode(preorder[b]);
        int key = b;
        while (key < e && preorder[key] <= preorder[b]) {
            key++;
        }
        if (b + 1 < key) {
            nt.left = bstFromPreorder(preorder, b + 1, key);
        }
        if (key < e) {
            nt.right = bstFromPreorder(preorder, key, e);
        }
        return nt;
    }

    //226
    //执行用时 : 1 ms, 在Invert Binary Tree的Java提交中击败了38.17% 的用户
    //内存消耗 : 33.2 MB, 在Invert Binary Tree的Java提交中击败了93.58% 的用户
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode t = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(t);
        return root;
    }

    //104
    //执行用时 : 1 ms, 在Maximum Depth of Binary Tree的Java提交中击败了89.15% 的用户
    //内存消耗 : 35.9 MB, 在Maximum Depth of Binary Tree的Java提交中击败了86.01% 的用户
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    public int maxDepth(TreeNode root, int deep) {
        if (root == null) {
            return deep;
        }
        deep++;
        deep = Math.max(maxDepth(root.left, deep), maxDepth(root.right, deep));
        return deep;
    }

    //814
    //执行用时 : 1 ms, 在Binary Tree Pruning的Java提交中击败了89.56% 的用户
    //内存消耗 : 33.4 MB, 在Binary Tree Pruning的Java提交中击败了99.30% 的用户
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    //894
    //执行用时 : 13 ms, 在All Possible Full Binary Trees的Java提交中击败了47.06% 的用户
    //内存消耗 : 51.5 MB, 在All Possible Full Binary Trees的Java提交中击败了58.90% 的用户
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<>();
        if (N % 2 == 0) {
            return ans;
        }
        if (N == 1) {
            TreeNode head = new TreeNode(0);
            ans.add(head);
            return ans;
        }
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - 1 - i);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode head = new TreeNode(0);
                    head.left = l;
                    head.right = r;
                    ans.add(head);
                }
            }
        }
        return ans;
    }

    //589
    //执行用时 : 3 ms, 在N-ary Tree Preorder Traversal的Java提交中击败了96.13% 的用户
    //内存消耗 : 59.8 MB, 在N-ary Tree Preorder Traversal的Java提交中击败了14.75% 的用户
    public List<Integer> preorder(Nodes root) {
        List<Integer> res = new LinkedList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(Nodes root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Nodes t : root.children) {
            preorder(t, res);
        }
        return;
    }

    //590
    //执行用时 : 3 ms, 在N-ary Tree Postorder Traversal的Java提交中击败了96.45% 的用户
    //内存消耗 : 56.2 MB, 在N-ary Tree Postorder Traversal的Java提交中击败了45.56% 的用户
    public List<Integer> postorder(Nodes root) {
        List<Integer> res = new LinkedList<>();
        postorder(root, res);
        return res;
    }

    public void postorder(Nodes root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Nodes t : root.children) {
            postorder(t, res);
        }
        res.add(root.val);
        return;
    }

    //700
    //执行用时 : 0 ms, 在Search in a Binary Search Tree的Java提交中击败了100.00% 的用户
    //内存消耗 : 48.3 MB, 在Search in a Binary Search Tree的Java提交中击败了37.67% 的用户
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    //559
    //执行用时 : 3 ms, 在Maximum Depth of N-ary Tree的Java提交中击败了85.59% 的用户
    //内存消耗 : 54.8 MB, 在Maximum Depth of N-ary Tree的Java提交中击败了37.78% 的用户
    public int maxDepth(Nodes root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0, 0);
    }

    private int dfs(Nodes root, int count, int maxDepth) {
        int index = 0;
        count++;
        if (root.children.isEmpty()) {
            return count;
        }
        while (index < root.children.size()) {
            maxDepth = Math.max(maxDepth, dfs(root.children.get(index++), count, maxDepth));
        }
        return maxDepth;
    }

    //701
    //执行用时 : 0 ms, 在Insert into a Binary Search Tree的Java提交中击败了100.00% 的用户
    //内存消耗 : 40.2 MB, 在Insert into a Binary Search Tree的Java提交中击败了90.48% 的用户
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    //979
    //执行用时 : 2 ms, 在Distribute Coins in Binary Tree的Java提交中击败了91.28% 的用户
    //内存消耗 : 37 MB, 在Distribute Coins in Binary Tree的Java提交中击败了72.73% 的用户
    int sum = 0;

    public int distributeCoins(TreeNode root) {
        getSum(root);
        return sum;
    }

    int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //负数表示左节点需要扣除的金币，需要从根节点搬金币下去；正数表示多出的金币，需要将多出的金币搬上去；
        int l = getSum(root.left);
        //负数表示右节点需要扣除的金币，需要从根节点搬金币下去；正数表示多出的金币，需要将多出的金币搬上去；
        int r = getSum(root.right);
        //计算路径（金币数的绝对值就是路径）
        sum += Math.abs(l) + Math.abs(r);
        //返回节点的金币量（已经扣除本身以及左、右子节点需要的）
        return l + r + root.val - 1;
    }

    //230
    //执行用时 : 2 ms, 在Kth Smallest Element in a BST的Java提交中击败了57.19% 的用户
    //内存消耗 : 39.1 MB, 在Kth Smallest Element in a BST的Java提交中击败了65.81% 的用户
    int preVisit = 0;
    boolean bpre = false;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        kthSmallest(root.left, k);
        if (!bpre) {
            preVisit++;
        }
        if (preVisit == k && !bpre) {
            preVisit = root.val;
            bpre = true;
        }
        kthSmallest(root.right, k);
        return preVisit;
    }

    //108
    //执行用时 : 2 ms, 在Convert Sorted Array to Binary Search Tree的Java提交中击败了37.98% 的用户
    //内存消耗 : 37.8 MB, 在Convert Sorted Array to Binary Search Tree的Java提交中击败了65.98% 的用户
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST(int[] nums, int b, int e) {
        if (b > e) {
            return null;
        }
        if (b + 1 == e) {
            return new TreeNode(nums[b]);
        }
        int i = (e + b) / 2;
        TreeNode nt = new TreeNode(nums[i]);
        if (b < i) {
            nt.left = sortedArrayToBST(nums, b, i);
        }
        if (i + 1 < e) {
            nt.right = sortedArrayToBST(nums, i + 1, e);
        }
        return nt;
    }

    //109
    //执行用时 : 7 ms, 在Convert Sorted List to Binary Search Tree的Java提交中击败了6.42% 的用户
    //内存消耗 : 41.3 MB, 在Convert Sorted List to Binary Search Tree的Java提交中击败了39.48% 的用户
    public TreeNode sortedListToBST0(ListNode head) {
        List<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] arr = new int[list.size()];
        int j = 0;
        for (int i : list) {
            arr[j++] = i;
        }
        return sortedArrayToBST(arr);
    }

    //109
    //执行用时 : 3 ms, 在Convert Sorted List to Binary Search Tree的Java提交中击败了54.81% 的用户
    //内存消耗 : 39.4 MB, 在Convert Sorted List to Binary Search Tree的Java提交中击败了96.22% 的用户
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode preNode = preMiddleNode(head);
        ListNode midNode = preNode.next;
        preNode.next = null;
        TreeNode root = new TreeNode(midNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(midNode.next);
        return root;
    }

    public ListNode preMiddleNode(ListNode head) {
        // slow表示中间节点(偶数取后一个), pre表示中间节点的前一个节点
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }

    /**
     * 125. 验证回文串
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.91%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了7.14%的用户
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len <= 1) {
            return true;
        }
        int i = 0, j = len - 1;
        while (i <= j) {
            while (i <= j && allToInteger(chars[i]) == -1) {
                i++;
            }
            while (i <= j && allToInteger(chars[j]) == -1) {
                j--;
            }
            if (i <= j) {
                if (allToInteger(chars[i]) != allToInteger(chars[j])) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }

    public int allToInteger(char a) {
        if (48 <= (int) a && (int) a <= 57) {
            return a;
        } else if (65 <= (int) a && (int) a <= 90) {
            return a;
        } else if (97 <= (int) a && (int) a <= 122) {
            return a - 32;
        } else {
            return -1;
        }
    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        dp[0][0] = true;
        // here's the p's length, not s's
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                // here's y axis should be i+1
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                //如果是任意元素 或者是对于元素匹配
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    //如果前一个元素不匹配 且不为任意元素
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                            /*
                            dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
                            or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                            or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                             */

                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 题目：42. 接雨水
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了12.86%的用户
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        int i = 0, j = len - 1;
        int sum = 0;
        int all = 0;
        int max = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                sum = sum + height[i];
                max = height[i] > max ? height[i] : max;
                all = all + max;
                i++;
            } else {
                sum = sum + height[j];
                max = height[j] > max ? height[j] : max;
                all = all + max;
                j--;
            }
        }
        return all - sum;
    }


    public void quickSort(int[] a, int l, int h) {
        if (l > h) {
            return;
        }
        int b = l, e = h;
        int k = a[l];
        while (b < e) {
            while (b < e && a[e] <= k) {
                e--;
            }
            if (b < e) {
                a[b] = a[e];
                b++;
            }
            while (a[b] >= k && b < e) {
                b++;
            }
            if (b < e) {
                a[e] = a[b];
                e--;
            }
        }
        /*此时i==j*/
        a[b] = k;

        /*递归调用，把key前面的完成排序*/
        this.quickSort(a, l, b - 1);


        /*递归调用，把key后面的完成排序*/
        this.quickSort(a, e + 1, h);
    }

    /**
     * 剑指 Offer 24. 反转链表
     * 执行用时：1 ms, 在所有 Java 提交中击败了6.37%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param head 初始链表
     * @return 反转链表
     */
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        ListNode newHead = stack.pop();
        ListNode p = newHead;
        while (!stack.isEmpty()) {
            ListNode tmp = stack.pop();
            p.next = tmp;
            p = tmp;

        }
        p.next = null;
        return newHead;
    }

    /**
     * 剑指 Offer 25. 合并两个排序的链表
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.36%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 合并两个排序的链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                p = p.next;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = p.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            p.next = l1;
        } else if (l2 != null) {
            p.next = l2;
        }
        return head.next;
    }

    public static ListNode createListNode(int[] array) {
        ListNode list = new ListNode(array[0]);
        ListNode p = list;
        for (int i = 1; i < array.length; i++) {
            ListNode tmp = new ListNode(array[i]);
            p.next = tmp;
            p = p.next;
        }
        return list;
    }

    /**
     * 96. 不同的二叉搜索树
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了7.69%的用户
     *
     * @param n n个结点
     * @return 几种不同的二叉搜索树
     */
    public int numTrees(int n) {
        if (n == 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    /**
     * 97. 交错字符串
     * 执行用时：5 ms, 在所有 Java 提交中击败了58.20%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了14.29%的用户
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @param s3 字符串3
     * @return 字符串3是否由字符串1和字符串2交错而成
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 1374. 生成每种字符都是奇数个的字符串
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.12%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param n int n
     * @return String
     */
    public String generateTheString(int n) {
        String res = "";
        if (n % 2 == 1) {
            char[] chars = new char[n];
            Arrays.fill(chars, 'a');
            res = new String(chars);
        } else {
            char[] chars = new char[n - 1];
            Arrays.fill(chars, 'a');
            res = new String(chars) + 'b';
        }
        return res;
    }


    /**
     * 327. 区间和的个数
     * <p>
     * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
     * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
     * <p>
     * 执行用时：188 ms, 在所有 Java 提交中击败了15.33% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了97.00% 的用户
     *
     * @param nums  整数数组 nums
     * @param lower 最小边界
     * @param upper 最大边界
     * @return 区间和在 [lower, upper] 之间的个数
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n <= 0) {
            return 0;
        }
        int num = 0;
        long[] pre = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                long tmp = pre[j] - pre[i];
                if (tmp >= lower && tmp <= upper) {
                    num++;
                }
            }
        }
        return num;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[n - 1][1];
    }

    /**
     * 1122. 数组的相对排序
     * 给你两个数组，arr1 和 arr2，
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
     * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了39.49% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了19.69% 的用户
     *
     * @param arr1 数组
     * @param arr2 数组
     * @return 数组的相对排序
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr2) {
            map.put(a, 0);
        }
        PriorityQueue<Integer> list = new PriorityQueue<>();
        for (int a : arr1) {
            if (map.containsKey(a)) {
                map.put(a, map.get(a) + 1);
            } else {
                list.add(a);
            }
        }
        int t = 0;
        for (int i = 0; i < arr2.length; i++) {
            int tmp = map.get(arr2[i]);
            for (int j = 0; j < tmp; j++) {
                arr1[t++] = arr2[i];
            }
        }
        while (!list.isEmpty()) {
            arr1[t++] = list.poll();
        }
        return arr1;
    }


    /**
     * 1402. 做菜顺序
     * <p>
     * 一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
     * 一道菜的 「喜爱时间」系数定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
     * 请你返回做完所有菜 「喜爱时间」总和的最大值为多少。
     * 你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了82.45% 的用户
     *
     * @param satisfaction n 道菜的满意程度 satisfaction
     * @return 返回做完所有菜 「喜爱时间」总和的最大值为多少
     */
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int sum = 0;
        int done = 0;
        int n = satisfaction.length;
        for (int i = n - 1; i >= 0; i--) {
            int tmp = sum + done + satisfaction[i];
            if (tmp >= sum) {
                done += satisfaction[i];
                sum = tmp;
            } else {
                break;
            }
        }
        return sum;
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            int t = map.getOrDefault(a, 0) + 1;
            map.put(a, t);
        }
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if (!set.add(m.getValue())) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        pathSumDFS(root, sum, 0, new ArrayList<>());
        return pathSumResult;
    }

    List<List<Integer>> pathSumResult = new ArrayList<>();

    private void pathSumDFS(TreeNode root, int target, int now, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.val + now == target) {
            if (root.left == null && root.right == null) {
                List<Integer> cop = new ArrayList<>(List.copyOf(list));
                cop.add(root.val);
                pathSumResult.add(cop);
                return;
            }
        }
        now = root.val + now;
        list.add(root.val);
        pathSumDFS(root.left, target, now, list);
        pathSumDFS(root.right, target, now, list);
        list.remove(list.size() - 1);
    }

    public int largestIsland0(int[][] grid) {
        int key = 10;
        Map<Integer, Integer> area = new HashMap<>();
        int n = grid.length;
        int m = grid[0].length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int a = largestIslandAres(grid, new int[]{i, j}, key);
                    area.put(key, a);
                    key++;
                    max = Math.max(a, max);
                }
            }
        }
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int tmp = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int[] d : dir) {
                        int nx = d[0] + i;
                        int ny = d[1] + j;
                        if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length) {
                            continue;
                        }
                        if (area.containsKey(grid[nx][ny]) && !set.contains(grid[nx][ny])) {
                            set.add(grid[nx][ny]);
                            tmp += area.get(grid[nx][ny]);
                        }
                    }
                    max = Math.max(max, tmp);
                }
            }
        }
        return max;
    }

    private int largestIslandAres(int[][] grid, int[] begin, int key) {
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Stack<int[]> stack = new Stack<>();
        stack.push(begin);
        int area = 0;
        while (!stack.isEmpty()) {
            int[] tmp = stack.pop();
            int x = tmp[0];
            int y = tmp[1];
            if (grid[x][y] != 1) {
                continue;
            }
            grid[x][y] = key;
            area++;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length) {
                    continue;
                }
                if (grid[nx][ny] == 1) {
                    stack.push(new int[]{nx, ny});
                }
            }
        }
        return area;
    }

    /**
     * 493. 翻转对
     * <p>
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     * <p>
     * 你需要返回给定数组中的重要翻转对的数量。
     * <p>
     * 执行用时：183 ms, 在所有 Java 提交中击败了15.47% 的用户
     * 内存消耗：57.4 MB, 在所有 Java 提交中击败了5.09% 的用户
     *
     * @param nums 数组
     * @return 返回给定数组中的重要翻转对的数量
     */
    public int reversePairs(int[] nums) {
        Set<Long> set = new TreeSet<>();
        Arrays.stream(nums).forEach(x -> {
            set.add((long) x);
            set.add((long) 2 * x);
        });
        int n = set.size();
        int i = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (long s : set) {
            map.put(s, i);
            i++;
        }
        BinaryIndexedTree bit = new BinaryIndexedTree(new int[n]);
        int ans = 0;
        for (long val : nums) {
            int upIdx = map.get(val);
            int doubleIdx = map.get(val * 2);
            ans += bit.prefixSum(n - 1) - bit.prefixSum(doubleIdx);
            bit.update(upIdx, 1);
        }
        return ans;
    }

    /**
     * 1649. 通过指令创建有序数组
     * <p>
     * 给你一个整数数组 instructions ，你需要根据 instructions 中的元素创建一个有序数组。
     * 一开始你有一个空的数组 nums ，你需要 从左到右 遍历 instructions 中的元素，将它们依次插入 nums 数组中。
     * 每一次插入操作的 代价 是以下两者的 较小值 ：
     * nums 中 严格小于  instructions[i] 的数字数目。
     * nums 中 严格大于  instructions[i] 的数字数目。
     * 比方说，如果要将 3 插入到 nums = [1,2,3,5] ，那么插入操作的 代价 为 min(2, 1)
     * (元素 1 和  2 小于 3 ，元素 5 大于 3 ），插入后 nums 变成 [1,2,3,3,5] 。
     * 请你返回将 instructions 中所有元素依次插入 nums 后的 总最小代价 。由于答案会很大，
     * 请将它对 109 + 7 取余 后返回。
     * <p>
     * 执行用时：266 ms, 在所有 Java 提交中击败了28.47% 的用户
     * 内存消耗：68.7 MB, 在所有 Java 提交中击败了5.10% 的用户
     *
     * @param instructions 整数数组
     * @return 将 instructions 中所有元素依次插入 nums 后的 总最小代价
     */
    public int createSortedArray(int[] instructions) {
        Set<Integer> set = new TreeSet<>();
        Arrays.stream(instructions).forEach(set::add);
        int n = set.size();
        Map<Integer, Integer> map = new HashMap<>();
        set.forEach(x -> map.put(x, map.size()));
        int ans = 0;
        BinaryIndexedTree bit = new BinaryIndexedTree(n);
        for (int i : instructions) {
            int key = map.get(i);
            int left = bit.prefixSum(key - 1);
            int right = bit.prefixSum(key);
            int last = bit.prefixSum(n - 1);
            ans += Math.min(left, last - right);
            ans = ans % 1000000007;
            bit.update(key, 1);
        }
        return ans;
    }

    /**
     * 976. 三角形的最大周长
     * <p>
     * 给定由一些正数（代表长度）组成的数组 arr，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
     * 如果不能形成任何面积不为零的三角形，返回 0。
     * <p>
     * 执行用时：8 ms, 在所有 Java 提交中击败了97.44% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了87.52% 的用户
     *
     * @param arr 数组
     * @return 三角形的最大周长
     */
    public int largestPerimeter(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return 0;
        }
        Arrays.sort(arr);
        for (int i = n - 1; i >= 2; i--) {
            if (arr[i] < arr[i - 1] + arr[i - 2]) {
                return arr[i] + arr[i - 1] + arr[i - 2];
            }
        }
        return 0;
    }

    /**
     * 539. 最小时间差
     * <p>
     * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     * <p>
     * 执行用时：18 ms, 在所有 Java 提交中击败了19.52% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了28.84% 的用户
     *
     * @param timePoints 时间列表
     * @return 最小时间差
     */
    public int findMinDifference(List<String> timePoints) {
        int ans = Integer.MAX_VALUE;
        int first = 0;
        boolean isFirst = true;
        int last = 0;
        Collections.sort(timePoints);
        for (String time : timePoints) {
            String[] t = time.split(":");
            if (isFirst) {
                isFirst = false;
                first = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
                last = first;
            } else {
                int tmp = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
                ans = Math.min(ans, tmp - last);
                last = tmp;
            }
        }
        ans = Math.min(ans, first + 24 * 60 - last);
        return ans;
    }

    public int leastInterval(char[] tasks, int n) {
        int result = 0;
        int[] num = new int[26];
        int max = 0;
        int all = 0;
        for (char task : tasks) {
            int key = task - 'A';
            num[key]++;
            if (max < num[key]) {
                all = 1;
                max = num[key];
            } else if (max == num[key]) {
                all++;
            }
        }
        result = (max - 1) * n + max + all - 1;
        return Math.max(result, tasks.length);
    }

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了39.39% 的用户
     *
     * @param numRows 非负整数
     * @return 杨辉三角前 numRows 行
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        List<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);
        while (numRows > 1) {
            List<Integer> pre = res.get(res.size() - 1);
            List<Integer> newList = new ArrayList<>();
            newList.add(1);
            for (int i = 1; i < pre.size(); i++) {
                newList.add(pre.get(i - 1) + pre.get(i));
            }
            newList.add(1);
            res.add(newList);
            numRows--;
        }
        return res;
    }

    /**
     * 376. 摆动序列
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
     * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
     * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
     * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了82.87% 的用户
     *
     * @param nums 连续数字
     * @return 最长摆动子序列长度
     */
    public int wiggleMaxLength(int[] nums) {
        int ans = 1;
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        //先不处理等于的情况
        int i = 1;
        boolean up = nums[0] < nums[1];
        while (i < n) {
            if (nums[i] != nums[i - 1]) {
                up = nums[i - 1] < nums[i];
                break;
            }
            i++;
        }
        for (; i < n; i++) {
            if (up && nums[i] > nums[i - 1]) {
                up = false;
                ans++;
            } else if (!up && nums[i] < nums[i - 1]) {
                up = true;
                ans++;
            }
        }
        return ans;
    }


    /**
     * 827. 最大人工岛
     * <p>
     * 在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。
     * 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）
     * <p>
     * 执行用时：233 ms, 在所有 Java 提交中击败了19.65% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了31.78% 的用户
     *
     * @param grid 二维地图
     * @return 最大人工岛
     */
    public int largestIsland(int[][] grid) {
        List<int[]> list = new ArrayList<>();
        int ans = 0;
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }
        if (list.isEmpty()) {
            return row * col;
        }

        for (int[] t : list) {
            largestIslandNum = 1;
            largestIsland(grid, t[0], t[1], new boolean[row][col]);
            ans = Math.max(ans, largestIslandNum);
        }
        return ans;
    }

    private int largestIslandNum = 0;
    private int[][] largestIslandDir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void largestIsland(int[][] grid, int x, int y, boolean[][] visited) {
        for (int[] dir : largestIslandDir) {
            int xx = x + dir[0];
            int yy = y + dir[1];
            if (xx < 0 || xx >= grid.length || yy < 0 || yy >= grid[0].length || visited[xx][yy] || grid[xx][yy] == 0) {
                continue;
            }
            largestIslandNum++;
            visited[xx][yy] = true;
            largestIsland(grid, xx, yy, visited);
        }
    }

    /**
     * 827. 最大人工岛
     * 在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。
     * 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）
     * <p>
     * 并查集
     * <p>
     * 执行用时：10 ms, 在所有 Java 提交中击败了71.49% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了48.12% 的用户
     *
     * @param grid
     * @return
     */
    public int largestIsland1(int[][] grid) {
        int res = 0, a = grid.length, len = a * a;
        int[] k = new int[len];
        Arrays.fill(k, 1);
        int[] father = new int[len];
        Arrays.fill(father, -1);
        for (int i = 0; i < a; ++i) {
            for (int j = 0; j < a; ++j) {
                if (grid[i][j] == 1) {
                    father[i * a + j] = i * a + j;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        int t = find(father, (i - 1) * a + j);
                        k[i * a + j] += k[t];
                        father[t] = i * a + j;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        int t = find(father, i * a + j - 1);
                        if (t == i * a + j) {
                            continue;
                        }
                        k[i * a + j] += k[t];
                        father[t] = i * a + j;
                    }
                }
            }
        }
        for (int i = 0; i < a; ++i) {
            for (int j = 0; j < a; ++j) {
                if (0 == grid[i][j]) {
                    Set<Integer> m = new HashSet<>();
                    int temp = 1, p = i * a + j;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        int t = find(father, p - a);
                        temp += k[t];
                        m.add(t);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        int t = find(father, p - 1);
                        if (m.add(t)) {
                            temp += k[t];
                        }
                    }
                    if (i < a - 1 && grid[i + 1][j] == 1) {
                        int t = find(father, p + a);
                        if (m.add(t)) {
                            temp += k[t];
                        }
                    }
                    if (j < a - 1 && grid[i][j + 1] == 1) {
                        int t = find(father, p + 1);
                        if (m.add(t)) {
                            temp += k[t];
                        }
                    }
                    res = Math.max(res, temp);
                }
            }
        }
        return res == 0 ? a * a : res;
    }

    int find(int[] father, int p) {
        if (father[p] == p) {
            return p;
        }
        return father[p] = find(father, father[p]);
    }

    /**
     * 217. 存在重复元素
     * <p>
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了76.63% 的用户
     * 内存消耗：42.5 MB, 在所有 Java 提交中击败了76.75% 的用户
     *
     * @param nums 数组
     * @return 是否存在重复的元素
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            if (!set.add(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 48. 旋转图像
     * <p>
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * 说明：
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了88.35% 的用户
     *
     * @param matrix 矩阵
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) {
            return;
        }
        int t = n / 2;
        for (int i = 0; i <= t; i++) {
            if (t == i && n % 2 == 0) {
                break;
            }
            for (int j = 0; j < t; j++) {
//                 5,1,9,11
//                 2,4,8,10
//                 13,3,6,7
//                 15,14,12,16

                // n == 5: 0,1 & 1,4
                // swap 1&2
                matrix[i][j] = matrix[i][j] ^ matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j] ^ matrix[j][n - 1 - i];
                matrix[i][j] = matrix[i][j] ^ matrix[j][n - 1 - i];

                // n == 5: 0,1(原 1,4) & 4,3
                // swap 1&3
                matrix[i][j] = matrix[i][j] ^ matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[i][j] ^ matrix[n - 1 - i][n - 1 - j];
                matrix[i][j] = matrix[i][j] ^ matrix[n - 1 - i][n - 1 - j];

                // n == 5: 0,1(原 4,3) & 3,0
                // swap 1&4
                matrix[i][j] = matrix[i][j] ^ matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[i][j] ^ matrix[n - 1 - j][i];
                matrix[i][j] = matrix[i][j] ^ matrix[n - 1 - j][i];
            }
        }
    }

    /**
     * 1288. 删除被覆盖区间
     * <p>
     * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
     * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了95.73% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了75.70% 的用户
     *
     * @param intervals 区间列表
     * @return 剩余区间的数目
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        };

        Arrays.sort(intervals, comparator);
        int max = Integer.MIN_VALUE;
        int ans = 0;
        for (int[] interval : intervals) {
            if (interval[1] > max) {
                max = interval[1];
                ans++;
            }
        }
        return ans;
    }

    /**
     * 1684. 统计一致字符串的数目
     * <p>
     * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
     * 请你返回 words 数组中 一致字符串 的数目。
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param allowed 字符串
     * @param words   字符串数组
     * @return 数组中 一致字符串 的数目
     */
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] chars = new boolean[26];
        for (char c : allowed.toCharArray()) {
            chars[c - 'a'] = true;
        }
        int ans = 0;
        for (String word : words) {
            int tmp = 1;
            for (char c : word.toCharArray()) {
                if (!chars[c - 'a']) {
                    tmp = 0;
                    break;
                }
            }
            ans += tmp;
        }
        return ans;
    }

    /**
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     * <p>
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了85.86% 的用户
     *
     * @param nums 数组
     * @return 数组中数字出现的次数
     */
    public int singleNumber3(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }


    /**
     * 面试题 08.04. 幂集
     * <p>
     * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
     * 说明：解集不能包含重复的子集。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.39% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了77.81% 的用户
     *
     * @param nums 集合
     * @return 集合的所有子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        subsets(nums, new ArrayList<>(), 0);
        return subsetsList;
    }

    private List<List<Integer>> subsetsList = new ArrayList<>();

    private void subsets(int[] num, List<Integer> list, int left) {
        List<Integer> newList = new ArrayList<>();
        newList.addAll(list);
        subsetsList.add(newList);
        for (int i = left; i < num.length; i++) {
            list.add(num[i]);
            subsets(num, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public String removeDuplicateLetters(String s) {
        char[] chars = s.toCharArray();
        int[] charNum = new int[26];
        boolean[] vis = new boolean[26];
        for (char cha : chars) {
            charNum[cha - 'a']++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                while (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) > ch) {
                    if (charNum[stringBuilder.charAt(stringBuilder.length() - 1) - 'a'] > 0) {
                        vis[stringBuilder.charAt(stringBuilder.length() - 1) - 'a'] = false;
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                stringBuilder.append(ch);
            }
            charNum[ch - 'a'] -= 1;
        }
        return stringBuilder.toString();
    }

    /**
     * 746. 使用最小花费爬楼梯
     * <p>
     * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.68% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了76.73% 的用户
     *
     * @param cost 非负数的体力花费值
     * @return 达到楼层顶部的最低花费
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n < 2) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }

    /**
     * 455. 分发饼干
     * <p>
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
     * 都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，
     * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * <p>
     * 执行用时：8 ms, 在所有 Java 提交中击败了88.98% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了44.32% 的用户
     *
     * @param g 胃口值
     * @param s 尺寸
     * @return 分发饼干
     */
    public int findContentChildren(int[] g, int[] s) {
        int m = s.length;
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        int j = 0;
        for (int value : g) {
            while (j < m && s[j] < value) {
                j++;
            }
            if (j >= m) {
                break;
            }
            ans++;
            j++;
        }
        return ans;
    }

    /**
     * 85. 最大矩形
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了78.93% 的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了51.30% 的用户
     *
     * @param matrix 二维二进制矩阵
     * @return 最大矩形
     */
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        if (n <= 0) {
            return 0;
        }
        int m = matrix[0].length;
        int[][] pre = new int[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            pre[i][0] = matrix[i][0] - '0';
            ans = Math.max(pre[i][0], ans);
            for (int j = 1; j < m; j++) {
                pre[i][j] = matrix[i][j] == '0' ? 0 : pre[i][j - 1] + 1;
                ans = Math.max(pre[i][j], ans);
            }
        }
        // 1 2 5 4
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                int key = pre[i][j];
                int len = 1;
                for (int ii = i - 1; ii >= 0; ii--) {
                    if (pre[ii][j] == 0) {
                        break;
                    }
                    key = Math.min(key, pre[ii][j]);
                    len++;
                    ans = Math.max(key * len, ans);
                }
            }
        }
        return ans;
    }

    /**
     * LCP 08. 剧情触发时间
     * <p>
     * 在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。一个势力的主要属性有三种，分别是文明等级（C），资源储备（R）以及人口数量（H）。
     * 在游戏开始时（第 0 天），三种属性的值均为 0。
     * 随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。
     * 这个二维数组的每个元素是一个长度为 3 的一维数组，例如 [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。
     * 所有剧情的触发条件也用一个二维数组 requirements 表示。这个二维数组的每个元素是一个长度为 3 的一维数组，
     * 对于某个剧情的触发条件 c[i], r[i], h[i]，如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，则剧情会被触发。
     * 根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。
     * <p>
     * 执行用时：317 ms, 在所有 Java 提交中击败了16.06% 的用户
     * 内存消耗：90.4 MB, 在所有 Java 提交中击败了69.34% 的用户
     *
     * @param increase     势力的主要属性
     * @param requirements 剧情
     * @return 每个剧情的触发时间
     */
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int n = requirements.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };

        PriorityQueue<int[]> cQueue = new PriorityQueue<>(comparator);
        PriorityQueue<int[]> rQueue = new PriorityQueue<>(comparator);
        PriorityQueue<int[]> hQueue = new PriorityQueue<>(comparator);

        int[] success = new int[n];

        for (int j = 0; j < n; j++) {
            if (requirements[j][0] == 0) {
                success[j]++;
            } else {
                cQueue.add(new int[]{requirements[j][0], j});
            }
            if (requirements[j][1] == 0) {
                success[j]++;
            } else {
                rQueue.add(new int[]{requirements[j][1], j});
            }
            if (requirements[j][2] == 0) {
                success[j]++;
            } else {
                hQueue.add(new int[]{requirements[j][2], j});
            }
            if (success[j] >= 3) {
                ans[j] = 0;
            }
        }
        int c = 0, r = 0, h = 0;
        for (int i = 0; i < increase.length; i++) {
            c += increase[i][0];
            r += increase[i][1];
            h += increase[i][2];
            while (!cQueue.isEmpty() && cQueue.peek()[0] <= c) {
                int t = cQueue.poll()[1];
                success[t]++;
                if (success[t] >= 3) {
                    ans[t] = i + 1;
                }
            }
            while (!rQueue.isEmpty() && rQueue.peek()[0] <= r) {
                int t = rQueue.poll()[1];
                success[t]++;
                if (success[t] >= 3) {
                    ans[t] = i + 1;
                }
            }
            while (!hQueue.isEmpty() && hQueue.peek()[0] <= h) {
                int t = hQueue.poll()[1];
                success[t]++;
                if (success[t] >= 3) {
                    ans[t] = i + 1;
                }
            }
        }
        return ans;
    }

    /**
     * LCP 18. 早餐组合
     * <p>
     * 小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks 中记录了每种饮料的价格。
     * 小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。
     * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
     * <p>
     * 执行用时：83 ms, 在所有 Java 提交中击败了80.73% 的用户
     * 内存消耗：58.1 MB, 在所有 Java 提交中击败了66.41% 的用户
     *
     * @param staple 整型数组
     * @param drinks 整型数组
     * @param x      整数
     * @return 早餐组合
     */
    public int breakfastNumber0(int[] staple, int[] drinks, int x) {
        Arrays.sort(drinks);
        Arrays.sort(staple);
        int ans = 0;
        int dr = drinks.length - 1;
        for (int value : staple) {
            while (dr >= 0 && drinks[dr] + value > x) {
                dr--;
            }
            if (dr < 0) {
                break;
            }
            ans = (ans + dr + 1) % 1000000007;
        }
        return ans;
    }

    /**
     * LCP 18. 早餐组合
     * <p>
     * 小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks 中记录了每种饮料的价格。
     * 小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。
     * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
     * <p>
     * 执行用时：64 ms, 在所有 Java 提交中击败了86.56% 的用户
     * 内存消耗：57.6 MB, 在所有 Java 提交中击败了84.88% 的用户
     * <p>
     * 树状数组解法
     *
     * @param staple 整型数组
     * @param drinks 整型数组
     * @param x      整数
     * @return 早餐组合
     */
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        BinaryIndexedTree bit = new BinaryIndexedTree(100005);
        for (int d : drinks) {
            bit.update(d, 1);
        }
        int ans = 0;
        for (int s : staple) {
            if (x - s >= 100005) {
                ans = (ans + drinks.length) % 1000000007;
            } else {
                ans = (ans + bit.prefixSum(x - s)) % 1000000007;
            }
        }
        return ans;
    }

    /**
     * 205. 同构字符串
     *
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
     * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，
     * 但字符可以映射自己本身。
     *
     * 执行用时：7 ms, 在所有 Java 提交中击败了79.71% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了48.13% 的用户
     * @param t 字符串
     * @param s 字符串
     * @return 是否是同构字符串
     */
    public boolean isIsomorphic(String t, String s) {
        char[] kv = new char[256];
        char[] kv2 = new char[256];
        for (int i = 0; i < s.length(); i++) {
            char c = kv[s.charAt(i)];
            if (c != '\u0000') {
                if (c != t.charAt(i)) {
                    return false;
                }
            } else {
                kv[s.charAt(i)] = t.charAt(i);
            }
            char c2 = kv2[t.charAt(i)];
            if (c2 != '\u0000') {
                if (c2 != s.charAt(i)) {
                    return false;
                }
            } else {
                kv2[t.charAt(i)] = s.charAt(i);
            }
        }
        return true;
    }
}
