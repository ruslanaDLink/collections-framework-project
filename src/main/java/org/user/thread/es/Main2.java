package org.user.thread.es;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę");
        int number = scanner.nextInt();

        Callable<Long> callable = () -> {
            long result = 1;
            for (int i = 2; i <= number; i++) {
                result *= i;
            }
            return result;
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Long> future = executorService.submit(callable);
        while (!future.isDone()) {
            System.out.println("Sprawdzenie czy Future isDone()");
        }
        try {
            Long aLong = future.get();
            System.out.println(aLong);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

    }
}
