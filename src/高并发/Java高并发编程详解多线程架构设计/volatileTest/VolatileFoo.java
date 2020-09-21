package 高并发.Java高并发编程详解多线程架构设计.volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: VolatileFoo
 * @description: volatile 关键字的测试
 * @author: lov.moran
 * @date 2020-09-21 11:33
 */
public class VolatileFoo {

    private final static int max = 5;

    // 不加volatile  Reader线程根本反应不过来init_value的变化
    static volatile int init_value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is running!");
            int localValue = init_value;
            while (localValue < max) {
                if (init_value != localValue) {
                    System.out.println("The init_value is update to " + init_value);
                    localValue = init_value;
                }
            }
            System.out.println(Thread.currentThread().getName() + " will be closed!");
        }, "Reader").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is running!");
            int localValue = init_value;
            while (localValue < max) {
                System.out.println("The init_value will be changed to " + (++localValue));
                init_value = localValue;
                // 短暂休眠  给予Reader足够的反应时间
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " will be closed!");
        }, "Updater").start();

    }
}
