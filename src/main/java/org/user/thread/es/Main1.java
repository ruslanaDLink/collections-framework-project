package org.user.thread.es;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 8; i++) {
            int id = i;
            Runnable task = () -> System.out.println(id + ": " + Thread.currentThread().getName());
            executorService.execute(task);
        }
        executorService.shutdown();
    }
}
