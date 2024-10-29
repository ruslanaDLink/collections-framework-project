package org.user.buffer;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 100;

    public synchronized void produce(Integer item) throws InterruptedException {
        while (queue.size() == CAPACITY){
            System.out.println(Thread.currentThread().getState());
            wait();
        }
        System.out.println("Wyprodukowano przedmiot: " + item);
        queue.add(item);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Integer integer = queue.poll();
        System.out.println("Skonsumowano przedmiot: " + integer);
        notifyAll();
    }

    public Queue<Integer> getQueue() {
        return queue;
    }
}
