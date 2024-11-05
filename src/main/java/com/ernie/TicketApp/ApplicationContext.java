package com.ernie.TicketApp;

import com.ernie.TicketApp.repository.TicketDAO;
import com.ernie.TicketApp.repository.UserDAO;
import com.ernie.TicketApp.service.TextToStringLoader;
import com.ernie.TicketApp.service.TicketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Value("${db.className}")
    private String dbClassName;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(dataSource(), jdbcTemplate(dataSource()));
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
        return new TicketService(userDAO(), textToStringLoader());
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
