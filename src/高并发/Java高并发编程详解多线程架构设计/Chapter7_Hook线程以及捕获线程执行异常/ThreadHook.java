package 高并发.Java高并发编程详解多线程架构设计.Chapter7_Hook线程以及捕获线程执行异常;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @description: hook 线程 功能测试
 * @author: Feng.H
 * @create: 2020-09-19 17:45
 **/
public class ThreadHook {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    System.out.println("The N0.1 hook thread is running!");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The N0.1 hook thread will exit!");
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    System.out.println("The N0.2 hook thread is running!");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The N0.2 hook thread will exit!");
            }
        });

        Thread thread = new Thread(() -> {
            try {
                System.out.println("The normal thread is running!");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The normal thread will exit!");
        });
//        thread.setDaemon(true);
        thread.start();

        System.out.println("Main thread is running!");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("System will shutdown!");
    }
}
