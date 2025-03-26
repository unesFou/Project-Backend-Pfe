# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Service-Annonce-0.0.1-SNAPSHOT.jar Service-Annonce-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "Service-Annonce-0.0.1-SNAPSHOT.jar"]
