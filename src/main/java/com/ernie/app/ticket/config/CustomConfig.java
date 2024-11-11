package com.ernie.app.ticket.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {

    @Bean
    @ConditionalOnProperty(name = "myBean.enabled", havingValue = "true", matchIfMissing = false)
    public ThisIsMyFirstConditionalBean thisIsMyFirstConditionalBean() {
        return new ThisIsMyFirstConditionalBean();
    }
}
