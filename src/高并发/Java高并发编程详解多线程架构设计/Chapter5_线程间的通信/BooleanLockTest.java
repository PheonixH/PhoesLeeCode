package 高并发.Java高并发编程详解多线程架构设计.Chapter5_线程间的通信;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @program: PhoesLeeCode
 * @className: BooleanLockTest
 * @description: 自定义显示锁的使用
 * @author: lov.moran
 * @date 2020-09-17 16:52
 */
public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            try {
                int randomInt = current().nextInt(10);
                System.out.println(currentThread() + "get the lock!");
                TimeUnit.SECONDS.sleep(randomInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BooleanLockTest test = new BooleanLockTest();
        IntStream.range(0,10).mapToObj(i -> new Thread(test::syncMethod)).forEach(Thread::start);
    }
}
