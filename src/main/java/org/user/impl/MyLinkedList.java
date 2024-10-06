package org.user.impl;

import org.user.list.MyList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int MODIFICATION_COUNT;
    private int size = 0;

    public MyLinkedList() {
        firstNode = null;
        lastNode = null;
        MODIFICATION_COUNT = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private void checkValue(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean add(T element) {
        checkValue(element);
        boolean isAdded;
        Node<T> newNode = new Node<>(element);
        if (firstNode == null) {
            firstNode = newNode;
            lastNode = newNode;
        } else {
            lastNode.next = newNode;
            newNode.previous = lastNode;
            lastNode = newNode;
        }
        isAdded = true;
        size++;
        MODIFICATION_COUNT++;
        return isAdded;
    }

    @Override
    public void add(T element, int index) {
        checkIndex(index);
        checkValue(element);
        Node<T> nodeToAdd = new Node<>(element);
        Node<T> first = firstNode;
        Node<T> nodeFromIndex = null;

        for (int i = 0; i < index; i++) {
            first = first.next;
            nodeFromIndex = first;
        }
        if (index == 0) {
            if (firstNode == null) {    //add on the start
                firstNode = nodeToAdd;
                lastNode = nodeToAdd;
            }
        } else if (index == size) { //add on the end
            lastNode.next = nodeToAdd;
            nodeToAdd.previous = lastNode;
            lastNode = nodeToAdd;
        } else {
            if (nodeFromIndex != null && nodeFromIndex.previous != null) {
                Node<T> previous = nodeFromIndex.previous;

                previous.next = nodeToAdd;
                nodeToAdd.previous = previous;

                nodeToAdd.next = nodeFromIndex;
                nodeFromIndex.previous = nodeToAdd;
            }
        }
        size++;
        MODIFICATION_COUNT++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> nodeToRemove;

        if (index == 0) {
            nodeToRemove = firstNode;
            firstNode = firstNode.next;
            firstNode.previous = null;
        } else if (index == (size - 1)) {
            nodeToRemove = lastNode;
            lastNode = lastNode.previous;
            lastNode.next = null;
        } else {
            Node<T> first = firstNode;
            for (int i = 0; i < index; i++) {
                first = first.next;
            }
            nodeToRemove = first;
            first.previous.next = first.next;
        }
        size--;
        MODIFICATION_COUNT++;
        return nodeToRemove.item;
    }

    @Override
    public boolean remove(T element) {
        checkValue(element);
        boolean isRemoved = false;
        Node<T> first = firstNode;

        if (element.equals(firstNode.item)) {
            firstNode = firstNode.next;
            size--;
            isRemoved = true;
        } else if (element.equals(lastNode.item)) {
            while (first.next != lastNode) {
                first = first.next;
            }
            first.next = null;
            lastNode = first;
            size--;
            isRemoved = true;
        }
        while (first.next != null) {
            T data = first.next.item;
            if (element.equals(data)) {
                first.next = first.next.next;
                size--;
                isRemoved = true;
            }
        }
        MODIFICATION_COUNT++;
        return isRemoved;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> first = firstNode;
        for (int i = 0; i < index; i++) {
            first = first.next;
        }
        return first.item;
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        checkValue(element);
        Node<T> first = firstNode;
        for (int i = 0; i < index; i++) {
            first = first.next;
        }
        T value = first.item;
        first.item = element;
        MODIFICATION_COUNT++;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T element) {
        checkValue(element);
        boolean contains = false;

        Node<T> first = firstNode;

        while (first != null) {
            if (first.item.equals(element)) {
                contains = true;
                break;
            }
            first = first.next;
        }
        return contains;
    }

    @Override
    public int indexOf(T element) {
        int index = 0;
        int defaultValue = -1;
        if (element == null) {
            return defaultValue;
        }
        Node<T> first = firstNode;
        while (first != null) {
            if (element.equals(first.item)) {
                return index;
            }
            index++;
            first = first.next;
        }
        return defaultValue;
    }

    @Override
    public int lastIndexOf(T element) {
        int lastIndex = -1;
        int defaultValue = -1;
        if (element == null) {
            return defaultValue;
        }
        Node<T> first = firstNode;
        while (first != null) {
            if (element.equals(first.item)) {
                return lastIndex;
            }
            first = first.next;
            lastIndex++;
        }
        return defaultValue;
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
        return new MyListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new MyListIterator(index);
    }

    private static class Node<T> {
        T item;
        Node<T> previous;
        Node<T> next;

        public Node(T item) {
            this.item = item;
            this.previous = null;
            this.next = null;
        }
    }

    private class MyIterator implements Iterator<T> {
        private int index = 0;
        private int iteratorModCount = MODIFICATION_COUNT;

        @Override
        public boolean hasNext() {
            checkForConcurrencyModification();
            return firstNode.next != null;
        }

        @Override
        public T next() {
            checkForConcurrencyModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            firstNode = firstNode.next;
            Node<T> value = firstNode;
            index++;
            return value.item;
        }

        @Override
        public void remove() {
            checkForConcurrencyModification();
            MyLinkedList.this.remove(index);
            iteratorModCount++;
        }

        private void checkForConcurrencyModification() {
            if (iteratorModCount != MODIFICATION_COUNT) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class MyListIterator implements ListIterator<T> {
        private Node<T> nodeFromIndex;
        private int index;
        private int lastIndex;
        private int iteratorModCount = MODIFICATION_COUNT;

        public MyListIterator() {
            index = 0;
        }

        public MyListIterator(int index) {
            checkIndex(index);
            this.index = index;
            if (index > (size / 2)) {
                nodeFromIndex = lastNode;
                for (int i = size - 1; i > index; i--) {
                    nodeFromIndex = nodeFromIndex.previous;
                }
            } else if (index < (size / 2)) {
                nodeFromIndex = firstNode;
                for (int i = 0; i < index; i++) {
                    nodeFromIndex = nodeFromIndex.next;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return size > index;
        }

        @Override
        public T next() {
            checkForConcurrencyModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            nodeFromIndex = nodeFromIndex.next;
            lastIndex = index++;
            return nodeFromIndex.item;
        }

        @Override
        public boolean hasPrevious() {
            return nodeFromIndex.previous != null;
        }

        @Override
        public T previous() {
            checkForConcurrencyModification();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            nodeFromIndex = nodeFromIndex.previous;
            lastIndex = --index;
            return nodeFromIndex.item;
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
            checkForConcurrencyModification();
            MyLinkedList.this.remove(index);
            lastIndex--;
            iteratorModCount++;
        }

        @Override
        public void set(T t) {
            MyLinkedList.this.set(lastIndex, t);
            iteratorModCount++;
        }

        @Override
        public void add(T t) {
            checkForConcurrencyModification();
            MyLinkedList.this.add(t, index);
            index++;
            iteratorModCount++;
        }

        private void checkForConcurrencyModification() {
            if (iteratorModCount != MODIFICATION_COUNT) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
