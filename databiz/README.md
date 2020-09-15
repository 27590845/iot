# xidianiot/databiz

databiz模块提供service层以及相关拓展服务

databiz模块提供Uid生成策略，需要在数据库建立相应的表"WORKER_NODE"

uid生成策略生命周期会在项目启动时交由spring管理，[具体说明点这里](https://github.com/baidu/uid-generator)