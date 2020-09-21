package 高并发.Java高并发编程详解多线程架构设计.ThreadPool.实现;

import 高并发.Java高并发编程详解多线程架构设计.ThreadPool.ThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @description: 线程池的应用
 * @author: Feng.H
 * @create: 2020-09-19 21:52
 **/
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (; ; ) {
            System.out.println("------------------------------------------------");
            System.out.println("getActiveCount: " + threadPool.getActiveCount());
            System.out.println("getQueueSize: " + threadPool.getQueueSize());
            System.out.println("getCoreSize: " + threadPool.getCoreSize());
            System.out.println("getMaxSize: " + threadPool.getMaxSize());
            TimeUnit.SECONDS.sleep(4);
        }
    }
}
