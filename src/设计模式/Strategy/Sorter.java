package 设计模式.Strategy;

import java.util.Comparator;

/**
 * @program: PhoesLeeCode
 * @className: Sorter
 * @description:
 * @author: lov.moran
 * @date 2020-09-07 16:28
 */
public class Sorter<T> {

    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minP = i;
            for (int j = i + 1; j < arr.length; j++) {
                minP = comparator.compare(arr[j], arr[minP]) == -1?j:minP;
            }
            swap(arr, i, minP);
        }
    }

    public static<T> void swap(T[]arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
