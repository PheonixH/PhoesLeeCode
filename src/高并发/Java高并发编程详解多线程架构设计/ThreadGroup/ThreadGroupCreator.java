package 高并发.Java高并发编程详解多线程架构设计.ThreadGroup;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @description: 线程组测试: 复制和递归
 * @author: Feng.H
 * @create: 2020-09-19 15:58
 **/
public class ThreadGroupCreator {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group = new ThreadGroup("测试线程组");
        System.out.println("未指定线程的父线程组时候：" + (group.getParent() == mainThreadGroup));


        ThreadGroup group2 = new ThreadGroup(group, "测试线程组");
        System.out.println("指定线程的父线程组时候：" + (group2.getParent() == mainThreadGroup));


        //递归：寻找活跃的线程
        Thread thread = new Thread(group2, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "测试线程");
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        Thread[] threads = new Thread[mainThreadGroup.activeCount()];
        //默认true
        int recureSize = mainThreadGroup.enumerate(threads, false);
        System.out.println(recureSize);

        recureSize = mainThreadGroup.enumerate(threads);
        System.out.println(recureSize);


    }
}
