#сборка
FROM maven
COPY pom.xml .
RUN mvn clean install -DskipTests
COPY . .

FROM openjdk:11-jdk-slim

WORKDIR /usr/src/varmetrics
COPY target/varmetrics.jar ./
COPY entry_point.sh /docker-entrypoint.sh

RUN chmod +x /docker-entrypoint.sh
CMD ["/docker-entrypoint.sh"]


#запуск
#ENTRYPOINT ["java", "-Dspring.profiles.active=DEV", "-jar", "varmetrics.jar"]
#CMD ["java", "Application"]

EXPOSE 8080
MAINTAINER Ilya Shebanov <Shebanov@gmail.com>