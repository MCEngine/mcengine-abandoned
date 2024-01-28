package com.github.mcengine;

import java.util.Properties;

public class MYSQLs {
    private static String path;
    private static Properties properties; // Delay initialization

    public static String getPath() {
        return path;
    }
    
    public static void setPath(String newPath) {
        path = newPath;
        properties = Util.readPropertiesFile(path); // Initialize properties here
    }

    // If you need to access properties from outside, consider adding a getter for it
    public static Properties getProperties() {
        return properties;
    }
}
