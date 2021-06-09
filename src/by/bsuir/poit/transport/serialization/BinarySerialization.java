package by.bsuir.poit.transport.serialization;

import by.bsuir.poit.transport.exception.PropertiesReadingException;
import by.bsuir.poit.transport.model.Garage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

public enum BinarySerialization implements Serialization<Garage> {
    INSTANCE;

    private static String inputPath;
    private static String outputPath;
    private static String serializeFileName;
    private static final String PROPERTIES_FILE_NAME = "src/resources/application.properties";

    @Override
    public void serialize(Garage entity) throws PropertiesReadingException {
        loadProperties();
        Path path = Paths.get(outputPath, serializeFileName);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            outputStream.writeObject(entity);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Optional<Garage> deserialize() throws PropertiesReadingException {
        loadProperties();
        Path path = Paths.get(inputPath, serializeFileName);
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            Garage garage = (Garage) inputStream.readObject();
            return Optional.of(garage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    private void loadProperties() throws PropertiesReadingException {
        Properties properties = new Properties();
        try (FileInputStream fileInput = new FileInputStream(PROPERTIES_FILE_NAME)) {
            properties.load(fileInput);
            extractProperties(properties);
        } catch (FileNotFoundException e) {
            throw new PropertiesReadingException("Property file cannot be found");
        } catch (IOException e) {
            throw new PropertiesReadingException("Property file reading error");
        }
    }

    private void extractProperties(Properties properties) {
        inputPath = properties.getProperty("inputPath");
        outputPath = properties.getProperty("outputPath");
        serializeFileName = properties.getProperty("serializeFileName");
    }
}
