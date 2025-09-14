package org.example.service;

import org.example.client.WeatherClient;
import org.example.model.WeatherDTO;

public record WeatherService(String defaultCity, WeatherClient client) {
    public WeatherDTO getWeatherByCity() {
        return client.getWeatherByCity(defaultCity);
    }
}
