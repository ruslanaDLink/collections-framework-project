package org.user.list;

import java.util.Iterator;
import java.util.ListIterator;

public interface MyList<T> extends Iterable<T>{

    boolean add(T element);

    void add(T element, int index);

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
}
