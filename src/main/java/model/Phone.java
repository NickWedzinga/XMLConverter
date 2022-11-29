package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.security.InvalidParameterException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone {
    String mobile;
    String landline;

    public Phone(String data) {
        String[] splitEntry = data.split("\\|");

        // Ensure we set mandatory attributes
        if (!splitEntry[0].startsWith("T")) {
            throw new InvalidParameterException(String.format("Programming error: Phone entry should start with [T], but was: %s", splitEntry[0]));
        }

        if (splitEntry.length != 3) {
            throw new InvalidParameterException(String.format("Expected Phone format to be T|mobile|landline, but was: [%s]", data));
        }

        this.mobile = splitEntry[1];
        this.landline = splitEntry[2];
    }

    public Phone(String mobile, String landline) {
        this.mobile = mobile;
        this.landline = landline;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLandline() {
        return landline;
    }
}
