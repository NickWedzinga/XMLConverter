package utils;

import model.People;
import model.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonsParser {

    private final Partitioner partitioner = new Partitioner();

    public People parse(File file) throws IOException {
        List<Person> persons = new ArrayList<>();
        List<List<String>> data = partitioner.partitionInputData(file);

        for (List<String> entry : data) {
            var person = new Person(entry);
            persons.add(person);
        }

        return new People(persons);
    }
}
