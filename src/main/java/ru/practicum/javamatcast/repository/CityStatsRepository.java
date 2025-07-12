package ru.practicum.javamatcast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.javamatcast.model.CityStats;

import java.util.Optional;

public interface CityStatsRepository extends JpaRepository<CityStats, Integer> {

    @Query("SELECT cs FROM CityStats cs WHERE cs.city = :city")
    Optional<CityStats> findByCity(String city);

}
