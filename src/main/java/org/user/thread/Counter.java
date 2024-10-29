package org.user.thread;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count = 0;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public void increment() {
        reentrantLock.lock();
        count++;
        reentrantLock.unlock();
    }

    public int getCount() {
        return count;
    }
}
