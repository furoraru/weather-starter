package org.example.configuration;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "weather")
public record WeatherProperties(
        String baseUrl,
        @NotBlank(message = "apiKey is required") String apiKey,
        @NotBlank(message = "defaultCity is required") String defaultCity) {
    public static final String WEATHER_BASE_URL = "http://api.weatherapi.com";

    public WeatherProperties {
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = WEATHER_BASE_URL;
        }
    }
}