package utils;

import model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonsParserTest {

    @Test
    @DisplayName("Input file should be converted to an expected Person list")
    void convertInputFiles() throws IOException {
        var parser = new PersonsParser();

        var actualPersons = parser.parse("src/test/resources/input/example.txt");
        var expectedPersons = testPersons();

        assertThat(actualPersons)
                .usingRecursiveComparison()
                .isEqualTo(expectedPersons);
    }

    @Test
    @DisplayName("PersonParser should fail if the input file specified does not exist")
    void failIfInputFileMissing() throws IOException {
        var parser = new PersonsParser();

        Exception e = assertThrows(
                IOException.class,
                () ->parser.parse("src/test/resources/input/missing-file.txt"));

        assertTrue(e.getMessage().startsWith("Expected input file with name"));

    }

    private People testPersons() {
        List<Person> list = new ArrayList<>();

        // Person 1
        List<Family> familyList1 = new ArrayList<>();
        var childAddress1 = new Address("Haga Slott", "Stockholm", "10002");
        var childPhone1 = new Phone("0768-101802", "08-101802");
        var child1 = new Family("Victoria", "1977", null, childAddress1);
        var child2 = new Family("Carl Philip", "1979", childPhone1, null);
        familyList1.add(child1);
        familyList1.add(child2);
        var phone1 = new Phone("0768-101801", "08-101801");
        var address1 = new Address("Drottningholms slott", "Stockholm", "10001");
        var person1 = new Person("Carl Gustaf", "Bernadotte", phone1, address1, familyList1);

        // Person 2
        var address2 = new Address("1600 Pennsylvania Avenue", "Washington, D.C", "20500");
        var person2 = new Person("Barack", "Obama", null, address2, new ArrayList<>());

        list.add(person1);
        list.add(person2);

        return new People(list);
    }
}