# BUILD STAGE
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# PACKAGE STAGE
FROM openjdk:11-jre-slim-buster
COPY --from=build /home/app/target/banking-system-1.jar /usr/src/banking-system/
WORKDIR /usr/src/banking-system
ENTRYPOINT ["java","-jar","banking-system-1.jar"]