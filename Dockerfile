FROM openjdk:11-jdk-slim
EXPOSE 8080

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} varmetrics.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=DEV", "-jar", "/varmetrics.jar"]
CMD ["java", "Application"]

MAINTAINER Ilya Shebanov <Shebanov@gmail.com>