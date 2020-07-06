package Template;

import java.util.Random;

/**
 * @program: PhoesLeeCode
 * @className: FenwichTree
 * @description: 树状数组模板
 * 树状数组
 * 原理参考：http://www.hawstein.com/posts/binary-indexed-trees.html
 * 或：https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
 * @author: lov.moran
 * @date 2020-07-06 16:45
 */
public class FenwichTree {

    public int length;
    private int[] tree;

    /**
     * 为了统一下标，所以tree[0]不被使用，数组有效范围1~length。
     */
    public FenwichTree(int length) {
        this.length = length;
        tree = new int[length + 1];
    }

    /**
     * 计算1~index范围内和
     * index一直减去lowBit(index)，直到index为0
     */
    public int sum(int index) {
        if (index < 1 && index > length) {
            throw new IllegalArgumentException("Out of Range!");
        }
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= lowBit(index);
        }
        return sum;
    }

    /**
     * 计算start~end范围内和
     */
    public int sum(int start, int end) {
        return sum(end) - sum(start - 1);
    }

    /**
     * index一直加上lowBit(index)，直到index为length。这些位置的值都加上value
     */
    public void put(int index, int value) {
        if (index < 1 && index > length) {
            throw new IllegalArgumentException("Out of Range!");
        }
        while (index <= length) {
            tree[index] += value;
            index += lowBit(index);
        }
    }

    /**
     * index一直减去lowBit(index)，直到index为length。这些位置的值都加上value
     */
    public int get(int index) {
        if (index < 1 && index > length) {
            throw new IllegalArgumentException("Out of Range!");
        }
        int sum = tree[index];
        int z = index - lowBit(index);
        index--;
        while (index != z) {
            sum -= tree[index];
            index -= lowBit(index);
        }
        return sum;
    }

    /**
     * 保留k的二进制最低位1的值。例如，1110保留最低位1即0010.
     */
    private static int lowBit(int k) {
        return k & -k;
    }

    /**
     * 树状数组测试
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int length=15;
        FenwichTree bTree=new FenwichTree(length);
        Random random=new Random();
        //随机放满数据
        for (int i = 1; i <= bTree.length; i++) {
            bTree.put(i, random.nextInt(100));
        }
        //取出每一位
        for (int i = 1; i <= bTree.length; i++) {
            int value=bTree.get(i);
            System.out.printf("%3d",value);
            System.out.print("  ");
        }
        System.out.println();
        //计算0~i的和
        for (int i = 1; i <= bTree.length; i++) {
            int sum=bTree.sum(i);

            System.out.printf("%3d",sum);
            System.out.print("  ");
        }
        System.out.println();
        //计算start~end的和
        System.out.printf("%3d",bTree.sum(2,4));
    }
}
