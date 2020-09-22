package 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool;

/**
 * @program: PhoesLeeCode
 * @description: 决定当Queue中的runnable 到达上限的时候用什么方式告知提交者
 * @author: Feng.H
 * @create: 2020-09-19 20:33
 **/
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    // 该拒绝策略会直接将任务丢弃
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // do nothing
        }
    }

    // 该拒绝策略会向提交者抛出异常
    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("the runnable " + runnable + " will be abort.");
        }
    }

    //该拒绝策略会使任务在提交者所在的线程中执行任务
    class RunnableDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }

}
