package ru.practicum.javamatcast.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.javamatcast.model.CityStats;
import ru.practicum.javamatcast.service.WeatherStatsService;

import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {
    private final WeatherStatsService weatherStatsService;

    @GetMapping
    public ResponseEntity<List<CityStats>> getStats() {
        log.info("Get /stats called");
        List<CityStats> stats = weatherStatsService.getAllStats();
        return ResponseEntity.ok(stats);
    }
}
