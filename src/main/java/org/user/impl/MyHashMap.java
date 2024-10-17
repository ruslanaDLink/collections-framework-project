package org.user.impl;

import org.user.map.MyMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] buckets;
    private int size = 0;

    public MyHashMap() {
        this.buckets = new Node[DEFAULT_CAPACITY];
    }

    public MyHashMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal capacity " + initialCapacity);
        }
        this.buckets = new Node[initialCapacity];
    }

    private int hash(Object key) {
        return key == null ? 0 : key.hashCode();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean isKeyPresent = false;
        Set<MyMap.MyEntry<K, V>> set = entrySet();
        for (MyMap.MyEntry<K, V> myEntry : set) {
            if (myEntry.getKey().equals(key)) {
                isKeyPresent = true;
            }
        }
        return isKeyPresent;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean isValuePresent = false;
        Set<MyMap.MyEntry<K, V>> set = entrySet();
        for (MyMap.MyEntry<K, V> myEntry : set) {
            if (myEntry.getValue().equals(value)) {
                isValuePresent = true;
            }
        }
        return isValuePresent;
    }

    @Override
    public V get(Object key) {
        V value = null;
        int hashcode = hash(key);
        int index = hashcode % (buckets.length - 1);
        Node<K, V> bucket = buckets[index];
        while (bucket != null) {
            if (bucket.key.equals(key)) {
                value = bucket.value;
            }
            bucket = bucket.next;
        }
        return value;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        V v;
        int hashcode = hash(key);
        int index = hashcode % (buckets.length - 1);
        Node<K, V> bucket = buckets[index];

        if (bucket == null) {
            Node<K, V> newNode = new Node<>(key, value, null);
            buckets[index] = newNode;
            v = newNode.value;
        } else {
            Node<K, V> kvNode = bucket;
            while (bucket != null) {
                if (bucket.key.equals(key)) {
                    bucket.value = value;
                }
                kvNode = bucket;
                bucket = bucket.next;
            }
            kvNode.next = new Node<>(key, value, null);
            v = kvNode.value;
        }
        size++;
        return v;
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int index = hash(key);
        Node<K, V> bucket = buckets[index];
        Node<K, V> previousNode = bucket;
        V v = bucket.value;

        while (bucket != null) {
            if (bucket.key != null && bucket.key.equals(key)) {
                v = bucket.value;
                if (previousNode == bucket) {
                    buckets[index] = bucket.next;
                } else {
                    previousNode.next = bucket.next;
                }
            }
            previousNode = bucket;
            bucket = bucket.next;
        }
        size--;
        return v;
    }

    @Override
    public void putAll(MyMap<? extends K, ? extends V> map) {
        if (map == null) {
            throw new NullPointerException();
        }
        Set<? extends MyMap.MyEntry<? extends K, ? extends V>> myEntries = map.entrySet();
        for (MyMap.MyEntry<? extends K, ? extends V> myEntry : myEntries) {
            this.put(myEntry.getKey(), myEntry.getValue());
        }
    }

    @Override
    public void clear() {
        this.buckets = new Node[]{};
    }

    @Override
    public Set<K> keySet() {
        //ensure keys are unique
        Set<K> keys = new HashSet<>();
        for (MyMap.MyEntry<K, V> entry : entrySet()) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        //values can be repeated
        Collection<V> values = new ArrayList<>();
        for (MyMap.MyEntry<K, V> entry : entrySet()) {
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public Set<MyMap.MyEntry<K, V>> entrySet() {
        Set<MyMap.MyEntry<K, V>> entries = new HashSet<>();
        for (Node<K, V> bucket : buckets) {
            if (bucket != null) {
                entries.add(bucket);
            }
        }
        return entries;
    }


    private static class Node<K, V> implements MyMap.MyEntry<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> that = (Node<?, ?>) o;
            return Objects.equals(key, that.key) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
