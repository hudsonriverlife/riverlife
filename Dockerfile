# Tomcat libs only (Ant compile needs catalina.dir/lib — see build.xml)
FROM tomcat:9.0-jdk8-temurin AS tomcatlibs

# Stage 1: Build WAR using JDK 8 + Ant
FROM eclipse-temurin:8-jdk AS builder
WORKDIR /app

RUN apt-get update && apt-get install -y ant && rm -rf /var/lib/apt/lists/*

# build.xml sets catalina.dir=tomcat
COPY --from=tomcatlibs /usr/local/tomcat /app/tomcat

COPY . .
RUN ant war

# Stage 2: Tomcat runtime (8.5 image tags retired; 9.0 + JDK 8 is the maintained path)
FROM tomcat:9.0-jdk8-temurin
WORKDIR /usr/local/tomcat

RUN rm -rf webapps/*

COPY --from=builder /app/dist/riverlife-v8-prod.war webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
