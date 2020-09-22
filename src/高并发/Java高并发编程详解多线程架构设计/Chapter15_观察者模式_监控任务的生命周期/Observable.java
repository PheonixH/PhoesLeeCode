package 高并发.Java高并发编程详解多线程架构设计.Chapter15_观察者模式_监控任务的生命周期;

/**
 * @program: PhoesLeeCode
 * @className: Observable
 * @description: 多线程架构模式.观察者模式-监控任务的生命周期
 * @author: lov.moran
 * @date 2020-09-22 11:40
 */
public interface Observable {

    // 任务生命周期枚举类型
    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }

    // 获取当前任务的生命周期状态
    Cycle getCycle();

    // 定义启动线程的方法， 主要是为了屏蔽Thread的其他方法
    void start();

    // 定义线程的打断方法， 作用同start()
    void interrupt();
}
