# ===== STAGE 1: Build the application =====
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY JyotiSoftware_pvt_ltd/pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY JyotiSoftware_pvt_ltd/src ./src
RUN mvn -q -DskipTests clean package

# ===== STAGE 2: Runtime =====
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENV PORT=9090
EXPOSE 9090
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar /app/app.jar"]
