# -----------------------------------------------------------
# ETAPA 1: Build
# Compila el proyecto y genera el JAR con todas las dependencias
# -----------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos primero solo el pom.xml para aprovechar la caché de Docker.
# Si el código cambia pero el pom.xml no, Maven no re-descarga dependencias.
COPY pom.xml .
RUN mvn dependency:resolve -q

# Ahora copiamos el código fuente y compilamos
COPY src ./src
RUN mvn package -DskipTests -q

# -----------------------------------------------------------
# ETAPA 2: Runtime
# Imagen final liviana, solo con lo necesario para correr
# -----------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Creamos el directorio para la base de datos SQLite
RUN mkdir -p /app/db

# Copiamos solo el JAR generado en la etapa de build
COPY --from=build /app/target/proye-is-1.0-SNAPSHOT.jar app.jar

# Puerto que expone Spark
EXPOSE 8080

# Variable de entorno para apuntar la BD al volumen montado
ENV db_url=jdbc:sqlite:/app/db/dev.db

# Comando de arranque
ENTRYPOINT java -Ddb.url=$db_url -jar app.jar