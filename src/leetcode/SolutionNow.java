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
     *
     * 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，
     * 且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.84% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了46.22% 的用户
     * @param nums 数组
     * @param k 关键整数
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
}
