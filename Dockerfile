FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
ARG PROFILE=dev
ARG APP_VERSION=1.0.0
COPY  --from=build /build/target/restaurant-*.jar app.jar

EXPOSE 8080

ENV DB_URL=jdbc:mysql://mysql-restaurant:3306/restaurant_api
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

CMD ["java", "-jar", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Dspring.datasource.url=${DB_URL}", "app.jar"]

