# Utiliza la imagen oficial de Maven y especifica Java 17 para compilar la aplicación
FROM maven:3.8.2-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Utiliza la imagen oficial de OpenJDK 17 para ejecutar la aplicación
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/integracioncontinua-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
