# xidianiot

物联网平台2020

[依赖包版本控制模块](./parent)

[公共模块](./common)

[关系型数据库模块](./database)

[公共业务逻辑模块](./databiz)

[非关系型数据接收、处理、持久化](./datacenter)

[对外数据接口、数据可视化操作](./dataapi)

[业务逻辑处理、Service、Mongo](./databiz)

dataapi开发者环境配置可以通过以下三种方式
development 开发环境
production 生产环境
1、修改web.xml中的
spring.profiles.default
默认为生产环境
2、在tomcat启动脚本start.sh中加入以下 JVM 参数
-Dspring.profiles.active="development"
3、idea打开tomcat调试的时候在 VM options 添加以下参数
-Dspring.profiles.active="development"

datacenter开发者环境配置可以通过以下三种方式
1、修改application.properties中的spring.profiles.active
2、java -jar datacenter.jar --spring.profiles.active=development
3、idea打开Application的配置 在 VM options 添加以下参数
-Dspring.profiles.active="development"
