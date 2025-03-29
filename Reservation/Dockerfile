# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y wget
WORKDIR /app
COPY target/Reservation-0.0.1-SNAPSHOT.jar Reservation-0.0.1-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "Reservation-0.0.1-SNAPSHOT.jar"]
