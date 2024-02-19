FROM openjdk:18 AS build
WORKDIR /app

COPY ./target/*SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
