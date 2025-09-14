package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.WeatherDTO;
import org.example.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@AllArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping()
    public WeatherDTO getWeatherByCity() {
        return weatherService.getWeatherByCity();
    }
}
