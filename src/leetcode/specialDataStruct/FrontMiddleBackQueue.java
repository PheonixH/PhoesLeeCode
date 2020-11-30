package leetcode.specialDataStruct;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: PhoesLeeCode
 * @description:
 * @author: Feng.H
 * @create: 2020-11-28 22:41
 **/
public class FrontMiddleBackQueue {

    private Deque<Integer> front;
    private Deque<Integer> back;

    public FrontMiddleBackQueue() {
        front = new LinkedList<>();
        back = new LinkedList<>();
    }

    public void pushFront(int val) {
        front.offerFirst(val);
        if (front.size() > back.size() + 1) {
            back.offerFirst(front.pollLast());
        }
    }

    public void pushMiddle(int val) {
        while (front.size() > back.size()) {
            back.offerFirst(front.pollLast());
        }
        front.offerLast(val);
    }

    public void pushBack(int val) {
        back.offerLast(val);
        if (front.size() + 1 < back.size()) {
            front.offerLast(back.pollFirst());
        }
    }

    public int popFront() {
        if(front.isEmpty()&&back.isEmpty()){
            return -1;
        }
        if (front.isEmpty()) {
            front.addLast(back.pollFirst());
        }
        int res = front.pollFirst();
        if (front.size() + 1 < back.size()) {
            front.offerLast(back.pollFirst());
        }
        return res;
    }

    public int popMiddle() {

        if(front.isEmpty()&&back.isEmpty()){
            return -1;
        }
        if (front.size() >= back.size()) {
            return front.pollLast();
        } else {
            return back.pollFirst();
        }
    }

    public int popBack() {

        if(front.isEmpty()&&back.isEmpty()){
            return -1;
        }
        if (back.isEmpty()) {
            back.addFirst(front.pollLast());
        }
        int res = back.pollLast();
        if (front.size() > back.size() + 1) {
            back.offerFirst(front.pollLast());
        }
        return res;
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue f = new FrontMiddleBackQueue();
        f.pushFront(1);
        f.pushBack(2);
        f.pushMiddle(3);
        f.pushMiddle(4);
        f.popFront();
        f.popMiddle();
        f.popMiddle();
        f.popBack();
        f.popFront();
    }
}
