# xidianiot/database

database模块对应原平台的pojo模块，在原pojo模块基础上添加了dao层
database模块负责数据库表映射、自定义实体类、mapper接口支持以及mapper接口实现
database模块原则上不提供mongo数据库支持，mongo支持放在了databiz模块
database模块不提供service层以及更上层服务
database模块依赖mybatis，不依赖mybatis-spring、spring-jdbc库

运行"com.xidian.iot.database.generator.Generator.main"以初始化数据库映射及相应的mapper

对象说明
PO：持久对象 (persistent object)。
是ORM(Objevt Relational Mapping)框架中Entity，PO属性和数据库中表的字段形成一一对应关系。

Entity：
实体，和PO的功能类似，和数据表一一对应，一个实体一张表。

VO：值对象(Value Object)。
表现层对象(View Object)，通常用于业务层之间的数据传递，由new创建，由GC回收。和PO一样也是仅仅包含数据而已，但应是抽象出的业务对象，可以和表对应，也可以不是。

BO：业务对象层的缩写(Business Object)。
BO把业务逻辑封转为一个对象，通过调用DAO方法，结合PO或VO进行业务操作。PO组合，如投保人是一个PO，被保险人是一个PO，险种信息是一个PO等等，他们组合气来是第一张保单的BO。

DTO：数据传输对象(Data Transfer Object)。
就是接口之间传递的数据封装，DTO作用，一是能提高数据传输的速度(减少了传输字段)，二能隐藏后端表结构。类似与VO，用于页面展示时就是VO。