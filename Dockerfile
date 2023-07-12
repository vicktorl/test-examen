FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /build

COPY . .

RUN mvn clean package

FROM openjdk:17-slim

EXPOSE 8080

COPY --from=builder /build/target/test-1-0.0.1-SNAPSHOT.jar /app/test.jar

CMD ["java", "-jar", "/app/test.jar"]
