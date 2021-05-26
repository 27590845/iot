FROM openjdk:8-jre-slim
MAINTAINER hansey
LABEL version="1" \
      description="dataapi in docker"

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone \
    mkdir -p /app/dataapi

WORKDIR /app/dataapi
ADD target/dataapi-boot-1.0-SNAPSHOT.jar ./

EXPOSE 8081

VOLUME ["/var/log/xidianiot"]
ENTRYPOINT java -jar dataapi-boot-1.0-SNAPSHOT.jar --spring.profiles.active=development

#进入到此dockerfile目录然后生成镜像
#docker build -t hansey97/dataapi .
#docker run -itd --name dataapi -p 8080:8081 \
#   -v /etc/hosts:/etc/hosts \
#   -v /var/log/xidianiot:/var/log/xidianiot \
#   [imageId]
#需要将/etc/hosts文件修改为以下配置
#mysql-iot
#mongodb-iot
#activemq-iot
#redis-iot