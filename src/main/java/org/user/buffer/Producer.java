package org.user.buffer;

import java.util.Random;

public class Producer extends Thread {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            int item = random.nextInt(10000);
            try {
                buffer.produce(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
