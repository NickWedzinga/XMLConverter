package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.People;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Exporter {
    private final java.util.logging.Logger logger = Logger.getLogger("exporter-logger");

    public void write(People people, String outputFilePath) throws IOException {
        ObjectMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File(outputFilePath);

        if (!file.createNewFile()) {
            throw new IOException(String.format("File with path [%s] already exists, please remove the pre-existing XML or convert a different input fle", outputFilePath));
        } else {
            logger.info(String.format("Empty XML created with path: [%s]", outputFilePath));
        }

        logger.info("Writing converted input data to XML");
        mapper.writeValue(file, people);
    }
}
