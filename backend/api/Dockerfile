# Definir la imagen base
FROM openjdk:8-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos de la aplicación
COPY backend/api/target/api-abm-persona-0.0.1-SNAPSHOT.jar api-abm-persona-0.0.1-SNAPSHOT.jar
COPY frontend/abm-persona/build/ frontend/

# Instalar MySQL
RUN apt-get update && \
    apt-get install -y mysql-server && \
    rm -rf /var/lib/apt/lists/*

# Configurar MySQL
COPY database.sql /docker-entrypoint-initdb.d/

# Exponer los puertos
EXPOSE 8080
EXPOSE 3306

# Establecer las variables de entorno para MySQL
ENV MYSQL_DATABASE=abm-persona
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=123456789
ENV MYSQL_ROOT_PASSWORD=123456789

# Agregar un volumen para la base de datos
VOLUME /var/lib/mysql

# Iniciar la aplicación
CMD ["java", "-jar", "api-abm-persona.jar"]
