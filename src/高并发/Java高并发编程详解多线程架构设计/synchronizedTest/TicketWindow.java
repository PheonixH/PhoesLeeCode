package 高并发.Java高并发编程详解多线程架构设计.synchronizedTest;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: TicketWindow
 * @description: 模拟营业大厅叫号程序 // synchronized
 * @author: lov.moran
 * @date 2020-09-16 15:45
 */
public class TicketWindow {

    public static int count = 0;

    public final static int max = 500;

    public final static Object mutex = new Object();

    public void fun() {
        synchronized (mutex) {
            while (count < max) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + ": I am deal with No." + count++ + ".");
            }
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow();
        for (int i = 0; i < 8; i++) {
            Thread thread = new Thread(ticketWindow::fun);
            thread.setName("Thread-AAAA" + i);
//            thread.setDaemon(true);
            thread.start();
        }
    }
}

