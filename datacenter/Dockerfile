FROM openjdk:8-jre-slim
MAINTAINER mrl
LABEL version="1" \
      description="datacenter in docker"

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone \
    mkdir -p /app/datacenter

WORKDIR /app/datacenter
ADD target/jar/*.jar ./

EXPOSE 9999

VOLUME ["/var/log/xidianiot"]
ENTRYPOINT java -jar datacenter.jar

#docker run -d --name datacenter -p 9999:9999 \
#   -v /etc/hosts:/etc/hosts \
#   -v /var/log/xidianiot:/var/log/xidianiot \
#   [imageId]

#/etc/hosts 需要配置的主机名:
#iot.pro.mysql
#iot.pro.mongodb
#iot.pro.activemq
#iot.pro.redis