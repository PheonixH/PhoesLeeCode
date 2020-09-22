package 高并发.Java高并发编程详解多线程架构设计.Chapter15_观察者模式_监控任务的生命周期;

/**
 * @program: PhoesLeeCode
 * @className: Task
 * @description: Task 函数接口定义
 * @author: lov.moran
 * @date 2020-09-22 15:18
 */
@FunctionalInterface
public interface Task<T> {

    T call();
}
