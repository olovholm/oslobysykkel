FROM adoptopenjdk/openjdk11
MAINTAINER ola@lovholm.net
VOLUME /tmp
ARG JAR_FILE=target/application.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
