# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/getway-0.0.1-SNAPSHOT.jar getway-0.0.1-SNAPSHOT.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "getway-0.0.1-SNAPSHOT.jar"]
