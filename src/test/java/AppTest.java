import model.People;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Exporter;
import utils.PersonsParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    @DisplayName("Input file should be read, transformed and exported to XML")
    void convertExpectedXML() throws IOException {
        // import and transform
        var parser = new PersonsParser();
        var people = parser.parse("src/test/resources/input/example.txt");

        // export xml
        var outputFile = "src/test/resources/output/example_converted.xml";
        var maybeFile = new File(outputFile);
        if (maybeFile.exists())
            maybeFile.delete();

        var exporter = new Exporter();
        exporter.write(people, outputFile);

        // assert new file has been created
        var maybeCreatedFile = new File(outputFile);
        assertTrue(maybeCreatedFile.exists());
    }
}