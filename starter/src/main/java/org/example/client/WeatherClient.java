package org.example.client;

import org.example.configuration.WeatherProperties;
import org.example.exception.WeatherApiException;
import org.example.model.WeatherDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherClient {
    public static final String WEATHER_BY_CITY_URI = "/v1/current.json?key={apiKey}&q={cityName}";

    private final WeatherProperties weatherProperties;
    private final RestClient restClient;

    public WeatherClient(WeatherProperties properties) {
        this.weatherProperties = properties;
        this.restClient = RestClient.builder()
                .baseUrl(weatherProperties.baseUrl())
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public WeatherDTO getWeatherByCity(String cityName) {
        return restClient.get()
                .uri(WEATHER_BY_CITY_URI, weatherProperties.apiKey(), cityName)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new WeatherApiException("Weather API error for city " + cityName, response.getStatusCode());
                })
                .body(WeatherDTO.class);
    }
}