# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Appartement-0.0.1-SNAPSHOT.jar Appartement-0.0.1-SNAPSHOT.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "Appartement-0.0.1-SNAPSHOT.jar"]
