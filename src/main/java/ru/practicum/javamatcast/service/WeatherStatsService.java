package ru.practicum.javamatcast.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.javamatcast.enums.WeatherState;
import ru.practicum.javamatcast.model.CityStats;
import ru.practicum.javamatcast.model.WeatherData;
import ru.practicum.javamatcast.repository.CityStatsRepository;

import java.util.List;

/***
 * Сервис для анализа статистики по погоде
 */
@Service
@RequiredArgsConstructor
@Transactional
public class WeatherStatsService {
    private final CityStatsRepository repository;

    public void processWeatherData(WeatherData data) {
        repository.findByCity(data.getCity()).ifPresentOrElse(
                cityStats -> updateCityStats(cityStats, data),
                () -> createNewCityStats(data)
        );
    }

    /***
     * Метод для обновления статистики по городу
     * @param cityStats
     * @param data
     */
    private CityStats updateCityStats(CityStats cityStats, WeatherData data) {
        if (data.getState() == WeatherState.SUNNY) cityStats.setSunnyDays(cityStats.getSunnyDays() + 1);
        else if (data.getState() == WeatherState.CLOUDY) cityStats.setCloudyDays(cityStats.getCloudyDays() + 1);
        else cityStats.setSunnyDays(cityStats.getSunnyDays());

        if (data.getTemperature() > cityStats.getMaxTemperature()) cityStats.setMaxTemperature(data.getTemperature());
        if (data.getTemperature() < cityStats.getMinTemperature()) cityStats.setMinTemperature(data.getTemperature());

        return repository.save(cityStats);
    }

    /***
     * Метод создания статистки по новому городу
     */
    private CityStats createNewCityStats(WeatherData data) {
        CityStats stats = CityStats.builder()
                .city(data.getCity())
                .sunnyDays(data.getState() == WeatherState.SUNNY ? 1 : 0)
                .cloudyDays(data.getState() == WeatherState.CLOUDY ? 1 : 0)
                .rainyDays(data.getState() == WeatherState.RAINY ? 1 : 0)
                .maxTemperature(data.getTemperature())
                .minTemperature(data.getTemperature())
                .build();
        return repository.save(stats);
    }


    /***
     * Метод для получения статистики по всем городам
     */
    public List<CityStats> getAllStats() {
        return repository.findAll();
    }
}
