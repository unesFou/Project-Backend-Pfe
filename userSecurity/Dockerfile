# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y wget
WORKDIR /app
COPY target/userSecurity-1.0-SNAPSHOT.jar userSecurity-1.0-SNAPSHOT.jar
EXPOSE 8068
ENTRYPOINT ["java", "-jar", "userSecurity-1.0-SNAPSHOT.jar"]
