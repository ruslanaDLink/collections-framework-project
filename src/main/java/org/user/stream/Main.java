package org.user.stream;

import org.user.impl.MyArrayList;
import org.user.list.MyList;
import org.user.optional.MyOptional;


public class Main {
    public static void main(String[] args) {
//        filterElements();
//        mapElements();
//        forEachElement();
//        task4();
//        count();
//        distinctElements();
//        limitedSize();
//        skip();
//        sort();
//        findFirst();
//        compare();
//        task11();
//        task13();
//        task14();
//        task15();
//        task16();
//        task17();
    }

    public static void filterElements() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(10);
        integers.add(20);
        integers.add(30);
        integers.add(40);
        integers.add(50);
        MyStream<Integer> integerMyStream = new MyStream<>(integers);
        MyList<Integer> list = integerMyStream.filter(num -> num > 30).toList();
        System.out.println(list.toString());
    }

    public static void mapElements() {
        MyArrayList<String> values = new MyArrayList<>();
        values.add("red");
        values.add("yellow");
        values.add("black");
        values.add("grey");
        MyStream<String> stringMyStream = new MyStream<>(values);

        MyList<String> list = stringMyStream.map(String::toUpperCase).toList();
        System.out.println(list);
    }

    public static void forEachElement() {
        MyArrayList<String> values = new MyArrayList<>();
        values.add("red");
        values.add("yellow");
        values.add("black");
        values.add("grey");
        MyStream<String> stringMyStream = new MyStream<>(values);

        stringMyStream.forEach(System.out::println);
    }

    public static void task4() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Anna");
        names.add("Bartek");
        names.add("Cecylia");
        names.add("Dan");

        MyStream<String> stream = new MyStream<>(names);
        stream
                .filter(n -> n.length() > 4)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    public static void count() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Anna");
        names.add("Bartek");
        names.add("Cecylia");
        names.add("Dan");
        MyStream<String> stream = new MyStream<>(names);
        long count = stream.count();
        System.out.println(count);
    }

    public static void distinctElements() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Anna");
        names.add("Bartek");
        names.add("Bartek");
        names.add("Bartek");
        names.add("Cecylia");
        names.add("Dan");
        names.add("Dan");
        names.add("Dan");
        names.add("Milosz");

        MyStream<String> stream = new MyStream<>(names);
        stream.distinct().forEach(System.out::println);
    }

    public static void limitedSize() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Anna");
        names.add("Bartek");
        names.add("Cecylia");
        names.add("Dan");
        names.add("Milosz");

        MyStream<String> stream = new MyStream<>(names);
        stream.limit(3).forEach(System.out::println);
    }

    public static void skip() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Anna");
        names.add("Bartek");
        names.add("Cecylia");
        names.add("Dan");
        names.add("Milosz");
        MyStream<String> stream = new MyStream<>(names);
        stream.skip(3).forEach(System.out::println);
    }

    public static void sort() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Daniel");
        names.add("Anna");
        names.add("Bartek");
        names.add("Cecylia");

        new MyStream<>(names)
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }

    public static void findFirst() {
        MyArrayList<Integer> numbers = new MyArrayList<>();
        numbers.add(8);
        numbers.add(13);
        numbers.add(1);
        numbers.add(27);

        MyOptional<Integer> optional = new MyStream<>(numbers).findFirst();
        System.out.println(optional.get());
    }

    public static void compare() {
        MyArrayList<Integer> numbers = new MyArrayList<>();
        numbers.add(8);
        numbers.add(13);
        numbers.add(1);
        numbers.add(27);

        new MyStream<>(numbers)
                .compare(Integer::compare)
                .forEach(System.out::println);
    }

    public static void task11() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Daniel");
        names.add("Anna");
        names.add("Bartek");
        names.add("Cecylia");

        new MyStream<>(names)
                .sorted(String::compareTo)
                .skip(2)
                .forEach(System.out::println);
    }

    public static void task13() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(7);
        integers.add(2);
        integers.add(11);
        integers.add(23);
        integers.add(6);
        integers.add(50);
        integers.add(9);

        Integer result = new MyStream<>(integers)
                .filter(i -> i > 10)
                .map(i -> i + 5)
                .findFirst().get();

        System.out.println(result);
    }

    public static void task14() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(7);
        integers.add(7);
        integers.add(2);
        integers.add(11);
        integers.add(23);
        integers.add(6);
        integers.add(50);
        integers.add(9);

        long result = new MyStream<>(integers)
                .distinct()
                .filter(number -> number > 5)
                .limit(3)
                .count();

        System.out.println(result);
    }

    public static void task15() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Daniel");
        names.add("Anna");
        names.add("Bartek");
        names.add("Cecylia");
        names.add("Ela");

        String result = new MyStream<>(names)
                .sorted(String::compareTo)
                .skip(3)
                .findFirst()
                .get();

        System.out.println(result);
    }

    public static void task16() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(10);
        integers.add(30);
        integers.add(40);
        integers.add(60);
        integers.add(80);

        long result = new MyStream<>(integers)
                .map(num -> num + 20)
                .filter(num -> num > 50)
                .count();
        System.out.println(result);
    }

    public static void task17() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Anna");
        names.add("Agnieszka");
        names.add("Bartek");
        names.add("Cecylia");
        names.add("Adam");

        long result = new MyStream<>(names)
                .filter(name -> name.charAt(0) == 'A')
                .map(String::toUpperCase)
                .count();
        System.out.println(result);
    }
}
