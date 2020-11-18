package leetcode;

import leetcode.datestruct.ListNode;
import leetcode.datestruct.TreeNode;

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
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了65.84% 的用户
     *
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

    /**
     * 126. 单词接龙 II
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
     * 每次转换只能改变一个字母。
     * 转换后得到的单词必须是字典中的单词。
     * 说明:
     * 如果不存在这样的转换序列，返回一个空列表。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     *
     * @param beginWord 起始单词
     * @param endWord   结束单词
     * @param wordList  词典
     * @return 单词接龙
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return new ArrayList<>();
        }
        wordList.add(0, beginWord);
        int len = wordList.size();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                String wordI = wordList.get(i);
                String wordJ = wordList.get(j);
                if (findLaddersAss(wordI, wordJ)) {
                    Set<String> listI = findLaddersMap.getOrDefault(wordI, new HashSet<>());
                    listI.add(wordJ);
                    findLaddersMap.put(wordI, listI);
                    Set<String> listJ = findLaddersMap.getOrDefault(wordJ, new HashSet<>());
                    listJ.add(wordI);
                    findLaddersMap.put(wordJ, listJ);
                }
            }
        }
        if (!findLaddersMap.containsKey(endWord)) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        findLaddersDFS(beginWord, set, endWord, list);
        return findLaddersRes;
    }

    private List<List<String>> findLaddersRes = new ArrayList<>();

    private int findLaddersResItemLen = Integer.MAX_VALUE;

    private Map<String, Set<String>> findLaddersMap = new HashMap<>();

    private boolean findLaddersAss(String wordA, String wordB) {
        int lenA = wordA.length();
        int lenB = wordB.length();
        if (lenA != lenB) {
            return false;
        }
        char[] charsA = wordA.toCharArray();
        char[] charsB = wordB.toCharArray();
        int time = 0;
        for (int i = 0; i < lenA; i++) {
            if (charsA[i] != charsB[i]) {
                time++;
                if (time >= 2) {
                    return false;
                }
            }
        }
        return time == 1;
    }

    private void findLaddersDFS(String key, Set<String> visit, String endWord, List<String> list) {
        for (String str : findLaddersMap.get(key)) {
            if (str.equals(endWord)) {
                int listLen = list.size() + 1;
                if (findLaddersRes.isEmpty()) {
                    List<String> listCopy = new ArrayList<>(List.copyOf(list));
                    listCopy.add(endWord);
                    findLaddersRes.add(listCopy);
                    findLaddersResItemLen = listLen;
                } else {
                    if (findLaddersResItemLen > listLen) {
                        findLaddersRes = new ArrayList<>();
                        findLaddersResItemLen = listLen;
                    } else if (findLaddersResItemLen < listLen) {
                        break;
                    }
                    List<String> listCopy = new ArrayList<>(List.copyOf(list));
                    listCopy.add(endWord);
                    findLaddersRes.add(listCopy);
                }
                break;
            }
            if (visit.contains(str)) {
                continue;
            }
            int listLen = list.size();
            if (findLaddersResItemLen <= listLen) {
                continue;
            }
            list.add(str);
            visit.add(str);
            findLaddersDFS(str, visit, endWord, list);
            visit.remove(str);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 126. 单词接龙 II
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
     * 每次转换只能改变一个字母。
     * 转换后得到的单词必须是字典中的单词。
     * 说明:
     * 如果不存在这样的转换序列，返回一个空列表。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * <p>
     * 执行用时：21 ms, 在所有 Java 提交中击败了94.79% 的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了85.11% 的用户
     *
     * @param beginWord 起始单词
     * @param endWord   结束单词
     * @param wordList  词典
     * @return 单词接龙
     */
    public List<List<String>> findLadders0(String beginWord, String endWord, List<String> wordList) {
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return res;
        }
        // 第 1 步：使用双向广度优先遍历得到后继结点列表 successors
        // key：字符串，value：广度优先遍历过程中 key 的后继结点列表
        Map<String, Set<String>> successors = new HashMap<>();
        boolean found = bidirectionalBfs(beginWord, endWord, wordSet, successors);
        if (!found) {
            return res;
        }
        // 第 2 步：基于后继结点列表 successors ，使用回溯算法得到所有最短路径列表
        Deque<String> path = new ArrayDeque<>();
        path.addLast(beginWord);
        dfs(beginWord, endWord, successors, path, res);
        return res;
    }

    private boolean bidirectionalBfs(String beginWord,
                                     String endWord,
                                     Set<String> wordSet,
                                     Map<String, Set<String>> successors) {
        // 记录访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int wordLen = beginWord.length();
        boolean forward = true;
        boolean found = false;
        // 在保证了 beginVisited 总是较小（可以等于）大小的集合前提下，&& !endVisited.isEmpty() 可以省略
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 一直保证 beginVisited 是相对较小的集合，方便后续编码
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;

                // 只要交换，就更改方向，以便维护 successors 的定义
                forward = !forward;
            }
            Set<String> nextLevelVisited = new HashSet<>();
            // 默认 beginVisited 是小集合，因此从 beginVisited 出发
            for (String currentWord : beginVisited) {
                char[] charArray = currentWord.toCharArray();
                for (int i = 0; i < wordLen; i++) {
                    char originChar = charArray[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (charArray[i] == j) {
                            continue;
                        }
                        charArray[i] = j;
                        String nextWord = new String(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (endVisited.contains(nextWord)) {
                                found = true;
                                // 在另一侧找到单词以后，还需把这一层关系添加到「后继结点列表」
                                addToSuccessors(successors, forward, currentWord, nextWord);
                            }

                            if (!visited.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                addToSuccessors(successors, forward, currentWord, nextWord);
                            }
                        }
                    }
                    charArray[i] = originChar;
                }
            }
            beginVisited = nextLevelVisited;
            visited.addAll(nextLevelVisited);
            if (found) {
                break;
            }
        }
        return found;
    }

    private void dfs(String beginWord,
                     String endWord,
                     Map<String, Set<String>> successors,
                     Deque<String> path,
                     List<List<String>> res) {

        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (!successors.containsKey(beginWord)) {
            return;
        }

        Set<String> successorWords = successors.get(beginWord);
        for (String successor : successorWords) {
            path.addLast(successor);
            dfs(successor, endWord, successors, path, res);
            path.removeLast();
        }
    }

    private void addToSuccessors(Map<String, Set<String>> successors, boolean forward,
                                 String currentWord, String nextWord) {
        if (!forward) {
            String temp = currentWord;
            currentWord = nextWord;
            nextWord = temp;
        }

        // Java 1.8 以后支持
        successors.computeIfAbsent(currentWord, a -> new HashSet<>());
        successors.get(currentWord).add(nextWord);
    }


    /**
     * 523. 连续的子数组和
     * <p>
     * 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，
     * 且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.84% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了46.22% 的用户
     *
     * @param nums 数组
     * @param k    关键整数
     * @return 是否满足条件
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        if (k == 0) {
            int time = 0;
            for (int i = 0; i < len; i++) {
                if (nums[i] == 0) {
                    time++;
                } else {
                    time = 0;
                }
                if (time >= 2) {
                    return true;
                }
            }
        } else {
            Map<Integer, Integer> map = new HashMap<>();
//            int[] arr = new int[Math.abs(k)];
//            Arrays.fill(arr, -1);
            int now = 0;
            for (int i = 0; i < len; i++) {
                now = (now + nums[i]) % k;
                if (now == 0 && i >= 1) {
                    return true;
                } else if (!map.containsKey(now)) {
                    map.put(now, i);
                } else if (i - map.get(now) >= 2) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 24. 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了63.84% 的用户
     *
     * @param head 链表
     * @return 两两交换之后的链表
     */
    public ListNode swapPairs(ListNode head) {
        if (head != null && head.next != null) {
            ListNode fir = head;
            ListNode sec = head.next;
            fir.next = sec.next;
            sec.next = fir;
            fir.next = swapPairs(fir.next);
            head = sec;
        }
        return head;
    }

    /**
     * 1002. 查找常用字符
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 你可以按任意顺序返回答案。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.36% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了100.00% 的用户
     *
     * @param arr 数组
     * @return 查找常用字符
     */
    public List<String> commonChars(String[] arr) {
        int[] chars = new int[26];
        for (char c : arr[0].toCharArray()) {
            chars[c - 'a']++;
        }
        for (int i = 1; i < arr.length; i++) {
            int[] tmp = new int[26];
            for (char c : arr[i].toCharArray()) {
                tmp[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                chars[j] = Math.min(chars[j], tmp[j]);
            }
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            for (int j = 0; j < chars[i]; j++) {
                list.add(String.valueOf(c));
            }
        }
        return list;
    }

    /**
     * 1003. 检查替换后的词是否有效
     * 给定有效字符串 "abc"。
     * 对于任何有效的字符串 V，我们可以将 V 分成两个部分 X 和 Y，使得 X + Y（X 与 Y 连接）等于 V。（X 或 Y 可以为空。）那么，X + "abc" + Y 也同样是有效的。
     * 例如，如果 S = "abc"，则有效字符串的示例是："abc"，"aabcbc"，"abcabc"，"abcabcababcc"。无效字符串的示例是："abccba"，"ab"，"cababc"，"bac"。
     * 如果给定字符串 S 有效，则返回 true；否则，返回 false。
     * <p>
     * 执行用时：23 ms, 在所有 Java 提交中击败了28.02% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了95.72% 的用户
     *
     * @param s 字符串
     * @return 字符串是否有效
     */
    public boolean isValid(String s) {
        if ("".equals(s)) {
            return true;
        }
        if (s.length() % 3 != 0) {
            return false;
        }
        int len = s.length();
        s = s.replaceAll("abc", "");
        int newlen = s.length();
        if (len == newlen) {
            return false;
        }
        return isValid(s);
    }

    public int longestOnes(int[] A, int K) {
        int len = A.length;
        List<int[]> list = new ArrayList<>();
        int now = 0;
        int isOne = A[0];
        int one = 0;
        for (int i = 0; i < len; i++) {
            if ((A[i] == 0 && isOne == 0) || (A[i] == 1 && isOne == 1)) {
                now++;
            } else {
                list.add(new int[]{isOne, now});
                now = 1;
                isOne = A[i];
            }
            if (A[i] == 1) {
                one++;
            }
        }
        if (K + one >= len) {
            return len;
        }
        int[] dp = new int[list.size()];
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] arr = list.get(i);
            if (arr[0] == 1) {
                int tmp = K;
                dp[i] = arr[1];
                for (int j = i - 1; j >= 0; j = j - 2) {
                    if (list.get(j)[1] <= tmp) {
                        dp[i] += list.get(j)[1];
                        if (j >= 0) {
                            dp[i] += list.get(j - 1)[1];
                        }
                        tmp -= list.get(j)[1];
                    }
                }
                dp[i] += tmp;
                max = Math.max(dp[i], max);
            }
        }
        return Math.min(max, K);
    }

