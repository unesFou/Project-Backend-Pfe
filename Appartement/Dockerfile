# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y wget
WORKDIR /app
COPY target/Appartement-0.0.1-SNAPSHOT.jar Appartement-0.0.1-SNAPSHOT.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "Appartement-0.0.1-SNAPSHOT.jar"]
