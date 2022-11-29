package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FamilyTest {
    @Test
    @DisplayName("Family constructor should create expected Family from input text data")
    void createExpectedAddress() {
        List<String> familyData = new ArrayList<>();
        familyData.add("F|test-name|test-born");
        familyData.add("T|test-mobile|test-landline");
        familyData.add("A|test-street|test-city|test-zipcode");

        var actual = new Family(familyData);
        var expected = getExpected();

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Family constructor should throw if more than 2 attributes are passed")
    void errorOnTooManyAttributes() {
        List<String> testList = new ArrayList<>();
        testList.add("F|test-name|test-born|error");

        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Family(testList));

        assertTrue(e.getMessage().startsWith("Expected Family format to be"));
    }

    @Test
    @DisplayName("Family constructor should throw if less than 2 attributes are passed")
    void errorOnTooFewAttributes() {
        List<String> testList = new ArrayList<>();
        testList.add("F|test-name");

        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Family(testList));

        assertTrue(e.getMessage().startsWith("Expected Family format to be"));
    }

    @Test
    @DisplayName("Family constructor should throw if unexpected child objects are passed")
    void errorOnUnexpectedChildObjects() {
        List<String> testList = new ArrayList<>();
        testList.add("F|test-name|test-born");
        testList.add("X|x-is-invalid|x-is-not-expected");

        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Family(testList));

        assertTrue(e.getMessage().startsWith("Data error: Family should only be followed by"));
    }

    private Family getExpected() {
        var address = new Address("test-street", "test-city", "test-zipcode");
        var phone = new Phone("test-mobile", "test-landline");
        return new Family("test-name", "test-born", phone, address);
    }
}