//    /**
//     * 116. 填充每个节点的下一个右侧节点指针
//     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
//     * struct Node {
//     * int val;
//     * Node *left;
//     * Node *right;
//     * Node *next;
//     * }
//     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
//     * 初始状态下，所有 next 指针都被设置为 NULL。
//     * <p>
//     * 执行用时：3 ms, 在所有 Java 提交中击败了45.62% 的用户
//     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了90.61% 的用户
//     *
//     * @param root 完美二叉树
//     * @return 完美二叉树
//     */
//    public Node connect(Node root) {
//        if (root != null) {
//            Queue<Node> queue = new LinkedList<>();
//            queue.add(root);
//            while (!queue.isEmpty()) {
//                int len = queue.size();
//                Node tmp = queue.poll();
//                while (len > 0) {
//                    if (tmp.left != null) {
//                        queue.add(tmp.left);
//                    }
//                    if (tmp.right != null) {
//                        queue.add(tmp.right);
//                    }
//                    if (len > 1) {
//                        tmp.next = queue.peek();
//                        tmp = queue.poll();
//                    } else {
//                        tmp.next = null;
//                    }
//                    len--;
//                }
//            }
//        }
//        return root;
//    }

    /**
     * 面试题 17.04. 消失的数字
     * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了14.63% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了88.02% 的用户
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }


    /**
     * 401. 二进制手表
     * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
     * 每个 LED 代表一个 0 或 1，最低位在右侧。
     * <p>
     * 执行用时：20 ms, 在所有 Java 提交中击败了10.32% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了46.58% 的用户
     *
     * @param num 数组
     * @return 时间
     */
    public List<String> readBinaryWatch(int num) {
        readBinaryWatch(0, num, new boolean[10]);
        return readBinaryWatchRes;
    }

    private List<String> readBinaryWatchRes = new ArrayList();
    private Set<String> readBinaryWatchSet = new HashSet<>();


    private void readBinaryWatch(int left, int now, boolean[] brr) {
        if (now == 0) {
            //
            int hour = 0;
            for (int i = 0; i < 4; i++) {
                int tmp = brr[i] ? 1 : 0;
                hour = hour * 2 + tmp;
            }
            if (hour >= 12) {
                return;
            }
            int minute = 0;
            for (int i = 4; i < 10; i++) {
                int tmp = brr[i] ? 1 : 0;
                minute = minute * 2 + tmp;
            }
            String str = hour + ":";
            if (minute >= 60) {
                return;
            } else if (minute < 10) {
                str += "0";
            }
            str += minute;
            if (readBinaryWatchSet.add(str)) {
                readBinaryWatchRes.add(str);
            }
            return;
        }
        for (
                int i = left;
                i < 10; i++) {
            brr[i] = true;
            readBinaryWatch(i + 1, now - 1, brr);
            brr[i] = false;
            readBinaryWatch(i + 1, now, brr);
        }
    }

    /**
     * 977. 有序数组的平方
     * <p>
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     * <p>
     * 执行用时：30 ms, 在所有 Java 提交中击败了6.70% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了5.03% 的用户
     *
     * @param A 数组
     * @return 结果
     */
    public int[] sortedSquares(int[] A) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int a : A) {
            priorityQueue.add(a * a);
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = priorityQueue.poll();
        }
        return A;
    }


    /**
     * 52. N皇后 II
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了81.33% 的用户
     * 内存消耗：34.9 MB, 在所有 Java 提交中击败了99.87% 的用户
     *
     * @param n n个皇后
     * @return 几种摆法
     */
    public int totalNQueens(int n) {
        totalNQueensAss(new int[n][n], 0);
        return totalNQueensRes;
    }

    private int totalNQueensRes = 0;

    private void totalNQueensAss(int[][] map, int row) {
        if (row >= map.length) {
            totalNQueensRes++;
        } else {
            for (int i = 0; i < map.length; i++) {
                if (map[row][i] == 0) {
                    for (int j = row + 1; j < map.length; j++) {
                        map[j][i]++;
                        if (i - (j - row) >= 0) {
                            map[j][i - j + row]++;
                        }
                        if (i + (j - row) < map.length) {
                            map[j][i + j - row]++;
                        }
                    }
                    totalNQueensAss(map, row + 1);
                    for (int j = row + 1; j < map.length; j++) {
                        map[j][i]--;
                        if (i - (j - row) >= 0) {
                            map[j][i - j + row]--;
                        }
                        if (i + (j - row) < map.length) {
                            map[j][i + j - row]--;
                        }
                    }
                }
            }
        }
    }

    /**
     * 300. 最长上升子序列
     * <p>
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * <p>
     * 执行用时：19 ms, 在所有 Java 提交中击败了11.99% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了96.41% 的用户
     *
     * @param nums 数组
     * @return 最长子数组长度
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int len = nums.length;
        int[] dp = new int[len];
        int ans = -1;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans + 1;
    }

    public int numberOfSets(int n, int k) {
        // length: 0 - x, k:
        // 0: 0,1 + (1-x, k-1)
        // 1: 0,2 + (1-x, k-1)
        // ...
        int[][] dp = new int[n + 1][k + 1];
        for (int j = 1; j <= k; j++) {
            for (int i = 0; i <= n; i++) {
                if (i < j || i == 0) {
                    dp[i][j] = 0;
                } else if (i == j || j == 1) {
                    dp[i][j] = 1;
                } else {
                    for (int x = 0; x < i; x++) {
                        dp[i][j] = (dp[i][j] + dp[i - x][j - 1]) % 1000000007;
                    }
                }
            }
        }
        return dp[n - 1][k];
    }


    /**
     * 执行用时：514 ms, 在所有 Java 提交中击败了5.21% 的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了24.02% 的用户
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode lastPre = head;
        while (lastPre.next != null && lastPre.next.next != null) {
            lastPre = lastPre.next;
        }
        if (lastPre.equals(head)) {
            return;
        }
        ListNode headBehind = head.next;
        head.next = lastPre.next;
        lastPre.next.next = headBehind;
        lastPre.next = null;
        reorderList(headBehind);
    }

    /**
     * 925. 长按键入
     * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了98.29% 的用户
     *
     * @param name  他的名字
     * @param typed 键盘输入的名字
     * @return 是否是长按键入
     */
    public boolean isLongPressedName(String name, String typed) {
        if (name.equals(typed)) {
            return true;
        }
        int n = name.length();
        int m = typed.length();
        if (n > m || n == 0) {
            return false;
        }
        int t = 0;
        char[] names = name.toCharArray();
        char[] typeds = typed.toCharArray();
        int i = 0;
        char pre = '~';
        while (i < n) {
            if (names[i] != typeds[i + t]) {
                if (pre != typeds[i + t]) {
                    return false;
                }
                t++;
                if (t + n > m) {
                    return false;
                }
            } else {
                pre = names[i];
                i++;
            }
        }
        while (i + t < m) {
            if (typeds[i + t] != names[n - 1]) {
                return false;
            }
            t++;
        }
        return true;
    }

    /**
     * 322. 零钱兑换
     * <p>
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * 执行用时：16 ms, 在所有 Java 提交中击败了52.62% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了90.18% 的用户
     *
     * @param coins  不同面额的硬币
     * @param amount 总金额
     * @return 凑成总金额所需的最少的硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return amount;
        }
        int[] dp = new int[10001];
        for (int coin : coins) {
            if (coin < 10001) {
                dp[coin]++;
            }
        }
        for (int i = 1; i <= amount; i++) {
            int tmp = 10002;
            for (int coin : coins) {
                if (i > coin && dp[i - coin] != 0 && dp[i - coin] != -1) {
                    tmp = Math.min(tmp, dp[i - coin] + 1);
                }
            }
            if (dp[i] == 0) {
                dp[i] = tmp == 10002 ? -1 : tmp;
            } else {
                dp[i] = Math.min(dp[i], tmp);
            }
        }
        return dp[amount];
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;

        int[] dp = new int[k];

        Map<String, int[]> map = new HashMap<>();
        for (int t = 1; t < n && t < k; t++) {
            int[] value = new int[t];
            int pre = 0;
            int tmp = t;
            while (tmp > 0) {
                pre = findMaxValue(nums1, pre, n + 1 - t);
                value[t - tmp] = nums1[pre];
                tmp--;
            }
            map.put("1-" + t, value);
        }

        for (int t = 1; t < m && t < k; t++) {
            int[] value = new int[t];
            int pre = 0;
            int tmp = t;
            while (tmp > 0) {
                pre = findMaxValue(nums2, pre, m + 1 - t);
                value[t - tmp] = nums2[pre];
                tmp--;
            }
            map.put("2-" + t, value);
        }

        int max = 0;
        for (int i = 0; i < k; i++) {
            if (i < n && k - i < m) {
                int[] arr = map.get("1-" + i);
                int[] brr = map.get("2-" + i);
                max = Math.max(max, findMaxNumber(arr, brr));
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[k - i] = max % 10;
            max = max / 10;
        }

        return res;
    }

    private int findMaxValue(int[] arr, int l, int r) {
        int key = 0, value = 0;
        for (int i = l; i < r; i++) {
            if (arr[i] == 9) {
                return i;
            } else if (arr[i] > key) {
                value = i;
            }
        }
        return value;
    }

    private int findMaxNumber(int[] arr, int[] brr) {
        int tmp = 0;
        int[][] dp = new int[arr.length + 1][brr.length + 1];
        // 选i-1个arr
        for (int i = 1; i <= arr.length; i++) {
            // 选j-1个brr
            for (int j = 1; j <= brr.length; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j] * 10 + arr[i];
                } else if (i == 1) {
                    dp[i][j] = dp[i][j - 1] * 10 + brr[j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] * 10 + arr[i], dp[i][j - 1] * 10 + brr[j]);
                }
            }
        }
        return dp[arr.length][brr.length];
    }

    public List<Integer> partitionLabels(String S) {
        Map<Character, int[]> map = new HashMap<>();
        char[] chars = S.toCharArray();
        int n = S.length();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(chars[i])) {
                map.get(chars[i])[1] = i;
            } else {
                map.put(chars[i], new int[]{i, i});
            }
        }
        List<Integer> list = new ArrayList<>();
        int t = 0;
        int pre = 0;
        while (t < n) {
            int max = map.get(chars[t])[1];
            while (t <= max) {
                max = Math.max(map.get(chars[t])[1], max);
                t++;
            }
            list.add(t - pre);
            pre = t;
        }
//        int[] array = list.stream().mapToInt(i->i).toArray();
        return list;
    }

    /**
     * 234. 回文链表
     * 请判断一个链表是否为回文链表。
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了28.12% 的用户
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了44.91% 的用户
     *
     * @param head 链表
     * @return 是否是回文链表
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head.next;
        Stack<ListNode> stack = new Stack<>();
        Queue<ListNode> queue = new LinkedList<>();
        stack.add(head);
        queue.add(fast);
        while (fast.next != null) {
            fast = fast.next;
            ListNode tmp = queue.poll();
            queue.add(fast);
            if (fast.next != null) {
                fast = fast.next;
                stack.add(tmp);
                queue.add(fast);
            }
        }
        while (!stack.empty()) {
            if (stack.pop().val != queue.poll().val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1578. 避免重复字母的最小删除成本
     * 给你一个字符串 s 和一个整数数组 cost ，其中 cost[i] 是从 s 中删除字符 i 的代价。
     * 返回使字符串任意相邻两个字母不相同的最小删除成本。
     * 请注意，删除一个字符后，删除其他字符的成本不会改变。
     * <p>
     * 执行用时：7 ms, 在所有 Java 提交中击败了87.56% 的用户
     * 内存消耗：47.1 MB, 在所有 Java 提交中击败了99.16% 的用户
     *
     * @param s    字符串
     * @param cost 整数数组
     * @return 避免重复字母的最小删除成本
     */
    public int minCost(String s, int[] cost) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int sumCost = 0;
        for (int i = 1; i < n; i++) {
            if (chars[i] == chars[i - 1]) {
                sumCost += Math.min(cost[i], cost[i - 1]);
                cost[i] = Math.max(cost[i], cost[i - 1]);
            }
        }
        return sumCost;
    }

    /**
     * 1577. 数的平方等于两数乘积的方法数
     * 给你两个整数数组 nums1 和 nums2 ，请你返回根据以下规则形成的三元组的数目（类型 1 和类型 2 ）：*
     * 类型 1：三元组 (i, j, k) ，如果 nums1[i]2 == nums2[j] * nums2[k] 其中 0 <= i < nums1.length 且 0 <= j < k < nums2.length
     * 类型 2：三元组 (i, j, k) ，如果 nums2[i]2 == nums1[j] * nums1[k] 其中 0 <= i < nums2.length 且 0 <= j < k < nums1.length
     * <p>
     * 执行用时：60 ms, 在所有 Java 提交中击败了59.97% 的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了99.83% 的用户
     *
     * @param nums1 整数数组
     * @param nums2 整数数组
     * @return 数的平方等于两数乘积的方法数
     */
    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int n : nums1) {
            map1.put(n, map1.getOrDefault(n, 0) + 1);
        }

        Map<Integer, Integer> map2 = new HashMap<>();
        for (int n : nums2) {
            map2.put(n, map2.getOrDefault(n, 0) + 1);
        }
        int res = 0;

        int n = nums1.length;
        int m = nums2.length;
        for (int i = 0; i < n; i += 1) {
            for (int j = i + 1; j < n; j += 1) {
                double tmp = Math.sqrt((long) nums1[i] * (long) nums1[j]);
                if (tmp % 1 == 0) {
                    res += map2.getOrDefault((int) tmp, 0);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                double tmp = Math.sqrt((long) nums2[i] * (long) nums2[j]);
                if (tmp % 1 == 0) {
                    res += map1.getOrDefault((int) tmp, 0);
                }
            }
        }

        return res;
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[friends.length];
        queue.offer(id);
        visited[id] = 1;
        List<String> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            if (level == 0) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int tmp = queue.poll();
                    ans.addAll(watchedVideos.get(tmp));
                }
                break;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int tmp = queue.poll();
                for (int friend : friends[tmp]) {
                    if (visited[friend] == 0) {
                        queue.offer(friend);
                        visited[friend] = 1;
                    }

                }
            }
            level--;
        }
        List<String> res = new ArrayList<>();
        ans.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByKey()).sorted(Map.Entry.comparingByValue()).forEachOrdered(e -> res.add(e.getKey()));
        return res;

    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 统计出现频率 frequency
        int[] freq = new int[101]; // 索引即数值
        for (int num : nums) {
            freq[num]++;
        }

        // 对频率(而非对原数组nums)从前到后累加
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i] + freq[i - 1];
        }

        // 输出结果
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            if (nums[i] > 0) {
                res[i] = freq[nums[i] - 1];
            }
        }
        return res;

    }

    /**
     * 144. 二叉树的前序遍历
     * <p>
     * 给定一个二叉树，返回它的 前序 遍历。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了97.74% 的用户
     *
     * @param root 二叉树
     * @return 前序 遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    private void preorderTraversal(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        list.add(treeNode.val);
        if (treeNode.left != null) {
            preorderTraversal(treeNode.left, list);
        }
        if (treeNode.right != null) {
            preorderTraversal(treeNode.right, list);
        }
    }

    /**
     * 1207. 独一无二的出现次数
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.43% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了98.25% 的用户
     *
     * @param arr 数组
     * @return 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            int tmp = map.getOrDefault(a, 0);
            map.put(a, tmp + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!set.add(entry.getValue())) {
                return false;
            }
        }
//        map.entrySet().parallelStream().forEach((entry) -> {
//            if (!set.add(entry.getValue())) {
//                return;
//            }
//        });
        return true;
    }


    /**
     * 799. 香槟塔
     * 我们把玻璃杯摆成金字塔的形状，其中第一层有1个玻璃杯，第二层有2个，依次类推到第100层，每个玻璃杯(250ml)将盛有香槟。
     * 从顶层的第一个玻璃杯开始倾倒一些香槟，当顶层的杯子满了，任何溢出的香槟都会立刻等流量的流向左右两侧的玻璃杯。
     * 当左右两边的杯子也满了，就会等流量的流向它们左右两边的杯子，依次类推。（当最底层的玻璃杯满了，香槟会流到地板上）
     * 例如，在倾倒一杯香槟后，最顶层的玻璃杯满了。倾倒了两杯香槟后，第二层的两个玻璃杯各自盛放一半的香槟。在倒三杯香槟后，
     * 第二层的香槟满了 - 此时总共有三个满的玻璃杯。在倒第四杯后，第三层中间的玻璃杯盛放了一半的香槟，他两边的玻璃杯各自盛放了四分之一的香槟，
     * 现在当倾倒了非负整数杯香槟后，返回第 i 行 j 个玻璃杯所盛放的香槟占玻璃杯容积的比例（i 和 j都从0开始）。
     * 执行用时：10 ms, 在所有 Java 提交中击败了10.00% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了5.97% 的用户
     *
     * @param poured      倾倒一些香槟
     * @param query_row   第 i 行
     * @param query_glass 第 j 个
     * @return 第 i 行 j 个玻璃杯所盛放的香槟占玻璃杯容积的比例（i 和 j都从0开始）
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        List<Double> list = new ArrayList<>();
        list.add((double) poured);
        for (int i = 0; i < query_row; i++) {
            List<Double> newList = new ArrayList<>();
            double pre = 0;
            for (int j = 0; j < list.size(); j++) {
                double tmp = (list.get(j) - 1.0) / 2;
                if (tmp < 0.0) {
                    tmp = 0.0;
                }
                newList.add(tmp + pre);
                pre = tmp;
            }
            newList.add(pre);
            list = newList;
        }
        return list.get(query_glass) > 1.0 ? 1.0 : list.get(query_glass);
    }

    /**
     * 1306. 跳跃游戏 III
     * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
     * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
     * 注意，不管是什么情况下，你都无法跳到数组之外。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：46 MB, 在所有 Java 提交中击败了85.38% 的用户
     *
     * @param arr   非负整数数组 arr
     * @param start 起始下标
     * @return 是否能够跳到对应元素值为 0 的 任一 下标处
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        canReach(arr, start, visited);
        return canReachRes3;
    }

    private boolean canReachRes3 = false;

    private void canReach(int[] arr, int now, boolean[] visited) {
        if (visited[now]) {
            return;
        }
        visited[now] = true;
        if (arr[now] == 0) {
            canReachRes3 = true;
            return;
        }
        if (now + arr[now] < arr.length) {
            canReach(arr, now + arr[now], visited);
        }
        if (now >= arr[now]) {
            canReach(arr, now - arr[now], visited);
        }
    }

    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> tmp = map.getOrDefault(arr[i], new ArrayList<>());
            tmp.add(i);
            map.put(arr[i], tmp);
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int move = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int tmp = queue.poll();
                if (tmp == n - 1) {
                    return move;
                }
                for (int j : map.get(arr[tmp])) {
                    if (!visited[j]) {
                        visited[j] = true;
                        queue.add(j);
                    }
                }
                if (tmp + 1 < n && !visited[tmp + 1]) {
                    visited[tmp + 1] = true;
                    queue.add(tmp + 1);
                }
                if (tmp - 1 > 0 && !visited[tmp - 1]) {
                    visited[tmp - 1] = true;
                    queue.add(tmp - 1);
                }
            }
            move++;
        }
        return 0;
    }

    /**
     * 463. 岛屿的周长
     * <p>
     * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
     * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
     * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     * <p>
     * 执行用时：9 ms, 在所有 Java 提交中击败了56.99% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了63.42% 的用户
     *
     * @param grid 网格地图
     * @return 岛屿周长
     */
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        if (n == 0) {
            return 0;
        }
        int m = grid[0].length;
        int ans = 0;
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int[] d : dir) {
                        int newI = d[0] + i;
                        int newJ = d[1] + j;
                        if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) {
                            ans++;
                        } else if (grid[newI][newJ] == 0) {
                            ans++;
                        }

                    }
                }
            }
        }
        return ans;
    }

    /**
     * 908. 最小差值 I
     * 给你一个整数数组 A，请你给数组中的每个元素 A[i] 都加上一个任意数字 x （-K <= x <= K），从而得到一个新数组 B 。
     * 返回数组 B 的最大值和最小值之间可能存在的最小差值。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了68.41% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了82.78% 的用户
     *
     * @param A 整数数组
     * @param K 任意数字
     * @return 最小差值 I
     */
    public int smallestRangeI(int[] A, int K) {
        int max = A[0], min = A[0];
        for (int i : A) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int res = max - min - 2 * A.length;
        return Math.max(res, 0);
    }

    /**
     * 349. 两个数组的交集
     * 给定两个数组，编写一个函数来计算它们的交集。
     * 执行用时：11 ms, 在所有 Java 提交中击败了7.55% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了43.60% 的用户
     *
     * @param nums1 数组
     * @param nums2 数组
     * @return 数组的交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);

        Set<Integer> set = new TreeSet<>();
        for (int i : nums2) {
            set.add(i);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (i > 0 && nums1[i - 1] == nums1[i]) {
                continue;
            }
            if (set.contains(nums1[i])) {
                list.add(nums1[i]);
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }


    /**
     * 1301. 最大得分的路径数目
     * 给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。
     * 你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字符 1, 2, ..., 9 或者障碍 'X'。在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。
     * 一条路径的 「得分」 定义为：路径上所有数字的和。
     * 请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对 10^9 + 7 取余。
     * 如果没有任何路径可以到达终点，请返回 [0, 0] 。
     * <p>
     * 执行用时：15 ms, 在所有 Java 提交中击败了69.62% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了94.67% 的用户
     *
     * @param board 正方形字符数组
     * @return 最大得分的路径数目
     */
    public int[] pathsWithMaxScore(List<String> board) {
        int row = board.size();
        if (row == 0) {
            return new int[]{0, 0};
        }
        int modNum = 1000000007;
        int col = board.get(0).length();
        int[][] dpScore = new int[row + 1][col + 1];
        int[][] dpPath = new int[row + 1][col + 1];
        //从右下角开始走，初始路径为1条
        dpPath[row - 1][col - 1] = 1;
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                //如果board[i][j] == 'X', 即为障碍
                //如果dpPath[i+1][j],dpPath[i][j+1]和dpPath[i+1][j+1]都等于0，就是路径被障碍'X'封死了
                if (board.get(i).charAt(j) != 'X' &&
                        (dpPath[i + 1][j] != 0 || dpPath[i][j + 1] != 0 || dpPath[i + 1][j + 1] != 0)) {
                    int maxScore = Math.max(Math.max(dpScore[i + 1][j], dpScore[i][j + 1]), dpScore[i + 1][j + 1]);
                    dpScore[i][j] = maxScore + board.get(i).charAt(j) - '0';
                    if (dpScore[i + 1][j] == maxScore) {
                        dpPath[i][j] = (dpPath[i][j] + dpPath[i + 1][j]) % modNum;
                    }
                    if (dpScore[i][j + 1] == maxScore) {
                        dpPath[i][j] = (dpPath[i][j] + dpPath[i][j + 1]) % modNum;
                    }
                    if (dpScore[i + 1][j + 1] == maxScore) {
                        dpPath[i][j] = (dpPath[i][j] + dpPath[i + 1][j + 1]) % modNum;
                    }
                }
            }
        }
        //dpScore[0][0] - ('E' - '0'),是因为结束时候为'E'，上面加了，所以要减去
        int maxScore = dpScore[0][0] == 0 ? 0 : dpScore[0][0] - ('E' - '0');
        return new int[]{maxScore, dpPath[0][0]};
    }

    /**
     * 941. 有效的山脉数组
     * <p>
     * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
     * <p>
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     * <p>
     * A.length >= 3
     * 在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了48.70% 的用户
     *
     * @param A 数组
     * @return 是否是有效的山脉数组
     */
    public boolean validMountainArray(int[] A) {
        int n = A.length;
        if (n < 3) {
            return false;
        }
        int left = 0;
        while (left < n - 1) {
            if (A[left] >= A[left + 1]) {
                break;
            }
            left++;
        }
        int right = n - 1;
        while (right > 0) {
            if (A[right - 1] <= A[right]) {
                break;
            }
            right--;
        }
        return left == right && left != 0 && left != n - 1;
    }


    /**
     * 1455. 检查单词是否为句中其他单词的前缀
     * <p>
     * 给你一个字符串 sentence 作为句子并指定检索词为 searchWord ，其中句子由若干用 单个空格 分隔的单词组成。
     * <p>
     * 请你检查检索词 searchWord 是否为句子 sentence 中任意单词的前缀。
     * <p>
     * 如果 searchWord 是某一个单词的前缀，则返回句子 sentence 中该单词所对应的下标（下标从 1 开始）。
     * 如果 searchWord 是多个单词的前缀，则返回匹配的第一个单词的下标（最小下标）。
     * 如果 searchWord 不是任何单词的前缀，则返回 -1 。
     * <p>
     * 字符串 S 的 「前缀」是 S 的任何前导连续子字符串。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了16.72% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了75.96% 的用户
     *
     * @param sentence   字符串 sentence
     * @param searchWord 检索词 searchWord
     * @return 检查单词是否为句中其他单词的前缀
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] strings = sentence.split(" ");
        int n = searchWord.length();
        for (int i = 0; i < strings.length; i++) {
            String tmp = strings[i];
            if (tmp.length() < n) {
                continue;
            }
            tmp = tmp.substring(0, n);
            if (tmp.equals(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }


    /**
     * 57. 插入区间
     * <p>
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * <p>
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.65% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了60.86% 的用户
     *
     * @param intervals   区间列表
     * @param newInterval 新的区间
     * @return 插入新的区间之后的列表
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        boolean f = false;
        for (int i = 0; i < n; i++) {
            if (f) {
                res.add(intervals[i]);
                continue;
            }
            int[] interval = intervals[i];
            if (newInterval[0] <= interval[1]) {
                if (newInterval[1] < interval[0]) {
                    res.add(newInterval);
                    f = true;
                    while (i < n) {
                        res.add(intervals[i++]);
                    }
                    break;
                } else {
                    int[] newTmp = new int[2];
                    newTmp[0] = Math.min(newInterval[0], interval[0]);
                    newTmp[1] = Math.max(newInterval[1], interval[1]);
                    while (i < n && intervals[i][0] <= newTmp[1]) {
                        newTmp[1] = Math.max(newTmp[1], intervals[i][1]);
                        i++;
                    }
                    res.add(newTmp);
                    if (i < n) {
                        res.add(intervals[i]);
                    }
                    f = true;
                }
            } else {
                res.add(interval);
            }
        }
        if (!f) {
            res.add(newInterval);
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 973. 最接近原点的 K 个点
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     * （这里，平面上两点之间的距离是欧几里德距离。）
     * <p>
     * 执行用时：42 ms, 在所有 Java 提交中击败了24.10% 的用户
     * 内存消耗：47.2 MB, 在所有 Java 提交中击败了53.81% 的用户
     *
     * @param points 平面上的点组成的列表
     * @param K      K 个
     * @return K 个距离原点 (0, 0) 最近的点
     */
    public int[][] kClosest(int[][] points, int K) {
        if (K >= points.length) {
            return points;
        }
        Comparator<int[]> nearlier = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                double a = Math.pow(o1[0], 2) + Math.pow(o1[1], 2);
                double b = Math.pow(o2[0], 2) + Math.pow(o2[1], 2);
                return (int) (a - b);
            }
        };

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(nearlier);
        for (int[] point : points) {
            priorityQueue.add(point);
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }

    /**
     * 31. 下一个排列
     * <p>
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须原地修改，只允许使用额外常数空间。
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.72% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了91.05% 的用户
     *
     * @param nums 数字序列
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        int max = n - 1;
        int t = max - 1;
        while (t >= 0) {
            if (nums[t] < nums[max]) {
                for (int j = t + 1; j < n; j++) {
                    if (nums[j] < nums[max] && nums[j] > nums[t]) {
                        max = j;
                    }
                }
                nums[t] = nums[max] ^ nums[t];
                nums[max] = nums[max] ^ nums[t];
                nums[t] = nums[max] ^ nums[t];
                break;
            }
            max = t;
            t--;
        }
        Arrays.sort(nums, t + 1, n);
    }

    /**
     * 922. 按奇偶排序数组 II
     * <p>
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * 你可以返回任何满足上述条件的数组作为答案。
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了78.68% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了93.56% 的用户
     *
     * @param A 非负整数数组
     * @return 按奇偶排序数组
     */
    public int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        int a = 0, b = 1;
        while (a < n && b < n) {
            while (a < n && A[a] % 2 == 0) {
                a += 2;
            }
            while (b < n && A[b] % 2 == 1) {
                b += 2;
            }
            if (a < n && b < n) {
                A[a] = A[a] ^ A[b];
                A[b] = A[a] ^ A[b];
                A[a] = A[a] ^ A[b];
            }
        }
        return A;
    }

    /**
     * 328. 奇偶链表
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了78.40% 的用户
     *
     * @param head 单链表
     * @return 有的奇数节点和偶数节点分别排在一起之后的单链表
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode one = head;
        if (one == null) {
            return head;
        }
        ListNode o = one;
        ListNode two = head.next;
        ListNode t = two;
        while (one != null && two != null) {
            one.next = two.next;
            if (one.next == null) {
                two.next = null;
                break;
            }
            one = one.next;
            two.next = one.next;
            two = two.next;
        }
        one.next = t;
        return o;
    }

    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     * 注意：
     * 总人数少于1100人。
     * <p>
     * 执行用时：24 ms, 在所有 Java 提交中击败了8.49% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了61.92% 的用户
     *
     * @param people 一群人
     * @return 根据身高重建队列
     */
    public int[][] reconstructQueue(int[][] people) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        };
