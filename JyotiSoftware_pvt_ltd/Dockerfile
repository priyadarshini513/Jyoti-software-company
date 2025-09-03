# ===== build =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

# ===== run =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# sensible JVM limits for Render free DB/web (256 MB RAM)
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError -XX:+UseG1GC"

# non-root
RUN useradd -m app && chown -R app:app /app
USER app

# copy fat jar
COPY --from=build /app/target/*.jar /app/app.jar

# optional; Render uses $PORT
EXPOSE 10000

# bind to Render's $PORT and all interfaces; DO NOT force any Spring profile
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar --server.address=0.0.0.0 --server.port=${PORT:-10000}"]
