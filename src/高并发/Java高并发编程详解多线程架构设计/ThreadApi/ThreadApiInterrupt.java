package 高并发.Java高并发编程详解多线程架构设计.ThreadApi;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: ThreadApiInterrupt
 * @description: interrupt中断
 * @author: lov.moran
 * @date 2020-09-17 10:53
 */
public class ThreadApiInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Oh, I am interrupted！");
            }
            System.out.println("Oh, I doing.");
        });
        thread1.start();
        System.out.println(thread1.isInterrupted());
        thread1.interrupt();
        System.out.println(thread1.isInterrupted());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("I need slppeing 2 seconds.");


        System.out.println("---------------------------------------");

        Thread thread2 = new Thread(() -> {
            int i = 0;
            while (i < 5) {
                i++;
//                System.out.println("I am Interrupted? " + Thread.interrupted());
                System.out.println("I am Interrupted? " + Thread.currentThread().isInterrupted());
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                    System.out.println("I am doing No." + i + ".");
//                } catch (InterruptedException e) {
//                    System.out.println("Oh, I am interrupted？" + Thread.currentThread().isInterrupted());
//                }
            }
        });
//        thread2.setDaemon(true);
        thread2.interrupt();
        thread2.start();
        System.out.println("Thread is Interrupted? " + thread2.isInterrupted());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Thread is Interrupted? " + thread2.isInterrupted());


    }
}
