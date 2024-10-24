package org.user.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.user.impl.MyArrayList;
import org.user.list.MyList;

import java.util.NoSuchElementException;

class MyStreamTest {

    @Test
    void filter() {
        //arrange
        MyList<String> popularCompanies = MyList.of("AMAZON", "APPLE", "MICROSOFT", "NVIDIA", "TESLA", "WALMART");
        //act
        MyStream<String> filteredElements = popularCompanies
                .stream()
                .filter(company -> company.charAt(0) == 'A');
        MyArrayList<String> filteredElementsList = filteredElements.toList();
        //assert
        Assertions.assertTrue(filteredElements.allMatch(x -> x.charAt(0) == 'A'));
        Assertions.assertEquals(2, filteredElements.count());
        Assertions.assertEquals("APPLE", filteredElementsList.get(1));
    }

    @Test
    void map() {
        //arrange
        MyList<String> popularCompanies = MyList.of("AMAZON", "APPLE", "MICROSOFT", "NVIDIA", "TESLA", "WALMART");
        //act
        MyStream<String> mappedToHexadecimal = popularCompanies.stream().map(x -> String.format("%h", x));
        MyArrayList<String> mappedList = mappedToHexadecimal.toList();
        //assert
        Assertions.assertEquals(popularCompanies.size(), mappedToHexadecimal.count());
        Assertions.assertNotEquals(popularCompanies.get(0), mappedList.get(0));
    }

    @Test
    void count() {
        //arrange
        MyList<String> popularCompanies = MyList.of("AMAZON", "APPLE", "MICROSOFT", "NVIDIA", "TESLA", "WALMART");
        //act
        long count = popularCompanies.stream().count();
        //assert
        Assertions.assertEquals(popularCompanies.size(), count);
    }

    @Test
    void distinct() {
        //arrange
        MyList<String> childrenList = MyList.of(
                "Monica", "Jeffrey", "Jack", "Omar", "Raquel", "Jeffrey", "Monica", "Lucy", "George", "Omar");
        //act
        MyStream<String> distinctStream = childrenList.stream().distinct();
        MyList<String> uniqueNames = distinctStream.toList();
        boolean areValuesUnique = false;
        String name = uniqueNames.get(0);
        for (String uniqueName : uniqueNames) {
            if (!name.equalsIgnoreCase(uniqueName)) {
                areValuesUnique = true;
                name = uniqueName;
            }
        }
        //assert
        Assertions.assertTrue(areValuesUnique);
    }


    @Test
    void limit() {
        //arrange
        MyList<String> productList = MyList.of(
                "gloves", "hair clips", "pyjamas", "sweeter", "cardigan");
        //act
        MyStream<String> limitedStream = productList.stream().limit(3);
        MyArrayList<String> limitedProductList = limitedStream.toList();
        //assert
        Assertions.assertEquals(3, limitedProductList.size());
        Assertions.assertFalse(limitedProductList.contains("sweeter"));
        Assertions.assertTrue(limitedProductList.contains("pyjamas"));
    }

    @Test
    void testForbiddenLimit() {
        //arrange
        MyList<String> productList = MyList.of(
                "gloves", "hair clips", "pyjamas", "sweeter", "cardigan");
        //act
        //...
        //assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> productList.stream().limit(-1).toList());
    }

    @Test
    void skip() {
        //arrange
        MyList<String> childrenList = MyList.of(
                "Monica", "Jeffrey", "Jack", "Omar", "Raquel");
        //act
        int skippedValuesCount = 2;
        MyArrayList<String> listWithSkippedValues = childrenList.stream().skip(skippedValuesCount).toList();
        //assert
        Assertions.assertEquals(childrenList.size() - 2, listWithSkippedValues.size());
        Assertions.assertEquals(childrenList.get(2), listWithSkippedValues.get(0));
        Assertions.assertNotEquals(childrenList.get(0), listWithSkippedValues.get(0));
    }

    @Test
    void testForbiddenSkipNumber() {
        //arrange
        MyList<String> childrenList = MyList.of(
                "Monica", "Jeffrey", "Jack", "Omar", "Raquel");
        //act
        //...
        //assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> childrenList.stream().skip(-7).toList());
    }

    @Test
    void sorted() {
        //arrange
        MyList<Integer> integers = MyList.of(60, 11, 33, 29, 45);
        //act
        MyStream<Integer> stream = integers.stream();
        MyArrayList<Integer> sortedIntegers = stream.sorted(Integer::compareTo).toList();
        //assert
        Assertions.assertEquals(stream.min(Integer::compareTo).get(), sortedIntegers.get(0));
        Assertions.assertEquals(stream.max(Integer::compareTo).get(), sortedIntegers.get(sortedIntegers.size() - 1));
    }

    @Test
    void sortNullValues() {
        //arrange
        MyList<String> integers = MyList.of(null, "1", "Kasia", "Monika", null, "8");
        //act
        MyStream<String> stream = integers.stream();
        //assert
        Assertions.assertThrows(NullPointerException.class, () ->stream.sorted(String::compareTo).toList());
    }

    @Test
    void findFirst() {
        //arrange
        MyList<String> defaultStrings = MyList.of(
                "1_STRING_1", "2_STRING_2", "3_STRING_3", "4_STRING_4", "5_STRING_5");
        //act
        String firstVar = defaultStrings.stream().findFirst().get();
        //assert
        Assertions.assertNotNull(firstVar);
        Assertions.assertEquals(defaultStrings.get(0), firstVar);
    }

