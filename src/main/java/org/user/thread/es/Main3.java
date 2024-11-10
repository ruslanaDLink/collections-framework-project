package org.user.thread.es;

//Zaimplementuj program, który wykorzystuje ScheduledExecutorService do wykonywania zadania co 2 sekundy, 5 razy.
//
//        Podpowiedź:
//        Użyj ScheduledExecutorService i metody scheduleAtFixedRate.
//        Skonfiguruj zadanie do anulowania po 5 wykonaniach.

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main3 {
    private static AtomicInteger count = new AtomicInteger(0);
    private static ScheduledExecutorService scheduledExecutorService;

    public static void main(String[] args) {
        Runnable task = Main3::completeTask;
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
    }

    private static void completeTask() {
        count.incrementAndGet();
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " completed the task " + count.intValue() + ".");
        if (count.intValue() == 5) {
            scheduledExecutorService.shutdown();
        }
    }
}
