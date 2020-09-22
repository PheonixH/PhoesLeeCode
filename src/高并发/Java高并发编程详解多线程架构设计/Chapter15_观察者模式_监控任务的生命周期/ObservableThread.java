package 高并发.Java高并发编程详解多线程架构设计.Chapter15_观察者模式_监控任务的生命周期;

import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: ObservableThread
 * @description:
 * @author: lov.moran
 * @date 2020-09-22 15:37
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    private Cycle cycle;

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("The Task is Required!");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<T>(), task);
    }

    @Override
    //重写run()方法并将他改为final!  不允许子类在对其进行修改。
    public final void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception exception) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case RUNNING -> {
                    this.lifecycle.onRunning(currentThread());
                    break;
                }
                case DONE -> {
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                }
                case STARTED -> {
                    this.lifecycle.onStart(currentThread());
                    break;
                }
                case ERROR -> {
                    this.lifecycle.onError(currentThread(), exception);
                }
            }
        } catch (Exception e) {
            if (cycle == Cycle.ERROR) {
                throw e;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    //测试
    public static void main(String[] args) {
        // 普通的Thread 空的EmptyLifecycle不关新任务生命周期的每一个阶段
        Observable observable = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish done!");
            return null;
        });
        observable.start();

        // 使任务生命周期每一个阶段：以下只实现了关心结果都有所返回
        final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<>(){
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The Result is: " + result);
                super.onFinish(thread, result);
            }
        };

        Observable observable1 = new ObservableThread<>(lifecycle, ()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish done!");
            return "Hello Observe!";
        });
        observable1.start();
    }
}
