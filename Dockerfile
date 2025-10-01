# Stage 1: Build WAR using OpenJDK + Ant
FROM openjdk:8-jdk AS builder
WORKDIR /app

# Install Ant (Debian package inside OpenJDK image)
RUN apt-get update && apt-get install -y ant && rm -rf /var/lib/apt/lists/*

# Copy source
COPY . .

# Run Ant build to create WAR
RUN ant war

# Stage 2: Tomcat runtime
FROM tomcat:8.5-jdk8
WORKDIR /usr/local/tomcat

# Remove default apps
RUN rm -rf webapps/*

# Copy built WAR into Tomcat webapps
COPY --from=builder /app/dist/riverlife-v7-prod.war webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
