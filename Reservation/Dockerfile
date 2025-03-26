# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Reservation-0.0.1-SNAPSHOT.jar Reservation-0.0.1-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "Reservation-0.0.1-SNAPSHOT.jar"]
