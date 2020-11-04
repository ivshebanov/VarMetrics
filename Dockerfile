FROM maven
WORKDIR /usr/src/varmetrics
COPY pom.xml .
RUN mvn clean install -DskipTests
COPY . .

FROM openjdk:11-jdk-slim
EXPOSE 8080

COPY --from=0 /usr/src/varmetrics/target/*.jar ./

ENTRYPOINT ["java", "-Dspring.profiles.active=PROM", "-jar", "varmetrics.jar"]
CMD ["java", "Application"]

MAINTAINER Ilya Shebanov <Shebanov@gmail.com>