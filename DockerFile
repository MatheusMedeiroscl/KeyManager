# Etapa de build
FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Cria diretório da aplicação
WORKDIR /app

# Copia os arquivos da aplicação
COPY . .

# Compila o projeto
RUN mvn clean package

# Etapa de runtime
FROM openjdk:17-jdk-slim

EXPOSE 8080

# Copia o .jar da etapa anterior
COPY --from=build /target/keymanager-0.0.1-SNAPSHOT.jar app.jar

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
