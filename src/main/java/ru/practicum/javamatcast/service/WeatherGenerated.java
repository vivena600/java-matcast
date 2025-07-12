package ru.practicum.javamatcast.service;

import ru.practicum.javamatcast.enums.WeatherState;
import ru.practicum.javamatcast.model.WeatherData;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class WeatherGenerated {
    private static final List<String> CITIES = List.of(
            "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань",
            "Нижний Новгород", "Челябинск", "Самара", "Омск", "Ростов-на-Дону"
    );

    /**
     * Метод для генерации погоды
     * @return WeatherData
     */
    public static WeatherData genertedRandomWeather() {
        Random random = new Random();
        String city = CITIES.get(random.nextInt(CITIES.size()));
        int temperature = random.nextInt(35);

        return WeatherData.builder()
                .city(city)
                .date(LocalDate.now())
                .temperature(temperature)
                .state(getState(temperature))
                .build();
    }

    private static WeatherState getState(int temperature) {
        if (temperature >= 30) {
            return WeatherState.SUNNY;
        } else if (temperature >= 25) {
            return WeatherState.CLOUDY;
        } else {
            return WeatherState.RAINY;
        }
    }
}
