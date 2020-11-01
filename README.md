# VarMetrics

[Сайт VarMetrics](https://varmetrics.herokuapp.com/)


### Сборка 

Сборка проекта со всеми тестами
```
mvn clean install
```

Сборка проекта без всех тестов, сейчас "-DskipTests" отключит все тесты
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
mvn clean install spring-boot:run -Dspring.profiles.active=DEV
```

Запуск PROM профиля, требует развернутой базы pg
```
mvn clean install spring-boot:run -Dspring.profiles.active=PROM
java -Dspring.profiles.active=PROM $JAVA_OPTS -jar target/*.war
```

### Возможные проблемы при работе с проектом
- Требуется интернет