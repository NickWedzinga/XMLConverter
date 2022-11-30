import utils.Exporter;
import utils.PersonsParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class App {
    public static void main( String[] args ) {
        try {
            var logger = Logger.getLogger("main-logger");

            var fileName = "input-data";
            var inputDirectory = String.format("src/main/resources/input/%s.txt", fileName);
            var outputDirectory = String.format("src/main/resources/output/%s_converted.xml", fileName);

            var parser = new PersonsParser();
            var parsedInputData = parser.parse(inputDirectory);

            var exporter = new Exporter();
            exporter.write(parsedInputData, outputDirectory);

            var maybeCreatedFile = new File(outputDirectory);

            if (maybeCreatedFile.exists() && !maybeCreatedFile.isDirectory()) {
                logger.info(String.format("Successfully converted file, the newly created XML file can be found in [%s]", outputDirectory));
            } else {
                throw new IOException("Programming error: No errors raised thus far, but the XML file was never created");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