//        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(comparator);
//
//        for (int[] p : people) {
//            priorityQueue.add(p);
//        }

        Arrays.sort(people, comparator);
        int[][] res = new int[people.length][2];
        boolean[] booleans = new boolean[people.length];
        int pre = 0;
        int preKey = -1;
//        while (!priorityQueue.isEmpty()) {
//            int[] tmp = priorityQueue.poll();
        for (int[] tmp : people) {
            if (preKey < tmp[0]) {
                preKey = tmp[0];
                pre = 0;
            }
            int t = 0;
            int tt = 0;
            while (t < tmp[1] - pre) {
                if (booleans[tt++]) {
                    continue;
                }
                t++;
            }
            while (booleans[tt]) {
                tt++;
            }
            res[tt] = tmp;
            booleans[tt] = true;
            pre++;
        }

        return res;
    }

    /**
     * 1030. 距离顺序排列矩阵单元格
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
     * 其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
     * <p>
     * 执行用时：14 ms, 在所有 Java 提交中击败了73.79% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了18.54% 的用户
     *
     * @param R  R行
     * @param C  C列
     * @param r0 坐标为 (r0, c0) 的单元格
     * @param c0 坐标为 (r0, c0) 的单元格
     * @return 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ret = new int[R * C][];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ret[i * C + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ret, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (Math.abs(a[0] - r0) + Math.abs(a[1] - c0)) - (Math.abs(b[0] - r0) + Math.abs(b[1] - c0));
            }
        });
        return ret;

    }

    /**
     * 34. 加油站
     *
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * 说明:
     *     如果题目有解，该答案即为唯一答案。
     *     输入数组均为非空数组，且长度相同。
     *     输入数组中的元素均为非负数。
     *
     * 执行用时：64 ms, 在所有 Java 提交中击败了23.24% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了93.32% 的用户
     * @param gas 加油站有汽油 gas[i]
     * @param cost 需要消耗汽油 cost[i]
     * @return 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            if (gas[i] - cost[i] < 0) {
                continue;
            }
            int tmp = 0;
            int t = 0;
            boolean right = true;
            while (t < n) {
                tmp += gas[(t + i) % n] - cost[(t + i) % n];
                if (tmp <= 0 && t != n - 1) {
                    right = false;
                    break;
                }
                t++;
            }
            if (right && tmp >= 0) {
                return i;
            }
        }
        return -1;
    }
}
//class Node {
//    public int val;
//    public Node left;
//    public Node right;
//    public Node next;
//
//    public Node() {
//    }
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, Node _left, Node _right, Node _next) {
//        val = _val;
//        left = _left;
//        right = _right;
//        next = _next;
//    }
//};