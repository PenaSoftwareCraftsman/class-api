FROM openjdk:17-jdk-slim AS build
WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src ./src
RUN ./mvnw package

FROM openjdk:17
WORKDIR /app
COPY --from=build target/*.jar demo.jar
ENTRYPOINT ["java", "-jar", "demo.jar"]
