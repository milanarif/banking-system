FROM openjdk:11-jre-slim-buster

COPY ./target/banking-system-1.jar /usr/src/banking-system/

WORKDIR /usr/src/banking-system

ENTRYPOINT ["java","-jar","banking-system-1.jar"]