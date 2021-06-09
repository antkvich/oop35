package by.bsuir.poit.transport.serialization;

import by.bsuir.poit.transport.exception.PropertiesReadingException;
import by.bsuir.poit.transport.model.Garage;
import by.bsuir.poit.transport.util.PropertiesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

public enum JsonSerialization implements Serialization<Garage> {
    INSTANCE;

    private static String inputPath;
    private static String outputPath;
    private static String jsonFileName;
    private static final String PROPERTIES_FILE_NAME = "src/resources/application.properties";

    @Override
    public void serialize(Garage entity) throws PropertiesReadingException {
        updateProperties();
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(outputPath, jsonFileName);
        try {
            mapper.writeValue(path.toFile(), entity);
        } catch (Exception e) {
            System.out.println("Can't output in json file.");
        }
    }

    public String serializeToString(Garage entity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(entity);
        } catch (Exception e) {
            System.out.println("Can't output in json file.");
        }
        return null;
    }

    @Override
    public Optional<Garage> deserialize() throws PropertiesReadingException {
        updateProperties();
        Path path = Paths.get(inputPath, jsonFileName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Garage garage = mapper.readValue(path.toFile(), Garage.class);
            return Optional.of(garage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Garage> deserializeFromString(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Garage garage = mapper.readValue(data, Garage.class);
            return Optional.of(garage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
        jsonFileName = properties.getProperty("jsonFileName");
    }
}
