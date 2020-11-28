package Template;

import java.util.Arrays;

/**
 * @program: PhoesLeeCode
 * @description: 树状数组
 * @author: Feng.H
 * @create: 2020-11-28 16:18
 **/
public class BinaryIndexedTree {


    private int[] bitArray;

    // O(nlogn) initialization
//	public BinaryIndexedTree(int[] list) {
//		this.bitArr = new int[list.length + 1];
//		for (int i = 0; i < list.length; i++) {
//			this.update(i, list[i]);
//		}
//	}

    /**
     * O(n) initialization
     */
    public BinaryIndexedTree(int[] num) {
        int n = num.length;
        this.bitArray = new int[n + 1];
        System.arraycopy(num, 0, this.bitArray, 1, num.length);

        for (int i = 1; i < n + 1; i++) {
            int j = i + (i & -i);
            if (j < n + 1) {
                this.bitArray[j] += this.bitArray[i];
            }
        }
    }

    /**
     * 原数组下标为 `idx` 的值增加 `val`
     * 注意不是修改为`val`!
     * Add `val` to elements in `idx` of original array
     *
     * @param idx index of the element in original array that is going to be updated
     * @param val number that will be added to the original element.
     */
    public void update(int idx, int val) {
        idx += 1;
        while (idx < this.bitArray.length) {
            this.bitArray[idx] += val;
            idx = idx + (idx & -idx);
        }
    }

    /**
     * 获取 0 ~ idx 位的原数组的元素和（包含idx）
     * Get the sum of elements in the original array up to index `idx`
     *
     * @param idx index of the last element that should be summed.
     * @return sum of elements from index 0 to `idx`.
     */
    public int prefixSum(int idx) {
        idx += 1;
        int result = 0;
        while (idx > 0) {
            result += this.bitArray[idx];
            idx = idx - (idx & -idx);
        }
        return result;
    }


    /**
     * 返回数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
     *
     * @param i 索引
     * @param j 索引
     * @return 数组从索引 i 到 j  (i ≤ j) 范围内元素的总和
     */
    public int sumRange(int i, int j) {
        return prefixSum(j) - prefixSum(i - 1);
    }

}
