package leetcode.datestruct;

import java.util.HashSet;
import java.util.Random;

/**
 * @ProjectName: PhoesLeeCode
 * @Package: LeetCode.datestruct
 * @ClassName: RandomizedSet
 * @Description: 380
 * @Author: Pheonix
 * @CreateDate: 2019/1/16 14:31
 * @Version: 1.0
 */
public class RandomizedSet {

    /**
     * @derecription: HashSet实现 --- 318ms
     *  * @param null
     * @author: pheonix
     * @time:  14:51
     * @return:
     */
    public HashSet<Integer> hashtable;

    /**
     * Initialize your LeetCode.data structure here.
     */
    public RandomizedSet() {
        this.hashtable = new HashSet<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!this.hashtable.contains(val)) {
            this.hashtable.add(val);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (this.hashtable.contains(val)) {
            this.hashtable.remove(val);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Object[] objects = this.hashtable.toArray();
        Random ra = new Random();
        if (objects.length != 0) {
            return (int) objects[ra.nextInt(objects.length)];
        } else {
            return 0;
        }
    }
    public static void main(String[] argc){
        RandomizedSet randomizedSet = new RandomizedSet();

        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(2));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.getRandom());
//        System.out.println(randomizedSet.remove(2));
//        System.out.println(randomizedSet.remove(2));
//        System.out.println(randomizedSet.remove(2));
//        System.out.println(randomizedSet.remove(2));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
