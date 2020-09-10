# xidianiot/database

1. database模块对应原平台的pojo模块，在原pojo模块基础上添加了dao层

2. database模块负责数据库表映射、自定义实体类、mapper接口支持以及mapper接口实现

3. database模块原则上不提供mongo数据库支持，mongo支持放在了databiz模块

4. database模块不提供service层以及更上层服务

5. database模块依赖mybatis，**不依赖spring相关库**

运行"com.xidian.iot.database.generator.Generator.main"以初始化数据库映射及相应的mapper