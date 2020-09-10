package leetcode.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: PhoesLeeCode
 * @className: AnimalShelf
 * @description: 面试题 03.06. 动物收容所
 * 执行用时：103 ms, 在所有 Java 提交中击败了41.21%的用户
 * 内存消耗：49.7 MB, 在所有 Java 提交中击败了100.00%的用户
 * @author: lov.moran
 * @date 2020-07-01 10:17
 */
class AnimalShelf {

    private List<int[]> animals;

    public AnimalShelf() {
        this.animals = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        animals.add(animal);
    }

    public int[] dequeueAny() {
        if (animals.size() == 0) {
            return new int[]{-1, -1};
        }
        return animals.remove(0);
    }

    public int[] dequeueDog() {
        for (int i = 0; i < animals.size(); i++) {
            int[] ani = animals.get(i);
            if (ani[1] == 1) {
                return animals.remove(i);
            }
        }
        return new int[]{-1, -1};
    }

    public int[] dequeueCat() {
        for (int i = 0; i < animals.size(); i++) {
            int[] ani = animals.get(i);
            if (ani[1] == 0) {
                return animals.remove(i);
            }
        }
        return new int[]{-1, -1};
    }
}
