package org.user.optional;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Optional<T> {
    private final T value;

    private Optional(T value) {
        this.value = value;
    }

    public static <T> Optional<T> offNullable(T value) {
        if (value == null) {
            return empty();
        } else {
            return new Optional<>(value);
        }
    }

    public static <T> Optional<T> empty() {
        return new Optional<>(null);
    }

    public boolean isPresent() {
        return value != null;
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public T orElse(T otherValue) {
        return value != null ? value : otherValue;
    }

    public static <T> Optional<T> of(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return new Optional<>(value);
    }

    public boolean isEmpty() {
        return value == null;
    }

    public T orElseGet(Supplier<? extends T> other) {
        return isPresent() ? value : other.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isPresent()) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }
}