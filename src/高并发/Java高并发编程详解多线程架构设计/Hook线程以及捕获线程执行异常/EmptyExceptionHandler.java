package 高并发.Java高并发编程详解多线程架构设计.Hook线程以及捕获线程执行异常;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @description: 测试出现unchecked exception的时候会去哪里处理
 * @author: Feng.H
 * @create: 2020-09-19 17:37
 **/
public class EmptyExceptionHandler {


    public static void main(String[] args) {
        //get current thread's thread group
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainThreadGroup.getName());
        System.out.println(mainThreadGroup.getParent());
        System.out.println(mainThreadGroup.getParent().getParent());

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //这里会出现 unchecked 异常
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();

    }
}
