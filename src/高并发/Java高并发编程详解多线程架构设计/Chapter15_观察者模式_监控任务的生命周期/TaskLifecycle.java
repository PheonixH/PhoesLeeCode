package 高并发.Java高并发编程详解多线程架构设计.Chapter15_观察者模式_监控任务的生命周期;

/**
 * @program: PhoesLeeCode
 * @className: TaskLifecycle
 * @description:
 * @author: lov.moran
 * @date 2020-09-22 15:12
 */
public interface TaskLifecycle<T> {

    //任务启动时候会触发
    void onStart(Thread thread);

    //任务运行时会触发
    void onRunning(Thread thread);

    //任务运行结束会触发
    void onFinish(Thread thread, T result);

    //任务执行报错时候会触发
    void onError(Thread thread, Exception exception);

    //任务生命周期接口空实现
    class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception exception) {

        }
    }
}
