package org.user.thread;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Counter counter = new Counter();
        MyThread myThread1 = new MyThread(counter);
        myThread1.start();
        MyRunnable myRunnable = new MyRunnable(counter);
        Thread myThread2 = new Thread(myRunnable);
        myThread2.start();


//        Thread myThread3 = new Thread(
//                () -> {
//                    for (int i = 0; i < 100; i++) {
//                        System.out.println("Thread in lambda: " + Thread.currentThread().getName());
//                    }
//                }
//        );
//        myThread3.start();
        myThread1.join();
        myThread2.join();
//        myThread3.join();
        System.out.println("Zakonczono prace watkow");
        System.out.println(counter.getCount());
    }
}
