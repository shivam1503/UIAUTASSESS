package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file: " + e.getMessage());
        }
    }

    // Get property by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Get integer property
    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    // Get boolean property
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
