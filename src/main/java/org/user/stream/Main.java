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
//        allMatch();
//        anyMatch();
//        task18();
//        noneMatch();
//        reduce();
//        task19();
//        min();
//        max();
//        task20();
//        peek();
        flatMap();
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

    public static void allMatch() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(10);
        integers.add(30);
        integers.add(40);
        integers.add(60);
        integers.add(80);

        boolean b = new MyStream<>(integers)
                .allMatch(n -> n > 0);
        System.out.println(b);
    }

    public static void anyMatch() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(10);
        integers.add(30);
        integers.add(40);
        integers.add(60);
        integers.add(80);

        boolean b = new MyStream<>(integers)
                .anyMatch(n -> n > 50);
        System.out.println(b);
    }

    public static void task18() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(2);
        integers.add(4);
        integers.add(6);
        integers.add(9);
        integers.add(12);

        boolean isEven = integers
                .stream()
                .allMatch(n -> n % 2 == 0);

        boolean isBigger10 = integers
                .stream()
                .anyMatch(n -> n > 10);

        System.out.println("Czy parzysta? " + isEven);
        System.out.println("Czy większa 10? " + isBigger10);
    }

    public static void noneMatch() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(10);
        integers.add(30);
        integers.add(40);
        integers.add(60);
        integers.add(80);

        boolean b = new MyStream<>(integers)
                .noneMatch(n -> n < 0);
        System.out.println(b);
    }

    public static void reduce() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(10);
        integers.add(30);
        integers.add(40);

        Integer sum = integers
                .stream()
                .reduce(10, Integer::sum);
        System.out.println(sum);
    }

    public static void task19() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(12);
        integers.add(15);
        integers.add(8);
        integers.add(22);

        Integer sum = integers
                .stream()
                .filter(x -> x > 10)
                .reduce(0, Integer::sum);
        boolean smallerThan5 = integers
                .stream()
                .noneMatch(x -> x < 5);

        System.out.println("Sum: " + sum);
        System.out.println("smaller than 5: " + smallerThan5);
    }

    public static void min() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(12);
        integers.add(15);
        integers.add(8);
        integers.add(22);

        Integer minValue = integers
                .stream()
                .min(Integer::compareTo)
                .get();

        System.out.println(minValue);
    }

    public static void max() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(12);
        integers.add(15);
        integers.add(8);
        integers.add(22);

        Integer minValue = integers
                .stream()
                .max(Integer::compareTo)
                .get();

        System.out.println(minValue);
    }

    public static void task20() {
        MyArrayList<Integer> integers = new MyArrayList<>();
        integers.add(3);
        integers.add(7);
        integers.add(2);
        integers.add(9);
        integers.add(1);

        Integer minValue = integers
                .stream()
                .min(Integer::compareTo)
                .get();

        Integer maxValue = integers
                .stream()
                .max(Integer::compareTo)
                .get();

        Integer sum = integers
                .stream()
                .reduce(0, Integer::sum);

        System.out.println("Minimum value " + minValue);
        System.out.println("Maximum value " + maxValue);
        System.out.println("Sum " + sum);
    }

    public static void peek() {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("Anna");
        names.add("Agnieszka");
        names.add("Bartek");
        names.add("Cecylia");
        names.add("Adam");

        names
                .stream()
                .peek(System.out::println);
    }

    public static void flatMap() {
        MyList<Integer> firstCollection = new MyArrayList<>();
        firstCollection.add(1);
        firstCollection.add(2);

        MyList<Integer> secondCollection = new MyArrayList<>();
        secondCollection.add(3);
        secondCollection.add(4);

        MyList<MyList<Integer>> numbers = new MyArrayList<>();
        numbers.add(firstCollection);
        numbers.add(secondCollection);

        MyArrayList<Integer> list = numbers
                .stream()
                .flatMap(x -> new MyStream<>(x)).toList();

        list.forEach(System.out::println);
    }
}
