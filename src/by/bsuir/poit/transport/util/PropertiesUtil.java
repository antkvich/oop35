package by.bsuir.poit.transport.util;

import by.bsuir.poit.transport.exception.PropertiesReadingException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private PropertiesUtil() {

    }

    public static Properties loadProperties(String propertiesFileName) throws PropertiesReadingException {
        Properties properties = new Properties();
        try (FileInputStream fileInput = new FileInputStream(propertiesFileName)) {
            properties.load(fileInput);
            return properties;
        } catch (FileNotFoundException e) {
            throw new PropertiesReadingException("Property file cannot be found");
        } catch (IOException e) {
            throw new PropertiesReadingException("Property file reading error");
        }
    }
}
