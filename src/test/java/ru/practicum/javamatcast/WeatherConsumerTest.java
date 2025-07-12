package ru.practicum.javamatcast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.javamatcast.enums.WeatherState;
import ru.practicum.javamatcast.model.WeatherData;
import ru.practicum.javamatcast.service.WeatherConsumer;
import ru.practicum.javamatcast.service.WeatherStatsService;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherConsumerTest {

    @Mock
    private WeatherStatsService weatherStatsService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private WeatherConsumer weatherConsumer;

    @Test
    void consume_shouldProcessValidMessage() throws JsonProcessingException {
        String json = "{\"city\":\"Москва\",\"temperature\":25,\"state\":\"SUNNY\"}";
        WeatherData weatherData = new WeatherData("Москва", 25, WeatherState.SUNNY, LocalDate.now());

        when(objectMapper.readValue(json, WeatherData.class)).thenReturn(weatherData);

        weatherConsumer.consume(json);

        verify(weatherStatsService, times(1)).processWeatherData(weatherData);
    }

    @Test
    void consume_shouldLogErrorForInvalidMessage() throws JsonProcessingException {
        String invalidJson = "invalid_json";

        when(objectMapper.readValue(invalidJson, WeatherData.class))
                .thenThrow(new JsonProcessingException("Invalid JSON") {});

        weatherConsumer.consume(invalidJson);

        verify(weatherStatsService, never()).processWeatherData(any());
    }
}