package ru.practicum.javamatcast.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.practicum.javamatcast.model.WeatherData;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherProducer {

    private final KafkaWeatherService kafkaWeatherService;

    @Scheduled(fixedRate = 20000)
    public void produceWeather() {
        WeatherData weatherData = WeatherGenerated.genertedRandomWeather();
        kafkaWeatherService.sendWeather(weatherData);
        log.info("Produced weather: {}", weatherData);
    }
}
