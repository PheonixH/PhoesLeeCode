package 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool;

/**
 * @program: PhoesLeeCode
 * @description: Runnable的一个实现，主要用于线程池内部，该类会使用到RunnableQueue， 然后不断的重queue中取出某个runnable 并且运行runnable 的run方法
 * @author: Feng.H
 * @create: 2020-09-19 20:41
 **/
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;


    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }


    @Override
    public void run() {
        //如果当前任务为running并且没有被中断， 那他将不断地从queue中获取runnable,然后执行run方法；
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前任务，会在线程池的shutdown方法中使用到
     */
    public void stop() {
        this.running = false;
    }
}
