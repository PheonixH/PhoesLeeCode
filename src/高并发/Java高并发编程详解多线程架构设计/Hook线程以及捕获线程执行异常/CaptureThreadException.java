package 高并发.Java高并发编程详解多线程架构设计.Hook线程以及捕获线程执行异常;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @description: UncaughtExceptionHandler使用实例
 * @author: Feng.H
 * @create: 2020-09-19 17:26
 **/
public class CaptureThreadException {

    public static void main(String[] args) {
        //设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur exception!");
            e.printStackTrace();
        });

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
