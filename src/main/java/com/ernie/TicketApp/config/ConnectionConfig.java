package com.ernie.TicketApp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {

    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    private static Connection connection;

    private static void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(input);
            dbUrl = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connect() {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Connected to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        loadProperties();
        connect();
        return connection;
    }

}
