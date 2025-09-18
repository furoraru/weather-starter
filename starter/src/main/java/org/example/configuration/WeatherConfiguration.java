package org.example.configuration;

import org.example.client.WeatherClient;
import org.example.service.WeatherService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(WeatherProperties.class)
public class WeatherConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public WeatherService weatherService(WeatherProperties weatherProperties, WeatherClient weatherClient) {
        return new WeatherService(weatherProperties.defaultCity(), weatherClient);
    }
}