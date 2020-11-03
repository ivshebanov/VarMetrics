FROM openjdk:11
ADD target/varmetrics.jar varmetrics.jar
ENTRYPOINT ["java", "-jar", "varmetrics.jar"]
MAINTAINER Ilya Shebanov <Shebanov@gmail.com>