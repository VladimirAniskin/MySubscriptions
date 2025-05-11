FROM openjdk:17-jdk-slim

WORKDIR /app

COPY gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "gateway.jar"]