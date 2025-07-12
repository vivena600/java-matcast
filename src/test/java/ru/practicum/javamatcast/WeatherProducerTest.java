package ru.practicum.javamatcast;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.javamatcast.model.WeatherData;
import ru.practicum.javamatcast.service.KafkaWeatherService;
import ru.practicum.javamatcast.service.WeatherProducer;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherProducerTest {

    @Mock
    private KafkaWeatherService kafkaWeatherService;

    @InjectMocks
    private WeatherProducer weatherProducer;

    @Test
    void produceWeather_shouldSendDataToKafka() {
        weatherProducer.produceWeather();

        verify(kafkaWeatherService, times(1)).sendWeather(any(WeatherData.class));
    }
}
