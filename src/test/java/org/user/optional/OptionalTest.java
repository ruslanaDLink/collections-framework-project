package org.user.optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class OptionalTest {

    @Test
    void offNullable() {
        //given

        //when
        Optional<String> emptyOptional = Optional.offNullable(null);
        Optional<String> nonEmptyOptional = Optional.offNullable("OPTIONAL_VALUE");

        //then
        Assertions.assertNotNull(emptyOptional);
        Assertions.assertNotNull(nonEmptyOptional);

    }

    @Test
    void empty() {
        //given

        //when
        Optional<Object> empty = Optional.empty();

        //then
        Assertions.assertFalse(empty.isPresent());

    }

    @Test
    void isPresent() {
        //given
        Optional<String> optional = Optional.of("OPTIONAL_VALUE");

        //when
        boolean isPresent = optional.isPresent();

        //then
        Assertions.assertTrue(isPresent);
    }

    @Test
    void get() {
        //given
        Optional<String> optional = Optional.of("OPTIONAL_VALUE");
        Optional<String> emptyOptional = Optional.empty();

        //when
        String value = optional.get();

        //then
        Assertions.assertNotNull(value);
        Assertions.assertThrows(NoSuchElementException.class, emptyOptional::get);
    }

    @Test
    void orElse() {
        //given
        Optional<String> emptyOptional = Optional.empty();

        //when
        String orElse = emptyOptional.orElseGet(() -> "Other value");

        //then
        Assertions.assertTrue(emptyOptional.isEmpty());
        Assertions.assertNotNull(orElse);
    }

    @Test
    void of() {
        //given
        String value = "DEFAULT_VALUE";

        //when
        Optional<String> newOptional = Optional.of(value);

        //then
        Assertions.assertThrows(NullPointerException.class, () -> Optional.of(null));
        Assertions.assertNotNull(newOptional);
    }

    @Test
    void isEmpty() {
        //given
        Optional<Object> emptyOptional = Optional.empty();
        Optional<String> nonEmptyOptional = Optional.of("val");

        //when
        boolean isEmpty = emptyOptional.isEmpty();
        boolean isEmptyNonEmptyOptional = nonEmptyOptional.isEmpty();

        //then
        Assertions.assertTrue(isEmpty);
        Assertions.assertFalse(isEmptyNonEmptyOptional);
    }

    @Test
    void orElseGet() {
        //given
        String defaultValue = "default value";
        Optional<String> emptyOptional = Optional.empty();

        //when
        String value = emptyOptional.orElseGet(() -> defaultValue);

        //then
        Assertions.assertNotNull(value);
        Assertions.assertEquals(value, defaultValue);

    }

    @Test
    void orElseThrowWithoutException() {
        //given
        Optional<String> optional = Optional.of("DEFAULT_OPTIONAL_VALUE");

        //when
        String orElseThrow = optional.orElseThrow(NoSuchElementException::new);

        //then
        Assertions.assertNotNull(orElseThrow);
        Assertions.assertDoesNotThrow(() -> orElseThrow);
    }

    @Test
    void orElseThrowWithException() {
        //given
        Optional<String> emptyOptional = Optional.empty();

        //when
        //...

        //then
        Assertions.assertThrows(NoSuchElementException.class, () -> emptyOptional.orElseThrow(NoSuchElementException::new));
    }
}