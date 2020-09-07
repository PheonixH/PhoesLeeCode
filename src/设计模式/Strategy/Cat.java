package 设计模式.Strategy;

import java.util.Comparator;

/**
 * @program: PhoesLeeCode
 * @className: Cat
 * @description:
 * @author: lov.moran
 * @date 2020-08-19 11:41
 */
public class Cat {
    int weight, height;

    public Cat(int weight, int height) {
        this.height = height;
        this.weight = weight;
    }


    /**
     * 比较方法：帮助排序
     *
     * @param c2 B猫
     * @return 猫的比较
     */
    public int compareTo(Cat c2) {
        if (this.weight != c2.weight) {
            return this.weight - c2.weight;
        } else {
            return this.height - c2.height;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight = " + weight + "," +
                "height = " + height +
                "}";
    }

    /**
     * 可以通过修改比较策略来修改排序
     */
    public static void main(String[] args) {
        Cat[] cats = {new Cat(1, 3), new Cat(2, 4), new Cat(3, 2)};
        Comparator<Cat> catComparator = new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return Integer.compare(o1.height, o2.height);
            }
        };
        Sorter.sort(cats, catComparator);
        System.out.println("Sort by Height:");
        for (Cat c : cats) {
            System.out.println(c);
        }
        Comparator<Cat> catComparatorW = new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        };
        Sorter.sort(cats, catComparatorW);
        System.out.println("Sort by Weight:");
        for (Cat c : cats) {
            System.out.println(c);
        }
    }
}
