package org.user.impl;

import org.user.list.MyList;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

public class MyArrayList implements MyList {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects;

    private int modCount;
    private int size;

    public MyArrayList() {
        this.objects = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(Object[] objects) {
        this.objects = objects;
        this.size = objects.length;
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.objects = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
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
    public boolean add(Object element) {
        resizeList();
        objects[size++] = element;
        return true;
    }

    @Override
    public void add(Object element, int index) {
        checkIndex(index);
        resizeList();
        System.arraycopy(objects, index, objects, index + 1, size);
        objects[index] = element;
        size++;
        modCount++;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Object objToRemove = objects[index];
        for (int i = index; i < size - 1; i++) {
            objects[i] = objects[i + 1];
        }
        objects[size - 1] = null;
        size--;
        return objToRemove;
    }

    @Override
    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(element)) {
                int position = size - i - 1;
                System.arraycopy(objects, i + 1, objects, i, position);
                objects[--size] = null;
                modCount++;
            }
        }
        return !contains(element);
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return objects[index];
    }

    @Override
    public Object set(int index, Object element) {
        checkIndex(index);
        Object object = objects[index];
        objects[index] = element;
        modCount++;
        return object;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        int index = -1;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null && objects[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void addFirst(Object element) {
        add(element, 0);
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public Object getFirst() {
        return objects[0];
    }

    @Override
    public Object getLast() {
        return objects[size - 1];
    }

    @Override
    public Object removeFirst() {
        return remove(0);
    }

    @Override
    public Object removeLast() {
        return remove(size - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<Object> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        checkIndex(index);
        return new MyListIterator(index);
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private class MyIterator implements Iterator<Object> {
        private int cursor;
        int iteratorModCount = modCount;

        @Override
        public boolean hasNext() {
            return size > cursor;
        }

        @Override
        public Object next() {
            if (modCount != iteratorModCount) {
                throw new ConcurrentModificationException();
            }
            return objects[cursor++];
        }
    }

    private class MyListIterator implements ListIterator<Object> {
        private int cursor;
        private int listModCount = modCount;
        private int lastIndex;

        MyListIterator(int cursor) {
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return size > cursor;
        }

        @Override
        public Object next() {
            if (modCount != listModCount) {
                throw new ConcurrentModificationException();
            }
            return objects[cursor++];
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public Object previous() {
            if (modCount != listModCount) {
                throw new ConcurrentModificationException();
            }
            checkIndex(cursor);
            lastIndex = --cursor;
            return objects[cursor];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (modCount != listModCount) {
                throw new ConcurrentModificationException();
            }
            MyArrayList.this.remove(lastIndex);
        }

        @Override
        public void set(Object o) {
            if (modCount != listModCount) {
                throw new ConcurrentModificationException();
            }
            MyArrayList.this.set(lastIndex, o);
        }

        @Override
        public void add(Object o) {
            if (modCount != listModCount) {
                throw new ConcurrentModificationException();
            }
            MyArrayList.this.add(o, cursor++);
        }
    }
}
