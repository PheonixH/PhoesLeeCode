package 高并发.Java高并发编程详解多线程架构设计.Chapter5_线程间的通信;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: EventQueue
 * @description: 待处理事件队列 -- 单一生产者&&单一消费者
 * @author: lov.moran
 * @date 2020-09-17 15:51
 */
public class EventQueue {

    private final int max;

    static class Event {

    }

    private final LinkedList<Event> events = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (events) {
            if (events.size() > max) {
                try {
                    System.out.println("The event link is full.");
                    events.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("The event is submitted!");
            events.addLast(event);
            events.notify();
        }
    }

    public Event take() {
        synchronized (events) {
            if (events.size() <= 0) {
                try {
                    System.out.println("The event link is empty.");
                    events.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = events.removeFirst();
            events.notify();
            System.out.println("The event is handled!");
            return event;
        }
    }

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }
}
