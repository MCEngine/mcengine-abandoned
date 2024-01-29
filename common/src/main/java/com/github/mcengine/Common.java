package com.github.mcengine;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Common {
    // Check if directory exits
    public static void checkDir(File dir) {
        if (dir.exists()) {
            System.out.println("Directories exists: " + dir.getAbsolutePath());
        } else {
            // Create directories recursively if they don't exist
            if (dir.mkdirs()) {
                System.out.println("Directories created: " + dir.getAbsolutePath());
            } else {
                System.out.println("Failed to create directories.");
            }
        }
    }

    // Check if the file exists
    public static void checkFile(File file) {
        if (file.exists()) {
            System.out.println("The file already exists: " + file.getAbsolutePath());
        } else {
            try {
                // Create parent directories if they don't exist
                File fileParent = file.getParentFile();
                checkDir(fileParent);

                // Create the file
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("File creation failed. The file may already exist.");
                }
            } catch (IOException e) {
                System.err.println("Error creating the file: " + e.getMessage());
            }
        }
    }

    public static void run(String path, File[] files, String[] querys) throws SQLException {
        for (File file : files) {
            file = new File(path + '/' + file);
            checkFile(file);
        }
        Properties properties = Util.readPropertiesFile(path + "/db.properties");
        for (String query : querys) {
            MYSQLs.initializeConnection(properties);
            MYSQLs.executeQuery(query);
            MYSQLs.closeConnection();
        }
    }
}
