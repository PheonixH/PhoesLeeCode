package 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool.实现;

import 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool.DenyPolicy;
import 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool.RunnableQueue;
import 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool.ThreadPool;

import java.util.LinkedList;

/**
 * @program: PhoesLeeCode
 * @description: 线程池的详细实现之任务列表
 * @author: Feng.H
 * @create: 2020-09-19 20:52
 **/
public class LinkedRunnableQueue implements RunnableQueue {

    //任务队列最大数量
    private final int limit;

    //队列满的时候拒绝策略
    private final DenyPolicy denyPolicy;

    //队列
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit) {
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            while (runnableList.isEmpty()) {
                try {
                    //如果任务队列中没有可执行任务， 则任务将会挂起，进入runnableList关联的monitor waitSet中等待唤醒
                    runnableList.wait();
                } catch (InterruptedException e) {
                    //被中断的时候抛出异常
//                    e.printStackTrace();
                    throw e;
                }
            }
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableList) {
            return runnableList.size();
        }
    }
}
