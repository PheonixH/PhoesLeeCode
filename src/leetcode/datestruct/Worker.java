package leetcode.datestruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ProjectName: PhoesLeeCode
 * @Package: LeetCode.datestruct
 * @ClassName: Worker
 * @Description:
 * @Author: Pheonix
 * @CreateDate: 2019/1/17 14:59
 * @Version: 1.0
 */
public class Worker {
    //    List<List<Double>> pay;
    public List<List<Double>> qua;

    public Worker() {
//        this.pay = new LinkedList<>();
        this.qua = new ArrayList<>();
    }

    public void add(int qual, int wage) {
        List<Double> list = new ArrayList<>();
        list.add((double) qual);
        list.add((double) wage);
        list.add((double) wage / (double) qual);
        qua.add(list);
    }

    public void add(List<Double> l) {
        qua.add(l);
    }

    public void sortByPay(int key) {
        Collections.sort(qua, new Comparator<List<Double>>() {
            @Override
            public int compare(List<Double> o1, List<Double> o2) {
                if (o1.get(key) >= o2.get(key)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public int sum(int K) {
        int totalq = 0;
        for (int i = 0; i < K; i++) {
            totalq += this.qua.get(i).get(0);
        }
        return totalq;
    }
}
