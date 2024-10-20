FROM amazoncorretto:17-alpine-jdk
LABEL authors="guilherme.ayusso"

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/beauty-manager-api-1.0.0.jar /app/beauty-manager-api.jar

# Copie os recursos da aplicação para o contêiner
COPY src/main/resources/ /app/

# Expose the port your application runs on
EXPOSE 8081

# Command to run the application
CMD ["sh", "-c", "java -jar -Dspring.profiles.active=default beauty-manager-api.jar"]