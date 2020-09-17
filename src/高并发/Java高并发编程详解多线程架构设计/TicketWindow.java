package 高并发.Java高并发编程详解多线程架构设计;

/**
 * @program: PhoesLeeCode
 * @className: TicketWindow
 * @description: 模拟营业大厅叫号程序
 * @author: lov.moran
 * @date 2020-09-16 15:45
 */
public class TicketWindow implements Runnable {

    public int count = 0;

    public void deal(String name) {
        System.out.println(name + ": I am deal with No." + count + ".");
    }


    @Override
    public synchronized void run() {
        for (; count <= 50; count++) {
            deal(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            Thread r = new Thread(new TicketWindow(), "EEE" + i);
            //在线程启动之前可以调用setName为其改名字  启动之后便不可以修改了
            r.setName("HHH" + i);
            r.start();
        }
    }
}

