package 高并发.Java高并发编程详解多线程架构设计.Chapter5_线程间的通信;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @program: PhoesLeeCode
 * @className: Lock
 * @description: 自定义显示锁
 * @author: lov.moran
 * @date 2020-09-17 16:33
 */
public interface Lock {
    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThread();
}
