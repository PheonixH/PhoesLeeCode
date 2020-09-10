package leetcode.datestruct;

import java.util.*;

/**
 * @ProjectName: PhoesLeeCode
 * @Package: LeetCode.datestruct
 * @ClassName: RandomizedCollection
 * @Description: 381:平均 时间复杂度 O(1) 下， 执行以下操作的数据结构  双链表 -- 288ms
 * @Author: Pheonix
 * @CreateDate: 2019/1/16 14:12
 * @Version: 1.0
 */
class RandomizedCollection {

    private Map<Integer, List<Integer>> data;
    private List<Integer> list;
    private Random mRandom;
    private int mSize;

    /**
     * Initialize your LeetCode.data structure here.
     */
    public RandomizedCollection() {
        this.data = new HashMap<Integer, List<Integer>>();
        this.list = new ArrayList<Integer>();
        this.mRandom = new Random();
        this.mSize = 0;
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!data.containsKey(val)) {
            data.put(val, new ArrayList<Integer>());
        }
        data.get(val).add(val);
        list.add(val);
        mSize++;
        return true;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (data.containsKey(val)) {
            if (data.get(val).size() > 0) {
                data.get(val).remove(Integer.valueOf(val));
                list.remove(Integer.valueOf(val));
                mSize--;
                return true;
            }
        }
        return false;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        int random = mRandom.nextInt(mSize);
        return list.get(random);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
