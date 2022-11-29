package utils;

import model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExporterTest {
    @Test
    @DisplayName("Input Person list should be exported to XML as expected")
    void exportPersonsListAsXML() throws IOException {
        var filePath = "src/test/resources/output/example_converted.xml";
        var maybeFile = new File(filePath);
        if(maybeFile.exists())
            maybeFile.delete();

        var exporter = new Exporter();
        exporter.write(getPersonsList(), filePath);

        var maybeCreatedFile = new File(filePath);
        assertTrue(maybeCreatedFile.exists());
    }

    private People getPersonsList() {
        List<Person> persons = new ArrayList<>();

        var phone = new Phone("0768-101801", "08-101801");
        var address1 = new Address("Drottningholms slott", "Stockholm", "10001");
        List<Family> family = new ArrayList<>();

        var childAddress1 = new Address("Haga Slott" ,"Stockholm", "10002");
        var childPhone2 = new Phone("0768-101802", "08-101802");
        var family1 = new Family("Victoria", "1977", null, childAddress1);
        var family2 = new Family("Carl Philip", "1979", childPhone2, null);
        family.add(family1);
        family.add(family2);

        var person1 = new Person("Carl Gustaf", "Bernadotte", phone, address1, family);
        persons.add(person1);

        var address2 = new Address("1600 Pennsylvania Avenue", "Washington, D.C", "20500");
        var person2 = new Person("Barack", "Obama", null, address2, new ArrayList<>());
        persons.add(person2);

        return new People(persons);
    }

}