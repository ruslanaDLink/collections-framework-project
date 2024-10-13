package org.user.impl;

import org.user.map.MyMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private final int DEFAULT_CAPACITY = 16;
    private LinkedList<MyEntry<K, V>>[] map;
    private Set<MyEntry<K, V>> entries = new HashSet<>();
    private int size = 0;

    public MyHashMap() {
        map = new LinkedList[DEFAULT_CAPACITY];
    }

    public MyHashMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal capacity " + initialCapacity);
        }
        map = new LinkedList[initialCapacity];
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
            if (myEntry.getKey() == key) {
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
            if (myEntry.getValue() == value) {
                isValuePresent = true;
            }
        }
        return isValuePresent;
    }

    @Override
    public V get(Object key) {
        V value = null;
        Set<MyMap.MyEntry<K, V>> entries = entrySet();
        for (MyMap.MyEntry<K, V> entry : entries) {
            if (entry.getKey() == key) {
                value = entry.getValue();
            }
        }
        return value;
    }

    @Override
    public V put(K key, V value) {
        V v = null;
        Set<MyMap.MyEntry<K, V>> entries = entrySet();
        for (MyMap.MyEntry<K, V> entry : entries) {
            if ((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey()))) {
                v = entry.getValue();
                entry.setValue(value);
                size++;
            }
        }
        return v;
    }

    @Override
    public V remove(Object key) {
        V v = null;
        Set<MyMap.MyEntry<K, V>> entries = entrySet();
        Iterator<MyMap.MyEntry<K, V>> iterator = entries.iterator();
        for (MyMap.MyEntry<K, V> entry : entries) {
            if (entry.getKey() == key) {
                if ((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey()))) {
                    v = entry.getValue();
                    iterator.remove();
                    size--;
                }
            }
        }
        return v;
    }


    @Override
    public void putAll(MyMap<? extends K, ? extends V> m) {
        if (m == null) {
            throw new NullPointerException();
        }
        Set<? extends MyMap.MyEntry<? extends K, ? extends V>> myEntries = m.entrySet();
        for (MyMap.MyEntry<? extends K, ? extends V> myEntry : myEntries) {
            this.put(myEntry.getKey(), myEntry.getValue());
        }
    }

    @Override
    public void clear() {
        map = new LinkedList[]{};
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
        for (LinkedList<MyEntry<K, V>> myEntries : map) {
            if (myEntries != null) {
                entries.addAll(myEntries);
            }
        }
        return entries;
    }


    private static class MyEntry<K, V> implements MyMap.MyEntry<K, V> {
        private K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
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
            MyEntry<?, ?> that = (MyEntry<?, ?>) o;
            return Objects.equals(key, that.key) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
