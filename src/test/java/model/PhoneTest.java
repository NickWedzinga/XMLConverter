package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    @Test
    @DisplayName("Phone constructor should create expected Phone from input text data")
    void createExpected() {
        var actual = new Phone("T|test-mobile|test-landline");
        var expected = getExpected();

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Phone constructor should throw if more than 2 attributes are passed")
    void errorOnTooManyAttributes() {
        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Phone("T|test-mobile|test-landline|error"));

        assertTrue(e.getMessage().startsWith("Expected Phone format to be"));
    }

    @Test
    @DisplayName("Phone constructor should throw if less than 2 attributes are passed")
    void errorOnTooFewAttributes() {
        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Phone("T|test-mobile"));

        assertTrue(e.getMessage().startsWith("Expected Phone format to be"));
    }

    @Test
    @DisplayName("Phone constructor should throw if incorrect input data line is passed")
    void errorOnIncorrectDataLine() {
        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Phone("X|test-mobile|test-landline"));

        assertTrue(e.getMessage().startsWith("Programming error: Phone entry should start with"));
    }

    private Phone getExpected() {
        return new Phone("test-mobile", "test-landline");
    }
}