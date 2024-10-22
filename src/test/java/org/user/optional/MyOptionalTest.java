package org.user.optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class MyOptionalTest {

    @Test
    void offNullable() {
        //given

        //when
        MyOptional<String> emptyMyOptional = MyOptional.offNullable(null);
        MyOptional<String> nonEmptyMyOptional = MyOptional.offNullable("OPTIONAL_VALUE");

        //then
        Assertions.assertNotNull(emptyMyOptional);
        Assertions.assertNotNull(nonEmptyMyOptional);

    }

    @Test
    void empty() {
        //given

        //when
        MyOptional<Object> empty = MyOptional.empty();

        //then
        Assertions.assertFalse(empty.isPresent());

    }

    @Test
    void isPresent() {
        //given
        MyOptional<String> myOptional = MyOptional.of("OPTIONAL_VALUE");

        //when
        boolean isPresent = myOptional.isPresent();

        //then
        Assertions.assertTrue(isPresent);
    }

    @Test
    void get() {
        //given
        MyOptional<String> myOptional = MyOptional.of("OPTIONAL_VALUE");
        MyOptional<String> emptyMyOptional = MyOptional.empty();

        //when
        String value = myOptional.get();

        //then
        Assertions.assertNotNull(value);
        Assertions.assertThrows(NoSuchElementException.class, emptyMyOptional::get);
    }

    @Test
    void orElse() {
        //given
        MyOptional<String> emptyMyOptional = MyOptional.empty();

        //when
        String orElse = emptyMyOptional.orElseGet(() -> "Other value");

        //then
        Assertions.assertTrue(emptyMyOptional.isEmpty());
        Assertions.assertNotNull(orElse);
    }

    @Test
    void of() {
        //given
        String value = "DEFAULT_VALUE";

        //when
        MyOptional<String> newMyOptional = MyOptional.of(value);

        //then
        Assertions.assertThrows(NullPointerException.class, () -> MyOptional.of(null));
        Assertions.assertNotNull(newMyOptional);
    }

    @Test
    void isEmpty() {
        //given
        MyOptional<Object> emptyMyOptional = MyOptional.empty();
        MyOptional<String> nonEmptyMyOptional = MyOptional.of("val");

        //when
        boolean isEmpty = emptyMyOptional.isEmpty();
        boolean isEmptyNonEmptyOptional = nonEmptyMyOptional.isEmpty();

        //then
        Assertions.assertTrue(isEmpty);
        Assertions.assertFalse(isEmptyNonEmptyOptional);
    }

    @Test
    void orElseGet() {
        //given
        String defaultValue = "default value";
        MyOptional<String> emptyMyOptional = MyOptional.empty();

        //when
        String value = emptyMyOptional.orElseGet(() -> defaultValue);

        //then
        Assertions.assertNotNull(value);
        Assertions.assertEquals(value, defaultValue);

    }

    @Test
    void orElseThrowWithoutException() {
        //given
        MyOptional<String> myOptional = MyOptional.of("DEFAULT_OPTIONAL_VALUE");

        //when
        String orElseThrow = myOptional.orElseThrow(NoSuchElementException::new);

        //then
        Assertions.assertNotNull(orElseThrow);
        Assertions.assertDoesNotThrow(() -> orElseThrow);
    }

    @Test
    void orElseThrowWithException() {
        //given
        MyOptional<String> emptyMyOptional = MyOptional.empty();

        //when
        //...

        //then
        Assertions.assertThrows(NoSuchElementException.class, () -> emptyMyOptional.orElseThrow(NoSuchElementException::new));
    }
}