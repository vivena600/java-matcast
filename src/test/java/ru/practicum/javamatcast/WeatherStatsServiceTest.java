package ru.practicum.javamatcast;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.javamatcast.enums.WeatherState;
import ru.practicum.javamatcast.model.CityStats;
import ru.practicum.javamatcast.model.WeatherData;
import ru.practicum.javamatcast.repository.CityStatsRepository;
import ru.practicum.javamatcast.service.WeatherStatsService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherStatsServiceTest {
    @Mock
    private CityStatsRepository repository;

    @InjectMocks
    private WeatherStatsService weatherStatsService;

    private CityStats city1 = CityStats.builder()
            .city("Москва")
            .sunnyDays(5)
            .maxTemperature(25)
            .minTemperature(10)
            .build();

    @Test
    void processWeatherData_shouldCreateNewStatsWhenCityNotFound() {
        WeatherData data = new WeatherData("Москва", 25, WeatherState.SUNNY, LocalDate.now());
        when(repository.findByCity("Москва")).thenReturn(Optional.empty());

        weatherStatsService.processWeatherData(data);

        verify(repository, times(1)).save(any(CityStats.class));
    }

    @Test
    void processWeatherData_shouldUpdateExistingStats() {
        WeatherData data = new WeatherData("Москва", 30, WeatherState.SUNNY, LocalDate.now());

        when(repository.findByCity("Москва")).thenReturn(Optional.of(city1));

        weatherStatsService.processWeatherData(data);

        assertEquals(6, city1.getSunnyDays());
        assertEquals(30, city1.getMaxTemperature());
        verify(repository, times(1)).save(city1);
    }
}
