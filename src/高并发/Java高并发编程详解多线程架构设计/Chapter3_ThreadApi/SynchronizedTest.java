package 高并发.Java高并发编程详解多线程架构设计.Chapter3_ThreadApi;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: SynchronizedTest
 * @description: synchronized test
 * @author: lov.moran
 * @date 2020-09-17 15:01
 */
public class SynchronizedTest {
    public synchronized void method01() {
        System.out.println(Thread.currentThread().getName() + ": method01");
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method02() {
        System.out.println(Thread.currentThread().getName() + ": method02");
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        new Thread(synchronizedTest::method01).start();
        new Thread(synchronizedTest::method02).start();

//        SynchronizedTest synchronizedTest2 = new SynchronizedTest();
//        new Thread(synchronizedTest::method01).start();
//        new Thread(synchronizedTest2::method02).start();

    }
}
