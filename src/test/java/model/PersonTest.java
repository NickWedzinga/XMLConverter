package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("Person constructor should create expected Person from input text data")
    void createExpectedPerson() {

        var actualPerson = new Person(getPersonData());
        var expectedPerson = getPerson();

        assertThat(actualPerson)
                .usingRecursiveComparison()
                .isEqualTo(expectedPerson);
    }

    @Test
    @DisplayName("Person constructor should throw if more than 2 attributes are passed")
    void errorOnTooManyAttributes() {
        List<String> data = new ArrayList<>();
        data.add("P|test-name|test-lastname|error");

        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Person(data));

        assertTrue(e.getMessage().startsWith("Expected Person format to be"));
    }

    @Test
    @DisplayName("Person constructor should throw if less than 2 attributes are passed")
    void errorOnTooFewAttributes() {
        List<String> data = new ArrayList<>();
        data.add("P|test-name");

        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Person(data));

        assertTrue(e.getMessage().startsWith("Expected Person format to be"));
    }

    @Test
    @DisplayName("Person constructor should throw if unexpected child objects are passed")
    void errorOnUnexpectedChildObjects() {
        List<String> data = new ArrayList<>();
        data.add("P|test-name|test-born");
        data.add("X|invalid-object");

        Exception e = assertThrows(
                InvalidParameterException.class,
                () -> new Person(data));

        assertTrue(e.getMessage().startsWith("Data error: Person should only be followed by"));
    }

    private List<String> getPersonData() {
        List<String> list = new ArrayList<>();
        list.add("P|Carl Gustaf|Bernadotte");
        list.add("T|0768-101801|08-101801");
        list.add("A|Drottningholms slott|Stockholm|10001");
        list.add("F|Victoria|1977");
        list.add("A|Haga Slott|Stockholm|10002");
        list.add("F|Carl Philip|1979");
        list.add("T|0768-101802|08-101802");

        return list;
    }

    private Person getPerson() {
        var phone = new Phone("0768-101801", "08-101801");
        var address = new Address("Drottningholms slott", "Stockholm", "10001");
        List<Family> family = new ArrayList<>();

        var childAddress1 = new Address("Haga Slott" ,"Stockholm", "10002");
        var childPhone2 = new Phone("0768-101802", "08-101802");
        var family1 = new Family("Victoria", "1977", null, childAddress1);
        var family2 = new Family("Carl Philip", "1979", childPhone2, null);
        family.add(family1);
        family.add(family2);

        return new Person("Carl Gustaf", "Bernadotte", phone, address, family);
    }
}