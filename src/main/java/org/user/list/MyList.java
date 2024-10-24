package org.user.list;

import org.user.impl.MyArrayList;
import org.user.stream.MyStream;

import java.util.Iterator;
import java.util.ListIterator;

public interface MyList<T> extends Iterable<T> {

    boolean add(T element);

    void add(T element, int index);

    @SafeVarargs
    static <T> MyList<T> of(T... elements) {
        if (elements == null) {
            throw new NullPointerException();
        }
        return new MyArrayList<>(elements);
    }

    T remove(int index);

    boolean remove(T element);

    T get(int index);

    T set(int index, T element);

    boolean isEmpty();

    boolean contains(T element);

    int indexOf(T element);

    int lastIndexOf(T element);

    int size();

    Iterator<T> iterator();

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int index);

    MyStream<T> stream();
}
