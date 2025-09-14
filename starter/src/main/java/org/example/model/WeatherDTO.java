package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherDTO(Location location, Current current) {
    public record Location(
            String name,
            String region,
            String country
    ) {
    }

    public record Current(
            @JsonProperty("temp_c") Double tempC,
            @JsonProperty("wind_kph") Double windKph,
            Double humidity
    ) {
    }
}