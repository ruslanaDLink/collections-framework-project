package org.user.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


class MyArrayListTest {

    @Test
    void add() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(5);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";
        String value4 = "#4.VALUE";
        String value5 = "#5.VALUE";

        //when
        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);
        myArrayList.add(value4);
        myArrayList.add(value5);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(5, myArrayList.size()),
                () -> Assertions.assertEquals(myArrayList.get(4), value5),
                () -> Assertions.assertEquals(myArrayList.get(2), value3),
                () -> Assertions.assertTrue(myArrayList.contains("#4.VALUE")),
                () -> Assertions.assertTrue(myArrayList.contains("#2.VALUE"))
        );
    }

    @Test
    void remove() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        //when
        boolean removedElement = myArrayList.remove(value1);

        //then
        Assertions.assertTrue(removedElement, "Failed to remove element " + removedElement);
        Assertions.assertEquals(2, myArrayList.size());
        Assertions.assertTrue(myArrayList.contains("#2.VALUE"));
    }

    @Test
    void testRemove() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(5);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";
        String value4 = "#4.VALUE";
        String value5 = "#5.VALUE";

        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);
        myArrayList.add(value4);
        myArrayList.add(value5);

        //when
        String removedFirstElement = myArrayList.remove(1);
        String removedSecondElement = myArrayList.remove(2);

        for (String s : myArrayList) {
            System.out.println(s);
        }
        //then
        Assertions.assertFalse(myArrayList.contains(removedFirstElement), "Failed to remove element " + removedFirstElement);
        Assertions.assertFalse(myArrayList.contains(removedSecondElement), "Failed to remove element " + removedSecondElement);
        Assertions.assertEquals(3, myArrayList.size());
        Assertions.assertFalse(myArrayList.contains("#2.VALUE"));
        Assertions.assertFalse(myArrayList.contains("#4.VALUE"));
    }

    @Test
    void get() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        //when
        String retrievedObj = myArrayList.get(2);

        //then
        Assertions.assertNotNull(retrievedObj, "Null Object");
        Assertions.assertEquals(3, myArrayList.size());
        Assertions.assertTrue(myArrayList.contains("#3.VALUE"));
    }

    @Test
    void set() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        //when
        myArrayList.set(0, "MODIFIED VALUE");

        //then
        Assertions.assertEquals(myArrayList.get(0), "MODIFIED VALUE", "Failed modification.");
        Assertions.assertEquals(3, myArrayList.size());
        Assertions.assertTrue(myArrayList.contains("#3.VALUE"));
    }

    @Test
    void isEmpty() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);
        MyArrayList<String> myEmptyArrayList = new MyArrayList<>();

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        //when
        boolean isEmptyFirstList = myArrayList.isEmpty();
        boolean isEmptySecondList = myEmptyArrayList.isEmpty();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, myArrayList.size()),
                () -> Assertions.assertTrue(myArrayList.contains("#1.VALUE")),
                () -> Assertions.assertFalse(isEmptyFirstList),
                () -> Assertions.assertTrue(isEmptySecondList)
        );

    }

    @Test
    void contains() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        //when
        boolean contains = myArrayList.contains(value2);

        //then
        Assertions.assertEquals(3, myArrayList.size());
        Assertions.assertTrue(contains, "Failed contains() condition.");
    }

    @Test
    void indexOf() {
        //given
        MyArrayList<String> seasons = new MyArrayList<>(4);

        String winter = "WINTER";
        String spring = "SPRING";
        String summer = "SUMMER";
        String autumn = "AUTUMN";

        seasons.add(winter);
        seasons.add(spring);
        seasons.add(summer);
        seasons.add(autumn);

        //when
        int indexOfWinter = seasons.indexOf(winter);
        int indexOfSpring = seasons.indexOf(spring);
        int indexOfSummer = seasons.indexOf(summer);
        int indexOfAutumn = seasons.indexOf(autumn);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, indexOfWinter),
                () -> Assertions.assertEquals(1, indexOfSpring),
                () -> Assertions.assertEquals(2, indexOfSummer),
                () -> Assertions.assertEquals(3, indexOfAutumn)
        );
    }

    @Test
    void lastIndexOf() {
        //given
        MyArrayList<String> seasons = new MyArrayList<>(3);

        String winter = "WINTER";
        String spring = "SPRING";
        String summer = "SUMMER";
        String autumn = "AUTUMN";

        seasons.add(winter);
        seasons.add(spring);
        seasons.add(summer);
        seasons.add(autumn);

        //when
        int lastIndexOfSummer = seasons.lastIndexOf(summer);

        //then
        Assertions.assertEquals(2, lastIndexOfSummer);
    }

    @Test
    void size() {
        //given
        MyArrayList<String> pizzas = new MyArrayList<>(10);

        //when
        pizzas.add("Hawaiian");
        pizzas.add("Margherita");
        pizzas.add("Neapolitan");
        pizzas.add("Sicilian");
        pizzas.add("Pepperoni");

        //then
        Assertions.assertTrue(pizzas.contains("Sicilian"));
        Assertions.assertEquals("Margherita", pizzas.get(1));
        Assertions.assertEquals(5, pizzas.size());
    }


    @Test
    void capacity() {
        //given
        MyArrayList<String> objects = new MyArrayList<>(2);
        objects.add("obj_1");
        objects.add("obj_2");
        objects.add("obj_3");
        objects.add("obj_4");

        //when
        int size = objects.size();

        //then
        Assertions.assertEquals(4, size);
    }

    @Test
    void iterator() {
        //given
        MyArrayList<String> capitals = new MyArrayList<>(195);

        capitals.add("Kiev");
        capitals.add("Warsaw");
        capitals.add("Canberra");
        capitals.add("Washington");
        capitals.add("Berlin");
        capitals.add("London");
        capitals.add("Madrid");
        capitals.add("Paris");

        //when
        Iterator<String> iterator = capitals.iterator();

        //then
        Assertions.assertAll(
                () -> Assertions.assertTrue(iterator.hasNext()),
                () -> Assertions.assertEquals("Warsaw", iterator.next())
        );
    }

    @Test
    void listIterator() {
        //given
        MyArrayList<String> capitals = new MyArrayList<>(195);

        capitals.add("Kiev");
        capitals.add("Warsaw");
        capitals.add("Canberra");
        capitals.add("Washington");
        capitals.add("Berlin");
        capitals.add("London");
        capitals.add("Madrid");
        capitals.add("Paris");

        //when
        ListIterator<String> listIterator = capitals.listIterator();

        //then
        Assertions.assertAll(
                () -> Assertions.assertTrue(listIterator.hasNext()),
                () -> Assertions.assertEquals("Kiev", listIterator.next()),
                () -> Assertions.assertTrue(listIterator.hasPrevious()),
                () -> Assertions.assertEquals("Warsaw", listIterator.next()),
                () -> Assertions.assertEquals("Canberra", listIterator.next()),
                () -> Assertions.assertEquals("Canberra", listIterator.previous()),
                () -> Assertions.assertEquals("Warsaw", listIterator.previous()),
                () -> Assertions.assertEquals(8, capitals.size())
        );
    }

    @Test
    void emptyListIterator() {
        //given
        MyArrayList<String> strings = new MyArrayList<>();

        //when
        ListIterator<String> listIterator = strings.listIterator();

        //then
        Assertions.assertAll(
                () -> Assertions.assertFalse(listIterator.hasNext()),
                () -> Assertions.assertFalse(listIterator.hasPrevious()),
                () -> Assertions.assertThrows(NoSuchElementException.class, listIterator::next),
                () -> Assertions.assertThrows(NoSuchElementException.class, listIterator::previous)
        );
    }


    @Test
    void listIteratorWithIndex() {
        //given
        MyArrayList<String> capitals = new MyArrayList<>(195);

        capitals.add("Kiev");
        capitals.add("Warsaw");
        capitals.add("Canberra");
        capitals.add("Washington");
        capitals.add("Berlin");
        capitals.add("London");
        capitals.add("Madrid");
        capitals.add("Paris");

        //when
        ListIterator<String> listIterator = capitals.listIterator(5);

        //then
        Assertions.assertAll(
                () -> Assertions.assertTrue(listIterator.hasNext()),
                () -> Assertions.assertEquals("London", listIterator.next()),
                () -> Assertions.assertTrue(listIterator.hasPrevious()),
                () -> Assertions.assertEquals("Madrid", listIterator.next()),
                () -> Assertions.assertEquals("Madrid", listIterator.previous()),
                () -> Assertions.assertEquals("London", listIterator.previous()),
                () -> Assertions.assertEquals(8, capitals.size())
        );
    }

    @Test
    void testLoop() {
        //given
        MyArrayList<String> count = new MyArrayList<>(3);

        count.add("one");
        count.add("two");
        count.add("three");

        //when
        Iterator<String> iterator = count.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equalsIgnoreCase("two")) {
                iterator.remove();
            }

        }

        //then
        Assertions.assertEquals(2, count.size());
        Assertions.assertFalse(count.contains("two"));
        Assertions.assertTrue(count.contains("one"));
        Assertions.assertTrue(count.contains("three"));
    }
}