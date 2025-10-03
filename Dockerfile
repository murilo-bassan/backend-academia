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

# Copia o código fonte
COPY src ./src

# Compila e gera o jar (sem rodar testes)
RUN ./mvnw clean package -DskipTests

# Expõe a porta
EXPOSE 8080

# Roda a aplicação
CMD ["java", "-jar", "target/sys-0.0.1-SNAPSHOT.jar"]
