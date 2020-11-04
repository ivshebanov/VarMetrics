#сборка
FROM maven
WORKDIR /usr/src/varmetrics
COPY pom.xml .
RUN mvn clean install -DskipTests
COPY . .

FROM openjdk:11-jdk-slim
EXPOSE 8080

COPY --from=0 /usr/src/varmetrics/target/varmetrics.jar ./

#запуск
ENTRYPOINT ["java", "-Dspring.profiles.active=DEV", "-jar", "varmetrics.jar"]
CMD ["java", "Application"]
RUN java -Dspring.profiles.active=DEV -jar varmetrics.jar

MAINTAINER Ilya Shebanov <Shebanov@gmail.com>