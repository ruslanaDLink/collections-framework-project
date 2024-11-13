package org.user.thread.fork;

public class SequentialSumExample {
    public static void main(String[] args) {
        int[] array = new int[1_000_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int value : array) {
            sum += value;
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Suma elementów tablicy: " + sum);
        System.out.println("Czas wykonania: " + duration);
    }
}
