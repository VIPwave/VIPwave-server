FROM openjdk:17
ARG JAR_FILE=target/server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} vipwave-server.jar
ENTRYPOINT ["java", "-jar", "/vipwave-server.jar"]
