package ru.practicum.javamatcast.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.practicum.javamatcast.model.WeatherData;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherConsumer {
    private final WeatherStatsService weatherStatsService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "metcast", groupId = "weather-group")
    public void consume(String message) {
        try {
            WeatherData weatherData = objectMapper.readValue(message, WeatherData.class);

            weatherStatsService.processWeatherData(weatherData);

            log.info("Processed weather data: {}", weatherData);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse weather data: {}", message, e);
        }
    }
}
