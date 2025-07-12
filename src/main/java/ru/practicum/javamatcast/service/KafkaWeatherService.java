package ru.practicum.javamatcast.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.practicum.javamatcast.model.WeatherData;

@Service
@RequiredArgsConstructor
public class KafkaWeatherService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendWeather(WeatherData weatherData) {
        try {
            String json = objectMapper.writeValueAsString(weatherData);
            kafkaTemplate.send("metcast", weatherData.getCity(), json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize weather data", e);
        }
    }
}
