package leetcode.specialDataStruct;

/**
 * @program: PhoesLeeCode
 * @description: 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * 实现 NumArray 类：
 *     NumArray(int[] nums) 使用数组 nums 初始化对象
 *     int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 *
 * 执行用时：11 ms, 在所有 Java 提交中击败了43.75% 的用户
 * 内存消耗：41.3 MB, 在所有 Java 提交中击败了70.75% 的用户
 * @author: Huang Feng
 * @create: 2021-03-01 09:42
 **/
public class NumArray {


    private int[] pre;


    public NumArray(int[] nums) {
        int n = nums.length;
        pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return pre[j + 1] - pre[i];
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */