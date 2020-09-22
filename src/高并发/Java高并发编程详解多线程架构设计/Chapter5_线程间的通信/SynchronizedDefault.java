package 高并发.Java高并发编程详解多线程架构设计.Chapter5_线程间的通信;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: SynchronizedDefault
 * @description: synchronized
 * @author: lov.moran
 * @date 2020-09-17 16:25
 */
public class SynchronizedDefault {

    public synchronized void method() {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println(Thread.currentThread().getName() + " sleep well!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDefault synchronizedDefault = new SynchronizedDefault();
        Thread t1 = new Thread(synchronizedDefault::method);
        t1.start();
        //make sure t1 started
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(synchronizedDefault::method);
        t2.start();

        //if t2 need to wait for a long time, t2.interrupt
        TimeUnit.SECONDS.sleep(5);
        t2.interrupt();
        System.out.println("T2 is interrupt?" + t2.isInterrupted());
        System.out.println("T2's state is " + t2.getState());
    }
}
