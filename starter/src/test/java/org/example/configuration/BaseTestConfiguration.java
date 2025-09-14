package org.example.configuration;

import org.example.client.WeatherClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(WeatherProperties.class)
public class BaseTestConfiguration {
    @Bean
    public WeatherClient weatherClient(WeatherProperties weatherProperties) {
        return new WeatherClient(weatherProperties);
    }
}