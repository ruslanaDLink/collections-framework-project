package org.user.buffer;

import java.util.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        Queue<Integer> queue = buffer.getQueue();
        System.out.println(queue.size());
    }
}
