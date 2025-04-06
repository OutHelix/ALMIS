# Проект "Система мониторинга сервера" (ALMIS)

## Описание проекта
Spring Boot приложение для мониторинга производительности сервера с поэтапным расширением функциональности. Проект начинается с простого REST API и развивается до распределенной системы с веб-интерфейсом.

## Технологический стек
- Java 17
- Spring Boot 3.1+
- Spring Data JPA
- PostgreSQL 15
- Docker + Docker Compose
- Thymeleaf + Chart.js
- JUnit 5 + Mockito

## Поэтапный план разработки

### Этап 1: Базовый REST API
Создание Spring Boot приложения с REST endpoint'ами:
- GET /api/metrics/current - текущие метрики сервера
- GET /api/metrics/cpu - текущая загрузка CPU
- GET /api/metrics/memory - использование памяти

Используемые технологии:
- spring-boot-starter-web
- oshi-core (сбор метрик)
- lombok

### Этап 2: Хранение данных в памяти
Реализация in-memory хранилища метрик:
- Сохранение истории показателей (последние N значений)
- GET /api/metrics/history?hours=24 - история за период
- Конфигурируемый интервал сбора данных

Используемые технологии:
- spring-boot-starter-cache
- Caffeine/Hazelcast

### Этап 3: Интеграция с PostgreSQL
Настройка работы с БД:
- Создание сущности Metric
- Spring Data JPA репозиторий
- Flyway миграции
- REST API для работы с историческими данными

Используемые технологии:
- spring-boot-starter-data-jpa
- postgresql driver
- flyway-core

### Этап 4: Агенты для удаленных серверов
Разработка отдельного модуля monitoring-agent:
- Автономный сбор метрик
- Отправка данных на центральный сервер
- Конфигурация через application.yml
- JWT аутентификация

Используемые технологии:
- spring-boot-starter-web (в агенте)
- spring-security
- jjwt

### Этап 5: Веб-интерфейс
Разработка UI:
- Дашборд с графиками
- Таблица с историей метрик
- Фильтрация по времени/серверам
- Адаптивный дизайн

Используемые технологии:
- spring-boot-starter-thymeleaf
- chart.js
- bootstrap

### Этап 6: Тестирование
Покрытие кода тестами:
- Unit-тесты сервисов
- Интеграционные тесты API
- Тесты безопасности
- Тесты с Testcontainers

Используемые технологии:
- spring-boot-starter-test
- testcontainers
- mockito

### Этап 7: Деплой
Подготовка к production:
- Docker образы для сервера и агентов
- docker-compose конфигурация
- Health checks
- Мониторинг самого приложения

Используемые технологии:
- spring-boot-actuator
- jib-maven-plugin
- docker-compose

## Итоговый стек технологий

### Backend
- Java 17 + Spring Boot 3
- REST API + Spring Security
- Spring Data JPA + Hibernate
- PostgreSQL + Flyway
- OSHI для сбора метрик

### Frontend
- Thymeleaf шаблоны
- Chart.js для графиков
- Bootstrap для стилей
- JavaScript для интерактивности

### DevOps
- Docker контейнеризация
- Docker Compose оркестрация
- GitHub Actions (CI/CD)

### Тестирование
- JUnit 5 + Mockito
- Testcontainers
- Spring Test

Проект готов к использованию как основа для портфолио junior Java разработчика.