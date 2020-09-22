package 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool;

/**
 * @program: PhoesLeeCode
 * @description: 手写线程池接口
 * @author: Feng.H
 * @create: 2020-09-19 17:26
 **/
public interface ThreadPool {

    /**
     * submit a task
     */
    void execute(Runnable runnable);

    /**
     * shutdown a thread pool
     */
    void shutdown();

    /**
     * return the init number of thread
     */
    int getInitSize();


    /**
     * return the max number of thread
     */
    int getMaxSize();


    /**
     * return the min number of thread
     */
    int getCoreSize();


    /**
     * return the answer of is the thread pool closed?
     */
    boolean isShutdown();

    int getActiveCount();

    int getQueueSize();
}
