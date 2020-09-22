package 高并发.Java高并发编程详解多线程架构设计.Chapter5_线程间的通信;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

/**
 * @program: PhoesLeeCode
 * @className: BooleanLock
 * @description: 自定义显示锁的实现
 * @author: lov.moran
 * @date 2020-09-17 16:35
 */
public class BooleanLock implements Lock{

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                blockedList.add(currentThread);
                this.wait();
            }
            blockedList.remove(currentThread);
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills < 0){
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked){
                    if(remainingMills < 0) {
                        throw new TimeoutException("can't get the lock during" + mills);
                    }
                    if(!blockedList.contains(currentThread)){
                        blockedList.add(currentThread);
                    }
                    this.wait();
                    remainingMills = endMills - System.currentTimeMillis();
                }
                blockedList.remove(currentThread);
                this.locked = true;
                this.currentThread =  currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if(currentThread == currentThread()){
                this.locked = false;
                Optional.of(currentThread().getName() + " release the lock.");
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThread() {
        return Collections.unmodifiableList(blockedList);
    }
}
