package com.github.mcengine;

import java.io.File;

public class Check {
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
}
