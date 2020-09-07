package LeetCode.datestruct;

/**
 * @ProjectName: PhoesLeeCode
 * @Package: PACKAGE_NAME
 * @ClassName: LeetCode.datestruct.MedianFinder
 * @Description: 295 数据流中位数
 * @Author: Pheonix
 * @CreateDate: 2019/1/16 10:13
 * @Version: 1.0
 */
public class MedianFinder {

    /**
     * @derecription: 双链表 -- 1023ms
     *  * @param null
     * @author: pheonix
     * @time:  13:19
     * @return:
     */
    private MedianFinderList min;
    private MedianFinderList max;
    private int minsize;
    private int maxsize;

    public MedianFinder() {
        this.min = null;
        this.max = null;
        this.maxsize = 0;
        this.minsize = 0;
    }

    public void addNum(int num) {
        if (minsize == 0) {
            MedianFinderList n = new MedianFinderList(num);
            min = n;
            minsize = 1;
            return;
        }
        if (maxsize == 0) {
            if(num < min.val){
                int k = min.val;
                addNum(k);
                min = min.next;
                minsize--;
                addNum(num);
                return;
            }
            MedianFinderList n = new MedianFinderList(num);
            max = n;
            maxsize = 1;
            return;
        }
        if (minsize <= maxsize) {
            if (num <= max.val) {
                MedianFinderList n = new MedianFinderList(num);
                MedianFinderList p = min;
                if (p.val <= n.val) {
                    n.next = p;
                    min = n;
                    minsize++;
                    return;
                }
                while (p.next != null && p.next.val > n.val) {
                    p = p.next;
                }
                if (p.next != null && p.next.val <= n.val) {
                    n.next = p.next;
                    p.next = n;
                } else {
                    p.next = n;
                }
                minsize++;
            }
            else {
                int k = max.val;
                addNum(k);
                MedianFinderList p = this.max;
                this.max = this.max.next;
                p = null;
                maxsize--;
                addNum(num);
            }
        } else {
            if (num >= min.val){
                MedianFinderList n = new MedianFinderList(num);
                MedianFinderList p = max;
                if (p.val >= n.val) {
                    n.next = p;
                    max = n;
                    maxsize++;
                    return;
                }
                while (p.next != null && p.next.val < n.val) {
                    p = p.next;
                }
                if (p.next != null && p.next.val >= n.val) {
                    n.next = p.next;
                    p.next = n;
                } else {
                    p.next = n;
                }
                maxsize++;
            }
            else {
                int k = min.val;
                addNum(k);
                MedianFinderList p = this.min;
                this.min = p.next;
                p = null;
                minsize--;
                addNum(num);
            }
        }
    }

    public double findMedian() {
        if(minsize == maxsize){
            if(minsize == 0){
                return 0;
            }
            return ((double)min.val + (double)max.val) / 2;
        } else {
            return min.val;
        }
    }

    /**
     * @derecription: 单链表 -- 2145ms
     *  * @param null
     * @author: pheonix
     * @time:  13:20
     * @return:
     */
//    //方案一 ： 链表  效率低
//    private int val;
//    private LeetCode.datestruct.MedianFinder next;
//    private LeetCode.datestruct.MedianFinder front;
//    private int size;
//
//    /**
//     * initialize your LeetCode.data structure here.
//     */
//    public LeetCode.datestruct.MedianFinder() {
//        this.setVal(0);
//        this.setFront(null);
//        this.setNext(null);
//    }
//
//    public void addNum(int num) {
//        if (this != null) {
//            if(size == 0){
//                LeetCode.datestruct.MedianFinder n = new LeetCode.datestruct.MedianFinder(num);
//                this.next = n;
//                n.front = this;
//                size++;
//                return;
//            }
//            LeetCode.datestruct.MedianFinder m = this.next;
//            while (m.next != null && m.val < num) {
//                m = m.next;
//            }
//            if (m.val >= num) {
//                LeetCode.datestruct.MedianFinder n = new LeetCode.datestruct.MedianFinder(num);
//                n.front = m.front;
//                m.front.next = n;
//                n.next = m;
//                m.front = n;
//            } else {
//                LeetCode.datestruct.MedianFinder n = new LeetCode.datestruct.MedianFinder(num);
//                m.next = n;
//                n.front = m;
//            }
//            this.size++;
//        }
//    }
//
//    public double findMedian() {
//        if (size == 0) {
//            return 0;
//        }
//        if (size / 2 * 2 == size) {
//            LeetCode.datestruct.MedianFinder p = this.next;
//            LeetCode.datestruct.MedianFinder q = this.next.next;
//            int x = size / 2 - 1;
//            double res = 0;
//            while (x > 0) {
//                p = q;
//                q = p.next;
//                x--;
//            }
//            res = (double) p.val + (double) q.val;
//            return res / 2;
//        } else {
//            LeetCode.datestruct.MedianFinder p = this.next;
//            int x = size / 2;
//            while (x > 0) {
//                p = p.next;
//                x--;
//            }
//            return p.val;
//        }
//    }
//
//    public LeetCode.datestruct.MedianFinder(int v) {
//        this.setVal(v);
//        this.setFront(null);
//        this.setNext(null);
//    }
//
//    public int getVal() {
//        return val;
//    }
//
//    public void setVal(int val) {
//        this.val = val;
//    }
//
//    public LeetCode.datestruct.MedianFinder getNext() {
//        return next;
//    }
//
//    public void setNext(LeetCode.datestruct.MedianFinder next) {
//        this.next = next;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public LeetCode.datestruct.MedianFinder getFront() {
//        return front;
//    }
//
//    public void setFront(LeetCode.datestruct.MedianFinder front) {
//        this.front = front;
//    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        int[] ints = {78,14,50,20,13,9,25,8,13,37,29,33,55,52,6,17,65,23,74,43,5,29,29,72,7,13,
        56,21,31,66,69,69,74,12,77,23,10,6,27,63,77,21,40,10,19,59,35,40,44,4,15,29,63,27,46,
        56,0,60,72,35,54,50,14,29,62,24,18,79,16,19,8,77,10,21,66,42,76,14,58,20,0};
        for(int i:ints){
            obj.addNum(i);
            System.out.println(obj.findMedian());
        }
        return;
    }
}

class MedianFinderList {
    MedianFinderList next;
    int val;

    public MedianFinderList(int v) {
        val = v;
        this.next = null;
    }
}



/**
 * Your LeetCode.datestruct.MedianFinder object will be instantiated and called as such:
 * LeetCode.datestruct.MedianFinder obj = new LeetCode.datestruct.MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
