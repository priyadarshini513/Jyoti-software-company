# ===== STAGE 1: Build =====
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

# Copy from the CURRENT folder (no subfolder prefix)
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests clean package

# ===== STAGE 2: Runtime =====
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built jar (any name) from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Render injects $PORT; default for local is fine
ENV PORT=9090
EXPOSE 9090

# Start Spring Boot binding to $PORT
ENTRYPOINT ["sh","-c","java -Dserver.port=$PORT -jar /app/app.jar"]
