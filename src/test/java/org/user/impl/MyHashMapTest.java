package org.user.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.user.map.MyMap;

import java.util.Collection;
import java.util.Set;


class MyHashMapTest {

    @Test
    void sizeShouldBeGreater0() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when
        int size = hashMap.size();

        //then
        Assertions.assertEquals(4, size, "Mismatched size -> " + size);

    }

    @Test
    void emptyMap() {
        //arrange
        MyHashMap<Integer, String> emptyHashMap = new MyHashMap<>();

        //act
        int emptyMapSize = emptyHashMap.size();
        boolean isEmpty = emptyHashMap.size() == 0;

        //assert
        Assertions.assertEquals(0, emptyMapSize);
        Assertions.assertTrue(isEmpty);
    }

    @Test
    void shouldNotBeEmpty() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when
        boolean isEmpty = hashMap.isEmpty();

        //then
        Assertions.assertFalse(isEmpty);
    }

    @Test
    void shouldBeEmpty() {
        //arrange
        MyHashMap<Integer, String> emptyHashMap = new MyHashMap<>();

        //act
        boolean isEmpty = emptyHashMap.isEmpty();

        //assert
        Assertions.assertTrue(isEmpty);
    }

    @Test
    void containsKey() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when

        //...

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(4, hashMap.size(), "Mismatched size."),
                () -> Assertions.assertTrue(hashMap.containsKey(1)),
                () -> Assertions.assertTrue(hashMap.containsKey(2)),
                () -> Assertions.assertTrue(hashMap.containsKey(3)),
                () -> Assertions.assertTrue(hashMap.containsKey(4)));
    }

    @Test
    void shouldNotContainKey() {
        //arrange
        MyHashMap<Integer, String> data = new MyHashMap<>(4);

        data.put(1, "Nominal data");
        data.put(2, "Ordinal");
        data.put(3, "Discrete");
        data.put(4, "Continuous");

        //act
        boolean containsKey = data.containsKey(5);

        //assert
        Assertions.assertFalse(containsKey);
    }

    @Test
    void containsValue() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when

        //...

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(4, hashMap.size(), "Mismatched size."),
                () -> Assertions.assertTrue(hashMap.containsValue("first_map_value")),
                () -> Assertions.assertTrue(hashMap.containsValue("second_map_value")),
                () -> Assertions.assertTrue(hashMap.containsValue("third_map_value")),
                () -> Assertions.assertTrue(hashMap.containsValue("fourth_map_value")));
    }

    @Test
    void shouldNotContainValue() {
        //arrange
        MyHashMap<Integer, String> data = new MyHashMap<>(4);

        data.put(1, "Nominal data");
        data.put(2, "Ordinal");
        data.put(3, "Discrete");
        data.put(4, "Continuous");

        //act
        boolean containsValue = data.containsValue("random");

        //assert
        Assertions.assertFalse(containsValue);
    }

    @Test
    void get() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when
        String fourthKey = hashMap.get(4);
        String firstKey = hashMap.get(1);

        //then
        Assertions.assertEquals("fourth_map_value", fourthKey, "Mismatched values.");
        Assertions.assertEquals("first_map_value", firstKey, "Mismatched values.");
    }

    @Test
    void getNullKey() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>(10);

        //when
        hashMap.put(1, "first_map_value");

        //then
        Assertions.assertThrows(NullPointerException.class, () -> hashMap.get(null));
    }

    @Test
    void getNullValue() {
        //arrange
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //act
        String nullValue = hashMap.get(8);

        //assert
        Assertions.assertNull(nullValue);
        Assertions.assertThrows(NullPointerException.class, () -> hashMap.get(null));
    }

    @Test
    void put() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>(4);

        //when
        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");

        //then
        Assertions.assertNotNull(hashMap.get(1), "Failed insertion.");
    }

    @Test
    void putNullKey() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>(4);

        //when
        hashMap.put(1, "first_map_value");

        //then
        Assertions.assertThrows(NullPointerException.class, () -> hashMap.put(null, "second_map_value"));
    }

    @Test
    void remove() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>(5);

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when
        String removedValue = hashMap.remove(2);

        //then
        Assertions.assertFalse(hashMap.containsValue(removedValue));
        Assertions.assertEquals(3, hashMap.size());
    }

    @Test
    void removeNullKey() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>(10);

        //when
        hashMap.put(1, "first_map_value");

        //then
        Assertions.assertThrows(NullPointerException.class, () -> hashMap.remove(null));
    }

    @Test
    void clear() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when
        hashMap.clear();

        //then
        Assertions.assertFalse(hashMap.containsKey(1));
        Assertions.assertFalse(hashMap.containsKey(2));
        Assertions.assertFalse(hashMap.containsValue("fourth_map_value"));
        Assertions.assertEquals(0, hashMap.size());
    }

    @Test
    void keySet() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when
        Set<Integer> integerSet = hashMap.keySet();
        boolean contains = integerSet.contains(3);
        boolean areKeysAbsent = !integerSet.isEmpty();
        int size = integerSet.size();

        //then
        Assertions.assertTrue(contains);
        Assertions.assertTrue(areKeysAbsent);
        Assertions.assertEquals(4, size);
    }

    @Test
    void values() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();
        MyHashMap<Integer, String> emptyHashMap = new MyHashMap<>();

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when
        Collection<String> values = hashMap.values();
        Collection<String> emptyValues = emptyHashMap.values();
        boolean contains = values.contains("third_map_value");
        boolean isEmpty = emptyValues.isEmpty();

        //then
        Assertions.assertTrue(contains);
        Assertions.assertTrue(isEmpty);
    }

    @Test
    void entrySet() {
        //given
        MyHashMap<Integer, String> hashMap = new MyHashMap<>(10);

        hashMap.put(1, "first_map_value");
        hashMap.put(2, "second_map_value");
        hashMap.put(3, "third_map_value");
        hashMap.put(4, "fourth_map_value");

        //when
        Integer key = null;
        String value = null;

        Set<MyMap.MyEntry<Integer, String>> myEntries = hashMap.entrySet();
        for (MyMap.MyEntry<Integer, String> myEntry : myEntries) {
            key = myEntry.getKey();
            value = myEntry.getValue();
        }

        //then
        Assertions.assertNotNull(key);
        Assertions.assertNotNull(value);
    }
}