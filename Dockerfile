# --- Stage 1: Build ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

# Build project ra file WAR
RUN mvn clean package -DskipTests

# --- Stage 2: Runtime with Tomcat ---
FROM tomcat:11.0.13-jdk21-temurin
WORKDIR /usr/local/tomcat/webapps/

# Xóa webapp mặc định của Tomcat (ROOT)
RUN rm -rf ROOT

# Copy file WAR đã build vào Tomcat và đổi tên thành ROOT.war
COPY --from=build /app/target/kmhn.war ./ROOT.war

# Render tự inject PORT env, Tomcat mặc định lắng nghe 8080
EXPOSE 8080

# Lệnh khởi chạy Tomcat
CMD ["catalina.sh", "run"]
