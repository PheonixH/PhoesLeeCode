package 高并发.Java高并发编程详解多线程架构设计.ThreadApi;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: ThreadApiSleep
 * @description: 线程API学习之sleep
 * @author: lov.moran
 * @date 2020-09-17 10:12
 */
public class ThreadApiSleep {
    public static void main(String[] args) {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " spends " + (endTime - startTime) + " ms.");
        }).start();
        long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " spends " + (endTime - startTime) + " ms.");
    }
}
