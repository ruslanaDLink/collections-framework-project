package org.user.thread.homework;

public class DiningPhilosophers {
    public static void main(String[] args) {
        Fork firstFork = new Fork();
        Fork secondFork = new Fork();
        Fork thirdFork = new Fork();
        Fork fourthFork = new Fork();
        Fork fifthFork = new Fork();

        Philosopher plato = new Philosopher(1, fifthFork, secondFork);
        Philosopher konfuzius = new Philosopher(2, secondFork, thirdFork);
        Philosopher socrates = new Philosopher(3, thirdFork, fourthFork);
        Philosopher voltaire = new Philosopher(4, fourthFork, fifthFork);
        Philosopher descartes = new Philosopher(5, fifthFork, firstFork);

        plato.start();
        konfuzius.start();
        socrates.start();
        voltaire.start();
        descartes.start();
    }
}


