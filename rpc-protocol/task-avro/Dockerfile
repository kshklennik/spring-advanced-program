#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /rpc-protocol/task-avro/src
COPY pom.xml rpc-protocol/task-avro/
RUN mvn -f rpc-protocol/task-avro/pom.xml clean package

#
# Run stage
#
FROM bitnami/java:11-prod AS build_package
RUN mkdir -p /opt/avrotask/
COPY --from=0 rpc-protocol/task-avro/target/task-avro-1.0-SNAPSHOT-exec.jar /opt/avrotask/task-avro-1.0-SNAPSHOT-exec.jar
ENV JAVA_OPTS=""
CMD /opt/bitnami/java/bin/java -server ${JAVA_OPTS} -jar /opt/avrotask/task-avro-1.0-SNAPSHOT-exec.jar