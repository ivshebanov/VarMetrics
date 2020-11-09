# VarMetrics

[Сайт VarMetrics](https://varmetrics.herokuapp.com/)


### Сборка 

Сборка проекта со всеми тестами
```
mvn clean install
```

Сборка проекта без тестов
```
mvn clean install -DskipTests
```

Сборка на heroku
```
mvn -DskipTests clean dependency:list install
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

### Возможные проблемы при работе с проектом
- Требуется интернет