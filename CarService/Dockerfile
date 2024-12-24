# Stage 1: Build the application with Maven
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# Copy the entire project (including the pom.xml) to the container
COPY . .

# Run the Maven build to create the jar file
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/CarService-0.0.1-SNAPSHOT.jar application.jar

# Run the application
ENTRYPOINT ["java", "-jar", "application.jar"]
