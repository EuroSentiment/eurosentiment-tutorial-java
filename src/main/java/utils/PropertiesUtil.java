package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.TreeSet;

public class PropertiesUtil {

    private Properties properties;

    public PropertiesUtil(String propertiesFile) throws FileNotFoundException {
        try {
            properties = new Properties();
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            throw new FileNotFoundException("Properties file [" + propertiesFile + "] could not be found");
        }
    }

    /**
     * Retrieves a property from properties file
     *
     * @param property
     * @return
     */
    public String getProperty(String property) {
        return properties.getProperty(property);
    }


    public int getIntProperty(String property) throws NumberFormatException {
        String stringValue = properties.getProperty(property);
        int intValue = 0;
        try {
            intValue = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid int value [" + stringValue + "] for property [" + property + "]");
        }
        return intValue;
    }

   public TreeSet<String> listProperties() {
        return new TreeSet(properties.stringPropertyNames());
    }
}
