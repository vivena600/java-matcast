package ru.practicum.javamatcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaMatcastApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMatcastApplication.class, args);
    }

}
