package leetcode.specialDataStruct;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @program: PhoesLeeCode
 * @description: 面试题 03.02. 栈的最小值 // 用双栈更佳，一个存栈元素  另一个存栈元素对应的最小值
 * @author: Feng.H
 * @create: 2020-12-19 16:34
 **/
public class MinStack {

    private Stack<Integer> stack;
    private PriorityQueue<Integer> priorityQueue;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        this.priorityQueue = new PriorityQueue<>();
        this.stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        priorityQueue.add(x);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        priorityQueue.remove(stack.pop());
    }

    public int top() {
        if(stack.isEmpty()){
            return 0;
        }
        return stack.peek();
    }

    public int getMin() {
        if(priorityQueue.isEmpty()){
            return 0;
        }
        return priorityQueue.peek();
    }
}
