package 高并发;

/**
 * @program: PhoesLeeCode
 * @className: TestJoin
 * @description: 尝试使用Thread.join() && Thread.sleep() && Thread.yield()
 * @author: lov.moran
 * @date 2020-09-16 11:28
 */
public class ThreadNormalFunction {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1我要先睡一会" + i);
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("2我开始啦");
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2等它先执行完毕");
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("3我开始啦" + i);
                Thread.yield();
                System.out.println("3我只谦让一下哦" + i);
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("4我开始啦" + i);
                Thread.yield();
                System.out.println("4谦让一下我也要抢先哦" + i);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
