package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.security.InvalidParameterException;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Family {
    // mandatory
    String name;
    String born;

    // optional
    Phone phone;
    Address address;

    public Family(List<String> data) {
        // Ensure we set mandatory attributes
        if (!data.get(0).startsWith("F")) {
            throw new InvalidParameterException(String.format("Programming error: Partitioned family entry should start with [F], but was: %s", data.get(0)));
        }

        // Set optional attributes to null
        this.phone = null;
        this.address = null;

        for (String line : data) {
            setFamilyAttribute(line);
        }
    }

    public Family(String name, String born, Phone phone, Address address) {
        this.name = name;
        this.born = born;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getBorn() {
        return born;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    private void setFamilyAttribute(String line) {
        if (line.startsWith("F")) {
            String[] splitEntry = line.split("\\|");
            if (splitEntry.length != 3) {
                throw new InvalidParameterException(String.format("Expected Family format to be F|name|born, but was: [%s]", line));
            }
            this.name = splitEntry[1];
            this.born = splitEntry[2];
        } else if (line.startsWith("T")) {
            this.phone = new Phone(line);
        } else if (line.startsWith("A")) {
            this.address = new Address(line);
        } else {
            throw new InvalidParameterException(String.format("Data error: Family should only be followed by [T or A], but found line: %s", line));
        }
    }
}
