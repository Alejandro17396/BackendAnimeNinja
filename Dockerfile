FROM openjdk:8-jre-slim

# Instala wget
RUN apt-get update && apt-get install -y wget

# Descarga wait-for-it.sh y le da permisos de ejecución
RUN wget https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh -O /wait-for-it.sh && \
    chmod +x /wait-for-it.sh

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["/wait-for-it.sh", "narutoDB:3306", "--", "java","-jar","/app.jar"]
