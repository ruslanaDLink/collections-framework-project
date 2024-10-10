package org.user.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ListIterator;


class MyLinkedListTest {

    @Test
    void add() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();

        //when
        boolean isAdded1 = strings.add("str1");
        boolean isAdded2 = strings.add("str2");
        boolean isAdded3 = strings.add("str3");
        boolean isAdded4 = strings.add("str4");

        //then
        Assertions.assertAll(
                () -> Assertions.assertFalse(strings.isEmpty()),
                () -> Assertions.assertEquals("str1", strings.get(0)),
                () -> Assertions.assertTrue(isAdded1),
                () -> Assertions.assertTrue(isAdded2),
                () -> Assertions.assertTrue(isAdded3),
                () -> Assertions.assertTrue(isAdded4)
        );
    }

    @Test
    void addWithIndex() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();

        //when
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");
        strings.add("str4");

        //then
        Assertions.assertEquals("str5", strings.get(1));
    }

    @Test
    void remove() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";
        strings.add(str1);
        strings.add(str2);
        strings.add(str3);

        //when
        boolean isRemoved = strings.remove(str1);

        //then
        Assertions.assertEquals("str2", strings.get(0));
        Assertions.assertTrue(isRemoved);
    }

    @Test
    void removeWithIndex() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";
        strings.add(str1);
        strings.add(str2);
        strings.add(str3);

        //when
        strings.remove(1);

        //then
        Assertions.assertFalse(strings.contains(str2));
    }

    @Test
    void get() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";
        strings.add(str1);
        strings.add(str2);
        strings.add(str3);

        //when
        String s = strings.get(0);

        //then
        Assertions.assertEquals(str1, s);
    }

    @Test
    void set() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";
        String str4 = "str4";

        strings.add(str1);
        strings.add(str2);
        strings.add(str3);

        //when
        strings.set(0, str4);

        //then
        Assertions.assertEquals(str4, strings.get(0));
    }

    @Test
    void isEmpty() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        MyLinkedList<String> emptyList = new MyLinkedList<>();
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");

        //when
        boolean isEmptyListFullOfStrings = strings.isEmpty();
        boolean isEmptyList = emptyList.isEmpty();

        //then
        Assertions.assertTrue(isEmptyList);
        Assertions.assertFalse(isEmptyListFullOfStrings);
    }

    @Test
    void contains() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");

        //when
        boolean contains1 = strings.contains("str1");
        boolean contains2 = strings.contains("str2");
        boolean contains3 = strings.contains("str3");

        //then
        Assertions.assertTrue(contains1);
        Assertions.assertTrue(contains2);
        Assertions.assertTrue(contains3);
    }

    @Test
    void indexOf() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");

        //when
        int i = strings.indexOf("str3");

        //then
        Assertions.assertEquals(2, i);
    }

    @Test
    void lastIndexOf() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");

        //when
        int i = strings.indexOf("str3");

        //then
        Assertions.assertEquals(2, i);
    }

    @Test
    void size() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");

        //when
        int size = strings.size();

        //then
        Assertions.assertEquals(3, size);
    }

    @Test
    void iterator() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");

        //when
        Iterator<String> iterator = strings.iterator();
        String next = iterator.next();

        //then
        Assertions.assertEquals("str2", next);
    }

    @Test
    void listIterator() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");

        //when
        ListIterator<String> listIterator = strings.listIterator();
        listIterator.add("str4");

        //then
        Assertions.assertTrue(listIterator.hasNext());
        Assertions.assertEquals("str2", listIterator.next());
        Assertions.assertEquals("str3", listIterator.next());
        Assertions.assertTrue(listIterator.hasPrevious());
        Assertions.assertEquals("str2", listIterator.previous());
        Assertions.assertEquals("str3", listIterator.next());
        Assertions.assertEquals("str4", listIterator.next());
    }

    @Test
    void testListIteratorWithIndex() {
        //given
        MyLinkedList<String> strings = new MyLinkedList<>();
        strings.add("str1");
        strings.add("str2");
        strings.add("str3");
        strings.add("str4");

        //when
        ListIterator<String> listIterator = strings.listIterator(1);

        //then
        Assertions.assertTrue(listIterator.hasNext());
        Assertions.assertEquals("str2", listIterator.next());
        Assertions.assertEquals("str3", listIterator.next());
        Assertions.assertTrue(listIterator.hasPrevious());
        Assertions.assertEquals("str2", listIterator.previous());
        Assertions.assertEquals("str3", listIterator.next());
        Assertions.assertEquals("str4", listIterator.next());
    }
}