FROM openjdk:11-jdk-slim
EXPOSE 8080

COPY target/varmetrics.jar varmetrics.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=PROM", "-jar", "/usr/src/varmetrics/varmetrics.jar"]
CMD ["java", "Application"]

MAINTAINER Ilya Shebanov <Shebanov@gmail.com>