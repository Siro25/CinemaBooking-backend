# Stage 1: Build Java Spring Boot Application
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Tải dependencies trước để cache Docker layer, giúp các lần build sau nhanh hơn
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy mã nguồn backend và biên dịch ứng dụng
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Minimal Runtime Environment
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Tạo user không có quyền root để tăng tính bảo mật cho container
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy file .jar đã đóng gói từ Stage 1
COPY --from=builder /app/target/*.jar app.jar

# Phân quyền cho appuser
RUN chown appuser:appgroup app.jar
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
