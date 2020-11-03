FROM openjdk:11-jdk-slim
EXPOSE 8080

RUN mkdir -p /usr/src/varmetrics
WORKDIR /usr/src/varmetrics
COPY varmetrics.jar /usr/src/varmetrics/varmetrics.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=PROM", "-jar", "/usr/src/varmetrics/varmetrics.jar"]
CMD ["java", "Application"]

MAINTAINER Ilya Shebanov <Shebanov@gmail.com>