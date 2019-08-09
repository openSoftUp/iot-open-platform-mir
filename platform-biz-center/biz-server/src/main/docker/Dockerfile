FROM java:8

MAINTAINER huyong <hy13550323506@gmail.com>

RUN echo "Asia/shanghai" > /etc/timezone;

VOLUME /tmp
ADD biz-server.jar biz.jar
RUN sh -c 'touch /biz.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /biz.jar" ]