# ===== build =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY JyotiSoftware_pvt_ltd/pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY JyotiSoftware_pvt_ltd/src ./src
# Build and normalize the jar name to /app/app.jar to avoid wildcard COPY issues
RUN mvn -q -DskipTests clean package \
 && bash -lc 'JAR=$(ls target/*.jar | grep -v original | head -n1) && cp "$JAR" /app/app.jar'

# ===== run =====
FROM eclipse-temurin:17-jre
WORKDIR /app
# (Optional hardening) run as non-root
RUN useradd -m app && chown -R app:app /app
USER app

COPY --from=build /app/app.jar app.jar
ENV PORT=9090 JAVA_OPTS=""
EXPOSE 9090
# Spring will read PORT from env; no need for sh -c
ENTRYPOINT ["java","-jar","/app/app.jar"]
# If you really want to hard-force the port from env, you could do:
# ENTRYPOINT ["sh","-c","java -Dserver.port=${PORT:-9090} -jar app.jar"]
