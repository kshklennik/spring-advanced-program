#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

COPY auth-task-app /com/epam/auth/auth-task-app
COPY auth-task-brute-force /com/epam/auth/auth-task-brute-force
COPY auth-task-persistence /com/epam/auth/auth-task-persistence
COPY pom.xml /com/epam/auth
RUN mvn -f /com/epam/auth/pom.xml clean package

#
# Package stage
#
FROM bitnami/java:11-prod AS run
RUN mkdir -p /opt/auth/
COPY --from=0 /com/epam/auth/auth-task-app/target/auth-task-app-1.0-SNAPSHOT-exec.jar /opt/auth/target/auth-task-app-1.0-SNAPSHOT-exec.jar
CMD /opt/bitnami/java/bin/java -server ${JAVA_OPTS} -jar /opt/auth/target/auth-task-app-1.0-SNAPSHOT-exec.jar
