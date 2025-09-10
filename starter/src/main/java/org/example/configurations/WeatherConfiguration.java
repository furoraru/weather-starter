package org.example.configurations;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import org.example.services.WeatherService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WeatherProperties.class)
public class WeatherConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public WeatherService weatherService(WeatherProperties weatherProperties) {
        return new WeatherService(weatherProperties.defaultCity(), new OpenWeatherMapClient(weatherProperties.apiKey()));
    }
}
