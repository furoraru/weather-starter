package org.example.services;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.request.weather.CurrentWeatherRequester;
import com.github.prominence.openweathermap.api.request.weather.single.SingleResultCurrentWeatherRequestTerminator;

import java.util.Map;

public record WeatherService(String defaultCity, OpenWeatherMapClient client) {
    public Map.Entry<String, String> getTemperature() {
        CurrentWeatherRequester requester = client.currentWeather();

        SingleResultCurrentWeatherRequestTerminator terminator = requester.single()
                .byCityName(defaultCity)
                .unitSystem(UnitSystem.METRIC)
                .retrieve();

        Temperature temperature = terminator.asJava().getTemperature();

        return Map.entry(defaultCity, temperature.toString());
    }
}
