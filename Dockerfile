FROM openjdk:11-jre-slim-buster

COPY target/banking-system-0.0.1-SNAPSHOT.jar /usr/share/banking-system-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/banking-system-0.0.1-SNAPSHOT.jar"]