package data;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @program: PhoesLeeCode
 * @className: StackOfPlates
 * @description: 面试题 03.03. 堆盘子
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 * <p>
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 * <p>
 * 执行用时：14 ms, 在所有 Java 提交中击败了83.96% 的用户
 * 内存消耗：47.6 MB, 在所有 Java 提交中击败了75.76% 的用户
 * @author: lov.moran
 * @date 2020-08-03 15:50
 */
public class StackOfPlates {
    //每个栈的最大cap数
    int maxcaps;
    //存储栈的linkedlist,因为linkedlist增删快
    LinkedList list = new LinkedList();

    public StackOfPlates(int cap) {
        this.maxcaps = cap;
    }

    public void push(int val) {
        if (maxcaps <= 0) {
            return;
        }
        if (list.isEmpty()) {
            //用来当做栈，存储元素的，arraylist查询较快
            ArrayList arr = new ArrayList();
            arr.add(val);
            list.add(arr);
        } else {
            ArrayList oldarr = (ArrayList) list.get(list.size() - 1);
            if (oldarr.size() == maxcaps) {
                ArrayList arr = new ArrayList();
                arr.add(val);
                list.add(arr);
            } else {
                //最后一个没有满
                oldarr.add(val);
            }
        }
    }

    public int pop() {
        if (list.size() == 0) {
            return -1;
        }
        ArrayList oldarr = (ArrayList) list.get(list.size() - 1);
        int re = (int) oldarr.get(oldarr.size() - 1);
        //删掉pop的元素
        oldarr.remove(oldarr.size() - 1);
        if (oldarr.size() == 0) {
            //删掉空的栈
            list.remove(oldarr);
        }
        return re;
    }

    public int popAt(int index) {
        if (list.size() == 0 || index > list.size() - 1) {
            return -1;
        }
        ArrayList oldarr = (ArrayList) list.get(index);
        int re = (int) oldarr.get(oldarr.size() - 1);
        //删掉pop的元素
        oldarr.remove(oldarr.size() - 1);
        if (oldarr.size() == 0) {
            //删掉空的栈
            list.remove(oldarr);
        }
        return re;

    }
}
