package org.user.thread;

public class MyRunnable implements Runnable {
    private final Counter counter;

    public MyRunnable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000000; i++) {
//            System.out.println("MyRunnable: " + Thread.currentThread().getName());
            counter.increment();
        }
    }
}
