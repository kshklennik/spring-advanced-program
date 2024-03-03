#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

COPY jmp-app /com/epam/jmpapp/jmp-app
COPY jmp-cloud-service-impl /com/epam/jmpapp/jmp-cloud-service-impl
COPY jmp-dto /com/epam/jmpapp/jmp-dto
COPY jmp-service-api /com/epam/jmpapp/jmp-service-api
COPY jmp-service-rest /com/epam/jmpapp/jmp-service-rest
COPY pom.xml /com/epam/jmpapp
RUN mvn -f /com/epam/jmpapp/pom.xml clean package

#
# Package stage
#
FROM bitnami/java:11-prod AS build_package
RUN mkdir -p /opt/jmpapp/
COPY --from=0 /com/epam/jmpapp/jmp-app/target/jmp-app-1.0.0-exec.jar /opt/jmpapp/target/jmp-app-1.0.0-exec.jar
CMD /opt/bitnami/java/bin/java -server ${JAVA_OPTS} -jar /opt/jmpapp/target/jmp-app-1.0.0-exec.jar
