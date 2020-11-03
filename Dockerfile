FROM openjdk:11-jdk-slim
EXPOSE 8080

RUN mkdir -p /usr/src/varmetrics
WORKDIR /usr/src/varmetrics
COPY . /usr/src/varmetrics

ENTRYPOINT ["/usr/src/varmetrics", "-Dspring.profiles.active=PROM", "-jar", "varmetrics.jar"]
CMD ["java", "Application"]

MAINTAINER Ilya Shebanov <Shebanov@gmail.com>