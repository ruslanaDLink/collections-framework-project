package org.user.stream;

import org.user.impl.MyArrayList;
import org.user.list.MyList;
import org.user.optional.MyOptional;
import org.user.stream.functionalinterfaces.MyBinaryOperator;
import org.user.stream.functionalinterfaces.MyComparator;
import org.user.stream.functionalinterfaces.MyConsumer;
import org.user.stream.functionalinterfaces.MyFunction;
import org.user.stream.functionalinterfaces.MyPredicate;


public class MyStream<T> {
    private final MyList<T> elements;

    public MyStream(MyList<T> elements) {
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
        if (maxSize < 0) {
            throw new IllegalArgumentException("Forbidden negative size " + maxSize);
        }
        MyArrayList<T> limitedElements = new MyArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            if (limitedElements.size() < maxSize) {
                limitedElements.add(elements.get(i));
            }
        }
        return new MyStream<>(limitedElements);
    }

    public MyStream<T> skip(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Forbidden negative number " + n);
        }
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
        return MyOptional.ofNullable(elements.get(0));
    }

    public MyStream<T> compare(MyComparator<T> comparator) {
        MyBubbleSort<T> bubbleSort = new MyBubbleSort<>();
        MyList<T> comparedElements = bubbleSort.sort(elements, comparator);
        return new MyStream<>(comparedElements);
    }

    public boolean allMatch(MyPredicate<T> predicate) {
        for (T element : elements) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    public boolean anyMatch(MyPredicate<T> predicate) {
        for (int i = 0; i < elements.size(); i++) {
            if (predicate.test(elements.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean noneMatch(MyPredicate<T> predicate) {
        for (T element : elements) {
            if (predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    public T reduce(T identity, MyBinaryOperator<T> accumulator) {
        T result = identity;
        for (T element : elements) {
            result = accumulator.apply(result, element);
        }
        return result;
    }

    public MyOptional<T> min(MyComparator<T> comparator) {
        if (elements.isEmpty()) {
            return MyOptional.empty();
        }
        T minValue = elements.get(0);

        for (T element : elements) {
            if (comparator.compare(element, minValue) <= 0) {
                minValue = element;
            }
        }
        return MyOptional.of(minValue);
    }

    public MyOptional<T> max(MyComparator<T> comparator) {
        if (elements.isEmpty()) {
            return MyOptional.empty();
        }
        T maxValue = elements.get(0);

        for (T element : elements) {
            if (comparator.compare(element, maxValue) > 0) {
                maxValue = element;
            }
        }
        return MyOptional.of(maxValue);
    }

    public MyStream<T> peek(MyConsumer<T> action) {
        for (T element : elements) {
            action.accept(element);
        }
        return this;
    }

    public <R> MyStream<R> flatMap(MyFunction<T, MyStream<R>> mapper) {
        MyList<R> values = new MyArrayList<>();
        for (T element : elements) {
            MyStream<R> apply = mapper.apply(element);
            for (R r : apply.elements) {
                values.add(r);
            }
        }
        return new MyStream<>(values);
    }
}
