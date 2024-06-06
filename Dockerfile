# Start with a base image containing Java runtime
FROM maven:3.8.3-openjdk-17-slim AS build

# The application's .jar file
ARG JAR_FILE=target/*.jar

# cd into the app directory
WORKDIR /usr/src/app

# Copy the pom.xml file
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:resolve

# Copy your other files
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's .jar file
ARG JAR_FILE=target/*.jar

# Add the application's .jar to the container
COPY --from=build /usr/src/app/${JAR_FILE} app.jar

# Run the .jar file
ENTRYPOINT ["java","-jar","/app.jar"]