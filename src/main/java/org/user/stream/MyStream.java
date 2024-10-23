package org.user.stream;

import org.user.impl.MyArrayList;
import org.user.optional.MyOptional;
import org.user.stream.functionalinterfaces.MyComparator;
import org.user.stream.functionalinterfaces.MyConsumer;
import org.user.stream.functionalinterfaces.MyFunction;
import org.user.stream.functionalinterfaces.MyPredicate;

import java.util.ArrayList;
import java.util.Comparator;


public class MyStream<T> {
    private final MyArrayList<T> elements;

    public MyStream(MyArrayList<T> elements) {
        this.elements = elements;
    }

    public MyArrayList<T> toList() {
        return new MyArrayList<>(elements);
    }


    public MyStream<T> filter(MyPredicate<T> predicate) {
        MyArrayList<T> filteredElements = new MyArrayList<>();
        for (T element : elements) {
            if (predicate.test(element)) {
                filteredElements.add(element);
            }
        }
        return new MyStream<>(filteredElements);
    }

    public <R> MyStream<R> map(MyFunction<T, R> mapper) {
        MyArrayList<R> mappedElements = new MyArrayList<>();
        for (T element : elements) {
            R appliedEl = mapper.apply(element);
            mappedElements.add(appliedEl);
        }
        return new MyStream<>(mappedElements);
    }

    public void forEach(MyConsumer<T> action) {
        for (T element : elements) {
            action.accept(element);
        }
    }

    public long count() {
        return elements.size();
    }

    public MyStream<T> distinct() {
        MyArrayList<T> uniqueElements = new MyArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            T element = elements.get(i);
            if (!uniqueElements.contains(element)) {
                uniqueElements.add(element);
            }
        }
        return new MyStream<>(uniqueElements);
    }

    public MyStream<T> limit(int maxSize) {
        MyArrayList<T> limitedElements = new MyArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            if (limitedElements.size() < maxSize) {
                limitedElements.add(elements.get(i));
            }
        }
        return new MyStream<>(limitedElements);
    }

    public MyStream<T> skip(int n) {
        MyArrayList<T> skippedElementsList = new MyArrayList<>();

        if (n > 0 && n < elements.size()) {
            for (int i = n; i < elements.size(); i++) {
                skippedElementsList.add(elements.get(i));
            }
        }
        return new MyStream<>(skippedElementsList);
    }

    public MyStream<T> sorted(MyComparator<T> comparator) {
        MyBubbleSort<T> bubbleSort = new MyBubbleSort<>();
        return new MyStream<>(bubbleSort.sort(elements, comparator));
    }


    public MyOptional<T> findFirst() {
        return MyOptional.offNullable(elements.get(0));
    }

    public MyStream<T> compare(MyComparator<T> comparator) {
        MyBubbleSort<T> bubbleSort = new MyBubbleSort<>();
        MyArrayList<T> comparedElements = bubbleSort.sort(elements, comparator);
        return new MyStream<>(comparedElements);
    }

}
