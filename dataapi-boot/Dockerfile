FROM openjdk:8-jre-slim
MAINTAINER hansey
LABEL version="1" \
      description="dataapi in docker"

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone \
    mkdir -p /app/datacenter

WORKDIR /app/dataapi
ADD target/jar/*.jar ./

EXPOSE 8081

VOLUME ["/var/log/xidianiot"]
ENTRYPOINT java -jar dataapi-boot.jar

#进入到此dockerfile目录然后生成镜像
#docker build -t hansey97/dataapi .
#docker run -d --name dataapi -p 8080:8080 \
#   -v /etc/hosts:/etc/hosts \
#   -v /var/log/xidianiot:/var/log/xidianiot \
#   [imageId]
#需要将/etc/hosts文件修改为以下配置
#iot.pro.mysql
#iot.pro.mongodb
#iot.pro.activemq
#iot.pro.redis