package com.github.mcengine;

import java.sql.*;
import java.util.Properties;

public class MYSQLs {
    private static Connection connection;

    ///////////////
    //  MySql   //
    /////////////
    // Initialize the connection
    public static void initializeConnection(Properties properties) throws SQLException {
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
