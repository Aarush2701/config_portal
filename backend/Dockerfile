# --------------------------------------
# Stage 1: Build the application with Maven
# --------------------------------------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

# Set working directory inside the container
WORKDIR /app

# Copy all project files to container
COPY . .

# Package the application (adjust module name if needed)
RUN mvn clean package -DskipTests

# --------------------------------------
# Stage 2: Run the packaged Spring Boot JAR
# --------------------------------------
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/auth-hub/target/auth-hub-1.0.0.jar app.jar

# Expose the port your app runs on (adjust if different)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
