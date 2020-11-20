-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: iotdata_tmp
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `node`
--

DROP TABLE IF EXISTS `node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node` (
  `node_id` bigint NOT NULL COMMENT '节点ID',
  `scene_id` bigint NOT NULL COMMENT '场景ID，一个节点对应一个场景，一个场景可包含多个节点',
  `scene_sn` char(20) NOT NULL COMMENT '网关SN',
  `node_name` varchar(40) NOT NULL COMMENT '节点名称',
  `node_desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `node_sn` varchar(255) NOT NULL COMMENT '节点物理节点号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`node_id`),
  KEY `scene_id` (`scene_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点表，一个场景下有多个节点。逻辑上可看作一个数据单元；物理上可看作数据采集设备，比如采集卡';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node`
--

LOCK TABLES `node` WRITE;
/*!40000 ALTER TABLE `node` DISABLE KEYS */;
/*!40000 ALTER TABLE `node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_act_alert`
--

DROP TABLE IF EXISTS `node_act_alert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_act_alert` (
  `naa_id` bigint NOT NULL,
  `nt_id` bigint NOT NULL,
  `naa_type` tinyint NOT NULL DEFAULT '3' COMMENT '警报类型 1短信 2email 3站内信',
  `naa_val` varchar(60) DEFAULT NULL COMMENT '发警报对象的数值',
  `naa_content` varchar(200) DEFAULT NULL COMMENT '发送内容',
  PRIMARY KEY (`naa_id`),
  KEY `nt_id` (`nt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一旦一个节点触发器被触发，就会有一个报警信息被发出';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_act_alert`
--

LOCK TABLES `node_act_alert` WRITE;
/*!40000 ALTER TABLE `node_act_alert` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_act_alert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_act_cmd`
--

DROP TABLE IF EXISTS `node_act_cmd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_act_cmd` (
  `nac_id` bigint NOT NULL,
  `nc_id` bigint NOT NULL COMMENT '关联的命令，node_cmd.nc_id',
  `nt_id` bigint NOT NULL,
  PRIMARY KEY (`nac_id`),
  KEY `nt_id` (`nt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一旦一个节点触发器被触发，可能会执行多个命令';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_act_cmd`
--

LOCK TABLES `node_act_cmd` WRITE;
/*!40000 ALTER TABLE `node_act_cmd` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_act_cmd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_attr`
--

DROP TABLE IF EXISTS `node_attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_attr` (
  `na_id` bigint NOT NULL COMMENT '节点属性ID',
  `node_id` bigint NOT NULL COMMENT '节点ID，一个节点属性对应一个节点，一个节点有多个节点属性',
  `na_key` varchar(20) NOT NULL COMMENT '属性标识',
  `na_name` varchar(20) DEFAULT NULL COMMENT '属性名称',
  `na_unit` varchar(6) DEFAULT NULL COMMENT '属性单位',
  `na_sym` varchar(6) DEFAULT NULL COMMENT '属性符号',
  `scene_sn` varchar(24) NOT NULL COMMENT '场景SN',
  `node_sn` varchar(24) NOT NULL COMMENT '节点SN',
  PRIMARY KEY (`na_id`),
  UNIQUE KEY `nodeId_attrKey` (`node_id`,`na_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点属性表，一个节点下有多个属性。逻辑上可看作数据单元里的一个字段；物理上可看作底层感知设备，比如传感器';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_attr`
--

LOCK TABLES `node_attr` WRITE;
/*!40000 ALTER TABLE `node_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_attr_std`
--

DROP TABLE IF EXISTS `node_attr_std`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_attr_std` (
  `nas_id` bigint NOT NULL COMMENT '节点属性模版ID',
  `nas_key` varchar(200) DEFAULT NULL COMMENT '主属性的归一化表示',
  `nas_desc` varchar(200) DEFAULT NULL COMMENT '对主属性的描述',
  `nas_unit` varchar(200) DEFAULT NULL COMMENT '主属性单位',
  `nas_sym` varchar(200) DEFAULT NULL COMMENT '主属性符号',
  PRIMARY KEY (`nas_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点属性模版，记录了一类节点属性的元数据，比如记录了某节点属性的计数单位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_attr_std`
--

LOCK TABLES `node_attr_std` WRITE;
/*!40000 ALTER TABLE `node_attr_std` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_attr_std` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_cmd`
--

DROP TABLE IF EXISTS `node_cmd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_cmd` (
  `nc_id` bigint NOT NULL COMMENT '节点命令ID',
  `node_id` bigint NOT NULL COMMENT '节点ID，一个节点命令对应一个节点，一个节点有多个命令',
  `nc_content` varchar(40) NOT NULL COMMENT '命令内容',
  `nc_name` varchar(40) DEFAULT NULL COMMENT '命令中文标题',
  `scene_sn` varchar(24) NOT NULL COMMENT '场景SN',
  `node_sn` varchar(24) NOT NULL COMMENT '节点SN',
  PRIMARY KEY (`nc_id`),
  KEY `node_id` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点命令，节点命令的接收者是通信终端，一般通信终端会通过寻址机制将收到的命令作用于某个数据采集设备，命令可分为“收集数据“”调整参数”两类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_cmd`
--

LOCK TABLES `node_cmd` WRITE;
/*!40000 ALTER TABLE `node_cmd` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_cmd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_cmd_group`
--

DROP TABLE IF EXISTS `node_cmd_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_cmd_group` (
  `ncg_id` bigint NOT NULL COMMENT '节点命令组ID',
  `node_id` bigint NOT NULL COMMENT '节点ID，一个命令组对应一个节点，具体关系待商榷',
  `ncg_name` varchar(40) DEFAULT NULL COMMENT '节点命令组名称',
  `ncg_key` varchar(40) DEFAULT NULL COMMENT '节点命令组key',
  PRIMARY KEY (`ncg_id`),
  KEY `key_unique` (`node_id`,`ncg_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点命令组，发不同类型的控制命令时，会对应不同的的处理方式，该表对控制命令进行了分类，比如“视频”“转动““频率“等不同类别的动作；一个命令对应到一个命令组，一个命令组可包含多条命令';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_cmd_group`
--

LOCK TABLES `node_cmd_group` WRITE;
/*!40000 ALTER TABLE `node_cmd_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_cmd_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_cmd_group_std`
--

DROP TABLE IF EXISTS `node_cmd_group_std`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_cmd_group_std` (
  `ncgs_id` bigint NOT NULL COMMENT '命令组模版ID',
  `ncgs_name` varchar(200) NOT NULL COMMENT '命令组名称',
  `ncgs_key` varchar(200) NOT NULL COMMENT '命令组属性',
  PRIMARY KEY (`ncgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点命令组名称模版，node_cmd_group_std.ncgs_name对应node_cmd_group.ncg_name，供添加命令组选择“命令组名称“';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_cmd_group_std`
--

LOCK TABLES `node_cmd_group_std` WRITE;
/*!40000 ALTER TABLE `node_cmd_group_std` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_cmd_group_std` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_cmd_prot`
--

DROP TABLE IF EXISTS `node_cmd_prot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_cmd_prot` (
  `ncp_id` bigint NOT NULL COMMENT '节点命令协议ID',
  `node_id` bigint NOT NULL COMMENT '节点ID，一个节点命令协议对应一个节点，一个节点一般有两个节点命令协议：数据上传协议、命令下发协议',
  `ncp_type` varchar(10) NOT NULL COMMENT '与此节点通信(虽然通信的基本单位是scene，但是scene会通过寻址机制定位到某一节点，所以下发的命令最终作用于节点，上传的数据也是以节点为数据单元；scene每次上传的数据的格式应该是：以数据单元为元素的数组，再附加一些scene、node本身的信息)采用的协议类型',
  `ncp_down` varchar(200) NOT NULL COMMENT '数据上传格式',
  `ncp_up` varchar(200) NOT NULL COMMENT '命令下发格式',
  PRIMARY KEY (`ncp_id`),
  KEY `node_id` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点通信协议，一个节点可以对应一个数据上传协议，和一个命令下发协议';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_cmd_prot`
--

LOCK TABLES `node_cmd_prot` WRITE;
/*!40000 ALTER TABLE `node_cmd_prot` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_cmd_prot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_cmd_std`
--

DROP TABLE IF EXISTS `node_cmd_std`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_cmd_std` (
  `ncs_id` bigint NOT NULL COMMENT '节点命令模版ID',
  `ncs_key` varchar(200) NOT NULL COMMENT '命令属性',
  `ncs_name` varchar(200) NOT NULL COMMENT '命令属性名称',
  PRIMARY KEY (`ncs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点命令名称模版，node_cmd_std.ncs_name字段对应node_cmd.nc_name，供添加命令选择“控制名称”';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_cmd_std`
--

LOCK TABLES `node_cmd_std` WRITE;
/*!40000 ALTER TABLE `node_cmd_std` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_cmd_std` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_cond`
--

DROP TABLE IF EXISTS `node_cond`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_cond` (
  `nc_id` bigint NOT NULL COMMENT '节点触发器触发条件ID',
  `na_id` bigint NOT NULL COMMENT '节点属性ID，一个条件对应一个节点属性，一个节点属性有多个条件，条件的作用(判断)对象是节点属性',
  `nt_id` bigint NOT NULL COMMENT '节点触发器ID，一个条件对应一个节点触发器，一个节点触发器有多个条件',
  `nc_op` tinyint NOT NULL COMMENT '操作符1>2>=3<4<=5==6新值7冻结8复活',
  `nc_val` varchar(60) DEFAULT NULL COMMENT '条件满足的数值',
  `node_sn` varchar(24) DEFAULT NULL COMMENT '节点SN',
  `scene_sn` varchar(24) DEFAULT NULL COMMENT '场景SN',
  `nc_fit_time` int DEFAULT '1' COMMENT '满足条件几次才算条件匹配成功',
  PRIMARY KEY (`nc_id`),
  KEY `na_id` (`na_id`),
  KEY `nt_id` (`nt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点触发器条件，一个节点可配置多个条件，一个条件对应一个触发器；一次“添加关联”操作可添加多个条件以及生成一个触发器(这些条件可能对应到不同节点，甚至不同场景下的不同节点)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_cond`
--

LOCK TABLES `node_cond` WRITE;
/*!40000 ALTER TABLE `node_cond` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_cond` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_trig`
--

DROP TABLE IF EXISTS `node_trig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node_trig` (
  `nt_id` bigint NOT NULL COMMENT '节点触发器ID',
  `nt_name` varchar(60) DEFAULT NULL COMMENT '节点触发器名称',
  `nt_desc` varchar(200) DEFAULT NULL COMMENT '节点触发器描述',
  `nt_invl` int DEFAULT '0' COMMENT '间隔时间，单位为秒',
  `nt_rept` tinyint DEFAULT '0' COMMENT '是否重复执行 0重复 1不重复',
  `nt_exec` tinyint DEFAULT '0' COMMENT '是否执行 0执行 1不执行',
  `nt_expr` datetime DEFAULT '9999-12-31 23:59:59' COMMENT '失效时间',
  PRIMARY KEY (`nt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点触发器，一个节点可配置多个触发器，一个触发器也可对应多个节点，甚至不同场景下的节点；一个触发器下的全部条件都满足时才会触发';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_trig`
--

LOCK TABLES `node_trig` WRITE;
/*!40000 ALTER TABLE `node_trig` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_trig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scene`
--

DROP TABLE IF EXISTS `scene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scene` (
  `scene_id` bigint NOT NULL COMMENT '场景ID',
  `scene_sn` char(20) NOT NULL COMMENT '场景网关号',
  `scene_name` varchar(40) NOT NULL COMMENT '场景名称',
  `scene_loc` varchar(60) DEFAULT NULL COMMENT '地点名称',
  `scene_lng` double DEFAULT NULL COMMENT '经度',
  `scene_lat` double DEFAULT NULL COMMENT '纬度',
  `scene_el` double DEFAULT NULL COMMENT '海拔',
  `scene_desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`scene_id`),
  KEY `idx_scene_sn_value` (`scene_sn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场景表，场景是数据上传与命令下发的最基本单位。逻辑上可看作管理着多个数据单元的交互窗口；物理上可看作通信终端，比如dtu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scene`
--

LOCK TABLES `scene` WRITE;
/*!40000 ALTER TABLE `scene` DISABLE KEYS */;
/*!40000 ALTER TABLE `scene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_letter`
--

DROP TABLE IF EXISTS `site_letter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_letter` (
  `sl_id` bigint NOT NULL,
  `is_read` smallint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `current_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_letter`
--

LOCK TABLES `site_letter` WRITE;
/*!40000 ALTER TABLE `site_letter` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_letter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker_node`
--

DROP TABLE IF EXISTS `worker_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker_node` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '自增的wordIdauto increment id',
  `HOST_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务器节点ip地址host name',
  `PORT` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务器节点端口port',
  `TYPE` int NOT NULL COMMENT '服务器节点类型、在docket容器中还是实际的机器node type: ACTUAL or CONTAINER',
  `LAUNCH_DATE` date NOT NULL COMMENT '登录时间',
  `MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='DB WorkerID Assigner for UID Generator';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker_node`
--

LOCK TABLES `worker_node` WRITE;
/*!40000 ALTER TABLE `worker_node` DISABLE KEYS */;
/*!40000 ALTER TABLE `worker_node` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-17 10:57:52
