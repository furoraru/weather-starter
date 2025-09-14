package org.example.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BaseTestConfiguration.class)
public class WeatherPropertiesTest {
    public static final String API_KEY = "test-api-key";
    public static final String DEFAULT_CITY = "Kobe";

    @Autowired
    private WeatherProperties weatherProperties;

    @Test
    void propertySettingTest() {
        assertThat(weatherProperties.apiKey()).isEqualTo(API_KEY);
        assertThat(weatherProperties.defaultCity()).isEqualTo(DEFAULT_CITY);
    }
}