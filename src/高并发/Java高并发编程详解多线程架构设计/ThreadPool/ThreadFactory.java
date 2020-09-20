package 高并发.Java高并发编程详解多线程架构设计.ThreadPool;

/**
 * @program: PhoesLeeCode
 * @description: the factory to create threads
 * @author: Feng.H
 * @create: 2020-09-19 20:30
 **/
public interface ThreadFactory {

    /**
     * create a thread
     * @param runnable runnable
     * @return the thread
     */
    Thread createThread(Runnable runnable);
}
