FROM openjdk:18 as build
WORKDIR /app

COPY ./target/*SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]