# Étape 1 : build avec Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : run avec Java
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/target/*SNAPSHOT.jar app.jar

# Render injecte PORT automatiquement
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
