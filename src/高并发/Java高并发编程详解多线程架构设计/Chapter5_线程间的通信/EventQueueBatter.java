package 高并发.Java高并发编程详解多线程架构设计.Chapter5_线程间的通信;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @program: PhoesLeeCode
 * @className: EventQueueBatter
 * @description: 多生产者&&多消费者 --- if改为while
 * @author: lov.moran
 * @date 2020-09-17 16:16
 */
public class EventQueueBatter {

    private final int max;

    static class Event {

    }

    private final LinkedList<EventQueue.Event> events = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueueBatter() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueueBatter(int max) {
        this.max = max;
    }

    public void offer(EventQueue.Event event) {
        synchronized (events) {
            while (events.size() > max) {
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

    public EventQueue.Event take() {
        synchronized (events) {
            while (events.size() <= 0) {
                try {
                    System.out.println("The event link is empty.");
                    events.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            EventQueue.Event event = events.removeFirst();
            events.notifyAll();
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
