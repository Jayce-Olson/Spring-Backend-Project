FROM ubuntu:latest
LABEL authors="Jayce Olson"
FROM openjdk:17-jdk-alpine

COPY target/D387_sample_code-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#ENTRYPOINT ["top", "-b"]