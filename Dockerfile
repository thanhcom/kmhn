# --- Stage 1: Build ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --- Stage 2: Runtime ---
FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/
RUN rm -rf ROOT
COPY --from=build /app/target/kmhn.war ./ROOT.war

# --- Fix: make Tomcat listen on Render's PORT ---
ENV PORT=8080
EXPOSE 8080

# Render injects PORT env (e.g. 10000), use it in server.xml
RUN sed -i 's/port="8080"/port="${PORT}"/' /usr/local/tomcat/conf/server.xml && \
    sed -i 's/address="localhost"//g' /usr/local/tomcat/conf/server.xml

CMD ["catalina.sh", "run"]
