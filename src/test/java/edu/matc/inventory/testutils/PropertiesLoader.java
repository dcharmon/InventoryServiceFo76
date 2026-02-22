package edu.matc.inventory.testutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This interface contains a default method that can be used anywhere a Properties
 * object is needed to be loaded.
 *
 */
public interface PropertiesLoader {


    /**
     * This default method will load a properties file into a Properties instance
     * and return it.
     * @param propertiesFilePath a path to a file on the java classpath list
     * @return a populated Properties instance or an empty Properties instance if
     * the file path was not found.
     */
    default Properties loadProperties(String propertiesFilePath){
        Logger logger = LogManager.getLogger(this.getClass());
        Properties properties = new Properties();

        try (InputStream input = this.getClass().getResourceAsStream(propertiesFilePath)) {
            if (input == null) {
                logger.error("Properties file not found: {}", propertiesFilePath);
                return properties;
            }
            properties.load(input);
        } catch (IOException ioException) {
            logger.error("Can't load the properties file", ioException);
        } catch (Exception exception) {
            logger.error("Problem loading properties file", exception);
        }
        return properties;
    }
}
