package 设计模式.Strategy;

import java.util.Arrays;

/**
 * @program: PhoesLeeCode
 * @className: Demo01
 * @description: 策略模式示例:策略模式可以使用在同一动作不同效果等等情况之下，例如游戏里面的攻击方式：
 * @author: lov.moran
 * @date 2020-08-19 11:35
 */
public class Demo {

    /**
     * 业务代码 1
     *
     * @param arr 待排序数组 int[]
     */
    public static void fun(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 业务代码 2
     *
     * @param arr 待排序数组 Cat[]
     */
    public static void fun(Cat[] arr) {
        Arrays.sort(arr);
    }

    //... 无穷无尽

    /**
     * 策略模式： 请大家都去实现完Comparable接口（里面有compareTo方法)之后再来调用
     *
     * @param arr 继承Comparable接口的数组
     */
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for(int j = i+1;i<arr.length;j++){
                min = arr[j].compareTo(arr[min]) == -1?j:min;
            }
            swap(arr, i ,min);
        }
    }

    static void swap(Comparable[] arr, int i, int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 9, 6};
        Demo.fun(arr);
        System.out.println(Arrays.toString(arr));
    }
}
