package 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool;

/**
 * @program: PhoesLeeCode
 * @description: 手写线程池接口任务队列
 * @author: Feng.H
 * @create: 2020-09-19 17:26
 **/
public interface RunnableQueue {

    /**
     * offer a task
     */
    void offer(Runnable runnable);

    /**
     * @return a runnable to a thread
     */
    Runnable take() throws InterruptedException;

    /**
     * @return the number of task;
     */
    int size();
}
