# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y wget
WORKDIR /app
COPY target/vehicle-0.0.1-SNAPSHOT.jar vehicle-0.0.1-SNAPSHOT.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "vehicle-0.0.1-SNAPSHOT.jar"]
