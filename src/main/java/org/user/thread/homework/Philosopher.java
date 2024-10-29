package org.user.thread.homework;

public class Philosopher extends Thread {
    private int id;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void think() {
        try {
            System.out.println("Philosopher " + id + ": THINKING..");
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void pickUpForks() throws InterruptedException {
        if (id % 2 == 0) {
            synchronized (leftFork) {
                System.out.println("Philosopher " + id + ": picks up left fork");
                leftFork.pickUp();
                Thread.sleep(10);
                synchronized (rightFork) {
                    System.out.println("Philosopher " + id + ": picks up right fork");
                    rightFork.pickUp();
                }
            }
        } else {
            synchronized (rightFork) {
                System.out.println("Philosopher " + id + ": picks up right fork");
                rightFork.pickUp();
                Thread.sleep(10);
                synchronized (leftFork) {
                    System.out.println("Philosopher " + id + ": picks up left fork");
                    leftFork.pickUp();
                }
            }
        }
    }

    public void eat() {
        try {
            System.out.println("Philosopher " + id + ": EATING..");
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void pickDownForks() {
        System.out.println("Philosopher " + id + ": picks down left fork");
        leftFork.pickDown();
        System.out.println("Philosopher " + id + ": picks down right fork");
        rightFork.pickDown();
    }

    @Override
    public void run() {
        while (true) {
            think();
            try {
                pickUpForks();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            eat();
            pickDownForks();
        }
    }
}
