package org.user.stream;

import org.user.impl.MyArrayList;
import org.user.list.MyList;
import org.user.optional.MyOptional;
import org.user.stream.functionalinterfaces.MyBinaryOperator;
import org.user.stream.functionalinterfaces.MyComparator;
import org.user.stream.functionalinterfaces.MyConsumer;
import org.user.stream.functionalinterfaces.MyFunction;
import org.user.stream.functionalinterfaces.MyPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MyStream<T> {
    private final MyList<T> elements;
    private boolean parallel = false;

    public MyStream(MyList<T> elements) {
        this.elements = elements;
    }

    public MyArrayList<T> toList() {
        return new MyArrayList<>(elements);
    }


    public MyStream<T> filter(MyPredicate<T> predicate) {
        MyList<T> filteredElements = new MyArrayList<>();
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (T element : elements) {
                Future<Boolean> submit = executorService.submit(() -> predicate.test(element));
                try {
                    if (submit.get()) {
                        filteredElements.add(element);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            for (T element : elements) {
                if (predicate.test(element)) {
                    filteredElements.add(element);
                }
            }
        }
        return new MyStream<>(filteredElements);
    }

    public <R> MyStream<R> map(MyFunction<T, R> mapper) {
        MyList<R> mappedElements = new MyArrayList<>();
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            MyList<Future<R>> futures = new MyArrayList<>();
            for (T element : elements) {
                Future<R> future = executorService.submit(() -> mapper.apply(element));
                futures.add(future);
            }
            for (Future<R> future : futures) {
                try {
                    R result = future.get();
                    mappedElements.add(result);
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            executorService.shutdown();
        } else {
            for (T element : elements) {
                R appliedEl = mapper.apply(element);
                mappedElements.add(appliedEl);
            }
        }
        return new MyStream<>(mappedElements);
    }

    public void forEach(MyConsumer<T> action) {
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            List<Runnable> tasks = new ArrayList<>();
            for (T element : elements) {
                tasks.add(() -> action.accept(element));
            }
            for (Runnable task : tasks) {
                executorService.execute(task);
            }
            executorService.shutdown();
        } else {
            for (T element : elements) {
                action.accept(element);
            }
        }
    }

    public long count() {
        return elements.size();
    }

    public MyStream<T> distinct() {
        MyList<T> uniqueElements = new MyArrayList<>();

        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (T element : elements) {
                try {
                    Boolean contains = executorService.submit(() -> uniqueElements.contains(element)).get();
                    if (!contains) {
                        uniqueElements.add(element);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            for (int i = 0; i < elements.size(); i++) {
                T element = elements.get(i);
                if (!uniqueElements.contains(element)) {
                    uniqueElements.add(element);
                }
            }
        }
        return new MyStream<>(uniqueElements);
    }

    public MyStream<T> limit(int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException("Forbidden negative size " + maxSize);
        }
        MyList<T> limitedElements = new MyArrayList<>();
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            try {
                for (T element : elements) {
                    Boolean aBoolean = executorService.submit(() -> limitedElements.size() < maxSize).get();
                    if (aBoolean) {
                        limitedElements.add(element);
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else {
            for (int i = 0; i < elements.size(); i++) {
                if (limitedElements.size() < maxSize) {
                    limitedElements.add(elements.get(i));
                }
            }
        }
        return new MyStream<>(limitedElements);
    }

    public MyStream<T> skip(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Forbidden negative number " + n);
        }
        MyList<T> skippedElementsList = new MyArrayList<>();

        if (n > 0 && n < elements.size()) {
            for (int i = n; i < elements.size(); i++) {
                skippedElementsList.add(elements.get(i));
            }
        }
        return new MyStream<>(skippedElementsList);
    }

    public MyStream<T> sorted(MyComparator<T> comparator) {
        MyMergeSort<T> mergeSort = new MyMergeSort<>();
        return new MyStream<>(mergeSort.sort(elements, comparator));
    }


    public MyOptional<T> findFirst() {
        return MyOptional.ofNullable(elements.get(0));
    }

    public MyStream<T> compare(MyComparator<T> comparator) {
        MyMergeSort<T> mergeSort = new MyMergeSort<>();
        MyList<T> comparedElements = mergeSort.sort(elements, comparator);
        return new MyStream<>(comparedElements);
    }

    public boolean allMatch(MyPredicate<T> predicate) {
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (T element : elements) {
                try {
                    Boolean res = executorService.submit(() -> predicate.test(element)).get();
                    if (!res) {
                        return false;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            for (T element : elements) {
                if (!predicate.test(element)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean anyMatch(MyPredicate<T> predicate) {
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (T element : elements) {
                try {
                    Boolean res = executorService.submit(() -> predicate.test(element)).get();
                    if (res) {
                        return true;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            for (int i = 0; i < elements.size(); i++) {
                if (predicate.test(elements.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean noneMatch(MyPredicate<T> predicate) {
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (T element : elements) {
                try {
                    Boolean res = executorService.submit(() -> predicate.test(element)).get();
                    if (res) {
                        return false;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            for (T element : elements) {
                if (predicate.test(element)) {
                    return false;
                }
            }
        }
        return true;
    }

    public T reduce(T identity, MyBinaryOperator<T> accumulator) {
        T result = identity;
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (T element : elements) {
                T r = result;
                try {
                    result = executorService.submit(() -> accumulator.apply(r, element)).get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            for (T element : elements) {
                result = accumulator.apply(result, element);
            }
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
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            MyList<Runnable> tasks = new MyArrayList<>();
            for (T element : elements) {
                tasks.add(() -> action.accept(element));
            }
            for (Runnable task : tasks) {
                executorService.execute(task);
            }
            executorService.shutdown();
        } else {
            for (T element : elements) {
                action.accept(element);
            }
        }
        return this;
    }

    public <R> MyStream<R> flatMap(MyFunction<T, MyStream<R>> mapper) {
        MyList<R> values = new MyArrayList<>();
        if (parallel) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (T element : elements) {
                Future<MyStream<R>> submittedFutures = executorService.submit(() -> mapper.apply(element));
                try {
                    MyStream<R> myStream = submittedFutures.get();
                    for (R res : myStream.elements) {
                        values.add(res);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            for (T element : elements) {
                MyStream<R> apply = mapper.apply(element);
                for (R r : apply.elements) {
                    values.add(r);
                }
            }
        }
        return new MyStream<>(values);
    }

    public MyStream<T> parallelStream() {
        parallel = true;
        return this;
    }
}