    @Test
    void findFirstNullValue() {
        //arrange
        MyList<String> defaultStrings = MyList.of(
                null, "1_STRING_1", "2_STRING_2", "3_STRING_3", "4_STRING_4", "5_STRING_5");
        //act
        //...
        //assert
        Assertions.assertThrows(NoSuchElementException.class, () -> defaultStrings.stream().findFirst().get());
    }

    @Test
    void compare() {
        //arrange
        MyList<String> letters = MyList.of("W", "F", "B", "C", "Z", "A", "K", "I", "N");
        //act
        MyList<String> sortedLetters = letters.stream().compare(String::compareTo).toList();
        //assert
        Assertions.assertEquals(0, sortedLetters.indexOf("A"));
        Assertions.assertEquals("Z", sortedLetters.get(sortedLetters.size() - 1));
    }

    @Test
    void allMatch() {
        //arrange
        MyList<Double> doubles = MyList.of(3.14, 7.54, 7.53, 3.11, 3.15, 4.0, 3.04, 7.55, 7.05);
        //act
        boolean allMatch = doubles.stream().allMatch(x -> x > 3.0);
        //assert
        Assertions.assertTrue(allMatch);
    }

    @Test
    void anyMatch() {
        //arrange
        MyList<Double> doubles = MyList.of(3.14, 7.54, 7.53, 3.11, 3.15, 4.0, 3.04, 7.55, 7.05);
        //act
        boolean anyMatch = doubles.stream().anyMatch(x -> x >= 7);
        //assert
        Assertions.assertTrue(anyMatch);
    }

    @Test
    void noneMatch() {
        //arrange
        MyList<Double> doubles = MyList.of(3.14, 7.54, 7.53, 3.11, 3.15, 4.0, 3.04, 7.55, 7.05);
        //act
        MyStream<Double> stream = doubles.stream();
        boolean noneMatch = stream.noneMatch(x -> x.isNaN());
        boolean noneMatch2 = stream.noneMatch(x -> x < 0);
        //assert
        Assertions.assertTrue(noneMatch);
        Assertions.assertTrue(noneMatch2);
    }

    @Test
    void reduce() {
        //arrange
        MyList<Integer> integers = MyList.of(100, 10, 300, 80, 30);
        //act
        Integer result = integers.stream().reduce(80, Integer::sum);
        //should be 600
        int sum = 80;
        for (Integer integer : integers) {
            sum += integer;
        }
        //assert
        Assertions.assertEquals(sum, result);
    }

    @Test
    void min() {
        //arrange
        MyList<Integer> integers = MyList.of(100, 10, 300, 80, 30);
        //act
        MyStream<Integer> stream = integers.stream();
        Integer minValue = stream.min(Integer::compare).get();
        Integer firstSortedValue = stream.sorted(Integer::compareTo).findFirst().get();
        //assert
        Assertions.assertEquals(firstSortedValue, minValue);
    }

    @Test
    void testMinNullValue() {
        //arrange
        MyList<Integer> integers = MyList.of(null, null, null);
        //act
        //...
        //assert
        Assertions.assertThrows(NullPointerException.class, () -> integers.stream().min(Integer::compare).get());
    }

    @Test
    void max() {
        //arrange
        MyList<Integer> integers = MyList.of(100, 10, 300, 80, 30);
        //act
        MyStream<Integer> stream = integers.stream();
        Integer maxValue = stream.max(Integer::compare).get();
        Integer lastSortedValue = stream.sorted(Integer::compareTo).toList().get(integers.size() - 1);
        //assert
        Assertions.assertEquals(lastSortedValue, maxValue);
    }

    @Test
    void testMaxNullValue() {
        //arrange
        MyList<Integer> integers = MyList.of(null, null, null);
        //act
        //...
        //assert
        Assertions.assertThrows(NullPointerException.class, () -> integers.stream().max(Integer::compare).get());
    }

    @Test
    void flatMap() {
        //arrange
        String profit2023 = "[2023] profit - 0.2% DOWN";
        String globalChains2023 = "[2023] global main chains - CHINA, JAPAN, PHILIPPINES";
        MyList<String> comStatistics2023 = new MyArrayList<>();
        comStatistics2023.add(profit2023);
        comStatistics2023.add(globalChains2023);
        String profit2024 = "[2024] profit - 3.1% UP";
        String globalChains2024 = "[2024] global main chains - CHINA, JAPAN, DENMARK, FINLAND, GERMANY";
        MyList<String> comStatistics2024 = new MyArrayList<>();
        comStatistics2024.add(profit2024);
        comStatistics2023.add(globalChains2024);

        MyList<MyList<String>> list = new MyArrayList<>();
        list.add(comStatistics2023);
        list.add(comStatistics2024);
        //act
        MyArrayList<String> overallStatistics = list.stream().flatMap(newList -> new MyStream<>(newList)).toList();
        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(comStatistics2023.size() + comStatistics2024.size(), overallStatistics.size()),
                () -> Assertions.assertTrue(overallStatistics.contains(profit2023)),
                () -> Assertions.assertTrue(overallStatistics.contains(profit2024)),
                () -> Assertions.assertTrue(overallStatistics.contains(globalChains2023)),
                () -> Assertions.assertTrue(overallStatistics.contains(globalChains2024))
        );
    }
}