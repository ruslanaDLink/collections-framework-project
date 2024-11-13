package org.user.thread.fork;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10_000;
    private final int[] array;
    private final int start;
    private final int end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, middle);
            SumTask rightTask = new SumTask(array, middle, end);

            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinExample {
    public static void main(String[] args) {
        int[] array = new int[1_000_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length);

        long result = pool.invoke(task);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Suma elementów tablicy: " + result);
        System.out.println("Czas wykonania: " + duration);
    }
}