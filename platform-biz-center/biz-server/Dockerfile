FROM java:8
MAINTAINER huyong <hy13550323506@gmail.com>

RUN echo "Asia/shanghai" > /etc/timezone;

VOLUME /tmp
ADD biz-server.jar /biz.jar

EXPOSE 8888

ENTRYPOINT ["java","-Xmx1024m","-XX:MaxDirectMemorySize=100M","-Djava.security.egd=file:/dev/./urandom","-jar","/biz.jar"]