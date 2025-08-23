# ===== build =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# ===== run =====
FROM eclipse-temurin:17-jre
WORKDIR /app
RUN useradd -m app && chown -R app:app /app
USER app

# copy the fat jar (adjust pattern if needed)
COPY --from=build /app/target/*.jar /app/app.jar

# EXPOSE is optional on Render; keep it tidy
EXPOSE 10000

# Bind to Render's $PORT (fallback 10000) and all interfaces
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-docker} --server.address=0.0.0.0 --server.port=${PORT:-10000}"]
