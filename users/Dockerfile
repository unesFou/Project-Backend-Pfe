# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/users-0.0.1-SNAPSHOT.jar users-0.0.1-SNAPSHOT.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "users-0.0.1-SNAPSHOT.jar"]
