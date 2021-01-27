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

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        };
        Arrays.sort(boxTypes, comparator);
        int ans = 0;
        int t = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            if (t + boxTypes[i][0] < truckSize) {
                ans += boxTypes[i][0] * boxTypes[i][1];
                t += boxTypes[i][0];
            } else {
                ans += (truckSize - t) * boxTypes[i][1];
                break;
            }
        }
        return ans;
    }

    public int countPairs(int[] deliciousness) {
        int n = deliciousness.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int del : deliciousness) {
            int t = map.getOrDefault(del, 0) + 1;
            map.put(del, t);
        }
        int[] arr = new int[22];
        arr[0] = 1;
        for (int i = 1; i < 22; i++) {
            arr[i] = 2 * arr[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 21; j >= 0; j--) {
                int key = arr[j] - deliciousness[i];
                if (key < 0) {
                    break;
                }
                int value = map.getOrDefault(key, 0);
                if (key == deliciousness[i]) {
                    value--;
                }
                ans += value;
                ans = ans % 1000000007;
            }
            int t = map.get(deliciousness[i]) - 1;
            if (t == 0) {
                map.remove(deliciousness[i]);
            } else {
                map.put(deliciousness[i], t);
            }
        }
        return ans;
    }

    public int waysToSplit(int[] nums) {
        long sum = 0;
        int n = nums.length;
        int[] pre = new int[n];
        sum = nums[0];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
            sum += nums[i];
        }
        int ans = 0;
        int val = 0;
        for (int i = 0; i < n; i++) {
            val += nums[i];
            if (val > sum / 3) {
                break;
            }
            int tmp = (int) (sum + val) / 2;
            int l = i + 1, r = n - 1;
            if (l >= r) {
                break;
            }
            while (l < r) {
                int half = (l + r + 1) / 2;
                if (pre[half] > tmp) {
                    r = half - 1;
                } else {
                    l = half;
                }
            }
            if (pre[l] < 2 * val) {
                break;
            }
            l = Math.min(n - 1, l);
            int ll = i + 1, lr = l;
            while (ll < lr) {
                int half = (ll + lr + 1) / 2;
                if (pre[half] < 2 * val) {
                    ll = half;
                } else {
                    lr = half - 1;
                }
            }
            ans += l - ll + 1;
            ans = ans % 1000000007;
        }
        return ans;
    }

    public int minOperations(int[] target, int[] arr) {
        return target.length - minOperationsAss(target, arr);
    }

    public int minOperationsAss(int[] target, int[] arr) {
        if (target == null || target.length <= 0 || arr == null || arr.length <= 0) {
            return 0;
        }
        int length1 = target.length;
        int length2 = arr.length;
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (target[i - 1] == arr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return target.length - dp[length1][length2];
    }

    /**
     * 5649. 解码异或后的数组
     */
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] arr = new int[n + 1];
        arr[0] = first;
        for (int i = 1; i <= n; i++) {
            arr[i] = encoded[i - 1] ^ arr[i - 1];
        }
        return arr;
    }

    /**
     * 5652. 交换链表中的节点
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode p = head;
        ListNode q = head;
        int i = 1;
        while (i < k) {
            q = q.next;
            i++;
        }
        ListNode first = q;
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }
        int t = first.val;
        first.val = p.val;
        p.val = t;
        return head;
    }

    /**
     * 5650. 执行交换操作后的最小汉明距离
     * 5 1 2 4 3
     * 3 1 2 4 5
     * 3 1 5 4 2
     * 2 1 5 4 3
     * <p>
     * 0 4 + 4 2 -> 0 2
     */

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);
        for (int[] a : allowedSwaps) {
            uf.union(a[0], a[1]);
        }
        HashMap<Integer, List<Integer>> tMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = tMap.getOrDefault(target[i], new ArrayList<>());
            list.add(i);
            tMap.put(target[i], list);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> list = tMap.getOrDefault(source[i], new ArrayList<>());
            boolean b = true;
            for (int j = 0; j < list.size(); j++) {
                if (uf.isConnect(i, list.get(j))) {
                    list.remove(j);
                    tMap.put(source[i], list);
                    b = false;
                    break;
                }
            }
            if (b) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 5639. 完成所有工作的最短时间
     * 91 84 76 59 49 38 8 6
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        Arrays.sort(jobs);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        PriorityQueue<Integer> p = new PriorityQueue<>((o1, o2) -> o1 - o2);
        int i = n - 1;
        for (; i >= n - k; i--) {
            p.add(jobs[i]);
        }
        for (; i >= 0; i--) {
            int t = p.poll();
            p.add(t + jobs[i]);
        }
        int max = 0;
        for (int t : p) {
            max = Math.max(t, max);
        }
        minimumTimeRequiredMax = max;
        help(jobs, new int[k], max, n - 1);
        return minimumTimeRequiredMax;

    }

    private int minimumTimeRequiredMax = 0;

    public void help(int[] jobs, int[] works, int max, int i) {
        if (i == 0) {
            int k = Integer.MAX_VALUE;
            int m = 0;
            for (int j = 0; j < works.length; j++) {
                k = Math.min(k, works[j]);
                m = Math.max(m, works[j]);
            }
            m = Math.max(m, k + jobs[i]);
            minimumTimeRequiredMax = Math.min(m, minimumTimeRequiredMax);
            return;
        }
        for (int j = 0; j < works.length; j++) {
            if (works[j] + jobs[i] < max) {
                works[j] += jobs[i];
                help(jobs, works, max, i - 1);
                works[j] -= jobs[i];
            }
        }
    }
}
