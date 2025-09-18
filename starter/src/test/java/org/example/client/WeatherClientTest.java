package org.example.client;

import org.example.configuration.BaseTestConfiguration;
import org.example.exception.WeatherApiException;
import org.example.model.WeatherDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BaseTestConfiguration.class)
@AutoConfigureWireMock
public class WeatherClientTest {
    @Autowired
    private WeatherClient weatherClient;

    @ParameterizedTest
    @MethodSource("provideCityTestData")
    void getWeatherByCity_shouldReturnWeatherData(String cityName, String expectedCity,
                                                  String expectedRegion, String expectedCountry,
                                                  Double expectedTemp, Double expectedWind,
                                                  Double expectedHumidity) {
        WeatherDTO result = weatherClient.getWeatherByCity(cityName);

        assertNotNull(result);
        assertNotNull(result.location());
        assertThat(result.location().name()).isEqualTo(expectedCity);
        assertThat(result.location().region()).isEqualTo(expectedRegion);
        assertThat(result.location().country()).isEqualTo(expectedCountry);

        assertNotNull(result.current());
        assertThat(result.current().tempC()).isEqualTo(expectedTemp);
        assertThat(result.current().windKph()).isEqualTo(expectedWind);
        assertThat(result.current().humidity()).isEqualTo(expectedHumidity);
    }

    private static Stream<Arguments> provideCityTestData() {
        return Stream.of(
                Arguments.of("Kobe", "Kobe", "Hyogo", "Japan", 28.0, 13.0, 94.0),
                Arguments.of("Moscow", "Moscow", "Moscow City", "Russia", 15.4, 13.0, 55.0)
        );
    }

    @Test
    void getWeatherByCity_shouldThrowWeatherApiExceptionForInvalidCity() {
        WeatherApiException exception = assertThrows(WeatherApiException.class, () -> {
            weatherClient.getWeatherByCity("InvalidCity");
        });

        assertThat(exception.getStatusCode().value()).isEqualTo(400);
    }
}