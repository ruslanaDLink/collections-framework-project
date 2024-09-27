package org.user.list;

import java.util.Iterator;
import java.util.ListIterator;

public interface MyList<E> {

    boolean add(E element);

    void add(E element, int index);

    E remove(int index);

    boolean remove(E element);

    E get(int index);

    E set(int index, E element);

    boolean isEmpty();

    boolean contains(E element);

    int indexOf(E element);

    int lastIndexOf(E element);

    void addFirst(E element);

    void addLast(E element);

    E getFirst();

    E getLast();

    E removeFirst();

    E removeLast();

    int size();

    Iterator<E> iterator();

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int index);
}
