# VarMetrics

[Сайт VarMetrics](https://varmetrics.herokuapp.com/)

[Сайт VarMetrics-dev](https://varmetrics-dev.herokuapp.com/)


### Сборка 

Сборка проекта со всеми тестами. Профиль run.it отвечает за интеграционные тесты
```
mvn clean install -P run.it
```

Сборка проекта без интеграционных тестов
```
mvn clean install
```

Сборка проекта без всех тестов
```
mvn clean install -DskipTests
```

Сборка на heroku
```
mvn -DskipTests clean dependency:list install
```

Сборка фронта
```
npm install
```

### Запуск
Запуск DEV профиля с поднятием h2
```
mvn clean install spring-boot:run -Dspring.profiles.active=DEV
```

Запуск PROM профиля, требует развернутой базы pg
```
mvn clean install spring-boot:run -Dspring.profiles.active=PROM
```

Запуск на heroku
```
java $JAVA_OPTS -DskipTests -Dspring.profiles.active=PROM -jar ./target/varmetrics.jar
```

Запуск фронта
```
npm start
```

### Возможные проблемы при работе с проектом
- Требуется интернет
- Для остановки npm можно воспользоваться control-c