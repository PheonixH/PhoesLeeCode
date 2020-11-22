package leetcode.datestruct;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: PhoesLeeCode
 * @description:
 * @author: Feng.H
 * @create: 2020-11-15 10:30
 **/
public class OrderedStream {

    private String[] strings;

    private int ptr;

    public OrderedStream(int n) {
        strings = new String[n];
    }

    public List<String> insert(int id, String value) {
        strings[id - 1] = value;
        List<String> res = new ArrayList<>();
        for (; ptr < strings.length; ptr++) {
            if (strings[ptr] != null) {
                res.add(strings[ptr]);
            } else {
                break;
            }
        }
        return res;
    }
}
