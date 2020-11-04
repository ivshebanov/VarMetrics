FROM openjdk:11-jdk-slim
EXPOSE 8080

WORKDIR /usr/src/varmetrics
COPY /var/lib/docker/tmp/docker-builder354133389/varmetrics.jar .

ENTRYPOINT ["java", "-Dspring.profiles.active=PROM", "-jar", "varmetrics.jar"]
CMD ["java", "Application"]

MAINTAINER Ilya Shebanov <Shebanov@gmail.com>