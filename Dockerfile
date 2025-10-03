# Usa uma imagem leve com JDK 17
FROM openjdk:17-jdk-slim

# Define diretório de trabalho
WORKDIR /app

# Copia o Maven wrapper e arquivos necessários
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dá permissão pro mvnw
RUN chmod +x mvnw

# Baixa dependências
RUN ./mvnw dependency:go-offline

# Copia o código fonte
COPY src ./src

# Builda o jar
RUN ./mvnw package -DskipTests

# Expõe a porta
EXPOSE 8080

# Roda a aplicação
CMD ["java", "-jar", "target/sys-0.0.1-SNAPSHOT.jar"]
