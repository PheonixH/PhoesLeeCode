package leetcode.specialDataStruct;

import java.util.Stack;

/**
 * @program: PhoesLeeCode
 * @description: 232. 用栈实现队列
 * <p>
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
 * <p>
 * 实现 MyQueue 类：
 * <p>
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * @author: Huang Feng
 * @create: 2021-03-05 11:09
 **/
public class MyQueue {

    Stack<Integer> first;

    Stack<Integer> second;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        first = new Stack<>();
        second = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        while (!second.empty()) {
            first.push(second.pop());
        }
        first.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        while (!first.empty()) {
            second.push(first.pop());
        }
        return second.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        while (!first.empty()) {
            second.push(first.pop());
        }
        return second.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return first.empty() && second.empty();
    }

}
