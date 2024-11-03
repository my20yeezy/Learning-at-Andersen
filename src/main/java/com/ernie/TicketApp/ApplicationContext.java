package com.ernie.TicketApp;

import com.ernie.TicketApp.repository.TicketDAO;
import com.ernie.TicketApp.repository.UserDAO;
import com.ernie.TicketApp.service.TextToStringLoader;
import com.ernie.TicketApp.service.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.ernie.TicketApp")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ApplicationContext {

    private static String dbClassName;
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

    private static void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(input);
            dbClassName = properties.getProperty("db.className");
            dbUrl = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        loadProperties();
        dataSource.setDriverClassName(dbClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(dataSource());
    }

    @Bean
    public TicketDAO ticketDAO() {
        return new TicketDAO(dataSource());
    }

    @Bean
    public TextToStringLoader textToStringLoader() {
        return new TextToStringLoader();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketService(textToStringLoader());
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
