package org.user.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
        boolean addedValue1 = myArrayList.add(value1);
        boolean addedValue2 = myArrayList.add(value2);
        boolean addedValue3 = myArrayList.add(value3);
        boolean addedValue4 = myArrayList.add(value4);
        boolean addedValue5 = myArrayList.add(value5);

        //then
        Assertions.assertAll(
                () -> Assertions.assertTrue(addedValue1, "Failed to add value " + value1),
                () -> Assertions.assertTrue(addedValue2, "Failed to add value " + value2),
                () -> Assertions.assertTrue(addedValue3, "Failed to add value " + value3),
                () -> Assertions.assertTrue(addedValue4, "Failed to add value " + value4),
                () -> Assertions.assertTrue(addedValue5, "Failed to add value " + value5)
        );
    }

    @Test
    void testAdd() {
    }

    @Test
    void remove() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        //when
        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        boolean removedElement = myArrayList.remove(value1);

        //then
        Assertions.assertTrue(removedElement, "Failed to remove element " + removedElement);
    }

    @Test
    void testRemove() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        //when
        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        String removedElement = myArrayList.remove(1);

        //then
        Assertions.assertFalse(myArrayList.contains(removedElement), "Failed to remove element " + removedElement);
    }

    @Test
    void get() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        //when
        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        String retrievedObj = myArrayList.get(2);

        //then
        Assertions.assertNotNull(retrievedObj, "Null Object");
    }

    @Test
    void set() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        //when
        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        String actualObject = myArrayList.get(0);
        String modifiedObject = myArrayList.set(0, "MODIFIED VALUE");

        boolean equalsCondition = actualObject.equals(modifiedObject);

        System.out.println(actualObject);
        System.out.println(modifiedObject);
        //then
        Assertions.assertTrue(equalsCondition, "Failed modification.");
    }

    @Test
    void isEmpty() {
        //given
        MyArrayList<String> myArrayList = new MyArrayList<>(3);
        MyArrayList<String> myEmptyArrayList = new MyArrayList<>();

        String value1 = "#1.VALUE";
        String value2 = "#2.VALUE";
        String value3 = "#3.VALUE";

        //when
        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        boolean isEmptyFirstList = myArrayList.isEmpty();
        boolean isEmptySecondList = myEmptyArrayList.isEmpty();

        //then
        Assertions.assertAll(
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

        //when
        myArrayList.add(value1);
        myArrayList.add(value2);
        myArrayList.add(value3);

        boolean contains = myArrayList.contains(value2);

        //then
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

        //when
        seasons.add(winter);
        seasons.add(spring);
        seasons.add(summer);
        seasons.add(autumn);

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

        //when
        seasons.add(winter);
        seasons.add(spring);
        seasons.add(summer);
        seasons.add(autumn);

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
        Assertions.assertEquals(5, pizzas.size());

    }

    @Test
    void iterator() {
        //given
        MyArrayList<String> capitals = new MyArrayList<>(195);

        //when
        capitals.add("Kiev");
        capitals.add("Warsaw");
        capitals.add("Canberra");
        capitals.add("Washington");
        capitals.add("Berlin");
        capitals.add("London");
        capitals.add("Madrid");
        capitals.add("Paris");

        Iterator<String> iterator = capitals.iterator();

        //then
        Assertions.assertAll(
                () -> Assertions.assertTrue(iterator.hasNext()),
                () -> Assertions.assertEquals("Kiev", iterator.next())
        );
    }

    @Test
    void listIterator() {
        //given
        MyArrayList<String> capitals = new MyArrayList<>(195);

        //when
        capitals.add("Kiev");
        capitals.add("Warsaw");
        capitals.add("Canberra");
        capitals.add("Washington");
        capitals.add("Berlin");
        capitals.add("London");
        capitals.add("Madrid");
        capitals.add("Paris");

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

        //when
        capitals.add("Kiev");
        capitals.add("Warsaw");
        capitals.add("Canberra");
        capitals.add("Washington");
        capitals.add("Berlin");
        capitals.add("London");
        capitals.add("Madrid");
        capitals.add("Paris");

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

        //when
        count.add("one");
        count.add("two");
        count.add("three");

        Iterator<String> iterator = count.iterator();

        while (iterator.hasNext()){
            String next = iterator.next();
            if (next.equalsIgnoreCase("two")){
                iterator.remove();
            }
        }

        //then

    }
}