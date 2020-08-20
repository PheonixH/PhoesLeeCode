package 设计模式.Strategy;

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
}
