package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeopleTest {
    @Test
    @DisplayName("People constructor should create expected People from input person list")
    void createExpected() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("test-name", "test-lastname", null,null, new ArrayList<>()));

        var people = new People(personList);

        assertEquals(personList, people.getPeople());
    }
}