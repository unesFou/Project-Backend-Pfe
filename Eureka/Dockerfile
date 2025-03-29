# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y wget
WORKDIR /app
COPY target/Eureka-0.0.1-SNAPSHOT.jar Eureka-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "Eureka-0.0.1-SNAPSHOT.jar"]