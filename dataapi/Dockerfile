FROM tomcat:8-jdk8
MAINTAINER Hansey

LABEL version="1.0" \
      description="dataapi in docker"

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone \
    rm -rf /usr/local/tomcat/webapps/*

#将target下的xx.war拷贝到/usr/local/tomcat/webapps/下
ADD ./target/**.war /usr/local/tomcat/webapps/

#端口
EXPOSE 8081

#设置启动命令
ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh","run"]

#进入到此dockerfile目录然后生成镜像
#docker build -t hansey97/dataapi .
#docker run -itd -p 8081:8081 --name dataapi hansey97/dataapi:latest
#需要将/etc/hosts文件修改为以下配置
#114.116.228.59 test.public.redis
#114.115.240.14 test.public.mysql
#114.116.228.59 test.public.mongodb
#114.116.228.59 test.public.activemq