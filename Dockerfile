FROM gradle:4.2.1-jre8-alpine
RUN mkdir -p /tmp/dependencies/core-web
ADD settings.gradle /tmp/dependencies
ADD core-web/build.gradle /tmp/dependencies/core-web
RUN cd /tmp/dependencies && gradle --refresh-dependencies

COPY . /tmp/build

RUN cd /tmp/build && gradle :web-root:build \
        #拷贝编译结果到指定目录
        && mv core-web/build/libs/*.jar /app.jar \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]