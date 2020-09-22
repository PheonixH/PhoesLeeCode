package 高并发.Java高并发编程详解多线程架构设计.Chapter8_ThreadPool;

/**
 * @program: PhoesLeeCode
 * @description: 自定义异常，用于通知任务提交者：队列已经无法接受新任务
 * @author: Feng.H
 * @create: 2020-09-19 20:39
 **/
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String s) {
        super(s);
    }
}
