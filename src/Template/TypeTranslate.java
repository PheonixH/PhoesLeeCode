package Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: PhoesLeeCode
 * @className: TypeTranslate
 * @description: java 简便的类型转换写法
 * @author: lov.moran
 * @date 2021-01-28 16:26
 */
public class TypeTranslate {

    /**
     * ArrayList 转 int[]
     *
     * @param list ArrayList类型
     * @return int[] 类型
     */
    public int[] arrayListToIntArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * ArrayList 转 Integer[]
     *
     * @param list ArrayList 类型
     * @return Integer[] 类型
     */
    public Integer[] arrayListToIntegerArray(List<Integer> list) {
        return list.toArray(new Integer[list.size()]);
    }

    /**
     * Integer[] 转 int[]
     *
     * @param array Integer[] 类型
     * @return int[] 类型
     */
    public int[] integerArrayToIntArray(Integer[] array) {
        return Arrays.stream(array).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * Integer[] 转 List
     *
     * @param array Integer[] 类型
     * @return List 类型
     */
    public List<Integer> integerArrayToList(Integer[] array) {
        return Arrays.asList(array);
    }

    /**
     * int[] 转 Integer[]
     *
     * @param arr int[] 类型
     * @return Integer[] 类型
     */
    public Integer[] intArrayToIntegerArray(int[] arr) {
        return Arrays.stream(arr).boxed().toArray(Integer[]::new);
    }

    /**
     * int[] 转 List
     *
     * @param arr int[] 类型
     * @return List 类型
     */
    public List<Integer> intArrayToList(int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    /**
     * map 的 keySet() 转 List
     *
     * @param map map 类型
     * @return List 类型
     */
    public List<Integer> mapToList(Map<Integer, Integer> map) {
        return new ArrayList<>(map.keySet());
    }

}