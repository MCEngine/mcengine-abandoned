package com.github.mcengine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Util {
    public static Properties readPropertiesFile(String filePath) {
        // Create a Properties object
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            // Load the properties from the file
            properties.load(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the Properties object
        return properties;
    }
}
