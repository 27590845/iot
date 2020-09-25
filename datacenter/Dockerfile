FROM openjdk:8-jre-slim
MAINTAINER mrl
LABEL version="1.0" \
      description="datacenter in docker"

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone \
    mkdir -p /app/datacenter

WORKDIR /app/datacenter
ADD target/jar/*.jar ./

VOLUME ["/var/log"]
ENTRYPOINT java -jar datacenter.jar

#docker run -d --name datacenter -p 9999:9999 \
#   -v /Users/liuyinggang/docker/etc/hosts:/etc/hosts \
#   -v /Users/liuyinggang/docker/var/log:/var/log \
#   [imageId]

#/etc/hosts 需要配置的主机名:
#192.168.124.8	redis1
#192.168.124.8	mysql1
#192.168.124.8	mongodb1
#192.168.124.8	activemq1