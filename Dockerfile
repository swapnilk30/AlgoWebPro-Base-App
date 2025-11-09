# Start from a Java 17 runtime
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file (replace with your actual JAR name)
COPY target/AlgoWebPro-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
