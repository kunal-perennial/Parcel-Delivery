package com.example.parceldelivery1.config;

import com.example.parceldelivery1.ParcelDelivery1Application;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
    @Bean
    public Logger getLogger() {
        Logger log = LogManager.getLogger(String.valueOf(ParcelDelivery1Application.class));
        PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
        return log;
    }
}
