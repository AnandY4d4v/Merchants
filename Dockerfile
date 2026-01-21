# ---------- Build stage ----------
FROM eclipse-temurin:17-jdk-focal AS builder

WORKDIR /app

# Copy maven wrapper and config
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies first (better caching)
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build jar
RUN ./mvnw clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
