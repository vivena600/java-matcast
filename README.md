![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-brightgreen)
![Kafka](https://img.shields.io/badge/Apache_Kafka-4.0.0-orange)
![H2](https://img.shields.io/badge/H2_Database-2.2.224-lightgrey)

Приложение для сбора и анализа статистики погодных данных с использованием Apache Kafka.

## Основные функции

- **Генерация** случайных данных о погоде (температура, состояние, город)
- **Отправка** данных в Kafka топик
- **Обработка** и сохранение статистики в БД
- **REST API** для доступа к статистике

## Технологический стек

- **Backend**: Java 17, Spring Boot 3.5.3
- **Message Broker**: Apache Kafka
- **Database**: H2 (in-memory)
- **Testing**: JUnit 5, Mockito
- **Build Tool**: Maven

## API
Запрос для получение статистики по всем городам
```
GET http://localhost:8080/stats
```

Пример:
```
  {
        "id": 1,
        "city": "Нижний Новгород",
        "sunnyDays": 0,
        "cloudyDays": 0,
        "rainyDays": 1,
        "maxTemperature": 10,
        "minTemperature": 7
  }
```

