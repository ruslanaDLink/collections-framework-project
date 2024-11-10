package org.user.thread.es;

import org.user.impl.MyArrayList;
import org.user.list.MyList;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//Utwórz program, który uruchamia 3 zadania Callable, każde zwracające losową liczbę po losowym czasie (np. od 1 do 3 sekund).
// Skorzystaj z ExecutorService z pulą 3 wątków. Wyświetl wyniki po zakończeniu wszystkich zadań.
//
//        Podpowiedź:
//        Użyj ThreadLocalRandom do generowania losowych liczb i opóźnień.
//        Przechowuj Future dla każdego zadania i użyj get() do pobrania wyników.
public class Main4 {

    public static void main(String[] args) {
        Callable<Integer> task;
        MyList<Future<Integer>> futures = new MyArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            task = () -> {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int seconds = random.nextInt(1, 3);
                int number = random.nextInt(0, 100);
                TimeUnit.SECONDS.sleep(seconds);
                return number;
            };
            Future<Integer> future = executorService.submit(task);
            futures.add(future);
        }
        for (Future<Integer> future : futures) {
            Integer result;
            try {
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            System.out.println(result);
        }
        executorService.shutdown();
    }
}
