package com.github.mcengine;

import java.sql.*;
import java.util.Properties;

public class MYSQLs {
    private static String path;
    private static Properties properties; // Delay initialization
    private static Connection connection;

    ///////////////////////
    //  Needed variable //
    /////////////////////
    public static String getPath() {
        return path;
    }
    
    public static void setPath(String newPath) {
        path = newPath;
        properties = Util.readPropertiesFile(path); // Initialize properties here
    }

    ///////////////
    //  MySql   //
    /////////////
    // Initialize the connection
    public static void initializeConnection() throws SQLException {
        String dbUrl = properties.getProperty("url");
        String dbUsername = properties.getProperty("username");
        String dbPassword = properties.getProperty("password");
        String dbName = properties.getProperty("dbname");
        String dbPort = properties.getProperty("port");

        String fullDbUrl = dbUrl + ':' + dbPort + '/' + dbName;
        connection = DriverManager.getConnection(fullDbUrl, dbUsername, dbPassword);
    }

    // Close the connection
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Execute SQL query
    public static void executeQuery(String query) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established.");
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }
}
