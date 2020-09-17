package 高并发.Java高并发编程详解多线程架构设计.synchronizedTest;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: LockTest
 * @description: 交叉锁测试
 * @author: lov.moran
 * @date 2020-09-17 15:28
 */
public class LockTest {

    private final Object o1 = new Object();
    private final Object o2 = new Object();

    public void method1(){
        synchronized (o1){
            System.out.println(Thread.currentThread().getName() + ": get o1!");
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + ": get o2!");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": release o2!");
        }
        System.out.println(Thread.currentThread().getName() + ": release o1!");
    }

    public void method2(){
        synchronized (o2){
            System.out.println(Thread.currentThread().getName() + ": get o2!");
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + ": get o1!");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": release o1!");
        }
        System.out.println(Thread.currentThread().getName() + ": release o2!");
    }

    public static void main(String[] args) {
        LockTest l = new LockTest();
        Thread t1 = new Thread(l::method1);
        Thread t2 = new Thread(l::method2);
        t1.start();
        t2.start();
    }
}
