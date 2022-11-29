package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Partitioner {
    /**
     * Reads the input file and splits it into an array with each entry starting with "P"
     * Returns raw List<Person<Data>>
     *
     * Example:
     * [
     *      [
     *          P|John|Doe
     *          T|XXX|YYY
     *          A|XXX|YYY|ZZZ
     *      ],
     *      [
     *          P|Jane|Doe
     *          T|XXX|YYY
     *          A|XXX|YYY|ZZZ
     *      ]
     * ]
     */
    public List<List<String>> partitionInputData(File file) throws IOException {
        var reader = new BufferedReader(new FileReader(file));

        List<List<String>> data = new ArrayList<>();
        List<String> entry = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            // Add raw person data to array and start parsing next person
            if(line.startsWith("P")) {
                if(!entry.isEmpty()) {
                    data.add(entry);
                }
                entry = new ArrayList<>();
            }

            entry.add(line);
        }

        if(!entry.isEmpty())
            data.add(entry); // add last entry

        return data;
    }

    /**
     * Partitions the input data into an array with each entry starting with "P" or "F"
     * Returns raw List<Individual<Data>>
     *
     * Example:
     * [
     *      [
     *          P|John|Doe
     *          T|XXX|YYY
     *          A|XXX|YYY|ZZZ
     *      ],
     *      [
     *          F|Jane|1979
     *          T|XXX|YYY
     *          A|XXX|YYY|ZZZ
     *      ]
     * ]
     */
    public List<List<String>> partitionIndividuals(List<String> inputData) {
        List<List<String>> data = new ArrayList<>();
        List<String> entry = new ArrayList<>();

        if(inputData.isEmpty()) {
            throw new InvalidParameterException("Expected individual to start with P or F, but was empty");
        } else if (!inputData.get(0).startsWith("P") && !inputData.get(0).startsWith("F")) {
            throw new InvalidParameterException(String.format("Expected individual to start with P or F, but was: %s", inputData.get(0)));
        }

        for (String inputLine : inputData) {
            // Add raw person data to array and start parsing next person
            if(inputLine.startsWith("P") || inputLine.startsWith("F")) {
                if(!entry.isEmpty())
                    data.add(entry);

                entry = new ArrayList<>();
            }

            entry.add(inputLine);
        }

        data.add(entry); // add last entry

        return data;
    }
}
