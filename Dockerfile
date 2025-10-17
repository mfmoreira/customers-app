# dockerfile
FROM gradle:8-jdk21 AS build
WORKDIR /home/gradle/project

# Copia todo o projeto e garante que o wrapper seja executável
COPY --chown=gradle:gradle . .
RUN chmod +x gradlew
RUN ./gradlew clean bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

# Cria usuário não-root
RUN addgroup --system app && adduser --system --ingroup app app || true
USER app

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
