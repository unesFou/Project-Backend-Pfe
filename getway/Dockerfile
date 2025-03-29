# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y wget
WORKDIR /app
COPY target/getway-0.0.1-SNAPSHOT.jar getway-0.0.1-SNAPSHOT.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "getway-0.0.1-SNAPSHOT.jar"]
