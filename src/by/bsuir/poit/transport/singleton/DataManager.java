package by.bsuir.poit.transport.singleton;

import by.bsuir.poit.transport.exception.PropertiesReadingException;
import by.bsuir.poit.transport.util.PropertiesUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class DataManager {

    private static final String INPUT_PATH_PROPERTY_NAME = "inputPath";
    private static final String OUTPUT_PATH_PROPERTY_NAME = "outputPath";
    private static final String HANDLED_FILE_PROPERTY_NAME = "handledFileName";
    private String inputPath;
    private String outputPath;
    private String handledFileName;
    private static final String PROPERTIES_FILE_NAME = "src/resources/application.properties";

    public void saveToFile(String data) throws PropertiesReadingException {
        updateProperties();
        Path path = Paths.get(outputPath, handledFileName);
        try (Writer fileWriter = new FileWriter(path.toFile());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String loadFromFile() throws PropertiesReadingException {
        updateProperties();
        Path path = Paths.get(inputPath, handledFileName);
        try (Scanner scanner = new Scanner(path)) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append(scanner.next());
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void updateProperties() throws PropertiesReadingException {
        Properties properties = PropertiesUtil.loadProperties(PROPERTIES_FILE_NAME);
        extractProperties(properties);
    }

    private void extractProperties(Properties properties) {
        inputPath = properties.getProperty(INPUT_PATH_PROPERTY_NAME);
        outputPath = properties.getProperty(OUTPUT_PATH_PROPERTY_NAME);
        handledFileName = properties.getProperty(HANDLED_FILE_PROPERTY_NAME);
    }
}
