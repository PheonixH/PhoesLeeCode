package 高并发;

/**
 * @program: PhoesLeeCode
 * @className: CreateSimpleThread
 * @description: 创建线程的两种方式
 * @author: lov.moran
 * @date 2020-09-16 11:03
 */
public class CreateSimpleThread {

    static class Thread01 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("继承Thread类实现线程！");
            }
        }
    }

    static class Thread02 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("继承Runnable接口实现线程！");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread01();
        Thread thread2 = new Thread(new Thread02());
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Lambda 方式生成线程");
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
