package by.bsuir.poit.transport.serialization;

import by.bsuir.poit.transport.exception.PropertiesReadingException;
import by.bsuir.poit.transport.model.Garage;
import by.bsuir.poit.transport.util.PropertiesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CustomSerialization implements Serialization<Garage> {
    private static String inputPath;
    private static String outputPath;
    private static String customFileName;
    private static final String PROPERTIES_FILE_NAME = "src/resources/application.properties";

    @Override
    public void serialize(Garage entity) throws PropertiesReadingException {
        updateProperties();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            String line = mapper.writeValueAsString(entity);
            Path path = Paths.get(outputPath, customFileName);
            Files.write(path, Collections.singletonList(line), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Garage> deserialize() throws PropertiesReadingException {
        updateProperties();
        Path path = Paths.get(inputPath, customFileName);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            return Optional.of(mapper.readValue(path.toFile(), Garage.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private void updateProperties() throws PropertiesReadingException {
        Properties properties = PropertiesUtil.loadProperties(PROPERTIES_FILE_NAME);
        extractProperties(properties);
    }

    private void extractProperties(Properties properties) {
        inputPath = properties.getProperty("inputPath");
        outputPath = properties.getProperty("outputPath");
        customFileName = properties.getProperty("customFileName");
    }
}
