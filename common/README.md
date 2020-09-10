# xidianiot/common

1. common模块对应原平台commons-master模块

2. common/common-mq包含了使用jms、activemq所需的依赖以及部分封装好的工具

3. common/common-cache包含了使用redis所需的依赖以及部分封装好的实体类缓存工具；若要使用缓存注解@Cacheable、@CacheEvict、@CachePut，可以直接依赖次此模块

## common-mq模块简介

1. common-mq模块主要提供两个功能：`发送消息`、`订阅消息`

2. 其中订阅消息分为两种模式，第一种是`动态订阅`，第二种是在spring配置文件里`配置监听器`
	
3. common-mq的功能全都体现在com.xidian.iot.common.mq下的三个接口文件，该接口目前有**两个实现：activemq、kafka**，调用者只需关注接口和实现类的注入
	
4. **动态订阅消息的使用方式**见common-mq模块下的测试类，里面提供了发送消息和动态订阅消息的demo

5. **配置监听器的使用方式**见com.xidian.iot.common.mq.*.listener.TestListenerJms 和 spring配置文件中最下方的注释