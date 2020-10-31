# VarMetrics

[Сайт VarMetrics](https://varmetrics.herokuapp.com/)


### Сборка 

Сборка проекта со всеми тестами
```
mvn clean install
```

Сборка проекта без всех тестов
```
mvn clean install -DskipTests -DskipITs
```

Сборка проекта без интеграционных тестов. Профиль run.it отвечает за интеграционные тесты
```
mvn clean install -DskipITs
```


### Запуск
Запуск DEV профиля с поднятием h2
```
mvn clean install spring-boot:run -Dspring.profiles.active=dev
```

Запуск PROM профиля, требует развернутой базы pg
```
mvn clean install spring-boot:run -Dspring.profiles.active=prom
```

### Возможные проблемы при работе с проектом
- Требуется интернет