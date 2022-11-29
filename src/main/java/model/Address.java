package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.security.InvalidParameterException;

@JsonInclude(Include.NON_NULL)
public class Address {
    String street;
    String city;
    String zipcode;

    public Address(String data) {
        String[] splitEntry = data.split("\\|");

        // Ensure we set mandatory attributes
        if (!splitEntry[0].startsWith("A")) {
            throw new InvalidParameterException(String.format("Programming error: Address entry should start with [A], but was: %s", splitEntry[0]));
        }

        if (splitEntry.length != 4) {
            throw new InvalidParameterException(String.format("Expected Address format to be A|street|city|zipCode, but was: [%s]", data));
        }

        this.street = splitEntry[1];
        this.city = splitEntry[2];
        this.zipcode = splitEntry[3];
    }

    public Address(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
}
