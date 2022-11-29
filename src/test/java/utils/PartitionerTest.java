package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartitionerTest {

    @Test
    @DisplayName("Partitioner should be able to parse and split input file per person")
    void partitionInputFile() throws IOException {
        var url = this.getClass().getResource("../input/partition-data.txt");
        assert url != null;

        var file = new File(url.getPath());
        var partitioner = new Partitioner();

        List<List<String>> actualData = partitioner.partitionInputData(file);

        assertEquals(testPartitionedList(), actualData);
    }

    @Test
    @DisplayName("Partitioner should be able to split input data on Person and Family")
    void partitionIndividualsFromInputData() {
        var partitioner = new Partitioner();

        List<List<String>> actualData = partitioner.partitionIndividuals(getListOfIndividuals());

        assertEquals(getExpectedPartitionedIndividuals(), actualData);

    }

    private List<List<String>> testPartitionedList() {
        List<List<String>> list = new ArrayList<>();
        List<String> entry1 = new ArrayList<>();
        List<String> entry2 = new ArrayList<>();

        entry1.add("P|Carl Gustaf|Bernadotte");
        entry1.add("T|0768-101801|08-101801");

        entry2.add("P|Barack|Obama");

        list.add(entry1);
        list.add(entry2);

        return list;
    }

    private List<String> getListOfIndividuals() {
        List<String> individuals = new ArrayList<>();

        individuals.add("P|Carl Gustaf|Bernadotte");
        individuals.add("T|0768-101801|08-101801");
        individuals.add("F|Victoria|1977");
        individuals.add("A|Haga Slott|Stockholm|10002");
        individuals.add("F|Carl Philip|1979");
        individuals.add("T|0768-101802|08-101802");

        return individuals;
    }

    private List<List<String>> getExpectedPartitionedIndividuals() {
        List<List<String>> list = new ArrayList<>();
        List<String> entry1 = new ArrayList<>();
        List<String> entry2 = new ArrayList<>();
        List<String> entry3 = new ArrayList<>();

        entry1.add("P|Carl Gustaf|Bernadotte");
        entry1.add("T|0768-101801|08-101801");

        entry2.add("F|Victoria|1977");
        entry2.add("A|Haga Slott|Stockholm|10002");

        entry3.add("F|Carl Philip|1979");
        entry3.add("T|0768-101802|08-101802");

        list.add(entry1);
        list.add(entry2);
        list.add(entry3);

        return list;
    }
}