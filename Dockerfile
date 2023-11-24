FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn -B dependency:go-offline

COPY src src

RUN mvn -B package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/inscription-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
