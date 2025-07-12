package ru.practicum.javamatcast.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "stats")
public class CityStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Column(name = "sunny_days")
    private Integer sunnyDays;

    @Column(name = "cloudy_days")
    private Integer cloudyDays;

    @Column(name = "rainy_days")
    private Integer rainyDays;

    @Column(name = "max_temp")
    private int maxTemperature;

    @Column(name = "min_temp")
    private int minTemperature;
}
