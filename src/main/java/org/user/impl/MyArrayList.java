package org.user.impl;

import org.user.list.MyList;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T>, Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] objects;

    private int modCount;
    private int size;

    public MyArrayList() {
        this.objects = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(T[] objects) {
        this.objects = objects;
        this.size = objects.length;
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.objects = (T[]) new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    public MyArrayList(MyList<T> elements) {
        this.objects = (T[]) new Object[elements.size()];
        this.size = 0;
        for (int i = 0; i < elements.size(); i++) {
            add(elements.get(i));
        }
    }

    private void resizeList() {
        int length = objects.length;
        if (size == length) {
            int modifiedCapacity = objects.length * 2 + 1;
            objects = Arrays.copyOf(objects, modifiedCapacity);
            modCount++;
        }
    }

    @Override
    public boolean add(T element) {
        resizeList();
        objects[size++] = element;
        return true;
    }

    @Override
    public void add(T element, int index) {
        checkIndex(index);
        resizeList();
        System.arraycopy(objects, index, objects, index + 1, size);
        objects[index] = element;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T objToRemove = objects[index];
        for (int i = index; i < size - 1; i++) {
            objects[i] = objects[i + 1];
        }
        objects[size - 1] = null;
        size--;
        modCount++;
        return objToRemove;
    }

    @Override
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(element)) {
                int position = size - i - 1;
                System.arraycopy(objects, i + 1, objects, i, position);
                objects[--size] = null;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return objects[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T object = objects[index];
        objects[index] = element;
        modCount++;
        return object;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        int index = -1;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null && objects[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        checkIndex(index);
        return new MyListIterator(index);
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public String toString() {
        Object[] objs = new Object[size];
        System.arraycopy(objects, 0, objs, 0, size);
        return "MyArrayList{" +
                "objects=" + Arrays.toString(objs) +
                '}';
    }

    private class MyIterator implements Iterator<T> {
        private int index = 0;
        private int iteratorModCount = modCount;

        @Override
        public boolean hasNext() {
            return size > index;
        }

        @Override
        public T next() {
            checkForModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return objects[index++];
        }

        @Override
        public void remove() {
            checkForModification();
            MyArrayList.this.remove(--index);
            iteratorModCount = modCount;
        }

        private void checkForModification() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class MyListIterator implements ListIterator<T> {
        private int index;
        private int lastIndex;

        MyListIterator(int cursor) {
            this.index = cursor;
        }

        @Override
        public boolean hasNext() {
            return size > index;
        }

        @Override
        public T next() {
            return objects[index++];
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            checkIndex(index);
            lastIndex = --index;
            return objects[index];
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(lastIndex);
            lastIndex--;
        }

        @Override
        public void set(T o) {
            MyArrayList.this.set(lastIndex, o);
        }

        @Override
        public void add(T o) {
            MyArrayList.this.add(o, index++);
        }
    }
}
