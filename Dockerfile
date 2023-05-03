FROM openjdk:8-jdk-slim
FROM mysql:latest
FROM node:latest
FROM ubuntu:latest



# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR de Spring Boot al contenedor
COPY backend/api/target/api-abm-persona-0.0.1-SNAPSHOT.jar .

RUN mkdir /nonexistent

RUN apt-get update && apt-get install -y default-jre

# Exponer el puerto 8080 para la aplicación Spring Boot
EXPOSE 8080


# Copiar el código fuente de React al contenedor
COPY frontend/abm-persona /app/frontend

RUN apt-get update
RUN apt-get install -y nodejs npm

# Instalar las dependencias de React
RUN cd frontend && npm install

# Exponer el puerto 4000 para la aplicación React
EXPOSE 4000

RUN apt-get update
RUN apt-get install -y mysql-server

# Copiar el archivo de configuración de MySQL al contenedor
COPY mysql.cnf /etc/mysql/conf.d/mysql.cnf

# Exponer el puerto 3306 para la base de datos MySQL
EXPOSE 3306

ENV MYSQL_DATABASE=abmpersona
ENV MYSQL_USER=personauser
ENV MYSQL_PASSWORD=123456789
ENV MYSQL_ROOT_PASSWORD=123456789
ENV PATH=$PATH:/usr/local/openjdk-8/bin

# Copiar el archivo de configuración de MySQL al contenedor
RUN service mysql start && \
mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "CREATE DATABASE ${MYSQL_DATABASE}" && \
mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "CREATE USER ${MYSQL_USER} IDENTIFIED BY '${MYSQL_PASSWORD}'" && \
mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "GRANT ALL PRIVILEGES ON ${MYSQL_DATABASE}.* TO '${MYSQL_USER}'@'%'" && \
mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "FLUSH PRIVILEGES"

VOLUME /var/lib/mysql

# Comando para ejecutar la aplicación
CMD service mysql start && java -jar api-abm-persona-0.0.1-SNAPSHOT.jar && cd frontend/abm-persona && npm start