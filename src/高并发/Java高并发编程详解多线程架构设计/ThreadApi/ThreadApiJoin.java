package 高并发.Java高并发编程详解多线程架构设计.ThreadApi;

/**
 * @program: PhoesLeeCode
 * @className: ThreadApiJoin
 * @description: join方法
 * @author: lov.moran
 * @date 2020-09-17 11:51
 */
public class ThreadApiJoin {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "第 " + i + " 次操作。");
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "第 " + i + " 次操作。");
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "第 " + i + " 次操作。");
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
