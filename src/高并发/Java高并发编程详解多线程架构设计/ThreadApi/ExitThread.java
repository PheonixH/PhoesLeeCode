package 高并发.Java高并发编程详解多线程架构设计.ThreadApi;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: ExitThread
 * @description: 结束线程
 * @author: lov.moran
 * @date 2020-09-17 12:02
 */
public class ExitThread {
    static class VolatileExitThread extends Thread {
        private volatile boolean closed = false;

        @Override
        public void run(){
            System.out.println("I start work!");
            while (!closed || !Thread.currentThread().isInterrupted()) {
                System.out.println("I am working now!");
            }
            System.out.println("end work.");
        }

        public void setClosedTrue(){
            this.closed = true;
            this.interrupt();
        }
    }


    public static void main(String[] args) throws InterruptedException {

//        //方式二  中断结束
//        Thread thread1 = new Thread(() -> {
//            System.out.println("I start work!");
//            while (!Thread.currentThread().isInterrupted()) {
//                System.out.println("I am working now!");
//            }
//            System.out.println("end work.");
//        });
//        thread1.start();
//        TimeUnit.MILLISECONDS.sleep(100);
//        thread1.interrupt();

        //方式三 volatile 结束
        VolatileExitThread thread2 = new VolatileExitThread();
        thread2.setName("TestThread");
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(1);
        thread2.setClosedTrue();

    }
}
