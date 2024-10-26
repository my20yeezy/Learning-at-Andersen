package com.ernie.TicketApp;

import com.ernie.TicketApp.repository.TicketDAO;
import com.ernie.TicketApp.repository.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@ComponentScan
public class ApplicationContext {

    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

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

    @Bean
    public DataSource dataSource() {
        loadProperties();
        return new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return DriverManager.getConnection(dbUrl, username, password);
            }

            @Override
            public PrintWriter getLogWriter() throws SQLException {
                return null;
            }

            @Override
            public void setLogWriter(PrintWriter out) throws SQLException {

            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {

            }

            @Override
            public int getLoginTimeout() throws SQLException {
                return 0;
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                return null;
            }
        };
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(dataSource());
    }

    @Bean
    public TicketDAO ticketDAO() {
        return new TicketDAO(dataSource());
    }
}
