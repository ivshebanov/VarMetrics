FROM openjdk:11-jdk-slim
EXPOSE 8080

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-Dspring.profiles.active=DEV", "-cp","dependency:dependency/lib/*", "/varmetrics.jar"]
CMD ["java", "Application"]

MAINTAINER Ilya Shebanov <Shebanov@gmail.com>