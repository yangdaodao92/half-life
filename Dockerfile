FROM gradle:4.2.1-jdk8-alpine
ENV GRADLE_USER_HOME=/project/.gradle
USER root
MAINTAINER yangningxiao123@163.com

COPY build.gradle /tmp/dependencies/
COPY settings.gradle /tmp/dependencies/
COPY core-web/src/main/java/com/halflife/MainApplication.java /tmp/dependencies/core-web/src/main/java/com/halflife/
COPY core-web/build.gradle /tmp/dependencies/core-web/
WORKDIR /tmp/dependencies
RUN gradle build

RUN rm -rf /tmp/dependencies/
COPY . /tmp/dependencies/

WORKDIR /tmp/dependencies/
RUN gradle bootRepackage
RUN mv core-web/build/libs/*.jar /app.jar
WORKDIR /
RUN rm -rf /tmp/dependencies/

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]