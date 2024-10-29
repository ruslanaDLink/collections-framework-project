package org.user.thread.homework;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final ReentrantLock lock = new ReentrantLock();

    public void pickUp() {
        lock.lock();
    }

    public void pickDown() {
        lock.unlock();
    }
}
