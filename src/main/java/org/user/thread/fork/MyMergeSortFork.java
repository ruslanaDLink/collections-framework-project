package org.user.thread.fork;

import org.user.impl.MyArrayList;
import org.user.list.MyList;
import org.user.stream.functionalinterfaces.MyComparator;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MyMergeSortFork<T> extends RecursiveTask<MyList<T>> {
    private final MyList<T> myList;
    private final MyComparator<T> comparator;

    public MyMergeSortFork(MyList<T> myList, MyComparator<T> comparator) {
        this.myList = myList;
        this.comparator = comparator;
    }

    @Override
    protected MyList<T> compute() {
        if (myList.size() <= 1) {
            return myList;
        }
        MyList<T> firstPart = new MyArrayList<>();
        MyList<T> lastPart = new MyArrayList<>();

        int midPoint = myList.size() / 2;

        for (int i = 0; i < midPoint; i++) {
            firstPart.add(myList.get(i));
        }
        for (int i = midPoint; i < myList.size(); i++) {
            lastPart.add(myList.get(i));
        }
        MyMergeSortFork<T> leftSortTask = new MyMergeSortFork<>(firstPart, comparator);
        MyMergeSortFork<T> rightSortTask = new MyMergeSortFork<>(lastPart, comparator);
        leftSortTask.fork();
        MyList<T> rightResult = rightSortTask.compute();
        MyList<T> leftResult = leftSortTask.join();

        return merge(leftResult, rightResult, comparator);
    }


    private MyList<T> merge(MyList<T> first, MyList<T> last, MyComparator<T> comparator) {
        MyList<T> sortedList = new MyArrayList<>();
        int left = 0;
        int right = 0;

        while (left < first.size() && right < last.size()) {
            if (comparator.compare(first.get(left), last.get(right)) <= 0) {
                sortedList.add(first.get(left));
                left++;
            } else {
                sortedList.add(last.get(right));
                right++;
            }
        }
        while (left < first.size()) {
            sortedList.add(first.get(left));
            left++;
        }
        while (right < last.size()) {
            sortedList.add(last.get(right));
            right++;
        }
        return sortedList;
    }

    public static void main(String[] args) {
        MyArrayList<Integer> elements = new MyArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            elements.add(i);
        }
        MyMergeSortFork<Integer> myMergeSortSequence = new MyMergeSortFork<>(elements, Integer::compareTo);
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        MyList<Integer> sortedElements = pool.invoke(myMergeSortSequence);

//        for (Integer sortedElement : sortedElements) {
//            System.out.println(sortedElement);
//        }

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Czas wykonania: " + duration);
    }


}
