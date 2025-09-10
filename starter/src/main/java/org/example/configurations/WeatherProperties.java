package org.example.configurations;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "weather")
public record WeatherProperties(@NotBlank(message = "apiKey is required") String apiKey, String defaultCity) {
    public WeatherProperties {
        if (defaultCity == null || defaultCity.isBlank()) {
            defaultCity = "Moscow";
        }
    }
}