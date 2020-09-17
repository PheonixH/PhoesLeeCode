package 高并发.Java高并发编程详解多线程架构设计.ThreadApi;

/**
 * @program: PhoesLeeCode
 * @className: ThreadApiSetPriority
 * @description: 设置线程优先级
 * @author: lov.moran
 * @date 2020-09-17 10:34
 */
public class ThreadApiSetPriority {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("我的优先级低，这是第 "+i+" 次操作。");
            }
        });
        thread1.setPriority(1);
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("我的优先级高，这是第 "+i+" 次操作。");
            }
        });
        thread2.setPriority(10);
        thread1.start();
        thread2.start();

        ThreadGroup threadGroup = new ThreadGroup("最大优先级5");
        threadGroup.setMaxPriority(5);
        Thread thread3 = new Thread(threadGroup, "最大优先级7");
        thread3.setPriority(7);
        System.out.println(thread3.getPriority()); // 输出5
    }
}
