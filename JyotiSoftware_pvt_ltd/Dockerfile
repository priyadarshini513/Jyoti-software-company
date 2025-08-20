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
COPY --from=build /app/target/*.jar /app/app.jar

ENV PORT=9090 JAVA_OPTS=""
EXPOSE 9090
ENTRYPOINT ["sh","-c","java -Dserver.port=${PORT:-9090} $JAVA_OPTS -jar /app/app.jar"]
