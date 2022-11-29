package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import utils.Partitioner;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    // mandatory
    String firstname;
    String lastname;

    // optional
    Phone phone;
    Address address;
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Family> family;

    public Person(String firstname, String lastname, Phone phone, Address address, List<Family> family) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.family = family;
    }

    public Person(List<String> data) {
        // Ensure we set mandatory attributes
        if (!data.get(0).startsWith("P")) {
            throw new InvalidParameterException(String.format("Programming error: Partitioned person entry should start with [P], but was: %s", data.get(0)));
        }

        // Set optional attributes to null
        this.phone = null;
        this.address = null;
        this.family = new ArrayList<>();

        var partitioner = new Partitioner();
        var partitionedIndividuals = partitioner.partitionIndividuals(data);

        for (List<String> individual : partitionedIndividuals ) {
            if (individual.get(0).startsWith("P")) {
                for (String line : individual) {
                    setPersonAttribute(line);
                }
            } else if (individual.get(0).startsWith("F")) {
                var newChild = new Family(individual);
                this.family.add(newChild);
            } else {
                throw new InvalidParameterException(String.format("Programming error: Line in partitioned data should start with [P or F], but was: %s", individual.get(0)));
            }
        }
    }

    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public Phone getPhone() { return phone; }
    public Address getAddress() { return address; }
    public List<Family> getFamily() { return Collections.unmodifiableList(family); }

    private void setPersonAttribute(String line) {
        if (line.startsWith("P")) {
            String[] splitEntry = line.split("\\|");
            if (splitEntry.length != 3) {
                throw new InvalidParameterException(String.format("Expected Person format to be P|firstname|lastname, but was: [%s]", line));
            }
            this.firstname = splitEntry[1];
            this.lastname = splitEntry[2];
        } else if (line.startsWith("T")) {
            this.phone = new Phone(line);
        } else if (line.startsWith("A")) {
            this.address = new Address(line);
        } else if (line.startsWith("F")) { // should never trigger
            throw new InvalidParameterException("Programming error: Line in Person started with F, was not partitioned correctly");
        } else {
            throw new InvalidParameterException(String.format("Data error: Person should only be followed by [T, A or F], but found line: %s", line));
        }
    }
}
