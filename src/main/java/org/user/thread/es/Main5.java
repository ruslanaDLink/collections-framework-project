package org.user.thread.es;

//Napisz program, który uruchamia listę zadań Callable (np. 10 zadań),
// każde zwracające swoje ID po losowym czasie. Skorzystaj z ExecutorService i metody invokeAll(),
// aby uruchomić wszystkie zadania i pobrać wyniki.
//
//        Podpowiedź:
//        invokeAll() zwraca listę Future.
//        Zadania będą wykonywane równolegle.


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main5 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            callables.add(() -> {
                UUID uuid = UUID.randomUUID();
                int seconds = ThreadLocalRandom.current().nextInt(1, 3);
                try {
                    TimeUnit.SECONDS.sleep(seconds);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return Thread.currentThread().getName() + ": " + uuid;
            });
        }
        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            for (Future<String> future : futures) {
                String res = future.get();
                System.out.println(res);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
