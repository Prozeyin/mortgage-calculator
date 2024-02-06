FROM maven:3.9.6-amazoncorretto-17 AS build

# Copy the project files into the image
COPY src /home/app/src
COPY pom.xml /home/app

# Package the application
RUN mvn -f /home/app/pom.xml clean package

# Use the official OpenJDK image to run the application
FROM openjdk:17

# Copy the JAR from the 'build' stage into this new stage
COPY --from=build /home/app/target/mortgage-calculator-1.0-SNAPSHOT.jar /usr/local/lib/mortgage-calculator.jar

# Set the entrypoint to run the jar file
ENTRYPOINT ["java","-jar","/usr/local/lib/mortgage-calculator.jar"]
