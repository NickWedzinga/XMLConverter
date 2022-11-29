package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    @DisplayName("Address constructor should create expected Address from input text data")
    void createExpectedAddress() {
        var actual = new Address("A|test-street|test-city|test-zipcode");
        var expected = getExpected();

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Address constructor should throw if more than 3 attributes are passed")
    void errorOnTooManyAttributes() {
        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Address("A|test-street|test-city|test-zipcode|error"));

        assertTrue(e.getMessage().startsWith("Expected Address format to be"));
    }

    @Test
    @DisplayName("Address constructor should throw if less than 3 attributes are passed")
    void errorOnTooFewAttributes() {
        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Address("A|test-street|test-city"));

        assertTrue(e.getMessage().startsWith("Expected Address format to be"));
    }

    @Test
    @DisplayName("Address constructor should throw if incorrect input data line is passed")
    void errorOnIncorrectInputData() {
        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Address("X|test-street|test-city|test-zipcode"));

        assertTrue(e.getMessage().startsWith("Programming error: Address entry should start with"));
    }

    private Address getExpected() {
        return new Address("test-street", "test-city", "test-zipcode");
    }
}