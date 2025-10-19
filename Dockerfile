# --- Stage 1: Build với Maven ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy source code và build WAR
COPY . .
RUN mvn clean package -DskipTests

# --- Stage 2: Runtime với Tomcat 10 ---
FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/

# Xóa ứng dụng mặc định của Tomcat
RUN rm -rf ROOT

# Copy WAR file build ở stage 1 và đặt tên là ROOT.war để Tomcat auto deploy
COPY --from=build /app/target/kmhn.war ./ROOT.war

# Render cung cấp PORT env, Tomcat mặc định 8080
EXPOSE 8080

# Chạy Tomcat
CMD ["catalina.sh", "run"]
